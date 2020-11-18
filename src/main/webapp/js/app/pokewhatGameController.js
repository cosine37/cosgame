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
		$scope.allCardsStyles = [];
		$scope.pmToChooseStyles = [];
		$scope.avatarStyles = [];
		$scope.hpBarStyle = []
		$scope.pmStyles = [];
		$scope.otherIndexes = [];
		$scope.moveStyles = [];
		$scope.ancientStyles = [];
		$scope.phase = "";
		$scope.lastMove = 0;

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
			if ($scope.allCards[$scope.curPlayer].length == 0){
				$scope.useMove(0);
			} else {
				$http({url: "/pokewhatgame/endturn", method: "POST"}).then(function(response){
					$scope.getBoard()
				});
			}
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
				var imgUrl = "url('/image/Pokewhat/Avatar/" + $scope.playerAvatars[i] + ".png')"
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
		
		sortAllCards = function(){
			for (k=0;k<$scope.allCards.length;k++){
				for (i=0;i<$scope.allCards[k].length;i++){
					for (j=i+1;j<$scope.allCards[k].length;j++){
						if ($scope.allCards[k][i]>$scope.allCards[k][j]){
							temp = $scope.allCards[k][i]
							$scope.allCards[k][i] = $scope.allCards[k][j]
							$scope.allCards[k][j] = temp
						}
					}
				}
			}
		}
		
		setAllCardStyles = function(){
			$scope.allCardsStyles = [];
			for (i=0;i<$scope.allCards.length;i++){
				var cardsStyles = [];
				for (j=0;j<$scope.allCards[i].length;j++){
					var imgUrl = "url('/image/Pokewhat/Cards/" + $scope.allCards[i][j] + ".png')"
					singleStyle = {
						"background": imgUrl,
						"background-size": "cover"
					}
					cardsStyles.push(singleStyle)
				}
				$scope.allCardsStyles.push(cardsStyles)
			}
		}
		
		setHpBarStyle = function(){
			$scope.hpBarStyle = [];
			for (i=0;i<$scope.hp.length;i++){
				$scope.hp[i] = parseInt($scope.hp[i]);
				var barStyle = [];
				for (j=0;j<$scope.hp[i];j++){
					var singleStyle
					if ($scope.hp[i] == 1){
						singleStyle = {
							"background-color": "red",
							"border-right": "1px solid black"
						}
					} else if ($scope.hp[i] < 4){
						singleStyle = {
							"background-color": "orange",
							"border-right": "1px solid black"
						}
					} else {
						singleStyle = {
							"background-color": "green",
							"border-right": "1px solid black"
						}
					}
					barStyle.push(singleStyle)
				}
				
				
				$scope.hpBarStyle.push(barStyle)
			}
		}
		
		setAncientStyles = function(){
			$scope.ancientStyles = [];
			for (i=0;i<$scope.ancient.length;i++){
				var imgUrl = "url('/image/Pokewhat/Cards/" + $scope.ancient[i] + ".png')"
				singleStyle = {
					"background": imgUrl,
					"background-size": "cover"
				}
				$scope.ancientStyles.push(singleStyle)
			}
		}
		
		setMoveStyles = function(){
			$scope.moveStyles = [];
			for (var i=1;i<=8;i++){
				$scope.moves.push(i);
				var imgUrl = "url('/image/Pokewhat/Cards/" + i.toString() + ".png')"
				if (i<$scope.lastMove || $scope.phase != 1){
					singleStyle = {
						"background": imgUrl,
						"background-size": "cover",
						"filter": "grayscale(100%) contrast(50%)"
					}
				} else {
					singleStyle = {
						"background": imgUrl,
						"background-size": "cover"
					}
				}
				$scope.moveStyles.push(singleStyle)
			}
			var imgUrl = "url('/image/Pokewhat/Cards/0.png')"
			$scope.moveStyleZero = {
				"background": imgUrl,
				"background-size": "cover"
			}
		}
		
		setOtherIndexes = function(){
			$scope.otherIndexes = [];
			var i = $scope.myIndex;
			do {
				if (i != $scope.myIndex){
					$scope.otherIndexes.push(i);
				}
				i = i+1;
				if (i == $scope.playerNames.length){
					i=0;
				}
			} while (i != $scope.myIndex)
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
				$scope.pmNames = response.data.pmNames;
				$scope.playerAvatars = response.data.playerAvatars;
				$scope.logs = response.data.logs;
				$scope.curPlayer = response.data.curPlayer;
				$scope.myIndex = parseInt(response.data.myIndex);
				$scope.playerAncients = response.data.playerAncients
				if ($scope.status == "2"){
					alert("Game Ends");
					$scope.goto("pokewhatendgame");
				}
				if ($scope.status == "3"){
					setPmToChooseStyles();
				}
				setAvatarPmStyles();
				sortAllCards();
				setAllCardStyles();
				setHpBarStyle();
				setAncientStyles();
				setMoveStyles();
				setOtherIndexes();
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
