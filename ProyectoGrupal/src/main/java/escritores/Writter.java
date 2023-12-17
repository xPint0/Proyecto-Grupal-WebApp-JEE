package escritores;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import controller.Dato;


public class Writter {

	private static String filePath;

	public Writter(String relativePath) {
		this.filePath = relativePath;
	}

	public static boolean escribirCSV(List<String> datos) {
		try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, true))) {

			// Escribir el nuevo registro
			writer.write(String.join(",", datos));
			writer.newLine();

			return true; // La escritura se realizó correctamente
		} catch (IOException e) {
			e.printStackTrace();
			return false; // Se produjo un error durante la escritura
		}
	}
	
	public static boolean escribirXML(String xmlPath, String periodo, String tipoVehiculo, String combustible, String unidades) {
        try {
            // Crear el documento XML a partir del archivo existente
            File xmlFile = new File(xmlPath);
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(xmlFile);

            // Obtener el elemento raíz del documento
            Element root = doc.getDocumentElement();

            // Crear un nuevo elemento 'row' con los datos proporcionados
            Element nuevoRow = doc.createElement("row");
            nuevoRow.setAttribute("Período", periodo);
            nuevoRow.setAttribute("Tipo_de_vehículo", tipoVehiculo);
            nuevoRow.setAttribute("Combustible", combustible);
            nuevoRow.setAttribute("Unidades", unidades);

            // Añadir el nuevo elemento al elemento raíz
            root.appendChild(nuevoRow);

            // Escribir los cambios de vuelta al archivo XML
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(xmlFile);
            transformer.transform(source, result);

            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

	public static boolean escribirJSON(String filePath, String nuevoPeriodo, String nuevoTipoVehiculo,
			String nuevoCombustible, String nuevasUnidades) {
		// Objeto que leerá y escribirá el JSON
		ObjectMapper objectMapper = new ObjectMapper();

		try {
			// Lee el JSON desde el archivo
			JsonNode jsonNode = objectMapper.readTree(new File(filePath));

			// Procesamos y creamos los datos existentes
			List<Dato> datos = new ArrayList<>();
			for (JsonNode node : jsonNode) {
				String periodo = node.get("Período").asText();
				String tipoVehiculo = node.get("Tipo de vehículo").asText();
				String combustible = node.get("Combustible").asText();
				String unidades = node.get("Unidades").asText();

				Dato dato = new Dato(periodo, tipoVehiculo, combustible, unidades);
				datos.add(dato);
			}

			// Agregar nuevos datos si se enviaron desde el formulario
			if (nuevoPeriodo != null && nuevoTipoVehiculo != null && nuevoCombustible != null
					&& nuevasUnidades != null) {
				// Crear un nuevo objeto Dato con los datos del formulario
				Dato nuevoDato = new Dato(nuevoPeriodo, nuevoTipoVehiculo, nuevoCombustible, nuevasUnidades);

				// Agregar el nuevo dato a la lista existente
				datos.add(nuevoDato);
			}

			// Crear un nuevo nodo JSON con los datos actualizados
			ArrayNode arrayNode = objectMapper.createArrayNode();
			for (Dato dato : datos) {
				ObjectNode objectNode = objectMapper.createObjectNode();
				objectNode.put("Período", dato.getPeriodo());
				objectNode.put("Tipo de vehículo", dato.getTipoVehiculo());
				objectNode.put("Combustible", dato.getCombustible());
				objectNode.put("Unidades", dato.getUnidades());
				arrayNode.add(objectNode);
			}

			// Escribir el JSON actualizado al archivo
			objectMapper.writeValue(new File(filePath), arrayNode);
			return true;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
		
	}
	public static boolean escribirXLS(String filePath, String nuevoPeriodo, String nuevoTipoVehiculo,
            String nuevoCombustible, String nuevasUnidades) {
		try {
			File file = new File(filePath);
			Workbook workbook;
		
		if (file.exists()) {
			FileInputStream fileInputStream = new FileInputStream(file);
			workbook = new XSSFWorkbook(fileInputStream);
		} else {
			workbook = new XSSFWorkbook();
		}
		
			Sheet sheet = workbook.getSheetAt(0);
			if (sheet == null) {
			sheet = workbook.createSheet("Hoja1");
		}
		
			int rowNum = sheet.getLastRowNum() + 1;
		
			Row row = sheet.createRow(rowNum);
		
			Cell periodoCell = row.createCell(0);
			periodoCell.setCellValue(nuevoPeriodo);
		
			Cell tipoVehiculoCell = row.createCell(1);
			tipoVehiculoCell.setCellValue(nuevoTipoVehiculo);
		
			Cell combustibleCell = row.createCell(2);
			combustibleCell.setCellValue(nuevoCombustible);
		
			Cell unidadesCell = row.createCell(3);
			unidadesCell.setCellValue(nuevasUnidades);
		
			// Cerrar el libro de trabajo antes de escribir en el archivo
			try (FileOutputStream fileOut = new FileOutputStream(filePath)) {
			workbook.write(fileOut);
		}
		
			workbook.close();
			return true;
		} catch (Exception e) {
			System.out.println("Error al escribir en el archivo XLS: " + e.getMessage());
			return false;
		}
		}
	

}
