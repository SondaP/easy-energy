<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="../../layout/taglib.jsp" %>

<c:if test="${param.success eq true}">
    <div class="alert alert-success">Zmiana zakończona powodzeniem</div>
</c:if>

<div class="row">
    <div class="col-md-6">
        <form:form modelAttribute="personalDataDTO" >
            <!-- left Company -->
            <div class="panel panel-default">
                <div class="panel-heading">
                    <h3 class="panel-title">
                        <strong>Dane personalne</strong>
                    </h3>
                </div>
                <div class="panel-body">
                    <table class="table table-bordered ">
                        <thead>
                        <tr>
                            <th class="col-md-4">Dane:</th>
                            <th>Edycja</th>
                        </tr>
                        </thead>
                        <tbody>

                        <tr>
                            <td><label for="firstName"
                                       class="col-sm-12 control-label">Imię:</label></td>
                            <td>
                                <div class="form-group">
                                    <div class="col-sm-12">

                                        <form:input path="firstName" value="${personalData.firstName}"
                                                    cssClass="form-control"/>
                                        <form:errors path="firstName"/>
                                    </div>
                                </div>
                            </td>
                        </tr>
                        <tr>
                            <td><label for="surname"
                                       class="col-sm-12 control-label">Nazwisko:</label></td>
                            <td>
                                <div class="form-group">
                                    <div class="col-sm-12">

                                        <form:input path="surname" value="${personalData.surname}"
                                                    cssClass="form-control"/>
                                        <form:errors path="surname"/>
                                    </div>
                                </div>
                            </td>
                        </tr>
                        <tr>
                            <td><label for="email"
                                       class="col-sm-12 control-label">Email:</label></td>
                            <td>
                                <div class="form-group">
                                    <div class="col-sm-12">

                                        <form:input path="email" value="${personalData.email}"
                                                    cssClass="form-control"/>
                                        <form:errors path="email"/>
                                    </div>
                                </div>
                            </td>
                        </tr>
                        <tr>
                            <td><label for="phoneNumber"
                                       class="col-sm-12 control-label">Numer telefonu:</label></td>
                            <td>
                                <div class="form-group">
                                    <div class="col-sm-12">

                                        <form:input path="phoneNumber" value="${personalData.phoneNumber}"
                                                    cssClass="form-control"/>
                                        <form:errors path="phoneNumber"/>
                                    </div>
                                </div>
                            </td>
                        </tr>


                        </tbody>
                    </table>

                </div>


                <div class="row">
                    <div class="col-md-6"></div>
                    <div class="col-md-6">
                        <center>
                            <a href="<spring:url value="/a/accountEdit.html"></spring:url>"
                               type="button" class="btn btn-warning">Edytuj <span
                                    class="glyphicon glyphicon-remove"></span>
                            </a>
                        </center>
                    </div>
                </div>

            </div>
            <form:input type="hidden" path="id" value="${personalData.id}"/>
        </form:form>
    </div>
</div>