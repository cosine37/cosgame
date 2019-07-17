var setUrl = function(d){
	hearder = "http://";
	server = "localhost:8080";
	return header + server + d;
}

var app = angular.module("dominionBoardApp", []);
app.controller("dominionBoardCtrl", ['$scope', '$window', '$http', '$document',
	function($scope, $window, $http, $document){
	
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
		
}]);
