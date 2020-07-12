<%@ page import="java.util.ArrayList" %>
<%@ page import="model.Product" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<jsp:include page="../sections/header.jsp"></jsp:include>

<div>Cart:</div>
<hr>

    <%

        ArrayList<Product> cart = (ArrayList<Product>) request.getSession().getAttribute("cart_list");
        if (cart != null) {


        int sum = 0;

        for (Product product : cart) {

            String title = "<div style=\"display: inline-block; width: 200px\"> " + product.getTitle() + " </div>";
            String price = "<div style=\"display: inline-block; width:  50px\"> " + product.getPrice() + " </div>";
            String remove = "<div style=\"display: inline-block; width:  50px\"><a href=\"remove?product_id=" + product.getProductId() + "\"> remove </a></div>";

            String separator = "<hr>";
            out.print(title + price + remove + separator);
            sum = sum + Integer.parseInt(product.getPrice());
        }

        out.print("<div> Sum: " + sum + "<div>");
        }
    %>
<div> </div>
<div><a href="/JavaWebShop_war_exploded/base/buy/pay">Pay</a></div>
<div> </div>
<div style="display: inline-block; width: 500px"><a href="/JavaWebShop_war_exploded/base/product/list">Continue shopping</a></div>


<jsp:include page="../sections/footer.jsp"></jsp:include>