<%@ page language="java"%>
<%@ page contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
    <head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
        <title>Product Page</title>
    </head>
    <body>
        <h3>Product</h3>
        <table border="1" cellpadding="1" cellspacing="1">
            <tr>
                <th>id</th>
                <th>name</th>
                <th>price</th>
                <th>buy</th>
            </tr>
            <c:forEach var="product" items="${products}">
            <tr>
                <td>${product.id}</td>
                <td>${product.name}</td>
                <td>${product.price}</td>
                <td align="center">
                    <a href="${pageContext.request.contextPath}/cart/buy/${product.id}">Buy</a>
                </td>
            </tr>
            </c:forEach>
        </table>
    </body>
</html>