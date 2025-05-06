var setUrl = function(d){
	hearder = "https://";
	server = "localhost:8080";
	return header + server + d;
}

var app = angular.module("sushiCreateGameApp", []);
app.controller("sushiCreateGameCtrl", ['$scope', '$window', '$http', '$document', '$timeout',
	function($scope, $window, $http, $document, $timeout){
		$scope.initialMoneyOptions = []
		
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
		
		$scope.kick = function(x){
			var data = {"index" : x}
			$http({url: "/sushi/kick", method: "POST", params: data}).then(function(response){
				$scope.getBoard();
			});
		}
		
		$scope.dismiss = function(x){
			$http.post("/sushi/dismiss").then(function(response){
				$scope.goto('sushi');
			});
		}
		
		$scope.startGame = function(){
			$scope.goto('sushigame');
			/*
			$http.post("/sushi/startgame").then(function(response){
				$scope.goto('sushigame');
			});
			*/
		}
		
		$scope.setStartPlayer = function($index){
			var x = parseInt($index)
			var data = {"index" : x}
			$http({url: "/sushi/setstartplayer", method: "POST", params: data}).then(function(response){
				$scope.getBoard();
			});
		}
		/*
		$scope.getBoard = function(){
			$http.get('/sushi/getboard').then(function(response){
				if (response.data.id == "NE"){
					$scope.goto('nothanks');
				} else {
					$scope.gamedata = response.data
					$scope.playerNames = response.data.playerNames
					$scope.curPlayer = response.data.curPlayer;
					$scope.lord = response.data.lord;
					$scope.status = response.data.status;
					$scope.initialRevealedMoney = response.data.intialRevealedMoney;
					
					var n = Math.trunc(12/$scope.playerNames.length)
					$scope.initialMoneyOptions = []
					for (i=1;i<=n;i++){
						$scope.initialMoneyOptions.push(i)
					}
					if ($scope.status != "0"){
						$scope.goto('nothanksgame');
					}
				}
				
			});
		}
		
		$scope.offturnHandle = function(){
			$scope.getBoard();
			$timeout(function(){
			    $scope.offturnHandle();
			},5000);
		}
		
		$scope.offturnHandle();
		*/
}]);
