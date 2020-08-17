var setUrl = function(d){
	hearder = "http://";
	server = "localhost:8080";
	return header + server + d;
}

var app = angular.module("citadelsMainApp", []);
app.controller("citadelsMainCtrl", ['$scope', '$window', '$http', '$document',
	function($scope, $window, $http, $document){
	
		$scope.boards = []
	
		$scope.goto = function(d){
			var x = "http://" + $window.location.host;
			$window.location.href = x + "/" + d;
		}
		
		$http.post('/username').then(function(response){
			$scope.username = response.data.value[0];
		});
		
		$http.get('/citadels/allboards').then(function(response){
			$scope.boards = response.data.value
		});
		
		$scope.newGame = function(){
			$http.post("/citadelsgame/newboard").then(function(response){
				$scope.goto('citadelscreategame');
			});
		}
		
		$scope.goToBoard = function(index){
			var data = {"boardId" : $scope.boards[index]}
			$http({url: "/citadelsgame/setboardid", method: "POST", params: data}).then(function(response){
				$scope.goto('citadelsgame')
			});
		}
		
		$scope.logout = function(){
			$http({url: "/logout", method: "POST"}).then(function(response){
				$scope.goto('login');
			});
		}
		
}]);
