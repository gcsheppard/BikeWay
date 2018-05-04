<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <link rel="stylesheet" href="/BikeWay/styles/styles.css">
    </head>
        <h1>The Bike Way</h1>
        <h2>Edit Product Information:</h2>
     <body>
        <form action="edit" method="post">
            <div class="b"><input type="text" name="model" value="${book.model}" placeholder="Model"}></div><br>
            <div class="b"><input type="text" name="manufacturer" value="${book.manufacturer}" placeholder="Manufacturer"></div><br>
            <div class="b"><input type="text" name="name" value="${book.name}" placeholder="Name"></div><br>
            <div class="b"><input type="text" name="type" value="${book.type}" placeholder="Type"></div><br>
            <div class="c"><input type="submit" value="Save Changes" /><br>
        </form>  
            <br><div class="e">${errors}</div>
    </body>
</html>
