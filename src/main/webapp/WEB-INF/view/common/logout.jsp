<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="../../layout/taglib.jsp" %>

<br><br>

<div class="row">
    <div class="col-md-6 col-md-offset-3">
        <c:if test="${param.logoutSuccess eq true}">
            <div class="alert alert-success"><center>Nastąpiło poprawne wylogowanie</center></div>
        </c:if>

        <c:if test="${param.sessionExpired eq true}">
            <div class="alert alert-success">Session expired!
                logout</div>
        </c:if>

    </div>
</div>