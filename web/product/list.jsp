<%@ page import="java.util.ArrayList" %><%--
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
        ArrayList<String> collection = (ArrayList<String>) request.getAttribute("productList");
        for (int i = 0; i < collection.size(); i++) {
            String text = "<div> <a id=\"list_element\" href=\"description\">" + collection.get(i) + " </a></div>";
            out.print(text);
        }

//        for (String element : collection) {
//            String text = "<div> <a id=\"list_element\" href=\" \">" + element + " </a></div>";
//            out.print(text);
//        }
    %>

<jsp:include page="../sections/footer.jsp"></jsp:include>
