<%@ page import="model.Product" %><%--
  Created by IntelliJ IDEA.
  User: Acer_2
  Date: 6.7.2020 г.
  Time: 22:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="../sections/header.jsp"></jsp:include>
    <%
        Product product = (Product) request.getSession().getAttribute("product");
        String productID = product.getProductId();
        out.print("<div>" + product.getTitle() + "</div>");
        out.print("<div>" + product.getDescription() + "</div>");
        out.print("<div>" + product.getPrice() + "</div>");

        String buy = "<div style=\"display: inline-block; width: 90px\"> <a id=\"list_element\" href=\"details?product_id=" + productID + "\">" + "Buy now" + " </a></div>";
        out.print(buy);

    %>


<jsp:include page="../sections/footer.jsp"></jsp:include>
