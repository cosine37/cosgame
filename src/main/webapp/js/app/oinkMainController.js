var setUrl = function(d){
	hearder = "http://";
	server = "localhost:8080";
	return header + server + d;
}

var app = angular.module("oinkMainApp", ["ngWebSocket"]);
app.controller("oinkMainCtrl", ['$scope', '$window', '$http', '$document', '$websocket',
	function($scope, $window, $http, $document, $websocket){
		const thisTab = "oink";
		$http.get('/alltabs').then(function(response){
			var tempTabs = response.data;
			for (i=0;i<tempTabs.length;i++){
				if (tempTabs[i].path == thisTab){
					tempTabs[i].style = {
						"padding-top": "0px",
						"font-size": "24px",
						"color": tempTabs[i].color,
						"background-color": tempTabs[i].backgroundColor
					}
				} else {
					tempTabs[i].style = {}
				}
			}
			
			$scope.allTabs = tempTabs;
		});
		
		var ws = $websocket("ws://" + $window.location.host + "/oink/allboardsrefresh");
		ws.onError(function(event) {
		});
	
		ws.onClose(function(event) {
		});
	
		ws.onOpen(function() {
		});
		
		var boardws = $websocket("ws://" + $window.location.host + "/oink/boardrefresh");
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
			$http({url: "/oink/newboard", method: "POST"}).then(function(response){
				var json_data = '{"type":"notify","content":"refresh"}';
		        ws.send(json_data);
		        $scope.goto('oinkcreategame')
			});
			
		}
		
		$scope.goToBoard = function(index){
			var data = {"boardId" : $scope.boards[index]}
			$http({url: "/oink/setboardid", method: "POST", params: data}).then(function(response){
				$http.post("/oink/join").then(function(response){
					//var json_data = '{"type":"notify","content":"refresh"}';
			        boardws.send("refresh");
					$scope.goto('oinkcreategame')
				});
			});
		}
		
		$scope.backToBoard = function(index){
			var data = {"boardId" : $scope.boards[index]}
			$http({url: "/oink/setboardid", method: "POST", params: data}).then(function(response){
				$scope.goto('oinkgame');
			});
		}
		
		$scope.getAllBoards = function(){
			$http.get('/oink/allboards').then(function(response){
				var n = response.data.value.length / 5;
				$scope.boards = []
				$scope.statuses = []
				$scope.lords = []
				$scope.gameModes = []
				$scope.canBack = []
				for (var i=0;i<n;i++){
					$scope.boards.push(response.data.value[i*5])
					var l = response.data.value[i*5+1]
					$scope.lords.push(l)
					var x = response.data.value[i*5+2]
					var t = ''
					if (x == '0'){
						t = '准备中'
					} else if (x == '10'){
						t = '游戏结束'
					} else {
						t = '游戏中'
					}
					$scope.statuses.push(t)
					var z = response.data.value[i*5+3]
					t = ''
					if (x == '0'){
						t = '-'
					} else if (z == '1'){
						t = '初创公司'
					} else if (z == '2'){
						t = '狗头侦探'
					}
					$scope.gameModes.push(t);
					var y = response.data.value[i*5+4]
					$scope.canBack.push(y)
				}
			});
		}
		
		$scope.getAllBoards();
		
		ws.onMessage(function(){
			$scope.getAllBoards();
		});
		
		$scope.chooseAvatar = function(x){
			var data = {"avatarId" : x}
			$http({url: "/oink/chooseavatar", method: "POST", params: data}).then(function(response){
				$scope.getAccountInfo()
			});
		}
		
		$scope.changeSignature = function(){
			var data = {"s" : $scope.mySignature}
			$http({url: "/oink/changesignature", method: "POST", params: data}).then(function(response){
				$scope.getAccountInfo()
			});
		}
		
		$scope.getAccountInfo = function(){
			$http.get('/oink/accountinfo').then(function(response){
				$scope.accountInfo = response.data;
			});
		}
		
		$scope.getAccountInfo()
		
		
		$scope.cards = []
		c = {
			"num" : 5,
			"name" : "五福铁门",
			"coinOn" : 0,
			"barColor": {
				"background-color": "rgb(255,165,0)"
			},
			"iconStyle" : {
				'background-image': 'url(/image/Oink/Startups/5.png)',
				"background-size" : 'cover'
			}
		}
		c2 = {
			"num" : 10,
			"name" : "十年纱窗",
			"coinOn" : 0,
			"barColor": {
				"background-color": "rgb(210,43,43)"
			},
			"iconStyle" : {
				'background-image': 'url(/image/Oink/Startups/10.png)',
				"background-size" : 'cover'
			}
		}
		$scope.cards.push(c);
		
		$scope.groveCards = []
		c = {
			"num": 5,
			"name": "托比亚斯·格雷格森",
			"description": "夏朋婕公寓的房客，克利夫兰的有钱人，但举止粗鲁，经常醉醺醺地回家。有一枚共济会图案的戒指。",
			"avatarStyle": {
				'background-image': 'url(/image/Oink/Grove/Arthur.png)',
				"background-size" : 'cover'
			}
		}
		$scope.groveCards.push(c);
	
}]);
