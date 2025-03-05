var setUrl = function(d){
	hearder = "https://";
	server = "localhost:8080";
	return header + server + d;
}

var app = angular.module("richCreateGameApp", ["ngWebSocket"]);
app.controller("richCreateGameCtrl", ['$scope', '$window', '$http', '$document', '$timeout', '$websocket',
	function($scope, $window, $http, $document, $timeout, $websocket){
		var ws = $websocket("wss://" + $window.location.host + "/rich/boardrefresh");
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
			var x = "https://" + $window.location.host;
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
		
		$scope.startMoney = 15000;
		$scope.startSalary = 2000;
		$scope.endCondition = 0;
		
		$scope.startGame = function(){
			var settings = [0, $scope.startMoney, $scope.startSalary,$scope.endCondition]
			var data = {"settings" : settings}
			$http({url: "/rich/startgame", method: "POST", params: data}).then(function(response){
				ws.send("start");
				$scope.goto('richgame')
			});
		}
		
		$scope.dismiss = function(){
			$http.post('/rich/dismiss').then(function(response){
				ws.send("dismiss");
			});
		}
		
		$scope.getBoard = function(){
			$http.get('/rich/getboard').then(function(response){
				$scope.gamedata = response.data
				$scope.id = response.data.id
				
				//$scope.playerNames = response.data.playerNames;
				if ($scope.id == "NE"){
					$scope.goto('rich');
					return;
				}
				$scope.status = response.data.status;
				if ($scope.status != "0"){
					$scope.goto('richgame')
				}
				
				$scope.lord = response.data.lord
			});
		}
		
		$scope.getBoard();
		
		ws.onMessage(function(){
			$scope.getBoard();
		});
		
		
}]);
