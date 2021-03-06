var setUrl = function(d){
	hearder = "http://";
	server = "localhost:8080";
	return header + server + d;
}

var app = angular.module("mafiaMainApp", []);
app.controller("mafiaMainCtrl", ['$scope', '$window', '$http', '$document',
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
		
		$scope.newGame = function(){
			$http.post("/mafiagame/newgame").then(function(response){
				$scope.goto('mafiagame');
			})
		}
		
		$scope.rules = function(){
			$scope.goto('mafiarules');
		}
		
		$scope.enterRoom = function(x){
			$scope.goto("mafiagame?RoomId="+x);
		}
		
		$http.post("/mafiarooms").then(function(response){
			$scope.roomIds = response.data.value;
		});
}]);
