var setUrl = function(d){
	hearder = "http://";
	server = "localhost:8080";
	return header + server + d;
}

var app = angular.module("richCreateGameApp", ["ngWebSocket"]);
app.controller("richCreateGameCtrl", ['$scope', '$window', '$http', '$document', '$timeout', '$websocket',
	function($scope, $window, $http, $document, $timeout, $websocket){
		var ws = $websocket("ws://" + $window.location.host + "/rich/boardrefresh");
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
		/*
		$scope.changeAvatar = function(x){
			var data = {"x" : x}
			$http({url: "/gravepsycho/setavatar", method: "POST", params: data}).then(function(response){
				ws.send("chooseAvatar");
			});
		}
		
		$scope.useEvent=false
		$scope.eventSwitch = function(){
			if ($scope.useEvent == true){
				$scope.useEvent=false
			} else {
				$scope.useEvent=true
			}
		}
		$scope.startGame = function(){
			var x = 0;
			if ($scope.useEvent){
				x = 1;
			}
			var data = {"useEvent" : x}
			$http({url: "/gravepsycho/startgame", method: "POST", params: data}).then(function(response){
				ws.send("start");
				$scope.goto('gravepsychogame')
			});
		}
		
		$scope.dismiss = function(){
			$http.post('/gravepsycho/dismiss').then(function(response){
				ws.send("dismiss");
			});
		}
		
		$scope.getBoard = function(){
			$http.get('/gravepsycho/getboard').then(function(response){
				$scope.gamedata = response.data
				$scope.id = response.data.id
				$scope.status = response.data.status;
				$scope.playerNames = response.data.playerNames;
				$scope.avatar = response.data.avatar
				if ($scope.id == "NE"){
					$scope.goto('/gravepsycho');
					return;
				}
				if ($scope.status != "0"){
					$scope.goto('/gravepsychogame')
				}
				$scope.lord = response.data.lord
			});
		}
		
		$scope.getBoard();
		
		ws.onMessage(function(){
			$scope.getBoard();
		});
		*/
		
}]);
