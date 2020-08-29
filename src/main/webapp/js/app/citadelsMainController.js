var setUrl = function(d){
	hearder = "http://";
	server = "localhost:8080";
	return header + server + d;
}

var app = angular.module("citadelsMainApp", []);
app.controller("citadelsMainCtrl", ['$scope', '$window', '$http', '$document', '$timeout',
	function($scope, $window, $http, $document, $timeout){
	
		$scope.boards = []
		$scope.statuses = []
		
	
		$scope.goto = function(d){
			var x = "http://" + $window.location.host;
			$window.location.href = x + "/" + d;
		}
		
		$http.post('/username').then(function(response){
			$scope.username = response.data.value[0];
		});
		
		$scope.getAllBoards = function(){
			$http.get('/citadels/allboards').then(function(response){
				var n = response.data.value.length / 2;
				$scope.boards = []
				$scope.statuses = []
				for (var i=0;i<n;i++){
					$scope.boards.push(response.data.value[i*2])
					var x = response.data.value[i*2+1]
					var t = ''
					if (x == '0'){
						t = 'pre-game'
					} else if (x == '3'){
						t = 'end game'
					} else {
						t = 'in game'
					}
						
					$scope.statuses.push(t)
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
