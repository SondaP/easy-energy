<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="pl" data-ng-app="myApp" data-ng-controller="myCtrl">
<!--Boghan-->

<%--<head>
    <link rel="stylesheet" href="css/font-awesome.css">
    <script src="js/lib/jquery-3.1.0.min.js"></script>
    <link rel="stylesheet" href="css/bootstrap.min.css">
    <link rel="stylesheet" href="css/style.css">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta charset="UTF-8">
    <title>Oferta</title>
</head>--%>
<head>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/a/electricityCalculator/css/font-awesome.css">
    <script src="${pageContext.request.contextPath}/resources/a/electricityCalculator/js/lib/jquery-3.1.0.min.js"></script>
    <script src="${pageContext.request.contextPath}/resources/a/electricityCalculator/js/lib/bootstrap.min.js"></script>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/a/electricityCalculator/css/bootstrap.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/a/electricityCalculator/css/style.css">
    <meta name="viewport" content="width=device-width, initial-scale=5">
    <meta charset="UTF-8">
    <title>Oferta</title>
</head>

<body ng-controller='DatepickerPopupDemoCtrl'>
<div class="container">
    <article class='col-xs-12 no-padding'>
        <section class="col-xs-2 no-padding">
            <label>Systemowy numer oferty:</label>
        </section>
        <section class="col-xs-2">
            <input type="number" data-ng-model='content.offerNumber' class="form-control" readonly>
        </section>
        <section class="col-xs-2">
            <label>Data utworzenia:</label>
        </section>
        <section class="col-xs-2">
            <input type="text" uib-datepicker-popup="{{format}}" data-ng-model="content.creationDate" class="form-control" value='' readonly>
        </section>
        <section class="col-xs-2 ">
            <label>Ostatinia edycja:</label>
        </section>
        <section class="col-xs-2 no-padding">
            <input type="text"  uib-datepicker-popup="{{format}}" data-ng-model="content.lastEditionDate" class="form-control" readonly>
        </section>
    </article>
    <!-- Section Dodaj klienta  -->
    <article class="col-xs-12 no-padding">
        <section class="col-xs-3 no-padding">
            <label>Dodaj klienta</label>
        </section>
        <div class="btn-group col-xs-6" role="group">
            <span class="btn btn-default glyphicon glyphicon-plus-sign" aria-hidden="true"></span>
            <span class="btn btn-default glyphicon glyphicon-search" aria-hidden="true"></span>
        </div>
    </article>
    <!-- Section with checkboxes "Ustaw parametry.." and "Ukryj sekcję"-->
    <article class="col-xs-12 no-padding" ng-controller='CloseSectionCtrl as close'>
        <section class="col-xs-6 no-padding">
            <div class="checkbox">
                <label>
                    <input data-ng-model='content.offerCalculationPerReceiverPointSet' type="checkbox" data-ng-change='changeSettingsCheckbox()'><span>Ustaw parametry oferty odzielnie dla każdego punktu odbioru</span></label>
            </div>
        </section>
        <section class="col-xs-6 no-padding">
            <div class="checkbox">
                <label>
                    <input data-ng-model='close.isSectionClose' type="checkbox" data-ng-change='close.closeSections()'><span>Ukryj sekcję</span></label>
            </div>
        </section>
    </article>
    <!--End Section with checkboxes "Ustaw parametry.." and "Ukryj sekcję"-->
    <!-- lość Stref for all Punct Odbioru-->
    <article class="col-xs-12 no-padding" data-ng-show='content.receiverPointList.length>=1 && content.offerCalculationPerReceiverPointSet==false'>
        <section class="col-xs-2 no-padding">
            <label>lość Stref:</label>
        </section>
        <section class="col-xs-1">
            <input type='number' min="1" max="4" data-ng-disabled="true" data-ng-model='content.receiverPointList[0].actualNumberOfZones' class="form-control">
        </section>
        <section class="col-xs-2 btn-group ">
            <span class="btn btn-default glyphicon glyphicon-plus-sign" data-ng-disabled="disableFirstSection" aria-hidden="true" data-ng-click='disableFirstSection || addNumberOfZones($index)'></span>
            <span class="btn btn-default glyphicon glyphicon-minus" data-ng-disabled="disableFirstSection" aria-hidden="true" data-ng-click='disableFirstSection || deleteNumberOfZones($index)'></span>
        </section>
    </article>
    <!-- End lość Stref for all Punct Odbioru -->
    <!-- Punct Item -->
    <article class="punct-item  no-padding frame-color" data-ng-repeat='receiverPointList in content.receiverPointList' ng-controller="ShowHideController as show" ng-init='punktIndex=$index'>
        <nav class="navbar navbar-default">
            <span class="btn showhide-color glyphicon  pull-left" ng-class="Data.status[$index]" aria-hidden="true" data-ng-click='show.showhide($index)'></span>
            <p class="navbar-text">
                {{$index+1}}. Punkt odbioru ({{receiverPointList.receiverPointDescription}})</p>
            <span class="navbar-brand glyphicon glyphicon-trash" aria-hidden="true" data-ng-click='removeReciverPoint($index)'></span>
        </nav>
        <div class="punct-container " ng-hide='Data.isHide[$index]'>
            <article class="col-xs-12 ">
                <section class="col-xs-4 no-padding">
                    <label class='fields-color'>Numer licznika / opis punktu odbioru:</label>
                </section>
                <section class="col-xs-3">
                    <input data-ng-disabled="disableFirstSection" type="text" data-ng-model='receiverPointList.receiverPointDescription' class="form-control">
                </section>
                <section class="col-xs-2 no-padding">
                    <label class='fields-color'>Nazwa taryfy:</label>
                </section>
                <section class="col-xs-3">
                    <input data-ng-disabled="disableFirstSection" type="text" data-ng-model='receiverPointList.tariffCode' class="form-control">
                </section>
            </article>
            <article class="col-xs-12 " data-ng-show='content.offerCalculationPerReceiverPointSet==true '>
                <section class="col-xs-2 no-padding">
                    <label class='fields-color'>lość Stref:</label>
                </section>
                <section class="col-xs-1">
                    <input type='number' min="1" max="4" data-ng-disabled="true" data-ng-model='receiverPointList.actualNumberOfZones' class="form-control">
                </section>
                <section class="col-xs-2 btn-group ">
                    <span class="btn btn-default glyphicon glyphicon-plus-sign" data-ng-disabled="disableFirstSection" aria-hidden="true" data-ng-click='disableFirstSection || addNumberOfZones($index)'></span>
                    <span class="btn btn-default glyphicon glyphicon-minus" data-ng-disabled="disableFirstSection" aria-hidden="true" data-ng-click='disableFirstSection || deleteNumberOfZones($index)'></span>
                </section>
            </article>
            <article class="col-xs-12 ">
                <table class="table table-bordered ">
                    <thead>
                    <tr>
                        <th class='col-xs-1 fields-color'></th>
                        <th class='col-xs-1 fields-color' data-ng-repeat='actualZoneList in receiverPointList.actualZoneList'>{{$index+1}}</th>
                    </tr>
                    </thead>
                    <tbody>
                    <tr>
                        <th class='col-xs-1'>Nazwa strefy:</th>
                        <th class='col-xs-1 ' data-ng-repeat='actualZoneList in receiverPointList.actualZoneList'>
                            <input data-ng-disabled="disableFirstSection" class='form-control' type="text" data-ng-model='actualZoneList.actualZoneCodeCode' data-ng-change='changeNameOfZoneCode($parent.$index,$index,actualZoneList.actualZoneCodeCode)'>
                        </th>
                    </tr>
                    </tbody>
                </table>
            </article>
            <article class="col-xs-12 ">
                <table class="table table-bordered ">
                    <thead>
                    <tr>
                        <th class='fields-color'>Zużycie</th>
                    </tr>
                    <tr>
                        <th class='fields-color'>Lp</th>
                        <th class='fields-color'>Dokument</th>
                        <th class='fields-color'>Początek okresu</th>
                        <th class='fields-color'>Koniec okresu</th>
                        <th class='fields-color' data-ng-repeat='invoiceZone in receiverPointList.invoiceList[0].invoiceZoneConsumptionList'>{{invoiceZone.actualZoneCode}}</th>
                    </tr>
                    </thead>
                    <tbody>
                    <tr data-ng-repeat='invoiceList in receiverPointList.invoiceList'>
                        <th>{{$index+1}}</th>
                        <th>
                            <input type="text" class='form-control' data-ng-model='invoiceList.documentNumber' data-ng-disabled="disableFirstSection">
                        </th>
                        <th>

                            <p class="input-group">
                                <input  data-ng-disabled="disableFirstSection" type="text" class="form-control" uib-datepicker-popup="{{format}}" ng-model="invoiceList.periodStart" is-open="opened[$index]"  datepicker-options="dateOptions" ng-required="true" close-text="Zamknąć" alt-input-formats="altInputFormats" />
                                <span class="input-group-btn">
                                     <button data-ng-disabled="disableFirstSection" type="button" class="btn btn-default" ng-click="open($index)"><i class="glyphicon glyphicon-calendar"></i></button>
                                     </span>
                            </p>
                        </th>
                        <th>

                            <p class="input-group">
                                <input  data-ng-disabled="disableFirstSection" type="text" class="form-control" uib-datepicker-popup="{{format}}" ng-model="invoiceList.getPeriodStop" is-open="opened2[$index]"  datepicker-options="dateOptions" ng-required="true" close-text="Zamknąć" alt-input-formats="altInputFormats" />
                                <span class="input-group-btn">
                                     <button data-ng-disabled="disableFirstSection" type="button" class="btn btn-default" ng-click="open2($index)"><i class="glyphicon glyphicon-calendar"></i></button>
                                     </span>
                            </p>

                        </th>
                        <th data-ng-repeat='invoiceZone in invoiceList.invoiceZoneConsumptionList'>
                            <input type="number" class='form-control' data-ng-model='invoiceZone.unitConsumption' data-ng-disabled="disableFirstSection">
                        </th>
                        <th>
                            <div class="btn-group">
                                <span class="btn btn-default glyphicon glyphicon-remove" aria-hidden="true" data-ng-show='$last=!firts' data-ng-hide='$first==$last' data-ng-click='deleteUse($parent.$index,$index)'></span>
                                <span data-ng-show="$last" class="btn btn-default glyphicon glyphicon-plus-sign" aria-hidden="true" data-ng-click='addUse($parent.$index,$index)'></span>
                            </div>
                        </th>
                    </tr>
                    </tbody>
                </table>
            </article>
            <article class="buttons col-xs-12 ">
                <button class="btn button-color pull-right" data-ng-model='disableFirstSection' data-ng-click='disableFields()'>Zatwierdź sekcję</button>
                <button class="btn button-color pull-right" data-ng-model='disableFirstSection' data-ng-click='enableFields()'>Edytuj sekcję</button>
            </article>
            <article class="row  calc-offer no-padding " data-ng-show="content.offerCalculationPerReceiverPointSet" ng-controller='ShowHideSummaryCalculation as showSummaryCalc'>
                <nav class="navbar navbar-default">
                    <span class="btn showhide-color glyphicon  pull-left" ng-class="showSummaryCalc.statusSummaryCalculation" aria-hidden="true" data-ng-click='showSummaryCalc.showhideSummaryCalculation()'></span>
                    <p class="navbar-text">Sekcja kalkulacji oferty</p>
                </nav>
                <div class="section-calc-item" ng-hide="showSummaryCalc.isSummaryCalcHide">
                    <h2 class='fields-color'> Dane łączne z licznika</h2>
                    <section class="col-xs-12">
                        <table class="table table-bordered ">
                            <tbody>
                            <tr>
                                <th>Łącznia ilość dni na podstawie zakresów</th>
                                <th>
                                    {{receiverPointList.receiverPointOfferCalculation.totalConsumptionSummary.totalNumberOfDaysForAllPeriods}}</th>
                            </tr>
                            <tr>
                                <th>Łączna ilość kwH w podanych zakresach</th>
                                <th>{{receiverPointList.receiverPointOfferCalculation.totalConsumptionSummary.totalElectricityUnitsConsumptionInAllPeriods}}</th>
                            </tr>
                            <tr>
                                <th>Szacowane zużycie roczne w Mwh</th>
                                <th>{{receiverPointList.receiverPointOfferCalculation.totalConsumptionSummary.predictedElectricityUnitConsumptionPerYear}}</th>
                            </tr>
                            </tbody>
                        </table>
                    </section>
                    <h2 class='fields-color'>Aktualne ceny klienta</h2>
                    <section class="col-xs-12">
                        <table class="table table-bordered ">
                            <thead>
                            <tr>
                                <th class='fields-color'></th>
                                <th class='fields-color' data-ng-repeat='actualZoneFeeList in receiverPointList.receiverPointOfferCalculation.actualReceiverPointFees.actualZoneFeeList'>{{actualZoneFeeList.actualZoneCode}}</th>
                            </tr>
                            </thead>
                            <tbody>
                            <tr>
                                <th>Aktualne ceny za kWH</th>
                                <th data-ng-repeat='actualZoneFeeList in receiverPointList.receiverPointOfferCalculation.actualReceiverPointFees.actualZoneFeeList'>
                                    <input type="number" class='form-control' data-ng-model='actualZoneFeeList.actualUnitPrice' data-ng-disabled='disableActualPrice'>
                                </th>
                            </tr>
                            </tbody>
                        </table>
                    </section>
                    <section class="col-xs-12">
                        <section class="col-xs-3 no-padding">
                            <label class='fields-color'>Aktualna opłata handlowa:</label>
                        </section>
                        <section class="col-xs-1">
                            <input type="number" data-ng-disabled='disableActualPrice' data-ng-model='receiverPointList.receiverPointOfferCalculation.actualReceiverPointFees.actualTradeFee' class="form-control">
                        </section>
                    </section>
                    <section class="buttons col-xs-12">
                        <button class=" button-color btn  pull-right" data-ng-model='disableActualPrice' data-ng-click='disableActualSection()'>Zatwierdź sekcję</button>
                        <button class="btn button-color pull-right" data-ng-click='enableActualSection()'>Edytuj sekcję</button>
                    </section>
                    <h2 class='fields-color'>Parametry oferty</h2>
                    <article class="col-xs-12">
                        <section class="col-xs-3 no-padding">
                            <label class='fields-color'>Proponowana długość kontraktu:</label>
                        </section>
                        <section class="col-xs-1">
                            <input data-ng-disabled='disableParametersOffer' type="number" data-ng-model='receiverPointList.receiverPointOfferCalculation.offerParameters.proposalContractMonthLength' class="form-control">
                        </section>
                       <%-- <section class="col-xs-3 no-padding">
                            <label class='fields-color'>Domyślna oferowana opłata handlowa:</label>
                        </section>
                        <section class="col-xs-2">

                            <input data-ng-disabled='disableParametersOffer' type="number" data-ng-model='receiverPointList.receiverPointOfferCalculation.offerParameters.defaultProposalTradeFee' class="form-control">
                        </section>--%>
                    </article>
                    <%--<section class="col-xs-12">
                        <table class="table table-bordered ">
                            <thead>
                            <tr>
                                <th class='fields-color'></th>
                                <th class='fields-color' data-ng-repeat='defaultZoneParamsList in receiverPointList.receiverPointOfferCalculation.offerParameters.defaultZoneParamsList'>{{defaultZoneParamsList.actualZoneCode}}</th>
                            </tr>
                            </thead>
                            <tbody>
                            <tr>
                                <th>Domyślna oferowana cena:</th>
                                <th data-ng-repeat='defaultZoneParamsList in receiverPointList.receiverPointOfferCalculation.offerParameters.defaultZoneParamsList'>
                                    <input data-ng-disabled='disableParametersOffer' type="number" class='form-control' data-ng-model='defaultZoneParamsList.defaultUnitPrice'>
                                </th>
                            </tr>
                            </tbody>
                        </table>
                    </section>--%>
                    <article class="buttons col-xs-12">
                        <button class="btn button-color pull-right" data-ng-model='disableParametersOffer' data-ng-click='disableParametersSection()'>Zatwierdź sekcję</button>
                        <button class="btn button-color pull-right" data-ng-click='enableParametersSection()'>Edytuj sekcję</button>
                    </article>
                </div>
            </article>
            <article class="row no-padding offers-retailers" data-ng-show="content.offerCalculationPerReceiverPointSet" ng-controller="ShowHideOfertu as showOfertu">
                <nav class="navbar navbar-default">
                    <span class="btn showhide-color glyphicon  pull-left" ng-class="showOfertu.statusOfertu" aria-hidden="true" data-ng-click='showOfertu.showhideOfertu()'></span>
                    <p class="navbar-text">Oferty sprzedawców</p>
                </nav>
                <div class="offers-retailers-container" ng-hide='showOfertu.isOfertuHide'>
                    <article class="offers-retailers-item" data-ng-repeat='proposalSellerList in receiverPointList.receiverPointOfferCalculation.proposalSellerList' ng-controller='ShowHideCalculationCtrl as hideCalc'>
                        <nav class="navbar navbar-default">
                            <span class="btn button-color glyphicon  pull-left" ng-class="hideCalc.statusCalculation" aria-hidden="true" data-ng-click='hideCalc.showhideCalculation()'></span>
                            <p class="navbar-text">{{$index+1}}. Kalkulacja ({{proposalSellerList.sellerCode}})</p>
                            <span class="navbar-brand glyphicon glyphicon-trash" aria-hidden="true" data-ng-click='removeOfferRetailer($index,punktIndex)'></span>
                        </nav>
                        <div class="calculation-container" ng-hide='hideCalc.isCalculationHide'>
                            <article class="col-xs-12">
                                <section class="col-xs-2 no-padding">
                                    <label class='fields-color'>Nazwa sprzedawcy:</label>
                                </section>
                                <section class="col-xs-3">
                                    <select class='form-control' ng-model="proposalSellerList.sellerCode" data-ng-disabled='disableCalculationOffer'>
                                        <option >{{proposalSellerList.sellerCode}}</option>
                                        <option ng-repeat="item in aviableSellers" value="{{item}}">{{item}}</option>
                                    </select>

                                </section>
                                <section class="col-xs-3 no-padding">
                                    <label class='fields-color'>Data aktualizacji cennika taryfy:</label>
                                </section>
                                <section class="col-xs-2">


                                    <p class="input-group" data-ng-disabled='disableCalculationOffer'>
                                        <input type="text"  data-ng-disabled='disableCalculationOffer' class="form-control" uib-datepicker-popup="{{format}}" ng-model="proposalSellerList.sellerTariffPublicationDate" is-open="opened3[$index]"  datepicker-options="dateOptions" ng-required="true" close-text="Zamknąć" alt-input-formats="altInputFormats" />
                                        <span class="input-group-btn">
                                     <button type="button" data-ng-disabled='disableCalculationOffer' class="btn btn-default" ng-click="open3($index)"><i class="glyphicon glyphicon-calendar"></i></button>
                                     </span>
                                    </p>
                                </section>
                            </article>
                            <article class="col-xs-12">
                                <section class="col-xs-3 no-padding">
                                    <label class='fields-color'>Oferowana opłata handlowa:</label>
                                </section>
                                <section class="col-xs-2">
                                    <input type="number" data-ng-disabled='disableCalculationOffer' data-ng-model='proposalSellerList.proposalTradeFee' class="form-control">
                                </section>
                            </article>
                            <article class="col-xs-12">
                                <table class="table table-bordered ">
                                    <thead>
                                    <tr>
                                        <th class='fields-color'>^</th>
                                        <th class='fields-color' data-ng-repeat='proposalZoneDetailsList in proposalSellerList.proposalZoneDetailsList'>
                                            {{proposalZoneDetailsList.actualZoneCode}}
                                        </th>
                                    </tr>
                                    </thead>
                                    <tbody>
                                    <tr>
                                        <th>Cena Operatora z cennika</th>
                                        <th data-ng-repeat='proposalZoneDetailsList in proposalSellerList.proposalZoneDetailsList'>
                                            <input type="number" data-ng-disabled='disableCalculationOffer' class='form-control' data-ng-model='proposalZoneDetailsList.sellerMinimalUnitPrice'>
                                        </th>
                                    </tr>
                                    <tr>
                                        <th>Oferowana cena / MWh</th>
                                        <th data-ng-repeat='proposalZoneDetailsList in proposalSellerList.proposalZoneDetailsList'>
                                            <input type="number" data-ng-disabled='disableCalculationOffer' class='form-control' data-ng-model='proposalZoneDetailsList.proposalUnitPrice'>
                                        </th>
                                    </tr>
                                    <%--  <tr>
                                <th>Nazwa oferowanej strefy (some description)</th>
                                <th data-ng-repeat='proposalZoneDetailsList in proposalSellerList.proposalZoneDetailsList'>
                                    <input type="text" data-ng-disabled='disableCalculationOffer' class='form-control' data-ng-model='proposalZoneDetailsList.proposalZoneCode'>
                                </th>
                            </tr>--%>
                                    </tbody>
                                </table>
                            </article>
                            <section class="buttons col-xs-12">
                                <button class="btn button-color-calculate pull-right" data-ng-click='sendCalculation()'>Wylicz ofertę</button>
                                <button class="btn button-color pull-right" data-ng-model='disableCalculationOffer' data-ng-click='disableCalculationSection()'>Zatwierdź sekcję</button>
                                <button class="btn button-color pull-right" data-ng-click='enableCalculationSection()'>Edytuj sekcję</button>
                            </section>
                            <article class="col-xs-12">
                                <table class="table table-bordered table-striped col-xs-12 ">
                                    <thead>
                                    <tr>
                                        <th class="fields-color">Na podstawienie cennika z dnia</th>
                                        <th class="fields-color">Szacowana masa marży dla całej umowy</th>
                                        <th class="fields-color">Szacowana masa marży w skali roku</th>
                                        <th class="fields-color">Szacowana oszczędność w skali roku</th>
                                        <th class="fields-color">Szacowana oszczędność w skali umowy</th>
                                        <th class="fields-color">Oszczędność w ujęciu procentowym</th>
                                    </tr>
                                    </thead>
                                    <tbody>
                                    <tr>
                                        <th>
                                            {{proposalSellerList.receiverPointEstimation.receiverPointDataEstimation.tariffIssueDate | date:'dd/MM/yyyy'}}
                                        </th>
                                        <th>
                                            {{proposalSellerList.receiverPointEstimation.receiverPointDataEstimation.estimatedContractProfitValue}}
                                        </th>
                                        <th>
                                            {{proposalSellerList.receiverPointEstimation.receiverPointDataEstimation.estimatedContractProfitValueInYearScale}}
                                        </th>
                                        <th>
                                            {{proposalSellerList.receiverPointEstimation.receiverPointDataEstimation.estimatedSavingsInYearScale}}
                                        </th>
                                        <th>
                                            {{proposalSellerList.receiverPointEstimation.receiverPointDataEstimation.estimatedSavingsInContractScale}}
                                        </th>
                                        <th>
                                            {{proposalSellerList.receiverPointEstimation.receiverPointDataEstimation.estimatedSavingsInPercentage}}
                                        </th>
                                    </tr>
                                    </tbody>
                                </table>
                            </article>
                            <article class="col-xs-12">
                                <table class="table table-bordered table-striped col-xs-12 ">
                                    <thead>
                                    <tr>
                                        <th class='fields-color'>Pułap</th>
                                        <th class='fields-color'>Prowizja roczna</th>
                                        <th class='fields-color'>
                                            Prowizja z umowy</th>
                                        <th class='fields-color'>Prowizja Partnera z umowy</th>
                                    </tr>
                                    </thead>
                                    <tbody>
                                    <tr data-ng-repeat='receiverPointProvisionList in proposalSellerList.receiverPointEstimation.receiverPointProvisionList'>
                                        <th>{{receiverPointProvisionList.levelCode}}</th>
                                        <th>{{receiverPointProvisionList.traderProvisionInYearScale}}</th>
                                        <th>{{receiverPointProvisionList.traderProvisionInContractScale}}</th>
                                        <th>{{receiverPointProvisionList.partnerProvisionInContractScale}}</th>
                                    </tr>
                                    </tbody>
                                </table>
                            </article>
                        </div>
                    </article>
                    <article class="col-xs-12 ">
                        <section class="col-xs-2 no-padding">
                            <label class='btn-addSeller'>Dodaj sprzedawcę</label>
                        </section>
                        <section class="col-xs-2">
                            <span class="btn btn-default glyphicon glyphicon-plus-sign" data-ng-click='addCalculationOffer($index)' aria-hidden="true"></span>
                        </section>
                    </article>
                </div>
            </article>
        </div>
    </article>
    <!-- End Punct Item -->
    <article class="col-xs-12 no-padding">
        <section class="col-xs-2 no-padding">
            <label class='color-blue'>Dodaj punkt odbioru:</label>
        </section>
        <section class="col-xs-2">
            <span class="btn btn-default glyphicon glyphicon-plus-sign" data-ng-click='addPunctItem()' aria-hidden="true"></span>
        </section>
    </article>
    <!-- all Reciever Point Offer Calcilation -->
    <div class="out-calc-container" data-ng-hide="content.offerCalculationPerReceiverPointSet">
        <article class="col-xs-12 no-padding calc-offer-out" ng-controller='ShowHideSummaryCalculation as showSummaryCalc'>
            <nav class="navbar navbar-default">
                <span class="btn button-color glyphicon  pull-left" ng-class="showSummaryCalc.statusSummaryCalculation" aria-hidden="true" data-ng-click='showSummaryCalc.showhideSummaryCalculation()'></span>
                <p class="navbar-text">Sekcja kalkulacji oferty</p>
            </nav>
            <div class="section-calc-item" ng-hide="showSummaryCalc.isSummaryCalcHide">
                <h2 class='fields-color'>Dane łączne z licznika</h2>
                <section class="col-xs-12">
                    <table class="table table-bordered ">
                        <tbody>
                        <tr>
                            <th>Łącznia ilość dni na podstawie zakresów</th>
                            <th>
                                {{content.allReceiverPointsOfferCalculation.totalConsumptionSummary.totalNumberOfDaysForAllPeriods}}</th>
                        </tr>
                        <tr>
                            <th>Łączna ilość kwH w podanych zakresach</th>
                            <th>{{content.allReceiverPointsOfferCalculation.totalConsumptionSummary.totalElectricityUnitsConsumptionInAllPeriods}}</th>
                        </tr>
                        <tr>
                            <th>Szacowane zużycie roczne w Mwh</th>
                            <th>{{content.allReceiverPointsOfferCalculation.totalConsumptionSummary.predictedElectricityUnitConsumptionPerYear}}</th>
                        </tr>
                        </tbody>
                    </table>
                </section>
                <h2 class='fields-color'>Aktualne ceny klienta</h2>
                <section class="col-xs-12">
                    <table class="table table-bordered ">
                        <thead>
                        <tr>
                            <th class='fields-color'></th>
                            <th class='fields-color' data-ng-repeat='actualZoneFeeList in content.allReceiverPointsOfferCalculation.actualReceiverPointFees.actualZoneFeeList'>{{actualZoneFeeList.actualZoneCode}}</th>
                        </tr>
                        </thead>
                        <tbody>
                        <tr>
                            <th>Aktualne ceny za kWH</th>
                            <th data-ng-repeat='actualZoneFeeList in content.allReceiverPointsOfferCalculation.actualReceiverPointFees.actualZoneFeeList'>
                                <input type="number" class='form-control' data-ng-model='actualZoneFeeList.actualUnitPrice' data-ng-disabled='disableActualPrice'>
                            </th>
                        </tr>
                        </tbody>
                    </table>
                </section>
                <section class="col-xs-12">
                    <section class="col-xs-3 no-padding">
                        <label class='fields-color'>Aktualna opłata handlowa:</label>
                    </section>
                    <section class="col-xs-1">
                        <input type="number" data-ng-disabled='disableActualPrice' data-ng-model='content.allReceiverPointsOfferCalculation.actualReceiverPointFees.actualTradeFee' class="form-control">
                    </section>
                </section>
                <section class="buttons col-xs-12">
                    <button class="btn button-color pull-right" data-ng-model='disableActualPrice' data-ng-click='disableActualSection()'>Zatwierdź sekcję</button>
                    <button class="btn button-color pull-right" data-ng-click='enableActualSection()'>Edytuj sekcję</button>
                </section>
                <div class="offer-parameters">
                    <h2 class='fields-color'>Parametry oferty</h2>
                    <article class="col-xs-12">
                        <section class="col-xs-3 no-padding">
                            <label class='fields-color'>Proponowana długość kontraktu:</label>
                        </section>
                        <section class="col-xs-1">
                            <input data-ng-disabled='disableParametersOffer' type="number" data-ng-model='content.allReceiverPointsOfferCalculation.offerParameters.defaultProposalTradeFee' class="form-control">
                        </section>
                       <%-- <section class="col-xs-3 no-padding">
                            <label class='fields-color'>Domyślna oferowana opłata handlowa:</label>
                        </section>
                        <section class="col-xs-2">
                            <input data-ng-disabled='disableParametersOffer' type="number" data-ng-model='content.allReceiverPointsOfferCalculation.offerParameters.proposalContractMonthLength' class="form-control">
                        </section>--%>
                    </article>
                    <%--<section class="col-xs-12">
                        <table class="table table-bordered ">
                            <thead>
                            <tr>
                                <th class='fields-color'></th>
                                <th class='fields-color' data-ng-repeat='defaultZoneParamsList in content.allReceiverPointsOfferCalculation.offerParameters.defaultZoneParamsList'>{{defaultZoneParamsList.actualZoneCode}}
                                </th>
                            </tr>
                            </thead>
                            <tbody>
                            <tr>
                                <th>Domyślna oferowana cena:</th>
                                <th data-ng-repeat='defaultZoneParamsList in content.allReceiverPointsOfferCalculation.offerParameters.defaultZoneParamsList'>
                                    <input data-ng-disabled='disableParametersOffer' type="number" class='form-control' data-ng-model='defaultZoneParamsList.defaultUnitPrice'>
                                </th>
                            </tr>
                            </tbody>
                        </table>
                    </section>--%>
                    <article class="buttons col-xs-12">
                        <button class="btn button-color pull-right" data-ng-model='disableParametersOffer' data-ng-click='disableParametersSection()'>Zatwierdź sekcję</button>
                        <button class="btn button-color pull-right" data-ng-click='enableParametersSection()'>Edytuj sekcję</button>
                    </article>
                </div>
            </div>
        </article>
        <article class="col-xs-12 no-padding offers-retailers" ng-controller="ShowHideOfertu as showOfertu">
            <nav class="navbar navbar-default">
                <span class="btn showhide-color glyphicon  pull-left" ng-class="showOfertu.statusOfertu" aria-hidden="true" data-ng-click='showOfertu.showhideOfertu()'></span>
                <p class="navbar-text">Oferty sprzedawców</p>
            </nav>
            <div ng-hide='showOfertu.isOfertuHide'>
                <article class="offers-retailers-item" data-ng-repeat='proposalSellerList in content.allReceiverPointsOfferCalculation.proposalSellerList' ng-controller='ShowHideCalculationCtrl as calc'>
                    <nav class="navbar navbar-default">
                        <span class="btn button-color glyphicon  pull-left" ng-class="calc.statusCalculation" aria-hidden="true" data-ng-click='calc.showhideCalculation()'></span>
                        <p class="navbar-text">
                            {{$index+1}}. Kalkulacja ({{proposalSellerList.sellerCode}})</p>
                        <span class="navbar-brand glyphicon glyphicon-trash" aria-hidden="true" data-ng-click='removeOfferRetailer($index,$parent.$index)'></span>
                    </nav>
                    <div class="calculation-item-container" ng-hide='calc.isCalculationHide'>
                        <article class="col-xs-12">
                            <section class="col-xs-2 no-padding">
                                <label class='fields-color'>Nazwa sprzedawcy:</label>
                            </section>
                            <section class="col-xs-3">
                                <select class='form-control' ng-model="proposalSellerList.sellerCode" data-ng-disabled='disableCalculationOffer'>
                                    <option >{{proposalSellerList.sellerCode}}</option>
                                    <option ng-repeat="item in aviableSellers" value="{{item}}">{{item}}</option>
                                </select>

                            </section>
                            <section class="col-xs-3 no-padding">
                                <label class='fields-color'>Data aktualizacji cennika taryfy:</label>
                            </section>
                            <section class="col-xs-2">

                                <p class="input-group" data-ng-disabled='disableCalculationOffer'>
                                    <input data-ng-disabled='disableCalculationOffer' type="text"  data-ng-disabled='disableCalculationOffer' class="form-control" uib-datepicker-popup="{{format}}" ng-model="proposalSellerList.sellerTariffPublicationDate" is-open="opened4[$index]"  datepicker-options="dateOptions" ng-required="true" close-text="Zamknąć" alt-input-formats="altInputFormats" />
                                    <span class="input-group-btn">
                                     <button data-ng-disabled='disableCalculationOffer' type="button" data-ng-disabled='disableCalculationOffer' class="btn btn-default" ng-click="open4($index)"><i class="glyphicon glyphicon-calendar"></i></button>
                                     </span>
                                </p>
                            </section>
                        </article>
                        <article class="col-xs-12">
                            <section class="col-xs-3 no-padding">
                                <label class='fields-color'>Oferowana opłata handlowa:</label>
                            </section>
                            <section class="col-xs-2">
                                <input type="number" data-ng-disabled='disableCalculationOffer' data-ng-model='proposalSellerList.proposalTradeFee' class="form-control">
                            </section>
                        </article>
                        <article class="col-xs-12">
                            <table class="table table-bordered ">
                                <thead>
                                <tr>
                                    <th class='fields-color'>^</th>
                                    <th class='fields-color' data-ng-repeat='proposalZoneDetailsList in proposalSellerList.proposalZoneDetailsList'>
                                        {{proposalZoneDetailsList.actualZoneCode}}
                                    </th>
                                </tr>
                                </thead>
                                <tbody>
                                <tr>
                                    <th>Cena Operatora z cennika</th>
                                    <th data-ng-repeat='proposalZoneDetailsList in proposalSellerList.proposalZoneDetailsList'>
                                        <input type="number" data-ng-disabled='disableCalculationOffer' class='form-control' data-ng-model='proposalZoneDetailsList.sellerMinimalUnitPrice'>
                                    </th>
                                </tr>
                                <tr>
                                    <th>Oferowana cena / MWh</th>
                                    <th data-ng-repeat='proposalZoneDetailsList in proposalSellerList.proposalZoneDetailsList'>
                                        <input type="number" data-ng-disabled='disableCalculationOffer' class='form-control' data-ng-model='proposalZoneDetailsList.proposalUnitPrice'>
                                    </th>
                                </tr>
                                <%-- <tr>
                            <th >Nazwa oferowanej strefy </th>
                            <th data-ng-repeat='proposalZoneDetailsList in proposalSellerList.proposalZoneDetailsList'>
                                <input type="text" data-ng-disabled='disableCalculationOffer' class='form-control' data-ng-model='proposalZoneDetailsList.proposalZoneCode'>
                            </th>
                        </tr>--%>
                                </tbody>
                            </table>
                        </article>
                        <section class="buttons col-xs-12">
                            <button class="btn button-color-calculate pull-right" data-ng-click='sendCalculation()'>Wylicz ofertę</button>
                            <button class="btn button-color pull-right" data-ng-model='disableCalculationOffer' data-ng-click='disableCalculationSection()'>Zatwierdź sekcję</button>
                            <button class="btn button-color pull-right" data-ng-click='enableCalculationSection()'>Edytuj sekcję</button>
                        </section>
                        <article class="col-xs-12">
                            <table class="table table-bordered table-striped col-xs-12 ">
                                <thead>
                                <tr>
                                    <th class="fields-color">Na podstawienie cennika z dnia</th>
                                    <th class="fields-color">Szacowana masa marży dla całej umowy</th>
                                    <th class="fields-color">Szacowana masa marży w skali roku</th>
                                    <th class="fields-color">Szacowana oszczędność w skali roku</th>
                                    <th class="fields-color">Szacowana oszczędność w skali umowy</th>
                                    <th class="fields-color">Oszczędność w ujęciu procentowym</th>
                                </tr>
                                </thead>
                                <tbody>
                                <tr>
                                    <th>
                                        {{proposalSellerList.receiverPointEstimation.receiverPointDataEstimation.tariffIssueDate | date:'dd/MM/yyyy'}}
                                    </th>
                                    <th>
                                        {{proposalSellerList.receiverPointEstimation.receiverPointDataEstimation.estimatedContractProfitValue}}
                                    </th>
                                    <th>
                                        {{proposalSellerList.receiverPointEstimation.receiverPointDataEstimation.estimatedContractProfitValueInYearScale}}
                                    </th>
                                    <th>
                                        {{proposalSellerList.receiverPointEstimation.receiverPointDataEstimation.estimatedSavingsInYearScale}}
                                    </th>
                                    <th>
                                        {{proposalSellerList.receiverPointEstimation.receiverPointDataEstimation.estimatedSavingsInContractScale}}
                                    </th>
                                    <th>
                                        {{proposalSellerList.receiverPointEstimation.receiverPointDataEstimation.estimatedSavingsInPercentage}}
                                    </th>
                                </tr>
                                </tbody>
                            </table>
                        </article>
                        <article class="col-xs-12">
                            <table class="table table-bordered table-striped col-xs-12 ">
                                <thead>
                                <tr>
                                    <th class='fields-color'>Pułap</th>
                                    <th class='fields-color'>Prowizja roczna</th>
                                    <th class='fields-color'>
                                        Prowizja z umowy</th>
                                    <th class='fields-color'>Prowizja Partnera z umowy</th>
                                </tr>
                                </thead>
                                <tbody>
                                <tr data-ng-repeat='receiverPointProvisionList in proposalSellerList.receiverPointEstimation.receiverPointProvisionList'>
                                    <th>{{receiverPointProvisionList.levelCode}}</th>
                                    <th>{{receiverPointProvisionList.traderProvisionInYearScale}}</th>
                                    <th>{{receiverPointProvisionList.traderProvisionInContractScale}}</th>
                                    <th>{{receiverPointProvisionList.partnerProvisionInContractScale}}</th>
                                </tr>
                                </tbody>
                            </table>
                        </article>
                    </div>
                </article>
                <article class="col-xs-12 ">
                    <section class="col-xs-2 no-padding">
                        <label class='btn-addSeller'>Dodaj sprzedawcę</label>
                    </section>
                    <section class="col-xs-2">
                        <span class="btn btn-default glyphicon glyphicon-plus-sign" data-ng-click='addCalculationOfferPoints($index)' aria-hidden="true"></span>
                    </section>
                </article>
            </div>
        </article>
    </div>
    <!-- End of all Reciever Point Ofeer -->
    <!-- Summary Info -->
    <article class="col-xs-12 no-padding summary-info" ng-controller='ShowHideController as showSumary'>
        <nav class="navbar navbar-default">
            <span class="btn button-color glyphicon  pull-left" ng-class="showSumary.statusSummary" aria-hidden="true" data-ng-click='showSumary.showhideSumary()'></span>
            <p class="navbar-text">Podsumowanie dla wszystkich punktów odbioru</p>
        </nav>
        <div class="summary-info-container" ng-hide='showSumary.isSummaryHide'>
            <h2 class='fields-color'>Estymacja dla punktu odbioru</h2>
            <article class="summary-info-item " data-ng-repeat='receiverPointEstimationList in content.offerSummaryDTO.receiverPointEstimationList'>
                <h2 class='fields-color'>{{receiverPointEstimationList.sellerCode}}</h2>
                <section class="col-xs-12">
                    <table class="table table-bordered table-striped col-xs-12 ">
                        <thead>
                        <tr>
                            <!--<th>Na podstawienie cennika z dnia</th>-->
                            <th class="fields-color">Szacowana masa marży dla wszystkich punktów</th>
                            <th class="fields-color">Szacowana całkowita masa marży w skali roku</th>
                            <th class="fields-color">Szacowana oszczędność w skali roku dla wszystkich punktów</th>
                            <th class="fields-color">Szacowana oszczędność w skali umowy</th>
                            <th class="fields-color">Oszczędność w ujęciu procentowym dla wszystkich punktów</th>
                        </tr>
                        </thead>
                        <tbody>
                        <tr>
                            <!-- <th>
                                 {{receiverPointEstimationList.allReceiverPointsDataEstimationForSeller.tariffIssueDate | date:'dd/MM/yyyy'}}
                             </th>-->
                            <th>
                                {{receiverPointEstimationList.allReceiverPointsDataEstimationForSeller.estimatedContractValueForAllPoint}}
                            </th>
                            <th>
                                {{receiverPointEstimationList.allReceiverPointsDataEstimationForSeller.estimatedContractValueInYearScaleForAllPoint}}
                            </th>
                            <th>
                                {{receiverPointEstimationList.allReceiverPointsDataEstimationForSeller.estimatedSavingsInYearScaleForAllPoint}}
                            </th>
                            <th>
                                {{receiverPointEstimationList.allReceiverPointsDataEstimationForSeller.estimatedSavingsInContractScaleForAllPoint}}
                            </th>
                            <th>
                                {{receiverPointEstimationList.allReceiverPointsDataEstimationForSeller.estimatedSavingsInPercentageForAllPoint}}
                            </th>
                        </tr>
                        </tbody>
                    </table>
                </section>
                <h2 class='fields-color'>Prowizje z punktu odbioru</h2>
                <article class="col-xs-12">
                    <table class="table table-bordered table-striped col-xs-12 ">
                        <thead>
                        <tr>
                            <th class='fields-color'>Pułap</th>
                            <th class='fields-color'>Prowizja Handlowca roczna</th>
                            <th class='fields-color'> Prowizja Handlowa z umowy</th>
                            <th class='fields-color'>Prowizja Partnera z umowyy</th>
                        </tr>
                        </thead>
                        <tbody>
                        <tr data-ng-repeat='allReceiverPointsProvisionForSellerList in receiverPointEstimationList.allReceiverPointsProvisionForSellerList'>
                            <th>{{allReceiverPointsProvisionForSellerList.levelCode}}</th>
                            <th>{{allReceiverPointsProvisionForSellerList.traderProvisionInYearScaleForAllPoint}}</th>
                            <th>{{allReceiverPointsProvisionForSellerList.traderProvisionInContractScaleForAllPoint}}</th>
                            <th>{{allReceiverPointsProvisionForSellerList.partnerProvisionInContractScaleForAllPoint}}</th>
                        </tr>
                        </tbody>
                    </table>
                </article>
            </article>
        </div>
    </article>
    <!-- End of Summary Info -->
    <!-- Comment -->
    <article class='comment col-xs-12 no-padding'>
        <p>Komentarz do oferty</p>
        <section class="col-xs-12">
            <textarea name="comment" cols="30" data-ng-model='content.offerNote' class='form-control'></textarea>
        </section>
    </article>
    <!-- End Comment -->
    <article class="col-xs-12 no-padding">
        <button class="btn btn-save pull-right" data-ng-click='sendData()'>Zapisz ofertę</button>
    </article>
</div>
<%--<script src="js/lib/angular.min.js"></script>
<script src="js/lib/angular-animate.min.js"></script>
<script src='js/lib/angular-locale_pl-pl.js'></script>
<script src="js/services/angular-modal-service.js"></script>
<script src='js/lib/ui-bootstrap-tpls-2.2.0.min.js'></script>
<script src="js/app.js"></script>
<script src='js/services/closeservice.js'></script>
<script src='js/controllers/dateFormatConroller.js'></script>
<script src='js/controllers/yesnocontroller.js'></script>
<script src='js/controllers/erorrcontroller.js'></script>
<script src='js/controllers/showhide.js'></script>
<script src='js/controllers/closesectioncontroller.js'></script>--%>
</body>

</html>

<script src="${pageContext.request.contextPath}/resources/a/electricityCalculator/js/lib/angular.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/a/electricityCalculator/js/lib/angular-animate.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/a/electricityCalculator/js/lib//angular-locale_pl-pl.js"></script>
<script src="${pageContext.request.contextPath}/resources/a/electricityCalculator/js/services/angular-modal-service.js"></script>
<script src='${pageContext.request.contextPath}/resources/a/electricityCalculator/js/lib/ui-bootstrap-tpls-2.2.0.min.js'></script>
<script src="${pageContext.request.contextPath}/resources/a/electricityCalculator/js/app.js"></script>
<script src='${pageContext.request.contextPath}/resources/a/electricityCalculator/js/services/closeservice.js'></script>
<script src='${pageContext.request.contextPath}/resources/a/electricityCalculator/js/controllers/dateFormatConroller.js'></script>
<script src='${pageContext.request.contextPath}/resources/a/electricityCalculator/js/controllers/yesnocontroller.js'></script>
<script src='${pageContext.request.contextPath}/resources/a/electricityCalculator/js/controllers/erorrcontroller.js'></script>
<script src='${pageContext.request.contextPath}/resources/a/electricityCalculator/js/controllers/showhide.js'></script>
<script src='${pageContext.request.contextPath}/resources/a/electricityCalculator/js/controllers/closesectioncontroller.js'></script>
<script>
var pageContext = "${pageContext.request.contextPath}";
var requestSourceType = "${requestSourceType}";
var offerIdForEdition = "${offerIdForEdition}";
</script>


