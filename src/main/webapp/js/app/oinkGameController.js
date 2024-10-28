var setUrl = function(d){
	hearder = "http://";
	server = "localhost:8080";
	return header + server + d;
}

var app = angular.module("oinkGameApp", ["ngWebSocket"]);
app.controller("oinkGameCtrl", ['$scope', '$window', '$http', '$document', '$timeout', '$websocket',
	function($scope, $window, $http, $document, $timeout, $websocket){
		var ws = $websocket("ws://" + $window.location.host + "/oink/boardrefresh");
		ws.onError(function(event) {
		});
		
		ws.onClose(function(event) {
		});
		
		ws.onOpen(function() {
		});
	
		$scope.STARTUPS = 1;
		$scope.chosenCard = -1;
	
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
		
		$scope.kick = function(x){
			var data = {"index" : x}
			$http({url: "/oink/kick", method: "POST", params: data}).then(function(response){
				ws.send("kick");
			});
		}
		
		$scope.dismiss = function(){
			$http.post("/oink/dismiss").then(function(response){
				ws.send("dismiss");
			});
		}
		
		$scope.draw = function(){
			if ($scope.game == $scope.STARTUPS){
				$http.put("/oink/startups/draw").then(function(response){
					ws.send("refresh");
				});
			}
			
		}
		
		$scope.discard = function(){
			if ($scope.chosenCard != -1){
				if ($scope.game == $scope.STARTUPS){
					var data = {"cardIndex" : $scope.chosenCard}
					$http({url: "/oink/startups/discard", method: "PUT", params: data}).then(function(response){
						$scope.chosenCard = -1
						ws.send("kick");
					});
				}
			}
			
		}
		
		$scope.clickCard = function(x){
			if ($scope.game == $scope.STARTUPS){
				if ($scope.phase != 2) return;
				if ($scope.chosenCard == x){
					$scope.hand[$scope.chosenCard].cstyle["margin-top"] = "0px";
					$scope.chosenCard = -1
				} else {
					if ($scope.chosenCard != -1){
						$scope.hand[$scope.chosenCard].cstyle["margin-top"] = "0px";
					}
					$scope.chosenCard = x
					$scope.hand[$scope.chosenCard].cstyle["margin-top"] = "-40px";
				}
			}
		}
		
		$scope.getBoard = function(){
			$http.get('/oink/getboard').then(function(response){
				if (response.data.id == "NE"){
					alert("该游戏已解散");
					$scope.goto('oink');
					return;
				}
				$scope.game = response.data.game
				$scope.status = response.data.status
				$scope.playerNames = response.data.playerNames
				$scope.lord = response.data.lord
				$scope.hand = []
				if ($scope.game == $scope.STARTUPS){
					$scope.gamedata = response.data.startups
					$scope.phase = $scope.gamedata.phase
					$scope.deck = []
					c = {
						"num": 0,
						"isDeck": true
					}
					$scope.deck.push(c);
				}
				$scope.hand = $scope.gamedata.myHand;
				
				var kicked = true;
				for (i=0;i<$scope.playerNames.length;i++){
					if ($scope.playerNames[i] == $scope.username){
						kicked = false;
					}
				}
				if (kicked){
					alert("你已被" + $scope.lord + "踢出");
					$scope.goto('oink');
					return;
				}
			});
		}
		
		$scope.getBoard();
		
		ws.onMessage(function(){
			$scope.getBoard();
		});
		
}]);
