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
		
		$scope.bgm = new Audio();
		$scope.volume = 0.5;
		randomizeBGM = function(){
			v = Math.floor(Math.random() * 4)+1;
			bgmSrc = '/sound/Rich/create_' + v + '.mp3'
			$scope.bgm.src = bgmSrc
			$scope.bgm.volume = $scope.volume;
		}
		$scope.updateVolume = function() {
			$scope.bgm.volume = $scope.volume;
		};
		$scope.bgm.addEventListener("ended", function() {
			randomizeBGM()
			$scope.bgm.play();
		}, true);
		randomizeBGM();
		
		$scope.startMoney = 15000;
		$scope.startSalary = 2000;
		$scope.endCondition = 52;
		$scope.firstPlayer = 0;
		$scope.mapId = 0;
		$scope.useGTA = 0;
		$scope.useNEW = 0;
		
		$scope.startGame = function(){
			var settings = [0, $scope.startMoney, $scope.startSalary,$scope.endCondition,$scope.firstPlayer,$scope.mapId, $scope.useGTA, $scope.useNEW]
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
				$scope.players = response.data.players;
				
				//alert($scope.players)
			});
		}
		
		$scope.getBoard();
		
		ws.onMessage(function(){
			$scope.getBoard();
		});
		
		$scope.getAccountInfo = function(){
			$http.get('/rich/accountinfo').then(function(response){
				$scope.accountInfo = response.data;
			});
		}
		
		$scope.chooseAvatar = function(x){
			var data = {"avatarId" : x}
			$http({url: "/rich/chooseavatar", method: "POST", params: data}).then(function(response){
				$scope.getAccountInfo();
				$scope.getBoard();
			});
		}
		
		$scope.getAccountInfo()
		
}]);
