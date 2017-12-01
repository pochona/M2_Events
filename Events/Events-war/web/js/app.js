/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

var app = angular.module('eventApp', ['ngMaterial']);

var port = "29201";
var project = "Events-war";
var baseUrl = "http://localhost:"+port+"/"+project;

app.controller('AppCtrl', function() {


});

app.controller('AnnulationCtrl', function($scope, $http, $window) {
    $scope.deleteReservation = function () {
        var url = baseUrl+"/webresources/annulation";
        $http.post(url, $scope.ref)
        .then(function mySuccess(response) {
                var reponse = response.data;
                $window.location.href = baseUrl+'/confirmation_delete.html?message='+reponse;
                $scope.return = response.data;
        }, function myError(response) {
                $scope.return = response.statutText;
        });
    };
        
    $scope.initResponse = function () {
        var mess = findGetParameter("message");
        console.log("ok")
        $scope.mess=mess;        
    };
});



app.controller('ReservationCtrl', function($scope, $http, $window) {


    $scope.addReservation = function () {
        var url = baseUrl+"/webresources/reservation";
        $http.post(url, $scope.reservation)
        .then(function mySuccess(response) {
                var reponse = response.data;
                $window.location.href = baseUrl+'/confirmation.html?ref='+reponse;
                $scope.return = response.data;
        }, function myError(response) {
                $scope.return = response.statutText;
        });
    };
    

    $scope.initResponse = function () {
        var ref = findGetParameter("ref");
        $scope.ref=ref;        
    };
});

app.controller('ListeCtrl', function($scope, $http, $window) {

	$scope.addReservation = function () {
        var url = baseUrl+"/webresources/reservation";
        $http.get(url, $scope.reservation).then(function mySuccess(response) {
                var reponse = response.data;
                $window.location.href = baseUrl+'/confirmation_verif.html?ref='+reponse;
                $scope.return = response.data;
        }, function myError(response) {
                $scope.return = response.statutText;
        });
    };
    

    $scope.initResponse = function () {
        var ref = findGetParameter("ref");
        $scope.ref=ref;        
    };

    
});

function findGetParameter(parameterName) {
        var result = null,
        tmp = [];
        location.search
        .substr(1)
        .split("&")
        .forEach(function (item) {
          tmp = item.split("=");
          if (tmp[0] === parameterName) result = decodeURIComponent(tmp[1]);
        });
        return result;
};