var setUrl = function(d){
	hearder = "http://";
	server = "localhost:8080";
	return header + server + d;
}

var app = angular.module("dominionBoardApp", []);
app.controller("dominionBoardCtrl", ['$scope', '$window', '$http', '$document',
	function($scope, $window, $http, $document){
		$scope.goto = function(d){
			var x = "http://localhost:8080";
			$window.location.href = x + "/" + d;
		}
	
		$http({url: "/dominiongame/playernames", method: "POST"}).then(function(response){
			$scope.playernames = response.data.value;
		});
		
		$scope.addBot = function() {
			$http({url: "/dominiongame/addbot", method: "POST"}).then(function(response){
				$http({url: "/dominiongame/playernames", method: "POST"}).then(function(response){
					$scope.playernames = response.data.value;
				});
			});
		}
		
		$scope.leave = function() {
			$http({url: "/dominiongame/leaveboard", method: "POST"}).then(function(response){
				$scope.goto("dominion");
			});
		}
		
		$scope.numPlayerOptions = [2,3,4];
		
		$scope.ready = function() {
			if ($scope.playernames.length == $scope.numPlayers){
				$http({url: "/dominiongame/setup", method: "POST"}).then(function(response){
					$scope.goto("dominiongame");
				});
			} else {
				alert("No enough players!");
			}
		}
		
}]);
