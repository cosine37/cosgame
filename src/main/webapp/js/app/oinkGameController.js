var setUrl = function(d){
	hearder = "https://";
	server = "localhost:8080";
	return header + server + d;
}

var app = angular.module("oinkGameApp", ["ngWebSocket"]);
app.controller("oinkGameCtrl", ['$scope', '$window', '$http', '$document', '$timeout', '$websocket',
	function($scope, $window, $http, $document, $timeout, $websocket){
		var ws = $websocket("wss://" + $window.location.host + "/oink/boardrefresh");
		ws.onError(function(event) {
		});
		
		ws.onClose(function(event) {
		});
		
		ws.onOpen(function() {
		});
	
		$scope.STARTUPS = 1;
		$scope.GROVE = 2;
		$scope.POPE = 6;
		$scope.WEST = 4;
		$scope.FLIP7 = 7;
		
		$scope.INGAME = 1
		$scope.ROUNDEND = 2
		$scope.ENDGAME = 3
		$scope.showEndRoundInfo = false;
		
		$scope.chosenCard = -1;
		$scope.chosenRoles = [0,0,0]
		$scope.chosenPlayer = -1;
		$scope.oldKey = 0
		$scope.alive = true;
		$scope.firstRefresh = true;
		$scope.showRuleGrove = false;
		
		$scope.round = -1;
		
		$scope.playingBGM = false
		$scope.bgm = new Audio();
		$scope.volume = 0.5;
		$scope.updateVolume = function() {
			$scope.bgm.volume = $scope.volume;
		};
		randomizeBGM = function(){
			if ($scope.game == $scope.STARTUPS){
				v = Math.floor(Math.random() * 3)+1;
				bgmSrc = '/sound/Oink/default' + v + '.mp3'
				$scope.bgm.src = bgmSrc
			} else if ($scope.game == $scope.GROVE){
				if ($scope.round == 1){
					v = Math.floor(Math.random() * 3)+1;
					bgmSrc = '/sound/Oink/grove1' + v + '.mp3'
					$scope.bgm.src = bgmSrc
				} else if ($scope.round < 4){
					v = Math.floor(Math.random() * 2)+1;
					bgmSrc = '/sound/Oink/grove2' + v + '.mp3'
					$scope.bgm.src = bgmSrc
				} else if ($scope.round < 7){
					v = Math.floor(Math.random() * 3)+1;
					bgmSrc = '/sound/Oink/grove3' + v + '.mp3'
					$scope.bgm.src = bgmSrc
				} else {
					v = Math.floor(Math.random() * 2)+1;
					bgmSrc = '/sound/Oink/grove4' + v + '.mp3'
					$scope.bgm.src = bgmSrc
				}
				
			} else if ($scope.game == $scope.POPE){
				if ($scope.status == $scope.ENDGAME){
					$scope.playWinLoseBGM(0);
				} else {
					if ($scope.round == 1){
						bgmSrc = '/sound/Oink/pope1.mp3'
						$scope.bgm.src = bgmSrc
					} else if ($scope.bgm.src == '/sound/Oink/pope2.mp3'){
						bgmSrc = '/sound/Oink/pope3.mp3'
						$scope.bgm.src = bgmSrc
					} else if ($scope.round > 9){
						bgmSrc = '/sound/Oink/pope4.mp3'
						$scope.bgm.src = bgmSrc
					} else {
						v = Math.floor(Math.random() * 2)+2;
						bgmSrc = '/sound/Oink/pope' + v + '.mp3'
						$scope.bgm.src = bgmSrc
					}
				}
			} else if ($scope.game == $scope.WEST){
				if ($scope.round == 1){
					bgmSrc = '/sound/Oink/west1.mp3'
					$scope.bgm.src = bgmSrc
				} else if ($scope.round < 4){
					v = Math.floor(Math.random() * 3)+2;
					bgmSrc = '/sound/Oink/west' + v + '.mp3'
					$scope.bgm.src = bgmSrc
				} else if ($scope.round < 6){
					v = Math.floor(Math.random() * 4)+2;
					bgmSrc = '/sound/Oink/west' + v + '.mp3'
					$scope.bgm.src = bgmSrc
				} else {
					bgmSrc = '/sound/Oink/west6.mp3'
					$scope.bgm.src = bgmSrc
				}
			}
			$scope.bgm.volume = $scope.volume;
		}
		$scope.bgm.addEventListener("ended", function() {
			randomizeBGM()
			$scope.bgm.play();
		}, true);
		
		$scope.playWinLoseBGM = function(x){
			if ($scope.game == $scope.STARTUPS){
				if (x == 1){
					$scope.bgm.src = '/sound/Oink/game_win.mp3'
					$scope.bgm.play();
				} else if (x == 2) {
					$scope.bgm.src = '/sound/Oink/game_tie.mp3'
					$scope.bgm.play();
				} else {
					$scope.bgm.src = '/sound/Oink/game_lose.mp3'
					$scope.bgm.play();
				}
			} else if ($scope.game == $scope.GROVE){
				$scope.bgm.src = '/sound/Oink/lemon.mp3'
				$scope.bgm.play();
			} else if ($scope.game == $scope.POPE){
				$scope.bgm.src = '/sound/Oink/flame.mp3'
				$scope.bgm.play();
			} else if ($scope.game == $scope.WEST){
				$scope.bgm.src = '/sound/Oink/west7.mp3'
				$scope.bgm.play();
			}
			
		}
		
		$scope.playClickSE = function(){
			var audio = new Audio("/sound/Oink/click.wav")
			audio.volume = $scope.volume;
			audio.play();
		}
		
		$scope.playRevealSE = function(){
			v = Math.floor(Math.random() * 3)+1;
			bgmSrc = '/sound/Oink/reveal' + v + '.mp3'
			var audio = new Audio(bgmSrc)
			audio.volume = $scope.volume;
			audio.play();
		}
		
		$scope.playMsgSE = function(){
			var audio = new Audio( '/sound/Oink/msg.mp3')
			audio.volume = $scope.volume;
			audio.play();
		}
		
		$scope.playPopeReceiveKeySE = function(){
			var audio = new Audio( '/sound/Oink/pope5.mp3')
			audio.volume = $scope.volume;
			audio.play();
		}
		
		$scope.playDownSE = function(){
			v = Math.floor(Math.random() * 2)+6;
			bgmSrc = '/sound/Oink/pope' + v + '.mp3'
			var audio = new Audio(bgmSrc)
			audio.volume = $scope.volume;
			audio.play();
		}
	
		$scope.goto = function(d){
			var x = "https://" + $window.location.host;
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
		
		$scope.kick = function(x){
			var data = {"index" : x}
			$http({url: "/oink/kick", method: "POST", params: data}).then(function(response){
				ws.send("kick");
			});
		}
		
		$scope.dismiss = function(){
			$http.post("/oink/dismiss").then(function(response){
				ws.send("dismiss");
			});
		}
		
		adjustLogs = function(logElementId){
			var logcontent = document.getElementById(logElementId);
			logcontent.scrollTop = logcontent.scrollHeight;
		}
		
		$scope.draw = function(){
			$scope.playClickSE()
			if ($scope.game == $scope.STARTUPS){
				$http.put("/oink/startups/draw").then(function(response){
					ws.send("refresh");
				});
			} else if ($scope.game == $scope.WEST){
				$http.put("/oink/west/draw").then(function(response){
					ws.send("refresh");
				});
			}
			
		}
		
		$scope.discard = function(){
			$scope.playClickSE()
			if ($scope.chosenCard != -1){
				if ($scope.game == $scope.STARTUPS){
					var data = {"cardIndex" : $scope.chosenCard}
					$http({url: "/oink/startups/discard", method: "PUT", params: data}).then(function(response){
						$scope.chosenCard = -1
						ws.send("refresh");
					});
				} else if ($scope.game == $scope.WEST){
					var data = {"cardIndex" : $scope.chosenCard}
					$http({url: "/oink/west/discard", method: "PUT", params: data}).then(function(response){
						$scope.chosenCard = -1
						ws.send("refresh");
					});
				}
			}
		}
		
		$scope.play = function(){
			$scope.playClickSE()
			if ($scope.game == $scope.STARTUPS){
				if ($scope.chosenCard != -1){
					var data = {"cardIndex" : $scope.chosenCard}
					$scope.chosenCard = -1
					$http({url: "/oink/startups/play", method: "PUT", params: data}).then(function(response){
						ws.send("refresh");
					});
				}
			} else if ($scope.game == $scope.GROVE){
				$scope.playClickSE()
				if ($scope.phase == 1){
					var t = $scope.chosenRoles[0] + $scope.chosenRoles[1] + $scope.chosenRoles[2]
					if (t == 2){
						var k = $scope.chosenRoles[0]*100 + $scope.chosenRoles[1]*10 + $scope.chosenRoles[2]
						var data = {"cardIndex" : k}
						$scope.chosenRoles = [0,0,0]
						$http({url: "/oink/grove/check", method: "PUT", params: data}).then(function(response){
							ws.send("refresh");
						});			
					}
				} else if ($scope.phase == 2){
					var data = {"cardIndex" : $scope.chosenCard}
					$scope.chosenCard = -1
					$http({url: "/oink/grove/accuse", method: "PUT", params: data}).then(function(response){
						ws.send("refresh");
					});
				}
				
			} else if ($scope.game == $scope.POPE){
				if ($scope.chosenCard != -1){
					var flag = true;
					if ($scope.chosenPlayer != -1){
						if ($scope.players[$scope.chosenPlayer].protect){
							if (confirm("目标玩家被守卫保护，对其打出的牌会无效，是否依然对其打出？") == true) {
								flag = true;
							} else {
								flag = false;
							}
						}
					}
					
					if (flag == false) return;
					var data = {"cardIndex" : $scope.chosenCard, "target": $scope.chosenPlayer}
					$scope.chosenCard = -1
					$scope.chosenPlayer = -1
					$http({url: "/oink/pope/play", method: "PUT", params: data}).then(function(response){
						ws.send("refresh");
					});
				}
			} else if ($scope.game == $scope.WEST){
				$http({url: "/oink/west/exchange", method: "PUT", params: data}).then(function(response){
					ws.send("refresh");
				});
			}
		}
		
		$scope.take = function(x){
			$scope.playClickSE()
			if (x != -1){
				if ($scope.game == $scope.STARTUPS){
					var data = {"cardIndex" : x}
					$scope.chosenCard = -1
					$http({url: "/oink/startups/take", method: "PUT", params: data}).then(function(response){
						ws.send("refresh");
					});
				} else if ($scope.game == $scope.WEST){
					if (x == 1){
						$http({url: "/oink/west/bid", method: "PUT", params: data}).then(function(response){
							ws.send("refresh");
						});
					} else if (x == 0){
						$http({url: "/oink/west/retreat", method: "PUT", params: data}).then(function(response){
							ws.send("refresh");
						});
					}
					
				}
			}
		}
		
		$scope.clickCard = function(x){
			if ($scope.game == $scope.STARTUPS){
				if ($scope.phase != 2) return;
				$scope.playClickSE()
				if ($scope.chosenCard == x){
					$scope.hand[$scope.chosenCard].cstyle["margin-top"] = "0px";
					$scope.chosenCard = -1
				} else {
					if ($scope.chosenCard != -1){
						$scope.hand[$scope.chosenCard].cstyle["margin-top"] = "0px";
					}
					$scope.chosenCard = x
					$scope.hand[$scope.chosenCard].cstyle["margin-top"] = "-40px";
				}
			} else if ($scope.game == $scope.GROVE){
				if ($scope.phase == 1){
					var t = $scope.chosenRoles[0] + $scope.chosenRoles[1] + $scope.chosenRoles[2]
					if ($scope.chosenRoles[x] == 1){
						$scope.chosenRoles[x] = 0
						$scope.playClickSE()
					} else {
						if (t<2){
							$scope.chosenRoles[x] = 1
							$scope.playClickSE()
						}
					}
				} else if ($scope.phase == 2){
					$scope.playClickSE()
					if ($scope.chosenCard == x) $scope.chosenCard = -1; else $scope.chosenCard = x
				}
				
			} else if ($scope.game == $scope.POPE){
				if ($scope.phase == 1 || $scope.phase == 3){
					$scope.playClickSE();
					if ($scope.chosenCard == x){
						$scope.hand[$scope.chosenCard].cstyle["margin-top"] = "0px";
						$scope.chosenCard = -1
					} else {
						if ($scope.chosenCard != -1){
							$scope.hand[$scope.chosenCard].cstyle["margin-top"] = "0px";
						}
						$scope.chosenCard = x
						$scope.hand[$scope.chosenCard].cstyle["margin-top"] = "-40px";
					}
				}
			} else if ($scope.game == $scope.WEST){
				if ($scope.phase == 2){
					$scope.playClickSE();
					if ($scope.chosenCard == x){
						$scope.hand[$scope.chosenCard].cstyle["margin-top"] = "0px";
						$scope.chosenCard = -1
					} else {
						if ($scope.chosenCard != -1){
							$scope.hand[$scope.chosenCard].cstyle["margin-top"] = "0px";
						}
						$scope.chosenCard = x
						$scope.hand[$scope.chosenCard].cstyle["margin-top"] = "-40px";
					}
				}
			}
		}
		
		$scope.confirmNextRound = function(){
			if ($scope.game == $scope.STARTUPS){
				$http.put("/oink/startups/confirmnextround").then(function(response){
					ws.send("refresh");
				});
			} else if ($scope.game == $scope.GROVE){
				$http.put("/oink/grove/confirmnextround").then(function(response){
					ws.send("refresh");
				});
			} else if ($scope.game == $scope.POPE){
				$http.put("/oink/pope/confirmnextround").then(function(response){
					ws.send("refresh");
				});
			} else if ($scope.game == $scope.WEST){
				$http.put("/oink/west/confirmnextround").then(function(response){
					ws.send("refresh");
				});
			}
			
		}
		
		// STARTUPS only functions
		$scope.startupsScoreStyle = function(x){
			var style = {}
			if (x == 2){
				style = {"font-weight": "bold", "color": "darkgreen"}
			}
			if (x == -1){
				style = {"color": "red"}
			}
			style["width"] = "25px"
			style["font-size"] = "18px";
			return style;
		}
		$scope.flipEndRoundPage = function(x){
			$scope.shownEndRoundPage = $scope.shownEndRoundPage + x;
		}
		// End STARTUPS only functions
		
		// GROVE only functions
		$scope.showSubmitSuspectButton = function(){
			var t = $scope.chosenRoles[0] + $scope.chosenRoles[1] + $scope.chosenRoles[2]
			if (t == 2) return true; else return false;
		}
		// End GROVE only functions
		
		// POPE only functions
		setPlayerPlayStyle = function(){
			var i,j
			for (i=0;i<$scope.players.length;i++){
				var n = $scope.players[i].play.length;
				for (j=0;j<n;j++){
					var x = "0px";
					if (j>0){
						if (n == 4){
							x = "-20px"
						} else if (n == 5){
							x = "-70px"
						} else if (n == 6){
							x = "-100px"
						} else if (n == 7){
							x = "-120px"
						} else if (n == 8){
							x = "-130px"
						} else if (n == 9){
							x = "-140px"
						} else if (n == 10){
							x = "-150px"
						}
					}
					$scope.players[i].play[j].cstyle["margin-left"] = x
				}
			}
		}
		
		$scope.playerBgColorPope = function(i){
			var ans = {"background-color": "rgba(211,211,211,0.6)"};
			if ($scope.players == null) return ans;
			if (i<$scope.players.length){
				if ($scope.players[i].active == true){
					if (i == $scope.gamedata.curPlayer){
						ans["background-color"] = "rgba(0,191,255,0.6)";
					} else {
						ans["background-color"] = "rgba(255,255,224,0.6)";
					}
					
				}
			}
			return ans;
		}
		
		$scope.showPlayCardButton = function(){
			if ($scope.chosenCard<0){
				return false;
			} else if ($scope.hand[$scope.chosenCard].canPlay == false){
				return false;
			}
			
			else {
				cardType = $scope.hand[$scope.chosenCard].type
				if (cardType == 0){
					return true;
				} else if (cardType == 1 || cardType == 2){
					if ($scope.chosenPlayer<0){
						return false;
					} else {
						return true;
					}
				}
				
				else {
					return false;
				}
			}
		}
		
		$scope.showChecked = function(i,f){
			if ($scope.phase != 1){
				return false;
			} else if ($scope.chosenCard<0){
				return false;
			} else if ($scope.players[i].active == false){
				return false;
			} else if ($scope.hand[$scope.chosenCard].canPlay == false){
				return false;
			}
			
			else {
				cardType = $scope.hand[$scope.chosenCard].type
				
				if (cardType == 0){
					return false;
				} else if (cardType == 1 || cardType == 2){
					if (i==$scope.gamedata.myIndex && cardType == 1){
						return false;
					} else {
						
						if ($scope.chosenPlayer == i){
							return f;
						} else {
							return (!f);
						}
						
					}
				}
			}
		}
		
		$scope.clickPlayer = function(i){
			$scope.playClickSE();
			cardType = $scope.hand[$scope.chosenCard].type
			
			if (cardType == 1|| cardType == 2){
				if ($scope.chosenPlayer == i){
					$scope.chosenPlayer = -1
				} else {
					$scope.chosenPlayer = i;
				}
			}
		}
		
		$scope.confirmTargeted = function(){
			$scope.playClickSE();
			if ($scope.phase == 9){
				$http.put("/oink/pope/confirmtargeted").then(function(response){
					ws.send("refresh");
				});
			}
		}
		
		$scope.popeResolve = function(x){
			$scope.playClickSE();
			$scope.chosenCard = -1;
			var data = {"val": x}
			$http({url: "/oink/pope/resolve", method: "PUT", params: data}).then(function(response){
				ws.send("refresh");
			});
		}
		
		$scope.showReturnCardButton = function(){
			if ($scope.chosenCard<0){
				return false;
			} else {
				return true;
			}
		}
		// End POPE only functions
		
		$scope.getBoard = function(){
			$http.get('/oink/getboard').then(function(response){
				if (response.data.id == "NE"){
					alert("该游戏已解散");
					$scope.goto('oink');
					return;
				}
				
				$scope.game = response.data.game
				var oldStatus = $scope.status
				$scope.status = response.data.status
				
				$scope.playerNames = response.data.playerNames
				$scope.lord = response.data.lord
				
				
				$scope.hand = []
				
				
				if ($scope.game == $scope.STARTUPS){
					$scope.bodyStyle = {
						"background": "linear-gradient( rgba(0, 0, 0, 0.8), rgba(0, 0, 0, 0.8) ), url('/image/Oink/Startups/game_bg.png')",
						"background-size": "cover"
					}
					$scope.gamedata = response.data.startups
					
					var oldPhase = $scope.phase
					$scope.phase = $scope.gamedata.phase
					$scope.players = $scope.gamedata.players
					$scope.deck = []
					c = {
						"num": 0,
						"isDeck": true
					}
					$scope.deck.push(c);
					
					if ($scope.phase != -1 && oldPhase == -1){
						$scope.playMsgSE();
					}
					
					$scope.showEndRoundInfo = false;
					if ($scope.status == $scope.ROUNDEND){
						$scope.showEndRoundInfo = true;
						if ($scope.gamedata.confirmed){
							$scope.shownEndRoundPage = 5
						} else {
							//$scope.shownEndRoundPage = 0
							if ($scope.shownEndRoundPage == null || oldStatus != $scope.ROUNDEND){
								$scope.shownEndRoundPage = 0
							}
						}
					}
					
					$scope.hand = $scope.gamedata.myHand;
					$http.post('/citadelsgame/empty').then(function(response){
						adjustLogs("log-zone")
					});
					
				} else if ($scope.game == $scope.GROVE){
					$scope.gamedata = response.data.grove
					$scope.players = $scope.gamedata.players
					
					var oldPhase = $scope.phase
					$scope.phase = $scope.gamedata.phase
					
					var oldRound = $scope.round
					$scope.round = $scope.gamedata.round;
					
					if ($scope.phase != -1 && oldPhase == -1){
						$scope.playMsgSE();
					}
					
					if ($scope.status == $scope.ROUNDEND && $scope.status != oldStatus){
						$scope.playRevealSE();
					}
					if ($scope.round != oldRound){
						randomizeBGM()
						$scope.bgm.play();
					}
					$http.post('/citadelsgame/empty').then(function(response){
						adjustLogs("log-zone-grove")
					});
				} else if ($scope.game == $scope.POPE){
					var oldPhase = $scope.phase
					$scope.gamedata = response.data.pope
					$scope.phase = $scope.gamedata.phase
					$scope.hand = $scope.gamedata.hand;
					$scope.players = $scope.gamedata.players
					$scope.myIndex = $scope.gamedata.myIndex
					$scope.round = $scope.gamedata.round;
					if ($scope.phase != -1 && oldPhase == -1){
						$scope.playMsgSE();
					}
					if ($scope.oldKey<$scope.players[$scope.myIndex].numKey && $scope.firstRefresh == false){
						$scope.playPopeReceiveKeySE()
					}
					$scope.oldKey = $scope.players[$scope.myIndex].numKey;
					
					if ($scope.alive && $scope.players[$scope.myIndex].active == false  && $scope.firstRefresh == false){
						$scope.playDownSE()
					}
					$scope.alive = $scope.players[$scope.myIndex].active
					
					setPlayerPlayStyle()
					
					$http.post('/citadelsgame/empty').then(function(response){
						adjustLogs("log-zone-pope")
					});
				} else if ($scope.game == $scope.WEST){
					var oldPhase = $scope.phase
					$scope.gamedata = response.data.west
					$scope.hand = $scope.gamedata.myHand
					$scope.phase = $scope.gamedata.phase
					$scope.round = $scope.gamedata.round;
					$scope.players = $scope.gamedata.players
					if ($scope.phase != -1 && oldPhase == -1){
						$scope.playMsgSE();
					}
					for (i=0;i<$scope.hand.length;i++){
						$scope.hand[i].cstyle={}
					}
					$scope.playStyle = []
					for (i=0;i<6;i++){
						var tstyle = {
							"background-color": "rgba(64,64,64,0.5)"
						}
						if ($scope.gamedata.winner == i && $scope.status == 6){
							tstyle = {
								"background-color": "rgba(0,150,255,0.4)"
							}
						}
						$scope.playStyle.push(tstyle)
					}
					
					if ($scope.status == $scope.ENDGAME){
						$scope.sortedPlayers = []
						for (i=1;i<=6;i++){
							for (j=0;j<$scope.players.length;j++){
								var p = $scope.players[j]
								if (p.rank == i){
									$scope.sortedPlayers.push(p);
								}
							}
						}
					}
					
					
					
					$http.post('/citadelsgame/empty').then(function(response){
						adjustLogs("log-zone-west")
					});
				} else if ($scope.game == $scope.FLIP7){
					$scope.gamedata = response.data.flip7
				}
				
				
				var kicked = true;
				for (i=0;i<$scope.playerNames.length;i++){
					if ($scope.playerNames[i] == $scope.username){
						kicked = false;
					}
				}
				if (kicked){
					alert("你已被" + $scope.lord + "踢出");
					$scope.goto('oink');
					return;
				}
				
				if ($scope.playingBGM == false){
					randomizeBGM()
					$scope.playingBGM = true;
				}
				
				
				if ($scope.status == $scope.ENDGAME){
					if ($scope.firstRefresh == true || $scope.status != oldStatus){
						$scope.playWinLoseBGM($scope.gamedata.myRanking)
					}
					
				}
				
				$scope.firstRefresh = false;
			});
		}
		
		$scope.getBoard();
		
		ws.onMessage(function(){
			$scope.getBoard();
		});
		
}]);
