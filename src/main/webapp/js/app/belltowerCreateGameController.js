var setUrl = function(d){
	hearder = "http://";
	server = "localhost:8080";
	return header + server + d;
}

var app = angular.module("belltowerCreateGameApp", ["ngWebSocket"]);
app.controller("belltowerCreateGameCtrl", ['$scope', '$window', '$http', '$document', '$timeout', '$websocket',
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
		
		$scope.changeDisplayName = function(){
			var data = {
				"displayName" : $scope.displayName	
			}
				
			$http({url: "/belltower/changedisplayname", method: "POST", params:data}).then(function(response){
				$scope.allRefresh();
			});
		}
		
		$scope.startGame = function(){
			var data = {
				"settings" : $scope.settings	
			}
			
			$http({url: "/belltower/startgame", method: "POST", params:data}).then(function(response){
				ws.send("start");
				$scope.goto('belltowergame');
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
				$scope.lord = response.data.lord
				$scope.players = response.data.players
				$scope.displayName = response.data.myDisplayName;
				if ($scope.status == '1'){
					$scope.goto('belltowergame');
				}
			});
		}
		
		$scope.getBoard();
		
		ws.onMessage(function(e){
			$scope.getBoard();
		});
		
		$scope.allRefresh = function(){
			var msg = "refresh";
	        ws.send(msg);
		}
		
		
}]);
