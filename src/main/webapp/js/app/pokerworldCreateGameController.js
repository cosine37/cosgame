var setUrl = function(d){
	hearder = "https://";
	server = "localhost:8080";
	return header + server + d;
}

var app = angular.module("pokerworldCreateGameApp", ["ngWebSocket"]);
app.controller("pokerworldCreateGameCtrl", ['$scope', '$window', '$http', '$document', '$timeout', '$websocket',
	function($scope, $window, $http, $document, $timeout, $websocket){
		var ws = $websocket("wss://" + $window.location.host + "/pokerworld/boardrefresh");
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
		
		$scope.showWizardSettings = false;
		$scope.chosenGame = -1;
		$scope.biggestRank = 13;
		$scope.firstPlayer = 0;
		$scope.totalRounds = 0;
		$scope.useDf = 0;
		$scope.useBomb = 0;
		$scope.useMerlin = 0;
		$scope.useStation = 0;
		$scope.useBallroom = 0;
		$scope.useFiveTenBonus = 0;
	
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
		
		$scope.dismiss = function(){
			$http.post("/pokerworld/dismiss").then(function(response){
				ws.send("dismiss");
			});
		}
		
		$scope.volume = 0.5;
		$scope.updateVolume = function() {
			$scope.bgm.volume = $scope.volume;
		};
		
		$scope.bgm = new Audio();
		randomizeBGM = function(){
			$scope.bgm.src ='/sound/Pokerworld/welcome.mp3'
			$scope.bgm.volume = $scope.volume;
		}
		randomizeBGM();
		$scope.bgm.addEventListener("ended", function() {
			randomizeBGM()
			$scope.bgm.play();
		}, true);
		
		
		
		
		playClickSE = function(){
			var audio = new Audio("/sound/Pokerworld/click.wav")
			audio.volume = $scope.volume;
			audio.play();
		}
		
		playRandomizeSE = function(){
			var audio = new Audio("/sound/Pokerworld/randomize.wav")
			audio.volume = $scope.volume;
			audio.play();
		}
		
		$scope.chooseGame = function(x){
			playClickSE()
			$scope.chosenGame = x;
			
		}
		
		$scope.setFirstPlayer = function(x){
			playClickSE()
			$scope.firstPlayer = x;
			
		}
		
		$scope.setBiggestRank = function(x){
			playClickSE()
			$scope.biggestRank = x;
		}
		
		$scope.setTotalRounds = function(x){
			playClickSE()
			$scope.totalRounds = x;
		}
		
		$scope.setUseDf = function(x){
			playClickSE()
			$scope.useDf = x;
		}
		
		$scope.setUseBomb = function(x){
			playClickSE()
			$scope.useBomb = x;
		}
		$scope.setUseMerlin = function(x){
			playClickSE()
			$scope.useMerlin = x;
		}
		$scope.setUseStation = function(x){
			playClickSE()
			$scope.useStation = x;
		}
		$scope.setUseBallroom = function(x){
			playClickSE()
			$scope.useBallroom = x;
		}
		$scope.setUseFiveTenBonus = function(x){
			playClickSE()
			$scope.useFiveTenBonus = x;
		}
		
		$scope.randomFirstPlayer = function(){
			playRandomizeSE()
			var x = Math.floor(Math.random() * $scope.players.length);
			$scope.firstPlayer = x;
		}
		
		$scope.highlightStyle = function(f){
			if (f){
				return {
					"background-color": "goldenrod"
				}
			} else{
				return {}
			}
		}
		
		$scope.startGame = function(){
			if ($scope.chosenGame == -1){
				alert("请选择游戏！");
				return;
			}
			playClickSE();
			
			multiplier = 2;
			x = 0;
			if ($scope.useDf){
				x = 1;
			}
			x = x*multiplier;
			if ($scope.useBomb){
				x = x+1;
			}
			x = x*multiplier;
			if ($scope.useMerlin){
				x = x+1;
			}
			x = x*multiplier;
			if ($scope.useStation){
				x = x+1;
			}
			x = x*multiplier;
			if ($scope.useBallroom){
				x = x+1;
			}
			y = 0
			if ($scope.useFiveTenBonus){
				y = 1;
			}
			
			settings = [-1,0,0,0,0]
			settings[0] = $scope.chosenGame;
			settings[1] = $scope.biggestRank;
			settings[2] = $scope.firstPlayer;
			settings[3] = $scope.totalRounds;
			settings[4] = x;
			settings[5] = y;
			
			var data = {
				"settings" : settings
			}
			
			$http({url: "/pokerworld/startgame", method: "POST", params:data}).then(function(response){
				ws.send("start");
				$scope.goto('pokerworldgame');
			});
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
				
				if ($scope.status == '1' || $scope.status == '301'){
					$scope.goto('pokerworldgame');
				}

			});
		}
		
		$scope.getBoard();
		
		ws.onMessage(function(e){
			var message = e.data
			heartCheck.reset();
			if (message == 'refresh' || message == 'start' || message == 'dismiss'){
				$scope.getBoard();
			}
			
		});
		
		$scope.allRefresh = function(){
			var msg = "refresh";
	        ws.send(msg);
		}
		
		
}]);
