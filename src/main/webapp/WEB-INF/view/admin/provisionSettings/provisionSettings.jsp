<!DOCTYPE html>
<html lang="pl" data-ng-app="myApp" data-ng-controller="myCtrl">
<!--Boghan-->

<%--<head>
    <link rel="stylesheet" href="css/font-awesome.css">
    <script src="js/lib/jquery-3.1.0.min.js"></script>
    <script src="js/lib/bootstrap.min.js"></script>
    <link rel="stylesheet" href="css/bootstrap.min.css">
    <link rel="stylesheet" href="css/style.css">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta charset="UTF-8">
    <title>Konfiguracja wariantow prowizji</title>
</head>--%>

<head>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/a/provisionSettings/css/font-awesome.css">
    <script src="${pageContext.request.contextPath}/resources/a/provisionSettings/js/lib/jquery-3.1.0.min.js"></script>
    <script src="${pageContext.request.contextPath}/resources/a/provisionSettings/js/lib/bootstrap.min.js"></script>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/a/provisionSettings/css/bootstrap.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/a/provisionSettings/css/style.css">
    <meta name="viewport" content="width=device-width, initial-scale=5">
    <meta charset="UTF-8">
    <title>Oferta</title>
</head>

<body>
<main>
    <div class="container">
        <div class="conf-block">
            <div class="row">
                <div class="col-xs-12">
                    <div class="header clearfix">
                        <div class="col-xs-4">
                            <h1>Konfiuracja wariantow prowizji</h1>
                        </div>
                        <div class="col-xs-5">
                            <span>{{userName}}</span>
                        </div>
                        <div class="col-xs-2 pull-right">
                            <select data-ng-model='productCodeReq' class='form-control' ng-change="setProductCode()">
                                <option ng-repeat="productCode in productCodes">{{productCode}}</option>
                            </select>
                        </div>
                    </div>
                </div>
            </div>
            <div class="my-row">
                <div class="my-cell sel">
                    <div class="col-xs-12">
                        <ul>
                            <li class='title'>Sprzedawcy</li>
                            <li class='gray' data-ng-class='sellerCodeClass[$index]' data-ng-repeat='seller in sellers' ng-click='setSeller(seller.sellerCode,$index)'>
                                {{seller.sellerCode}}
                            </li>
                        </ul>
                    </div>
                </div>
                <div class="my-cell pro">
                    <div ng-show="provisionVariants!=null">
                        <div class="col-xs-3">
                            <h3>
                                {{provisionVariants.sellerCode}} </h3>
                        </div>
                        <div class="col-xs-9">
                            <h3>{{provisionVariants.productCode}}</h3>
                        </div>
                        <div class="col-xs-12">
                            <table class="table table-bordered">
                                <thead>
                                <tr>
                                    <th>Lp.</th>
                                    <th>Procent</th>
                                    <th>Opis wariantu</th>
                                    <th>akcja</th>
                                </tr>
                                </thead>
                                <tbody>
                                <tr data-ng-repeat='provisionVariantDTOList in provisionVariants.provisionVariantDTOList'>
                                    <td>{{$index+1}}</td>
                                    <td>
                                        <input ng-change='nubmerValidate($index)' ng-model='provisionVariantDTOList.provisionPercentageValue'>
                                    </td>
                                    <td>
                                        <input type="text" ng-model='provisionVariantDTOList.provisionLevelDescription'>
                                    </td>
                                    <td><span class=" glyphicon glyphicon-remove" aria-hidden="true" data-ng-click='removeProvisionVariant($index)'></td>
                                </tr>
                                </tbody>
                            </table>
                        </div>
                        <div class="col-xs-12 plus-block">
                            <span class="pull-right glyphicon glyphicon-plus-sign" aria-hidden="true"   data-ng-click='addProvisionVariant()'>
                        </div>
                        <div class="col-xs-12">
                            <button class='btn btn-default pull-right' ng-click='saveData()'>Zapisz</button>
                        </div>
                    </div>

                </div>


            </div>

        </div>
    </div>
</main>
</body>

<%--<script src="js/lib/angular.min.js"></script>
<script src="js/lib/angular-animate.min.js"></script>
<script src='js/services/angular-modal-service.js'></script>
<script src="js/app.js"></script>
<script src='js/controllers/yesnocontroller.js'></script>
<script></script>--%>

</body>

</html>

<script src="${pageContext.request.contextPath}/resources/a/provisionSettings/js/lib/angular.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/a/provisionSettings/js/lib/angular-animate.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/a/provisionSettings/js/services/angular-modal-service.js"></script>
<script src="${pageContext.request.contextPath}/resources/a/provisionSettings/js/app.js"></script>
<script src="${pageContext.request.contextPath}/resources/a/provisionSettings/js/controllers/yesnocontroller.js"></script>


<script>
var pageContext = "${pageContext.request.contextPath}";
var requestSourceType = "${requestSourceType}";
var offerIdForEdition = "${offerIdForEdition}";
</script>