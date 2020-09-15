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
		$scope.roleStyle = []
		$scope.hand = []
		$scope.handStyle = []
		$scope.handDisabled = []
		$scope.built = []
		$scope.builtStyle = []
		$scope.builtDisabled = []
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
		$scope.chooseBuilts = []
		$scope.playerRoleStyle=[]
		$scope.roleStyle=[]
		$scope.roleStyleSkills=[]
		$scope.chooseOrDiscard = "choose"
		$scope.showBigImage = false
		$scope.bigImage = ""
		$scope.bigImageStyle = {}
		$scope.bigImageDivStyle = {}
		$scope.chooseRoleTableStyle = {}
		$scope.chooseRoleNumCol = 4
		$scope.showRoleSelection = false
		
		$scope.roleTable = []
		
		
		getRoleImageByNum = function(x){
			var ans=""
			for (i=0;i<$scope.roleNums.length;i++){
				if ($scope.roleNums[i] == x){
					ans = $scope.roleImgs[i]
					break
				}
			}
			return ans
		}
			
		setPlayerStyle = function(){
			$scope.playerStyle = []
			$scope.builtDisabled = []
			$scope.builtStyle = []
			$scope.playerRoleStyle = []
			$scope.chooseBuilts = []
			var i
			for (i=0;i<$scope.playerNames.length;i++){
				myStyle = {
						"background-color" : "#dddddd",
				}
				otherStyle = {
						
				}
				if ($scope.playerNames[i] == $scope.username){
					$scope.playerStyle.push(myStyle)
				} else {
					$scope.playerStyle.push(otherStyle)
				}
				
				var imgUrl
				if ($scope.roleRevealed[i] == "-1"){
					imgUrl = "url('/image/Citadels/role.png')"
				} else {
					imgUrl = "url('/image/Citadels/Roles/" + getRoleImageByNum($scope.roleRevealed[i]) + ".png')"
				}
				var singleRoleStyle = {
					"background": imgUrl,
					"background-size": "cover",
					"float": "left",
				}
				if ($scope.roleRevealed[i] == $scope.killedRole && $scope.killedRole != -1){
					singleRoleStyle = {
						"background": imgUrl,
						"background-color" : "grey",
						"background-blend-mode" : "screen",
						"background-size": "cover",
						"float": "left",
					}
				}
				$scope.playerRoleStyle.push(singleRoleStyle)
				
				var singleBuiltDisabled = []
				var singleBuiltStyle = []
				var singleChooseBuilts = []
				
				for (j=0;j<$scope.built[i].length;j++){
					var x = $scope.disableBuilt(i,j)
					singleBuiltDisabled.push(x)
					var imgUrl = "url('/image/Citadels/Cards/" + $scope.built[i][j] + ".png')"
					var imgUrlGradient = "url('/image/Citadels/Cards/" + $scope.built[i][j] + ".png'), linear-gradient(#f00, #00f)"
					var marginLeft = "0px"
					if ($scope.built[i].length == 7 && j != 0){
						marginLeft = "-12px"
					} else if ($scope.built[i].length == 8 && j != 0){
						marginLeft = "-21px"
					} else if ($scope.built[i].length == 9 && j != 0){
						marginLeft = "-28px"
					} else if ($scope.built[i].length == 10 && j != 0){
						marginLeft = "-33px"
					} else if ($scope.built[i].length == 11 && j != 0){
						marginLeft = "-37px"
					} 
					var singleStyle = {
						"background": imgUrlGradient,
						"background-size": "cover",
						"float": "left",
						"position": "relative",
						"margin-left": marginLeft,
						"background-blend-mode" : "lighten"
					}
					if (x){
						singleStyle = {
							"background": imgUrl,
							"background-size": "cover",
							"float": "left",
							"position": "relative",
							"margin-left": marginLeft
						}
					}
					singleBuiltStyle.push(singleStyle)
					singleChooseBuilts.push("n")
				}
				$scope.chooseBuilts.push(singleChooseBuilts)
				$scope.builtDisabled.push(singleBuiltDisabled)
				$scope.builtStyle.push(singleBuiltStyle)
				
			}
			//alert($scope.builtStyle[0][1].background)
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
			$scope.handDisabled = []
			for (i=0;i<$scope.hand.length;i++){
				var x = 'n'
				if ($scope.phase != '2'){
					x = 'y'
				}
				if ($scope.canclickhand[i] == 'n'){
					x = 'y'
				}
				if ($scope.askType != "0"){
					x = 'y'
				}
				$scope.handDisabled.push(x)
			}
			
			$scope.handStyle = []
			for (i=0;i<$scope.hand.length;i++){
				var imgUrl = "url('/image/Citadels/Cards/" + $scope.hand[i] + ".png')"
				var marginLeft = "0px"
				if ($scope.hand.length == 6 && i != 0){
					marginLeft = "-10px"
				}
				if ($scope.hand.length >= 7 && i != 0){
					marginLeft = "-25px"
				}
				if ($scope.hand.length >= 8 && i != 0){
					marginLeft = "-36px"
				}
				if ($scope.hand.length >= 9 && i != 0){
					marginLeft = "-45px"
				}
				if ($scope.hand.length >= 10 && i != 0){
					marginLeft = "-60px"
				}
				if ($scope.hand.length >= 12 && i != 0){
					marginLeft = "-75px"
				}
				if ($scope.hand.length >= 18 && i != 0){
					marginLeft = "-85px"
				}
				var singleHandStyle = {
						"background": imgUrl,
						"margin-left" : marginLeft,
						"background-size": "cover",
						"float": "left"
					}
				if ($scope.handDisabled[i] == 'y'){
					singleHandStyle = {
							"background": imgUrl,
							"background-color" : "grey",
							"background-blend-mode" : "screen",
							"margin-left" : marginLeft,
							"background-size": "cover",
							"float" : "left",
						}
				}
				$scope.handStyle.push(singleHandStyle)
			}
			$scope.tempRevealedTopStyle = []
			for (i=0;i<$scope.tempRevealedTop.length;i++){
				var imgUrl = "url('/image/Citadels/Cards/" + $scope.tempRevealedTop[i] + ".png')"
				var marginLeft = "0px"
				singleHandStyle = {
					"background": imgUrl,
					"background-color" : "grey",
					"background-blend-mode" : "screen",
					"margin-left" : marginLeft,
					"background-size": "cover",
					"float" : "left"
				}
				$scope.tempRevealedTopStyle.push(singleHandStyle)
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
			if ($scope.handDisabled[x] == 'y'){
				return
			}
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
			if ($scope.roleOwners[x] == '-1'){
				$scope.selectedRole = x;
			}
		}
		
		$scope.setSelectedRoleSkill = function(x){
			if ($scope.askLs[x] == 'y'){
				$scope.selectedRoleSkill = x;
			}
			
		}
		
		$scope.chooseSkill = function(x){
			var data = {"index" : x}
			$http({url: "/citadelsgame/chooseskill", method: "POST", params: data}).then(function(response){
				$scope.getBoard()
			});
		}
		
		$scope.disableBuilt = function(playerIndex, builtIndex){
			if ($scope.askType == '2' && $scope.phase == '2'){ // destroy
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
				
			} else if ($scope.askType == '6' &&  $scope.phase == '2'){ // beautify
				if ($scope.askBuiltInfo[playerIndex][builtIndex] == '-1'){
					return true
				} else {
					return false
				}
			}
			
			else {
				return true;
			}
		}
		
		$scope.chooseBuilt = function(playerIndex, builtIndex){
			if ($scope.builtDisabled[playerIndex][builtIndex]){
				return
			}
			if ($scope.askType == '0' && $scope.phase == '2' && $scope.username == $scope.playerNames[playerIndex]){ //use built skill
				var data = {
						"builtIndex" : builtIndex
				}
				$http({url: "/citadelsgame/choosecardskill", method: "POST", params: data}).then(function(response){
					$scope.getBoard()
				})
			} else if ($scope.askType == '2' && $scope.phase == '2'){
				if ($scope.askId == '99306'){// chemical plant
					var x = 80000 + playerIndex * 100 + builtIndex;
					var data = {
							"builtIndex": 0,
							"x": x
					}
					$http({url: "/citadelsgame/usecardskill", method: "POST", params: data}).then(function(response){
						$scope.getBoard()
					})
				} 
				
				else { // use skill, destroy & take beautify
					var wouldChoose = true
					if ($scope.askId == '1081'){ // warlord destroy
						wouldChoose = confirm("需要花费" + $scope.askBuiltInfo[playerIndex][builtIndex] + "￥，确定继续吗？")
					}
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
				
				
			} else if ($scope.askType == '6' && $scope.phase == '2'){ // beautify
				if ($scope.chooseBuilts[playerIndex][builtIndex] == "n"){
					var limit = parseInt($scope.askLimit)
					var i,j;
					var count = 0;
					for (i=0;i<$scope.chooseBuilts.length;i++){
						for (j=0;j<$scope.chooseBuilts[i].length;j++){
							if ($scope.chooseBuilts[i][j] == "y"){
								count = count+1;
							}
						}
					}
					if (count<limit){
						$scope.chooseBuilts[playerIndex][builtIndex] = "y"
					}
				} else {
					$scope.chooseBuilts[playerIndex][builtIndex] = "n"
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
			if ($scope.askLs[x] == "n"){
				return
			}
			if ($scope.askId == '99502' || $scope.askId == '99305'){ // South Street & Framework
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
			var imgUrl = "url('/image/Citadels/Cards/" + $scope.hand[index] + ".png')"
			var selectedImgUrl = "url('/image/Citadels/Cards/" + $scope.hand[index] + ".png'), linear-gradient(#f00, #f00)"
			var marginLeft = "0px"
			if ($scope.hand.length == 6 && index != 0){
				marginLeft = "-10px"
			}
			if ($scope.hand.length >= 7 && index != 0){
				marginLeft = "-25px"
			}
			if ($scope.hand.length >= 8 && index != 0){
				marginLeft = "-36px"
			}
			if ($scope.hand.length >= 9 && index != 0){
				marginLeft = "-45px"
			}
			if ($scope.hand.length >= 10 && index != 0){
				marginLeft = "-60px"
			}
			if ($scope.hand.length >= 12 && index != 0){
				marginLeft = "-75px"
			}
			if ($scope.hand.length >= 18 && index != 0){
				marginLeft = "-85px"
			}
			var selectedStyle = {
				"background": selectedImgUrl,
				"background-size": "cover",
				"float": "left",
				"margin-left" :marginLeft,
				"background-blend-mode" : "lighten"
			}
			var notSelectedStyle = {
				"background": imgUrl,
				"background-size": "cover",
				"float": "left",
				"margin-left" :marginLeft
			}
			
			
			if ($scope.chooseHand[index] == "y"){
				return selectedStyle
			} else {
				return notSelectedStyle
			}
		}
		
		$scope.chooseOneFromHandStyle = function(index){
			var imgUrl = "url('/image/Citadels/Cards/" + $scope.hand[index] + ".png')"
			var marginLeft = "0px"
			if ($scope.hand.length == 6 && index != 0){
				marginLeft = "-10px"
			}
			if ($scope.hand.length >= 7 && index != 0){
				marginLeft = "-25px"
			}
			if ($scope.hand.length >= 8 && index != 0){
				marginLeft = "-36px"
			}
			if ($scope.hand.length >= 9 && index != 0){
				marginLeft = "-45px"
			}
			if ($scope.hand.length >= 10 && index != 0){
				marginLeft = "-60px"
			}
			if ($scope.hand.length >= 12 && index != 0){
				marginLeft = "-75px"
			}
			if ($scope.hand.length >= 18 && index != 0){
				marginLeft = "-85px"
			}
			var notSelectedStyle = {
				"background": imgUrl,
				"background-size": "cover",
				"float": "left",
				"margin-left" :marginLeft
			}
			
			disabledStyle = {
				"background": imgUrl,
				"background-color" : "grey",
				"background-blend-mode" : "screen",
				"margin-left" : marginLeft,
				"background-size": "cover",
				"float" : "left",
			}
			if ($scope.askLs[index] == "n") {
				return disabledStyle
			} else {
				return notSelectedStyle
			}
		}
		
		$scope.disabledChooseOneFromHand = function(index){
			if ($scope.askLs[index] == "n") {
				return true
			} else {
				return false
			}
		}
		
		$scope.revealedStyle = function(index){
			var imgUrl = "url('/image/Citadels/Cards/" + $scope.revealedCards[index] + ".png')"
			var notSelectedStyle = {
				"background": imgUrl,
				"background-size": "cover",
				"float": "left"
			}
			return notSelectedStyle
		}
		
		$scope.chooseAllHand = function(){
			for (var i=0;i<$scope.chooseHand.length;i++){
				$scope.chooseHand[i] = "y"
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
		
		$scope.useBeautifySkill = function(x){
			var builtIndex = 0;
			for (i=0;i<$scope.chooseBuilts.length;i++){
				for (j=0;j<$scope.chooseBuilts[i].length;j++){
					if ($scope.chooseBuilts[i][j] == "y"){
						builtIndex = builtIndex*100+j+1;
					}
				}
			}
			var data = {
					"skillIndex" : x,
					"roleIndex" : 0,
					"playerIndex" : 0,
					"builtIndex" : builtIndex
					}
			$http({url: "/citadelsgame/useskill", method: "POST", params: data}).then(function(response){
				$scope.getBoard()
			});
		}
		
		$scope.cancelSkill = function(){
			$http.post('/citadelsgame/cancelskill').then(function(response){
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
		
		setChooseRoleStyle = function(){
			$scope.chooseRoleDivStyle = {
				"position": "absolute",
				"left": "0%",
				"top": "0%",
				"height": "100%",
				"width": "100%",
				"background": "rgba(150, 150, 150)"
			}
			$scope.roleStyle=[]
			$scope.roleStyleSkills=[]
			for (i=0;i<$scope.roleImgs.length;i++){
				var imgUrl = "url('/image/Citadels/Roles/" + $scope.roleImgs[i] + ".png')"
				var singleStyle = {
					"background": imgUrl,
					"background-size": "cover",
					"float": "left"
				}
				if ($scope.roleOwners[i] == '-2'){
					singleStyle = {
						"background": imgUrl,
						"background-color" : "rgb(204, 204, 0)",
						"background-blend-mode" : "screen",
						"background-size": "cover",
						"float": "left"
					}
				}
				
				if ($scope.askLs[i] == 'y'){
					$scope.roleStyleSkills.push(singleStyle)
				} else {
					if ($scope.roleOwners[i] != '-2'){
						cannotChooseStyle = {
							"background": imgUrl,
							"background-color" : "grey",
							"background-blend-mode" : "screen",
							"background-size": "cover",
							"float": "left"
						}
					} else {
						cannotChooseStyle = singleStyle
					}
					
					$scope.roleStyleSkills.push(cannotChooseStyle)
				}
				
				if ($scope.roleOwners[i] == '-3'){
					singleStyle = {
						"background": imgUrl,
						"background-color" : "grey",
						"background-blend-mode" : "screen",
						"background-size": "cover",
						"float": "left"
					}
				}
				$scope.roleStyle.push(singleStyle)
			}
		}
		
		$scope.unshowBigImage = function(){
			$scope.showBigImage = false
		}
		
		$scope.showHand = function(index){
			$scope.showBigImage = true
			$scope.bigImage = "/image/Citadels/Cards/" + $scope.hand[index] + ".png"
			setBigImageStyle()
		}
		
		$scope.showRevealedCard = function(index){
			$scope.showBigImage = true
			$scope.bigImage = "/image/Citadels/Cards/" + $scope.revealedCards[index] + ".png"
			setBigImageStyle()
		}
		
		$scope.showTempRevealedCard = function(index){
			$scope.showBigImage = true
			$scope.bigImage = "/image/Citadels/Cards/" + $scope.tempRevealedTop[index] + ".png"
			setBigImageStyle()
		}
		
		$scope.showBuilt = function(playerIndex, builtIndex){
			$scope.showBigImage = true
			$scope.bigImage = "/image/Citadels/Cards/" + $scope.built[playerIndex][builtIndex] + ".png"
			setBigImageStyle()
		}
		
		$scope.showPlayerRole = function(playerIndex){
			$scope.showBigImage = true
			$scope.bigImage = "/image/Citadels/Roles/" + getRoleImageByNum($scope.roleRevealed[playerIndex]) + ".png"
			setBigImageStyle()
		}
		
		$scope.unshowRoleSelection = function(){
			$scope.showRoleSelection = false
		}
		
		$scope.openRoleSelection = function(){
			$scope.showRoleSelection = true
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
					$scope.roleImgs = response.data.roleImgs
					$scope.built = response.data.built
					$scope.cardsUnder = response.data.cardsUnder
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
					$scope.askLimit = response.data.askLimit
					$scope.canUseRoleSkill = response.data.canUseRoleSkill
					$scope.canUseCardSkill = response.data.canUseCardSkill
					$scope.isLord = response.data.isLord
					$scope.scores = response.data.scores
					$scope.netScores = response.data.netScores
					$scope.extraScores = response.data.extraScores
					$scope.yourRole = response.data.yourRole
					$scope.chooseOrDiscard = response.data.chooseOrDiscard
					$scope.roundCount = response.data.roundCount
					$scope.killedRole = response.data.killedRole
					$scope.stealedRole = response.data.stealedRole
					$scope.tempRevealedTop = response.data.tempRevealedTop;
					$scope.finishCount = response.data.finishCount
					$scope.regicide = response.data.regicide
					$scope.beautifyLevel = response.data.beautifyLevel
					if ($scope.regicide == "y"){
						$scope.regicideDisplay = "送葬者获得市长标记"
					} else {
						$scope.regicideDisplay = "回合结束时市长获得市长标记"
					}
					
					if ($scope.roleImgs.length == 8){
						$scope.roleTable = []
						var tr1 = [0,1,2,3]
						var tr2 = [4,5,6,7]
						$scope.roleTable.push(tr1)
						$scope.roleTable.push(tr2)
						
						$scope.chooseRoleTableStyle={
							"position" : "absolute",
							"width" : "930px",
							"margin-left" : "-465px",
							"left" : "50%"
						}
						$scope.chooseRoleNumCol = 4
						
					} else if ($scope.roleImgs.length == 9){
						$scope.roleTable = []
						var tr1 = [0,1,2,3,4]
						var tr2 = [5,6,7,8]
						$scope.roleTable.push(tr1)
						$scope.roleTable.push(tr2)
						$scope.chooseRoleTableStyle={
							"position" : "absolute",
							"width" : "1160px",
							"margin-left" : "-580px",
							"left" : "50%"
						}
						$scope.chooseRoleNumCol = 5
					}
					
					for (i=0;i<$scope.scores.length;i++){
						if ($scope.extraScores[i] != "0" && $scope.status != '3'){ 
							$scope.scores[i] = $scope.scores[i] + "(+" + $scope.extraScores[i] + ")"
						}
					}
					
					if ($scope.askType == '0'){
						if ($scope.phase == '-1'){
							$scope.askMsg = "现在不是你的回合"
						} else if ($scope.status == '1'){
							$scope.askMsg = "请选择角色"
						} else if ($scope.phase == '0'){
							$scope.askMsg = "请选择获得￥或者获得牌"
						} else if ($scope.phase == '1'){
							if ($scope.chooseOrDiscard == 'choose'){
								$scope.askMsg = "请选择一张展示的牌加入手牌"
							} else if ($scope.chooseOrDiscard == 'discard'){
								$scope.askMsg = "请选择1张你不需要的展示的牌置于牌堆底，剩余的牌将加入你的手牌。"
							}
						} else if ($scope.phase == '2'){
							$scope.askMsg = "你可以建造建筑或使用技能"
						}
					}
					
					if ($scope.status == '3'){
						$scope.statusDisplay = "游戏结束"
						alert("游戏结束");
						$scope.goto('citadelsendgame');
					} else {
						if ($scope.status == '2'){
							$scope.statusDisplay = "进行回合"
						} else if ($scope.status == '1'){
							$scope.statusDisplay = "选择角色"
						}
						setPlayerStyle()
						setButtons()
						setHand()
					}
					
					if ($scope.status == '1'){
						setChooseRoleStyle()
						$scope.showRoleSelection = true;
					}
					
					if ($scope.status == '2' && $scope.askType == '1'){
						setChooseRoleStyle()
						$scope.showRoleSelection = true;
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
			},1500);
		}
		
		$scope.offturnHandle();
		
}]);