var setUrl = function(d){
	hearder = "http://";
	server = "localhost:8080";
	return header + server + d;
}

var app = angular.module("threechaodomsGameApp", ["ngWebSocket", "ngSanitize"]);
app.controller("threechaodomsGameCtrl", ['$scope', '$window', '$http', '$document', '$timeout', '$websocket',
	function($scope, $window, $http, $document, $timeout, $websocket){
		var ws = $websocket("ws://" + $window.location.host + "/threechaodoms/boardrefresh");
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
		
		$scope.OFFTURN = -1;
		$scope.MAKEHAND = 0;
		$scope.PLAYCARD = 1;
		$scope.RECRUIT = 2;
		$scope.DISCARD = 3;
		
		// Setup Section
		$scope.setupJail = -1;
		$scope.setupExile = -1;
		$scope.handSetup = function(x){
			if ($scope.setupJail == x){
				$scope.setupJail = -1
			} else if ($scope.setupExile == x){
				$scope.setupExile = -1;
			} else if ($scope.setupJail == -1){
				$scope.setupJail = x
			} else if ($scope.setupExile == -1){
				$scope.setupExile = x;
			}
		}
		$scope.canSubmitHandSetup = function(){
			if ($scope.setupJail != -1 && $scope.setupExile != -1){
				return true
			} else {
				return false
			}
		}
		$scope.submitHandSetup = function(){
			if ($scope.canSubmitHandSetup()){
				var data = {
					"jail": $scope.setupJail,
					"exile": $scope.setupExile
				}
				$http({url: "/threechaodoms/setuphand", method: "POST", params: data}).then(function(response){
					$scope.allRefresh()
				});
			}
		}
		// end setup section
		
		// Play or Discard Section
		$scope.CHOOSEONE = 1
		$scope.CHOOSEHAND = 2
		$scope.CHOOSEPLAYER = 3;
		$scope.CHOOSEPLAY = 4;
		$scope.CHOOSEPLAYOPTION = 5;
		$scope.CHOOSEPLAYEROPTION = 6;
		$scope.CHOOSEJAIL = 7;
		$scope.CHOOSEJAILOPTION = 8;
		$scope.CHOOSEPLAYJAIL = 9;
		$scope.CHOOSEPLAYJAILOPTION = 10;
		
		$scope.INJAIL = 101;
		$scope.OTHERPLAYER = 102;
		
		$scope.playMode = 0;
		$scope.selectedCard = -1;
		$scope.targets = [-1,-1,-1,-1,-1];
		$scope.chosenOption = -1;
		$scope.chosenHand = -1;
		$scope.selectedPlayer = -1;
		$scope.selectedPlayerIndex = -1
		$scope.selectedCardIndex = -1
		$scope.selectedJailIndex = -1
		$scope.changeMode = function(x){
			$scope.playMode = x;
			for (var i=0;i<$scope.gamedata.myHand.length;i++){
				$scope.gamedata.myHand.selected = 0
			}
			$scope.selectedCard = -1;
		}
			// Play or Discard -- Discard
		$scope.exileCards = function(){
			var exileIndexes = []
			for (var i=0;i<$scope.gamedata.myHand.length;i++){
				if ($scope.gamedata.myHand[i].selected == 1){
					exileIndexes.push(i)
				}
			}
			if (exileIndexes.length == 0){
				alert("请至少选择一名武将！");
			}
			var data = {
					"targets": exileIndexes
				}
			//alert(exileIndexes)
			$http({url: "/threechaodoms/exilecards", method: "POST", params: data}).then(function(response){
				$scope.allRefresh()
			});
		}
		
		$scope.selectCardToExile = function(x){
			if ($scope.gamedata.myHand[x].selected == 1){
				$scope.gamedata.myHand[x].selected = 0
			} else {
				var t = 0
				for (var i=0;i<$scope.gamedata.myHand.length;i++){
					if ($scope.gamedata.myHand[i].selected == 1){
						t=t+1;
					}
					if (t<3){
						$scope.gamedata.myHand[x].selected = 1
					}
				}
			}
				
		}
		
		$scope.canExile = function(){
			var t = 0
			for (var i=0;i<$scope.gamedata.myHand.length;i++){
				if ($scope.gamedata.myHand[i].selected == 1){
					t=t+1;
				}
			}
			if (t==0 || t>3){
				return false
			} else {
				return true
			}
		}
		
		$scope.discard = function(){
			var data = {
				"target": $scope.selectedCard
			}
			$http({url: "/threechaodoms/discard", method: "POST", params: data}).then(function(response){
				$scope.selectedCard = -1
				$scope.allRefresh()
			});
		}
			// end Play or Discard -- Discard
			// Play or Discard -- Play
		$scope.canChoosePlay = function(x,y){
			if ($scope.gamedata.phase != $scope.PLAYCARD) return false;
			if ($scope.selectedCard == -1) return false;
			var type = $scope.gamedata.myHand[$scope.selectedCard].playType;
			if (type != $scope.CHOOSEPLAY && type != $scope.CHOOSEPLAYOPTION && 
					type != $scope.CHOOSEPLAYJAIL && type != $scope.CHOOSEPLAYJAILOPTION) return false;
			var subType = $scope.gamedata.myHand[$scope.selectedCard].playSubType;
			if (subType >= 200){
				var t = subType-200;
				var f = $scope.gamedata.players[x].play[y].faction
				if (t == f){
					return true;
				} else {
					return false;
				}
			} else if (subType == 102){
				if (x != $scope.gamedata.myIndex){
					return true;
				} else {
					return false;
				}
			} 
			
			else {
				return true;
			}
		}
		
		$scope.canChooseJail = function(x,y){
			if ($scope.gamedata.phase != $scope.PLAYCARD) return false;
			if ($scope.selectedCard == -1) return false;
			var type = $scope.gamedata.myHand[$scope.selectedCard].playType;
			if (type != $scope.CHOOSEJAIL && type != $scope.CHOOSEJAILOPTION && 
					type != $scope.CHOOSEPLAYJAIL && type != $scope.CHOOSEPLAYJAILOPTION) return false;
			var subType = $scope.gamedata.myHand[$scope.selectedCard].playSubType;
			if (subType == 102){
				if (x != $scope.gamedata.myIndex){
					return true;
				} else {
					return false;
				}
			}
			
			else{
				return true;
			}
		}
		
		$scope.playCard = function(){
			var type = $scope.gamedata.myHand[$scope.selectedCard].playType;
			if (type == $scope.CHOOSEONE){
				$scope.targets[0] = $scope.chosenOption
			} else if (type == $scope.CHOOSEHAND){
				var x = $scope.chosenHand;
				if (x>$scope.selectedCard){
					x = x-1;
				}
				$scope.targets[0] = x
			} else if (type == $scope.CHOOSEPLAYER){
				$scope.targets[0] = $scope.selectedPlayer
			} else if (type == $scope.CHOOSEPLAY){
				$scope.targets[1] = $scope.selectedPlayerIndex
				$scope.targets[2] = $scope.selectedCardIndex
			} else if (type == $scope.CHOOSEPLAYOPTION){
				$scope.targets[0] = $scope.chosenOption
				$scope.targets[1] = $scope.selectedPlayerIndex
				$scope.targets[2] = $scope.selectedCardIndex
			} else if (type == $scope.CHOOSEPLAYEROPTION){
				$scope.targets[0] = $scope.chosenOption
				$scope.targets[1] = $scope.selectedPlayer
			} else if (type == $scope.CHOOSEJAIL){
				$scope.targets[1] = $scope.selectedPlayerIndex
				$scope.targets[2] = $scope.selectedJailIndex
			} else if (type == $scope.CHOOSEJAILOPTION){
				$scope.targets[0] = $scope.chosenOption
				$scope.targets[1] = $scope.selectedPlayerIndex
				$scope.targets[2] = $scope.selectedJailIndex
			} else if (type == $scope.CHOOSEPLAYJAIL){
				$scope.targets[1] = $scope.selectedPlayerIndex
				$scope.targets[2] = $scope.selectedCardIndex
				$scope.targets[3] = $scope.selectedJailIndex
			}  else if (type == $scope.CHOOSEPLAYJAILOPTION){
				$scope.targets[0] = $scope.chosenOption
				$scope.targets[1] = $scope.selectedPlayerIndex
				$scope.targets[2] = $scope.selectedCardIndex
				$scope.targets[3] = $scope.selectedJailIndex
			} 
			
			var data = {
				"cardIndex": $scope.selectedCard,
				"targets": $scope.targets
			}
			$http({url: "/threechaodoms/play", method: "POST", params: data}).then(function(response){
				$scope.selectedCard = -1
				$scope.allRefresh()
			});
		}
		$scope.cancelCard = function(){
			$scope.selectedCard = -1
			//alert($scope.selectedCard)
		}
		$scope.canPlaySelectedCard = function(){
			if ($scope.selectedCard == -1){
				return false;
			} else {
				var type = $scope.gamedata.myHand[$scope.selectedCard].playType
				if (type == $scope.CHOOSEONE){
					if ($scope.gamedata.myHand[$scope.selectedCard].options.length == 0){
						return true;
					}
					if ($scope.chosenOption>=0 && $scope.chosenOption<$scope.gamedata.myHand[$scope.selectedCard].options.length){
						return true;
					} else {
						return false;
					}
				} else if (type == $scope.CHOOSEHAND){
					if ($scope.chosenHand != -1){
						return true;
					} else {
						return false;
					}
				} else if (type == $scope.CHOOSEPLAYER){
					if ($scope.selectedPlayer != -1){
						return true;
					} else {
						return false;
					}
				} else if (type == $scope.CHOOSEPLAY){
					if ($scope.selectedPlayerIndex != -1 && $scope.selectedCardIndex != -1){
						return true;
					} else {
						return false;
					}
				} else if (type == $scope.CHOOSEPLAYOPTION){
					if ($scope.selectedPlayerIndex != -1 && $scope.selectedCardIndex != -1){
						if ($scope.gamedata.myHand[$scope.selectedCard].options.length == 0){
							return true;
						}
						if ($scope.chosenOption>=0 && $scope.chosenOption<$scope.gamedata.myHand[$scope.selectedCard].options.length){
							return true;
						} else {
							return false;
						}
					} else {
						return false;
					}
				} else if (type == $scope.CHOOSEPLAYEROPTION){
					if ($scope.selectedPlayer != -1){
						if ($scope.gamedata.myHand[$scope.selectedCard].options.length == 0){
							return true;
						}
						if ($scope.chosenOption>=0 && $scope.chosenOption<$scope.gamedata.myHand[$scope.selectedCard].options.length){
							return true;
						} else {
							return false;
						}
					} else {
						return false;
					}
				} else if (type == $scope.CHOOSEJAIL){
					if ($scope.selectedPlayerIndex != -1 && $scope.selectedJailIndex != -1){
						return true;
					} else {
						return false;
					}
				} else if (type == $scope.CHOOSEJAILOPTION){
					if ($scope.selectedPlayerIndex != -1 && $scope.selectedJailIndex != -1){
						if ($scope.gamedata.myHand[$scope.selectedCard].options.length == 0){
							return true;
						}
						if ($scope.chosenOption>=0 && $scope.chosenOption<$scope.gamedata.myHand[$scope.selectedCard].options.length){
							return true;
						} else {
							return false;
						}
					} else {
						return false;
					}
				} else if (type == $scope.CHOOSEPLAYJAIL){
					if ($scope.selectedPlayerIndex != -1 || $scope.selectedJailIndex != -1){
						return true;
					} else {
						return false;
					}
				} else if (type == $scope.CHOOSEPLAYJAILOPTION){
					if ($scope.selectedPlayerIndex != -1 || $scope.selectedJailIndex != -1){
						if ($scope.gamedata.myHand[$scope.selectedCard].options.length == 0){
							return true;
						}
						if ($scope.chosenOption>=0 && $scope.chosenOption<$scope.gamedata.myHand[$scope.selectedCard].options.length){
							return true;
						} else {
							return false;
						}
					} else {
						return false;
					}
				}
			}
		}
			// end Play or Discard -- Play
		
		$scope.clickPlayer = function(x){			
			if ($scope.gamedata.phase == $scope.PLAYCARD){
				var type = $scope.gamedata.myHand[$scope.selectedCard].playType
				if (type == $scope.CHOOSEPLAYER || type == $scope.CHOOSEPLAYEROPTION){
					if ($scope.selectedPlayer == x){
						$scope.selectedPlayer = -1;
					} else {
						$scope.selectedPlayer = x;
					}
				}
			}
		}
		
		$scope.clickPlay = function(x,y){
			if ($scope.gamedata.phase == $scope.PLAYCARD){
				var type = $scope.gamedata.myHand[$scope.selectedCard].playType
				if (type == $scope.CHOOSEPLAY || type == $scope.CHOOSEPLAYOPTION || 
						type == $scope.CHOOSEPLAYJAIL || type == $scope.CHOOSEPLAYJAILOPTION){
					$scope.selectedJailIndex = -1
					if ($scope.selectedPlayerIndex == x && $scope.selectedCardIndex == y){
						$scope.selectedPlayerIndex = -1
						$scope.selectedCardIndex = -1
					} else {
						$scope.selectedPlayerIndex = x
						$scope.selectedCardIndex = y
					}
				}
			}
		}
		
		$scope.clickJail = function(x,y){
			if ($scope.gamedata.phase == $scope.PLAYCARD){
				var type = $scope.gamedata.myHand[$scope.selectedCard].playType
				if (type == $scope.CHOOSEJAIL || type == $scope.CHOOSEJAILOPTION
						|| type == $scope.CHOOSEPLAYJAIL || type == $scope.CHOOSEPLAYJAILOPTION){
					$scope.selectedCardIndex = -1
					if ($scope.selectedPlayerIndex == x && $scope.selectedJailIndex == y){
						$scope.selectedPlayerIndex = -1
						$scope.selectedJailIndex = -1
					} else {
						$scope.selectedPlayerIndex = x
						$scope.selectedJailIndex = y
					}
				}
			}
		}
		
		$scope.clickHand = function(x){
			if ($scope.gamedata.phase == $scope.MAKEHAND){
				$scope.handSetup(x)
			} else if ($scope.gamedata.phase == $scope.PLAYCARD){
				if ($scope.playMode == 0){
					//$scope.playCard(x, [0])
					if ($scope.selectedCard == x){
						$scope.selectedCard = -1;
					} else if ($scope.selectedCard == -1){ 
						$scope.selectedCard = x;
						$scope.chosenOption = -1;
						$scope.selectedPlayer = -1
						$scope.chosenHand = -1;
						$scope.targets = [-1,-1,-1,-1,-1];
						$scope.selectedPlayerIndex = -1
						$scope.selectedCardIndex = -1
						$scope.selectedJailIndex = -1
					} else {
						var playType = $scope.gamedata.myHand[$scope.selectedCard].playType
						
						if (playType == $scope.CHOOSEHAND){
							if ($scope.chosenHand == x){
								$scope.chosenHand = -1
							} else {
								$scope.chosenHand = x;
							}
						} else {
							$scope.selectedCard = x;
							$scope.chosenOption = -1;
							$scope.selectedPlayer = -1
							$scope.chosenHand = -1;
							$scope.targets = [-1,-1,-1,-1,-1];
						}
						
					}
					
				} else if ($scope.playMode == 1){
					$scope.selectCardToExile(x)
				} 
				
			} else if ($scope.gamedata.phase == $scope.DISCARD){
				if ($scope.selectedCard == x){
					$scope.selectedCard = -1;
				} else { 
					$scope.selectedCard = x;
				}
			}
			
		}
			
		// End Play or Discard Section
		
		// Tavern Section
		$scope.recruitTarget = -1
		$scope.recruit = function(){
			var data = {
				"target": $scope.recruitTarget
			}
			$http({url: "/threechaodoms/recruit", method: "POST", params: data}).then(function(response){
				$scope.recruitTarget = -1
				$scope.allRefresh()
			});
		}
		$scope.clickDeck = function(){
			if ($scope.gamedata.phase == $scope.RECRUIT){
				if ($scope.recruitTarget == 4){
					$scope.recruitTarget = -1
				} else {
					$scope.recruitTarget = 4
				}
			}
		}
		$scope.clickTavern = function(x){
			if ($scope.gamedata.phase == $scope.RECRUIT){
				if ($scope.gamedata.tavern[x].blankSpace) return;
				if ($scope.recruitTarget == x){
					$scope.recruitTarget = -1
				} else {
					$scope.recruitTarget = x
				}
			}
		}
		$scope.cancelTavern = function(){
			if ($scope.gamedata.phase == $scope.RECRUIT){
				$scope.recruitTarget = -1
			}
		}
		// End Tavern Section
		
		// Set Styles Section
		$scope.handStyles = []
		var buildHandStyles = function(){
			$scope.handStyles = []
			for (i=0;i<$scope.gamedata.myHand.length;i++){
				var c = $scope.gamedata.myHand[i]
				$scope.handStyles.push(buildCard(c))
			}
		}
		
		$scope.tavernStyles = []
		var buildTavernStyles = function(){
			$scope.tavernStyles = []
			for (i=0;i<$scope.gamedata.tavern.length;i++){
				var c = $scope.gamedata.tavern[i]
				$scope.tavernStyles.push(buildCard(c))
			}
		}
		
		var buildPlayerStyles = function(){
			var i,j;
			for (i=0;i<$scope.gamedata.players.length;i++){
				var p = $scope.gamedata.players[i]
				var playStyles = []
				for (j=0;j<$scope.gamedata.players[i].play.length;j++){
					var c=$scope.gamedata.players[i].play[j]
					playStyles.push(buildCard(c))
				}
				$scope.gamedata.players[i].playStyles = playStyles
				var jailStyles = []
				for (j=0;j<$scope.gamedata.players[i].jail.length;j++){
					var c=$scope.gamedata.players[i].jail[j]
					jailStyles.push(buildCard(c))
				}
				$scope.gamedata.players[i].jailStyles = jailStyles
			}
		}
		// End Set Styles Section
		
		var buildWinCondition = function(){
			$scope.winCondition = "";
			var factions = ["魏","蜀","吴","群"];
			for (i=0;i<$scope.gamedata.myID.length;i++){
				if ($scope.gamedata.myID[i] == 1){
					if ($scope.winCondition == ""){
						$scope.winCondition = factions[i]
					} else {
						$scope.winCondition = $scope.winCondition + "," + factions[i]
					}
				}
			}
			if ($scope.status == 3){
				var i,j
				for (i=0;i<$scope.players.length;i++){
					var idDisplay = "";
					for (j=0;j<$scope.players[i].id.length;j++){
						if ($scope.players[i].id[j] == 1){
							if (idDisplay == ""){
								idDisplay = factions[j]
							} else {
								idDisplay = idDisplay + "," + factions[j]
							}
						}
					}
					$scope.players[i].idDisplay = idDisplay
				}
			}
		}
		
		$scope.getBoard = function(){
			$http.get('/threechaodoms/getboard').then(function(response){
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
				
				buildHandStyles()
				buildTavernStyles()
				buildPlayerStyles()
				buildWinCondition()
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
