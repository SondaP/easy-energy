<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="../../layout/taglib.jsp"%>

<div class="row">
    <div class="col-md-6">

        <div class="panel panel-default">
            <div class="panel-heading">
                <h3 class="panel-title">
                    <strong>Ustawienia cenników</strong>
                </h3>
            </div>
            <div class="panel-body">


                <div class="list-group">
                    <a href='<spring:url value="/user-settings/company-details.html"></spring:url>'
                       class="list-group-item">Aktualizuj cennik</a>
                    <a href="#" class="list-group-item">Historia cenników</a>
                </div>
            </div>
        </div>

        <div class="panel panel-default">
            <div class="panel-heading">
                <h3 class="panel-title">
                    <strong>Zarządzanie prowizją</strong>
                </h3>
            </div>
            <div class="panel-body">

                <div class="list-group">
                    <a href="#" class="list-group-item">Ustaw poziom prowizji dla kalkulatora oferty</a>
                </div>
            </div>
        </div>


        <div class="panel panel-default">
            <div class="panel-heading">
                <h3 class="panel-title">
                    <strong>Zarządzanie dostawcami energi</strong>
                </h3>
            </div>
            <div class="panel-body">

                <div class="list-group">
                    <a href='<spring:url value="/a/sellers.html"></spring:url>' class="list-group-item">Lista dostawców</a>
                    <a href="#" class="list-group-item">Zarejestruj dostawcę</a>
                </div>

            </div>
        </div>

        <div class="panel panel-default">
            <div class="panel-heading">
                <h3 class="panel-title">
                    <strong>Zarządzanie klientem</strong>
                </h3>
            </div>
            <div class="panel-body">

                <div class="list-group">
                    <a href="#" class="list-group-item">Lista globalnych klientów</a>
                </div>
            </div>
        </div>



    </div>
    <div class="col-md-6">

        <div class="panel panel-default">
            <div class="panel-heading">
                <h3 class="panel-title">
                    <strong>Ustawienia oferty</strong>
                </h3>
            </div>
            <div class="panel-body">

                <div class="list-group">
                    <a href="#" class="list-group-item">Dane kontaktowe na ofercie</a>
                </div>
            </div>
        </div>


        <div class="panel panel-default">
            <div class="panel-heading">
                <h3 class="panel-title">
                    <strong>Konto Partnera</strong>
                </h3>
            </div>
            <div class="panel-body">

                <div class="list-group">
                    <a href='<spring:url value="/a/account.html"></spring:url>' class="list-group-item">Edycja konta</a>
                    <a href="#" class="list-group-item">Reset hasła</a>
                </div>

            </div>
        </div>


        <div class="panel panel-default">
            <div class="panel-heading">
                <h3 class="panel-title">
                    <strong>Zarządzanie użytkownikami</strong>
                </h3>
            </div>
            <div class="panel-body">

                <div class="list-group">
                    <a href="#" class="list-group-item">Lista użytkowników</a>
                    <a href='<spring:url value="/a/register.html"></spring:url>' class="list-group-item">Zarejestruj handlowca</a>
                </div>
            </div>
        </div>




    </div>
</div>