/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

var app = angular.module('eventApp', ['ngMaterial']);



app.controller('AppCtrl', function() {


});

app.controller('AnnulationCtrl', function($scope, $http) {
	$scope.deleteReservation = function () {
		var url = "http://localhost:16569/Events-war/webresources/annulation";
		$http.post(url, $scope.ref)
		.then(function mySuccess(response) {
			$scope.return = response.data;
		}, function myError(response) {
			$scope.return = response.statutText;
		});
	};
});


app.controller('ReservationCtrl', function($scope, $http) {

	$scope.addReservation = function () {
		var url = "http://localhost:16569/Events-war/webresources/reservation";
		$http.post(url, $scope.reservation)
		.then(function mySuccess(response) {
			$scope.return = response.data;
		}, function myError(response) {
			$scope.return = response.statutText;
		});
	};
    
});