var setUrl = function(d){
	hearder = "http://";
	server = "localhost:8080";
	return header + server + d;
}

var app = angular.module("nothanksEndGameApp", []);
app.controller("nothanksEndGameCtrl", ['$scope', '$window', '$http', '$document',
	function($scope, $window, $http, $document){
		$scope.allHandsStyle = []
		$scope.rankings = []
		
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
		
		setAllHands = function(){
			$scope.allHandsStyle = []
			for (var i=0;i<$scope.allHands.length;i++){
				var singleHandStyle = []
				for (var j=0;j<$scope.allHands[i].length;j++){
					var imgUrl = "url('/image/Nothanks/Cards/" + $scope.allHands[i][j] + ".png')"
					var marginLeft = "0px"
					var singleCardStyle = {
						"background": imgUrl,
						"background-size": "cover",
						"float": "left",
						"margin-left" :marginLeft
					}
					singleHandStyle.push(singleCardStyle)
				}
				$scope.allHandsStyle.push(singleHandStyle)
			}
		}
		
		$scope.dismiss = function(x){
			$http.post("/nothanksgame/dismiss").then(function(response){
				$scope.goto('nothanks');
			});
		}
		
		maxGift = function(x){
			var ans = 0;
			for (i=0;i<$scope.allHands[x].length;i++){
				var t = parseInt($scope.allHands[x][i])
				if (t>ans){
					ans = t;
				}
			}
			return t;
		}
		
		hasQs = function(x){
			for (i=0;i<$scope.allHands[x].length;i++){
				if ($scope.allHands[x][i] == "0"){
					return true;
				}
			}
			return false;
		}
		
		shouldSwap = function(i,j){
			s1 = parseInt($scope.scores[i])
			s2 = parseInt($scope.scores[j])
			if (s1 < s2){
				return false;
			} else if (s1 > s2){
				return true;
			} else {
				m1 = parseInt($scope.revealedMoney[i])
				m2 = parseInt($scope.revealedMoney[j])
				if (m1>m2){
					return false
				} else if (m1<m2){
					return true;
				} else {
					if (hasQs(i)){
						return true;
					} else if (hasQs(j)){
						return false;
					} else {
						if (maxGift(i) < maxGift(j)){
							return true;
						} else {
							return false;
						}
					}
				}
			}
		}
		
		exchange = function(i,j){
			var temp;
			temp=$scope.scores[i]
			$scope.scores[i]=$scope.scores[j]
			$scope.scores[j]=temp
			temp=$scope.playerNames[i]
			$scope.playerNames[i]=$scope.playerNames[j]
			$scope.playerNames[j]=temp
			temp=$scope.revealedMoney[i]
			$scope.revealedMoney[i]=$scope.revealedMoney[j]
			$scope.revealedMoney[j]=temp
			
			var tallHands;
			tallHands=$scope.allHands[i]
			$scope.allHands[i]=$scope.allHands[j]
			$scope.allHands[j]=tallHands
			
			var tallHandsStyle;
			tallHandsStyle=$scope.allHandsStyle[i]
			$scope.allHandsStyle[i]=$scope.allHandsStyle[j]
			$scope.allHandsStyle[j]=tallHandsStyle
			
		}
		
		sortPlayers = function(){
			for (i=0;i<$scope.playerNames.length;i++){
				for (j=i+1;j<$scope.playerNames.length;j++){
					if (shouldSwap(i,j)){
						exchange(i,j)
					}
				}
				$scope.rankings.push(i+1);
			}
		}
		
		$scope.getBoard = function(){
			$http.get('/nothanksgame/getboard').then(function(response){
				$scope.gamedata = response.data
				$scope.playerNames = response.data.playerNames
				$scope.allHands = response.data.allHands;
				$scope.scores = response.data.scores;
				$scope.lord = response.data.lord
				$scope.revealedMoney = response.data.revealedMoney
				setAllHands();
				sortPlayers();
			});
		}
		
		$scope.getBoard();
		
}]);
