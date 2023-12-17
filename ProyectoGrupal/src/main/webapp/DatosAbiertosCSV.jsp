<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" import="java.util.List"  %>
 
<%
List<String[]> data = (List<String[]>) request.getAttribute("datos");
%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
    <title>Datos Abiertos Formato CSV</title>
</head>
<body>
    <h2 style="text-align: center;">DATOS</h2>
    <table border="1" style="width:50%; margin-left: 25%; margin-right: 25%;">
        <tr>
            <th>Período</th>
            <th>Tipo de vehículo</th>
            <th>Combustible</th>
            <th>Unidades</th>
        </tr>
        <%
        for (int i = 1; i < data.size(); i++) {
            String[] datos = data.get(i);
        %>
            <tr>
                <td><%= datos[0] %></td>
                <td><%= datos[1] %></td>
                <td><%= datos[2] %></td>
                <td><%= datos[3] %></td>
            </tr>
        <%
        }
        %>
    </table>
    <br><br><br>

    <form action="Menu.jsp">
        <input type="submit" value="Volver">
    </form>
</body>
</html>
