var setUrl = function(d){
	hearder = "http://";
	server = "localhost:8080";
	return header + server + d;
}

var app = angular.module("oinkCreateGameApp", ["ngWebSocket"]);
app.controller("oinkCreateGameCtrl", ['$scope', '$window', '$http', '$document', '$timeout', '$websocket',
	function($scope, $window, $http, $document, $timeout, $websocket){
		var ws = $websocket("ws://" + $window.location.host + "/oink/boardrefresh");
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
		
		var firstTimeEnter = true;
	
		$scope.settings = [0,0];
		$scope.gameMode = 0;
		$scope.chatBox = 0;
		$scope.firstPlayer = 0;
		$scope.gameName = ["抽象小品（请选择游戏）", "初创公司","狗头侦探","","兄莠弟攻","","保卫教宗",""]
		
		$scope.STARTUPS = 1;
		$scope.GROVE = 2;
		$scope.POPE = 6;
		$scope.WEST = 4;
		
		$scope.CHOOSEAVATAR = 1;
		$scope.CHANGESIGNATURE = 2;
		
		$scope.chosenAvatar = -1;
		$scope.useDeluxe = true;
		
		$scope.bgm = new Audio();
		$scope.volume = 0.5;
		randomizeBGM = function(){
			v = Math.floor(Math.random() * 2)+1;
			bgmSrc = '/sound/Oink/create' + v + '.mp3'
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
		
		$scope.playClickSE = function(){
			var audio = new Audio("/sound/Oink/click.wav")
			audio.volume = $scope.volume;
			audio.play();
		}
		
		$scope.playMsgSE = function(){
			var audio = new Audio("/sound/Oink/msg.mp3")
			audio.volume = $scope.volume;
			audio.play();
		}
		
		$scope.playKnockSE = function(){
			var audio = new Audio("/sound/Oink/knock.mp3")
			audio.volume = $scope.volume;
			audio.play();
		}
	
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
			$scope.playClickSE()
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
		
		$scope.chooseAvatar = function(x){
			$scope.playClickSE()
			var data = {"avatarId" : x}
			$http({url: "/oink/chooseavatar", method: "POST", params: data}).then(function(response){
				ws.send("chooseAvatar");
			});
		}
		
		$scope.changeSignature = function(){
			$scope.playClickSE()
			var data = {"s" : $scope.mySignature}
			$http({url: "/oink/changesignature", method: "POST", params: data}).then(function(response){
				ws.send("chooseAvatar");
			});
		}
		
		$scope.showStart = function(){
			if ($scope.username != $scope.lord) return false;
			if ($scope.gameMode < 1) return false;
			
			return true;
		}
		
		$scope.setFirstPlayer = function(x){
			$scope.firstPlayer = x;
		}
		
		$scope.start = function(){
			$scope.playClickSE()
			$scope.settings[0] = $scope.gameMode;
			$scope.settings[1] = $scope.firstPlayer;
			$scope.settings[2] = 0;
			if ($scope.useDeluxe == true && $scope.gameMode == $scope.POPE){
				$scope.settings[2] = 1;
			}
			var data = {"settings" : $scope.settings}
			$http({url: "/oink/startgame", method: "POST", params: data}).then(function(response){
				ws.send("start");
				$scope.goto('oinkgame');
			});
			
		}
		
		$scope.getBoard = function(){
			$http.get('/oink/getboard').then(function(response){
				if (response.data.id == "NE"){
					alert("该游戏已解散");
					$scope.goto('oink');
					return;
				}
				
				
				var np = 1
				if ($scope.playerNames != null){
					np = $scope.playerNames.length;
				}
				
				
				$scope.gamedata = response.data
				$scope.status = response.data.status
				$scope.playerNames = response.data.playerNames
				$scope.lord = response.data.lord
				var kicked = true;
				for (i=0;i<$scope.playerNames.length;i++){
					if ($scope.playerNames[i] == $scope.username){
						kicked = false;
						
						$scope.myAvatarStyles = $scope.gamedata.accounts[i].avatarStyles;
						$scope.myAvatars = $scope.gamedata.accounts[i].avatars;
						$scope.mySignature = $scope.gamedata.accounts[i].signature;
					}
				}
				if (kicked){
					alert("你已被" + $scope.lord + "踢出");
					$scope.goto('oink');
					return;
				}
				
				if ($scope.playerNames.length > np && firstTimeEnter == false){
					$scope.playKnockSE()
				}
				firstTimeEnter = false
				
				if ($scope.status == "1"){
					$scope.goto('oinkgame')
				}
			});
		}
		
		$scope.getBoard();
		
		ws.onMessage(function(){
			$scope.getBoard();
		});
		
}]);
