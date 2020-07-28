<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.HashMap" %>
<%@ page import="java.util.Set" %>
<%@ page import="java.util.Map" %>
<%@ page import="model.Product" %>
<%@ page import="config.RouteMap" %><%--
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
<div>Search
    <form <%--action="/JavaWebShop_war_exploded/base/product/search_list"--%>>
        <input type="text" name="search_string" placeholder="product name">
        <input type="submit">
    </form>
</div>
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
                String cartAdd = "<div style=\"display: inline-block; width: 90px\"> <a id=\"list_element\" href=\"" + RouteMap.PREFIX + "/base/cart/add?product_id=" + productID + "\">" + "Add to cart" + " </a></div>";
                String separator = "<hr>";
                out.print(title + price + buy + cartAdd + separator);
            }
        }

        int pageLimit = Integer.parseInt ((request.getSession().getAttribute("page_limit")).toString());
        int productCount = Integer.parseInt ((request.getSession().getAttribute("product_count")).toString());
        int pageIndex = Integer.parseInt ((request.getSession().getAttribute("page_index")).toString());
        int previousIndex = (pageIndex < 2) ? 1 : (pageIndex - 1);
        int nextIndex = pageIndex + 1;
        String search = "";
        if (session.getAttribute("search_string") != null) {
            search = session.getAttribute("search_string").toString();
        }


        if (pageIndex > 1) {
            String previousPage = "<div style=\"display: inline-block; width: 150px\"><a href=\"list?page_index=" + previousIndex + "&search_string=" + search + "\">Previous page</a></div>";
            out.print(previousPage);
        } else {
            out.print("<div style=\"display: inline-block; width: 150px\"></div>");
        }

        int coefficient = 1;
        while ((productCount - pageLimit * coefficient) > 0) {
            String color = "color: indigo";
            if (pageIndex == coefficient) {
                color = "color: darkorange";
            }
            String forPageNumber = "<span style=\"padding-left: 10px; padding-right: 10px\"><a style=\"" + color + "\" href=\"list?page_index=" + coefficient + "&search_string=" + search + "\">" + coefficient + "</a></span>";
            out.print(forPageNumber);
            coefficient ++;
        }
        if (coefficient > 1) {
            String color = "color: indigo";
            if (pageIndex == coefficient) {
                color = "color: darkorange";
            }
            String forPageNumber = "<span style=\"padding-left: 10px; padding-right: 10px\"><a style=\"" + color + "\" href=\"list?page_index=" + coefficient + "&search_string=" + search + "\">" + coefficient + "</a></span>";
            out.print(forPageNumber);
        }

        if ((pageIndex * pageLimit) < productCount) {
            String nextPage = "<div style=\"display: inline-block; width: 150px\"><a href=\"list?page_index=" + nextIndex + "&search_string=" + search + "\">Next page</a></div>";
            out.print(nextPage);
        }

    %>
<div></div>
<div >
    <div style="display: inline-block; width: 200px">Shown products on page:</div>
    <%
        out.print("<span><a style=\" padding-left: 5px \" href=\"list?page_limit=3&search_string=" + search + "\">3</a></span>");
        out.print("<span><a style=\" padding-left: 5px \" href=\"list?page_limit=5&search_string=" + search + "\">5</a></span>");
        out.print("<span><a style=\" padding-left: 5px \" href=\"list?page_limit=10&search_string=" + search + "\">10</a></span>");

    %>


<%--    <span><a href="list?page_limit=3">3</a></span>
    <span><a href="list?page_limit=5">5</a></span>
    <span><a href="list?page_limit=10">10</a></span>
    <span><a href="list?page_limit=12">12</a></span>  --%>
</div>

<jsp:include page="../sections/footer.jsp"></jsp:include>
