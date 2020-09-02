var setUrl = function(d){
	hearder = "http://";
	server = "localhost:8080";
	return header + server + d;
}

var app = angular.module("citadelsGameApp", []);
app.controller("citadelsGameCtrl", ['$scope', '$window', '$http', '$document','$timeout',
	function($scope, $window, $http, $document, $timeout){
	
		$scope.goto = function(d){
			var x = "http://" + $window.location.host;
			$window.location.href = x + "/" + d;
		}
		
		$http.post('/username').then(function(response){
			$scope.username = response.data.value[0];
		});
		
		$scope.gamedata = "nothing"
		$scope.hand = []
		$scope.buildable = []
		$scope.canclickhand = []
		$scope.phase = "-1"
		$scope.selectedRole = -1
		$scope.selectedRoleSkill = -1
		$scope.roleNums = []
		$scope.roleOwners = []
		$scope.roleRevealed = []
		$scope.playerNames = []
		$scope.playerStyle = []
		$scope.skillButtons = []
		$scope.chooseHand = []
		$scope.chooseOrDiscard = "choose"
		
		setPlayerStyle = function(){
			$scope.playerStyle = []
			var i
			for (i=0;i<$scope.playerNames.length;i++){
				myStyle = {
						"background-color" : "yellow",
				}
				otherStyle = {
						
				}
				if ($scope.playerNames[i] == $scope.username){
					$scope.playerStyle.push(myStyle)
				} else {
					$scope.playerStyle.push(otherStyle)
				}
			}
		}
		
		setButtons = function(){
			
		}
			
		setHand = function(){
			$scope.canclickhand = $scope.buildable
			if ($scope.phase == "2"){
				
			} else {
				var i
				for (i=0;i<$scope.canclickhand.length;i++){
					$scope.canclickhand[i] = 'n';
				}
			}
			$scope.chooseHand = []
			for (i=0;i<$scope.hand.length;i++){
				$scope.chooseHand.push("n");
			}
		}
		
		adjustLogs = function(){
			var logcontent = document.getElementById("logcontent");
			logcontent.scrollTop = logcontent.scrollHeight;
		}
		
		$scope.startTurn = function(){
			$http.post('/citadelsgame/startturn').then(function(response){
				$scope.getBoard()
			});
		}
		
		$scope.endTurn = function(){
			var i
			var flag = false
			for (i=0;i<$scope.canUseRoleSkill.length;i++){
				if ($scope.canUseRoleSkill[i] == 'y'){
					flag = true;
					break;
				}
			}
			var wouldEnd = true;
			if (flag){
				wouldEnd = confirm("你还有未使用的角色技能，确定继续吗？")
			}
			if (wouldEnd){
				$http.post('/citadelsgame/endturn').then(function(response){
					$scope.getBoard()
				});
			}
		}
		
		$scope.seeCards = function(){
			$http.post('/citadelsgame/seecards').then(function(response){
				$scope.getBoard()
			});
		}
		
		$scope.chooseCard = function(x){
			var data = {"index" : x}
			$http({url: "/citadelsgame/choosecard", method: "POST", params: data}).then(function(response){
				$scope.getBoard()
			});
		}
		
		$scope.taketwo = function(){
			$http.post('/citadelsgame/taketwocoins').then(function(response){
				$scope.getBoard()
			});
		}
		
		$scope.build = function(x){
			var data = {"index" : x}
			$http({url: "/citadelsgame/build", method: "POST", params: data}).then(function(response){
				$scope.getBoard()
			});
		}
		
		$scope.curPlayerChooseRole = function(){
			$http.post('/citadelsgame/curplayerchooserole').then(function(response){
				$scope.getBoard()
			});
		}
		
		$scope.botNextMove = function(){
			$http.post('/citadelsgame/botnextmove').then(function(response){
				$scope.getBoard()
			});
		}
		
		$scope.setSelectedRole = function(x){
			$scope.selectedRole = x;
		}
		
		$scope.setSelectedRoleSkill = function(x){
			$scope.selectedRoleSkill = x;
		}
		
		$scope.chooseSkill = function(x){
			var data = {"index" : x}
			$http({url: "/citadelsgame/chooseskill", method: "POST", params: data}).then(function(response){
				$scope.getBoard()
			});
		}
		
		$scope.disableBuilt = function(playerIndex, builtIndex){
			if ($scope.askType == '2' && $scope.phase == '2'){
				if ($scope.askBuiltInfo[playerIndex][builtIndex] == '-1'){
					return true
				} else {
					return false
				}
			} else if ($scope.askType == '0' && $scope.phase == '2' && $scope.username == $scope.playerNames[playerIndex]){
				if ($scope.canUseCardSkill[builtIndex] == 'y'){
					return false
				} else {
					return true;
				}
				
			} else {
				return true;
			}
		}
		
		$scope.chooseBuilt = function(playerIndex, builtIndex){
			if ($scope.askType == '0' && $scope.phase == '2' && $scope.username == $scope.playerNames[playerIndex]){ //use skill
				var data = {
						"builtIndex" : builtIndex
				}
				$http({url: "/citadelsgame/choosecardskill", method: "POST", params: data}).then(function(response){
					$scope.getBoard()
				})
			} else if ($scope.askType == '2' && $scope.phase == '2'){ // destroy
				var wouldChoose = confirm("需要花费" + $scope.askBuiltInfo[playerIndex][builtIndex] + "￥，确定继续吗？")
				if (wouldChoose){
					var data = {
							"skillIndex" : 1,
							"roleIndex" : 0,
							"playerIndex" : playerIndex,
							"builtIndex" : builtIndex
					}
					$http({url: "/citadelsgame/useskill", method: "POST", params: data}).then(function(response){
						$scope.getBoard()
					})
				}
			}
			
			
		}
		
		$scope.useSkill = function(x){
			var data = {
					"skillIndex" : x,
					"roleIndex" : $scope.selectedRoleSkill,
					"playerIndex" : -1,
					"builtIndex" : 0
					}
			$http({url: "/citadelsgame/useskill", method: "POST", params: data}).then(function(response){
				$scope.getBoard()
			});
		}
		
		$scope.choosePlayer = function(playerIndex){
			var x = 0;
			var data = {
					"skillIndex" : x,
					"roleIndex" : 0,
					"playerIndex" : playerIndex,
					"builtIndex" : 0
			}
			$http({url: "/citadelsgame/useskill", method: "POST", params: data}).then(function(response){
				$scope.getBoard()
			});
		}
		
		$scope.clickHandCard = function(index){
			if ($scope.chooseHand[index] == "y"){
				$scope.chooseHand[index] = "n"
			} else {
				$scope.chooseHand[index] = "y"
			}
		}
		
		$scope.chooseOneFromHand = function(x){
			if ($scope.askId == '99502'){ // South Street
				var builtIndex = parseInt($scope.askBuiltIndex)
				var data = {
						"builtIndex" : builtIndex,
						"x" : x
				}
				$http({url: "/citadelsgame/usecardskill", method: "POST", params: data}).then(function(response){
					$scope.getBoard()
				});
			}
		}
		
		$scope.chooseHandStyle = function(index){
			var selectedStyle = {
					"color" : "blue"
			}
			var notSelectedStyle = {
					"color" : "black"
			}
			if ($scope.chooseHand[index] == "y"){
				return selectedStyle
			} else {
				return notSelectedStyle
			}
		}
		
		$scope.useSkillOnHand = function(x){
			var handChoices = ""
			for (var i=0;i<$scope.chooseHand.length;i++){
				handChoices = handChoices + $scope.chooseHand[i]
			}
			var data={
					"skillIndex": x,
					"handChoices": handChoices
			}
			$http({url: "/citadelsgame/useskillonhand", method: "POST", params: data}).then(function(response){
				$scope.getBoard()
			});
		}
		
		$scope.chooseRole = function(){
			if ($scope.selectedRole == -1){
				alert("Please choose a role!")
			} else {
				var data = {"index" : $scope.selectedRole}
				$http({url: "/citadelsgame/chooserole", method: "POST", params: data}).then(function(response){
					$scope.getBoard()
				});
			}
			
		}
		
		$scope.getBoard = function(){
			$http.get('/citadelsgame/getboard').then(function(response){
				if (response.data.id == "NE"){
					$scope.goto('citadels');
				} else {
					$scope.gamedata = JSON.stringify(response.data)
					$scope.hand = response.data.hand
					$scope.buildable = response.data.buildable
					$scope.phase = response.data.phase
					$scope.revealedCards = response.data.revealedCards
					$scope.status = response.data.status
					$scope.roleNums = response.data.roleNums
					$scope.roleOwners = response.data.roleOwners
					$scope.built = response.data.built
					$scope.coins = response.data.coins
					$scope.handSizes = response.data.handSizes
					$scope.playerNames = response.data.playerNames
					$scope.deckSize = response.data.deckSize
					$scope.roleRevealed = response.data.roleRevealed
					$scope.curPlayer = response.data.curPlayer
					$scope.selectedRole = -1
					$scope.selectedRoleSkill = -1
					$scope.bank = response.data.bank
					$scope.deckSize = response.data.deckSize
					$scope.crown = response.data.crown
					$scope.logs = response.data.logs
					$scope.skillButtons = response.data.skillButtons
					$scope.askId = response.data.askId
					$scope.askType = response.data.askType
					$scope.askLs = response.data.askLs
					$scope.askMsg = response.data.askMsg
					$scope.askBuiltIndex = response.data.askBuiltIndex
					$scope.askBuiltInfo = response.data.askBuiltInfo
					$scope.canUseRoleSkill = response.data.canUseRoleSkill
					$scope.canUseCardSkill = response.data.canUseCardSkill
					$scope.isLord = response.data.isLord
					$scope.scores = response.data.scores
					$scope.netScores = response.data.netScores
					$scope.extraScores = response.data.extraScores
					$scope.yourRole = response.data.yourRole
					$scope.chooseOrDiscard = response.data.chooseOrDiscard
					
					if ($scope.status == '3'){
						$scope.statusDisplay = "End Game"
						alert("游戏结束");
						$scope.goto('citadelsendgame');
					} else {
						if ($scope.status == '2'){
							$scope.statusDisplay = "Take Turns"
						} else if ($scope.status == '1'){
							$scope.statusDisplay = "Choose Role"
						}
						setPlayerStyle()
						setButtons()
						setHand()
					}
					
					for (i=0;i<$scope.scores.length;i++){
						if ($scope.extraScores[i] != "0" && $scope.status != '3'){ 
							$scope.scores[i] = $scope.scores[i] + "(+" + $scope.extraScores[i] + ")"
						}
					}
					
					$http.post('/citadelsgame/empty').then(function(response){
						adjustLogs()
					});
				}
				
			});
		}
		
		$scope.logout = function(){
			$http({url: "/logout", method: "POST"}).then(function(response){
				$scope.goto('login');
			});
		}
		
		$scope.offturnHandle = function(){
			if ($scope.phase == "-1" && $scope.status !='3'){
				$scope.getBoard();
			}
			$timeout(function(){
			    $scope.offturnHandle();
			},1000);
		}
		
		$scope.offturnHandle();
		
}]);