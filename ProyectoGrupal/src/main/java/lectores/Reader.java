package lectores;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import controller.Dato;

public class Reader {
	
	
	public static List<Dato> readXLS(String nombreArchivo) {
        List<Dato> datos = new ArrayList<>();

        try (FileInputStream fileInputStream = new FileInputStream(nombreArchivo)) {
            Workbook workbook = new XSSFWorkbook(fileInputStream);
            Sheet sheet = workbook.getSheetAt(0); // Obtener la hoja (sheet) deseada, en este caso la primera hoja (índice 0)

            for (Row row : sheet) {
                // Saltar la primera fila (encabezados)
                if (row.getRowNum() == 0) {
                    continue;
                }

                Cell periodoCell = row.getCell(0);
                Cell tipoVehiculoCell = row.getCell(1);
                Cell combustibleCell = row.getCell(2);
                Cell unidadesCell = row.getCell(3);

                Double periodoD = periodoCell.getNumericCellValue();
                Integer periodoI = periodoD.intValue();
                String periodo = periodoI.toString();
                String tipoVehiculo = tipoVehiculoCell.getStringCellValue();
                String combustible = combustibleCell.getStringCellValue();
                Double unidadesD = unidadesCell.getNumericCellValue();
                Integer unidadesI = unidadesD.intValue();
                String unidades = unidadesI.toString();

                Dato dato = new Dato(periodo, tipoVehiculo, combustible, unidades);
                datos.add(dato);
            }

            workbook.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return datos;
    }

	public static List<Dato> readXML(String filePath) { 
        List<Dato> datos = new ArrayList<>();

        try {
            // Crear un constructor de documentos
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();

            // Parsear el archivo XML
            Document document = builder.parse(filePath);

            // Obtener la raíz del documento
            Element raiz = document.getDocumentElement();

            // Obtener la lista de nodos "row"
            NodeList listaRows = raiz.getElementsByTagName("row");

            // Iterar sobre la lista de nodos "row"
            for (int i = 0; i < listaRows.getLength(); i++) {
                Element row = (Element) listaRows.item(i);

                // Obtener los atributos del nodo "row"
                String periodo = row.getAttribute("Período");
                String tipoVehiculo = row.getAttribute("Tipo_de_vehículo");
                String combustible = row.getAttribute("Combustible");
                String unidades = row.getAttribute("Unidades");

                // Crear un objeto Dato y agregarlo a la lista
                Dato dato = new Dato(periodo, tipoVehiculo, combustible, unidades);
                datos.add(dato);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return datos;
    }
	
	  public static List<Dato> readJSON(String filePath) {
	        // Objeto que leerá el JSON
	        ObjectMapper objectMapper = new ObjectMapper();
	        List<Dato> datos = new ArrayList<>();

	        try {
	            // Lee el JSON desde el archivo
	            JsonNode jsonNode = objectMapper.readTree(new File(filePath));

	            // Procesamos y creamos los datos
	            for (JsonNode node : jsonNode) {
	                String periodo = node.get("Período").asText();
	                String tipoVehiculo = node.get("Tipo de vehículo").asText();
	                String combustible = node.get("Combustible").asText();
	                String unidades = node.get("Unidades").asText();

	                Dato dato = new Dato(periodo, tipoVehiculo, combustible, unidades);
	                datos.add(dato);
	            }
	        } catch (IOException e) {
	            e.printStackTrace();
	        }

	        return datos;
	    }
	  
	  public static List<String[]> readCSV(String filePath) throws IOException {
	        List<String[]> data = new ArrayList<>();

	        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
	            String line;
	            while ((line = br.readLine()) != null) {
	                String[] values = line.split(",");
	                data.add(values);
	            }
	        }

	        return data;
	    }

	  
}
