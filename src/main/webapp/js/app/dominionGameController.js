var setUrl = function(d){
	hearder = "http://";
	server = "localhost:8080";
	return header + server + d;
}

var app = angular.module("dominionGameApp", []);
app.controller("dominionGameCtrl", ['$scope', '$window', '$http', '$document',
	function($scope, $window, $http, $document){
		$scope.base=[];
		$scope.kindom=[];
		
		$scope.baseStyle={
			"height": "109px",
			"width": "71px"
		};
		
		$scope.kindomStyle={
			"height": "210px",
			"width": "140px"
		};
		
		$scope.playStyle={
			"height": "109px",
			"width": "71px"
		}
		
		$scope.goto = function(d){
			var x = "http://localhost:8080";
			$window.location.href = x + "/" + d;
		}
		
		$http.post('/dominiongame/getbase').then(function(response){
			$scope.base=response.data;
		});
		
		$http.post('/dominiongame/getkindom').then(function(response){
			$scope.kindom=response.data;
		});
		
		
}]);
