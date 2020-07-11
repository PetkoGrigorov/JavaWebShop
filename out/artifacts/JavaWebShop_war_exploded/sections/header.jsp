
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>$Title$</title>
    <link rel="stylesheet" href="/JavaWebShop_war_exploded/style.css">
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



            <a href="/JavaWebShop_war_exploded/index.jsp">Net-School</a>

        </div>

        <div id="message">

            ${logged_name}

        </div>

        <div id="menu">
            <ul>
                <li> <a href="/JavaWebShop_war_exploded/base/home/index">Home</a> </li>
                <li> <a href="/JavaWebShop_war_exploded/base/home/index">CART</a> </li>
                <%--<li><a href="/Exercise_war_exploded/subjects/math.jsp">Math</a> </li> --%>
                <%--<li><a<%-- href="/Exercise_war_exploded/subjects/history.jsp">History</a> </li> --%>
            </ul>
        </div>
    </div>
