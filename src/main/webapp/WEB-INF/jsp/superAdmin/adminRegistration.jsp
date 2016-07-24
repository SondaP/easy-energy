<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="../../layout/taglib.jsp" %>


<form:form commandName="user" action="/sa/register.html" cssClass="form-horizontal">
    <div class="form-group">
        <label for="name" class="col-sm-2 control-label">Nazwa:</label>
        <div class="col-sm-10">
            <form:input path="name" cssClass="form-control"/>
        </div>
    </div>
    <div class="form-group">
        <label for="email" class="col-sm-2 control-label">Email:</label>
        <div class="col-sm-10">
            <form:input path="email" cssClass="form-control"/>
        </div>
    </div>
    <div class="form-group">
        <label for="password" class="col-sm-2 control-label">Hasło:</label>
        <div class="col-sm-10">
            <form:password path="password" cssClass="form-control"/>
        </div>
    </div>

    <div class="form-group">
        <div class="col-sm-2">
            <input type="submit" value="Rejestruj" class="btn btn-lg btn-primary"/>
        </div>
    </div>


</form:form>