var setUrl = function(d){
	hearder = "http://";
	server = "localhost:8080";
	return header + server + d;
}

var app = angular.module("pokewhatEndGameApp", []);
app.controller("pokewhatEndGameCtrl", ['$scope', '$window', '$http', '$document', '$timeout',
	function($scope, $window, $http, $document, $timeout){
		$scope.rankings = [];
		$scope.playerNames = [];
		$scope.scores = [];
		$scope.scoreLastRound = [];
		$scope.championIndexes = [];
		$scope.avatarStyles = [];
		$scope.pmStyles = [];
		$scope.champions = "";
		$scope.isChampion = false;
		
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
		
		
		$scope.dismiss = function(){
			$http.post('/pokewhatgame/dismiss').then(function(response){
				$scope.goto('pokewhatgame');
			});
		}
		
		shouldSwap = function(i,j){
			var s1 = parseInt($scope.scores[i])
			var s2 = parseInt($scope.scores[j])
			if (s1<s2){
				return true;
			} else if (s1 == s2){
				var l1 = parseInt($scope.scoreLastRound[i])
				var l2 = parseInt($scope.scoreLastRound[j])
				if (l1<l2){
					return true;
				} else if (l1 == l2){
					var h1 = parseInt($scope.hp[i])
					var h2 = parseInt($scope.hp[j])
					if (h1<h2){
						return true;
					} else {
						return false;
					}
				} else {
					return false;
				}
			} else {
				return false;
			}
		}
		
		exchange = function(i,j){
			var temp;
			temp = $scope.playerNames[i]
			$scope.playerNames[i] = $scope.playerNames[j]
			$scope.playerNames[j] = temp
			temp = $scope.scores[i]
			$scope.scores[i] = $scope.scores[j]
			$scope.scores[j] = temp
			temp = $scope.scoreLastRound[i]
			$scope.scoreLastRound[i] = $scope.scoreLastRound[j]
			$scope.scoreLastRound[j] = temp
			temp = $scope.hp[i]
			$scope.hp[i] = $scope.hp[j]
			$scope.hp[j] = temp
			temp = $scope.avatars[i];
			$scope.avatars[i] = $scope.avatars[j];
			$scope.avatars[j] = temp;
			temp = $scope.pms[i];
			$scope.pms[i] = $scope.pms[j];
			$scope.pms[j] = temp;
			temp = $scope.pmNames[i];
			$scope.pmNames[i] = $scope.pmNames[j];
			$scope.pmNames[j] = temp;
			temp = $scope.pmSizes[i];
			$scope.pmSizes[i] = $scope.pmSizes[j];
			$scope.pmSizes[j] = temp;
			temp = $scope.playerAvatars[i]
			$scope.playerAvatars[i] = $scope.playerAvatars[j]
			$scope.playerAvatars[j] = temp
			temp = $scope.hp[i];
			$scope.hp[i] = $scope.hp[j];
			$scope.hp[j] = temp;
		}
		
		sortPlayers = function(){
			var i,j;
			for (i=0;i<$scope.playerNames.length;i++){
				for (j=i+1;j<$scope.playerNames.length;j++){
					if (shouldSwap(i,j)){
						echange(i,j)
					}
				}
			}
		}
		
		setRankings = function() {
			$scope.rankings = [];
			$scope.champions = "";
			var t = 1;
			for (i=0;i<$scope.scores.length;i++){
				if (i==0){
					$scope.rankings.push(t);
					$scope.champions = $scope.playerNames[i];
				} else {
					if ($scope.scores[i] == $scope.scores[i-1] 
					&& $scope.scoreLastRound[i] == $scope.scoreLastRound[i-1]
					&& $scope.hp[i] == $scope.hp[i-1]){
						$scope.rankings.push(t);
					} else {
						t = t+1;
						$scope.rankings.push(t);
					}
				}
			}
		}
		
		setChampions = function(){
			$scope.championIndexes = [];
			$scope.championNames = "";
			$scope.isChampion = false;
			for (i=0;i<$scope.rankings.length;i++){
				if ($scope.rankings[i] == 1){
					$scope.championIndexes.push(i);
					if (i==0){
						$scope.championNames = $scope.playerNames[i] + "和" + $scope.pmNames[i];
					} else {
						$scope.championNames = $scope.championNames + ", " + $scope.playerNames[i] + "和" + $scope.pmNames[i];
					}
				} else {
					break;
				}
			}
			for (i=0;i<$scope.championIndexes.length;i++){
				
			}
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
						"background-size": "cover",
						"height": $scope.pmSizes[i] + "px",
						"width": $scope.pmSizes[i] + "px"
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
		
		
		$scope.getBoard = function(){
			$http.get('/pokewhatgame/getboard').then(function(response){
				$scope.id = response.data.id;
				if ($scope.id == "NE"){
					$scope.goto('/pokewhat');
					return;
				}
				$scope.lord = response.data.lord
				$scope.playerNames = response.data.playerNames;
				$scope.scores = response.data.scores;
				$scope.scoreLastRound = response.data.scoreLastRound;
				$scope.hp = response.data.hp;
				$scope.avatars = response.data.avatars;
				$scope.pms = response.data.pm;
				$scope.pmNames = response.data.pmNames;
				$scope.pmSizes = response.data.pmSizes;
				$scope.playerAvatars = response.data.playerAvatars;
				sortPlayers();
				setRankings();
				setChampions();
				setAvatarPmStyles();
			});
		}
		
		$scope.getBoard();
		
}]);
