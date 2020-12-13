var setUrl = function(d){
	hearder = "http://";
	server = "localhost:8080";
	return header + server + d;
}

var app = angular.module("onenightGameApp", []);
app.controller("onenightGameCtrl", ['$scope', '$window', '$http', '$document',
	function($scope, $window, $http, $document){
		$scope.rolesSelect = [];
	
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
		
		$scope.changeRolesSelect = function(x,y){
			$scope.rolesSelect[x] = y;
		}
		
		$scope.submitRolesSelect = function(){
			alert($scope.rolesSelect)
			var data = {"roles" : $scope.rolesSelect}
			$http({url: "/onenightgame/setroles", method: "POST", params: data}).then(function(response){
				$scope.getBoard()
			});
		}
		
		$scope.night = function(){
			$http.post('/onenightgame/night').then(function(response){
				$scope.getBoard()
			});
		}
		
		$scope.getBoard = function(){
			$http.get('/onenightgame/getboard').then(function(response){
				$scope.gamedata = response.data
				$scope.playerNames = response.data.playerNames;
				$scope.lord = response.data.lord;
				$scope.status = response.data.status
				$scope.canNight = response.data.canNight;
				$scope.rolesThisGame = response.data.rolesThisGame
				if ($scope.canNight == "n"){
					$scope.rolesSelect = [];
					for (i=0;i<$scope.playerNames.length+3;i++){
						$scope.rolesSelect.push(-1);
					}
				}
				
			});
		}
		
		$scope.getBoard();
}]);
