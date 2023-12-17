<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<h2 style="text-align: center" >TIPO DE ERROR</h2>
<%if(request.getAttribute("errorLectura") != null){ %>
<p style="text-align: center" >Error de lectura</p>
<%}else if(request.getAttribute("errorEscritura") != null){ %>
<p style="text-align: center" >Error de escritura</p>
<%}else if(request.getAttribute("errorAccion") != null){ %>
<p style="text-align: center" >Error de accion</p>
<%}else{ %>
<p style="text-align: center" >Error desconocido</p>
<%} %>
</body>
</html>