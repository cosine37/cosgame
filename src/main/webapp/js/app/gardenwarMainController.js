var setUrl = function(d){
	hearder = "http://";
	server = "localhost:8080";
	return header + server + d;
}

var app = angular.module("gardenwarMainApp", ["ngWebSocket", "ngSanitize"]);
app.controller("gardenwarMainCtrl", ['$scope', '$window', '$http', '$document', '$websocket',
	function($scope, $window, $http, $document, $websocket){
		$scope.card = {}
		var ws = $websocket("ws://" + $window.location.host + "/gardenwar/allboardsrefresh");
		ws.onError(function(event) {
		});
	
		ws.onClose(function(event) {
		});
	
		ws.onOpen(function() {
		});
		
		var boardws = $websocket("ws://" + $window.location.host + "/gardenwar/boardrefresh");
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
			$http({url: "/gardenwar/newboard", method: "POST"}).then(function(response){
				var json_data = '{"type":"notify","content":"refresh"}';
		        ws.send(json_data);
		        $scope.goto('gardenwarcreategame')
			});
			
		}
		
		$scope.goToBoard = function(index){
			var data = {"boardId" : $scope.boards[index]}
			$http({url: "/gardenwar/setboardid", method: "POST", params: data}).then(function(response){
				$http.post("/gardenwar/join").then(function(response){
					//var json_data = '{"type":"notify","content":"refresh"}';
			        boardws.send("refresh");
					$scope.goto('gardenwarcreategame')
				});
			});
		}
		
		$scope.backToBoard = function(index){
			var data = {"boardId" : $scope.boards[index]}
			$http({url: "/gardenwar/setboardid", method: "POST", params: data}).then(function(response){
				$scope.goto('gardenwargame');
			});
		}
		
		$scope.getAllBoards = function(){
			$http.get('/gardenwar/allboards').then(function(response){
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
		/*
		$scope.makeCard = function(){
			buildCard($scope.card)
		}
		
		var initializeCard = function(){
			$scope.card = {}
			$scope.card.sun = 0;
			$scope.card.pea = 0;
			$scope.card.cost = 2;
			$scope.card.shield = 10;
			$scope.card.taunt = true;
			
			$scope.card.name = "噬碑藤";
			$scope.card.type = "基本";
			$scope.card.clan = "藤蔓";
			$scope.card.img = "sunflower.png"
			$scope.card.desc = "抽1张牌，你可以消耗该牌并弃置一名玩家的放置植物。"
			
			$scope.cardDisplay = buildCard($scope.card)
		}
		
		initializeCard();
		*/
		//buildCard(0)
		
		
		$scope.getAllBoards();
		
		ws.onMessage(function(){
			$scope.getAllBoards();
		});
		
}]);
