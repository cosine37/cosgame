var setUrl = function(d){
	hearder = "http://";
	server = "localhost:8080";
	return header + server + d;
}

var app = angular.module("dominionListApp", []);
app.controller("dominionListCtrl", ['$scope', '$window', '$http', '$document',
	function($scope, $window, $http, $document){
	
		//TODO: customize this
		$scope.numplayers = 2;
	
		$scope.goto = function(d){
			var x = "http://localhost:8080";
			$window.location.href = x + "/" + d;
		}
		
		$scope.logout = function(){
			$http({url: "/logout", method: "POST"}).then(function(response){
				$scope.goto('login');
			});
		}
		
		$scope.newGame = function(){
			$http.post("/dominiongame/newgame").then(function(response){
				$scope.goto('dominiongame');
			})
		}
		
		$scope.newBoard = function(){
			var data = {"numPlayers": $scope.numplayers};
			$http({url: "/dominiongame/newboard", method: "POST", params: data}).then(function(response){
				$scope.goto("dominionboard");
			});
		}
		
		$scope.enterBoard = function(x){
			$scope.goto("dominiongame?BoardId="+x);
		}
		
		$http.post("/dominionboards").then(function(response){
			$scope.boardIds = response.data.value;
		});
}]);
