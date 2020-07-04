<%--
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
          <a href="/JavaWebShop_war_exploded/base/product/list">Product list</a>
      </div>
      <div>
          <a href="/JavaWebShop_war_exploded/base/auth/login">Login</a>

   <%--       <form action="base/auth/loginprocess" method="get">
              <input type="text" name="user_email" placeholder="username">
              <input type="password" name="user_password" placeholder="password">
              <input type="submit">
          </form> --%>

      </div>
      <div>
          <a href="/JavaWebShop_war_exploded/base/auth/registration">Registration</a>
      </div>

       <jsp:include page="sections/footer.jsp"></jsp:include>


