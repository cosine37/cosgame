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
		
		$http.post('/dominiongame/endgamemsg').then(function(response){
			$scope.endgame = response.data;
			$http({url: "/dominiongame/randomize", method: "POST"}).then(function(response){
				
			});
		});
		
		
		
}]);
