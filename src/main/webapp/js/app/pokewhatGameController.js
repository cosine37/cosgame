var setUrl = function(d){
	hearder = "http://";
	server = "localhost:8080";
	return header + server + d;
}

var app = angular.module("pokewhatGameApp", []);
app.controller("pokewhatGameCtrl", ['$scope', '$window', '$http', '$document',
	function($scope, $window, $http, $document){
		$scope.moves = [];
		$scope.allCards = [];
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
		
		$scope.useMove = function(x){
			var data = {
				"x" : x
			}
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
		
		
		$scope.getBoard = function(){
			$http.get('/pokewhatgame/getboard').then(function(response){
				$scope.gamedata = response.data
				$scope.phase = response.data.phase;
				$scope.lastMove = parseInt(response.data.lastMove);
				$scope.playerNames = response.data.playerNames;
				$scope.playedCards = response.data.playedCards;
				$scope.scores = response.data.scores;
				$scope.hp = response.data.hp;
				$scope.allCards = response.data.allCards;
				$scope.round = response.data.round;
				$scope.turn = response.data.turn;
			});
		}
		
		$scope.getBoard();
		
}]);
