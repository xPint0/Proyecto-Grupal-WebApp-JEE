<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%><%@ page import="java.util.List" %><%@ page import="controller.Dato" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Datos Abiertos Formato XLS</title>
</head>
<body>
    <h2 style="text-align: center;">DATOS</h2>
    <table border="1" style="width:50%; margin-left: 25%; margin-right: 25%;">
        <tr>
            <th>Periodo</th>
            <th>Tipo de vehiculo</th>
            <th>Combustible</th>
            <th>Unidades</th>
        </tr>
        <%
        List<Dato> data = (List<Dato>) request.getAttribute("datos");
        if (data != null) {
            for (Dato dato : data) {
        %>
                <tr>
                    <td><%= dato.getPeriodo() %></td>
                    <td><%= dato.getTipoVehiculo() %></td>
                    <td><%= dato.getCombustible() %></td>
                    <td><%= dato.getUnidades() %></td>
                </tr>
        <%
            }
        } else {
            out.println("No se han encontrado datos.");
        }
        %>
    </table>
    <br><br><br>

    <form action="Menu.jsp">
        <input type="submit" value="Volver">
    </form>
</body>
</html>