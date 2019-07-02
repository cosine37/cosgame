var setUrl = function(d){
	hearder = "http://";
	server = "localhost:8080";
	return header + server + d;
}

var app = angular.module("mafiaGameApp", []);
/*
app.config(['$locationProvider', function($locationProvider){
	$locationProvider.html5Mode(true);
}])
*/
app.controller("mafiaGameCtrl", ['$scope', '$window', '$http', '$document',
	function($scope, $window, $http, $document){
	
		$scope.goto = function(d){
			var x = "http://localhost:8080";
			$window.location.href = x + "/" + d;
		}
		
		$scope.roomId = $window.location.search.substr(8);
		
		$scope.playerNames = [];
		
		var data = {"roomId": $scope.roomId}
		$http({url: "/mafiagame/players", method: "POST", params: data}).then(function(response){
			$scope.players = response.data.value;
			var s = 4;
			var n = $scope.players.length / s;
			var i;
			for (i=0;i<n;i++){
				//alert($scope.players[i*s]);
				$scope.playerNames.push($scope.players[i*s]);
			}
		});
		
		
		
}]);