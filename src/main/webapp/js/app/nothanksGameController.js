var setUrl = function(d){
	hearder = "http://";
	server = "localhost:8080";
	return header + server + d;
}

var app = angular.module("nothanksGameApp", []);
app.controller("nothanksGameCtrl", ['$scope', '$window', '$http', '$document',
	function($scope, $window, $http, $document){
		$scope.disableUserButton = []
	
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
		
		setUsers = function(){
			$scope.disableUserButton = []
			for (i=0;i<$scope.playerNames.length;i++){
				if ($scope.playerNames[i] == $scope.username){
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
				
				setUsers();
			});
		}
		
		$scope.getBoard();
		
}]);
