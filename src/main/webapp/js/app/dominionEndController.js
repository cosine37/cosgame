var setUrl = function(d){
	hearder = "http://";
	server = "localhost:8080";
	return header + server + d;
}

var app = angular.module("dominionEndApp", []);
app.controller("dominionEndCtrl", ['$scope', '$window', '$http', '$document',
	function($scope, $window, $http, $document){
		$scope.goto = function(d){
			var x = "http://localhost:8080";
			$window.location.href = x + "/" + d;
		}
		
		$scope.endMsg = "You Resigned";
		
		
}]);
