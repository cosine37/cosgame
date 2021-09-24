var setUrl = function(d){
	hearder = "http://";
	server = "localhost:8080";
	return header + server + d;
}

var app = angular.module("architectGameApp", []);
app.controller("architectGameCtrl", ['$scope', '$window', '$http', '$document',
	function($scope, $window, $http, $document){
	
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
		
		$scope.play = function(x){
			var data = {"cardIndex" : x}
			$http({url: "/architect/play", method: "POST", params: data}).then(function(response){
				//$scope.allRefresh()
				$scope.getBoard()
				//$scope.hideAreaCard();
			});
		}
		
		$scope.rest = function(){
			$http({url: "/architect/rest", method: "POST"}).then(function(response){
				//$scope.allRefresh()
				$scope.getBoard()
				//$scope.hideAreaCard();
			});
		}
		
		$scope.hire = function(x){
			var resArr = [-1]
			var data = {"index" : x, "res": resArr}
			$http({url: "/architect/hire", method: "POST", params: data}).then(function(response){
				//$scope.allRefresh()
				$scope.getBoard()
				//$scope.hideAreaCard();
			});
		}
		
		setResources = function(){
			var resNames = ["wood", "stone", "iron", "gold"];
			for (i=0;i<$scope.players.length;i++){
				var warehouseArr = []
				var warehouseStyles = []
				for (j=0;j<$scope.players[i].warehouse.length;j++){
					var x = parseInt($scope.players[i].warehouse[j])
					for (k=0;k<x;k++){
						warehouseArr.push(j)
						var imgUrl = "url('/image/Architect/Res/" + resNames[j] + ".png')" 
						var singleStyle = {
							"background": imgUrl,
							"background-size": "cover"
						}
						warehouseStyles.push(singleStyle)
					}
				}
				$scope.players[i].warehouseArr = warehouseArr
				$scope.players[i].warehouseStyles = warehouseStyles
			}
		}
		
		$scope.getBoard = function(){
			$http.get('/architect/getboard').then(function(response){
				$scope.gamedata = response.data
				$scope.myIndex = parseInt(response.data.myIndex)
				$scope.players = response.data.players
				$scope.revealedCards = response.data.revealedCards
				$scope.hand = $scope.players[$scope.myIndex].hand
				setResources()
			});
		}
		
		$scope.getBoard();
		
}]);
