var setUrl = function(d){
	hearder = "https://";
	server = "localhost:8080";
	return header + server + d;
}

var app = angular.module("gravepsychoEndGameApp", []);
app.controller("gravepsychoEndGameCtrl", ['$scope', '$window', '$http', '$document', '$timeout',
	function($scope, $window, $http, $document, $timeout){
		$scope.champions = "";
		$scope.isWinner = false;
		$scope.championDict = {}
		$scope.money = []
		$scope.championMoney = 0;
	
		$scope.goto = function(d){
			var x = "https://" + $window.location.host;
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
			$http.post('/gravepsycho/dismiss').then(function(response){
				$scope.goto('gravepsycho')
			});
		}
		
		$scope.isChampion = function(){
			var x = $scope.money[0];
			for (i=0;i<$scope.money.length;i++){
				if ($scope.championMoney == $scope.money[i] && $scope.username == $scope.playerNames[i]){
					return true;
				}
			}
			return false;
		}
		
		sortPlayers = function(){
			var i,j;
			for (i=0;i<$scope.money.length;i++){
				for (j=i+1;j<$scope.money.length;j++){
					var m1 = parseInt($scope.money[i])
					var m2 = parseInt($scope.money[j])
					if (m1 < m2){
						temp = $scope.money[i];
						$scope.money[i] = $scope.money[j];
						$scope.money[j] = temp;
						temp = $scope.playerNames[i];
						$scope.playerNames[i] = $scope.playerNames[j];
						$scope.playerNames[j] = temp;
						temp = $scope.avatar[i];
						$scope.avatar[i] = $scope.avatar[j];
						$scope.avatar[j] = temp;
					}
				}
			}
		}
		
		decideChampions = function(){
			$scope.championMoney = $scope.money[0]
			$scope.champions = $scope.playerNames[0]
			for (i=1;i<$scope.money.length;i++){
				if ($scope.money[i] == $scope.championMoney){
					$scope.champions = $scope.champions + ", " + $scope.playerNames[i];
				}
			}
		}
		
		$scope.getBoard = function(){
			$http.get('/gravepsycho/getboard').then(function(response){
				$scope.gamedata = response.data
				$scope.lord = response.data.lord
				$scope.playerNames = response.data.playerNames;
				$scope.money = response.data.money;
				$scope.avatar = response.data.avatar;
				sortPlayers()
				decideChampions()
			});
		}
		
		$scope.getBoard();
}]);