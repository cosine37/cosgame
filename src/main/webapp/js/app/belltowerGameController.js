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
		
		// Choose Role Section
		/*
		$scope.changeGhost = function(){
			$http.post("/belltower/changeghost").then(function(response){
				$scope.allRefresh()
			});
		}
		
		$scope.confirmRoles = function(){
			$http.post("/belltower/confirmroles").then(function(response){
				$scope.allRefresh()
			});
		}
		*/
		// End Choose Role Section
		
		// Human Choose Place Section
		/*
		$scope.chosenPlace = [0,0,0,0,0,0,0,0,0,0,0,0];
		$scope.clickPlace = function(x){
			if (x>0 && x<$scope.chosenPlace.length){
				if ($scope.chosenPlace[x] == 1){
					$scope.chosenPlace[x] = 0
				} else if ($scope.chosenPlace[x] == 0){
					var numChosen = 0;
					for (i=0;i<$scope.chosenPlace.length;i++){
						numChosen = numChosen+$scope.chosenPlace[i]
					}
					if (numChosen<$scope.gamedata.myNumPlaceNextTurn){
						$scope.chosenPlace[x] = 1
					} else if ($scope.gamedata.myNumPlaceNextTurn == 1){
						for (i=0;i<$scope.chosenPlace.length;i++){
							$scope.chosenPlace[i] = 0;
						}
						$scope.chosenPlace[x] = 1
					}
				}
			}
		}
		
		$scope.submitPlace = function(){
			if ($scope.canSubmitPlace()){
				var data = {
					"targets" : $scope.chosenPlace
				}
				
				$http({url: "/propnight/chooseplace", method: "POST", params:data}).then(function(response){
					$scope.allRefresh()
				});
			}
		}
		
		$scope.canSubmitPlace = function(){
			if ($scope.gamedata == null) return false
			var numChosen = 0;
			for (i=0;i<$scope.chosenPlace.length;i++){
				numChosen = numChosen+$scope.chosenPlace[i]
			}
			if (numChosen == $scope.gamedata.myNumPlaceNextTurn){
				return $scope.showChoosePlace()
			} else {
				return false;
			}
		}
		
		$scope.showChoosePlace = function(){
			if ($scope.gamedata.myRole == 0){
				if ($scope.gamedata.phase == 1){
					return true
				} else {
					return false
				}
			} else if ($scope.gamedata.myRole == 1){
				if ($scope.gamedata.phase == 2){
					return true
				} else {
					return false
				}
			}
		}
		*/
		// End Human Choose Place Section
		$scope.getBoard = function(){
			$http.get('/belltower/getboard').then(function(response){
				if (response.data.id == "NE"){
					alert("该游戏已解散");
					$scope.goto('belltower');
					return;
				}
				$scope.gamedata = response.data
				$scope.status = response.data.status
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
