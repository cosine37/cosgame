var setUrl = function(d){
	hearder = "http://";
	server = "localhost:8080";
	return header + server + d;
}

var app = angular.module("dominionListApp", []);
app.controller("dominionListCtrl", ['$scope', '$window', '$http', '$document',
	function($scope, $window, $http, $document){
	
		$scope.goto = function(d){
			var x = "http://" + $window.location.host;
			$window.location.href = x + "/" + d;
		}
		
		$scope.logout = function(){
			$http({url: "/logout", method: "POST"}).then(function(response){
				$scope.goto('login');
			});
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
		
		
}]);
