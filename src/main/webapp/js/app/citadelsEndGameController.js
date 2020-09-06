var setUrl = function(d){
	hearder = "http://";
	server = "localhost:8080";
	return header + server + d;
}

var app = angular.module("citadelsEndGameApp", []);
app.controller("citadelsEndGameCtrl", ['$scope', '$window', '$http', '$document',
	function($scope, $window, $http, $document){
	
		$scope.goto = function(d){
			var x = "http://" + $window.location.host;
			$window.location.href = x + "/" + d;
		}
		
		$http.post('/username').then(function(response){
			$scope.username = response.data.value[0];
		});
		
		$scope.gamedata = "nothing"
			
		$scope.dismiss = function(x){
			$http.post("/citadelsgame/dismiss").then(function(response){
				$scope.goto('citadels');
			});
		}
		
		$scope.winner = []
		$scope.rankings = []
		$scope.playerStyle = []
		$scope.builtStyle = []
		$scope.specialHandsStyle = []
		$scope.showCup = false;
		$scope.showBigImage = false
		
		setWinner = function(){
			if ($scope.playerNames[0] == $scope.username){
				$scope.showCup = true;
			}
			$scope.winner.push($scope.playerNames[0])
			var i
			for (i=1;i<$scope.scores.length;i++){
				if ($scope.scores[i] == $scope.scores[0]
					&& $scope.netScores[i] == $scope.netScores[0]
					&& $scope.coins[i] == $scope.coins[0]){
					$scope.winner.push($scope.playerNames[i])
					if ($scope.playerNames[i] == $scope.username){
						$scope.showCup = true;
					}
				} else {
					break;
				}
			}
			
			var curRank = 1
			$scope.rankings.push(curRank)
			
			for (i=1;i<$scope.scores.length;i++){
				curRank = curRank+1
				if ($scope.scores[i] == $scope.scores[i-1]
					&& $scope.netScores[i] == $scope.netScores[i-1]
					&& $scope.coins[i] == $scope.coins[i-1]){
					var x = $scope.rankings[i-1]
					$scope.rankings.push(x)
				} else {
					$scope.rankings.push(curRank)
				}
			}
		}
		
		exchange = function(i,j){
			var temp
			temp=$scope.scores[i]
			$scope.scores[i]=$scope.scores[j]
			$scope.scores[j]=temp
			temp=$scope.netScores[i]
			$scope.netScores[i]=$scope.netScores[j]
			$scope.netScores[j]=temp
			temp=$scope.playerNames[i]
			$scope.playerNames[i]=$scope.playerNames[j]
			$scope.playerNames[j]=temp
			temp=$scope.coins[i]
			$scope.coins[i]=$scope.coins[j]
			$scope.coins[j]=temp
			
			var tbuilt
			tbuilt=$scope.built[i]
			$scope.built[i]=$scope.built[j]
			$scope.built[j]=tbuilt
		}
		
		sortPlayers = function(){
			var i,j
			var temp
			for (i=0;i<$scope.scores.length;i++){
				for (j=i+1;j<$scope.scores.length;j++){
					var si = parseInt($scope.scores[i])
					var sj = parseInt($scope.scores[j])
					if (si < sj){
						exchange(i,j)
					} else if (si == sj) {
						var ni = parseInt($scope.netScores[i])
						var nj = parseInt($scope.netScores[j])
						if (ni < nj){
							exchange(i,j)
						} else if (ni == nj){
							var ci = parseInt($scope.coins[i])
							var cj = parseInt($scope.coins[j])
							if (ci < cj){
								exchange(i,j)
							}
						}
					}
				}
			}
			$scope.playerStyle = []
			for (i=0;i<$scope.playerNames.length;i++){
				myStyle = {
					"background-color" : "#dddddd",
					"color" : "rgb(0,0,102)"
				}
				otherStyle = {
					"color" : "white"
				}
				if ($scope.playerNames[i] == $scope.username){
					$scope.playerStyle.push(myStyle);
				} else {
					$scope.playerStyle.push(otherStyle);
				}
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
		
		$scope.unshowBigImage = function(){
			$scope.showBigImage = false
		}
		
		$scope.showBuilt = function(playerIndex, builtIndex){
			$scope.showBigImage = true
			$scope.bigImage = "/image/Citadels/Cards/" + $scope.built[playerIndex][builtIndex] + ".png"
			setBigImageStyle()
		}
		
		$scope.showSpecialHand = function(playerIndex, specialHandIndex){
			$scope.showBigImage = true
			$scope.bigImage = "/image/Citadels/Cards/" + $scope.specialHands[playerIndex][specialHandIndex] + ".png"
			setBigImageStyle()
		}
		
		$scope.getBoard = function(){
			$http.get('/citadelsgame/getboard').then(function(response){
				if (response.data.id == "NE"){
					$scope.goto('citadels');
				} else {
					$scope.gamedata = JSON.stringify(response.data)
					$scope.isLord = response.data.isLord 
					$scope.scores = response.data.scores;
					$scope.netScores = response.data.netScores;
					$scope.playerNames = response.data.playerNames
					$scope.coins = response.data.coins;
					$scope.built = response.data.built;
					$scope.specialHands = response.data.specialHands;
					$scope.builtStyle = []
					$scope.specialHandsStyle = []
					for (i=0;i<$scope.built.length;i++){
						var singleBuiltStyle = []
						for (j=0;j<$scope.built[i].length;j++){
							var imgUrl = "url('/image/Citadels/Cards/" + $scope.built[i][j] + ".png')"
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
							singleStyle = {
								"background": imgUrl,
								"background-size": "cover",
								"float": "left",
								"position": "relative",
								"margin-left": marginLeft
							}
							singleBuiltStyle.push(singleStyle)
						}
						$scope.builtStyle.push(singleBuiltStyle)
					}
					
					for (i=0;i<$scope.specialHands.length;i++){
						var singleSpecialHandStyle = []
						for (j=0;j<$scope.specialHands[i].length;j++){
							var imgUrl = "url('/image/Citadels/Cards/" + $scope.specialHands[i][j] + ".png')"
							var marginLeft = "0px"
							if ($scope.specialHands[i].length == 3 && j != 0){
								marginLeft = "36px"
							} else if ($scope.specialHands[i].length == 4 && j != 0){
								marginLeft = "49px"
							}
							singleStyle = {
								"background": imgUrl,
								"background-size": "cover",
								"float": "left",
								"position": "relative",
								"margin-left": marginLeft
							}
							singleSpecialHandStyle.push(singleStyle)
						}
						$scope.specialHandsStyle.push(singleSpecialHandStyle)
					}
					
					sortPlayers()
					setWinner()
				}
			});
		}
		
		
		$scope.getBoard();
		
}]);