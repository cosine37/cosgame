var setUrl = function(d){
	hearder = "http://";
	server = "localhost:8080";
	return header + server + d;
}

var sleep = function(ms) {
	return new Promise(resolve => setTimeout(resolve, ms));
}

var app = angular.module("gardenwarGameApp", ["ngWebSocket", "ngSanitize"]);
app.controller("gardenwarGameCtrl", ['$scope', '$window', '$http', '$document', '$timeout', '$websocket',
	function($scope, $window, $http, $document, $timeout, $websocket){
		var ws = $websocket("ws://" + $window.location.host + "/gardenwar/boardrefresh");
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
		
		$scope.handDisplay = []
		$scope.playDisplay = []
		$scope.equipDisplay = []
		$scope.baseCardDisplay = []
		$scope.supplyCardDisplay = []
		$scope.target = -1
		$scope.targets = []
		
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
		
		$scope.clickHand = function(x){
			if ($scope.gamedata.curPlayer == $scope.gamedata.myIndex && $scope.gamedata.phase == 2){
				if ($scope.gamedata.askType == 0){ // play card
					var data = {"x" : x}
					$http({url: "/gardenwar/play", method: "POST", params: data}).then(function(response){
						$scope.allRefresh()
					});
				} else if ($scope.gamedata.askType == 2 || $scope.gamedata.askType == 1){
					if (x>=0 && x<$scope.gamedata.myHand.length){
						if ($scope.gamedata.askType == 1 || ($scope.gamedata.askType == 2 && $scope.gamedata.myHand[x].type != 2)){
							if ($scope.target != x){
								$scope.target = x
							} else {
								$scope.target = -1
							}
						}
					}
				} else if ($scope.gamedata.askType == 3){
					if (x>=0 && x<$scope.gamedata.myHand.length){
						/*
						if ($scope.gamedata.askType == 1 || ($scope.gamedata.askType == 2 && $scope.gamedata.myHand[x].type != 2)){
							if ($scope.target != x){
								$scope.target = x
							} else {
								$scope.target = -1
							}
						}*/
						if ($scope.gamedata.myHand[x].img == "sunShroom" || $scope.gamedata.myHand[x].img == "sunShroom2"){
							if ($scope.target != x){
								$scope.target = x
							} else {
								$scope.target = -1
							}
						}
					}
				}
			}
		}
		
		$scope.clickEquip = function(x){
			if ($scope.gamedata.curPlayer == $scope.gamedata.myIndex && $scope.gamedata.phase == 2){
				if ($scope.gamedata.myEquip[x].activated == false){
					var data = {"x" : x}
					$http({url: "/gardenwar/activate", method: "POST", params: data}).then(function(response){
						$scope.allRefresh()
					});
				}
			}
		}
		
		$scope.resolve = function(){
			if ($scope.gamedata.askType == 2 || $scope.gamedata.askType == 1 || $scope.gamedata.askType == 3){
				var targets = [];
				targets.push($scope.target)
				var data = {"targets" : targets}
				$http({url: "/gardenwar/resolve", method: "POST", params: data}).then(function(response){
					$scope.allRefresh()
				});
			}
		}
		
		$scope.cancelResolve = function(){
			if ($scope.gamedata.askType == 2 || $scope.gamedata.askType == 1 || $scope.gamedata.askType == 3){
				var flag = confirm("你确定不选择目标吗？");
				if (flag = false) return;
				var targets = [];
				targets.push(-1)
				var data = {"targets" : targets}
				$http({url: "/gardenwar/resolve", method: "POST", params: data}).then(function(response){
					$scope.allRefresh()
				});
			}
		}
		
		$scope.disableResolve = function(){
			if ($scope.gamedata == null) return true;
			if ($scope.gamedata.askType == 2 || $scope.gamedata.askType == 1 || $scope.gamedata.askType == 3){
				if ($scope.target>=0 && $scope.target<$scope.gamedata.myHand.length){
					return false;
				} else {
					return true;
				}
			}
			
			else {
				return true;
			}
		}
		
		$scope.autoplay = function(){
			if ($scope.gamedata.curPlayer == $scope.gamedata.myIndex){
				$http({url: "/gardenwar/autoplay", method: "POST"}).then(function(response){
					$scope.allRefresh()
				});
			}
		}
		
		canAfford = function(c){
			//alert($scope.gamedata.curPlayerSun)
			if (c.cost > $scope.gamedata.curPlayerSun){
				return false;
			}
			return true;
		}
		
		canAffordBasic = function(x){
			var c = $scope.gamedata.baseCards[x]
			if (canAfford(c) && $scope.gamedata.curPlayerCanBuy[x]){
				return true;
			} else {
				return false;
			}
		}
		canAffordSupply = function(x){
			var c = $scope.gamedata.supply[x]
			if (canAfford(c)){
				return true;
			} else {
				return false;
			}
		}
		
		$scope.buyBasic = function(x){
			if ($scope.gamedata.phase != 4) return
			if (canAffordBasic(x)){
				var data = {"x" : x}
				$http({url: "/gardenwar/buybasic", method: "POST", params: data}).then(function(response){
					$scope.allRefresh()
				});
			}
		}
		
		$scope.buy = function(x){
			if ($scope.gamedata.phase != 4) return
			if (canAffordSupply(x)){
				var data = {"x" : x}
				$http({url: "/gardenwar/buy", method: "POST", params: data}).then(function(response){
					$scope.allRefresh()
				});
			}
		}
		
		$scope.nextPhase = function(){
			var flag = true
			if ($scope.gamedata.phase == 2){
				if ($scope.handDisplay.length > 0){
					flag = confirm("你还有没打出的牌，确定吗？")
				}
			} else if ($scope.gamedata.phase == 3){
				if ($scope.gamedata.curPlayerPea > 0){
					flag = confirm("你还有未使用的攻击点数，确定吗？")
				}
			} else if ($scope.gamedata.phase == 4){
				if ($scope.gamedata.curPlayerSun > 0){
					flag = confirm("你还有未使用的阳光，确定吗？")
				}
			}
			if (flag){
				$http({url: "/gardenwar/nextphase", method: "POST"}).then(function(response){
					$scope.allRefresh()
				});
			}			
		}
		
		setMsgByPhase = function(){
			if ($scope.gamedata.phase == 0){
				$scope.playerMsg = "现在不是你的回合";
			} else if ($scope.gamedata.phase == 2){
				$scope.playerMsg = "出牌阶段";
			} else if ($scope.gamedata.phase == 3){
				$scope.playerMsg = "你可以对一名玩家（和该玩家放置的植物）造成伤害";
			} else if ($scope.gamedata.phase == 4){
				$scope.playerMsg = "你可以购买卡牌";
			}
		}
		
		buildPlayDisplay = function(){
			$scope.playDisplay = []
			var i;
			for (i=0;i<$scope.gamedata.curPlayerPlay.length;i++){
				var c = $scope.gamedata.curPlayerPlay[i];
				var cd = buildCard(c);
				$scope.playDisplay.push(cd);
			}
		}
		
		buildHandDisplay = function(){
			$scope.handDisplay = []
			var i;
			for (i=0;i<$scope.gamedata.myHand.length;i++){
				var c = $scope.gamedata.myHand[i];
				var cd = buildCard(c);
				$scope.handDisplay.push(cd);
			}
		}
		
		buildEquipDisplay = function(){
			$scope.equipDisplay = []
			var i;
			for (i=0;i<$scope.gamedata.myEquip.length;i++){
				var c = $scope.gamedata.myEquip[i];
				var cd = buildCard(c, true);
				$scope.equipDisplay.push(cd);
			}
		}
		
		buildBaseCardDisplay = function(){
			$scope.baseCardDisplay = []
			var i;
			for (i=0;i<$scope.gamedata.baseCards.length;i++){
				var c = $scope.gamedata.baseCards[i];
				var cd = buildCard(c);
				
				if (canAffordBasic(i) || $scope.gamedata.phase != 4){
					cd.affordStyle = {}
				} else {
					cd.affordStyle = {"filter": "brightness(50%)"}
				}
				
				$scope.baseCardDisplay.push(cd);
			}
		}
		
		buildSupplyCardDisplay = function(){
			$scope.supplyCardDisplay = []
			var i;
			for (i=0;i<$scope.gamedata.supply.length;i++){
				var c = $scope.gamedata.supply[i];
				var cd = buildCard(c);
				
				if (canAfford(c) || $scope.gamedata.phase != 4){
					cd.affordStyle = {}
				} else {
					cd.affordStyle = {"filter": "brightness(50%)"}
				}
				$scope.supplyCardDisplay.push(cd);
			}
		}
		
		setTargets = function(){
			$scope.target = -1
			$scope.targets = []
			if ($scope.gamedata.type == 2){
				for (i=0;i<$scope.gamedata.myHand.length;i++){
					$scope,targets.push(0);
				}
			}
		}
		
		$scope.getBoard = function(){
			$http.get('/gardenwar/getboard').then(function(response){
				if (response.data.id == "NE"){
					alert("该游戏已解散");
					$scope.goto('gardenwar');
					return;
				}
				$scope.gamedata = response.data
				//alert(JSON.stringify($scope.gamedata.baseCards[2]))
				setMsgByPhase()
				buildHandDisplay()
				buildPlayDisplay()
				buildEquipDisplay()
				buildBaseCardDisplay()
				buildSupplyCardDisplay()
				setTargets()
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
