var setUrl = function(d){
	hearder = "http://";
	server = "localhost:8080";
	return header + server + d;
}

var app = angular.module("marshbrosMainApp", ["ngWebSocket"]);
app.controller("marshbrosMainCtrl", ['$scope', '$window', '$http', '$document', '$websocket',
	function($scope, $window, $http, $document, $websocket){
		
		var ws = $websocket("ws://" + $window.location.host + "/marshbros/allboardsrefresh");
		ws.onError(function(event) {
		});
	
		ws.onClose(function(event) {
		});
	
		ws.onOpen(function() {
		});
		
		var boardws = $websocket("ws://" + $window.location.host + "/marshbros/boardrefresh");
		boardws.onError(function(event) {
		});
	
		boardws.onClose(function(event) {
		});
	
		boardws.onOpen(function() {
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
		
		$scope.newGame = function(){
			$http({url: "/marshbros/newboard", method: "POST"}).then(function(response){
				var json_data = '{"type":"notify","content":"refresh"}';
		        ws.send(json_data);
				$scope.goto('marshbroscreategame');
			});
		}
		
		$scope.getAllBoards = function(){
			$http.get('/marshbros/allboards').then(function(response){
				var n = response.data.value.length / 4;
				$scope.boards = []
				$scope.statuses = []
				$scope.lords = []
				$scope.canBack = []
				for (var i=0;i<n;i++){
					$scope.boards.push(response.data.value[i*4])
					var l = response.data.value[i*4+1]
					$scope.lords.push(l)
					var x = response.data.value[i*4+2]
					var t = ''
					if (x == '0'){
						t = '准备中'
					} else if (x == '5'){
						t = '游戏结束'
					} else {
						t = '游戏中'
					}
					$scope.statuses.push(t)
					var y = response.data.value[i*4+3]
					$scope.canBack.push(y)
				}
			});
		}
		
		$scope.goToBoard = function(index){
			var data = {"boardId" : $scope.boards[index]}
			$http({url: "/marshbros/setboardid", method: "POST", params: data}).then(function(response){
				$http.post("/marshbros/join").then(function(response){
					//var json_data = '{"type":"notify","content":"refresh"}';
			        boardws.send($scope.boards[index]);
					$scope.goto('marshbroscreategame')
				});
			});
		}
		
		$scope.backToBoard = function(index){
			var data = {"boardId" : $scope.boards[index]}
			$http({url: "/marshbros/setboardid", method: "POST", params: data}).then(function(response){
				$scope.goto('marshbrosgame');
			});
		}
		
		$scope.getAllBoards();
		
		ws.onMessage(function(){
			$scope.getAllBoards();
		});
		
		
}]);
