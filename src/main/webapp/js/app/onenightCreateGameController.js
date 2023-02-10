var setUrl = function(d){
	hearder = "http://";
	server = "localhost:8080";
	return header + server + d;
}

var app = angular.module("onenightCreateGameApp", []);
app.controller("onenightCreateGameCtrl", ['$scope', '$window', '$http', '$document', '$timeout',
	function($scope, $window, $http, $document, $timeout){
		$scope.settings = [0];
		$scope.soleWolfOption = false;
	
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
		
		$scope.kick = function(x){
			var data = {"index" : x}
			$http({url: "/onenight/kick", method: "POST", params: data}).then(function(response){
				//ws.send("kick");
				$scope.getBoard();
			});
		}
		
		$scope.dismiss = function(){
			$http.post("/onenight/dismiss").then(function(response){
				//ws.send("dismiss");
				$scope.getBoard();
			});
		}
		
		$scope.start = function(){
			if ($scope.soleWolfOption){
				$scope.settings[0] = 1;
			}
			var data = {"settings" : $scope.settings}
			$http({url: "/onenightgame/startgame", method: "POST", params: data}).then(function(response){
				$scope.goto('onenightgame');
			});
			
		}
		
		$scope.addBot = function(){
			$http.post('/onenightgame/addbot').then(function(response){
				$scope.getBoard();
			});
		}
		
		$scope.getBoard = function(){
			$http.get('/onenightgame/getboard').then(function(response){
				if (response.data.id == "NE"){
					alert("该游戏已解散");
					$scope.goto('onenight');
					return;
				}
				$scope.gamedata = response.data
				$scope.status = response.data.status
				$scope.playerNames = response.data.playerNames
				$scope.lord = response.data.lord
				var kicked = true;
				for (i=0;i<$scope.playerNames.length;i++){
					if ($scope.playerNames[i] == $scope.username){
						kicked = false;
					}
				}
				if (kicked){
					alert("你已被" + $scope.lord + "踢出");
					$scope.goto('architect');
					return;
				}
				if ($scope.status != "0"){
					$scope.goto('onenightgame')
				}
			});
		}
		
		$scope.offturnHandle = function(){
			$scope.getBoard();
			$timeout(function(){
			    $scope.offturnHandle();
			},2500);
		}
		
		$scope.offturnHandle();
}]);
