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
		
		setWinner = function(){
			$scope.winner.push($scope.playerNames[0])
			var i
			for (i=1;i<$scope.scores.length;i++){
				if ($scope.scores[i] == $scope.scores[0]
					&& $scope.netScores[i] == $scope.netScores[0]
					&& $scope.coins[i] == $scope.coins[0]){
					$scope.winner.push($scope.playerNames[i])
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
					sortPlayers()
					setWinner()
				}
			});
		}
		
		$scope.getBoard();
		
}]);