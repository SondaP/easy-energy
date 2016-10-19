<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="../../layout/taglib.jsp" %>
<script src="${pageContext.request.contextPath}/resources/common/js/registrationValidation.js"></script>

<c:if test="${param.success eq true}">
    <div class="alert alert-success">Rejestracja zakończona powodzeniem</div>
</c:if>

<form:form commandName="user" action="/sa/register.html" cssClass="form-horizontal registrationForm">
    <div class="form-group">
        <label for="name" class="col-sm-2 control-label">Nazwa:</label>
        <div class="col-sm-10">
            <form:input path="name" cssClass="form-control"/>
            <form:errors path="name" />

        </div>
    </div>
    <div class="form-group">
        <label for="email" class="col-sm-2 control-label">Email:</label>
        <div class="col-sm-10">
            <form:input path="email" cssClass="form-control"/>
            <form:errors path="email" />
        </div>
    </div>
    <div class="form-group">
        <label for="password" class="col-sm-2 control-label">Hasło:</label>
        <div class="col-sm-10">
            <form:password path="password" cssClass="form-control"/>
            <form:errors path="password" />
        </div>
    </div>
    <div class="form-group">
        <label for="password" class="col-sm-2 control-label">Powtórz hasło:</label>
        <div class="col-sm-10">
            <input type="password" name="password_again" id="password_again" class="form-control">
        </div>
    </div>
    <div class="form-group">
        <div class="col-sm-2">
            <input type="submit" value="Rejestruj" class="btn btn-lg btn-primary"/>
        </div>
    </div>


</form:form>