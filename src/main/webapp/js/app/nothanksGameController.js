var setUrl = function(d){
	hearder = "http://";
	server = "localhost:8080";
	return header + server + d;
}

var app = angular.module("nothanksGameApp", []);
app.controller("nothanksGameCtrl", ['$scope', '$window', '$http', '$document', '$timeout',
	function($scope, $window, $http, $document, $timeout){
		$scope.disableUserButton = []
		$scope.handStyle = []
		$scope.playerStyle = []
		$scope.packageStyle = {}
		$scope.moneyStyle = {}
		$scope.phase = "-1"
		$scope.status = ""
	
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
		
		$scope.receive = function(){
			$http.post('/nothanksgame/receive').then(function(response){
				$scope.getBoard();
			});
		}
		
		$scope.send = function(x){
			var data = {
					"x" : x
			}
			$http({url: "/nothanksgame/send", method: "POST", params: data}).then(function(response){
				$scope.getBoard()
			});
		}
		
		$scope.botnextmove = function(){
			$http.post('/nothanksgame/botnextmove').then(function(response){
				$scope.getBoard();
			});
		}
		
		setUsers = function(){
			$scope.disableUserButton = []
			$scope.playerStyle = []
			for (i=0;i<$scope.playerNames.length;i++){
				var singlePlayerStyle = {}
				if ($scope.playerNames[i] == $scope.username){
					singlePlayerStyle = {
						"background-color" : "#dddddd"
					}
					$scope.disableUserButton.push(true);
				} else {
					if ($scope.phase == 0){
						if ($scope.trueMoney == "0"){
							$scope.disableUserButton.push(true);
						} else {
							$scope.disableUserButton.push(false);
						}
					} else {
						$scope.disableUserButton.push(true);
					}
				}
				$scope.playerStyle.push(singlePlayerStyle)
			}
		}
		
		setHand = function(){
			$scope.handStyle = []
			for (var i=0;i<$scope.hand.length;i++){
				var imgUrl = "url('/image/Nothanks/Cards/" + $scope.hand[i] + ".png')"
				var marginLeft = "0px"
				if ($scope.hand.length >= 10 && i != 0){
					marginLeft = "-8px"
				}
				if ($scope.hand.length >= 11 && i != 0){
					marginLeft = "-16px"
				}
				if ($scope.hand.length >= 12 && i != 0){
					marginLeft = "-24px"
				}	
				if ($scope.hand.length >= 13 && i != 0){
					marginLeft = "-31px"
				}	
				var singleStyle = {
					"background": imgUrl,
					"background-size": "cover",
					"float": "left",
					"margin-left" :marginLeft
				}
				$scope.handStyle.push(singleStyle)
			}
			var imgUrl = "url('/image/Nothanks/Cards/" + $scope.packCardImg + ".png')"
			if ($scope.packCardImg == ""){
				$scope.packageStyle = {

				}
			} else {
				$scope.packageStyle = {
					"background": imgUrl,
					"background-size": "cover",
					"float": "left",
					"margin-left" :marginLeft
				}
			}
			
			var imgUrl = "url('/image/Nothanks/Cards/-3.png')"
			$scope.moneyStyle = {
				"background": imgUrl,
				"background-size": "cover",
				"float": "left",
				"margin-left" :marginLeft
			}
		}
		
		$scope.getBoard = function(){
			$http.get('/nothanksgame/getboard').then(function(response){
				$scope.gamedata = response.data
				$scope.playerNames = response.data.playerNames
				$scope.revealedMoney = response.data.revealedMoney
				$scope.handSizes = response.data.handSizes
				$scope.status = response.data.status
				$scope.phase = response.data.phase
				$scope.trueMoney = response.data.trueMoney;
				$scope.packCardImg = response.data.packCardImg;
				$scope.packMoney = response.data.packMoney;
				$scope.hand = response.data.hand;
				$scope.packCardImg = response.data.packCardImg;
				$scope.curPlayer = response.data.curPlayer;
				
				if ($scope.status == '2'){
					$scope.goto("nothanksendgame");
				}
				setUsers();
				setHand();
			});
		}
		
		$scope.offturnHandle = function(){
			if ($scope.phase == "-1" && $scope.status !='2'){
				$scope.getBoard();
			}
			$timeout(function(){
			    $scope.offturnHandle();
			},1500);
		}
		
		$scope.offturnHandle();
		
}]);
