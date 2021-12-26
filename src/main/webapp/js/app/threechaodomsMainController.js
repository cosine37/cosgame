var setUrl = function(d){
	hearder = "http://";
	server = "localhost:8080";
	return header + server + d;
}

var app = angular.module("threechaodomsMainApp", ["ngWebSocket", "ngSanitize"]);
app.controller("threechaodomsMainCtrl", ['$scope', '$window', '$http', '$document', '$websocket',
	function($scope, $window, $http, $document, $websocket){
		var ws = $websocket("ws://" + $window.location.host + "/pokerworld/allboardsrefresh");
		ws.onError(function(event) {
		});
	
		ws.onClose(function(event) {
		});
	
		ws.onOpen(function() {
		});
		
		var boardws = $websocket("ws://" + $window.location.host + "/pokerworld/boardrefresh");
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
			$http({url: "/threechaodoms/newboard", method: "POST"}).then(function(response){
				var json_data = '{"type":"notify","content":"refresh"}';
		        ws.send(json_data);
		        $scope.goto('threechaodomscreategame')
			});
		}
		
		$scope.goToBoard = function(index){
			var data = {"boardId" : $scope.boards[index]}
			$http({url: "/threechaodoms/setboardid", method: "POST", params: data}).then(function(response){
				$http.post("/threechaodoms/join").then(function(response){
					//var json_data = '{"type":"notify","content":"refresh"}';
			        boardws.send($scope.boards[index]);
					$scope.goto('threechaodomscreategame')
				});
			});
		}
		
		$scope.backToBoard = function(index){
			var data = {"boardId" : $scope.boards[index]}
			$http({url: "/threechaodoms/setboardid", method: "POST", params: data}).then(function(response){
				$scope.goto('threechaodomsgame');
			});
		}
		
		$scope.getAllBoards = function(){
			$http.get('/threechaodoms/allboards').then(function(response){
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
					} else if (x == '3'){
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
		
		$scope.dailyReward = function(){
			$http.post('/threechaodoms/dailyreward').then(function(response){
				$scope.getAccountInfo()
			});
		}
		
		$scope.cleanAccount = function(){
			$http.post('/threechaodoms/cleanaccount').then(function(response){
				$scope.getAccountInfo()
			});
		}
		
		$scope.buySkin = function(x){
			var data = {"id" : x}
			$http({url: "/threechaodoms/buyskin", method: "POST", params: data}).then(function(response){
				$scope.getAccountInfo()
			});
		}
		
		$scope.useSkin = function(x){
			var data = {"id" : x}
			$http({url: "/threechaodoms/useskin", method: "POST", params: data}).then(function(response){
				$scope.getAccountInfo()
			});
		}
		
		$scope.unuseSkin = function(x){
			var data = {"id" : x}
			$http({url: "/threechaodoms/unuseskin", method: "POST", params: data}).then(function(response){
				$scope.getAccountInfo()
			});
		}
		
		var setSkinCards = function(){
			$scope.skinCardStyles = []
			var i;
			for (i=0;i<$scope.accountInfo.skinCards.length;i++){
				var cardStyle = buildCard($scope.accountInfo.skinCards[i])
				$scope.skinCardStyles.push(cardStyle)
			}
		}
		
		$scope.getAccountInfo = function(){
			$http.get('/threechaodoms/accountinfo').then(function(response){
				$scope.accountInfo = response.data;
				
				setSkinCards()
			});
		}
		
		$scope.getAccountInfo()
		$scope.getAllBoards();
		
		ws.onMessage(function(){
			$scope.getAllBoards();
		});
		
}]);
