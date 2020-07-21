

<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<jsp:include page="../sections/header.jsp"></jsp:include>




<div>
    <form>
        <input type="text" style="display: block" placeholder="Shipping address">
        <input type="radio" style="display: inline-block" name="method" value="visa">Visa
        <div></div>
        <input type="radio" style="display: inline-block" name="method" value="master">Master Card
        <div></div>
        <input type="radio" style="display: inline-block" name="method" value="discover">Discover
        <div></div>
        <input type="radio" style="display: inline-block" name="method" value="pay_pal"> PayPal
    </form>
</div>







<jsp:include page="../sections/footer.jsp"></jsp:include>
