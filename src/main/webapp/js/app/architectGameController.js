var setUrl = function(d){
	hearder = "http://";
	server = "localhost:8080";
	return header + server + d;
}

var app = angular.module("architectGameApp", []);
app.controller("architectGameCtrl", ['$scope', '$window', '$http', '$document',
	function($scope, $window, $http, $document){
		var resNames = ["wood", "stone", "iron", "gold"];
		$scope.shownPlayDetails = -1;
	
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
			$scope.shownPlayDetails = -1
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
		
		$scope.clickHand = function(x){
			if ($scope.shownPlayDetails == x){
				$scope.shownPlayDetails = -1
			} else {
				$scope.shownPlayDetails = x
			}
			
		}
		
		setCardStyle = function(c){
			var i
			
			var provideResArr = [];
			var provideResStyles = [];
			for (i=0;i<c.provideRes.length;i++){
				var x = parseInt(c.provideRes[i])
				for (j=0;j<x;j++){
					provideResArr.push(i)
					var imgUrl = "url('/image/Architect/Res/" + resNames[i] + ".png')" 
					var singleStyle = {
						"background": imgUrl,
						"background-size": "cover"
					}
					provideResStyles.push(singleStyle)
				}
			}
			
			var x = parseInt(c.numUpgrade)
			for (i=0;i<x;i++){
				provideResArr.push(4)
				var imgUrl = "url('/image/Architect/Res/upgrade.png')" 
				var singleStyle = {
					"background": imgUrl,
					"background-size": "cover"
				}
				provideResStyles.push(singleStyle)
			}
			
			c.provideResArr = provideResArr
			c.provideResStyles = provideResStyles
			
			var imgUrl = "url('/image/Architect/Cards/" + c.img + ".png')" 
			var singleStyle = {
				"background": imgUrl,
				"background-size": "cover"
			} 
			c.cardStyle = singleStyle
		}
		
		setCardStyles = function(){
			var i
			for (i=0;i<$scope.hand.length;i++){
				setCardStyle($scope.hand[i])
			}
		}
		
		
		setResources = function(){
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
				setCardStyles()
			});
		}
		
		$scope.getBoard();
		
}]);
