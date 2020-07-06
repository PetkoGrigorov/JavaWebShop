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

    <%
        ArrayList<Product> collection = (ArrayList<Product>) request.getAttribute("productList");
        if (collection != null) {
            for (int i = 0; i < collection.size(); i++) {
                String text = "<div> <a id=\"list_element\" href=\"description?" + collection.get(i).getProductId() + "\">" + collection.get(i).getProductId() + "  " + collection.get(i).getTitle() + " </a></div>";
                out.print(text);
            }
        }



//        HashMap<String, String> collectionHash = (HashMap<String, String>) request.getAttribute("productList");
//        Set<Map.Entry<String, String>> entrySet = collectionHash.entrySet();
//        for (Map.Entry<String, String> element : entrySet) {
//            String text = "<div> <a id=\"list_element\" href=\"description\">" + element.getKey() + "   " + element.getValue() + " </a></div>";
//            out.print(text);
//        }



//        ArrayList<String> collection = (ArrayList<String>) request.getAttribute("productList");
//        if (collection != null) {
//            for (int i = 0; i < collection.size(); i++) {
//                String text = "<div> <a id=\"list_element\" href=\"description\">" + collection.get(i) + " </a></div>";
//                out.print(text);
//            }
//        }


//        for (String element : collection) {
//            String text = "<div> <a id=\"list_element\" href=\" \">" + element + " </a></div>";
//            out.print(text);
//        }
    %>

<jsp:include page="../sections/footer.jsp"></jsp:include>
