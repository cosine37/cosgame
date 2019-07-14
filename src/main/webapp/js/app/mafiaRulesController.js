var setUrl = function(d){
	hearder = "http://";
	server = "localhost:8080";
	return header + server + d;
}

var app = angular.module("mafiaRulesApp", []);
app.controller("mafiaRulesCtrl", ['$scope', '$window', '$http', '$document',
	function($scope, $window, $http, $document){
	
		$scope.goto = function(d){
			var x = "http://localhost:8080";
			$window.location.href = x + "/" + d;
		}
		
		$scope.logout = function(){
			$http({url: "/logout", method: "POST"}).then(function(response){
				$scope.goto('login');
			});
		}
		
		$scope.newGame = function(){
			$http.post("/mafiagame/newgame").then(function(response){
				$scope.goto('mafiagame');
			})
		}
		
		$scope.rooms = function(){
			$scope.goto('mafia');
		}
		
		
}]);
