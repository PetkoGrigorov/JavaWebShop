<%@ page import="model.Product" %>
<%@ page import="config.RouteMap" %><%--
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
        if (product == null) {
            String productNull = "<div>Product not found</div>";
            out.print(productNull);
        } else {
            String productID = product.getProductId();
            out.print("<div>" + product.getTitle() + "</div>");
            out.print("<div>" + product.getDescription() + "</div>");
            out.print("<div>" + product.getPrice() + "</div>");

            String buy = "<div style=\"display: inline-block; width: 90px\"> <a id=\"list_element\" href=\"" + RouteMap.PREFIX + "/base/buy/pay?product_id=" + productID + "\">" + "Pay" + " </a></div>";
            out.print(buy);
            String cartAdd = "<div style=\"display: inline-block; width: 90px\"> <a id=\"list_element\" href=\"" + RouteMap.PREFIX + "/base/cart/add?product_id=" + productID + "\">" + "Add to cart" + " </a></div>";
            out.print(cartAdd);

        }

    %>
<div> </div>
<div style="display: inline-block; width: 500px"><a href="/JavaWebShop_war_exploded/base/product/list">Continue shopping</a></div>


<jsp:include page="../sections/footer.jsp"></jsp:include>
