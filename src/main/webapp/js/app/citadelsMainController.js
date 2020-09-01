var setUrl = function(d){
	hearder = "http://";
	server = "localhost:8080";
	return header + server + d;
}

var app = angular.module("citadelsMainApp", []);
app.controller("citadelsMainCtrl", ['$scope', '$window', '$http', '$document', '$timeout',
	function($scope, $window, $http, $document, $timeout){
	
		$scope.boards = []
		$scope.lords = []
		$scope.statuses = []
		$scope.canBack = []
	
		$scope.goto = function(d){
			var x = "http://" + $window.location.host;
			$window.location.href = x + "/" + d;
		}
		
		$http.post('/username').then(function(response){
			$scope.username = response.data.value[0];
		});
		
		$scope.getAllBoards = function(){
			$http.get('/citadels/allboards').then(function(response){
				var n = response.data.value.length / 4;
				$scope.boards = []
				$scope.statuses = []
				for (var i=0;i<n;i++){
					$scope.boards.push(response.data.value[i*4])
					var l = response.data.value[i*4+1]
					$scope.lords.push(l)
					var x = response.data.value[i*4+2]
					var t = ''
					if (x == '0'){
						t = 'pre-game'
					} else if (x == '3'){
						t = 'end game'
					} else {
						t = 'in game'
					}
					$scope.statuses.push(t)
					var y = response.data.value[i*4+3]
					$scope.canBack.push(y)
				}
			});
		}
		
		
		$scope.newGame = function(){
			$http.post("/citadelsgame/newboard").then(function(response){
				$scope.goto('citadelscreategame');
			});
		}
		
		$scope.goToBoard = function(index){
			var data = {"boardId" : $scope.boards[index]}
			$http({url: "/citadelsgame/setboardid", method: "POST", params: data}).then(function(response){
				$http.post("/citadelsgame/join").then(function(response){
					$scope.goto('citadelscreategame')
				});
			});
		}
		
		$scope.backToBoard = function(index){
			var data = {"boardId" : $scope.boards[index]}
			$http({url: "/citadelsgame/setboardid", method: "POST", params: data}).then(function(response){
				$scope.goto('citadelsgame')
			});
		}
		
		$scope.logout = function(){
			$http({url: "/logout", method: "POST"}).then(function(response){
				$scope.goto('login');
			});
		}
		
		$scope.offturnHandle = function(){
			$scope.getAllBoards();
			$timeout(function(){
			    $scope.offturnHandle();
			},1000);
		}
		
		$scope.offturnHandle();
		
}]);
