<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="../../layout/taglib.jsp" %>

<c:if test="${param.success eq true}">
    <div class="alert alert-success">Zmiana zakończona powodzeniem</div>
</c:if>

<div class="row">
    <div class="col-md-6">

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
                            <td>Imię:</td>
                            <td>
                                <div class="form-group">
                                    <div class="col-sm-12">
                                        <c:out  value="${personalData.firstName}"/>
                                    </div>
                                </div>
                            </td>
                        </tr>
                        <tr>
                            <td>Nazwisko:</td>
                            <td>
                                <div class="form-group">
                                    <div class="col-sm-12">
                                        <c:out  value="${personalData.surname}"/>
                                    </div>
                                </div>
                            </td>
                        </tr>
                        <tr>
                            <td>Email:</td>
                            <td>
                                <div class="form-group">
                                    <div class="col-sm-12">
                                        <c:out  value="${personalData.email}"/>
                                    </div>
                                </div>
                            </td>
                        </tr>
                        <tr>
                            <td>Numer telefonu:</td>
                            <td>
                                <div class="form-group">
                                    <div class="col-sm-12">
                                        <c:out  value="${personalData.phoneNumber}"/>
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
                            <a href="<spring:url value="/t/accountEdit.html"></spring:url>"
                               type="button" class="btn btn-warning">Edytuj <span
                                    class="glyphicon glyphicon-remove"></span>
                            </a>
                        </center>
                    </div>
                </div>

            </div>

    </div>
</div>