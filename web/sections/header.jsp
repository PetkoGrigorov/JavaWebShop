<%@ page import="java.util.ArrayList" %>
<%@ page import="model.Product" %>
<%@ page import="model.system.AuthUser" %>
<%@ page import="config.RouteMap" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>$Title$</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/style.css">
    <style>
        #list_element {
            margin-right: 5px;
            margin-left: 5px;
            color: azure;
            text-decoration: none;
        }
        #list_element:hover {
            color: darkorange;
        }
    </style>
</head>
<body>

    <div id="header">

        <div id="logo">

            <a href="${pageContext.request.contextPath}/index.jsp">Net-School</a>

        </div>

        <div id="message">

            ${logged_name}

        </div>

        <div id="menu">
            <ul>
                <li> <a href="${pageContext.request.contextPath}/base/home/index">Home</a> </li>
                <li> <a href="${pageContext.request.contextPath}/base/cart/list">CART<%

                    ArrayList<Product> cart = (ArrayList<Product>) request.getSession().getAttribute("cart_list");
                    int count = 0;
                    if (cart != null) {
                        count = cart.size();
                    }
                    out.print(count);
                %></a> </li>

                <li><a href="">Math</a> </li>
                <%--<li><a<%-- href="/Exercise_war_exploded/subjects/history.jsp">History</a> </li> --%>
            </ul>

            <%
                if (AuthUser.isUserAuthenticated()) {
                    String logout = "<span style=\"padding-left: 5px; padding-right: 5px\"><a href=\"" + RouteMap.PREFIX + "/base/auth/logout\">Logout</a></span>";
                    out.print(logout);
                } else {
                    String login = "<span style=\"padding-left: 5px; padding-right: 5px\"><a href=\"" + RouteMap.PREFIX + "/base/auth/login\">Login</a></span>";
                    String registration = "<span style=\"padding-left: 5px; padding-right: 5px\"><a href=\"" + RouteMap.PREFIX + "/base/auth/registration\">Registration</a></span>";
                    out.print(login + registration);
                }


            %>

        </div>
    </div>
