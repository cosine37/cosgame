var setUrl = function(d){
	hearder = "http://";
	server = "localhost:8080";
	return header + server + d;
}

var app = angular.module("pokewhatCreateGameApp", ["ngWebSocket"]);
app.controller("pokewhatCreateGameCtrl", ['$scope', '$window', '$http', '$document', '$timeout', '$websocket',
		function($scope, $window, $http, $document, $timeout, $websocket){
		var ws = $websocket("ws://" + $window.location.host + "/pokewhat/boardrefresh");
		ws.onError(function(event) {
		});
	
		ws.onClose(function(event) {
		});
	
		ws.onOpen(function() {
		});
	
		$scope.avatarTableCols = [0,1,2,3,4,5,6]
		$scope.avatarTableRows = [0,1,2]
		$scope.avatarStyles = [];
		$scope.playerAvatarStyles = [];
	
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
		
		$scope.volume = 0.5;
		$scope.updateVolume = function() {
			$scope.bgm.volume = $scope.volume;
		};
		
		$scope.bgm = new Audio();
		randomizeBGM = function(){
			$scope.bgm.src ='/sound/Pokewhat/create_game_bgm.mp3'
			$scope.bgm.volume = $scope.volume;
		}
		randomizeBGM();
		$scope.bgm.addEventListener("ended", function() {
			randomizeBGM()
			$scope.bgm.play();
		}, true);
		
		
		$scope.addBot = function(){
			$http.post('/pokewhatgame/addbot').then(function(response){
				ws.send("addbot");
			});
		}
		
		$scope.startGame = function(){
			if ($scope.playerNames.length<2 || $scope.playerNames.length>5){
				alert("人数必须为2~5人！")
			} else {
				$http.post('/pokewhatgame/startgame').then(function(response){
					ws.send("start");
					$scope.goto('pokewhatgame');
				});
			}
		}
		
		$scope.kick = function(x){
			var data = {"index" : x}
			$http({url: "/pokewhatgame/kick", method: "POST", params: data}).then(function(response){
				ws.send("kick");
			});
		}
		
		$scope.dismiss = function(){
			$http.post('/pokewhatgame/dismiss').then(function(response){
				ws.send("dismiss");
			});
		}
		
		$scope.changeFirstPlayer = function(x){
			var data = {"index" : x}
			$http({url: "/pokewhatgame/changefirstplayer", method: "POST", params: data}).then(function(response){
				ws.send("changeFirstPlayer");
			});
		}
		
		$scope.changeAvatar = function(x){
			var data = {"x" : x}
			$http({url: "/pokewhatgame/changeavatar", method: "POST", params: data}).then(function(response){
				ws.send("changeAvatar");
			});
		}
		
		$scope.setGameEndScore = function(x){
			var data = {"x" : x}
			$http({url: "/pokewhatgame/changegameendscore", method: "POST", params: data}).then(function(response){
				ws.send("changeGameEndScore");
			});
		}
		
		setAvatars = function(){
			$scope.avatarStyles = [];
			for (i=0;i<$scope.avatars.length;i++){
				var imgUrl = "url('/image/Pokewhat/Avatar/" + $scope.avatars[i] + ".png')"
				singleAvatarStyle = {
					"background": imgUrl,
					"background-size": "cover"
				}
				$scope.avatarStyles.push(singleAvatarStyle);
			}
			$scope.playerAvatarStyles = [];
			for (i=0;i<$scope.playerAvatars.length;i++){
				var imgUrl = "url('/image/Pokewhat/Avatar/" + $scope.playerAvatars[i] + ".png')"
				singleAvatarStyle = {
					"background": imgUrl,
					"background-size": "cover",
					"background-color": "rgba(120,120,120,0.2)",
				}
				$scope.playerAvatarStyles.push(singleAvatarStyle);
			}
		}
		
		sleep = function(ms) {
			return new Promise(resolve => setTimeout(resolve, ms));
		}
		
		setTempMsg = async function(){
			$scope.tempMsg = "等待Sleep";
			if ($scope.tf){
				sleep(2000).then(function(){
					$scope.tf = false;
					$scope.tempMsg = "更换形象";
				})
				
			}
		}
		
		$scope.getBoard = function(){
			$http.get('/pokewhatgame/getboard').then(function(response){
				$scope.id = response.data.id;
				if ($scope.id == "NE"){
					$scope.goto('/pokewhat');
					return;
				}
				/*
				if ($scope.tf){
					$timeout(function(){
						$scope.tf = false;
						$scope.tempMsg = "更换形象";
					}, 2000)
				}
				*/
				$scope.gamedata = response.data;
				$scope.lord = response.data.lord;
				$scope.status = response.data.status;
				$scope.curPlayer = parseInt(response.data.curPlayer);
				$scope.playerNames = response.data.playerNames;
				$scope.avatars = response.data.avatars;
				$scope.playerAvatars = response.data.playerAvatars;
				$scope.gameEndScore = response.data.gameEndScore;
				if ($scope.status != "0"){
					$scope.goto('pokewhatgame');
				}
				setAvatars()
			});
		}
		
		$scope.getBoard();
		
		ws.onMessage(function(){
			$scope.getBoard();
		});
		
}]);
