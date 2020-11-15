var setUrl = function(d){
	hearder = "http://";
	server = "localhost:8080";
	return header + server + d;
}

var app = angular.module("pokewhatCreateGameApp", []);
app.controller("pokewhatCreateGameCtrl", ['$scope', '$window', '$http', '$document', '$timeout',
	function($scope, $window, $http, $document, $timeout){
	
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
		
		$scope.addBot = function(){
			$http.post('/pokewhatgame/addbot').then(function(response){
				$scope.getBoard();
			});
		}
		
		$scope.startGame = function(){
			$http.post('/pokewhatgame/startgame').then(function(response){
				$scope.goto('pokewhatgame');
			});
		}
		
		$scope.getBoard = function(){
			$http.get('/pokewhatgame/getboard').then(function(response){
				$scope.id = response.data.id;
				if ($scope.id == "NE"){
					$scope.goto('/pokewhat');
					return;
				}
				$scope.gamedata = response.data;
				$scope.lord = response.data.lord;
				$scope.status = response.data.status;
				if ($scope.status != "0"){
					$scope.goto('pokewhatgame');
				}
			});
		}
		
		$scope.offturnHandle = function(){
			$scope.getBoard();
			$timeout(function(){
			    $scope.offturnHandle();
			},5000);
		}
		
		$scope.offturnHandle();
		
}]);
