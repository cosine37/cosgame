var setUrl = function(d){
	hearder = "http://";
	server = "localhost:8080";
	return header + server + d;
}

var app = angular.module("dominionMainApp", []);
app.controller("dominionMainCtrl", ['$scope', '$window', '$http', '$document',
	function($scope, $window, $http, $document){
	
		$scope.goto = function(d){
			var x = "http://localhost:8080";
			$window.location.href = x + "/" + d;
		}
		
		$scope.newGame = function(){
			$http.post("/dominiongame/newgame").then(function(response){
				$scope.goto('dominiongame');
			})
		}
		
		$scope.enterBoard = function(x){
			$scope.goto("dominiongame?BoardId="+x);
		}
		
		$http.post("/dominionboards").then(function(response){
			$scope.boardIds = response.data.value;
		});
}]);
