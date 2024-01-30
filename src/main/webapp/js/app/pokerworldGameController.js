var setUrl = function(d){
	hearder = "http://";
	server = "localhost:8080";
	return header + server + d;
}

var sleep = function(ms) {
	return new Promise(resolve => setTimeout(resolve, ms));
}

var app = angular.module("pokerworldGameApp", ["ngWebSocket"]);
app.controller("pokerworldGameCtrl", ['$scope', '$window', '$http', '$document', '$timeout', '$websocket',
	function($scope, $window, $http, $document, $timeout, $websocket){
		var ws = $websocket("ws://" + $window.location.host + "/pokerworld/boardrefresh");
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
		});
	
		ws.onClose(function(event) {
		});
	
		ws.onOpen(function() {
			heartCheck.start();
		});
		
		$scope.cardStyles = [];
		$scope.hand = [];
		$scope.showCard = [];
		var i;
		for (i=0;i<52;i++){
			$scope.showCard.push(false);
		}
		$scope.dominantRankSuits = {};
		$scope.dominantRankSuits["s"] = 0;
		$scope.dominantRankSuits["d"] = 0;
		$scope.dominantRankSuits["h"] = 0;
		$scope.dominantRankSuits["c"] = 0;
		$scope.curDistributeCardIndex = 0;
		$scope.distributing = false;
		$scope.disableHide = true;
		$scope.disablePlayButton = true;
		$scope.indexSequence = [0,1,2,3];
		
		$scope.SFSJ = 0;
		$scope.WIZARD = 1;
		
		$scope.BIDTRICKS = 1;
		$scope.PLAYCARDS = 3;
		
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
		
		$scope.changeBid = function(x){
			$scope.numTrick = $scope.numTrick+x
			if ($scope.numTrick<0) $scope.numTrick = 0
			if ($scope.numTrick>$scope.round) $scope.numTrick = $scope.round
		}
		
		$scope.bidWizard = function(){
			var data = {"bid": $scope.numTrick}
			$http({url: "/pokerworld/wizard/bid", method: "PUT", params: data}).then(function(response){
				$scope.allRefresh()
			});
		}
		
		
		$scope.clickCard = function(x){
			if ($scope.gameMode == $scope.SFSJ){
				if ($scope.status == 3 && $scope.myIndex == $scope.curPlayer){
					//$scope.disablePlayButton = true;
					if (x>=0 && x<$scope.hand.length){
						$scope.hand[x].chosen = 1-$scope.hand[x].chosen
					}
				} else if ($scope.status == 2 && $scope.curClaimedPlayer == $scope.myIndex){
					if (x>=0 && x<$scope.hand.length){
						$scope.hand[x].chosen = 1-$scope.hand[x].chosen
					}
					var i
					var t = 0
					for (i=0;i<$scope.hand.length;i++){
						t = t+$scope.hand[i].chosen
					}
					if (t == 12){
						$scope.disableHide = false;
					} else {
						$scope.disableHide = true;
					}
				}
			} else if ($scope.gameMode == $scope.WIZARD && $scope.myIndex == $scope.curPlayer && $scope.playable[x] == 1){
				if ($scope.status == $scope.PLAYCARDS){
					if (x>=0 && x<$scope.hand.length){
						if ($scope.chosenCard == x){
							$scope.hand[$scope.chosenCard].cstyle["margin-top"] = "0px"
							$scope.chosenCard = -1;
						} else {
							if ($scope.chosenCard != -1){
								$scope.hand[$scope.chosenCard].cstyle["margin-top"] = "-30px"
							}
							$scope.chosenCard = x;
						}
					}
					if ($scope.chosenCard != -1){
						$scope.hand[$scope.chosenCard].cstyle["margin-top"] = "-30px"
					}
				}
			}
			
		}
		
		$scope.disablePlay = function(){
			if ($scope.status == '3'){
				if ($scope.numPlayed == -1){
					return false;
				} else {
					var t = 0
					for (i=0;i<$scope.hand.length;i++){
						t = t+$scope.hand[i].chosen
					}
					if (t == $scope.numPlayed){
						return false;
					} else {
						return true;
					}
				}
			} else if ($scope.status == '2'){
				return false;
			}
		}
		
		$scope.resetChosen = function(){
			var i
			for (i=0;i<$scope.hand.length;i++){
				$scope.hand[i].chosen = 0
			}
		}
		
		$scope.confirmHide = function(){
			var playedIndex = []
			for (i=0;i<$scope.hand.length;i++){
				if ($scope.hand[i].chosen == 1){
					playedIndex.push(i);
				}
			}
			//alert(JSON.stringify(playedIndex))
			var data = {"playedIndex": playedIndex}
			$http({url: "/pokerworld/confirmhide", method: "POST", params: data}).then(function(response){
				$scope.allRefresh()
			});
		}
		
		$scope.play = function(){
			var playedIndex = []
			if ($scope.gameMode == $scope.SFSJ){
				for (i=0;i<$scope.hand.length;i++){
					if ($scope.hand[i].chosen == 1){
						playedIndex.push(i);
					}
				}
			} else if ($scope.gameMode == $scope.WIZARD){
				playedIndex.push($scope.chosenCard);
			}
			
			var data = {"playedIndex": playedIndex}
			$http({url: "/pokerworld/playcards", method: "POST", params: data}).then(function(response){
				$scope.allRefresh()
			});
		}
		
		$scope.endDistribute = function(){
			$http({url: "/pokerworld/enddistribute", method: "POST"}).then(function(response){
				$scope.allRefresh()
			});
		}
		
		$scope.claimDominant = function(suit, n){
			var data = {"dominantSuit": suit, "numDominant":n}
			$http({url: "/pokerworld/claimdominant", method: "POST", params: data}).then(function(response){
				$scope.allRefresh()
			});
		}
		
		$scope.confirmEndTurn = function(){
			$http({url: "/pokerworld/confirmendturn", method: "POST"}).then(function(response){
				$scope.allRefresh()
			});
		}
		
		translateRawCard = function(raw, p){
			var card = {}
			var r = raw.substring(0,1);
			var s = raw.substring(1,2);
			var c = "black";
			if (r == "T") r = "10";
			card["suitRaw"] = s;
			if (raw == "JO"){
				r = "joker"
				s = "\u265b";
				c = "gold";
			}
			if (raw == "jo"){
				r = "joker"
				s = "\u2655";
				c = "black";
			}
			if (s == "s"){
				s = "\u2660";
				c = "black";
			}
			if (s == "h"){
				s = "\u2665";
				c = "red";
			}
			if (s == "d"){
				s = "\u2666";
				c = "red";
			}
			if (s == "c"){
				s = "\u2663";
				c = "black";
			}
			
			if (raw == "WZ" || raw == "Wz" || raw == "wZ" || raw == "wz"){
				r = "wizard"
				s = raw;
				c = "blue";
			}
			
			if (raw == "JE" || raw == "Je" || raw == "jE" || raw == "je"){
				r = "jester"
				s = raw;
				c = "green";
			}
			
			card["rank"] = r
			card["suit"] = s
			card["color"] = c
			card["chosen"] = 0;
			card["cstyle"] = {
				"margin-top": "0px"
			}
			
			if (p == 1){
				card["cstyle"]["opacity"] = "1";
				if ($scope.status == $scope.BIDTRICKS){
					card["cstyle"]["cursor"] = "auto"
				}
			} else {
				card["cstyle"]["opacity"] = "0.5";
				card["cstyle"]["cursor"] = "not-allowed"
			}
			
			return card
		}
		
		setCardStyles = function(){
			$scope.hand = [];
			var i = 0;
			while (i<$scope.myCards.length){
				var rawCard = $scope.myCards.substring(i,i+2);
				$scope.hand.push(translateRawCard(rawCard, $scope.playable[i/2]));
				i=i+2;
			}
			for (i=0;i<$scope.players.length;i++){
				var j = 0;
				var played = [];
				while (j<$scope.players[i].playedCards.length){
					var rawCard = $scope.players[i].playedCards.substring(j,j+2);
					played.push(translateRawCard(rawCard, 1));
					j = j+2;
				}
				$scope.players[i]["played"] = played
			}
		}
		
		setIndexes = function(){
			$scope.indexSequence = [0,1,2,3];
			var i;
			for (i=0;i<4;i++){
				var x = ($scope.myIndex + i)%4;
				$scope.indexSequence[i] = x;
			}
		}
		
		distributeOneCard = function(){
			//var rawCard = $scope.myCards.substring($scope.curDistributeCardIndex,$scope.curDistributeCardIndex+2);
			//$scope.hand.push(translateRawCard(rawCard));
			if ($scope.curDistributeCardIndex >= $scope.distributeSequence.length) return
			var x = $scope.distributeSequence[$scope.curDistributeCardIndex]
			var c = $scope.hand[x];
			if (c.rank == $scope.dominantRank){
				$scope.dominantRankSuits[c.suitRaw] = $scope.dominantRankSuits[c.suitRaw]+1;
			}
			$scope.showCard[x] = true
			$timeout(function(){
				$scope.curDistributeCardIndex = $scope.curDistributeCardIndex+1
				if ($scope.curDistributeCardIndex < $scope.distributeSequence.length){
					distributeOneCard();
				} else {
					$scope.showEndDistribute = true;
				}
			}, 500);
		}
		
		distributeCards = function(){
			$scope.showEndDistribute = false;
			distributeOneCard();
		}
		
		$scope.getBoard = function(){
			$http.get('/pokerworld/getboard').then(function(response){
				if (response.data.id == "NE"){
					alert("该游戏已解散");
					$scope.goto('pokerworld');
					return;
				}
				$scope.gamedata = response.data
				$scope.id = response.data.id
				$scope.status = response.data.status
				$scope.gameMode = response.data.gameMode
				$scope.players = response.data.players
				$scope.lord = response.data.lord
				$scope.myCards = response.data.myCards
				$scope.round = response.data.round
				$scope.numTrick = 0
				$scope.distributeSequence = response.data.sequence;
				$scope.dominantRank = response.data.dominantRank;
				$scope.dominantSuit = response.data.dominantSuit;
				$scope.numDominant = response.data.numDominant;
				$scope.curClaimedPlayer = response.data.curClaimedPlayer;
				$scope.dominantSuitDisplay = "";
				$scope.dominantSuitDisplayClass = "";
				$scope.myIndex = response.data.myIndex;
				$scope.numPlayed = response.data.numPlay;
				$scope.curPlayer = response.data.curPlayer;
				$scope.winPlayer = response.data.winPlayer;
				$scope.confirmed = response.data.confirmed;
				$scope.confirmedNextTurn = response.data.confirmedNextTurn;
				$scope.attackerPointsGained = response.data.attackerPointsGained;
				$scope.chosenCard = -1;
				$scope.playable = response.data.playable
				if ($scope.dominantSuit == "s"){
					$scope.dominantSuitDisplay = "\u2660";
					$scope.dominantSuitDisplayClass = "black";
				} else if ($scope.dominantSuit == "h"){
					$scope.dominantSuitDisplay = "\u2665";
					$scope.dominantSuitDisplayClass = "red";
				} else if ($scope.dominantSuit == "c"){
					$scope.dominantSuitDisplay = "\u2663";
					$scope.dominantSuitDisplayClass = "black";
				} else if ($scope.dominantSuit == "d"){
					$scope.dominantSuitDisplay = "\u2666";
					$scope.dominantSuitDisplayClass = "red";
				} 
				
				var i
				var kicked = true;
				for (i=0;i<$scope.players.length;i++){
					if ($scope.players[i].name == $scope.username){
						kicked = false;
					}
				}
				if (kicked){
					alert("你已被" + $scope.lord + "踢出");
					$scope.goto('pokerworld');
					return;
				}
				setCardStyles()
				/*
				if ($scope.status == "1" && $scope.distributing == false){
					$scope.distributing = true;
					distributeCards();
				} else {
					
				}
				*/

			});
		}
		
		$scope.getBoard();
		
		ws.onMessage(function(e){
			var message = e.data
			heartCheck.reset();
			if (message == 'refresh' || message == 'start'){
				$scope.getBoard();
			}
			
		});
		
		$scope.allRefresh = function(){
			var msg = "refresh";
	        ws.send(msg);
		}
		
		
}]);
