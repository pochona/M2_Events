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

app.controller('AnnulationCtrl', function($scope, $http) {
	$scope.deleteReservation = function () {
		var url = baseUrl+"/webresources/annulation";
		$http.post(url, $scope.ref)
		.then(function mySuccess(response) {
			$scope.return = response.data;
		}, function myError(response) {
			$scope.return = response.statutText;
		});
	};
});


app.controller('ReservationCtrl', function($scope, $http, $window) {


	$scope.addReservation = function () {
		var url = baseUrl+"/webresources/reservation";
		$http.post(url, $scope.reservation)
		.then(function mySuccess(response) {
			var reponse = response.data;
			console.log(reponse)
			$window.location.href = baseUrl+'/confirmation.html';
			$scope.return = response.data;
		}, function myError(response) {
			$scope.return = response.statutText;
		});
	};
    

    $scope.initResponse = function () {
    	$scope.reponse = reponse;   
    	console.log("ok")
    	console.log($scope.reponse)
	};
});