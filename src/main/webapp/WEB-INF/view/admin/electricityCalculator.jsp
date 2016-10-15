<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="../../layout/taglib.jsp" %>


<!DOCTYPE html>
<html lang="pl" data-ng-app="myApp" data-ng-controller="myCtrl">

<head>
    <link rel="stylesheet" href="/resources/energyOfferCalc_V_1/css/font-awesome.css">
    <script src="/resources/energyOfferCalc_V_1/js/lib/jquery-3.1.0.min.js"></script>
    <script src="/resources/energyOfferCalc_V_1/js/lib/bootstrap.min.js"></script>
    <link rel="stylesheet" href="/resources/energyOfferCalc_V_1/css/bootstrap.min.css">
    <link rel="stylesheet" href="/resources/energyOfferCalc_V_1/css/style.css">
    <meta name="viewport" content="width=device-width, initial-scale=5">
    <meta charset="UTF-8">
    <title>Oferta</title>
</head>

<body>
<article class='col-xs-12 no-padding'>
    <section class="col-xs-2 no-padding">
        <label>Nazva ofertu</label>
    </section>
    <section class="col-xs-2">
        <input type="text" data-ng-model='content.offerNumber' class="form-control" readonly>
    </section>
    <section class="col-xs-2">
        <label>Data utworzenia:</label>
    </section>
    <section class="col-xs-2">
        <input type="date" data-ng-model="content.creationDate" class="form-control" value='' readonly>
    </section>
    <section class="col-xs-2 ">
        <label>Ostatinia edycja:</label>
    </section>
    <section class="col-xs-2 no-padding">
        <input type="date" data-ng-model="content.lastEditionDate" class="form-control" readonly>
    </section>
</article>
<article class="col-xs-12 no-padding">
    <section class="col-xs-3 no-padding">
        <label>Dodaj klienta</label>
    </section>
    <div class="btn-group col-xs-6" role="group">
        <span class="btn btn-default glyphicon glyphicon-plus-sign" aria-hidden="true"></span>
        <span class="btn btn-default glyphicon glyphicon-search" aria-hidden="true"></span>
    </div>
</article>
<article class="col-xs-12 no-padding">
    <section class="col-xs-12 no-padding">
        <input data-ng-model='content.offerCalculationPerReceiverPointSet' type="checkbox" data-ng-change='changeSettingsCheckbox()'> Ustaw parametry oferty odzielnie dla każdego punktu odbioru
    </section>
</article>
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

<article class="punct-item" data-ng-repeat='receiverPointList in content.receiverPointList'>
    <nav class="navbar navbar-default">
        <p class="navbar-text">
            {{$index+1}}. Punkt odbioru</p>
        <span class="navbar-brand glyphicon glyphicon-trash" aria-hidden="true" data-ng-click='removeReciverPoint($index)'></span>
    </nav>
    <article class="col-xs-12">
        <section class="col-xs-4 no-padding">
            <label>Numer licznika / opis punktu odbioru:</label>
        </section>
        <section class="col-xs-3">
            <input data-ng-disabled="disableFirstSection" type="text" data-ng-model='receiverPointList.receiverPointDescription' class="form-control">
        </section>
        <section class="col-xs-2 no-padding">
            <label>Nazwa taryfy:</label>
        </section>
        <section class="col-xs-3">
            <input data-ng-disabled="disableFirstSection" type="text" data-ng-model='receiverPointList.tariffCode' class="form-control">
        </section>
    </article>
    <article class="col-xs-12 " data-ng-show='content.offerCalculationPerReceiverPointSet==true '>
        <section class="col-xs-2 no-padding">
            <label>lość Stref:</label>
        </section>
        <section class="col-xs-1">
            <input type='number' min="1" max="4" data-ng-disabled="true" data-ng-model='receiverPointList.actualNumberOfZones' class="form-control">
        </section>
        <section class="col-xs-2 btn-group ">
            <span class="btn btn-default glyphicon glyphicon-plus-sign" data-ng-disabled="disableFirstSection" aria-hidden="true" data-ng-click='disableFirstSection || addNumberOfZones($index)'></span>
            <span class="btn btn-default glyphicon glyphicon-minus" data-ng-disabled="disableFirstSection" aria-hidden="true" data-ng-click='disableFirstSection || deleteNumberOfZones($index)'></span>
        </section>
    </article>
    <article class="col-xs-12">
        <table class="table table-bordered ">
            <thead>
            <tr>
                <th class='col-xs-1'></th>
                <th class='col-xs-1' data-ng-repeat='actualZoneList in receiverPointList.actualZoneList'>{{$index+1}}</th>
            </tr>
            </thead>
            <tbody>
            <tr>

                <th class='col-xs-1'>Nazwa strefy:</th>
                <th class='col-xs-1' data-ng-repeat='actualZoneList in receiverPointList.actualZoneList'>
                    <input data-ng-disabled="disableFirstSection" class='form-control' type="text" data-ng-model='actualZoneList.actualZoneCodeCode' data-ng-change='changeNameOfZoneCode($parent.$index,$index,actualZoneList.actualZoneCodeCode)'>

                </th>
            </tr>
            </tbody>
        </table>
    </article>
    <article class="col-xs-12">
        <table class="table table-bordered ">
            <thead>
            <tr>
                <th>Zużycie</th>
            </tr>
            <tr>
                <th>Lp</th>
                <th>Dokument</th>
                <th>Początek okresu</th>
                <th>Koniec okresu</th>
                <th data-ng-repeat='invoiceZone in receiverPointList.invoiceList[0].invoiceZoneConsumptionList'>{{invoiceZone.actualZoneCode}}</th>
            </tr>
            </thead>
            <tbody>
            <tr data-ng-repeat='invoiceList in receiverPointList.invoiceList'>
                <th>{{$index+1}}</th>
                <th>
                    <input type="text" class='form-control' data-ng-model='invoiceList.documentNumber' data-ng-disabled="disableFirstSection">
                </th>
                <th>
                    <input type="date" class='form-control' data-ng-model='invoiceList.periodStart' data-ng-disabled="disableFirstSection">
                </th>
                <th>
                    <input type="date" class='form-control' data-ng-model='invoiceList.getPeriodStop' data-ng-disabled="disableFirstSection">
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
    <article class="buttons col-xs-12">
        <button class="btn btn-success pull-right" data-ng-model='disableFirstSection' data-ng-click='disableFields()'>Zatwierdź sekcję</button>
        <button class="btn btn-success pull-right" data-ng-model='disableFirstSection' data-ng-click='enableFields()'>Edytuj sekcję</button>
    </article>
    <article class="col-xs-12 no-padding calc-offer" data-ng-show="content.offerCalculationPerReceiverPointSet">
        <h2>Sekcja kalkulacji oferty</h2>
        <h2> Dane łączne z licznika</h2>
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
        <h2>
            Aktualne ceny klienta</h2>
        <section class="col-xs-12">
            <table class="table table-bordered ">
                <thead>
                <tr>
                    <th></th>
                    <th data-ng-repeat='actualZoneFeeList in receiverPointList.receiverPointOfferCalculation.actualReceiverPointFees.actualZoneFeeList'>{{actualZoneFeeList.actualZoneCode}}</th>
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
                <label>Aktualna opłata handlowa:</label>
            </section>
            <section class="col-xs-1">
                <input type="number" data-ng-disabled='disableActualPrice' data-ng-model='receiverPointList.receiverPointOfferCalculation.actualReceiverPointFees.actualTradeFee' class="form-control">
            </section>
        </section>
        <section class="buttons col-xs-12">
            <button class="btn btn-success pull-right" data-ng-model='disableActualPrice' data-ng-click='disableActualSection()'>Zatwierdź sekcję</button>
            <button class="btn btn-success pull-right" data-ng-click='enableActualSection()'>Edytuj sekcję</button>
        </section>
        <div class="offer-parameters">
            <h2>Parametry oferty</h2>
            <article class="col-xs-12">
                <section class="col-xs-3 no-padding">
                    <label>Domyślna oferowana opłata handlowa:</label>
                </section>
                <section class="col-xs-2">
                    <input data-ng-disabled='disableParametersOffer' type="number" data-ng-model='receiverPointList.receiverPointOfferCalculation.offerParameters.defaultProposalTradeFee' class="form-control">
                </section>
                <section class="col-xs-3 no-padding">
                    <label>Proponowana długość kontraktu:</label>
                </section>
                <section class="col-xs-2">
                    <input data-ng-disabled='disableParametersOffer' type="number" data-ng-model='receiverPointList.receiverPointOfferCalculation.offerParameters.proposalContractMonthLength' class="form-control">
                </section>
            </article>
            <section class="col-xs-12">
                <table class="table table-bordered ">
                    <thead>
                    <tr>
                        <th></th>
                        <th data-ng-repeat='defaultZoneParamsList in receiverPointList.receiverPointOfferCalculation.offerParameters.defaultZoneParamsList'>{{defaultZoneParamsList.actualZoneCode}}</th>
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
            </section>
            <article class="buttons col-xs-12">

                <button class="btn btn-success pull-right" data-ng-model='disableParametersOffer' data-ng-click='disableParametersSection()'>Zatwierdź sekcję</button>
                <button class="btn btn-success pull-right" data-ng-click='enableParametersSection()'>Edytuj sekcję</button>
            </article>
        </div>
        <h2>Oferty sprzedawców</h2>
        <article class="col-xs-12 offers-retailers">
            <article class="offers-retailers-item" data-ng-repeat='proposalSellerList in receiverPointList.receiverPointOfferCalculation.proposalSellerList'>
                <nav class="navbar navbar-default">
                    <p class="navbar-text">
                        {{$index+1}}. Kalkulacja</p>
                    <span class="navbar-brand glyphicon glyphicon-trash" aria-hidden="true" data-ng-click='removeOfferRetailer($index,$parent.$index)'></span>
                </nav>
                <article class="col-xs-12">
                    <section class="col-xs-2 no-padding">
                        <label>Nazwa sprzedawcy:</label>
                    </section>
                    <section class="col-xs-2">
                        <input type="text" data-ng-disabled='disableCalculationOffer' data-ng-model='proposalSellerList.sellerCode' class="form-control">
                    </section>
                    <section class="col-xs-3 no-padding">
                        <label>Data aktualizacji cennika taryfy:</label>
                    </section>
                    <section class="col-xs-2">
                        <input type="date" data-ng-disabled='disableCalculationOffer' data-ng-model='proposalSellerList.sellerTariffPublicationDate' class="form-control">
                    </section>
                </article>
                <article class="col-xs-12">
                    <section class="col-xs-3 no-padding">
                        <label>Oferowana opłata handlowa:</label>
                    </section>
                    <section class="col-xs-2">
                        <input type="text" data-ng-disabled='disableCalculationOffer' data-ng-model='proposalSellerList.proposalTradeFee' class="form-control">
                    </section>
                </article>
                <article class="col-xs-12">
                    <table class="table table-bordered ">
                        <thead>
                        <tr>
                            <th>^</th>
                            <th data-ng-repeat='proposalZoneDetailsList in proposalSellerList.proposalZoneDetailsList'>
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
                            <th>Oferowana cena / MWh (some description)</th>
                            <th data-ng-repeat='proposalZoneDetailsList in proposalSellerList.proposalZoneDetailsList'>
                                <input type="number" data-ng-disabled='disableCalculationOffer' class='form-control' data-ng-model='proposalZoneDetailsList.proposalUnitPrice'>
                            </th>
                        </tr>
                        <tr>
                            <th>Nazwa oferowanej strefy (some description)</th>
                            <th data-ng-repeat='proposalZoneDetailsList in proposalSellerList.proposalZoneDetailsList'>
                                <input type="text" data-ng-disabled='disableCalculationOffer' class='form-control' data-ng-model='proposalZoneDetailsList.proposalZoneCode'>
                            </th>
                        </tr>
                        </tbody>
                    </table>
                </article>
                <section class="buttons col-xs-12">
                    <button class="btn btn-primary pull-right" data-ng-click='sendCalculation()'>Wylicz ofertę</button>
                    <button class="btn btn-success pull-right" data-ng-model='disableCalculationOffer' data-ng-click='disableCalculationSection()'>Zatwierdź sekcję</button>
                    <button class="btn btn-success pull-right" data-ng-click='enableCalculationSection()'>Edytuj sekcję</button>
                </section>
                <article class="col-xs-12">
                    <table class="table table-bordered table-striped col-xs-12 ">
                        <thead>
                        <tr>
                            <th>Na podstawienie cennika z dnia</th>
                            <th>Szacowana masa marży dla wszystkich punktów</th>
                            <th>Szacowana całkowita masa marży w skali roku</th>
                            <th>Szacowana oszczędność w skali roku dla wszystkich punktów</th>
                            <th>Szacowana oszczędność w skali umowy</th>
                            <th>Oszczędność w ujęciu procentowym dla wszystkich punktów</th>
                        </tr>
                        </thead>
                        <tbody>
                        <tr>
                            <th>
                                {{proposalSellerList.receiverPointEstimation.receiverPointDataEstimation.tariffIssueDate}}
                            </th>
                            <th>
                                {{proposalSellerList.receiverPointEstimation.receiverPointDataEstimation.estimatedUnitConsumptionInYearScale}}
                            </th>
                            <th>
                                {{proposalSellerList.receiverPointEstimation.receiverPointDataEstimation.estimatedContractProfitValue}}
                            </th>
                            <th>
                                {{proposalSellerList.receiverPointEstimation.receiverPointDataEstimation.estimatedContractProfitValueInYearScale}}
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
                            <th>Pułap</th>
                            <th>Prowizja roczna</th>
                            <th>
                                Prowizja z umowy</th>
                            <th>Prowizja Partnera z umowy</th>
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
            </article>
            <article class="col-xs-12">
                <section class="col-xs-2 no-padding">
                    <label>Dodaj sprzedawcę</label>
                </section>
                <section class="col-xs-2">
                    <span class="btn btn-default glyphicon glyphicon-plus-sign" data-ng-click='addCalculationOffer($index)' aria-hidden="true"></span>
                </section>
            </article>
        </article>
    </article>
</article>
<article class="col-xs-12 no-padding">
    <section class="col-xs-2 no-padding">
        <label>Dodaj punkt odbioru:</label>
    </section>
    <section class="col-xs-2">
        <span class="btn btn-default glyphicon glyphicon-plus-sign" data-ng-click='addPunctItem()' aria-hidden="true"></span>
    </section>
</article>

<!-- all Reciever Point Offer Calcilation -->
<article class="col-xs-12 no-padding calc-offer-out" data-ng-hide="content.offerCalculationPerReceiverPointSet">

    <h2>Sekcja kalkulacji oferty
    </h2>
    <h2>
        Dane łączne z licznika</h2>
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
    <h2>
        Aktualne ceny klienta</h2>
    <section class="col-xs-12">
        <table class="table table-bordered ">
            <thead>
            <tr>
                <th></th>
                <th data-ng-repeat='actualZoneFeeList in content.allReceiverPointsOfferCalculation.actualReceiverPointFees.actualZoneFeeList'>{{actualZoneFeeList.actualZoneCode}}</th>
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
            <label>Aktualna opłata handlowa:</label>
        </section>
        <section class="col-xs-1">
            <input type="number" data-ng-disabled='disableActualPrice' data-ng-model='content.allReceiverPointsOfferCalculation.actualReceiverPointFees.actualTradeFee' class="form-control">
        </section>
    </section>
    <section class="buttons col-xs-12">
        <button class="btn btn-success pull-right" data-ng-model='disableActualPrice' data-ng-click='disableActualSection()'>Zatwierdź sekcję</button>
        <button class="btn btn-success pull-right" data-ng-click='enableActualSection()'>Edytuj sekcję</button>
    </section>
    <div class="offer-parameters">
        <h2>Parametry oferty</h2>
        <article class="col-xs-12">
            <section class="col-xs-3 no-padding">
                <label>Domyślna oferowana opłata handlowa:</label>
            </section>
            <section class="col-xs-2">
                <input data-ng-disabled='disableParametersOffer' type="number" data-ng-model='content.allReceiverPointsOfferCalculation.offerParameters.defaultProposalTradeFee' class="form-control">
            </section>
            <section class="col-xs-3 no-padding">
                <label>Proponowana długość kontraktu:</label>
            </section>
            <section class="col-xs-2">
                <input data-ng-disabled='disableParametersOffer' type="number" data-ng-model='content.allReceiverPointsOfferCalculation.offerParameters.proposalContractMonthLength' class="form-control">
            </section>
        </article>
        <section class="col-xs-12">
            <table class="table table-bordered ">
                <thead>
                <tr>
                    <th></th>
                    <th data-ng-repeat='defaultZoneParamsList in content.allReceiverPointsOfferCalculation.offerParameters.defaultZoneParamsList'>{{defaultZoneParamsList.actualZoneCode}}</th>
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
        </section>
        <article class="buttons col-xs-12">

            <button class="btn btn-success pull-right" data-ng-model='disableParametersOffer' data-ng-click='disableParametersSection()'>Zatwierdź sekcję</button>
            <button class="btn btn-success pull-right" data-ng-click='enableParametersSection()'>Edytuj sekcję</button>
        </article>
    </div>
    <h2>Oferty sprzedawców</h2>
    <article class="col-xs-12 offers-retailers">
        <article class="offers-retailers-item" data-ng-repeat='proposalSellerList in content.allReceiverPointsOfferCalculation.proposalSellerList'>
            <nav class="navbar navbar-default">
                <p class="navbar-text">
                    {{$index+1}}. Kalkulacja</p>
                <span class="navbar-brand glyphicon glyphicon-trash" aria-hidden="true" data-ng-click='removeOfferRetailer($index,$parent.$index)'></span>
            </nav>
            <article class="col-xs-12">
                <section class="col-xs-2 no-padding">
                    <label>Nazwa sprzedawcy:</label>
                </section>
                <section class="col-xs-2">
                    <input type="text" data-ng-disabled='disableCalculationOffer' data-ng-model='proposalSellerList.sellerCode' class="form-control">
                </section>
                <section class="col-xs-3 no-padding">
                    <label>Data aktualizacji cennika taryfy:</label>
                </section>
                <section class="col-xs-2">
                    <input type="date" data-ng-disabled='disableCalculationOffer' data-ng-model='proposalSellerList.sellerTariffPublicationDate' class="form-control">
                </section>
            </article>
            <article class="col-xs-12">
                <section class="col-xs-3 no-padding">
                    <label>Oferowana opłata handlowa:</label>
                </section>
                <section class="col-xs-2">
                    <input type="text" data-ng-disabled='disableCalculationOffer' data-ng-model='proposalSellerList.proposalTradeFee' class="form-control">
                </section>
            </article>
            <article class="col-xs-12">
                <table class="table table-bordered ">
                    <thead>
                    <tr>
                        <th>^</th>
                        <th data-ng-repeat='proposalZoneDetailsList in proposalSellerList.proposalZoneDetailsList'>
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
                        <th>Oferowana cena / MWh (some description)</th>
                        <th data-ng-repeat='proposalZoneDetailsList in proposalSellerList.proposalZoneDetailsList'>
                            <input type="number" data-ng-disabled='disableCalculationOffer' class='form-control' data-ng-model='proposalZoneDetailsList.proposalUnitPrice'>
                        </th>
                    </tr>
                    <tr>
                        <th>Nazwa oferowanej strefy (some description)</th>
                        <th data-ng-repeat='proposalZoneDetailsList in proposalSellerList.proposalZoneDetailsList'>
                            <input type="text" data-ng-disabled='disableCalculationOffer' class='form-control' data-ng-model='proposalZoneDetailsList.proposalZoneCode'>
                        </th>
                    </tr>
                    </tbody>
                </table>
            </article>
            <section class="buttons col-xs-12">
                <button class="btn btn-primary pull-right" data-ng-click='sendCalculation()'>Wylicz ofertę</button>
                <button class="btn btn-success pull-right" data-ng-model='disableCalculationOffer' data-ng-click='disableCalculationSection()'>Zatwierdź sekcję</button>
                <button class="btn btn-success pull-right" data-ng-click='enableCalculationSection()'>Edytuj sekcję</button>
            </section>
            <article class="col-xs-12">
                <table class="table table-bordered table-striped col-xs-12 ">
                    <thead>
                    <tr>
                        <th>Na podstawienie cennika z dnia</th>
                        <th>Szacowana masa marży dla wszystkich punktów</th>
                        <th>Szacowana całkowita masa marży w skali roku</th>
                        <th>Szacowana oszczędność w skali roku dla wszystkich punktów</th>
                        <th>Szacowana oszczędność w skali umowy</th>
                        <th>Oszczędność w ujęciu procentowym dla wszystkich punktów</th>
                    </tr>
                    </thead>
                    <tbody>
                    <tr>
                        <th>
                            {{proposalSellerList.receiverPointEstimation.receiverPointDataEstimation.tariffIssueDate}}
                        </th>
                        <th>
                            {{proposalSellerList.receiverPointEstimation.receiverPointDataEstimation.estimatedUnitConsumptionInYearScale}}
                        </th>
                        <th>
                            {{proposalSellerList.receiverPointEstimation.receiverPointDataEstimation.estimatedContractProfitValue}}
                        </th>
                        <th>
                            {{proposalSellerList.receiverPointEstimation.receiverPointDataEstimation.estimatedContractProfitValueInYearScale}}
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
                        <th>Pułap</th>
                        <th>Prowizja roczna</th>
                        <th>
                            Prowizja z umowy</th>
                        <th>Prowizja Partnera z umowy</th>
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
        </article>
        <article class="col-xs-12">
            <section class="col-xs-2 no-padding">
                <label>Dodaj sprzedawcę</label>
            </section>
            <section class="col-xs-2">
                <span class="btn btn-default glyphicon glyphicon-plus-sign" data-ng-click='addCalculationOfferPoints($index)' aria-hidden="true"></span>
            </section>
        </article>
    </article>
</article>

<article class="col-xs-12 no-padding summary-info">
    <nav class="navbar navbar-default">
        <p class="navbar-text">Podsumowanie dla wszystkich punktów odbioru</p>
    </nav>
    <h2>Estymacja dla punktu odbioru
    </h2>
    <article class="summary-info-item " data-ng-repeat='receiverPointEstimationList in content.offerSummaryDTO.receiverPointEstimationList'>
        <h2>{{receiverPointEstimationList.sellerCode}}</h2>
        <section class="col-xs-12">
            <table class="table table-bordered table-striped col-xs-12 ">
                <thead>
                <tr>
                    <th>Na podstawienie cennika z dnia</th>
                    <th>Szacowana masa marży dla całej umowy</th>
                    <th>Szacowana masa marży w skali roku</th>
                    <th>Szacowana oszczędność w skali roku</th>
                    <th>Szacowana oszczędność w skali umowy</th>
                    <th>Oszczędność w ujęciu procentowym</th>
                </tr>
                </thead>
                <tbody>
                <tr>
                    <th>
                        {{receiverPointEstimationList.allReceiverPointsDataEstimationForSeller.tariffIssueDate}}
                    </th>
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
        <h2>Prowizje z punktu odbioru</h2>
        <article class="col-xs-12">
            <table class="table table-bordered table-striped col-xs-12 ">
                <thead>
                <tr>
                    <th>Pułap</th>
                    <th>Prowizja Handlowca roczna</th>
                    <th>
                        Prowizja Handlowa z umowy</th>
                    <th>Prowizja Partnera z umowyy</th>
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
</article>
<article class='comment col-xs-12 no-padding'>
    <p>Komentarz do oferty</p>
    <section class="col-xs-12">
        <textarea name="comment" cols="30" data-ng-model='content.offerNote' class='form-control'></textarea>
    </section>
</article>
<article class="col-xs-12 no-padding">
    <button class="btn btn-success pull-right" data-ng-click='sendData()'>Zapisz ofertę</button>
</article>

<script src="/resources/energyOfferCalc_V_1/js/lib/angular.min.js"></script>
<script src="/resources/energyOfferCalc_V_1/js/lib/angular-animate.min.js"></script>
<script src="/resources/energyOfferCalc_V_1/js/services/angular-modal-service.js"></script>

<script src="/resources/energyOfferCalc_V_1/js/app.js"></script>
<script src='/resources/energyOfferCalc_V_1/js/controllers/yesnocontroller.js'></script>

</body>

</html>
