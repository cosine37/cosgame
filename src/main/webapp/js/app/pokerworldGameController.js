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
		$scope.HORSERACE = 2;
		$scope.HEARTS = 3;
		
		$scope.BIDTRICKS = 1;
		$scope.PLAYCARDS = 3;
		$scope.SCORING = 4;
		$scope.CONFIRMROUNDTURN = 5;
		
		$scope.PREENDGAME = 9;
		$scope.ENDGAME = 10;
		
		$scope.STATIONCHOOSE = 21;
		$scope.CIRCUSPASS = 22;
		
		$scope.passTo = ""
		$scope.HEARTSPASS = 301;
		$scope.HEARTSPASSSELECT = 301;
		$scope.HEARTSRECEIVE = 302;
		$scope.HEARTSWAITING = 303;
		$scope.numCardsChosen = 0;
		
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
		
		$scope.dismiss = function(){
			$http.post("/pokerworld/dismiss").then(function(response){
				ws.send("dismiss");
			});
		}
		
		playClickSE = function(){
			if ($scope.playSE){
				var audio = new Audio("/sound/Pokerworld/click.wav")
				audio.play();
			}
		}
		
		playPlaySE = function(){
			if ($scope.playSE){
				var audio = new Audio("/sound/Pokerworld/play.wav")
				audio.play();
			}
		}
		
		playBidSE = function(){
			if ($scope.playSE){
				var audio = new Audio("/sound/Pokerworld/bid.wav")
				audio.play();
			}
		}
		
		$scope.playedBGM = false
		$scope.playBGM = true;
		$scope.playSE = true;
		$scope.muteSEButton = "关闭音效"
		$scope.bgm = new Audio();
		$scope.muteButton = "关闭BGM"
		randomizeBGM = function(){
			if ($scope.gameMode == $scope.WIZARD){
				if ($scope.totalRounds == 6){
					if ($scope.round == 6){
						v = Math.floor(Math.random() * 5)+1;
						bgmSrc = '/sound/Pokerworld/ending' + v + '.mp3'
					} else if ($scope.round == 4){
						v = Math.floor(Math.random() * 3)+1;
						bgmSrc = '/sound/Pokerworld/game_r8' + v + '.mp3'
					} else if ($scope.round == 2){
						v = Math.floor(Math.random() * 4)+1;
						bgmSrc = '/sound/Pokerworld/game_r4' + v + '.mp3'
					} else if ($scope.round == 5){
						bgmSrc = '/sound/Pokerworld/game_r19.mp3'
					}
					
					else {
						v = Math.floor(Math.random() * 3)+1;
						bgmSrc = '/sound/Pokerworld/game' + v + '.mp3'
					}
				} else if ($scope.totalRounds == 10){
					if ($scope.round == 10){
						v = Math.floor(Math.random() * 5)+1;
						bgmSrc = '/sound/Pokerworld/ending' + v + '.mp3'
					} else if ($scope.round == 5){
						v = Math.floor(Math.random() * 3)+1;
						bgmSrc = '/sound/Pokerworld/game_r8' + v + '.mp3'
					} else if ($scope.round == 3 || $scope.round == 7){
						v = Math.floor(Math.random() * 4)+1;
						bgmSrc = '/sound/Pokerworld/game_r4' + v + '.mp3'
					} else if ($scope.round == 9){
						bgmSrc = '/sound/Pokerworld/game_r19.mp3'
					}
					
					else {
						v = Math.floor(Math.random() * 3)+1;
						bgmSrc = '/sound/Pokerworld/game' + v + '.mp3'
					}
				} else {
					if ($scope.round == $scope.totalRounds){
						v = Math.floor(Math.random() * 5)+1;
						bgmSrc = '/sound/Pokerworld/ending' + v + '.mp3'
					} else if ($scope.round == 4 || $scope.round == 9 || $scope.round == 15){
						v = Math.floor(Math.random() * 3)+1;
						bgmSrc = '/sound/Pokerworld/game_r8' + v + '.mp3'
					} else if ($scope.round == 7 || $scope.round == 13 || $scope.round == 17){
						v = Math.floor(Math.random() * 4)+1;
						bgmSrc = '/sound/Pokerworld/game_r4' + v + '.mp3'
					} else if ($scope.round == 11 || ($scope.round == 14 && $scope.totalRound == 15) || $scope.round == 19){
						//v = Math.floor(Math.random() * 2)+1;
						//bgmSrc = '/sound/Pokerworld/game_r11' + v + '.mp3'
						bgmSrc = '/sound/Pokerworld/game_r19.mp3'
					}
					
					else {
						v = Math.floor(Math.random() * 3)+1;
						bgmSrc = '/sound/Pokerworld/game' + v + '.mp3'
					}
				}
				
				$scope.bgm.src = bgmSrc
			}
			
		}
		$scope.bgm.addEventListener("ended", function() {
			randomizeBGM()
			$scope.bgm.play();
		}, true);
		$scope.playBGM = function(){
			if ($scope.playedBGM == false){
				$scope.playedBGM = true;
				randomizeBGM()
		        $scope.bgm.play();
		        $scope.muteButton = "关闭BGM"
			}
		}
		$scope.mute = function(){
			if ($scope.playedBGM == true){
				if ($scope.playBGM == false){
					$scope.bgm.play();
					$scope.playBGM = true;
					$scope.muteButton = "关闭BGM"
				} else {
					$scope.bgm.pause();
					$scope.playBGM = false;
					$scope.muteButton = "播放BGM"
				}
			}
		}
		$scope.muteSE = function(){
			if ($scope.playSE){
				$scope.playSE = false;
				$scope.muteSEButton = "播放音效"
			} else {
				$scope.playSE = true;
				$scope.muteSEButton = "关闭音效"
			}
		}
		$scope.playWinLoseBGM = function(f){
			if ($scope.playBGM){
				if (f){
					$scope.bgm.src = '/sound/Pokerworld/game_win.mp3'
					$scope.bgm.play();
				} else {
					$scope.bgm.src = '/sound/Pokerworld/game_lose.mp3'
					$scope.bgm.play();
				}
			} 
		}
		
		$scope.showScoreTable = false;
		$scope.tableText = ["轮次","下注","实际","加分","分数"]
		$scope.scoreTableSwitch = function(f){
			playClickSE()
			$scope.showScoreTable = f;
		}
		
		$scope.highlightStyle = function(f){
			if (f){
				return {
					"background-color": "goldenrod"
				}
			} else{
				return {}
			}
		}
		
		$scope.changeBid = function(x){
			playClickSE()
			$scope.numTrick = $scope.numTrick+x
			if ($scope.numTrick<-1000) $scope.numTrick = 0
			if ($scope.numTrick>1000) $scope.numTrick = $scope.hand.length
		}
		
		$scope.bidWizard = function(){
			playBidSE()
			var data = {"bid": $scope.numTrick}
			$http({url: "/pokerworld/wizard/bid", method: "PUT", params: data}).then(function(response){
				$scope.allRefresh()
			});
		}
		
		$scope.cardOptions = []
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
			} else if ($scope.gameMode == $scope.WIZARD && $scope.playable[x] == 1){
				if ($scope.status == $scope.PLAYCARDS || $scope.status == $scope.CONFIRMROUNDTURN && $scope.myIndex == $scope.curPlayer){
					playClickSE()
					if (x>=0 && x<$scope.hand.length){
						if ($scope.chosenCard == x){
							$scope.hand[$scope.chosenCard].cstyle["margin-top"] = "0px"
							$scope.chosenCard = -1;
							$scope.cardOptions = []
						} else {
							if ($scope.chosenCard != -1){
								$scope.hand[$scope.chosenCard].cstyle["margin-top"] = "0px"
							}
							$scope.chosenCard = x;
							$scope.hand[$scope.chosenCard].cstyle["margin-top"] = "-30px"
							
							//TODO: add selections here
							c = $scope.hand[$scope.chosenCard]
							$scope.cardOptions = []
							$scope.selectedCardOption = -1;
							if (c.suit == "ME"){
								$scope.cardOptions = ["巫师", "小丑"];
							} else if (c.suit == "?"){
								$scope.cardOptions = ["\u2660", "\u2665", "\u2663", "\u2666"];
							}
							
						}
						
					}
				} else if ($scope.status == $scope.CIRCUSPASS && $scope.gamedata.myCircusIndex == -1){
					playClickSE()
					if (x>=0 && x<$scope.hand.length){
						if ($scope.chosenCard == x){
							$scope.hand[$scope.chosenCard].cstyle["margin-top"] = "0px"
							$scope.chosenCard = -1;
							$scope.cardOptions = []
						} else {
							if ($scope.chosenCard != -1){
								$scope.hand[$scope.chosenCard].cstyle["margin-top"] = "0px"
							}
							$scope.chosenCard = x;
							$scope.hand[$scope.chosenCard].cstyle["margin-top"] = "-30px"
						}
						
					}
					$scope.selectedCardOption = -1;
					//alert($scope.chosenCard)
				}
			} else if ($scope.gameMode == $scope.HEARTS){
				if ($scope.phase == $scope.HEARTSPASSSELECT){
					if (x>=0 && x<$scope.hand.length){
						$scope.hand[x].chosen = 1-$scope.hand[x].chosen
						if ($scope.hand[x].chosen != 1){
							$scope.hand[x].cstyle["margin-top"] = "0px"
						} else {
							$scope.hand[x].cstyle["margin-top"] = "-30px"
						}
						
						$scope.numCardsChosen = 0;
						for (i=0;i<$scope.hand.length;i++){
							$scope.numCardsChosen = $scope.hand[i].chosen + $scope.numCardsChosen
						}
					}
				}
			}
			
		}
		
		$scope.cardOptionStyle = function(y){
			x = $scope.cardOptions[y];
			if (x == "\u2660" || x == "\u2663") {
				return {"color":"black"}
			}
			if (x == "\u2665" || x == "\u2666") {
				return {"color":"red"}
			}
			return {}
		}
		
		$scope.selectCardOption = function(x){
			$scope.selectedCardOption = x;
		}
		
		$scope.showPlayButton = function(){
			if ($scope.curPlayer != $scope.myIndex) return false
			if ($scope.chosenCard == -1) return false
			if ($scope.cardOptions.length > 0 && $scope.selectedCardOption == -1 && $scope.status != $scope.CIRCUSPASS) return false;
			return true;
		}
		
		$scope.selectedStationOption = 0
		$scope.selectStationOption = function(x){
			$scope.selectedStationOption = x
		}
		$scope.submitStationOption = function(){
			if ($scope.status != $scope.STATIONCHOOSE) return;
			if ($scope.selectedStationOption == 1 || $scope.selectedStationOption == -1){
				var data = {"option": $scope.selectedStationOption}
				$http({url: "/pokerworld/selectStationOption", method: "POST", params: data}).then(function(response){
					$scope.allRefresh()
				});
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
			playPlaySE()
			var playedIndex = []
			if ($scope.gameMode == $scope.SFSJ){
				for (i=0;i<$scope.hand.length;i++){
					if ($scope.hand[i].chosen == 1){
						playedIndex.push(i);
					}
				}
			} else if ($scope.gameMode == $scope.WIZARD){
				if ($scope.chosenCard != -1) {
					playedIndex.push($scope.chosenCard);
				} else {
					return;
				}
			}
			
			if (playedIndex.length > 0){
				var data = {"playedIndex": playedIndex, "option": $scope.selectedCardOption}
				//alert(playedIndex)
				$http({url: "/pokerworld/playcards", method: "POST", params: data}).then(function(response){
					$scope.chosenCard = -1;
					$scope.cardOptions = []
					$scope.allRefresh()
				});
			}	
		}
		
		// Begin HEARTS functions
		$scope.pass = function(){
			if ($scope.gameMode == $scope.HEARTS && $scope.status == $scope.HEARTSPASS){
				playPlaySE()
				var passedIndex = []
				for (i=0;i<$scope.hand.length;i++){
					if ($scope.hand[i].chosen == 1){
						passedIndex.push(i);
					}
				}
				if (passedIndex.length == 3){
					var data = {"passedIndex": passedIndex}
					$http({url: "/pokerworld/passcards", method: "POST", params: data}).then(function(response){
						$scope.allRefresh()
					});
				}
			}
		}
		
		$scope.confirmPass = function(){
			if ($scope.gameMode == $scope.HEARTS && $scope.status == $scope.HEARTSPASS && $scope.phase == $scope.HEARTSRECEIVE){
				playPlaySE()
				$http({url: "/pokerworld/confirmpasscard", method: "POST"}).then(function(response){
					$scope.allRefresh()
				});
			}
		}
		
		// End HEARTS functions
		
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
		
		translateRawCard = function(raw, p, f, skins){
			var card = {}
			var r = raw.substring(0,1);
			var s = raw.substring(1,2);
			var c = "black";
			if (r == "T") r = "10";
			card["suitRaw"] = s;
			var ts = s
			var cstyle = {
				"margin-top": "0px"
			}
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
			if (s == 'u'){
				s = "?";
				c = "navy";
				
			}
			
			if (r == 'N'){
				cstyle["background-image"] = 'url(/image/Pokerworld/station.png)'
				cstyle["background-size"] = 'cover'
			} else if (r == 'S'){
				cstyle["background-image"] = 'url(/image/Pokerworld/ballroom.png)'
				cstyle["background-size"] = 'cover'
			}
			
			customSkin = null;
			if (raw == "WZ" || raw == "Wz" || raw == "wZ" || raw == "wz"){
				r = "wizard"
				s = raw;
				c = "blue";
				
				if (raw == "WZ" && skins[0] != -1){
					customSkin = {
						'background-image': 'url(/image/Pokerworld/Skins/' + skins[0].toString() + '.png)'
					}
				}
				if (raw == "Wz" && skins[1] != -1){
					customSkin = {
						'background-image': 'url(/image/Pokerworld/Skins/' + skins[1].toString() + '.png)'
					}
				}
				if (raw == "wZ" && skins[2] != -1){
					customSkin = {
						'background-image': 'url(/image/Pokerworld/Skins/' + skins[2].toString() + '.png)'
					}
				}
				if (raw == "wz" && skins[3] != -1){
					customSkin = {
						'background-image': 'url(/image/Pokerworld/Skins/' + skins[3].toString() + '.png)'
					}
				}
			}
			
			if (raw == "JE" || raw == "Je" || raw == "jE" || raw == "je"){
				r = "jester"
				s = raw;
				c = "green";
				
				if (raw == "JE" && skins[4] != -1){
					customSkin = {
						'background-image': 'url(/image/Pokerworld/Skins/' + skins[4].toString() + '.png)'
					}
				}
				if (raw == "Je" && skins[5] != -1){
					customSkin = {
						'background-image': 'url(/image/Pokerworld/Skins/' + skins[5].toString() + '.png)'
					}
				}
				if (raw == "jE" && skins[6] != -1){
					customSkin = {
						'background-image': 'url(/image/Pokerworld/Skins/' + skins[6].toString() + '.png)'
					}
				}
				if (raw == "je" && skins[7] != -1){
					customSkin = {
						'background-image': 'url(/image/Pokerworld/Skins/' + skins[7].toString() + '.png)'
					}
				}
			}
			
			if (raw == "BM" || raw == "Bm" || raw == "bM" || raw == "bm"){
				r = "bomb"
				s = raw;
				c = "darkred";
				
				if (skins[8] != -1){
					customSkin = {
						'background-image': 'url(/image/Pokerworld/Skins/' + skins[8].toString() + '.png)'
					}
				}
			}
			
			if (raw == "DR" || raw == "Dr" || raw == "dR" || raw == "dr"){
				r = "dragon"
				s = raw;
				c = "purple";
				
				if (skins[9] != -1){
					customSkin = {
						'background-image': 'url(/image/Pokerworld/Skins/' + skins[9].toString() + '.png)'
					}
				}
			}
			
			if (raw == "FR" || raw == "Fr" || raw == "fR" || raw == "fr"){
				r = "fairy"
				s = raw;
				c = "pink";
				
				if (skins[10] != -1){
					customSkin = {
						'background-image': 'url(/image/Pokerworld/Skins/' + skins[10].toString() + '.png)'
					}
				}
			}
			
			if (raw == "ME" || raw == "Me" || raw == "mE" || raw == "me"){
				r = "merlin"
				s = raw;
				c = "bg";
				if (raw == "Me") c = "blue";
				if (raw == "mE") c = "green";
				
				if (skins[11] != -1){
					customSkin = {
						'background-image': 'url(/image/Pokerworld/Skins/' + skins[11].toString() + '.png)'
					}
				}
			}
			
			card["rank"] = r
			card["suit"] = s
			card["color"] = c
			card["chosen"] = 0;
			card["cstyle"] = cstyle
			card["custom"] = customSkin

			if (ts == $scope.dominantSuit){
				card["cstyle"]["background-color"] = "palegoldenrod"
			}
			
			if ($scope.status == $scope.SCORING && f){
				if (ts == $scope.dominantSuitLastRound){
					card["cstyle"]["background-color"] = "olive"
				} else if (raw == "WZ" || raw == "Wz" || raw == "wZ" || raw == "wz"){
					card["cstyle"]["background-color"] = "cadetblue"
				} else if (raw == "JE" || raw == "Je" || raw == "jE" || raw == "je"){
					card["cstyle"]["background-color"] = "mediumturquoise"
				}
				
				else {
					card["cstyle"]["background-color"] = "grey"
				}
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
			var i = 0;
			chosen = []
			if ($scope.gameMode == $scope.HEARTS){
				for (i=0;i<$scope.hand.length;i++){
					if ($scope.hand[i].chosen == null){
						chosen.push(0);
					} else if ($scope.hand[i].chosen == 1){
						chosen.push(1);
					} else {
						chosen.push(0);
					}
				}
			}
			
			$scope.hand = [];
			i=0;
			$scope.numCardsChosen = 0;
			while (i<$scope.myCards.length){
				var rawCard = $scope.myCards.substring(i,i+2);
				var c = translateRawCard(rawCard, $scope.playable[i/2], false, $scope.gamedata.myChosenSkins)
				c["cstyle"]["margin-top"] = "0px";
				// hearts pass cards handle
				if ($scope.gameMode == $scope.HEARTS && $scope.status == 301 && ($scope.phase == 302 ||$scope.phase == 303) ){
					var f = false;
					for (j=0;j<$scope.gamedata.myPlayedIndex.length;j++){
						if ($scope.gamedata.myPlayedIndex[j] == i/2){
							c["cstyle"]["margin-top"] = "-30px";
						}
					}
				}
				if ($scope.gameMode == $scope.HEARTS && $scope.phase == 301){
					if (i/2 < chosen.length){
						z = chosen[i/2]
						c.chosen = z;
						$scope.numCardsChosen = $scope.numCardsChosen+z;
						if (z == 1){
							c["cstyle"]["margin-top"] = "-30px";
						}
					} else {
						c.chosen = 0;
					}
				}
				$scope.hand.push(c);
				i=i+2;
			}
			for (i=0;i<$scope.players.length;i++){
				var j = 0;
				var played = [];
				while (j<$scope.players[i].playedCards.length){
					var rawCard = $scope.players[i].playedCards.substring(j,j+2);
					played.push(translateRawCard(rawCard, 1, true, $scope.players[i].chosenSkins));
					j = j+2;
				}
				$scope.players[i]["played"] = played
			}
			$scope.trump = []
			if ($scope.dominantCard.length == 2){
				var trumpCard =translateRawCard($scope.dominantCard,1, false, $scope.gamedata.myChosenSkins)
				$scope.trump.push(trumpCard)
			}
		}
		
		setPlayerInfoDisplay = function(){
			var i=0;
			for (i=0;i<$scope.players.length;i++){
				var display = "";
				var displayClass = "black";
				var n = $scope.players[i].scores.length
				if (n == 0){
					display = "0";
					displayClass = "black";
				} else if (n == 1){
					display = "" + $scope.players[i].scores[0];
					if ($scope.players[i].scores[n-1] >= 0){
						display = display + "(+" + $scope.players[i].scores[n-1] + ")";
						displayClass = "darkgreen";
					} else {
						display = display + "(" + $scope.players[i].scores[n-1] + ")";
						displayClass = "red";
					}
				} else {
					display = "" + $scope.players[i].scores[n-1];
					var t = $scope.players[i].scores[n-1] - $scope.players[i].scores[n-2]
					if (t >= 0){
						display = display + "(+" + t + ")";
						displayClass = "darkgreen";
					} else {
						display = display + "(" + t + ")";
						displayClass = "red";
					}
				}
				$scope.players[i].scoreDisplay = display;
				$scope.players[i].scoreDisplayClass = displayClass;
				
				
				
				if ($scope.status == $scope.SCORING){
					$scope.players[i].bidDisplay = "" + $scope.players[i].bids[$scope.players[i].bids.length-1]
					$scope.players[i].actualDisplay = "" + $scope.players[i].actuals[$scope.players[i].actuals.length-1]
					$scope.players[i].fiveTenDisplay = "" + $scope.players[i].bonuses[$scope.players[i].bonuses.length-1]
				} else if ($scope.players[i].bids.length == $scope.round){
					$scope.players[i].bidDisplay = "" + $scope.players[i].bids[$scope.players[i].bids.length-1]
					$scope.players[i].actualDisplay = "" + $scope.players[i].actuals[$scope.players[i].actuals.length-1]
					$scope.players[i].fiveTenDisplay = "" + $scope.players[i].bonuses[$scope.players[i].bonuses.length-1]
				} else {
					$scope.players[i].bidDisplay = "-"
					$scope.players[i].actualDisplay = "0"
					$scope.players[i].fiveTenDisplay = "0"
				}
			}
		}
		
		setRoundDisplay = function(){
			if ($scope.status == $scope.PREENDGAME){
				$scope.roundDisplay = "游戏结束"
			} else {
				$scope.roundDisplay = "轮次 " + $scope.round;
				if ($scope.status == $scope.SCORING){
					t = $scope.round-1
					$scope.roundDisplay = "轮次 " + t + " 结束";
				}
				$scope.roundDisplay = $scope.roundDisplay + "（共" + $scope.totalRounds + "轮）"
			}	
			if ($scope.biggestRank == 1){
				$scope.biggestRankDisplay = "A"
			} else if ($scope.biggestRank == 2){
				$scope.biggestRankDisplay = "2"
			} if ($scope.biggestRank == 13){
				$scope.biggestRankDisplay = "K"
			}
			
		}
		
		setTrickInfo = function(){
			$scope.trickMsg = "吃了一墩，下回合第一个出牌。";
			var f = false;
			for (i=0;i<$scope.players.length;i++){
				var j = 0;
				while (j<$scope.players[i].playedCards.length){
					var rawCard = $scope.players[i].playedCards.substring(j,j+2);
					if (rawCard == "BM") f = true
					j = j+2;
				}
			}
			if (f){
				$scope.trickMsg = "如吃一墩。因为被炸了，所以没吃到，但是下回合依然第一个出牌。";
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
				prevStatus = $scope.status;
				
				$scope.gamedata = response.data
				$scope.id = response.data.id
				$scope.status = response.data.status
				$scope.phase = response.data.phase
				$scope.gameMode = response.data.gameMode
				$scope.players = response.data.players
				$scope.lord = response.data.lord
				$scope.myCards = response.data.myCards
				$scope.round = response.data.round
				$scope.totalRounds = response.data.totalRounds;
				$scope.numTrick = 0
				$scope.distributeSequence = response.data.sequence;
				$scope.dominantRank = response.data.dominantRank;
				$scope.dominantSuit = response.data.dominantSuit;
				$scope.dominantCard = response.data.dominantCard;
				$scope.currentSuit = response.data.currentSuit;
				$scope.numDominant = response.data.numDominant;
				$scope.curClaimedPlayer = response.data.curClaimedPlayer;
				$scope.dominantSuitDisplay = "";
				$scope.dominantSuitDisplayClass = "";
				$scope.myIndex = response.data.myIndex;
				$scope.numPlayed = response.data.numPlay;
				$scope.firstPlayer = response.data.firstPlayer;
				$scope.curPlayer = response.data.curPlayer;
				$scope.winPlayer = response.data.winPlayer;
				$scope.confirmed = response.data.confirmed;
				$scope.confirmedNextTurn = response.data.confirmedNextTurn;
				$scope.attackerPointsGained = response.data.attackerPointsGained;
				$scope.chosenCard = -1;
				$scope.playable = response.data.playable
				$scope.biggestRank = response.data.biggestRank
				$scope.myEndGameRewards = response.data.myEndGameRewards
				$scope.useFiveTenBonus = response.data.fiveTenBonus
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
				setPlayerInfoDisplay()
				setRoundDisplay()
				setTrickInfo()
				
				if ($scope.gameMode == $scope.WIZARD){
					if (prevStatus != null && prevStatus != $scope.status && $scope.status == $scope.SCORING){
						myBidThisTurn = $scope.players[$scope.myIndex].bidDisplay
						myActualThisTurn = $scope.players[$scope.myIndex].actualDisplay
						$scope.playWinLoseBGM(myBidThisTurn == myActualThisTurn)
					}
				} else if ($scope.gameMode == $scope.HEARTS){
					
					var x = ($scope.myIndex + $scope.round) % $scope.players.length
					$scope.passTo = $scope.players[x].name
					
					//$scope.numCardsChosen = 0;
				}
				
				if ($scope.status == $scope.PREENDGAME){
					setEndGameInfo()
				}
				
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
			if (message == 'refresh' || message == 'start' || message == 'dismiss'){
				$scope.getBoard();
			}
			
		});
		
		$scope.allRefresh = function(){
			var msg = "refresh";
	        ws.send(msg);
		}
		
		
}]);
