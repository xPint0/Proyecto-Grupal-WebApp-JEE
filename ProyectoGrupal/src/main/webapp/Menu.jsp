<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Tratamiento ficheros</title>
</head>
<body>
<h2>TRATAMIENTO FICHEROS</h2>
<form action="ServletAcceso" method="post">
<table>
<tr>
<td><br><br>
	Formato del fichero: <select name="formato" ><option value="XLS">XLS</option> <option value="CSV">CSV</option> <option value="JSON">JSON</option> <option value="XML">XML</option> </select> <hr>
	¿Qué quiere hacer con el fichero? <br><br>
	Lectura: &emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&nbsp;&nbsp;	<input id="lectura" type="radio" name="accion" value="lectura" ><br>
	Escritura: &emsp;&emsp;&emsp;&emsp;&emsp;&emsp;	<input id="escritura" type="radio" name="accion" value="escritura" ><br>
</td>
<td>
	DATO1: <input type="text" name="dato1" placeholder="Ingrese período del vehiculo" style="width: 180px;" > <br>
	DATO2: <input type="text" name="dato2" placeholder="Ingrese tipo de vehiculo" style="width: 180px;" > <br> 
	DATO3: <input type="text" name="dato3" placeholder="Ingrese tipo de combustible" style="width: 180px;" > <br> 
	DATO4: <input type="text" name="dato4" placeholder="Ingrese unidades"> <br>
	
	<% if(request.getAttribute("vacio") != null){ %>
	<p style="color : red" align="right"><b>(*) Los campos no pueden estar vacios </b></p>
	<%} %>
</td>
</tr>
</table>

<input type="submit" value="Enviar"/>
</form>
</body>
</html>