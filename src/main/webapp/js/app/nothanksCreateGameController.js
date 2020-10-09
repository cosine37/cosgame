var setUrl = function(d){
	hearder = "http://";
	server = "localhost:8080";
	return header + server + d;
}

var app = angular.module("nothanksCreateGameApp", []);
app.controller("nothanksCreateGameCtrl", ['$scope', '$window', '$http', '$document', '$timeout',
	function($scope, $window, $http, $document, $timeout){
	
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
		
		$scope.addbot = function(){
			$http.post("/nothanksgame/addbot").then(function(response){
				$scope.getBoard();
			});
		}
		
		$scope.kick = function(x){
			var data = {"index" : x}
			$http({url: "/nothanksgame/kick", method: "POST", params: data}).then(function(response){
				$scope.getBoard();
			});
		}
		
		$scope.dismiss = function(x){
			$http.post("/nothanksgame/dismiss").then(function(response){
				$scope.goto('nothanks');
			});
		}
		
		$scope.startGame = function(){
			$http.post("/nothanksgame/startgame").then(function(response){
				$scope.goto('nothanksgame');
			});
		}
		
		$scope.setStartPlayer = function($index){
			var x = parseInt($index)
			var data = {"index" : x}
			$http({url: "/nothanksgame/setstartplayer", method: "POST", params: data}).then(function(response){
				$scope.getBoard();
			});
		}
		
		$scope.getBoard = function(){
			$http.get('/nothanksgame/getboard').then(function(response){
				if (response.data.id == "NE"){
					$scope.goto('nothanks');
				} else {
					$scope.gamedata = response.data
					$scope.playerNames = response.data.playerNames
					$scope.curPlayer = response.data.curPlayer;
					$scope.lord = response.data.lord;
					$scope.status = response.data.status;
					
					if ($scope.status != "0"){
						$scope.goto('nothanksgame');
					}
				}
				
			});
		}
		
		$scope.offturnHandle = function(){
			/*
			if ($scope.showConfig == 'n'){
				$scope.getBoard();
			}
			$timeout(function(){
			    $scope.offturnHandle();
			},5000);
			*/
			$scope.getBoard();
			$timeout(function(){
			    $scope.offturnHandle();
			},5000);
		}
		
		$scope.offturnHandle();
		
}]);
