var setUrl = function(d){
	hearder = "http://";
	server = "localhost:8080";
	return header + server + d;
}

var app = angular.module("onenightGameApp", []);
app.controller("onenightGameCtrl", ['$scope', '$window', '$http', '$document', '$timeout',
	function($scope, $window, $http, $document, $timeout){
		$scope.rolesSelect = [];
		$scope.indexes = [];
		$scope.playerRoleStyles = [];
		$scope.centerRoleStyles = [];
		$scope.playerSelect = [];
		$scope.centerSelect = [];
		$scope.myRoleStyle = {};
		$scope.updatedRoleStyle = {};
		$scope.roleChooseStyles = [];
		$scope.rolesThisGameStyles = [];
		$scope.status = "0";
		$scope.voteIndex = -1;
		$scope.totalSelected = 0;
		$scope.showConfirmed = true;
		$scope.bodyClass = "day"
		$scope.roleChooseIndexes = [];
	
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
				$scope.getBoard()
			});
		}
		
		$scope.night = function(){
			$http.post('/onenightgame/night').then(function(response){
				$scope.getBoard()
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
			
			if (tp == $scope.choosePlayerNum && tc == $scope.chooseCenterNum){
				$scope.showConfirmed = true;
			} else {
				$scope.showConfirmed = false;
			}
		}
		
		$scope.clickPlayerRole = function(x){
			if ($scope.status == "2" && $scope.confirmed == "n"){
				if ($scope.playerSelect[x] == "y"){
					$scope.playerSelect[x] = "n"
				} else {
					if ($scope.canChooseBoth == "n"){
						for (i=0;i<$scope.centerSelect.length;i++){
							$scope.centerSelect[i] = "n"
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
			} else if ($scope.status == "3" && $scope.voted == "n"){
				$scope.voteIndex = x;
			}  
			updateShowConfirmed()
		}
		
		$scope.clickCenterRole = function(x){
			if ($scope.status == "2" && $scope.confirmed == "n"){
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
		
		$scope.canConfirmNight = function(){
			var ans = true;
			if ($scope.mandatory == "y"){
				
			}
			return ans;
		}
		
		$scope.confirmNight = function(){
			if ($scope.status != "2") return
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
			var data = {"targets" : targets}
			if ($scope.hasSkill == "y" && targets.length > 0){
				$http({url: "/onenightgame/useskill", method: "POST", params: data}).then(function(response){
					$scope.getBoard()
				});
			} else {
				$http({url: "/onenightgame/confirmnight", method: "POST"}).then(function(response){
					$scope.getBoard()
				});
			}
			
		}
		
		$scope.chooseVote = function(x){
			$scope.voteIndex = x;
		}
		
		$scope.confirmVote = function(){
			if ($scope.voteIndex != -1){
				var data = {"index" : $scope.voteIndex}
				$http({url: "/onenightgame/vote", method: "POST", params: data}).then(function(response){
					$scope.getBoard()
				});
			} else {
				alert("请选择你的投票对象")
			}
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
		
		$scope.unshowBigImage = function(){
			$scope.showBigImage = false
		}
		
		setRoleStyles = function(){
			var imgUrl;
			$scope.playerRoleStyles = [];
			$scope.playerSelect = [];
			for (i=0;i<$scope.playerMarks.length;i++){
				$scope.playerSelect.push("n");
				imgUrl = "url('/image/Onenight/qs.png')"
				if ($scope.playerMarks[i] == "-1" || $scope.playerMarks[i] == "-2" || $scope.playerMarks[i] == "-3"){
					
				} else {
					imgUrl = "url('/image/Onenight/Roles/" + $scope.playerMarks[i] + ".png')"
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
				$scope.gamedata = response.data
				$scope.playerNames = response.data.playerNames;
				$scope.playerDisplayNames = response.data.playerDisplayNames;
				$scope.playerMarks = response.data.playerMarks;
				$scope.centerMarks = response.data.centerMarks;
				$scope.choosePlayerNum = parseInt(response.data.choosePlayerNum)
				$scope.chooseCenterNum = parseInt(response.data.chooseCenterNum)
				$scope.canChooseBoth = response.data.canChooseBoth
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
				if ($scope.mandatory == 'y'){
					$scope.showConfirmed = false;
				}
				if ($scope.status == '4') {
					setResultMsg();
				} else if ($scope.status == '2'){
					$scope.centerMsg.unshift("天黑了。")
				} else if ($scope.status == '3'){
					$scope.centerMsg.unshift("天亮了。")
				}
				if ($scope.status == '2'){
					$scope.bodyClass = "night"
				} else {
					$scope.bodyClass = "day"
				}
				$scope.voteIndex = -1;
				setRoleStyles();
			});
		}
		
		$scope.offturnHandle = function(){
			if ($scope.status == "0"){
				$scope.getBoard();
			} else if ($scope.status == "1" && $scope.lord != $scope.username){
				$scope.getBoard();
			} else if ($scope.status == "2" && $scope.confirmed == "y"){
				$scope.getBoard();
			} else if ($scope.status == "3" && $scope.voted == "y"){
				$scope.getBoard();
			}
			$timeout(function(){
			    $scope.offturnHandle();
			},1500);
		}
		
		$scope.offturnHandle()
}]);
