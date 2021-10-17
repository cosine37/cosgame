var setUrl = function(d){
	hearder = "http://";
	server = "localhost:8080";
	return header + server + d;
}

var app = angular.module("pokerworldGameApp", ["ngWebSocket"]);
app.controller("pokerworldGameCtrl", ['$scope', '$window', '$http', '$document', '$timeout', '$websocket',
	function($scope, $window, $http, $document, $timeout, $websocket){
		var ws = $websocket("ws://" + $window.location.host + "/pokerworld/boardrefresh");
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
		
		$scope.cardStyles = [];
		$scope.cardDisplay = [];
		
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
		
		translateRawCard = function(raw){
			var card = {}
			var r = raw.substring(0,1);
			var s = raw.substring(1,2);
			var c = "black";
			if (r == "T") r = "10";
			if (raw == "JO"){
				r = "joker"
				s = "\u265b";
				c = "red";
			}
			if (raw == "jo"){
				r = "joker"
				s = "\u2655";
				c = "black";
			}
			if (s == "s"){
				s = "\u2660";
				c = "black";
			}
			if (s == "h"){
				s = "\u2665";
				c = "red";
			}
			if (s == "d"){
				s = "\u2666";
				c = "red";
			}
			if (s == "c"){
				s = "\u2663";
				c = "black";
			}
			card["rank"] = r
			card["suit"] = s
			card["color"] = c
			return card
		}
		
		setCardStyles = function(){
			$scope.cardDisplay = [];
			var i = 0;
			while (i<$scope.myCards.length){
				var rawCard = $scope.myCards.substring(i,i+2);
				
				$scope.cardDisplay.push(translateRawCard(rawCard));
				i=i+2;
			}
		}
		
		$scope.getBoard = function(){
			$http.get('/pokerworld/getboard').then(function(response){
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
				$scope.myCards = response.data.myCards
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
				
				setCardStyles()

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
