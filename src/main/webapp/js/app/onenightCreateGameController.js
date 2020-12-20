var setUrl = function(d){
	hearder = "http://";
	server = "localhost:8080";
	return header + server + d;
}

var app = angular.module("onenightCreateGameApp", []);
app.controller("onenightCreateGameCtrl", ['$scope', '$window', '$http', '$document', '$timeout',
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
		
		$scope.start = function(){
			$http.post('/onenightgame/startgame').then(function(response){
				$scope.goto('onenightgame');
			});
		}
		
		$scope.addBot = function(){
			$http.post('/onenightgame/addbot').then(function(response){
				$scope.getBoard();
			});
		}
		
		$scope.getBoard = function(){
			$http.get('/onenightgame/getboard').then(function(response){
				$scope.gamedata = response.data
				$scope.status = response.data.status
				$scope.playerNames = response.data.playerNames
				if ($scope.status != "0"){
					$scope.goto('onenightgame')
				}
			});
		}
		
		$scope.offturnHandle = function(){
			$scope.getBoard();
			$timeout(function(){
			    $scope.offturnHandle();
			},2500);
		}
		
		$scope.offturnHandle();
}]);
