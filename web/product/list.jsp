<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.HashMap" %>
<%@ page import="java.util.Set" %>
<%@ page import="java.util.Map" %>
<%@ page import="model.Product" %><%--
  Created by IntelliJ IDEA.
  User: Acer_2
  Date: 19.6.2020 г.
  Time: 22:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="../sections/header.jsp"></jsp:include>

<%--
    <ul>
        <li>Cat</li>
        <li>Dog</li>
        <li>Horse</li>
        <li>Camel</li>
        <li>Sheep</li>
        <li>Eagle</li>
        <li>Spider</li>
    </ul>
    --%>

<div>Products:</div>
<hr>

    <%
        ArrayList<Product> collection = (ArrayList<Product>) request.getAttribute("productList");
        if (collection != null) {
            for (int i = 0; i < collection.size(); i++) {
                Product element = collection.get(i);
                String productID = element.getProductId();
                String productTitle = element.getTitle();
                String productPrice = element.getPrice();
                String title = "<div style=\"display: inline-block; width: 120px; text-transform: uppercase\"> <a id=\"list_element\" href=\"details?product_id=" + productID + "\">" + productTitle + " </a></div>";
                String price = "<div style=\"display: inline-block; width: 90px\">price " + productPrice + "$</div>";
                String buy = "<div style=\"display: inline-block; width: 90px\"> <a id=\"list_element\" href=\"details?product_id=" + productID + "\">" + "Buy now" + " </a></div>";
                String cartAdd = "<div style=\"display: inline-block; width: 90px\"> <a id=\"list_element\" href=\"details?product_id=" + productID + "\">" + "Add to cart" + " </a></div>";
                String separator = "<hr>";
                out.print(title + price + buy + cartAdd + separator);
            }
        }


    %>

<div style="display: inline-block; width: 30px"></div>

<jsp:include page="../sections/footer.jsp"></jsp:include>
