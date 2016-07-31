<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>

<html>

<head>

    <%-- REQUIRED taglib for spring linksExample: href='<spring:url value="/" />absolute url	 --%>
    <%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
    <!-- Latest compiled and minified CSS -->
    <link rel="stylesheet"
          href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
    <!-- Optional theme -->
    <link rel="stylesheet"
          href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap-theme.min.css">
    <!-- jQuery  -->
    <script
            src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.0/jquery.min.js"></script>
    <!--  TAG FOR JQUERY VALIDATION PLUGIN -->
    <script type="text/javascript"
            src="http://ajax.aspnetcdn.com/ajax/jquery.validate/1.14.0/jquery.validate.min.js"></script>
    <!-- Latest compiled and minified JavaScript -->
    <script
            src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
    <!-- this tag will read value of the current attribute from general - depend on the jsp page value will be the name of the page -->
    <%@ taglib uri="http://tiles.apache.org/tags-tiles-extras"
               prefix="tilesx" %>
    <!-- taglib for Security expressions   -->
    <%@ taglib uri="http://www.springframework.org/security/tags" prefix="security" %>
    <%--Local Css Style --%>
    <link href="${pageContext.request.contextPath}/resources/css/classic.css" rel="stylesheet">


    <title><tiles:getAsString name="title"></tiles:getAsString></title>
    <tilesx:useAttribute name="currentDef"/>
</head>
<body>

<div class="custom-full-container">
    <nav class="navbar navbar-default navbar-static-top">
        <div class="container-full">

            <div class="navbar-header">
                <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar"
                        aria-expanded="false" aria-controls="navbar">
                </button>
                <a class="navbar-brand" href="<spring:url value="/"/>">Easy-energy</a>
            </div>
            <div id="navbar" class="navbar-collapse collapse">
                <ul class="nav navbar-nav">

                    <%-- No AUTHENTICATION --%>
                    <security:authorize access="!isAuthenticated()">
                        <li class="${currentDef == 'login' ? 'active' : ''}"><a
                                href="<spring:url value="/login.html"/>">Login</a></li>
                    </security:authorize>


                    <%-- SUPER ADMIN --%>
                    <security:authorize access="hasRole('ROLE_SUPER_ADMIN')">
                        <li class="${currentDef == 'sa-users' ? 'active' : ''}"><a
                                href="<spring:url value="/sa/users.html"/>">Użytkownicy</a></li>
                        <li class="${currentDef == 'sa-adminRegister' ? 'active' : ''}"><a
                                href="<spring:url value="/sa/register.html"/>">Rejestracja Partnera</a></li>
                    </security:authorize>

                    <%-- ADMIN --%>
                    <security:authorize access="hasRole('ROLE_ADMIN')">
                        <li class="${currentDef == 'sa-adminRegister' ? 'active' : ''}"><a
                                href="<spring:url value="#"/>">Aktualizuj cenniki</a></li>
                        <li class="${currentDef == 'sa-adminRegister' ? 'active' : ''}"><a
                                href="<spring:url value="#"/>">Handlowcy</a></li>
                        <li class="${currentDef == 'a-userRegister' ? 'active' : ''}"><a
                                href="<spring:url value="/a/register.html"/>">Rejestracja Handlowca</a></li>
                        <li class="${currentDef == 'a-account' ? 'active' : ''}"><a
                                href="<spring:url value="/a/account.html"/>">Konto</a></li>

                    </security:authorize>


                    <%-- USER --%>
                    <security:authorize access="hasRole('ROLE_USER')">
                        <li><a href="#about">Wylicz ofertę</a></li>
                        <li><a href="#contact">Oferty</a></li>
                        <li><a href="#contact">Klienci</a></li>
                    </security:authorize>

                </ul>


                <!-- Right site of menu -->
                <ul class="nav navbar-pills navbar-right">

                </ul>
                <ul class="nav navbar-pills navbar-right">
                    <security:authorize access="isAuthenticated()">
                        <li><p>
                            <p>
                                <a href="<spring:url value="/logout"></spring:url>"
                                   type="button" class="btn btn-danger"> <span
                                        class="glyphicon glyphicon-off"></span>
                                </a></li>
                    </security:authorize>
                </ul>

                <ul class="nav navbar-pills navbar-right">
                    <security:authorize access="isAuthenticated()">
                        <li><p>
                            <p>

                                <a role="button" class="btn btn-default" data-container="body"
                                   data-toggle="popover" data-placement="bottom"
                                   data-content="phone number">Help <span
                                        class="glyphicon glyphicon-earphone"></span></a></li>
                    </security:authorize>
                </ul>

                <ul class="nav navbar-pills navbar-right">
                    <security:authorize access="hasRole('ROLE_SUPER_ADMIN')">
                        <li><p>
                            <p>
                                <a href="<spring:url value="/user-settings.html"></spring:url>"
                                   type="button" class="btn btn-default">${pageContext.request.userPrincipal.name}
                                    <span class="glyphicon glyphicon-cog"></span>
                                </a></li>
                    </security:authorize>
                </ul>

                <ul class="nav navbar-pills navbar-right">
                    <security:authorize access="hasRole('ADMIN')">
                        <li><p>
                            <p>
                                <a href="<spring:url value="/a/settings.html"></spring:url>"
                                   type="button" class="btn btn-default">${pageContext.request.userPrincipal.name}
                                    <span class="glyphicon glyphicon-cog"></span>
                                </a></li>
                    </security:authorize>
                </ul>


                <ul class="nav navbar-pills navbar-right">
                    <security:authorize access="hasRole('USER')">
                        <li><p>
                            <p>
                                <a href="<spring:url value="#"></spring:url>"
                                   type="button" class="btn btn-default">${pageContext.request.userPrincipal.name}
                                    <span class="glyphicon glyphicon-cog"></span>
                                </a></li>
                    </security:authorize>
                </ul>


            </div>
        </div>
    </nav>

    <div class="content-body-container">
        <tiles:insertAttribute name="body"/>
    </div>
    <br> <br>
    <center>
        <tiles:insertAttribute name="footer"/>
    </center>

</div>
</div>
</body>
</html>
