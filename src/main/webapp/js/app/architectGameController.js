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
		$scope.shownHireDetails = -1;
	
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
		
		playClickQuote = function(c){
			var n = c.clickQuote.length
			var x = Math.floor(Math.random() * n);
			var audio = new Audio("/sound/Architect/" + c.clickQuote[x] + ".mp3")
			audio.play();
		}
		
		playResolveQuote = function(c){
			var n = c.resolveQuote.length
			var x = Math.floor(Math.random() * n);
			var audio = new Audio("/sound/Architect/" + c.resolveQuote[x] + ".mp3")
			audio.play();
		}
		
		playRecoverMusic = function(){
			var quotes = ["recover01","recover02","recover03","recover04"]
			var x = Math.floor(Math.random() * 4);
			var audio = new Audio("/sound/Architect/" + quotes[x] + ".mp3")
			audio.play();
		}
		
		playHiredMusic = function(){
			var audio = new Audio("/sound/Architect/hired.mp3")
			audio.play();
		}
		
		$scope.play = function(x){
			$scope.shownPlayDetails = -1
			var data = {"cardIndex" : x}
			var c = $scope.hand[x]
			playResolveQuote(c)
			$http({url: "/architect/play", method: "POST", params: data}).then(function(response){
				//$scope.allRefresh()
				$scope.getBoard()
				//$scope.hideAreaCard();
			});
		}
		
		$scope.rest = function(){
			playRecoverMusic()
			$http({url: "/architect/rest", method: "POST"}).then(function(response){
				//$scope.allRefresh()
				$scope.getBoard()
				//$scope.hideAreaCard();
			});
		}
		
		$scope.hire = function(x){
			$scope.shownHireDetails = -1;
			var resArr = [-1,-1,-1,-1,-1,-1]
			var data = {"index" : x, "res": resArr}
			playHiredMusic()
			$http({url: "/architect/hire", method: "POST", params: data}).then(function(response){
				//$scope.allRefresh()
				$scope.getBoard()
				//$scope.hideAreaCard();
			});
		}
		
		$scope.clickRevealedCard = function(x){
			if (x>$scope.revealedCards.length) return
			if ($scope.shownHireDetails == x){
				$scope.shownHireDetails = -1
			} else {
				playClickQuote($scope.revealedCards[x])
				$scope.shownHireDetails = x
			}
			
		}
		
		$scope.clickHand = function(x){
			if (x>$scope.hand.length) return
			if ($scope.shownPlayDetails == x){
				$scope.shownPlayDetails = -1
			} else {
				playClickQuote($scope.hand[x])
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
			for (i=0;i<$scope.revealedCards.length;i++){
				setCardStyle($scope.revealedCards[i])
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
