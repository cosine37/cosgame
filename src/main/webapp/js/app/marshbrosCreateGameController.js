var setUrl = function(d){
	hearder = "http://";
	server = "localhost:8080";
	return header + server + d;
}

var app = angular.module("marshbrosCreateGameApp", ["ngWebSocket"]);
app.controller("marshbrosCreateGameCtrl", ['$scope', '$window', '$http', '$document', '$websocket',
	function($scope, $window, $http, $document, $websocket){
		
		var ws = $websocket("ws://localhost:13737/marshbros/boardrefresh");
		ws.onError(function(event) {
		});
	
		ws.onClose(function(event) {
		});
	
		ws.onOpen(function() {
		});
	
		$scope.settings = [0];
		
		$scope.goto = function(d){
			var x = "http://" + $window.location.host;
			$window.location.href = x + "/" + d;
		}
		
		$http.post('/username').then(function(response){
			$scope.username = response.data.value[0];
			
			$scope.lord = $scope.username
		});
		
		$scope.logout = function(){
			$http({url: "/logout", method: "POST"}).then(function(response){
				$scope.goto('login');
			});
		}
		
		$scope.addBot = function(){
			/*
			$http({url: "/zodiac/addbot", method: "POST"}).then(function(response){
				$scope.getBoard();
			});
			*/
		}
		
		$scope.start = function(){
			/*
			$http({url: "/zodiac/startgame", method: "POST"}).then(function(response){
				$scope.goto('zodiacgame');
			});
			*/
		}
		
		
		
		$scope.getBoard = function(){
			$http.get('/marshbros/getboard').then(function(response){
				$scope.gamedata = response.data
				$scope.id = response.data.id
				$scope.status = response.data.status
				$scope.playerNames = response.data.players
				$scope.lord = response.data.lord
				
				//ws.send(id);
			});
		}
		
		$scope.getBoard()
		
		//var json_data = '{type:notify,content:refresh}';
		
		
		ws.onMessage(function(){
			$scope.getBoard();
		});
		
		/*
		$scope.start = function(){
			if ($scope.soleWolfOption){
				$scope.settings[0] = 1;
			}
			var data = {"settings" : $scope.settings}
			$http({url: "/onenightgame/startgame", method: "POST", params: data}).then(function(response){
				$scope.goto('onenightgame');
			});
			
		}
		
		$scope.addBot = function(){
			$http.post('/onenightgame/addbot').then(function(response){
				$scope.getBoard();
			});
		}
		
		*/
}]);
