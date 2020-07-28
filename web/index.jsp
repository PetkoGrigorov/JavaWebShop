<%@ page import="model.system.AuthUser" %>
<%@ page import="config.RouteMap" %><%--
  Created by IntelliJ IDEA.
  model.User: Acer_2
  Date: 16.6.2020 г.
  Time: 20:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

      <jsp:include page="sections/header.jsp"></jsp:include>

      <h1>Hello</h1>

      <div>
          <a href="${pageContext.request.contextPath}/base/product/list">Product list</a>
      </div>

      <div>
          <%-- <a href="${pageContext.request.contextPath}/base/auth/login">Login</a> --%>
          <%
              if (!AuthUser.isUserAuthenticated()) {
                  out.print("<a href=\"" + RouteMap.PREFIX + "/base/auth/login\">Login</a>");
              }
          %>
      </div>

      <div>
          <%-- <a href="${pageContext.request.contextPath}/base/auth/registration">Registration</a> --%>
          <%
              if (!AuthUser.isUserAuthenticated()) {
                  out.print("<a href=\"" + RouteMap.PREFIX + "/base/auth/registration\">Registration</a>");
              }
          %>
      </div>

       <jsp:include page="sections/footer.jsp"></jsp:include>


