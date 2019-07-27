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
		
		$scope.isLord = false;
		var s = "";
		
		$http({url: "/dominiongame/islord", method: "POST"}).then(function(response){
			s = response.data.value[0];
			if (s == "Lord") $scope.isLord = true;
		});
	
		$http({url: "/dominiongame/playernames", method: "POST"}).then(function(response){
			$scope.playernames = response.data.value;
			$scope.playernames[0] = $scope.playernames[0] + "(lord)";
		});
		
		$scope.addBot = function() {
			$http({url: "/dominiongame/addbot", method: "POST"}).then(function(response){
				$http({url: "/dominiongame/playernames", method: "POST"}).then(function(response){
					$scope.playernames = response.data.value;
					$scope.playernames[0] = $scope.playernames[0] + "(lord)";
				});
			});
		}
		
		$scope.leave = function() {
			$http({url: "/dominiongame/leaveboard", method: "POST"}).then(function(response){
				$scope.goto("dominion");
			});
		}
		
		$scope.numPlayerOptions = [2,3,4];
		
		$scope.showKick = function(index){
			if (index == 0) return false;
			return $scope.isLord;
		}
		
		$scope.ready = function() {
			if ($scope.playernames.length == $scope.numPlayers){
				$http({url: "/dominiongame/setup", method: "POST"}).then(function(response){
					$scope.goto("dominiongame");
				});
			} else {
				alert("No enough players!");
			}
		}
		
		$scope.kick = function(name){
			var data = {"kickedName": name};
			$http({url: "/dominiongame/kick", method: "POST", params: data}).then(function(response){
				$http({url: "/dominiongame/playernames", method: "POST"}).then(function(response){
					$scope.playernames = response.data.value;
					$scope.playernames[0] = $scope.playernames[0] + "(lord)";
				});
			});
		}
		
}]);
