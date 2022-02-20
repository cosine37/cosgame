var setUrl = function(d){
	hearder = "http://";
	server = "localhost:8080";
	return header + server + d;
}

var app = angular.module("belltowerMainApp", ["ngWebSocket", "ngSanitize"]);
app.controller("belltowerMainCtrl", ['$scope', '$window', '$http', '$document', '$websocket',
	function($scope, $window, $http, $document, $websocket){
		var ws = $websocket("ws://" + $window.location.host + "/belltower/allboardsrefresh");
		ws.onError(function(event) {
		});
	
		ws.onClose(function(event) {
		});
	
		ws.onOpen(function() {
		});
		
		var boardws = $websocket("ws://" + $window.location.host + "/belltower/boardrefresh");
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
			$http({url: "/belltower/newboard", method: "POST"}).then(function(response){
				var json_data = '{"type":"notify","content":"refresh"}';
		        ws.send(json_data);
				$scope.goto('belltowercreategame');
			});
		}
		
		$scope.goToBoard = function(index){
			var data = {"boardId" : $scope.boards[index]}
			$http({url: "/belltower/setboardid", method: "POST", params: data}).then(function(response){
				$http.post("/belltower/join").then(function(response){
					//var json_data = '{"type":"notify","content":"refresh"}';
			        boardws.send($scope.boards[index]);
					$scope.goto('belltowercreategame')
				});
			});
		}
		
		$scope.backToBoard = function(index){
			var data = {"boardId" : $scope.boards[index]}
			$http({url: "/belltower/setboardid", method: "POST", params: data}).then(function(response){
				$scope.goto('belltowergame');
			});
		}
		
		$scope.dailyReward = function(){
			$http.post('/belltower/dailyreward').then(function(response){
				$scope.getAccountInfo();
			});
		}
		
		$scope.cleanAccount = function(){
			$http.post('/belltower/cleanaccount').then(function(response){
				$scope.getAccountInfo();
			});
		}
		
		$scope.showPlaceButtons = true;
		$scope.shownPlace = "";
		$scope.openPlace = function(x){
			$scope.shownPlace = x
			$scope.placeStyle = {
				"background-image" : "url('/image/Belltower/" + x + ".jpg')",
				"background-size" : "cover"
			}
		}
		$scope.closePlace = function(){
			$scope.shownPlace = ""
		}
		
		
		$scope.loadingReward = false;
		$scope.showReward = false;
		$scope.dig = function(){
			$scope.loadingReward = true;
			$http({url: "/belltower/dig", method: "POST"}).then(function(response){
				var rewardMsgRaw = response.data.value[0];
				$scope.rewardMsg = "";
				if (rewardMsgRaw.charAt(0) == 'd'){
					$scope.rewardImg = "/image/Belltower/diamond.png";
					$scope.rewardMsg = "获得" + rewardMsgRaw.substring(1) + "颗钻石";
				} else if (rewardMsgRaw.charAt(0) == 'i'){
					$scope.rewardImg = "/image/Belltower/Icons/" + rewardMsgRaw.substring(1,4) + ".png";
					var level = rewardMsgRaw.charAt(1);
					if (level == '1'){
						$scope.rewardMsg = "获得头像："
					} else if (level == '2'){
						$scope.rewardMsg = "获得稀有头像："
					} else if (level == '3'){
						$scope.rewardMsg = "获得史诗头像："
					} else {
						$scope.rewardMsg = "获得头像："
					}
					$scope.rewardMsg = $scope.rewardMsg + rewardMsgRaw.substring(4);
				} else if (rewardMsgRaw.charAt(0) == 'k'){
					$scope.rewardImg = "/image/Belltower/chest.png";
					$scope.rewardMsg = "获得一个宝箱";
				}
				$scope.getAccountInfo()
				for (var i=0;i<2500000000;i++){}
				//time.sleep(2)
				$scope.loadingReward = false;
				$scope.showReward = true;
			});
		}
		
		$scope.openChest = function(){
			$scope.loadingReward = true;
			$http({url: "/belltower/openchest", method: "POST"}).then(function(response){
				/*
				var rewardMsgRaw = response.data.value[0];
				$scope.rewardMsg = "";
				if (rewardMsgRaw.charAt(0) == 'd'){
					$scope.rewardImg = "/image/Belltower/diamond.png";
					$scope.rewardMsg = "获得" + rewardMsgRaw.substring(1) + "枚钻石";
				} else if (rewardMsgRaw.charAt(0) == 'i'){
					$scope.rewardImg = "/image/Belltower/Icons/" + rewardMsgRaw.substring(1,4) + ".png";
					var level = rewardMsgRaw.charAt(1);
					if (level == '1'){
						$scope.rewardMsg = "获得头像："
					} else if (level == '2'){
						$scope.rewardMsg = "获得稀有头像："
					} else if (level == '3'){
						$scope.rewardMsg = "获得史诗头像："
					} else {
						$scope.rewardMsg = "获得头像："
					}
					$scope.rewardMsg = $scope.rewardMsg + rewardMsgRaw.substring(4);
				} else if (rewardMsgRaw.charAt(0) == 'k'){
					$scope.rewardImg = "/image/Belltower/chest.png";
					$scope.rewardMsg = "获得一个宝箱";
				}
				*/
				var rewardMsgsRaw = response.data.value;
				$scope.rewardMsgs = []
				$scope.rewardImgs = []
				for (var i=0;i<rewardMsgsRaw.length;i++){
					var rewardMsgRaw = rewardMsgsRaw[i];
					if (rewardMsgRaw.charAt(0) == 'd'){
						$scope.rewardImg = "/image/Belltower/diamond.png";
						$scope.rewardMsg = rewardMsgRaw.substring(1) + "颗钻石";
					} else if (rewardMsgRaw.charAt(0) == 'i'){
						$scope.rewardImg = "/image/Belltower/Icons/" + rewardMsgRaw.substring(1,4) + ".png";
						var level = rewardMsgRaw.charAt(1);
						if (level == '1'){
							$scope.rewardMsg = "头像："
						} else if (level == '2'){
							$scope.rewardMsg = "稀有头像："
						} else if (level == '3'){
							$scope.rewardMsg = "史诗头像："
						} else {
							$scope.rewardMsg = "头像："
						}
						$scope.rewardMsg = $scope.rewardMsg + rewardMsgRaw.substring(4);
					} else if (rewardMsgRaw.charAt(0) == 'm'){
						$scope.rewardImg = "/image/Belltower/coin.png";
						$scope.rewardMsg = rewardMsgRaw.substring(1) + "枚钱币";
					}
					$scope.rewardImgs.push($scope.rewardImg);
					$scope.rewardMsgs.push($scope.rewardMsg);
				}
				$scope.getAccountInfo()
				for (var i=0;i<2500000000;i++){}
				//time.sleep(2)
				$scope.loadingReward = false;
				$scope.showReward = true;
			});
		}
		
		$scope.getAllBoards = function(){
			$http.get('/belltower/allboards').then(function(response){
				var n = response.data.value.length / 4;
				$scope.boards = []
				$scope.statuses = []
				$scope.lords = []
				$scope.canBack = []
				for (var i=0;i<n;i++){
					$scope.boards.push(response.data.value[i*4])
					var l = response.data.value[i*4+1]
					$scope.lords.push(l)
					var x = response.data.value[i*4+2]
					var t = ''
					if (x == '0'){
						t = '准备中'
					} else if (x == '5'){
						t = '游戏结束'
					} else {
						t = '游戏中'
					}
					$scope.statuses.push(t)
					var y = response.data.value[i*4+3]
					$scope.canBack.push(y)
				}
			});
		}
		
		$scope.getAccountInfo = function(){
			$http.get('/belltower/accountinfo').then(function(response){
				$scope.accountInfo = response.data;
			});
		}
		
		$scope.getAccountInfo();
		$scope.getAllBoards();
		
		ws.onMessage(function(){
			$scope.getAllBoards();
		});
		
}]);
