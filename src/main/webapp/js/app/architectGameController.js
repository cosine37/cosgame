var setUrl = function(d){
	hearder = "http://";
	server = "localhost:8080";
	return header + server + d;
}

var app = angular.module("architectGameApp", ["ngWebSocket"]);
app.controller("architectGameCtrl", ['$scope', '$window', '$http', '$document', '$websocket',
	function($scope, $window, $http, $document, $websocket){
		$scope.username = ""
		$http.post('/username').then(function(response){
			$scope.username = response.data.value[0];
		});
	
		var ws = $websocket("ws://" + $window.location.host + "/architect/boardrefresh");
		var heartCheck = {
			timeout: 10000,//10s
			timeoutObj: null,
			reset: function(){
				clearTimeout(this.timeoutObj);
			　　 	this.start();
			},
			start: function(){
				this.timeoutObj = setTimeout(function(){
					var msg = $scope.username + " heart beat"
					ws.send(msg);
				}, this.timeout)
			}
		}
	
		ws.onError(function(event) {
			//alert("error!")
		});
	
		ws.onClose(function(event) {
			//alert("closed!")
		});
	
		ws.onOpen(function() {
			heartCheck.start();
		});
	
		var resNames = ["wood", "stone", "iron", "gold"];
		
		$scope.altOptions = ["0/0", "0/1", "1/0", "0/2", "1/1", "2/0", "0/3", "1/2", "2/1", "3/0"]
		$scope.altMax = [0,2,5,9]
		
		$scope.shownPlayDetails = -1;
		$scope.shownHireDetails = -1;
		$scope.shownBuildingDetails = -1;
		$scope.playTime = 0;
		$scope.maxPlayTime = 0;
		$scope.selectedRes = []
		$scope.canDiscard = false;
		$scope.hireRes = [];
		$scope.maxNumBuilding = -1;
		$scope.selectedPlay = []
	
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
		
		playYourTurnMusic = function(){
			var audio = new Audio("/sound/Architect/yourturn.mp3")
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
		
		playEndingMusic = function(){
			var quotes = ["ending01","ending02"]
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
			} else if (c.type == '4'){
				for (var i=0;i<$scope.selectedPlay.length;i++){
					if ($scope.selectedPlay[i] == 1){
						targets.push(i)
					}
				}
				if (c.subType == '3'){
					for (var i=0;i<$scope.selectedRes.length;i++){
						if ($scope.selectedRes[i] == 1){
							var y = $scope.players[$scope.myIndex].warehouseArr[i]
							targets.push(y)
						}
					}
				}
			} else if (c.type == '5'){
				targets.push($scope.playTime)
			}
			if (targets.length==0){
				targets.push(-1)
			}
			var data = {"cardIndex" : x, "targets": targets}
			$http({url: "/architect/play", method: "POST", params: data}).then(function(response){
				$scope.allRefresh()
				//$scope.getBoard()
				//$scope.hideAreaCard();
			});
		}
		
		$scope.rest = function(){
			$scope.shownPlayDetails = -1
			$scope.shownHireDetails = -1
			playRecoverMusic()
			$http({url: "/architect/rest", method: "POST"}).then(function(response){
				$scope.allRefresh()
				//$scope.getBoard()
				//$scope.hideAreaCard();
			});
		}
		
		$scope.hire = function(x){
			$scope.shownHireDetails = -1;
			var resArr = [-1,-1,-1,-1,-1,-1]
			for (i=0;i<$scope.hireRes.length;i++){
				if ($scope.hireRes[i]>=0){
					var t = $scope.players[$scope.myIndex].warehouseArr[$scope.hireRes[i]]
					resArr[i] = t
				}
			}
			//alert(JSON.stringify(resArr))
			$scope.hireRes = [];
			var data = {"index" : x, "res": resArr}
			playHiredMusic()
			$http({url: "/architect/hire", method: "POST", params: data}).then(function(response){
				$scope.allRefresh()
				//$scope.getBoard()
			});
		}
		
		$scope.build = function(x){
			$scope.shownBuildingDetails = -1
			$scope.shownPlayDetails = -1
			$scope.shownHireDetails = -1
			playBuildMusic()
			var data = {"buildingIndex" : x}
			$http({url: "/architect/build", method: "POST", params: data}).then(function(response){
				$scope.allRefresh()
				//$scope.getBoard()
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
				$scope.allRefresh()
				//$scope.getBoard()
				//$scope.hideAreaCard();
			});
		}
		
		$scope.clickRevealedCard = function(x){
			if ($scope.phase != 1) return
			if (x>$scope.revealedCards.length) return
			$scope.shownPlayDetails = -1
			$scope.selectedRes = []
			$scope.selectedPlay = []
			$scope.hireRes = []
			if ($scope.shownHireDetails == x){
				$scope.shownHireDetails = -1
				
			} else {
				playClickQuote($scope.revealedCards[x])
				$scope.shownHireDetails = x
				var i
				for (i=0;i<$scope.players[$scope.myIndex].warehouseArr.length;i++){
					$scope.selectedRes.push(0)
				}
				for (i=0;i<$scope.players[$scope.myIndex].play.length;i++){
					$scope.selectedPlay.push(0)
				}
				for (i=0;i<6;i++){
					if (i<x){
						$scope.hireRes.push(-1)
					} else {
						$scope.hireRes.push(-2)
					}
				}
				//alert(JSON.stringify($scope.hireRes))
			}
		}
		
		$scope.clickHand = function(x){
			if ($scope.phase != 1) return
			if (x>$scope.hand.length) return
			$scope.shownHireDetails = -1;
			$scope.selectedRes = []
			$scope.selectedPlay = []
			$scope.playTime = 0;
			$scope.maxPlayNum = parseInt($scope.hand[x].maxPlayNum)
			for (var i=0;i<$scope.players[$scope.myIndex].warehouseArr.length;i++){
				$scope.selectedRes.push(0)
			}
			for (i=0;i<$scope.players[$scope.myIndex].play.length;i++){
				$scope.selectedPlay.push(0)
			}
			if ($scope.shownPlayDetails == x){
				$scope.shownPlayDetails = -1
			} else {
				playClickQuote($scope.hand[x])
				$scope.shownPlayDetails = x
			}
		}
		
		$scope.clickRevealedBuilding = function(x){
			if ($scope.phase != 1) return
			if (x>$scope.revealedBuildings.length) return
			if ($scope.revealedBuildings[x].canBuild != 'y') return
			if ($scope.shownBuildingDetails == x){
				$scope.shownBuildingDetails = -1
			} else {
				$scope.shownBuildingDetails = x
			}
		}
		
		$scope.buildingTDStyle = function(x){
			var style = {}
			if (x == 0 && $scope.num3vp > 0){
				style = {"background": "gold"}
			} else if (x == 1 && $scope.num1vp > 0){
				style = {"background": "silver"}
			}
			return style
		}
		
		$scope.numBuildingStyle = function(x){
			var t = parseInt(x)
			//alert(t)
			var style = {}
			if (t == $scope.numBuildingFinish){
				style = {"font-weight": "bold", "font-size": "24px", "color": "red"}
			} else if (t == $scope.numBuildingFinish-1){
				style = {"font-weight": "bold", "font-size": "20px", "color": "chocolate"}
			}
			return style
		}
		
		$scope.clickPlay = function(x){
			if ($scope.phase == 1){
				if ($scope.shownPlayDetails>=0 && $scope.shownPlayDetails<$scope.hand.length){
					c = $scope.hand[$scope.shownPlayDetails]
					if (c.type == '4'){
						if ($scope.selectedPlay.length > x && x>=0){
							if ($scope.selectedPlay[x] == 1){
								$scope.selectedPlay[x] = 0
							} else {
								var n = parseInt(c.numAwaken)
								var t = 0
								for (i=0;i<$scope.selectedPlay.length;i++){
									t=t+$scope.selectedPlay[i]
								}
								if (t<n){
									$scope.selectedPlay[x] = 1;
								}
							}
						}
					}
				}
			}
		}
		
		$scope.showPlayChecked = function(x){
			if (x >= $scope.selectedPlay.length){
				return false;
			} else if ($scope.selectedPlay[x] == 1){
				return true;
			} else {
				return false;
			}
		}
		
		$scope.resStyle = function(xx){
			var style ={}
			style["background"] = $scope.players[$scope.myIndex].warehouseStyles[xx]["background"]
			style["background-size"] = $scope.players[$scope.myIndex].warehouseStyles[xx]["background-size"]
			if (xx>=0 && xx<$scope.selectedRes.length){
				if ($scope.selectedRes[xx] == 1){
					style.border = "2px solid rgb(160,32,240)"
				} else {
					style.border = "none"
				}
				if (xx>0 && xx%5 == 0){
					style["margin-left"] = "15px"
				} else {
					style["margin-left"] = "0px"
				}
			}
			return style
		}
		
		$scope.clickRes = function(x){
			if ($scope.phase == 2){
				
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
			} else if ($scope.phase == 1){
				if ($scope.shownPlayDetails>=0 && $scope.shownPlayDetails<$scope.hand.length){
					c = $scope.hand[$scope.shownPlayDetails]
					if (c.type == '3' || c.subType == '3'){
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
				} else if ($scope.shownHireDetails>=0 && $scope.shownHireDetails<$scope.revealedCards.length){
					var addIn = true
					var i
					for (i=0;i<$scope.shownHireDetails;i++){
						if (x == $scope.hireRes[i]){
							addIn = false
							$scope.selectedRes[x] = 0
							$scope.hireRes[i] = -1
							break
						}
					}
					if (addIn){
						for (i=0;i<$scope.shownHireDetails;i++){
							if ($scope.hireRes[i] == -1){
								$scope.selectedRes[x] = 1
								$scope.hireRes[i] = x
								break
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
			} else if (c.type == '2' || c.type == '5'){
				if ($scope.playTime == 0){
					return true;
				} else {
					return false;
				}
			} else if (c.type == '4'){
				var n = parseInt(c.numAwaken)
				var m = 0
				for (i=0;i<$scope.selectedPlay.length;i++){
					m = m+$scope.selectedPlay[i]
				}
				if (n != m){
					return true
				} else {
					if (c.subType == '3'){
						n = parseInt(c.numUpgrade)
						m = 0
						for (i=0;i<$scope.selectedRes.length;i++){
							m = m+$scope.selectedRes[i]
						}
						if (n != m){
							return true
						} else {
							return false
						}
					} else {
						return false;
					}
				}
				
			}
			return true;
		}
		
		
		
		$scope.disableHire = function(x){
			if ($scope.shownHireDetails != x) return true
			for (var i=0;i<x;i++){
				if ($scope.hireRes[i] < 0) return true
			}
			return false;
		}
		
		$scope.showMiddleRes = function(x){
			if (x>=$scope.hireRes.length || x<0) return false
			if ($scope.hireRes[x] == -2) return false
			if (x>$scope.shownHireDetails) return false;
			return true
		}
		
		$scope.middleResStyle = function(x){
			var style = {}
			if (x>=0 && x<$scope.hireRes.length){
				var imgUrl
				if ($scope.hireRes[x] == -1){
					imgUrl = "url('/image/Architect/Res/empty.png')" 
				} else {
					var t = $scope.players[$scope.myIndex].warehouseArr[$scope.hireRes[x]]
					imgUrl = "url('/image/Architect/Res/" + resNames[t] + ".png')" 
				}
				style = {
					"background": imgUrl,
					"background-size": "cover"
				}
			}
			return style
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
			
			if (c.type == '5'){
				var imgUrl = "url('/image/Architect/slash.png')" 
				var singleStyle = {
					"background": imgUrl,
					"background-size": "cover"
				}
				provideResArr.push(6)
				provideResStyles.push(singleStyle)
				for (i=0;i<c.provideResAlt.length;i++){
					var x = parseInt(c.provideResAlt[i])
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
			
			var x = parseInt(c.numAwaken)
			for (i=0;i<x;i++){
				provideResArr.push(5)
				var imgUrl = "url('/image/Architect/Res/awaken.png')" 
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
			var i,j
			for (i=0;i<$scope.hand.length;i++){
				setCardStyle($scope.hand[i])
			}
			$scope.selectedPlay = []
			for (i=0;i<$scope.players.length;i++){
				for (j=0;j<$scope.players[i].play.length;j++){
					setCardStyle($scope.players[i].play[j])
					if (i == $scope.myIndex){
						$scope.selectedPlay.push(0);
					}
				}
			}
			for (i=0;i<$scope.revealedCards.length;i++){
				setCardStyle($scope.revealedCards[i])
			}
			
		}
		
		setBuildingStyle = function(b,f){
			var imgUrl = "url('/image/Architect/Buildings/" + b.img + ".png')" 
			var singleStyle = {
				"background": imgUrl,
				"background-size": "cover"
			} 
			if (b.canBuild != "y" && f){
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
				setBuildingStyle($scope.revealedBuildings[i], true)
			}
			
			for (i=0;i<$scope.myBuildings.length;i++){
				setBuildingStyle($scope.myBuildings[i], false)
			}
		}
		
		
		setResources = function(){
			var i,j
			$scope.selectedRes = []
			for (i=0;i<$scope.players.length;i++){
				var warehouseArr = []
				var warehouseStyles = []
				for (j=0;j<$scope.players[i].warehouse.length;j++){
					var x = parseInt($scope.players[i].warehouse[j])
					for (k=0;k<x;k++){
						warehouseArr.push(j)
						if (i == $scope.myIndex) $scope.selectedRes.push(0)
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
			
			for (i=0;i<$scope.revealedCards.length;i++){
				var styles = []
				for (j=0;j<$scope.revealedCards[i].resOn.length;j++){
					var x = parseInt($scope.revealedCards[i].resOn[j])
					var imgUrl = "url('/image/Architect/Res/" + resNames[x] + ".png')" 
					var singleStyle = {
						"background": imgUrl,
						"background-size": "cover"
					}
					if ($scope.revealedCards[i].resOn.length > 6 && i>0){
						singleStyle = {
							"background": imgUrl,
							"background-size": "cover",
							"margin-left": "-5px"
						}
					}
					styles.push(singleStyle)
				}
				$scope.revealedCards[i].resOnStyles = styles
			}
			
		}
		
		var playEndingMusicHandle = function(){
			var tempMax = -1;
			var i
			for (i=0;i<$scope.players.length;i++){
				var x = parseInt($scope.players[i].numBuildings)
				if (x>tempMax) tempMax = x
			}
			
			if (tempMax >= $scope.numBuildingFinish-1 && $scope.maxNumBuilding < $scope.numBuildingFinish-1){
				playEndingMusic()
			}
			
			$scope.maxNumBuilding = tempMax;
		}
		
		$scope.getBoard = function(){
			$http.get('/architect/getboard').then(function(response){
				$scope.gamedata = response.data
				$scope.status = response.data.status
				if ($scope.status == '3'){
					alert("Game ends");
					$scope.goto('architectendgame');
				}
				$scope.players = response.data.players
				$scope.myIndex = parseInt(response.data.myIndex)
				var tempPhase = $scope.players[$scope.myIndex].phase
				if (tempPhase == '1' && $scope.phase == '0'){
					playYourTurnMusic()
				}
				
				$scope.lord = response.data.lord
				$scope.num1vp = response.data.num1vp
				$scope.num3vp = response.data.num3vp
				$scope.revealedCards = response.data.revealedCards
				$scope.revealedBuildings = response.data.revealedBuildings
				$scope.hand = response.data.myHand
				$scope.myBuildings = response.data.myBuildings
				$scope.phase = $scope.players[$scope.myIndex].phase
				$scope.numBuildingFinish = parseInt(response.data.numBuildingFinish)
				//alert($scope.phase)
				$scope.myScore = response.data.myScore
				$scope.myNum1vp = response.data.myNum1vp
				$scope.myNum3vp = response.data.myNum3vp
				$scope.curPlayerIndex = parseInt(response.data.curPlayerIndex)
				setCardStyles()
				setBuildingStyles()
				setResources()
				playEndingMusicHandle()
				
			});
		}
		
		$scope.getBoard();
		
		ws.onMessage(function(e){
			//alert(JSON.stringify(e.data));
			var message = e.data
			heartCheck.reset();
			if (message == 'refresh'){
				$scope.getBoard();
			}
			
		});
		
		$scope.allRefresh = function(){
			var msg = "refresh";
	        ws.send(msg);
		}
		
}]);
