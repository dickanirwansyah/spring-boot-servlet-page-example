<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
     <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
     <head>
         <title>Cart Page</title>
     </head>
     <body>
        <h3>Your Cart</h3>
        <table border="1">
            <tr>
                <th>Option</th>
                <th>Id</th>
                <th>Name</th>
                <th>Price</th>
                <th>Quantity</th>
                <th>SubTotal</th>
            </tr>
            <c:set var="total" value="0"></c:set>
            <c:forEach var="itemCart" items="${sessionScope.cart}">
                <c:set var="total" value="${total + itemCart.product.price * itemCart.quantity}"></c:set>
                <tr>
                    <td><a href="">Remove</a></td>
                    <td>${itemCart.product.id}</td>
                    <td>${itemCart.product.name}</td>
                    <td>${itemCart.product.price}</td>
                    <td>${itemCart.quantity}</td>
                    <td>${itemCart.product.price * itemCart.quantity}</td>
                </tr>
            </c:forEach>
            <tr>
                <td colspan="6" align="right">Sum</td>
                <td>${total}</td>
            </tr>
        </table>
        <br/>
        <a href="${pageContext.request.contextPath}/product/index">Continue Shopping</a>
     </body>
</html>