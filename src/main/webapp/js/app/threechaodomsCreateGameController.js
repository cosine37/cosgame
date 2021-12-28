var setUrl = function(d){
	hearder = "http://";
	server = "localhost:8080";
	return header + server + d;
}

var app = angular.module("threechaodomsCreateGameApp", ["ngWebSocket"]);
app.controller("threechaodomsCreateGameCtrl", ['$scope', '$window', '$http', '$document', '$timeout', '$websocket',
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
		
		$scope.startGame = function(){
			
			var data = {
				"settings" : $scope.settings	
			}
			
			$http({url: "/threechaodoms/startgame", method: "POST", params:data}).then(function(response){
				ws.send("start");
				$scope.goto('threechaodomsgame');
			});
			
			
		}
		
		/*
		$scope.startGame = function(){
			var data = {
				"settings" : $scope.settings	
			}
			$http({url: "/threechaodoms/startgame", method: "POST", params:data}).then(function(response){
				ws.send("start");
				$scope.goto('pokerworldgame');
			});
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
				
				if ($scope.status == '1'){
					$scope.goto('pokerworldgame');
				}

			});
		}*/
		
		$scope.getBoard = function(){
			$http.get('/threechaodoms/getboard').then(function(response){
				if (response.data.id == "NE"){
					alert("该游戏已解散");
					$scope.goto('threechaodoms');
					return;
				}
				$scope.gamedata = response.data
				$scope.status = response.data.status
				$scope.lord = response.data.lord
				$scope.players = response.data.players
				if ($scope.status == '1'){
					$scope.goto('threechaodomsgame');
				}
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
