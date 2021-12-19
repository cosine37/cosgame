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
		$scope.playMode = 0;
		$scope.selectedCard = -1;
		$scope.targets = [0,0,0,0,0];
		$scope.changeMode = function(x){
			$scope.playMode = x;
			for (var i=0;i<$scope.gamedata.myHand.length;i++){
				$scope.gamedata.myHand.selected = 0
			}
			$scope.selectedCard = -1;
			$scope.targets = [0,0,0,0,0];
		}
			// Play or Discard -- Discard
		$scope.exileCards = function(){
			var exileIndexes = []
			for (var i=0;i<$scope.gamedata.myHand.length;i++){
				if ($scope.gamedata.myHand.selected == 1){
					exileIndexes.push(i)
				}
			}
			var data = {
					"targets": exileIndexes
				}
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
			// end Play or Discard -- Discard
			// Play or Discard -- Play
		$scope.playCard = function(){
			var data = {
				"cardIndex": $scope.selectedCard,
				"targets": $scope.targets
			}
			$http({url: "/threechaodoms/play", method: "POST", params: data}).then(function(response){
				$scope.allRefresh()
			});
		}
		$scope.canPlaySelectedCard = function(){
			if ($scope.selectedCard == -1){
				return false;
			} else {
				return true;
			}
			
		}
			// end Play or Discard -- Play
		// End Play or Discard Section
		
		$scope.clickHand = function(x){
			if ($scope.gamedata.phase == $scope.MAKEHAND){
				$scope.handSetup(x)
			} else if ($scope.gamedata.phase == $scope.PLAYCARD){
				if ($scope.playMode == 0){
					//$scope.playCard(x, [0])
					if ($scope.selectedCard == x){
						$scope.selectedCard = -1;
					} else {
						$scope.selectedCard = x;
					}
					
				} else if ($scope.playMode == 1){
					$scope.selectCardToExile(x)
				}
				
			}
			
		}
		
		$scope.handStyles = []
		var buildHandStyles = function(){
			$scope.handStyles = []
			for (i=0;i<$scope.gamedata.myHand.length;i++){
				var c = $scope.gamedata.myHand[i]
				$scope.handStyles.push(buildCard(c))
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
