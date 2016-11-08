angular.module('myApp', ['angularModalService', 'ngAnimate'])
    .controller('myCtrl', ["$scope", "$http", "ModalService", function($scope, $http, ModalService) {

        /* Resources */
        //Boghan
       /* var getSellersPath = "../data/sellers.json";
        var getProductCodesPath = '../data/productCodes.json'
        var getProvisionVariantsPath = "/a/provision/provisionVariants";
        var sendDataPath="/a/provision/userProvision";
        var yesNoTemplatePath = "../templates/yesno.html"*/

        var userName = 'paxxa';


        /*Get resources*/
        var yesNoTemplatePath = pageContext + '/resources/a/electricityCalculator/templates/yesno.jsp';

        var getSellersPath = pageContext + "/a/provision/sellers.json";
        var getProductCodesPath = pageContext + "/a/provision/productCodes.json";
        /*POST*/
        var getProvisionVariantsPath = pageContext + "/a/provision/provisionVariants.json";
        var sendDataPath = pageContext + "/a/provision/userProvision.json"


        $scope.userName = userName;
        //Get Seller List
        $http({
            method: 'GET',
            url: getSellersPath,
            headers: {
                'Content-Type': 'application/json;charset=utf-8'
            }
        }).then(function(response) {
            $scope.sellers = response.data;

        });
        //Get Product Codes
        $http({
            method: 'GET',
            url: getProductCodesPath,
            headers: {
                'Content-Type': 'application/json;charset=utf-8'
            }
        }).then(function(response) {
            $scope.productCodes = response.data;
            $scope.productCodeReq = $scope.productCodes[0];

        });


        $scope.setSeller = function(x, index) {

            $scope.sellerCodeClass = [];
            $scope.sellerCodeReq = x;


            $scope.sellerCodeClass[index] = 'red';
            getProvisionVariatns();
        };

        $scope.setProductCode = function() {
            if ($scope.sellerCodeReq) {
                getProvisionVariatns();
            }

        };



        var getProvisionVariatns = function() {
            var data = {
                "userName": $scope.userName,
                "productCode": $scope.productCodeReq,
                "sellerCode": $scope.sellerCodeReq


            };

            //POST and GET ProvisionVariants

            $http({
                method: 'POST',
                url: getProvisionVariantsPath,
                data: data,
                headers: {
                    'Content-Type': 'application/json;charset=utf-8'
                }
            }).then(function(response) {
                $scope.provisionVariants=response.data;


            }).then(function(response) {

            });
        }

        $scope.saveData=function(){
            $http({
                method: 'POST',
                url: sendDataPath,
                data: $scope.provisionVariants,
                headers: {
                    'Content-Type': 'application/json;charset=utf-8'
                }
            }).then(function(response) {
                $scope.provisionVariants=response.data;


            }).then(function(response) {

            });

        }
        $scope.nubmerValidate = function(index) {
            $scope.$watch('provisionVariants.provisionVariantDTOList[' + index + '].provisionPercentageValue', function(newValue, oldValue) {
                var arr = String(newValue).split("");
                $scope.provisionVariants.provisionVariantDTOList[index].provisionPercentageValue = parseFloat($scope.provisionVariants.provisionVariantDTOList[index].provisionPercentageValue);

                if (arr.length === 0) return;
                if (arr.length === 1 && (arr[0] == '-' || arr[0] === '.')) return parseFloat(newValue);
                if (arr.length === 2 && newValue === '-.') return parseFloat(newValue);
                if (isNaN(newValue)) {
                    $scope.provisionVariants.provisionVariantDTOList[index].provisionPercentageValue = parseFloat(oldValue);
                }

            });

        }
        $scope.addProvisionVariant = function() {
            $scope.provisionVariants.provisionVariantDTOList.push({
                "provisionLevelDescription": "Przykładowy próg",
                "provisionPercentageValue": 0
            });
        }
        $scope.removeProvisionVariant = function(index) {

            ModalService.showModal({
                templateUrl: yesNoTemplatePath,
                controller: "YesNoController"
            }).then(function(modal) {
                modal.element.modal();
                modal.close.then(function(result) {
                    if (result == true) {
                        $scope.provisionVariants.provisionVariantDTOList.splice(index, 1);
                    }
                });
            });

        }

    }]);