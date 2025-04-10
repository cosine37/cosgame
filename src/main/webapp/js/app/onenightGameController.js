var setUrl = function(d){
	hearder = "https://";
	server = "localhost:8080";
	return header + server + d;
}

var app = angular.module("onenightGameApp", ["ngWebSocket"]);
app.controller("onenightGameCtrl", ['$scope', '$window', '$http', '$document', '$timeout', '$websocket',
	function($scope, $window, $http, $document, $timeout, $websocket){
		var ws = $websocket("wss://" + $window.location.host + "/onenight/boardrefresh");
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
			//alert("error!")
		});
	
		ws.onClose(function(event) {
			//alert("closed!")
		});
	
		ws.onOpen(function() {
			heartCheck.start();
		});
		$scope.rolesSelect = [];
		$scope.indexes = [];
		$scope.playerRoleStyles = [];
		$scope.centerRoleStyles = [];
		$scope.playerStatusStyles = [];
		$scope.playerSelect = [];
		$scope.centerSelect = [];
		$scope.statusSelect = [];
		$scope.myRoleStyle = {};
		$scope.updatedRoleStyle = {};
		$scope.updatedStatusStyle = {};
		$scope.finalStatusStyle = {};
		$scope.roleChooseStyles = [];
		$scope.rolesThisGameStyles = [];
		$scope.status = "0";
		$scope.voteIndex = -1;
		$scope.totalSelected = 0;
		$scope.showConfirmed = true;
		$scope.bodyClass = "day"
		$scope.roleChooseIndexes = [];
		$scope.detectiveIndex = -1;
		$scope.centerZoneMsg = "中央区域"
	
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
		
		$scope.selectRole = function(x){
			if ($scope.lord != $scope.username) return
			$scope.totalSelected = 0;
			for (i=0;i<$scope.rolesSelect.length;i++){
				$scope.totalSelected = $scope.totalSelected + $scope.rolesSelect[i];
			}
			if ($scope.rolesSelect[x] == 1){
				$scope.rolesSelect[x] = 0;
				$scope.totalSelected = $scope.totalSelected-1;
			} else {
				if ($scope.totalSelected == $scope.playerNames.length+3){
					
				} else {
					$scope.rolesSelect[x] = 1;
					$scope.totalSelected = $scope.totalSelected+1;
				}
			}
		}
		
		$scope.submitRolesSelect = function(){
			var data = {"roles" : $scope.rolesSelect}
			$http({url: "/onenightgame/setroles", method: "POST", params: data}).then(function(response){
				$scope.allRefresh()
			});
		}
		
		$scope.night = function(){
			$http.post('/onenightgame/night').then(function(response){
				$scope.allRefresh()
			});
		}
		
		$scope.dismiss = function(){
			$http.post("/onenight/dismiss").then(function(response){
				ws.send("dismiss");
			});
		}
		
		updateShowConfirmed = function(){
			if ($scope.mandatory != 'y') return
			var tp = 0;
			for (i=0;i<$scope.playerSelect.length;i++){
				if ($scope.playerSelect[i] == "y"){
					tp++;
				}
			}
			var tc = 0;
			for (i=0;i<$scope.centerSelect.length;i++){
				if ($scope.centerSelect[i] == "y"){
					tc++;
				}
			}
			
			var ts = 0;
			for (i=0;i<$scope.statusSelect.length;i++){
				if ($scope.statusSelect[i] == "y"){
					ts++;
				}
			}
			
			if ($scope.choosePlayerNum > 0 && $scope.chooseStatusNum > 0){
				$scope.showConfirmed = false;
				if (tp == $scope.choosePlayerNum) $scope.showConfirmed = true;
				if (ts == $scope.chooseStatusNum) $scope.showConfirmed = true;
				return;
			}
			
			if (tp == $scope.choosePlayerNum && tc == $scope.chooseCenterNum && ts == $scope.chooseStatusNum){
				$scope.showConfirmed = true;
			} else {
				$scope.showConfirmed = false;
			}
		}
		
		$scope.clickPlayerStatus = function(x){
			if (($scope.status == "2" || $scope.status == "7") && $scope.confirmed == "n" && $scope.hasSkill == "y"){
				if ($scope.statusSelect[x] == "y"){
					$scope.statusSelect[x] = "n"
				} else {
					$scope.playerSelect[x] = "n"
					if ($scope.canChooseBoth == "n"){
						for (i=0;i<$scope.playerSelect.length;i++){
							$scope.playerSelect[i] = "n"
						}
					}
					var t = 0;
					for (i=0;i<$scope.statusSelect.length;i++){
						if ($scope.statusSelect[i] == "y"){
							t++;
						}
					}
					if (t < $scope.chooseStatusNum){
						$scope.statusSelect[x] = "y";
					} else {
						if ($scope.chooseStatusNum == 1){
							for (i=0;i<$scope.statusSelect.length;i++){
								$scope.statusSelect[i] = "n"
							}
							$scope.statusSelect[x] = "y";
						} else {
							
						}
					}
				}
			}
			updateShowConfirmed()
		}
		
		$scope.clickSelfStatus = function(){
			if ($scope.canChooseSelfStatus == "y"){
				$scope.clickPlayerStatus($scope.myIndex);
			}
		}
		
		$scope.clickPlayerRole = function(x){
			if (($scope.status == "2" || $scope.status == "7") && $scope.confirmed == "n" && $scope.hasSkill == "y"){
				if ($scope.playerSelect[x] == "y"){
					$scope.playerSelect[x] = "n"
				} else {
					$scope.statusSelect[x] = "n"
					if ($scope.canChooseBoth == "n"){
						for (i=0;i<$scope.centerSelect.length;i++){
							$scope.centerSelect[i] = "n"
						}
						for (i=0;i<$scope.statusSelect.length;i++){
							$scope.statusSelect[i] = "n"
						}
					}
					var t = 0;
					for (i=0;i<$scope.playerSelect.length;i++){
						if ($scope.playerSelect[i] == "y"){
							t++;
						}
					}
					if (t < $scope.choosePlayerNum){
						$scope.playerSelect[x] = "y";
					} else {
						if ($scope.choosePlayerNum == 1){
							for (i=0;i<$scope.playerSelect.length;i++){
								$scope.playerSelect[i] = "n"
							}
							$scope.playerSelect[x] = "y";
						} else {
							
						}
					}
				}
			} else if (($scope.status == "3" || $scope.status == "6")&& $scope.voted == "n"){
				$scope.voteIndex = x;
			}  
			updateShowConfirmed()
		}
		
		$scope.clickCenterRole = function(x){
			if (($scope.status == "2" || $scope.status == "7") && $scope.confirmed == "n" && $scope.hasSkill == "y"){
				if ($scope.centerSelect[x] == "y"){
					$scope.centerSelect[x] = "n"
				} else {
					if ($scope.canChooseBoth == "n"){
						for (i=0;i<$scope.playerSelect.length;i++){
							$scope.playerSelect[i] = "n"
						}
					}
					var t = 0;
					for (i=0;i<$scope.centerSelect.length;i++){
						if ($scope.centerSelect[i] == "y"){
							t++;
						}
					}
					if (t < $scope.chooseCenterNum){
						$scope.centerSelect[x] = "y";
					} else {
						if ($scope.chooseCenterNum == 1){
							for (i=0;i<$scope.centerSelect.length;i++){
								$scope.centerSelect[i] = "n"
							}
							$scope.centerSelect[x] = "y";
						} else {
							
						}
					}
				}
			}
			updateShowConfirmed()
		}
		
		$scope.confirmNight = function(){
			if ($scope.status != "2" && $scope.status != "7") return
			var targets = [];
			for (i=0;i<$scope.playerSelect.length;i++){
				if ($scope.playerSelect[i] == "y"){
					targets.push(i)
				}
			}
			for (i=0;i<$scope.centerSelect.length;i++){
				if ($scope.centerSelect[i] == "y"){
					targets.push(i+100)
				}
			}
			for (i=0;i<$scope.statusSelect.length;i++){
				if ($scope.statusSelect[i] == "y"){
					targets.push(i+200)
				}
			}
			var data = {"targets" : targets}
			if ($scope.hasSkill == "y" && targets.length > 0){
				$http({url: "/onenightgame/useskill", method: "POST", params: data}).then(function(response){
					$scope.allRefresh()
				});
			} else {
				$http({url: "/onenightgame/confirmnight", method: "POST"}).then(function(response){
					$scope.allRefresh()
				});
			}
		}
		
		$scope.idiotNight = function(x){
			var data = {"option" : x}
			$http({url: "/onenightgame/useskillidiot", method: "POST", params: data}).then(function(response){
				$scope.allRefresh()
			});
		}
		
		$scope.chooseVote = function(x){
			$scope.voteIndex = x;
		}
		
		$scope.confirmVote = function(){
			if ($scope.voteIndex != -1){
				var data = {"index" : $scope.voteIndex}
				$http({url: "/onenightgame/vote", method: "POST", params: data}).then(function(response){
					$scope.allRefresh()
				});
			} else {
				alert("请选择你的投票对象")
			}
		}
		
		$scope.forfeitVote = function(){
			if (confirm("你确定要弃权吗？")){
				var data = {"index" : -1}
				$http({url: "/onenightgame/vote", method: "POST", params: data}).then(function(response){
					$scope.allRefresh()
				});
			}
		}
		
		$scope.restart = function(){
			$http.post('/onenightgame/restart').then(function(response){
				$scope.allRefresh()
			});
		}
		
		setBigImageStyle = function(){
			$scope.bigImageStyle = {
				"height": "609px", 
				"width": "392px", 
				"position": "absolute",
				"left": "50%",
				"top": "50%",
				"margin-left": "-196px",
				"margin-top": "-280px",
				"background": "url(" + $scope.bigImage + ")", 
				"background-size": "cover"
			}
			
			$scope.bigImageDivStyle = {
				"position": "absolute",
				"left": "0%",
				"top": "0%",
				"height": "100%",
				"width": "100%",
				"background": "rgba(150, 150, 150, 0.5)"
			}
		}
		
		$scope.showRoleChooseImg = function(index){
			$scope.showBigImage = true
			$scope.bigImage = "/image/Onenight/Roles/" + $scope.rolesChoose[index] + ".png"
			setBigImageStyle()
		}
		
		$scope.showRoleThisGameBigImage = function(index){
			$scope.showBigImage = true
			$scope.bigImage = "/image/Onenight/Roles/" + $scope.rolesThisGame[index] + ".png"
			setBigImageStyle()
		}
		
		$scope.showMyInitialBigImage = function(){
			$scope.showBigImage = true
			$scope.bigImage = "/image/Onenight/Roles/" + $scope.initialRole + ".png"
			setBigImageStyle()
		}
		
		$scope.showMyUpdatedBigImage = function(){
			$scope.showBigImage = true
			$scope.bigImage = "/image/Onenight/Roles/" + $scope.updatedRole + ".png"
			setBigImageStyle()
		}
		
		$scope.unshowBigImage = function(){
			$scope.showBigImage = false
		}
		
		setStatusStyles = function(){
			var imgUrl;
			$scope.playerStatusStyles = [];
			$scope.statusSelect = [];
			for (i=0;i<$scope.statusMarks.length;i++){
				$scope.statusSelect.push("n");
				imgUrl = "url('/image/Onenight/Statuses/" + $scope.statusMarks[i] + ".png')"
				if ($scope.statusMarks[i] == 'TARGET'){
					imgUrl = "url('/image/Onenight/Statuses/s99.png')"
				}
				var singleStyle = {
					"background": imgUrl,
					"background-size": "cover"
				}
				
				$scope.playerStatusStyles.push(singleStyle)
			}
			imgUrl = "url('/image/Onenight/Statuses/" + $scope.updatedStatus + ".png')"
			$scope.updatedStatusStyle = {
				"background": imgUrl,
				"background-size": "cover"
			}
			imgUrl = "url('/image/Onenight/Statuses/" + $scope.finalStatus + ".png')"
			$scope.finalStatusStyle = {
				"background": imgUrl,
				"background-size": "cover"
			}
		}
		
		setRoleStyles = function(){
			var imgUrl;
			$scope.playerRoleStyles = [];
			$scope.playerSelect = [];
			for (i=0;i<$scope.playerMarks.length;i++){
				$scope.playerSelect.push("n");
				imgUrl = "url('/image/Onenight/qs.png')"
				if ($scope.playerMarks[i] == "-1" || $scope.playerMarks[i] == "-2" || $scope.playerMarks[i] == "-3" || $scope.playerMarks[i] == "-5"){
					
				} else if ($scope.playerMarks[i] == "-4"){
					imgUrl = "url('/image/Onenight/wolfcard.png')"
				} else {
					imgUrl = "url('/image/Onenight/Roles/" + $scope.playerMarks[i] + ".png')"
				}
				if (i == $scope.detectiveIndex && $scope.status == '3'){
					imgUrl = "url('/image/Onenight/Roles/" + $scope.detectiveRoleImg + ".png')"
				}
				var singleStyle = {
					"background": imgUrl,
					"background-size": "cover"
				}
				$scope.playerRoleStyles.push(singleStyle)
			}
			imgUrl = "url('/image/Onenight/Roles/" + $scope.initialRole + ".png')"
			$scope.myRoleStyle = {
				"background": imgUrl,
				"background-size": "cover"
			}
			imgUrl = "url('/image/Onenight/Roles/" + $scope.updatedRole + ".png')"
			$scope.updatedRoleStyle = {
				"background": imgUrl,
				"background-size": "cover"
			}
			$scope.centerRoleStyles = [];
			$scope.centerSelect = [];
			for (i=0;i<$scope.centerMarks.length;i++){
				$scope.centerSelect.push("n");
				imgUrl = "url('/image/Onenight/qs.png')"
				if ($scope.centerMarks[i] == "-1" || $scope.centerMarks[i] == "-2"){
					
				} else {
					imgUrl = "url('/image/Onenight/Roles/" + $scope.centerMarks[i] + ".png')"
				}
				var singleStyle = {
					"background": imgUrl,
					"background-size": "cover"
				}
				$scope.centerRoleStyles.push(singleStyle)
			}
			$scope.roleChooseIndexes = [];
			$scope.roleChooseStyles = [];
			for (i=0;i<$scope.rolesChoose.length;i++){
				imgUrl = "url('/image/Onenight/Roles/" + $scope.rolesChoose[i] + ".png')"
				var singleStyle = {
					"background": imgUrl,
					"background-size": "cover"
				}
				$scope.roleChooseStyles.push(singleStyle)
			}
			var y = 0;
			var slRoleChooseIndexes = [];
			for (i=0;i<$scope.rolesChoose.length;i++){
				slRoleChooseIndexes.push(i);
				y++;
				if (y == 13){
					$scope.roleChooseIndexes.push(slRoleChooseIndexes)
					slRoleChooseIndexes = [];
					y = 0;
				}
			}
			if (slRoleChooseIndexes.length>0){
				$scope.roleChooseIndexes.push(slRoleChooseIndexes)
			}
			
			$scope.rolesThisGameStyles = [];
			for (i=0;i<$scope.rolesThisGame.length;i++){
				imgUrl = "url('/image/Onenight/Roles/" + $scope.rolesThisGame[i] + ".png')"
				var singleStyle = {
					"background": imgUrl,
					"background-size": "cover"
				}
				$scope.rolesThisGameStyles.push(singleStyle)
			}
		}
		
		setResultMsg = function(){
			$scope.centerMsg = []
			$scope.centerMsg.push("投票结束。")
			$scope.centerMsg.push("你获得了" + $scope.numVotes[$scope.myIndex] + "票。")
			var voteMsg = "";
			for (i=0;i<$scope.playerNames.length;i++){
				var x = parseInt($scope.playerVotes[i]);
				var s = ""
				if (x == -1){
					s = $scope.playerNames[i] + "弃权。";
				} else {
					s = $scope.playerNames[i] + "投给了" + $scope.playerNames[x] + "。";
				}
				voteMsg = voteMsg + s
			}
			$scope.centerMsg.push(voteMsg);
			var flag = true;
			var voteOutMsg = "";
			for (i=0;i<$scope.votedOut.length;i++){
				if ($scope.votedOut[i] == "y"){
					if (flag){
						flag = false;
						voteOutMsg = $scope.playerNames[i];
					} else {
						voteOutMsg = voteOutMsg + "," + $scope.playerNames[i];
					}
				}
			} 
			if (flag){
				voteOutMsg = "没有人被公投出局。"
			} else {
				voteOutMsg = voteOutMsg + "被公投出局。"
			}
			$scope.centerMsg.push(voteOutMsg);
			var winMsg = "获胜者：";
			flag = true;
			for (i=0;i<$scope.winPlayers.length;i++){
				if (flag){
					flag = false;
					winMsg = winMsg + $scope.winPlayers[i];
				} else {
					winMsg = winMsg + ", " + $scope.winPlayers[i];
				}
			}
			if (flag){
				winMsg = "没有获胜者"
			}
			winMsg = winMsg + "。"
			$scope.centerMsg.push(winMsg)
		}
		
		$scope.getBoard = function(){
			$http.get('/onenightgame/getboard').then(function(response){
				if (response.data.id == "NE"){
					alert("该游戏已解散");
					$scope.goto('onenight');
					return;
				}
				$scope.gamedata = response.data
				$scope.playerNames = response.data.playerNames;
				$scope.playerDisplayNames = response.data.playerDisplayNames;
				$scope.playerMarks = response.data.playerMarks;
				$scope.centerMarks = response.data.centerMarks;
				$scope.statusMarks = response.data.statusMarks;
				$scope.choosePlayerNum = parseInt(response.data.choosePlayerNum)
				$scope.chooseCenterNum = parseInt(response.data.chooseCenterNum)
				$scope.chooseStatusNum = parseInt(response.data.chooseStatusNum)
				$scope.canChooseBoth = response.data.canChooseBoth
				$scope.canChooseSelfStatus = response.data.canChooseSelfStatus
				$scope.lord = response.data.lord;
				$scope.status = response.data.status
				$scope.canNight = response.data.canNight;
				$scope.rolesThisGame = response.data.rolesThisGame
				$scope.initialRole = response.data.initialRole
				$scope.initialRoleName = response.data.initialRoleName
				$scope.myIndex = parseInt(response.data.myIndex)
				$scope.mandatory = response.data.mandatory
				$scope.hasSkill = response.data.hasSkill
				$scope.showUpdatedRole = response.data.showUpdatedRole
				$scope.updatedRole = response.data.updatedRole
				$scope.centerMsg = response.data.centerMsg
				$scope.confirmed = response.data.confirmed
				$scope.voted = response.data.voted
				$scope.rolesChoose = response.data.rolesChoose
				$scope.numVotes = response.data.numVotes;
				$scope.playerVotes = response.data.playerVotes;
				$scope.votedOut = response.data.votedOut
				$scope.winPlayers = response.data.winPlayers
				$scope.detectiveIndex = parseInt(response.data.detectiveIndex)
				$scope.detectiveRoleImg = response.data.detectiveRoleImg
				$scope.sentinelIndex = response.data.sentinelIndex
				$scope.soleWolf = response.data.soleWolf;
				$scope.finalRoles = response.data.finalRoles;
				$scope.firstPlayer = response.data.firstPlayer;
				$scope.restrictedPlayer = response.data.restrictedPlayer;
				$scope.restrictedIndex = response.data.restrictedIndex;
				$scope.beggarIndex = response.data.beggarIndex;
				$scope.useStatus = response.data.useStatus;
				$scope.updatedStatus = response.data.updatedStatus;
				$scope.finalStatus = response.data.finalStatus;
				$scope.showFinalStatus = response.data.showFinalStatus;
				
				//alert($scope.useStatus);
				if ($scope.canNight == "n" || $scope.rolesSelect.length == 0){
					$scope.rolesSelect = [];
					for (i=0;i<$scope.rolesChoose.length;i++){
						$scope.rolesSelect.push(0);
					}
				}
				var x = $scope.myIndex+1;
				$scope.indexes = [];
				for (i=0;i<$scope.playerNames.length;i++){
					if (x == $scope.playerNames.length){
						x = 0;
					}
					if (x == $scope.myIndex) break;
					$scope.indexes.push(x);
					x = x+1;
				}
				$scope.showConfirmed = true;
				if ($scope.mandatory == 'y' && $scope.hasSkill == 'y'){
					$scope.showConfirmed = false;
				}
				if ($scope.status == '4') {
					setResultMsg();
					$scope.playerMarks = $scope.finalRoles
					$scope.updatedRole = $scope.playerMarks[$scope.myIndex]
				} else if ($scope.status == '2'){
					$scope.centerMsg.unshift("天黑了。")
				} else if ($scope.status == '3' || $scope.status == '6'){
					var morningMsg = "天亮了，初始发言玩家为" + $scope.firstPlayer + "。";
					if ($scope.status == '6'){
						morningMsg = "口吃法官宣布额外进行一轮发言，初始发言玩家为" + $scope.firstPlayer + "。";
					}
					if ($scope.restrictedIndex != '-1'){
						morningMsg = morningMsg + $scope.restrictedPlayer + "中了森林诅咒，只能用方言（如不会方言则只能用英语）发言。"
					}
					
					$scope.centerMsg.unshift(morningMsg);
				} else if ($scope.status == '7'){
					$scope.centerMsg.unshift("现在是黄昏。")
				}
				if ($scope.status == '2'){
					$scope.bodyClass = "night"
				} else if ($scope.status == '7'){
					$scope.bodyClass = "dusk"
				} else {
					$scope.bodyClass = "day"
				}
				$scope.centerZoneMsg = "中央区域"
				if ($scope.soleWolf == "y"){
					$scope.centerZoneMsg = "中央区域  (若你是独狼，系统会自动显示该区域一张身份，系统会尽可能保证显示的身份为“人”阵营)"
				}
				$scope.voteIndex = -1;
				setRoleStyles();
				setStatusStyles();
			});
		}
		$scope.getBoard();
		ws.onMessage(function(e){
			//alert(JSON.stringify(e.data));
			var message = e.data
			heartCheck.reset();
			if (message == 'refresh'){
				$scope.getBoard();
			}
			
		});
		
		$scope.allRefresh = function(){
			var msg = "refresh";
	        ws.send(msg);
		}
}]);
