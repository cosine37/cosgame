var setUrl = function(d){
	hearder = "http://";
	server = "localhost:8080";
	return header + server + d;
}

var app = angular.module("architectGameApp", []);
app.controller("architectGameCtrl", ['$scope', '$window', '$http', '$document',
	function($scope, $window, $http, $document){
	
		$scope.goto = function(d){
			var x = "http://" + $window.location.host;
			$window.location.href = x + "/" + d;
		}
		
		$http.post('/username').then(function(response){
			$scope.username = response.data.value[0];
		});
		
		$scope.logout = function(){
			$http({url: "/logout", method: "POST"}).then(function(response){
				$scope.goto('login');
			});
		}
		
		$scope.play = function(x){
			var data = {"cardIndex" : x}
			$http({url: "/architect/play", method: "POST", params: data}).then(function(response){
				//$scope.allRefresh()
				$scope.getBoard()
				//$scope.hideAreaCard();
			});
		}
		
		$scope.getBoard = function(){
			$http.get('/architect/getboard').then(function(response){
				$scope.gamedata = response.data
				$scope.myIndex = parseInt(response.data.myIndex)
				$scope.hand = response.data.players[$scope.myIndex].hand
				
			});
		}
		
		$scope.getBoard();
		
}]);
