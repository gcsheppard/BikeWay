<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <link rel="stylesheet" href="/BikeWay/styles/styles.css">
    </head>
        <h1>The Bike Way</h1>
        <h2>Enter New Product Information:</h2>
     <body>
        <form action="new" method="post">
            <div class="b">Model: <input type="text" name="model" value="${bike.model}" placeholder="Model"}></div><br>
            <div class="b">Manufacturer: <input type="text" name="manufacturer" value="${bike.manufacturer}" placeholder="Manufacturer"></div><br>
            <div class="b">Name: <input type="text" name="name" value="${bike.name}" placeholder="Name"></div><br>
            <div class="b">Type: <input type="text" name="type" value="${bike.type}" placeholder="Type"></div><br>
            <div class="c"><input type="submit" value="Save" /><br>
        </form>  
            
        <c:choose>
            <c:when test = "${errors != null}">
                <br><br><div class="e">Errors:</div>
                <ul>
                <c:forEach var="error" items="${errors.values()}">
                    <li>
                        <div class="e">${error}</div>
                    </li>
                </c:forEach>
                </ul>
            </c:when>
        </c:choose>        
                
        <br><br><div class="d"><a href="/BikeWay/bikes">Return to list</a></div><br>
            
    </body>
</html>
