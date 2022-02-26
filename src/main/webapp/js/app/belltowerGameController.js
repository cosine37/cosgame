var setUrl = function(d){
	hearder = "http://";
	server = "localhost:8080";
	return header + server + d;
}

var app = angular.module("belltowerGameApp", ["ngWebSocket"]);
app.controller("belltowerGameCtrl", ['$scope', '$window', '$http', '$document', '$timeout', '$websocket',
	function($scope, $window, $http, $document, $timeout, $websocket){
		
		var ws = $websocket("ws://" + $window.location.host + "/belltower/boardrefresh");
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
		
		$scope.settings = [0]
		
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
		
		$scope.PREGAME = 1;
		$scope.ASSIGNROLE = 1;
		$scope.INGAME = 2;
		$scope.ENDGAME = 3;
		
		$scope.NIGHT = 0;
		$scope.DAY = 2;
		$scope.EXECUTION = 3;
		
		$scope.HUMAN = 1;
		$scope.DEVIL = 2;
		
		$scope.groupNames = ["村民","外来者","恶魔","爪牙"];
		$scope.groupNumbers = [0,0,0,0];
		
		$scope.addGroupNumber = function(x){
			$scope.groupNumbers[x] = $scope.groupNumbers[x]+1
		}
		
		$scope.subtractGroupNumber = function(x){
			$scope.groupNumbers[x] = $scope.groupNumbers[x]-1
		}
		
		$scope.canSubmitGroupNumbers = function(){
			if ($scope.groupNumbers == null) return false;
			if ($scope.players == null) return false;
			var i
			var x=0;
			for (i=0;i<$scope.groupNumbers.length;i++){
				x = x+$scope.groupNumbers[i]
			}
			if (x == $scope.players.length){
				return true
			} else {
				return false
			}
		}
		
		
		$scope.submitGroupNumbers = function(){
			if ($scope.canSubmitGroupNumbers()){
				var data = {
					"groupNumbers" : $scope.groupNumbers
				}
				
				$http({url: "/belltower/submitgroupnumbers", method: "POST", params:data}).then(function(response){
					$scope.allRefresh()
				});
			}
		}
		// night handles
		/*
		 * Indexes for targets
		 * 0: option;
		 * 1: first player;
		 * 2: second player;
		 * 
		 */
		$scope.targets = [-1,-1,-1,-1,-1]
		$scope.chosenPlayer = -1;
		$scope.chosenPlayer2 = -1;
		$scope.nominatedPlayer = -1;
		
		$scope.numChosenPlayer = function(){
			if ($scope.chosenPlayer == -1){
				return 0;
			} else if ($scope.chosenPlayer2 == -1){
				return 1;
			} else {
				return 2;
			}
		}
		
		$scope.choosePlayer = function(x){
			if ($scope.phase == $scope.NIGHT){
				if ($scope.gamedata.myRole.numPlayerChoose == 0){
					
				} else if ($scope.gamedata.myRole.numPlayerChoose == 1){
					if ($scope.chosenPlayer == x){
						$scope.chosenPlayer = -1
					} else {
						$scope.chosenPlayer = x
					}
				} else if ($scope.gamedata.myRole.numPlayerChoose == 2){
					if ($scope.chosenPlayer == x){
						$scope.chosenPlayer = -1
					} else if ($scope.chosenPlayer2 == x){
						$scope.chosenPlayer2 = -1
					} else if ($scope.chosenPlayer == -1){
						$scope.chosenPlayer = x
					} else if ($scope.chosenPlayer2 == -1){
						$scope.chosenPlayer2 = x
					}
				}
			} else if ($scope.phase == $scope.DAY){
				if ($scope.gamedata.curNominator == $scope.gamedata.myIndex){
					$scope.nominatedPlayer = x;
				}
			}
			
		}
		
		$scope.confirmNight = function(){
			$scope.targets[1] = $scope.chosenPlayer;
			$scope.targets[2] = $scope.chosenPlayer2;
			
			var data = {
				"targets" : $scope.targets
			}
			$http({url: "/belltower/confirmnight", method: "POST", params:data}).then(function(response){
				$scope.chosenPlayer = -1;
				$scope.chosenPlayer2 = -1;
				$scope.allRefresh()
			});
		}
		
		$scope.canConfirmNight = function(){
			if ($scope.gamedata == null){
				return false;
			}
			var npc = $scope.gamedata.myRole.numPlayerChoose;
			if (npc == 0) {
				return true;
			} else if (npc == 1){
				if ($scope.chosenPlayer != -1){
					return true;
				} else {
					return false;
				}
			} else if (npc == 2){
				if ($scope.chosenPlayer != -1 && $scope.chosenPlayer2 != -1){
					return true;
				} else {
					return false;
				}
			}
			
			else {
				return false;
			}
		}
		// ---- end night handles
		
		// day handles
		$scope.vote = function(x){
			var data = {
				"f" : x
			}
			$http({url: "/belltower/vote", method: "POST", params:data}).then(function(response){
				$scope.allRefresh()
			});
		}
		
		$scope.nominate = function(x){
			var data = {
				"target" : x
			}
			$http({url: "/belltower/nominate", method: "POST", params:data}).then(function(response){
				$scope.allRefresh()
				$scope.nominatedPlayer = -1;
			});
		}
		
		$scope.confirmDay = function(){
			$http({url: "/belltower/confirmday", method: "POST"}).then(function(response){
				$scope.allRefresh()
			});
		}
		// ---- end day handles
		
		$scope.roleFrameClass = function(x){
			var ans = "role-frame"
			if ($scope.players[x].win){
				ans = ans + " player-win"
			}
			return ans;
		}
		
		$scope.iconClass = function(x){
			var ans = "icon-game";
			if ($scope.status == $scope.ENDGAME){
				
			} else if ($scope.phase == $scope.NIGHT){
				if ($scope.gamedata.myRole.numPlayerChoose == 0){
					
				} else if ($scope.gamedata.myRole.numPlayerChoose == 1){
					if ($scope.chosenPlayer == x){
						ans = "icon-game-choosable";
					} else {
						ans = "icon-game-choosable";
					}
				} else if ($scope.gamedata.myRole.numPlayerChoose == 2){
					if ($scope.chosenPlayer == x){
						ans = "icon-game-choosable";
					} else if ($scope.chosenPlayer2 == x){
						ans = "icon-game-choosable";
					} else if ($scope.chosenPlayer == -1){
						ans = "icon-game-choosable";
					} else if ($scope.chosenPlayer2 == -1){
						ans = "icon-game-choosable";
					}
				}
			} else if ($scope.phase == $scope.DAY){
				if ($scope.gamedata.curNominator == $scope.gamedata.myIndex){
					ans = "icon-game-choosable";
				}
			}
			if ($scope.players[x].alive == false && $scope.status!=$scope.ASSIGNROLE){
				ans = ans + " icon-game-not-alive"
			}
			return ans;
		}
		
		var setNominateMessage = function(){
			$scope.nominateMessage = "今早首位发言玩家是" + $scope.players[$scope.gamedata.firstNominator].displayName + "，发言顺序为";
			
			if ($scope.gamedata.sequence == 1){
				$scope.nominateMessage = $scope.nominateMessage + "顺时针。"
			} else if ($scope.gamedata.sequence = -1){
				$scope.nominateMessage = $scope.nominateMessage + "逆时针。"
			}
			
			$scope.userNVMsg = "";
			$scope.showNoVote = false;
			$scope.showNoNominate = false;
			if ($scope.gamedata.curVoter == -1){
				if ($scope.gamedata.curNominator == $scope.gamedata.myIndex){
					$scope.userNVMsg = "请选择你想要指控的对象。";
					$scope.showNoNominate = true;
				} else {
					$scope.userNVMsg = "正等待" + $scope.players[$scope.gamedata.curNominator].displayName + "决定指控对象。";
				}
			} else {
				if ($scope.gamedata.curVoter == $scope.gamedata.myIndex){
					$scope.userNVMsg = "你是否要投票给" + $scope.players[$scope.gamedata.nominated].displayName + "?";
					$scope.showNoVote = true;
				} else {
					$scope.userNVMsg = "正等待" + $scope.players[$scope.gamedata.curVoter].displayName + "决定是否需要投票给" + $scope.players[$scope.gamedata.nominated].displayName + "。";
				}
			}
		}
		
		$scope.showIntoNight = false;
		//$scope.firstRefresh = true;
		$scope.getBoard = function(){
			$http.get('/belltower/getboard').then(function(response){
				
				if (response.data.id == "NE"){
					alert("该游戏已解散");
					$scope.goto('belltower');
					return;
				}
				var oldPhase = $scope.phase
				var oldStatus = $scope.status
				$scope.gamedata = response.data
				$scope.status = response.data.status
				$scope.phase = response.data.phase
				
				if ($scope.phase == $scope.NIGHT){
					$scope.bodyClass = "night"
				} else {
					$scope.bodyClass = "day"
				}
				//$scope.firstRefresh == false && 
				/*
				if (((oldPhase != $scope.NIGHT && $scope.phase == $scope.NIGHT) || (oldStatus != $scope.INGAME && $scope.status == $scope.INGAME))){
					var nightMessageDiv = document.getElementById("nightMessage")
					//alert(nightMessageDiv.style.display)
					nightMessageDiv.style.display = "block";
				}
				*/
				$scope.lord = response.data.lord
				$scope.players = response.data.players
				
				if ($scope.players.length<11){
					$scope.playerRows = [0];
					$scope.playerCols = [];
					var i;
					var row = []
					for (i=0;i<$scope.players.length;i++){
						row.push(i);
					}
					$scope.playerCols.push(row);
				} else {
					$scope.playerRows = [0,1];
					$scope.playerCols = [[0,1,2,3,4,5,6,7,8,9]];
					var i;
					var row = []
					for (i=10;i<$scope.players.length;i++){
						row.push(i-10);
					}
					$scope.playerCols.push(row);
				}
				
				setNominateMessage()
				if (oldStatus != $scope.ENDGAME && $scope.status == $scope.ENDGAME){
					$scope.showReceives = 0;
					var n = $scope.players[$scope.gamedata.myIndex].receives.length;
					//alert(n)
					var displayReceives = function(){
						$timeout(function(){
							$scope.showReceives = $scope.showReceives+1;
							if ($scope.showReceives <= n){
								displayReceives()
							}
							//alert($scope.showReceives)
						},1000);
					}
					displayReceives();
					
				}
				//$scope.firstRefresh = false;
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
