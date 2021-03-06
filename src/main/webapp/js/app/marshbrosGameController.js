var setUrl = function(d){
	hearder = "http://";
	server = "localhost:8080";
	return header + server + d;
}

var app = angular.module("marshbrosGameApp", ["ngWebSocket"]);
app.controller("marshbrosGameCtrl", ['$scope', '$window', '$http', '$document', '$websocket',
	function($scope, $window, $http, $document, $websocket){
	
		var ws = $websocket("ws://" + $window.location.host + "/marshbros/boardrefresh");
		ws.onError(function(event) {
		});
	
		ws.onClose(function(event) {
		});
	
		ws.onOpen(function() {
		});
	
		$scope.settings = [0];
		$scope.handStyles = [];
		$scope.areaStyles = [];
		$scope.myIndex = 0;
		$scope.roleIndex = -1;
		$scope.shownInstruction = "";
		
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
		
		
		
		$scope.appoint = function(x){
			var data = {"index" : x}
			$http({url: "/marshbros/appoint", method: "POST", params: data}).then(function(response){
				$scope.getBoard()
			});
		}
		
		$scope.raid = function(){
			var data = {"index" : $scope.roleIndex}
			$http({url: "/marshbros/raid", method: "POST", params: data}).then(function(response){
				$scope.getBoard()
			});
		}
		
		$scope.showAreaCard = function(x){
			$scope.curAreaCardIndex = x;
			imgUrl = "url('/image/Marshbros/Empty/" + $scope.areaCards[$scope.myIndex][x] + ".png')"
			var singleStyle = {
				"background": imgUrl,
				"background-size": "cover"
			}
			$scope.choosedRoleStyle = singleStyle
		}
		
		$scope.showInstructions = function(s){
			$scope.shownInstruction = s;
		}
		
		$scope.openChoices = function(x,y){
			if ($scope.roleIndex == y){
				$scope.roleIndex = -1;
				$scope.choosedRoleStyle = {}
				$scope.showInstructions("")
			} else {
				$scope.roleIndex = y;
				$scope.showAreaCard($scope.roleIndex);
			}
		}
		
		$scope.cancelChoosed = function(){
			$scope.openChoices($scope.myIndex, $scope.roleIndex)
		}
		
		setAreaStyle = function(){
			$scope.areaStyles = [];
			for (j=0;j<$scope.areaCards.length;j++){
				var singleAreaStyles = []
				for (i=0;i<$scope.areaCards[j].length;i++){
					imgUrl = "url('/image/Marshbros/Empty/" + $scope.areaCards[j][i] + ".png')"
					var singleStyle = {
						"background": imgUrl,
						"background-size": "cover"
					}
					//alert(JSON.stringify(singleStyle))
					singleAreaStyles.push(singleStyle)
				}
				$scope.areaStyles.push(singleAreaStyles)
			}
		}
		
		setHandStyle = function(){
			$scope.handStyles = [];
			for (i=0;i<$scope.hand.length;i++){
				imgUrl = "url('/image/Marshbros/Card/" + $scope.hand[i] + ".png')"
				var singleStyle = {
					"background": imgUrl,
					"background-size": "cover"
				}
				$scope.handStyles.push(singleStyle)
			}
		}
		
		setUI = function(){
			setAreaStyle()
			setHandStyle()
		}
		
		$scope.getBoard = function(){
			$http.get('/marshbros/getboard').then(function(response){
				$scope.gamedata = response.data
				$scope.id = response.data.id
				$scope.status = response.data.status
				$scope.playerNames = response.data.players
				$scope.lord = response.data.lord
				$scope.hand = response.data.hand
				$scope.areaCards = response.data.areaCards;
				$scope.myIndex = parseInt(response.data.myIndex);
				
				setUI();
				//ws.send(id);
			});
		}
		
		$scope.getBoard()
		
		
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
