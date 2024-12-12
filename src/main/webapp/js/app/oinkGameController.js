var setUrl = function(d){
	hearder = "http://";
	server = "localhost:8080";
	return header + server + d;
}

var app = angular.module("oinkGameApp", ["ngWebSocket"]);
app.controller("oinkGameCtrl", ['$scope', '$window', '$http', '$document', '$timeout', '$websocket',
	function($scope, $window, $http, $document, $timeout, $websocket){
		var ws = $websocket("ws://" + $window.location.host + "/oink/boardrefresh");
		ws.onError(function(event) {
		});
		
		ws.onClose(function(event) {
		});
		
		ws.onOpen(function() {
		});
	
		$scope.STARTUPS = 1;
		$scope.GROVE = 2;
		
		$scope.ROUNDEND = 2
		$scope.ENDGAME = 3
		$scope.showEndRoundInfo = false;
		
		$scope.chosenCard = -1;
		$scope.chosenRoles = [0,0,0]
		
		$scope.playingBGM = false
		$scope.bgm = new Audio();
		randomizeBGM = function(){
			if ($scope.game == $scope.STARTUPS){
				v = Math.floor(Math.random() * 3)+1;
				bgmSrc = '/sound/Oink/default' + v + '.mp3'
				$scope.bgm.src = bgmSrc
			}
			
		}
		$scope.bgm.addEventListener("ended", function() {
			randomizeBGM()
			$scope.bgm.play();
		}, true);
		
		$scope.playWinLoseBGM = function(x){
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
		}
		
		$scope.playClickSE = function(){
			var audio = new Audio("/sound/Oink/click.wav")
			audio.play();
		}
	
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
						"background": "linear-gradient( rgba(0, 0, 0, 0.7), rgba(0, 0, 0, 0.7) ), url('/image/Oink/Startups/game_bg.png')",
						"background-size": "cover"
					}
					$scope.gamedata = response.data.startups
					$scope.phase = $scope.gamedata.phase
					$scope.players = $scope.gamedata.players
					$scope.deck = []
					c = {
						"num": 0,
						"isDeck": true
					}
					$scope.deck.push(c);
					
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
					$scope.phase = $scope.gamedata.phase
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
				
				
				if ($scope.status == $scope.ENDGAME && $scope.status != oldStatus){
					$scope.playWinLoseBGM($scope.gamedata.myRanking)
				}
				
			});
		}
		
		$scope.getBoard();
		
		ws.onMessage(function(){
			$scope.getBoard();
		});
		
}]);
