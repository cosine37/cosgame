var setUrl = function(d){
	hearder = "http://";
	server = "localhost:8080";
	return header + server + d;
}

var app = angular.module("pokerworldMainApp", ["ngWebSocket"]);
app.controller("pokerworldMainCtrl", ['$scope', '$window', '$http', '$document', '$websocket',
	function($scope, $window, $http, $document, $websocket){
		const thisTab = "pokerworld";
		$http.get('/alltabs').then(function(response){
			var tempTabs = response.data;
			for (i=0;i<tempTabs.length;i++){
				if (tempTabs[i].path == thisTab){
					tempTabs[i].style = {
						"padding-top": "0px",
						"font-size": "24px",
						"color": tempTabs[i].color,
						"background-color": tempTabs[i].backgroundColor
					}
				} else {
					tempTabs[i].style = {}
				}
			}
			
			$scope.allTabs = tempTabs;
		});
	
		var ws = $websocket("ws://" + $window.location.host + "/pokerworld/allboardsrefresh");
		ws.onError(function(event) {
		});
	
		ws.onClose(function(event) {
		});
	
		ws.onOpen(function() {
		});
		
		var boardws = $websocket("ws://" + $window.location.host + "/pokerworld/boardrefresh");
		boardws.onError(function(event) {
		});
	
		boardws.onClose(function(event) {
		});
	
		boardws.onOpen(function() {
		});
		
		$scope.wizard = {
			"rank": "wizard",
			"suit": "WZ",
			"color": "blue"
		}
		
		$scope.jester = {
			"rank": "jester",
			"suit": "JE",
			"color": "green"
		}
		
		$scope.jesters = []
		$scope.jesters.push($scope.jester)
		$scope.wizards = []
		$scope.wizards.push($scope.wizard)
		
		bomb = {
			"rank": "bomb",
			"suit": "BM",
			"color": "darkred"
		}
		dragon = {
			"rank": "dragon",
			"suit": "DR",
			"color": "purple"
		}
		fairy = {
			"rank": "fairy",
			"suit": "FR",
			"color": "pink"
		}
		$scope.specials = []
		$scope.specials.push(bomb);
		$scope.specials.push(dragon);
		$scope.specials.push(fairy);
		$scope.shownCardIndex = -1;
	
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
			$http({url: "/pokerworld/newboard", method: "POST"}).then(function(response){
				var json_data = '{"type":"notify","content":"refresh"}';
		        ws.send(json_data);
		        $scope.goto('pokerworldcreategame')
			});
			
		}
		
		$scope.goToBoard = function(index){
			var data = {"boardId" : $scope.boards[index]}
			$http({url: "/pokerworld/setboardid", method: "POST", params: data}).then(function(response){
				$http.post("/pokerworld/join").then(function(response){
					//var json_data = '{"type":"notify","content":"refresh"}';
			        boardws.send("refresh");
					$scope.goto('pokerworldcreategame')
				});
			});
		}
		
		$scope.backToBoard = function(index){
			var data = {"boardId" : $scope.boards[index]}
			$http({url: "/pokerworld/setboardid", method: "POST", params: data}).then(function(response){
				$scope.goto('pokerworldgame');
			});
		}
		
		$scope.getAllBoards = function(){
			$http.get('/pokerworld/allboards').then(function(response){
				var n = response.data.value.length / 5;
				$scope.boards = []
				$scope.statuses = []
				$scope.lords = []
				$scope.gameModes = []
				$scope.canBack = []
				for (var i=0;i<n;i++){
					$scope.boards.push(response.data.value[i*5])
					var l = response.data.value[i*5+1]
					$scope.lords.push(l)
					var x = response.data.value[i*5+2]
					var t = ''
					if (x == '0'){
						t = '准备中'
					} else if (x == '10'){
						t = '游戏结束'
					} else {
						t = '游戏中'
					}
					$scope.statuses.push(t)
					var z = response.data.value[i*5+3]
					t = ''
					if (x == '0'){
						t = '-'
					} else if (z == '0'){
						t = '四副升级'
					} else if (z == '1'){
						t = '巫师牌'
					}
					$scope.gameModes.push(t);
					var y = response.data.value[i*5+4]
					$scope.canBack.push(y)
				}
			});
		}
		
		$scope.getAllBoards();
		
		ws.onMessage(function(){
			$scope.getAllBoards();
		});
		
		
		
		
		
		
		
		
		
		$scope.skins = []
		$scope.skinCategories = ["巫师","小丑","炸弹","巨龙","妖精"]
		skinChosen = function(raw){
			for (x=0;x<$scope.accountInfo.chosenSkins.length;x++){
				if ($scope.accountInfo.chosenSkins[x] == raw) return true;
			}
			return false;
		}
		setSkinsDisplay = function(){
			ranks = ["wizard", "jester", "bomb", "dragon", "fairy"]
			suits = ["WZ", "JE", "BM", "DR", "FR"]
			colors = ["blue", "green", "darkred", "purple", "pink"]
			$scope.skins = []
			for (i=0;i<$scope.accountInfo.allSkinImgs.length;i++){
				singleTypeSkins = []
				for (j=0;j<$scope.accountInfo.allSkinImgs[i].length;j++){
					tc = {
						"rank": ranks[i],
						"suit": suits[i],
						"color": colors[i],
						"raw": $scope.accountInfo.allSkinImgs[i][j],
						"custom": {
							'background-image': 'url(/image/Pokerworld/Skins/' + $scope.accountInfo.allSkinImgs[i][j] + '.png)'
						}
					}
					/*
					tc["custom"] = {
						'background-image': 'url(/image/Pokerworld/Skins/' + $scope.accountInfo.allSkinImgs[i][j] + '.png)'
					}
					*/
					//tc.name = "aaaa"
					tc.chosen = skinChosen($scope.accountInfo.allSkinImgs[i][j]);
					tc.name = $scope.accountInfo.allSkinNames[i][j];
					if ($scope.accountInfo.allSkinImgs[i][j] == "qs"){
						tc["color"] = "grey";
					} else {
						
					}
					
					singleTypeSkins.push(tc);
				}
				$scope.skins.push(singleTypeSkins)
			}
		}
		
		$scope.shownSkins = -1;
		$scope.showSkins = function(x){
			$scope.shownSkins = x;
		}
		
		$scope.clickSkinCard = function(c){
			var data = {"skinId" : parseInt(c.raw)}
			if (c.chosen){
				$http({url: "/pokerworld/cancelchooseskin", method: "POST", params: data}).then(function(response){
					$scope.getAccountInfo();
				})
			} else {
				$http({url: "/pokerworld/chooseskin", method: "POST", params: data}).then(function(response){
					$scope.getAccountInfo();
				})
			}
		}
		
		
		
		playChatSE = function(){
			var audio = new Audio("/sound/Pokerworld/" + $scope.chatSound)
			audio.play();
		}
		playMiningSE = function(){
			var audio = new Audio("/sound/Pokerworld/mining.wav")
			audio.play();
		}
		
		$scope.showReward = false;
		$scope.loadingReward = false;
		$scope.dig = function(){
			playMiningSE()
			$scope.loadingReward = true;
			$scope.chatSound = "gain.wav"
			$http({url: "/pokerworld/dig", method: "POST"}).then(function(response){
				var rewardMsgRaw = response.data.value[0];
				$scope.rewardMsg = "";
				$scope.rewardType = rewardMsgRaw.substring(0,1)
				if (rewardMsgRaw.charAt(0) == 'd'){
					$scope.rewardImg = "/image/Pokerworld/diamond.png";
					if (rewardMsgRaw.substring(1) == "1"){
						
					} else if (rewardMsgRaw.substring(1) == "2"){
						$scope.chatSound = "success.wav"
					} else {
						$scope.chatSound = "cheer.wav"
					}
					$scope.rewardMsg = "获得" + rewardMsgRaw.substring(1) + "颗钻石";
				} else if (rewardMsgRaw.charAt(0) == 's'){
					$scope.rewardImg = "/image/Pokerworld/Skins/" + rewardMsgRaw.substring(2,6) + ".png";
					var level = rewardMsgRaw.charAt(1);
					if (level == '1'){
						$scope.rewardMsg = "获得皮肤："
					} else if (level == '2'){
						$scope.chatSound = "success.wav"
						$scope.rewardMsg = "获得稀有皮肤："
					} else if (level == '3'){
						$scope.chatSound = "cheer.wav"
						$scope.rewardMsg = "获得史诗皮肤："
					} else {
						$scope.rewardMsg = "获得皮肤："
					}
					$scope.rewardMsg = $scope.rewardMsg + rewardMsgRaw.substring(6);
				} else if (rewardMsgRaw.charAt(0) == 'k'){
					$scope.chatSound = "cheer.wav"
					$scope.rewardImg = "/image/Pokerworld/chest.png";
					$scope.rewardMsg = "获得一个宝箱";
				}
				
				$scope.digCards = []
				skinType = rewardMsgRaw.substring(2,3)
				tc = {
					"rank": "wizard",
					"suit": "WZ",
					"color": "blue"
				}
				if (skinType == '1'){
					tc = {
						"rank": "wizard",
						"suit": "WZ",
						"color": "blue"
					}
				} else if (skinType == '2'){
					tc = {
						"rank": "jester",
						"suit": "JE",
						"color": "green"
					}
				} else if (skinType == '3'){
					tc = {
						"rank": "bomb",
						"suit": "BM",
						"color": "darkred"
					}
				} else if (skinType == '4'){
					tc = {
						"rank": "dragon",
						"suit": "DR",
						"color": "purple"
					}
				} else if (skinType == '5'){
					tc = {
						"rank": "fairy",
						"suit": "FR",
						"color": "pink"
					}
				}
				
				tc["custom"] = {
					'background-image': 'url(' + $scope.rewardImg + ')'
				}
				
				//tc["custom"] = $scope.rewardImg
				//alert(JSON.stringify(tc))
				$scope.digCards.push(tc)
				
				$scope.getAccountInfo()
				for (var i=0;i<2500000000;i++){}
				//time.sleep(2)
				$scope.loadingReward = false;
				$scope.showReward = true;
				playChatSE()
			});
		}
		
		$scope.shownPlace = ""
		$scope.openPlace = function(x){
			$scope.shownPlace = x
		}
		$scope.closePlace = function(){
			$scope.shownPlace = ""
		}
		
		$scope.dailyReward = function(){
			$http.post('/pokerworld/dailyreward').then(function(response){
				$scope.getAccountInfo();
			});
		}
		
		$scope.cleanAccount = function(){
			$http.post('/pokerworld/cleanaccount').then(function(response){
				$scope.getAccountInfo();
			});
		}
		$scope.getAccountInfo = function(){
			$http.get('/pokerworld/accountinfo').then(function(response){
				$scope.accountInfo = response.data;
				setSkinsDisplay()
			});
		}
		$scope.getAccountInfo()
		
	
}]);
