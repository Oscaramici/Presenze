﻿//var myApp = angular.module('INAApp', ['LoginService']);


app.controller('LoginController', function ($scope, $mdDialog, LoginService) {
    $scope.status = '  ';
    $scope.customFullscreen = false;
    $("#ConfirmDialog").hide();
    $scope.LoginCheck = function ($event) {
        var User = {
            UserName: $scope.uName,
            Password: $scope.password,
            Department: $scope.department
        };
        $("#divLoading").show();
        $scope.event = $event;
        var getData = LoginService.UserLogin(User);
        getData.then(function (msg) {
            if (msg.data.success == "false") {
                $("#divLoading").hide();
              //  $("#ConfirmDialog").modal('show');
                $scope.msg = "Errore nell'autenticazione contattare servizio tecnico";
                $scope.showConfirm($scope.msg,$scope.event);
            }
            else if (msg.data.success == "true" && msg.data.Error != null) {
                $("#divLoading").hide();
               // $("#ConfirmDialog").modal('show');
                $scope.msg = msg.data.Error;
                $scope.showConfirm($scope.msg,$scope.event);
            }
            else if (msg.data.success == "true" && (msg.data.ResponseUrl != "" && msg.data.ResponseUrl != null)) {
                 uID = msg.data;
                $("#divLoading").hide();
                window.location.href = "/INASAIAMVC/" + msg.data.ResponseUrl;
            }
        });     
    }

    $scope.showConfirm = function (msg,event) {
        // Appending dialog to document.body to cover sidenav in docs app
        var confirm = $mdDialog.confirm()
              .title('Attenzione autenticazione non effettuata')
              .textContent(msg)
              .ariaLabel('Lucky day')
              .targetEvent(event)
              .ok('Ok')
              .cancel('Cancella');

        $mdDialog.show(confirm).then(function () {
            $scope.status = msg;
        }, function () {
            $scope.status = msg;
        });
    };


    function clearFields() {
        $scope.uName = '';
        $scope.password = '';
        $scope.department = '';
    }
   
 

});



