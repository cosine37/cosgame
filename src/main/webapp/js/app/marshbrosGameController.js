var setUrl = function(d){
	hearder = "http://";
	server = "localhost:8080";
	return header + server + d;
}

var app = angular.module("marshbrosGameApp", ["ngWebSocket"]);
app.controller("marshbrosGameCtrl", ['$scope', '$window', '$http', '$document', '$websocket',
	function($scope, $window, $http, $document, $websocket){
	
		var ws = $websocket("ws://" + $window.location.host + "/marshbros/boardrefresh");
		ws.onError(function(event) {
		});
	
		ws.onClose(function(event) {
		});
	
		ws.onOpen(function() {
		});
	
		$scope.settings = [0];
		$scope.handStyles = [];
		$scope.areaStyles = [];
		$scope.myIndex = 0;
		$scope.roleIndex = -1;
		$scope.shownInstruction = "";
		$scope.attackRoleIndex = -1;
		$scope.attackPlayerIndex = -1;
		$scope.attackMode = false;
		$scope.attackClasses = [];
		$scope.numSacrifice = 0;
		$scope.showSacrifice = false;
		$scope.chooseSacrifice = [];
		$scope.sacrificeIndex = -1;
		
		$scope.goto = function(d){
			var x = "http://" + $window.location.host;
			$window.location.href = x + "/" + d;
		}
		
		$http.post('/username').then(function(response){
			$scope.username = response.data.value[0];
			
			$scope.lord = $scope.username
		});
		
		$scope.logout = function(){
			$http({url: "/logout", method: "POST"}).then(function(response){
				$scope.goto('login');
			});
		}
		
		setAttackClasses = function(){
			$scope.attackClasses = []
			for (i=0;i<$scope.attackTargets.length;i++){
				var singleAttackTargets = $scope.attackTargets[i]
				var singleAttackClasses = []
				for (j=0;j<singleAttackTargets.length;j++){
					if (singleAttackTargets[j] == "1"){
						singleAttackClasses.push("attackable");
					} else {
						singleAttackClasses.push("");
					}
				}
				$scope.attackClasses.push(singleAttackClasses);
			}
		}
		
		$scope.draw = function(x){
			$http({url: "/marshbros/draw", method: "POST"}).then(function(response){
				$scope.getBoard()
			});
		}
		
		$scope.endAction = function(){
			$http({url: "/marshbros/endaction", method: "POST"}).then(function(response){
				$scope.getBoard()
			});
		}
		
		$scope.appoint = function(x){
			if ($scope.phase == "1" || $scope.phase == "3"){
				var data = {"index" : x}
				$http({url: "/marshbros/appoint", method: "POST", params: data}).then(function(response){
					$scope.getBoard()
				});
			}
		}
		
		$scope.raid = function(){
			var data = {"index" : $scope.roleIndex}
			$http({url: "/marshbros/raid", method: "POST", params: data}).then(function(response){
				$scope.getBoard()
				$scope.hideAreaCard();
			});
		}
		
		$scope.openAttack = function(){
			$scope.attackMode = true
			setAttackClasses()
		}
		
		$scope.cancelAttack = function(){
			$scope.attackMode = false
		}
		
		$scope.attack = function(){
			var data = {"index" : $scope.roleIndex, "attackPlayer" : $scope.attackPlayerIndex, "attackRole" : $scope.attackRoleIndex}
			$http({url: "/marshbros/attack", method: "POST", params: data}).then(function(response){
				$scope.getBoard()
				$scope.attackMode = false
				$scope.hideAreaCard();
			});
		}
		
		$scope.chooseForSacrifice = function(x){
			if ($scope.sacrificeIndex == x){
				$scope.sacrificeIndex = -1;
			} else {
				$scope.sacrificeIndex = x;
			}
		}
		
		$scope.confirmSacrifice = function(){
			var data = {"roleIndex" : $scope.sacrificeIndex}
			$http({url: "/marshbros/sacrifice", method: "POST", params: data}).then(function(response){
				$scope.getBoard()
			});
		}
		
		$scope.hideAreaCard = function(){
			$scope.roleIndex = -1
			$scope.curAreaCardIndex = -1;
			$scope.choosedRoleStyle = {}
		}
		
		$scope.showAreaCard = function(x){
			$scope.curAreaCardIndex = x;
			imgUrl = "url('/image/Marshbros/Empty/" + $scope.roles[$scope.myIndex][x].img + ".png')"
			var singleStyle = {
				"background": imgUrl,
				"background-size": "cover"
			}
			$scope.choosedRoleStyle = singleStyle
			$scope.choosedAtk = $scope.roles[$scope.myIndex][x].atk
			$scope.choosedHp = $scope.roles[$scope.myIndex][x].hp
		}
		
		$scope.showInstructions = function(s){
			$scope.shownInstruction = s;
		}
		
		$scope.openChoices = function(x,y){
			if ($scope.roleIndex == y){
				$scope.roleIndex = -1;
				$scope.choosedRoleStyle = {}
				$scope.showInstructions("")
			} else if ($scope.phase == 2 && 
					($scope.roles[$scope.myIndex][y].choice == "-1" || $scope.roles[$scope.myIndex][y].choice == "-2")){ // action phase
				$scope.roleIndex = y;
				$scope.chosenRole = $scope.roles[$scope.myIndex][y]
				$scope.showAreaCard($scope.roleIndex);
			} else if ($scope.phase == 4){
				$scope.chooseForSacrifice(y)
			}
		}
		
		$scope.clickAreaCard = function(x,y){
			if ($scope.attackMode == true){
				$scope.attackPlayerIndex = x
				$scope.attackRoleIndex = y
				$scope.attack()
			} else {
				if (x == $scope.myIndex){
					$scope.openChoices(x,y)
				}
			}
		}
		
		$scope.cancelChoosed = function(){
			$scope.openChoices($scope.myIndex, $scope.roleIndex)
		}
		
		setAreaStyle = function(){
			$scope.areaStyles = [];
			for (j=0;j<$scope.roles.length;j++){
				var singleAreaStyles = []
				for (i=0;i<$scope.roles[j].length;i++){
					imgUrl = "url('/image/Marshbros/Empty/" + $scope.roles[j][i].img + ".png')"
					var singleStyle = {
						"background": imgUrl,
						"background-size": "cover"
					}
					if ($scope.roles[$scope.myIndex][i].choice != "-1" && $scope.roles[$scope.myIndex][i].choice != "-2"){
						singleStyle = {
							"background": imgUrl,
							"background-color" : "gray",
							"background-blend-mode" : "screen",
							"background-size": "cover"
						}
					}
					//alert(JSON.stringify(singleStyle))
					singleAreaStyles.push(singleStyle)
				}
				$scope.areaStyles.push(singleAreaStyles)
				
				//alert(JSON.stringify($scope.areaStyles))
			}
		}
		
		setHandStyle = function(){
			$scope.handStyles = [];
			for (i=0;i<$scope.hand.length;i++){
				imgUrl = "url('/image/Marshbros/Card/" + $scope.hand[i] + ".png')"
				var singleStyle = {
					"background": imgUrl,
					"background-size": "cover"
				}
				$scope.handStyles.push(singleStyle)
			}
		}
		
		setUI = function(){
			setAreaStyle()
			setHandStyle()
		}
		
		$scope.getBoard = function(){
			$http.get('/marshbros/getboard').then(function(response){
				$scope.gamedata = response.data
				$scope.id = response.data.id
				$scope.status = response.data.status
				$scope.playerNames = response.data.players
				$scope.lord = response.data.lord
				$scope.hand = response.data.hand
				$scope.roles = response.data.roles
				$scope.myIndex = parseInt(response.data.myIndex);
				$scope.attackTargets = response.data.attackTargets;
				$scope.phase = response.data.phase;
				$scope.numSacrifice = 0;
				$scope.sacrificeIndex = -1;
				$scope.showSacrifice = false;
				
				if ($scope.phase == "4") { //sacrifice
					$scope.numSacrifice = $scope.roles[$scope.myIndex].length - 3;
					$scope.chooseSacrifice = [];
					for (i=0;i<$scope.roles[$scope.myIndex].length;i++){
						$scope.chooseSacrifice.push(0);
					}
				}
				setUI();
				//ws.send(id);
			});
		}
		
		$scope.getBoard()
		
		
		ws.onMessage(function(){
			$scope.getBoard();
		});
		
		/*
		$scope.start = function(){
			if ($scope.soleWolfOption){
				$scope.settings[0] = 1;
			}
			var data = {"settings" : $scope.settings}
			$http({url: "/onenightgame/startgame", method: "POST", params: data}).then(function(response){
				$scope.goto('onenightgame');
			});
			
		}
		
		$scope.addBot = function(){
			$http.post('/onenightgame/addbot').then(function(response){
				$scope.getBoard();
			});
		}
		
		*/
}]);
