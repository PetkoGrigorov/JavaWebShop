<%--
  Created by IntelliJ IDEA.
  User: Acer_2
  Date: 21.6.2020 г.
  Time: 21:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

        <jsp:include page="sections/header.jsp"></jsp:include>

        <div>
            login: ${logged_name}
        </div>

        <div>
            ${message}
        </div>

        <form method="post" action="${pageContext.request.contextPath}/base/auth/login">
            <input class="input" type="text" name="user_email">
            <input class="input" type="password" name="user_pass">
            <input class="input" type="submit">
        </form>


        <jsp:include page="sections/footer.jsp"></jsp:include>

