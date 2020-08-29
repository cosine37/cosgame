var setUrl = function(d){
	hearder = "http://";
	server = "localhost:8080";
	return header + server + d;
}

var app = angular.module("citadelsCreateGameApp", []);
app.controller("citadelsCreateGameCtrl", ['$scope', '$window', '$http', '$document', '$timeout',
	function($scope, $window, $http, $document, $timeout){
		//$scope.crown = 0
	
		$scope.showConfig = 'n'
	
		$scope.goto = function(d){
			var x = "http://" + $window.location.host;
			$window.location.href = x + "/" + d;
		}
		
		$http.post('/username').then(function(response){
			$scope.username = response.data.value[0];
		});
		
		$scope.getBoard = function(){
			$http.get('/citadelsgame/getboard').then(function(response){
				if (response.data.id == "NE"){
					$scope.goto('citadels');
				} else {
					$scope.gamedata = JSON.stringify(response.data)
					$scope.playerNames = response.data.playerNames
					$scope.crown = parseInt(response.data.crown)
					$scope.tcrown = $scope.crown
					$scope.isLord = response.data.isLord
					$scope.status = response.data.status
					if ($scope.status != "0"){
						$scope.goto('citadelsgame');
					}
				}
			});
		}
		
		$scope.giveCrown = function(){
			var data = {"crownIndex" : $scope.tcrown}
			$http({url: "/citadelsgame/givecrown", method: "POST", params: data}).then(function(response){
				$scope.getBoard();
			});
		}
		
		$scope.setEndNum = function(x){
			var data = {"endnum" : x}
			$http({url: "/citadelsgame/setendnum", method: "POST", params: data}).then(function(response){
				$scope.getBoard();
			});
		}
		
		$scope.startGame = function(){
			/*
			if ($scope.playerNames.length < 4){
				alert("No enough players.  The players size must between 4~7")
			} else if ($scope.playerNames.length > 7){
				alert("Too much players.  The players size must between 4~7")
			} else {
				$http.post('citadelsgame/start').then(function(response){
					$scope.goto('citadelsgame');
				})
			}
			*/
			$http.post('citadelsgame/start').then(function(response){
				$scope.goto('citadelsgame');
			})
		}
		
		$scope.addBot = function(){
			$http.post('citadelsgame/addbot').then(function(response){
				$scope.getBoard();
			})
		}
		
		$scope.setTCrown = function(x){
			$scope.tcrown = x;
		}
		
		$scope.randomCrown = function(){
			var n = $scope.playerNames.length;
			var x = Math.floor(Math.random() * n);
			$scope.setTCrown(x);
		}
		
		$scope.kick = function(x){
			var data = {"index" : x}
			$http({url: "/citadelsgame/kick", method: "POST", params: data}).then(function(response){
				$scope.getBoard();
			});
		}
		
		$scope.dismiss = function(x){
			$http.post("/citadelsgame/dismiss").then(function(response){
				$scope.goto('citadels');
			});
		}
		
		
		$scope.showConfigDiv = function(){
			$scope.showConfig = 'y'
		}
		
		$scope.hideConfigDiv = function(){
			$scope.showConfig = 'n'
		}
		
		$scope.debug = function(){
			alert($scope.tcrown)
		}
		
		$scope.offturnHandle = function(){
			$scope.getBoard();
			$timeout(function(){
			    $scope.offturnHandle();
			},1000);
		}
		
		$scope.offturnHandle();
}]);