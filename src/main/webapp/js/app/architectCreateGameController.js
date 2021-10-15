var setUrl = function(d){
	hearder = "http://";
	server = "localhost:8080";
	return header + server + d;
}

var app = angular.module("architectCreateGameApp", ["ngWebSocket"]);
app.controller("architectCreateGameCtrl", ['$scope', '$window', '$http', '$document', '$websocket',
	function($scope, $window, $http, $document, $websocket){
		
		var ws = $websocket("ws://" + $window.location.host + "/architect/boardrefresh");
		ws.onError(function(event) {
		});
	
		ws.onClose(function(event) {
		});
	
		ws.onOpen(function() {
		});
		
		$scope.settings = [1,1,0]
	
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
		
		$scope.dismiss = function(){
			$http.post("/architect/dismiss").then(function(response){
				ws.send("dismiss");
			});
		}
		
		$scope.kick = function(x){
			var data = {"index" : x}
			$http({url: "/architect/kick", method: "POST", params: data}).then(function(response){
				ws.send("kick");
			});
		}
		
		$scope.settingSwitch = function(x){
			if ($scope.settings[x] == 0){
				$scope.settings[x] = 1
			} else {
				$scope.settings[x] = 0
			}
		}
		
		$scope.startGame = function(){
			var data = {
				"settings" : $scope.settings	
			}
			$http({url: "/architect/startgame", method: "POST", params:data}).then(function(response){
				ws.send("start");
				$scope.goto('architectgame');
			});
		}
		
		$scope.getBoard = function(){
			$http.get('/architect/getboard').then(function(response){
				if (response.data.id == "NE"){
					alert("该游戏已解散");
					$scope.goto('architect');
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
					$scope.goto('architect');
					return;
				}
				
				if ($scope.status == '1'){
					$scope.goto('architectgame');
				}

			});
		}
		
		$scope.getBoard();
		
		ws.onMessage(function(){
			$scope.getBoard();
		});
		
}]);
