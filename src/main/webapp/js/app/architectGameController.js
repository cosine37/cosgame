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
		$scope.shownBuildingDetails = -1;
		$scope.playTime = 0;
		$scope.maxPlayTime = 0;
		$scope.selectedRes = []
		$scope.canDiscard = false;
	
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
		
		playDiscardMusic = function(){
			var audio = new Audio("/sound/Architect/discard.mp3")
			audio.play();
		}
		
		playBuildMusic = function(){
			var quotes = ["build01","build02"]
			var x = Math.floor(Math.random() * quotes.length);
			var audio = new Audio("/sound/Architect/" + quotes[x] + ".mp3")
			audio.play();
		}
		
		$scope.play = function(x){
			var c = $scope.hand[x]
			playResolveQuote(c)
			$scope.shownPlayDetails = -1
			var targets = []
			if (c.type == '3'){
				for (var i=0;i<$scope.selectedRes.length;i++){
					if ($scope.selectedRes[i] == 1){
						var y = $scope.players[$scope.myIndex].warehouseArr[i]
						targets.push(y)
					}
				}
			} else if (c.type == '2'){
				targets.push($scope.playTime)
			}
			if (targets.length==0){
				targets.push(-1)
			}
			var data = {"cardIndex" : x, "targets": targets}
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
		
		$scope.build = function(x){
			$scope.shownBuildingDetails = -1
			playBuildMusic()
			var data = {"buildingIndex" : x}
			$http({url: "/architect/build", method: "POST", params: data}).then(function(response){
				//$scope.allRefresh()
				$scope.getBoard()
				//$scope.hideAreaCard();
			});
		}
		
		$scope.discard = function(){
			playDiscardMusic()
			var targets = []
			var i;
			for (i=0;i<$scope.selectedRes.length;i++){
				if ($scope.selectedRes[i] == 1){
					targets.push($scope.players[$scope.myIndex].warehouseArr[i])
				}
			}
			var data = {"targets": targets}
			$http({url: "/architect/discard", method: "POST", params: data}).then(function(response){
				//$scope.allRefresh()
				$scope.getBoard()
				//$scope.hideAreaCard();
			});
		}
		
		$scope.clickRevealedCard = function(x){
			if ($scope.phase != '1') return
			if (x>$scope.revealedCards.length) return
			if ($scope.shownHireDetails == x){
				$scope.shownHireDetails = -1
			} else {
				playClickQuote($scope.revealedCards[x])
				$scope.shownHireDetails = x
			}
			
		}
		
		$scope.clickHand = function(x){
			if ($scope.phase != '1') return
			if (x>$scope.hand.length) return
			$scope.selectedRes = []
			$scope.playTime = 0;
			$scope.maxPlayNum = parseInt($scope.hand[x].maxPlayNum)
			for (var i=0;i<$scope.players[$scope.myIndex].warehouseArr.length;i++){
				$scope.selectedRes.push(0)
			}
			if ($scope.shownPlayDetails == x){
				$scope.shownPlayDetails = -1
			} else {
				playClickQuote($scope.hand[x])
				$scope.shownPlayDetails = x
			}
		}
		
		$scope.clickRevealedBuilding = function(x){
			if ($scope.phase != '1') return
			if (x>$scope.revealedBuildings.length) return
			if ($scope.revealedBuildings[x].canBuild != 'y') return
			if ($scope.shownBuildingDetails == x){
				$scope.shownBuildingDetails = -1
			} else {
				$scope.shownBuildingDetails = x
			}
		}
		
		$scope.resStyle = function(x){
			var style = $scope.players[$scope.myIndex].warehouseStyles[x]
			if (x>=0 && x<$scope.selectedRes.length){
				if ($scope.selectedRes[x] == 1){
					style.border = "2px solid rgb(160,32,240)"
				} else {
					style.border = "none"
				}
				
			}
			return style
		}
		
		$scope.clickRes = function(x){
			if ($scope.phase == '2'){
				if ($scope.selectedRes[x] == 1){
					$scope.selectedRes[x] = 0;
					$scope.canDiscard = false;
				} else {
					var t = 0
					for (i=0;i<$scope.selectedRes.length;i++){
						t=t+$scope.selectedRes[i]
					}
					if (t+10 < $scope.selectedRes.length){
						$scope.selectedRes[x] = 1;
						t++;
						if (t+10 == $scope.selectedRes.length){
							$scope.canDiscard = true;
						}
					}
				}
			} else if ($scope.phase == '1'){
				if ($scope.shownPlayDetails>=0 && $scope.shownPlayDetails<$scope.hand.length){
					c = $scope.hand[$scope.shownPlayDetails]
					if (c.type == '3'){
						if ($scope.players[$scope.myIndex].warehouseArr[x]!=3){
							if ($scope.selectedRes[x] == 1){
								$scope.selectedRes[x] = 0;
							} else {
								var n = parseInt(c.numUpgrade)
								var t = 0
								for (i=0;i<$scope.selectedRes.length;i++){
									t=t+$scope.selectedRes[i]
								}
								if (t<n){
									$scope.selectedRes[x] = 1;
								}
							}
						}
					}
				}
			}
		}
		
		$scope.changePlayTime = function(x){
			$scope.playTime = $scope.playTime+x;
		}
		
		$scope.disablePlay = function(x){
			c = $scope.hand[x]
			if (c.type == '1'){
				return false
			} else if (c.type == '3'){
				var n = parseInt(c.numUpgrade)
				var m = 0
				for (i=0;i<$scope.selectedRes.length;i++){
					m = m+$scope.selectedRes[i]
				}
				if (n != m){
					return true
				} else {
					return false
				}
			} else if (c.type == '2'){
				if ($scope.playTime == 0){
					return true;
				} else {
					return false;
				}
			}
			return true;
		}
		
		setCardStyle = function(c){
			var i,j
			
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
			
			var needResArr = [];
			var needResStyles = [];
			for (i=0;i<c.needRes.length;i++){
				var x = parseInt(c.needRes[i])
				for (j=0;j<x;j++){
					needResArr.push(i)
					var imgUrl = "url('/image/Architect/Res/" + resNames[i] + ".png')" 
					var singleStyle = {
						"background": imgUrl,
						"background-size": "cover"
					}
					needResStyles.push(singleStyle)
				}
			}
			//alert(JSON.stringify(needResArr))
			c.needResArr = needResArr
			c.needResStyles = needResStyles
			
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
		
		setBuildingStyle = function(b){
			var imgUrl = "url('/image/Architect/Buildings/" + b.img + ".png')" 
			var singleStyle = {
				"background": imgUrl,
				"background-size": "cover"
			} 
			if (b.canBuild != "y"){
				singleStyle = {
					"background": imgUrl,
					"background-size": "cover",
					"opacity": "0.5"
				}
			}
			b.buildingStyle = singleStyle
			
			var priceArr = []
			var priceStyles = []
			for (i=0;i<b.price.length;i++){
				var x = parseInt(b.price[i])
				for (j=0;j<x;j++){
					priceArr.push(i)
					var imgUrl = "url('/image/Architect/Res/" + resNames[i] + ".png')" 
					var singleStyle = {
						"background": imgUrl,
						"background-size": "cover"
					}
					
					priceStyles.push(singleStyle)
				}
			}
			b.priceArr = priceArr
			b.priceStyles = priceStyles
		}
		
		setBuildingStyles = function(){
			var i
			for (i=0;i<$scope.revealedBuildings.length;i++){
				setBuildingStyle($scope.revealedBuildings[i])
			}
		}
		
		
		setResources = function(){
			for (i=0;i<$scope.players.length;i++){
				var warehouseArr = []
				var warehouseStyles = []
				$scope.selectedRes = []
				for (j=0;j<$scope.players[i].warehouse.length;j++){
					var x = parseInt($scope.players[i].warehouse[j])
					for (k=0;k<x;k++){
						warehouseArr.push(j)
						$scope.selectedRes.push(0)
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
				$scope.revealedBuildings = response.data.revealedBuildings
				$scope.hand = $scope.players[$scope.myIndex].hand
				$scope.phase = $scope.players[$scope.myIndex].phase
				setResources()
				setCardStyles()
				setBuildingStyles()
			});
		}
		
		$scope.getBoard();
		
}]);
