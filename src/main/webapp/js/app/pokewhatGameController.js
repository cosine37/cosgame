var setUrl = function(d){
	hearder = "http://";
	server = "localhost:8080";
	return header + server + d;
}

var app = angular.module("pokewhatGameApp", []);
app.controller("pokewhatGameCtrl", ['$scope', '$window', '$http', '$document', '$timeout',
	function($scope, $window, $http, $document, $timeout){
		$scope.moves = [];
		$scope.allCards = [];
		$scope.pmToChooseStyles = [];
		$scope.avatarStyles = [];
		$scope.pmStyles = [];
		$scope.phase = "";
		$scope.lastMove = 0;
		
		for (var i=1;i<=8;i++){
			$scope.moves.push(i);
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
		
		$scope.choosePm = function(x){
			var data = {"x" : x}
			$http({url: "/pokewhatgame/choosepm", method: "POST", params: data}).then(function(response){
				$scope.getBoard()
			});
		}
		
		$scope.botChoosePm = function(){
			$http({url: "/pokewhatgame/botchoosepm", method: "POST"}).then(function(response){
				$scope.getBoard()
			});
		}
		
		$scope.useMove = function(x){
			var data = {"x" : x}
			$http({url: "/pokewhatgame/usemove", method: "POST", params: data}).then(function(response){
				$scope.getBoard()
			});
		}
		
		$scope.endTurn = function(){
			$http({url: "/pokewhatgame/endturn", method: "POST"}).then(function(response){
				$scope.getBoard()
			});
		}
		
		$scope.botNextMove = function(){
			$http({url: "/pokewhatgame/botnextmove", method: "POST"}).then(function(response){
				$scope.getBoard()
			});
		}
		
		setAvatarPmStyles = function(){
			$scope.avatarStyles = [];
			$scope.pmStyles = [];
			for (i=0;i<$scope.pms.length;i++){
				if ($scope.pms[i] == null){
					singleStyle = {}
				} else {
					var imgUrl = "url('/image/Pokewhat/PM/" + $scope.pms[i] + ".png')"
					singleStyle = {
						"background": imgUrl,
						"background-size": "cover"
					}
				}
				
				$scope.pmStyles.push(singleStyle);
			}
			for (i=0;i<$scope.avatars.length;i++){
				var imgUrl = "url('/image/Pokewhat/Avatar/" + $scope.avatars[i] + ".png')"
				singleStyle = {
					"background": imgUrl,
					"background-size": "cover"
				}
				$scope.avatarStyles.push(singleStyle);
			}
		}
		
		setPmToChooseStyles = function(){
			$scope.pmToChooseStyles = [];
			for (i=0;i<$scope.pmToChoose.length;i++){
				var imgUrl = "url('/image/Pokewhat/PM/" + $scope.pmToChoose[i] + ".png')"
				singleStyle = {
					"background": imgUrl,
					"background-size": "cover"
				}
				$scope.pmToChooseStyles.push(singleStyle);
			}
		}
		
		$scope.getBoard = function(){
			$http.get('/pokewhatgame/getboard').then(function(response){
				$scope.id = response.data.id;
				if ($scope.id == "NE"){
					$scope.goto('/pokewhat');
					return;
				}
				$scope.lord = response.data.lord;
				$scope.phase = response.data.phase;
				$scope.lastMove = parseInt(response.data.lastMove);
				$scope.playerNames = response.data.playerNames;
				$scope.playedCards = response.data.playedCards;
				$scope.scores = response.data.scores;
				$scope.hp = response.data.hp;
				$scope.allCards = response.data.allCards;
				$scope.round = response.data.round;
				$scope.turn = response.data.turn;
				$scope.deckSize = response.data.deckSize;
				$scope.ancientSize = response.data.ancientSize;
				$scope.ancient = response.data.ancient;
				$scope.status = response.data.status;
				$scope.pmToChoose = response.data.pmToChoose;
				$scope.pmToChooseNames = response.data.pmToChooseNames;
				$scope.avatars = response.data.avatars;
				$scope.pms = response.data.pm;
				$scope.playerAvatars = response.data.playerAvatars;
				$scope.logs = response.data.logs;
				if ($scope.status == "2"){
					alert("Game Ends");
					$scope.goto("pokewhatendgame");
				}
				if ($scope.status == "3"){
					setPmToChooseStyles();
				}
				setAvatarPmStyles();
			});
		}
		
		$scope.offturnHandle = function(){
			if ($scope.phase != "1" && $scope.status !='2'){
				$scope.getBoard();
			}
			$timeout(function(){
			    $scope.offturnHandle();
			},1500);
		}
		
		$scope.offturnHandle();
		
}]);
