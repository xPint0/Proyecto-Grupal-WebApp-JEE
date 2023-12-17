package controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
//import lectores.CSVReader;
import lectores.Reader;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.print.DocFlavor.URL;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import escritores.AbrirArchivo;
import escritores.Writter;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
//import lectores.CSVReader;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.print.DocFlavor.URL;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

//import escritores.AbrirCSV;
//import escritores.CSVWriter;

/**
 * Servlet implementation class ServletAcceso
 */

public class ServletAcceso extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ServletAcceso() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		String page = "";

		String dato1 = request.getParameter("dato1");
		String dato2 = request.getParameter("dato2");
		String dato3 = request.getParameter("dato3");
		String dato4 = request.getParameter("dato4");

		if (dato1 == null || dato2 == null || dato3 == null || dato4 == null || dato1.isEmpty() || dato2.isEmpty()
				|| dato3.isEmpty() || dato4.isEmpty()) {

			page = "Menu.jsp";
			request.setAttribute("vacio", true);

		} else {
			// lectura
			if (request.getParameter("accion").equalsIgnoreCase("lectura")) {

				// Lectura CSV
				if (request.getParameter("formato").equalsIgnoreCase("CSV")) {

					// ruta del archivo CSV
					String filePath = "C:\\Users\\dpjre\\eclipse-workspace\\ProyectoGrupal\\src\\main\\resources\\parquealcobendas.csv";

					// Lee el CSV utilizando la clase Reader
					List<String[]> data = Reader.readCSV(filePath);
					// Si hay lectura de datos se envia al jsp para la muestra

					if (data != null) {
						page = "DatosAbiertosCSV.jsp";
						request.setAttribute("datos", data);
					} else { // Si no a la pagina de error
						page = "Error.jsp";
						request.setAttribute("errorLectura", true);

					}

					// Lectura JSON
				} else if (request.getParameter("formato").equalsIgnoreCase("JSON")) {

					String filePath = "C:\\Users\\dpjre\\eclipse-workspace\\ProyectoGrupal\\src\\main\\resources\\parquealcobendas.json";

					List<Dato> datos = Reader.readJSON(filePath);

					request.setAttribute("datos", datos);

					page = "DatosAbiertosJSON.jsp";

					// Lectura XML
				} else if (request.getParameter("formato").equalsIgnoreCase("XML")) {
					
					String filePath = "C:\\Users\\dpjre\\eclipse-workspace\\ProyectoGrupal\\src\\main\\resources\\parquealcobendas.xml";

					List<Dato> datos = Reader.readXML(filePath);

					request.setAttribute("datos", datos);

					page = "DatosAbiertosXML.jsp";
					
					
					// Lectura XLS
				} else {
					String archivo = "C:\\Users\\dpjre\\eclipse-workspace\\ProyectoGrupal\\src\\main\\resources\\parquealcobendas.xlsm"; // Cambiar la ruta y nombre del archivo

			        List<Dato> datos = Reader.readXLS(archivo);

			        request.setAttribute("datos", datos);

			        page="DatosAbiertosXLS.jsp";
			    
				}

				// escritura
			} else if (request.getParameter("accion").equalsIgnoreCase("escritura")) {

				// Escritura CSV
				if (request.getParameter("formato").equalsIgnoreCase("CSV")) {

					// ruta del archivo CSV					
					String filePath = "C:\\Users\\dpjre\\eclipse-workspace\\ProyectoGrupal\\src\\main\\resources\\parquealcobendas.csv";

					// System.out.println(filePath);

					// Agrega los nuevos datos al archivo CSV utilizando csvWritter
					Writter csvWriter = new Writter(filePath);
					List<String> nuevoRegistro = Arrays.asList(dato1, dato2, dato3, dato4);

					// Comprobacion de escritura 
					// True
					if (Writter.escribirCSV(nuevoRegistro)) { // Abre el archivo CSV
						AbrirArchivo.abrir(filePath);

						// Redirige a la página principal
						page = "Menu.jsp";

						// False
					} else {

						// Redirige a la página de error
						page = "Error.jsp";
						request.setAttribute("errorEscritura", true);

					}

					// Escritura JSON
				} else if (request.getParameter("formato").equalsIgnoreCase("JSON")) {

					// Ruta del archivo JSON
					String filePath = "C:\\Users\\dpjre\\eclipse-workspace\\ProyectoGrupal\\src\\main\\resources\\parquealcobendas.json";

					// Obtener parámetros del formulario
					String nuevoPeriodo = request.getParameter("dato1");
					String nuevoTipoVehiculo = request.getParameter("dato2");
					String nuevoCombustible = request.getParameter("dato3");
					String nuevasUnidades = request.getParameter("dato4");

					// Llamar al método de escritura

					if (Writter.escribirJSON(filePath, nuevoPeriodo, nuevoTipoVehiculo, nuevoCombustible,
							nuevasUnidades)) {

						AbrirArchivo.abrir(filePath);
						page = "/Menu.jsp";
					} else {
						// Redirige a la página de error

						request.setAttribute("errorEscritura", true);
						page = "Error.jsp";
					}

					// Escritura XML
				} else if (request.getParameter("formato").equalsIgnoreCase("XML")) {

					// Ruta del archivo XML
					String filePath = "C:\\Users\\dpjre\\eclipse-workspace\\ProyectoGrupal\\src\\main\\resources\\parquealcobendas.xml";

					// Obtener parámetros del formulario
					String nuevoPeriodo = request.getParameter("dato1");
					String nuevoTipoVehiculo = request.getParameter("dato2");
					String nuevoCombustible = request.getParameter("dato3");
					String nuevasUnidades = request.getParameter("dato4");

					// Llamar al método de escritura

					if (Writter.escribirXML(filePath, nuevoPeriodo, nuevoTipoVehiculo, nuevoCombustible,
							nuevasUnidades)) {

						AbrirArchivo.abrir(filePath);
						page = "/Menu.jsp";
					} else {
						// Redirige a la página de error

						request.setAttribute("errorEscritura", true);
						page = "Error.jsp";
					}
					
					// Escritura XLS
				} else if (request.getParameter("formato").equalsIgnoreCase("XLS")) {
					
					String ruta = "C:\\Users\\dpjre\\eclipse-workspace\\ProyectoGrupal\\src\\main\\resources\\parquealcobendas.xlsm"; // Cambiar la ruta y nombre del archivo

                    String nuevoPeriodo = request.getParameter("dato1");
                    String nuevoTipoVehiculo = request.getParameter("dato2");
                    String nuevoCombustible = request.getParameter("dato3");
                    String nuevasUnidades = request.getParameter("dato4");

                    boolean escrituraExitosa = Writter.escribirXLS(ruta, nuevoPeriodo, nuevoTipoVehiculo, nuevoCombustible, nuevasUnidades);

                    if (escrituraExitosa) {
                        page = "/Menu.jsp";
                        AbrirArchivo.abrir(ruta);
                    } else {
                        page = "/Error.jsp";
                    }
					
				}
				// no ha seleccionado nada
				else {
					page = "Error.jsp";
					request.setAttribute("errorAccion", true);
				}
			}
		}
		request.getRequestDispatcher(page).forward(request, response);
	}

}