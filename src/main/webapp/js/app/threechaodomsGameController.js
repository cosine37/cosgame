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
		$scope.CHOOSETWO = 11;
		$scope.CHOOSEJAILOPTIONTWO = 12;
		$scope.CHOOSETWOJAILOPTION = 13;
		$scope.CHOOSEPLAYMEOTHEROPTION = 14;
		$scope.CHOOSEPLAYOPTIONTWO = 15;
		$scope.CHOOSEJAILHAND = 16;
		$scope.CHOOSEPLAYHAND = 17;
		$scope.CHOOSETOMB = 18;
		
		$scope.INJAIL = 101;
		$scope.OTHERPLAYER = 102;
		$scope.MYCARD = 103;
		
		$scope.playMode = 0;
		$scope.selectedCard = -1;
		$scope.targets = [-1,-1,-1,-1,-1,-1,-1];
		$scope.chosenOption = -1;
		$scope.chosenHand = -1;
		$scope.selectedPlayer = -1;
		$scope.selectedPlayerIndex = -1
		$scope.selectedCardIndex = -1
		$scope.selectedJailIndex = -1
		$scope.selectedPlayerIndex2 = -1;
		$scope.selectedCardIndex2 = -1;
		$scope.selectedJailIndex2 = -1;
		$scope.chosenOption2 = -1;
		$scope.selectedTomb = -1
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
			if ($scope.gamedata == null) return false;
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
		$scope.showJailForPlay = function(x){
			if ($scope.gamedata.phase == $scope.PLAYCARD){
				if ($scope.selectedCard == -1) return false;
				var type = $scope.gamedata.myHand[$scope.selectedCard].playType
				var subType = $scope.gamedata.myHand[$scope.selectedCard].playSubType
				if ($scope.gamedata.myHand[$scope.selectedCard].emulator && $scope.emulatedCard != null){
					type = $scope.emulatedCard.playType
					subType = $scope.emulatedCard.playSubType
					//alert(type)
				}
				if ((type == $scope.CHOOSEHAND && subType == $scope.INJAIL) || type == $scope.CHOOSEJAILHAND){
					if (x == $scope.chosenHand) {
						return true;
					} else {
						return false;
					}
				} else {
					return false;
				}
			} else {
				return false;
			}
		}
		
		$scope.showExchangeForPlay = function(x){
			if ($scope.gamedata.phase == $scope.PLAYCARD){
				if ($scope.selectedCard == -1) return false;
				var type = $scope.gamedata.myHand[$scope.selectedCard].playType
				var subType = $scope.gamedata.myHand[$scope.selectedCard].playSubType
				if ($scope.gamedata.myHand[$scope.selectedCard].emulator && $scope.emulatedCard != null){
					type = $scope.emulatedCard.playType
					subType = $scope.emulatedCard.playSubType
				}
				if (type == $scope.CHOOSEPLAYHAND){
					if (x == $scope.chosenHand) {
						return true;
					} else {
						return false;
					}
				} else {
					return false;
				}
			} else {
				return false;
			}
		}
		
		$scope.canChoosePlayer = function(name){
			if ($scope.gamedata.phase != $scope.PLAYCARD) return false;
			if ($scope.selectedCard == -1) return false;
			if (name == $scope.username) return false;
			var type = $scope.gamedata.myHand[$scope.selectedCard].playType;
			if ($scope.gamedata.myHand[$scope.selectedCard].emulator && $scope.emulatedCard != null){
				type = $scope.emulatedCard.playType
			}
			if (type == $scope.CHOOSEPLAYER || type == $scope.CHOOSEPLAYEROPTION){
				return true
			} else {
				return false;
			}
		}
		
		$scope.canChoosePlay = function(x,y){
			if ($scope.gamedata.phase != $scope.PLAYCARD) return false;
			if ($scope.selectedCard == -1) return false;
			var type = $scope.gamedata.myHand[$scope.selectedCard].playType;
			if ($scope.gamedata.myHand[$scope.selectedCard].emulator && $scope.emulatedCard != null){
				type = $scope.emulatedCard.playType
			}
			if (type != $scope.CHOOSEPLAY && type != $scope.CHOOSEPLAYOPTION && 
					type != $scope.CHOOSEPLAYJAIL && type != $scope.CHOOSEPLAYJAILOPTION && type != $scope.CHOOSEPLAYMEOTHEROPTION && 
					type != $scope.CHOOSEPLAYOPTIONTWO && type != $scope.CHOOSEPLAYHAND) return false;
			var subType = $scope.gamedata.myHand[$scope.selectedCard].playSubType;
			if ($scope.gamedata.myHand[$scope.selectedCard].emulator && $scope.emulatedCard != null){
				subType = $scope.emulatedCard.playSubType
			}
			if (subType >=210){
				var t = subType-210;
				var f = $scope.gamedata.players[x].play[y].faction
				if (t == f){
					return false;
				} else {
					if (x == $scope.gamedata.myIndex){
						return true;
					} else {
						return false;
					}
				}
			} else if (subType >= 200){
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
			} else if (subType == 103){
				if (x == $scope.gamedata.myIndex){
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
			if ($scope.gamedata.myHand[$scope.selectedCard].emulator && $scope.emulatedCard != null){
				type = $scope.emulatedCard.playType
			}
			if (type != $scope.CHOOSEJAIL && type != $scope.CHOOSEJAILOPTION && 
					type != $scope.CHOOSEPLAYJAIL && type != $scope.CHOOSEPLAYJAILOPTION && 
					type != $scope.CHOOSEJAILOPTIONTWO && type != $scope.CHOOSETWOJAILOPTION &&
					type != $scope.CHOOSEJAILHAND) return false;
			var subType = $scope.gamedata.myHand[$scope.selectedCard].playSubType;
			if ($scope.gamedata.myHand[$scope.selectedCard].emulator && $scope.emulatedCard != null){
				subType = $scope.emulatedCard.playSubType
			}
			if (subType == 102){
				if (x != $scope.gamedata.myIndex){
					return true;
				} else {
					return false;
				}
			} else if (subType == 103){
				if (x == $scope.gamedata.myIndex){
					return true;
				} else {
					return false;
				}
			}
			
			else{
				return true;
			}
		}
		
		$scope.canChooseTomb = function(){
			if ($scope.gamedata.phase != $scope.PLAYCARD) return false;
			if ($scope.selectedCard == -1) return false;
			var type = $scope.gamedata.myHand[$scope.selectedCard].playType;
			if ($scope.gamedata.myHand[$scope.selectedCard].emulator && $scope.emulatedCard != null){
				type = $scope.emulatedCard.playType
			}
			if (type == $scope.CHOOSETOMB){
				return true;
			} else {
				return false
			}
		}
		
		$scope.playCard = function(){
			var type = $scope.gamedata.myHand[$scope.selectedCard].playType;
			if ($scope.gamedata.myHand[$scope.selectedCard].emulator && $scope.emulatedCard != null){
				if ($scope.emulatedCard == null) return
				type = $scope.emulatedCard.playType
			}
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
			} else if (type == $scope.CHOOSEPLAYJAILOPTION){
				$scope.targets[0] = $scope.chosenOption
				$scope.targets[1] = $scope.selectedPlayerIndex
				$scope.targets[2] = $scope.selectedCardIndex
				$scope.targets[3] = $scope.selectedJailIndex
			} else if (type == $scope.CHOOSETWO){
				$scope.targets[0] = $scope.chosenOption
				$scope.targets[4] = $scope.chosenOption2
			} else if (type == $scope.CHOOSEJAILOPTIONTWO){
				$scope.targets[0] = $scope.chosenOption
				$scope.targets[1] = $scope.selectedPlayerIndex
				$scope.targets[2] = $scope.selectedJailIndex
				$scope.targets[4] = $scope.chosenOption2
			} else if (type == $scope.CHOOSETWOJAILOPTION){
				$scope.targets[0] = $scope.chosenOption
				$scope.targets[1] = $scope.selectedPlayerIndex
				$scope.targets[2] = $scope.selectedJailIndex
				$scope.targets[3] = $scope.selectedPlayerIndex2
				$scope.targets[4] = $scope.selectedJailIndex2
			} else if (type == $scope.CHOOSEPLAYMEOTHEROPTION){
				$scope.targets[0] = $scope.chosenOption
				$scope.targets[1] = $scope.selectedPlayerIndex
				$scope.targets[2] = $scope.selectedCardIndex
				$scope.targets[3] = $scope.selectedCardIndex2
			} else if (type == $scope.CHOOSEPLAYOPTIONTWO){
				$scope.targets[0] = $scope.chosenOption
				$scope.targets[1] = $scope.selectedPlayerIndex
				$scope.targets[2] = $scope.selectedCardIndex
				$scope.targets[4] = $scope.chosenOption2
			} else if (type == $scope.CHOOSEJAILHAND){
				var x = $scope.chosenHand;
				if (x>$scope.selectedCard){
					x = x-1;
				}
				$scope.targets[0] = x
				$scope.targets[1] = $scope.selectedPlayerIndex
				$scope.targets[2] = $scope.selectedJailIndex
			} else if (type == $scope.CHOOSEPLAYHAND){
				var x = $scope.chosenHand;
				if (x>$scope.selectedCard){
					x = x-1;
				}
				$scope.targets[0] = x
				$scope.targets[1] = $scope.selectedPlayerIndex
				$scope.targets[2] = $scope.selectedCardIndex
			} else if (type == $scope.CHOOSETOMB){
				$scope.targets[0] = $scope.selectedTomb;
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
				var cardToPlay = $scope.gamedata.myHand[$scope.selectedCard]
				if ($scope.gamedata.myHand[$scope.selectedCard].emulator){
					if ($scope.emulatedCard == null) return false;
					type = $scope.emulatedCard.playType
					cardToPlay = $scope.emulatedCard
				}
				
				if (type == $scope.CHOOSEONE){
					if (cardToPlay.options.length == 0){
						return true;
					}
					if ($scope.chosenOption>=0 && $scope.chosenOption<cardToPlay.options.length){
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
						if (cardToPlay.options.length == 0){
							return true;
						}
						if ($scope.chosenOption>=0 && $scope.chosenOption<cardToPlay.options.length){
							return true;
						} else {
							return false;
						}
					} else {
						return false;
					}
				} else if (type == $scope.CHOOSEPLAYEROPTION){
					if ($scope.selectedPlayer != -1){
						if (cardToPlay.options.length == 0){
							return true;
						}
						if ($scope.chosenOption>=0 && $scope.chosenOption<cardToPlay.options.length){
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
						if (cardToPlay.options.length == 0){
							return true;
						}
						if ($scope.chosenOption>=0 && $scope.chosenOption<cardToPlay.options.length){
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
						if (cardToPlay.options.length == 0){
							return true;
						}
						if ($scope.chosenOption>=0 && $scope.chosenOption<cardToPlay.options.length){
							return true;
						} else {
							return false;
						}
					} else {
						return false;
					}
				} else if (type == $scope.CHOOSETWO){
					if ($scope.chosenOption != -1 && $scope.chosenOption2 != -1){
						return true;
					} else {
						return false;
					}
				} else if (type == $scope.CHOOSEJAILOPTIONTWO){
					if ($scope.selectedPlayerIndex != -1 && $scope.selectedJailIndex != -1){
						if ($scope.chosenOption != -1 && $scope.chosenOption2 != -1){
							return true;
						} else {
							return false;
						}
					} else {
						return false;
					}
				} else if (type == $scope.CHOOSETWOJAILOPTION){
					if ($scope.selectedPlayerIndex != -1 && $scope.selectedJailIndex != -1 && $scope.selectedPlayerIndex2 != -1 && $scope.selectedJailIndex2 != -1){
						if (cardToPlay.options.length == 0){
							return true;
						}
						if ($scope.chosenOption>=0 && $scope.chosenOption<cardToPlay.options.length){
							return true;
						} else {
							return false;
						}
					} else {
						return false;
					}
				} else if (type == $scope.CHOOSEPLAYMEOTHEROPTION){
					if ($scope.selectedPlayerIndex != -1 && $scope.selectedCardIndex != -1 && $scope.selectedCardIndex2 != -1){
						if (cardToPlay.options.length == 0){
							return true;
						}
						if ($scope.chosenOption>=0 && $scope.chosenOption<cardToPlay.options.length){
							return true;
						} else {
							return false;
						}
					} else {
						return false;
					}
				} else if (type == $scope.CHOOSEPLAYOPTIONTWO){
					if ($scope.selectedPlayerIndex != -1 && $scope.selectedCardIndex != -1){
						if ($scope.chosenOption != -1 && $scope.chosenOption2 != -1){
							return true;
						} else {
							return false;
						}
					} else {
						return false;
					}
				} else if (type == $scope.CHOOSEJAILHAND){
					if ($scope.chosenHand != -1){
						if ($scope.selectedPlayerIndex != -1 && $scope.selectedJailIndex != -1){
							return true;
						} else {
							return false;
						}
					} else {
						return false;
					}
				} else if (type == $scope.CHOOSEPLAYHAND){
					if ($scope.chosenHand != -1){
						if ($scope.selectedPlayerIndex != -1 && $scope.selectedCardIndex != -1){
							return true;
						} else {
							return false;
						}
					} else {
						return false;
					}
				} else if (type == $scope.CHOOSETOMB){
					if ($scope.selectedTomb != -1){
						return true;
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
				
				if ($scope.gamedata.myHand[$scope.selectedCard].emulator){
					type = $scope.emulatedCard.playType
				}
				
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
				
				if ($scope.gamedata.myHand[$scope.selectedCard].emulator){
					type = $scope.emulatedCard.playType
				}
				
				if (type == $scope.CHOOSEPLAY || type == $scope.CHOOSEPLAYOPTION || 
						type == $scope.CHOOSEPLAYJAIL || type == $scope.CHOOSEPLAYJAILOPTION || 
						type == $scope.CHOOSEPLAYOPTIONTWO || type == $scope.CHOOSEPLAYHAND){
					$scope.selectedJailIndex = -1
					if ($scope.selectedPlayerIndex == x && $scope.selectedCardIndex == y){
						$scope.selectedPlayerIndex = -1
						$scope.selectedCardIndex = -1
					} else {
						$scope.selectedPlayerIndex = x
						$scope.selectedCardIndex = y
					}
				} else if (type == $scope.CHOOSEPLAYMEOTHEROPTION){
					$scope.selectedJailIndex = -1;
					$scope.selectedJailIndex2 = -1;
					if (x == $scope.gamedata.myIndex){
						if (y == $scope.selectedCardIndex2){
							$scope.selectedPlayerIndex2 = -1
							$scope.selectedCardIndex2 = -1
						} else {
							$scope.selectedPlayerIndex2 = x
							$scope.selectedCardIndex2 = y
						}
 					} else {
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
		}
		
		$scope.clickJail = function(x,y){
			if ($scope.gamedata.phase == $scope.PLAYCARD){
				var type = $scope.gamedata.myHand[$scope.selectedCard].playType
				
				if ($scope.gamedata.myHand[$scope.selectedCard].emulator){
					type = $scope.emulatedCard.playType
				}
				
				if (type == $scope.CHOOSEJAIL || type == $scope.CHOOSEJAILOPTION
						|| type == $scope.CHOOSEPLAYJAIL || type == $scope.CHOOSEPLAYJAILOPTION
						|| type == $scope.CHOOSEJAILOPTIONTWO || type == $scope.CHOOSEJAILHAND){
					$scope.selectedCardIndex = -1
					if ($scope.selectedPlayerIndex == x && $scope.selectedJailIndex == y){
						$scope.selectedPlayerIndex = -1
						$scope.selectedJailIndex = -1
					} else {
						$scope.selectedPlayerIndex = x
						$scope.selectedJailIndex = y
					}
				} else if (type == $scope.CHOOSETWOJAILOPTION){
					$scope.selectedCardIndex = -1;
					$scope.selectedCardIndex2 = -1;
					if ($scope.selectedPlayerIndex == x && $scope.selectedJailIndex == y){
						$scope.selectedPlayerIndex = -1
						$scope.selectedJailIndex = -1
					} else if ($scope.selectedPlayerIndex == -1 && $scope.selectedJailIndex == -1){
						$scope.selectedPlayerIndex = x
						$scope.selectedJailIndex = y
					} else if  ($scope.selectedPlayerIndex2 == x && $scope.selectedJailIndex2 == y){
						$scope.selectedPlayerIndex2 = -1
						$scope.selectedJailIndex2 = -1
					} else if ($scope.selectedPlayerIndex2 == -1 && $scope.selectedJailIndex2 == -1){
						$scope.selectedPlayerIndex2 = x
						$scope.selectedJailIndex2 = y
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
						$scope.chosenOption2 = -1;
						$scope.selectedPlayerIndex2 = -1;
						$scope.selectedCardIndex2 = -1;
						$scope.selectedJailIndex2 = -1;
						$scope.selectedTomb = -1
						$scope.emulatedCard = null
						$scope.showEmulatedCard = false;
					} else {
						var playType = $scope.gamedata.myHand[$scope.selectedCard].playType
						
						if ($scope.gamedata.myHand[$scope.selectedCard].emulator){
							playType = $scope.emulatedCard.playType
						}
						
						if (playType == $scope.CHOOSEHAND || playType == $scope.CHOOSEJAILHAND
								|| playType == $scope.CHOOSEPLAYHAND){
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
							$scope.selectedPlayerIndex = -1
							$scope.selectedCardIndex = -1
							$scope.selectedJailIndex = -1
							$scope.chosenOption2 = -1;
							$scope.selectedPlayerIndex2 = -1;
							$scope.selectedCardIndex2 = -1;
							$scope.selectedJailIndex2 = -1;
							$scope.emulatedCard = null
							$scope.showEmulatedCard = false;
						}
						
					}
					
					if ($scope.selectedCard != -1){
						var isEmulator = $scope.gamedata.myHand[$scope.selectedCard].emulator;
						if (isEmulator){
							var emulateType = $scope.gamedata.myHand[$scope.selectedCard].emulateType;
							if (emulateType == $scope.EMULATETOMB){
								setEmulatedCardStyle($scope.gamedata.tomb[0]);
							}
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
		
		$scope.clickTomb = function(x){
			if ($scope.selectedTomb == x){
				$scope.selectedTomb = -1;
			} else {
				$scope.selectedTomb = x;
			}
		}
			
		// End Play or Discard Section
		
		// Emulator Section
		$scope.EMULATETOMB = 301
		
		$scope.showEmulatedCard = false;
		$scope.emulatedCard = null
		var setEmulatedCardStyle = function(c){
			$scope.showEmulatedCard = true;
			$scope.emulatedCard = c;
			$scope.emulatedCardStyles = [buildCard(c)];
		}
		// End Emulator Section
		
		// Message Section
		$scope.showDirectPlay = function(){
			if ($scope.selectedCard == -1) return false;
			var type = $scope.gamedata.myHand[$scope.selectedCard].playType
			if ($scope.gamedata.myHand[$scope.selectedCard].emulator && $scope.emulatedCard != null){
				type = $scope.emulatedCard.playType
			}
			if (type == 0){
				return true;
			} else {
				return false;
			}
		}
		
		var isOptionType = function(type){
			if (type == $scope.CHOOSEONE || type == $scope.CHOOSEPLAYOPTION || type == $scope.CHOOSEPLAYEROPTION
					|| type == $scope.CHOOSEJAILOPTION || type == $scope.CHOOSEPLAYJAILOPTION
					|| type == $scope.CHOOSETWO || type == $scope.CHOOSEJAILOPTIONTWO
					|| type == $scope.CHOOSETWOJAILOPTION || type == $scope.CHOOSEPLAYMEOTHEROPTION
					|| type == $scope.CHOOSEPLAYOPTIONTWO){
				return true;
			} else {
				return false;
			}
		}
		
		$scope.showNoOption = function(){
			if ($scope.selectedCard == -1) return false;
			var type = $scope.gamedata.myHand[$scope.selectedCard].playType
			var optionLength = $scope.gamedata.myHand[$scope.selectedCard].options.length
			if ($scope.gamedata.myHand[$scope.selectedCard].emulator && $scope.emulatedCard != null){
				type = $scope.emulatedCard.playType
				optionLength = $scope.emulatedCard.options.length
			}
			if (isOptionType(type)){
				if (optionLength == 0){
					return true;
				} else {
					return false;
				}
			} else {
				return false;
			}
		}
		
		$scope.showOptions1 = function(){
			if ($scope.selectedCard == -1) return false;
			var type = $scope.gamedata.myHand[$scope.selectedCard].playType
			var optionLength = $scope.gamedata.myHand[$scope.selectedCard].options.length
			if ($scope.gamedata.myHand[$scope.selectedCard].emulator && $scope.emulatedCard != null){
				type = $scope.emulatedCard.playType
				optionLength = $scope.emulatedCard.options.length
			}
			if (isOptionType(type)){
				if (optionLength > 0){
					return true;
				} else {
					return false;
				}
			} else {
				return false;
			}
		}
		
		$scope.showOptions2 = function(){
			if ($scope.selectedCard == -1) return false;
			var type = $scope.gamedata.myHand[$scope.selectedCard].playType
			var optionLength = $scope.gamedata.myHand[$scope.selectedCard].options.length
			if ($scope.gamedata.myHand[$scope.selectedCard].emulator && $scope.emulatedCard != null){
				type = $scope.emulatedCard.playType
				optionLength = $scope.emulatedCard.options2.length
			}
			if (type == $scope.CHOOSETWO || type == $scope.CHOOSEJAILOPTIONTWO || type == $scope.CHOOSEPLAYOPTIONTWO){
				if (optionLength > 0){
					return true;
				} else {
					return false;
				}
			} else {
				return false;
			}
		}
		
		
		$scope.showIndividualOption1 = function(x){
			if ($scope.selectedCard == -1) return false;
			var optionLength = $scope.gamedata.myHand[$scope.selectedCard].options.length
			if ($scope.gamedata.myHand[$scope.selectedCard].emulator && $scope.emulatedCard != null){
				optionLength = $scope.emulatedCard.options.length
			}
			if (optionLength > x) {
				return true;
			} else {
				return false;
			}
		}
		
		$scope.showIndividualOption2 = function(x){
			if ($scope.selectedCard == -1) return false;
			var optionLength = $scope.gamedata.myHand[$scope.selectedCard].options2.length
			if ($scope.gamedata.myHand[$scope.selectedCard].emulator && $scope.emulatedCard != null){
				optionLength = $scope.emulatedCard.options2.length
			}
			if (optionLength > x) {
				return true;
			} else {
				return false;
			}
		}
		// End Message Section
		// Tavern Section
		$scope.recruitTarget = -1
		$scope.recruitFromDeck = function(){
			$scope.recruitTarget = 4
			$scope.recruit();
		}
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
		
		$scope.tombIndex = 0;
		var buildTombStyle = function(){
			$scope.tombStyles = []
			for (i=0;i<$scope.gamedata.tomb.length;i++){
				var singleStyle = buildCard($scope.gamedata.tomb[i])
				$scope.tombStyles.push(singleStyle);
			}
		}
		
		$scope.changeTombIndex = function(x){
			$scope.tombIndex = $scope.tombIndex + x;
			if ($scope.tombIndex < 0) {
				$scope.tombIndex = 0;
			}
			var maxValue = $scope.numTomb-1
			if ($scope.tombIndex > maxValue){
				$scope.tombIndex = maxValue
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
						$scope.winCondition = $scope.winCondition + "，" + factions[i]
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
		
		var buildRewards = function(){
			$scope.rewards = []
			var p = $scope.players[$scope.gamedata.myIndex]
			$scope.rewards = p.receives
		}
		
		var adjustLogs = function(){
			var logcontent = document.getElementById("logs");
			logcontent.scrollTop = logcontent.scrollHeight;
		}
		
		$scope.getBoard = function(){
			$http.get('/threechaodoms/getboard').then(function(response){
				if (response.data.id == "NE"){
					alert("该游戏已解散");
					$scope.goto('threechaodoms');
					return;
				}
				$scope.gamedata = response.data
				$scope.id = response.data.id
				$scope.status = response.data.status
				$scope.players = response.data.players
				$scope.lord = response.data.lord
				$scope.tombIndex = 0;
				var i
				var kicked = true;
				for (i=0;i<$scope.players.length;i++){
					if ($scope.players[i].name == $scope.username){
						kicked = false;
					}
				}
				/*
				if (kicked){
					alert("你已被" + $scope.lord + "踢出");
					$scope.goto('threechaodoms');
					return;
				}*/
				
				buildHandStyles()
				buildTavernStyles()
				buildPlayerStyles()
				buildTombStyle()
				buildWinCondition()
				buildRewards()
				$http.post('/citadelsgame/empty').then(function(response){
					adjustLogs()
				});
				
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
