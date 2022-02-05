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
		
		$scope.NIGHT = 0;
		
		
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
			if ($scope.gamedata.myRole.numPlayerChoose == 0){
				
			} else if ($scope.gamedata.myRole.numPlayerChoose == 1){
				if ($scope.chosenPlayer == x){
					$scope.chosenPlayer = -1
				} else {
					$scope.chosenPlayer = x
				}
			} else if ($scope.gamedata.myRole.numPlayerChoose == 2){
				
			}
		}
		
		$scope.confirmNight = function(){
			$scope.targets[1] = $scope.chosenPlayer;
			$scope.targets[2] = $scope.chosenPlayer2;
			
			var data = {
				"targets" : $scope.targets
			}
			$http({url: "/belltower/confirmnight", method: "POST", params:data}).then(function(response){
				$scope.allRefresh()
			});
		}
		
		$scope.getBoard = function(){
			$http.get('/belltower/getboard').then(function(response){
				if (response.data.id == "NE"){
					alert("该游戏已解散");
					$scope.goto('belltower');
					return;
				}
				$scope.gamedata = response.data
				$scope.status = response.data.status
				$scope.phase = response.data.phase
				$scope.lord = response.data.lord
				$scope.players = response.data.players
				
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
