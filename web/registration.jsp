<%--
  Created by IntelliJ IDEA.
  User: Acer_2
  Date: 21.6.2020 г.
  Time: 21:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

        <jsp:include page="sections/header.jsp"></jsp:include>

        <form method="post">
            <input class="input" type="text" placeholder="username" name="user_name">
            <input class="input" type="password" placeholder="password" name="user_pass">
            <input class="input" type="password" placeholder="repeat password" name="user_pass_repeat">
            <input class="input" type="email" placeholder="email" name="user_email">
            <input class="input" type="text" placeholder="first name" name="user_fname">
            <input class="input" type="text" placeholder="last name" name="user_lname">
            <input class="input" type="submit">
        </form>



        <jsp:include page="sections/footer.jsp"></jsp:include>

