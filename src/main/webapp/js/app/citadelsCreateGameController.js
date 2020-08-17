var setUrl = function(d){
	hearder = "http://";
	server = "localhost:8080";
	return header + server + d;
}

var app = angular.module("citadelsCreateGameApp", []);
app.controller("citadelsCreateGameCtrl", ['$scope', '$window', '$http', '$document',
	function($scope, $window, $http, $document){
	
		$scope.goto = function(d){
			var x = "http://" + $window.location.host;
			$window.location.href = x + "/" + d;
		}
		
		$http.post('/username').then(function(response){
			$scope.username = response.data.value[0];
		});
		
		$scope.getBoard = function(){
			$http.get('/citadelsgame/getboard').then(function(response){
				$scope.gamedata = JSON.stringify(response.data)
				$scope.playerNames = response.data.playerNames
				$scope.crown = parseInt(response.data.crown)
			});
		}
		
		$scope.giveCrown = function(x){
			var data = {"crownIndex" : x}
			$http({url: "/citadelsgame/givecrown", method: "POST", params: data}).then(function(response){
				$scope.getBoard();
			});
		}
		
		$scope.startGame = function(){
			$http.post('citadelsgame/start').then(function(response){
				$scope.goto('citadelsgame');
			})
		}
		
		$scope.addBot = function(){
			$http.post('citadelsgame/addbot').then(function(response){
				$scope.getBoard();
			})
		}
		
		$scope.logout = function(){
			$http({url: "/logout", method: "POST"}).then(function(response){
				$scope.goto('login');
			});
		}
		
		$scope.getBoard();
		
}]);