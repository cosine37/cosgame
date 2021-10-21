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
		
		$scope.clickCard = function(x){
			if ($scope.status == '3'){
				if (x>=0 && x<$scope.hand.length){
					$scope.hand[x].chosen = 1-$scope.hand[x].chosen
				}
			}
		}
		
		$scope.resetChosen = function(){
			var i
			for (i=0;i<$scope.hand.length;i++){
				$scope.hand[i].chosen = 0
			}
		}
		
		$scope.play = function(){
			var playedIndex = []
			for (i=0;i<$scope.hand.length;i++){
				if ($scope.hand[i].chosen == 1){
					playedIndex.push(i);
				}
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
		
		translateRawCard = function(raw){
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
			card["rank"] = r
			card["suit"] = s
			card["color"] = c
			card["chosen"] = 0;
			return card
		}
		
		setCardStyles = function(){
			$scope.hand = [];
			var i = 0;
			while (i<$scope.myCards.length){
				var rawCard = $scope.myCards.substring(i,i+2);
				$scope.hand.push(translateRawCard(rawCard));
				i=i+2;
			}
			for (i=0;i<$scope.players.length;i++){
				var j = 0;
				var played = [];
				while (j<$scope.players[i].playedCards.length){
					var rawCard = $scope.players[i].playedCards.substring(j,j+2);
					played.push(translateRawCard(rawCard));
					j = j+2;
				}
				$scope.players[i]["played"] = played
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
				$scope.players = response.data.players
				$scope.lord = response.data.lord
				$scope.myCards = response.data.myCards
				$scope.distributeSequence = response.data.sequence;
				$scope.dominantRank = response.data.dominantRank;
				$scope.dominantSuit = response.data.dominantSuit;
				$scope.numDominant = response.data.numDominant;
				$scope.curClaimedPlayer = response.data.curClaimedPlayer;
				$scope.dominantSuitDisplay = "";
				$scope.dominantSuitDisplayClass = "";
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
				if ($scope.status == "1" && $scope.distributing == false){
					$scope.distributing = true;
					distributeCards();
				} else {
					
				}
				

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
