<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <link rel="stylesheet" href="/BikeWay/styles/styles.css">
    </head>
    <body>
        <h1>The Bike Way</h1>
        <h2>Product Management</h2>
        
        <table id="customers">
            <tr>
                <th>Model</th>
                <th>Manufacturer</th>
                <th>Name</th>
                <th>Type</th>
                <th>Edit</th>
                <th>Delete</th>
            </tr>
            <c:forEach var="bike" items="${bikes}">
                <tr>
                    <td><c:out value = "${bike.model}"/></td>
                    <td><c:out value = "${bike.manufacturer}"/></td>
                    <td><c:out value = "${bike.name}"/></td>
                    <td><c:out value = "${bike.type}"/></td>
                    <td><a href="/BikeWay/edit?id=${bike.id}">Edit Bike</a></td>
                    <td><a href="/BikeWay/delete?id=${bike.id}">Delete Bike</a></td>
                </tr>
            </c:forEach>
        </table>
        <br><br><div class="d"><a href="/BikeWay/new">Add New Bike</a></div><br>
    </body>
</html>
