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
		$scope.showBigImage = false;
		var s = "";
		
		$scope.kindom = [];
		
		$http({url: "/dominiongame/islord", method: "POST"}).then(function(response){
			s = response.data.value[0];
			if (s == "Lord") $scope.isLord = true;
		});
	
		$http({url: "/dominiongame/playernames", method: "POST"}).then(function(response){
			$scope.playernames = response.data.value;
			$scope.playernames[0] = $scope.playernames[0] + "(lord)";
		});
		
		$http.post('/dominiongame/getkindom').then(function(response){
			$scope.kindom=response.data;
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
		
		
		$scope.randomize = function(){
			$http({url: "/dominiongame/randomize", method: "POST"}).then(function(response){
				$http.post('/dominiongame/getkindom').then(function(response){
					$scope.kindom=response.data;
				});
			});
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
		
		$scope.showCard = function(image){
			$scope.bigImage = image;
			$scope.showBigImage = true;
			$scope.bigImageStyle = {
				"height": "420px", 
				"width": "280px", 
				"position": "absolute",
				"left": "50%",
				"top": "50%",
				"margin-left": "-140px",
				"margin-top": "-210px",
				"background": "url(" + image + ")", 
				"background-size": "cover"
			}
			$scope.bigImageDivStyle = {
				"position": "absolute",
				"left": "0%",
				"top": "0%",
				"height": "100%",
				"width": "100%",
				"background": "rgba(150, 150, 150, 0.5)"
			}
		}
		
		$scope.unshowBigImage = function(){
			$scope.showBigImage = false;
		}
		
}]);
