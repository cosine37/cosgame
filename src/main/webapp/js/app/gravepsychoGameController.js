var setUrl = function(d){
	hearder = "http://";
	server = "localhost:8080";
	return header + server + d;
}

var app = angular.module("gravepsychoGameApp", ["ngWebSocket"]);
app.controller("gravepsychoGameCtrl", ['$scope', '$window', '$http', '$document', '$timeout', '$websocket',
	function($scope, $window, $http, $document, $timeout, $websocket){
		var ws = $websocket("ws://" + $window.location.host + "/oink/boardrefresh");
		ws.onError(function(event) {
		});
		
		ws.onClose(function(event) {
		});
		
		ws.onOpen(function() {
		});
		
		$scope.myDecision = "-1"
		$scope.revealed = []
		$scope.removedIndexes = [[0,1,2,3,4],[5,6,7,8,9]]
		$scope.showRemoved = false;
		
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
		
		$scope.decision = function(x){
			var data = {"x" : x}
			$http({url: "/gravepsycho/decision", method: "POST", params: data}).then(function(response){
				ws.send("refresh");
			});
		}
		
		$scope.thiefTarget = -1;
		$scope.clickThiefTarget = function(x){
			if (x == $scope.thiefTarget){
				$scope.thiefTarget = -1;
			} else {
				$scope.thiefTarget = x
			}
		}
		$scope.decisionThief = function(){
			if ($scope.thiefTarget == -1) return;
			var x = 100+$scope.thiefTarget
			var data = {"x" : x}
			$http({url: "/gravepsycho/decision", method: "POST", params: data}).then(function(response){
				$scope.thiefTarget = -1;
				ws.send("refresh");
			});
		}
		
		adjustLogs = function(logElementId){
			var logcontent = document.getElementById(logElementId);
			logcontent.scrollTop = logcontent.scrollHeight;
		}
		
		setRevealedStyle = function(){
			$scope.revealedStyle = []
			for (i=0;i<$scope.revealed.length;i++){
				var imgUrl = "url('/image/Gravepsycho/Cards/" + $scope.revealed[i] + ".png')"
				var singleStyle
				if (i==0 || $scope.revealed.length<10){
					singleStyle = {
						"background": imgUrl,
						"background-size": "cover"
					}
				} else {
					var n = $scope.revealed.length;
					var x = Math.floor((1200 - 126*n)/(n-1) - 0.2)
					var marginLeft = x.toString() + "px"
					singleStyle = {
						"background": imgUrl,
						"background-size": "cover",
						"margin-left": marginLeft
					}
				}
				$scope.revealedStyle.push(singleStyle)
			}
			$scope.removedStyle = []
			for (i=0;i<$scope.removed.length;i++){
				var imgUrl = "url('/image/Gravepsycho/Cards/" + $scope.removed[i] + ".png')"
				var singleStyle = {
					"background": imgUrl,
					"background-size": "cover"
				}
				$scope.removedStyle.push(singleStyle)
			}
		}
		
		$scope.getBoard = function(){
			$http.get('/gravepsycho/getboard').then(function(response){
				$scope.gamedata = response.data
				$scope.status = response.data.status
				if ($scope.status == '4'){
					$scope.goto('gravepsychoendgame')
				}
				$scope.round = response.data.round
				$scope.myIndex = response.data.myIndex;
				$scope.myDecision = response.data.myDecision;
				$scope.playerNames = response.data.playerNames;
				$scope.revealed = response.data.revealed;
				$scope.moneyThisTurn = response.data.moneyThisTurn;
				$scope.myMoney = response.data.myMoney;
				$scope.avatar = response.data.avatar;
				$scope.stillIn = response.data.stillIn;
				$scope.leftover = response.data.leftover;
				$scope.removed = response.data.removed;
				$scope.logs = response.data.logs;
				$scope.useEvent = response.data.useEvent;
				$scope.event = response.data.event;
				$scope.event.estyle = {
					"background":"url('/image/Gravepsycho/Events/" + $scope.event.img + ".png')",
					"background-size":"cover"
				}
				$scope.useThief = response.data.useThief;
				$scope.canUseThief = response.data.canUseThief;
				setRevealedStyle()
				
				$http.post('/citadelsgame/empty').then(function(response){
					adjustLogs("log-zone")
				});
			});
		}
		
		$scope.getBoard();
		
		ws.onMessage(function(){
			$scope.getBoard();
		});
}]);