var setUrl = function(d){
	hearder = "http://";
	server = "localhost:8080";
	return header + server + d;
}

var app = angular.module("citadelsGameApp", []);
app.controller("citadelsGameCtrl", ['$scope', '$window', '$http', '$document',
	function($scope, $window, $http, $document){
	
		$scope.goto = function(d){
			var x = "http://" + $window.location.host;
			$window.location.href = x + "/" + d;
		}
		
		$http.post('/username').then(function(response){
			$scope.username = response.data.value[0];
		});
		
		$scope.gamedata = "nothing"
		
			
		$scope.taketwo = function(){
			$http.post('/citadelsgame/taketwocoins').then(function(response){
				$http.get('/citadelsgame/getboard').then(function(response){
					$scope.gamedata = JSON.stringify(response.data);
					//alert(JSON.stringify(response.data));
				});
			});
		}
			
		$scope.startGame = function(){
			$http.post('/citadelsgame/start').then(function(response){
				$http.get('/citadelsgame/getboard').then(function(response){
					$scope.gamedata = JSON.stringify(response.data);
					//alert(JSON.stringify(response.data));
				});
			});
		}
		
		$scope.logout = function(){
			$http({url: "/logout", method: "POST"}).then(function(response){
				$scope.goto('login');
			});
		}
		
}]);