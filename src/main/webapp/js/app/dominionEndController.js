var setUrl = function(d){
	hearder = "http://";
	server = "localhost:8080";
	return header + server + d;
}

var app = angular.module("dominionEndApp", []);
app.controller("dominionEndCtrl", ['$scope', '$window', '$http', '$document',
	function($scope, $window, $http, $document){
		$scope.goto = function(d){
			var x = "http://" + $window.location.host;
			$window.location.href = x + "/" + d;
		}
		
		$scope.endMsg = "";
		$scope.sc1 = "";
		$scope.sc2 = "";
		
		$http.post('/dominiongame/endgamemsg').then(function(response){
			$scope.endMsg = response.data.value[0];
			$scope.sc1 = response.data.value[1];
			$scope.sc2 = response.data.value[2];
		});
		
}]);
