<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%><%@ page import="java.util.List" %><%@ page import="controller.Dato" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Mostrar JSON</title>
</head>
<body>

<h2>Datos JSON</h2>

<%
    // Obtener la lista de datos del atributo de solicitud
    List<Dato> datos = (List<Dato>) request.getAttribute("datos");
%>

<% if (datos != null && !datos.isEmpty()) { %>
    <table border="1">
        <tr>
            <th>Periodo</th>
            <th>Tipo de vehiculo</th>
            <th>Combustible</th>
            <th>Unidades</th>
        </tr>
        <% for (Dato dato : datos) { %>
            <tr>
                <td><%= dato.getPeriodo() %></td>
                <td><%= dato.getTipoVehiculo() %></td>
                <td><%= dato.getCombustible() %></td>
                <td><%= dato.getUnidades() %></td>
            </tr>
        <% } %>
    </table>
<% } else { %>
    <p>No hay datos para mostrar.</p>
<% } %>
    <br><br><br>

    <form action="Menu.jsp">
        <input type="submit" value="Volver">
    </form>
</body>
</html>