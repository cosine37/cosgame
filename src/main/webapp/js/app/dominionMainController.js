var setUrl = function(d){
	hearder = "http://";
	server = "localhost:8080";
	return header + server + d;
}

var app = angular.module("dominionMainApp", []);
app.controller("dominionMainCtrl", ['$scope', '$window', '$http', '$document', '$timeout',
	function($scope, $window, $http, $document, $timeout){
	
		$scope.showedExpansion = 0;
		$scope.setShowedExpansion = function(x){
			$scope.showedExpansion = x;
		}
		
		//TODO: customize this
		$scope.numplayers = 2;
	
		$scope.goto = function(d){
			var x = "http://" + $window.location.host;
			$window.location.href = x + "/" + d;
		}
		
		$http.post('/username').then(function(response){
			$scope.username = response.data.value[0];
		});
		
		$http.get('/dominioninfo/allcardintro').then(function(response){
			$scope.expansionsinfo = response.data;

		})
		
		$scope.logout = function(){
			$http({url: "/logout", method: "POST"}).then(function(response){
				$scope.goto('login');
			});
		}
		
		$scope.newGame = function(){
			$http.post("/dominiongame/newgame").then(function(response){
				$scope.goto('dominiongame');
			})
		}
		
		$scope.newBoard = function(){
			var data = {"numPlayers": $scope.numplayers};
			$http({url: "/dominiongame/newboard", method: "POST", params: data}).then(function(response){
				$scope.goto("dominionboard");
			});
		}
		
		$scope.enterBoard = function(x){
			var data = {"boardId": x};
			$http({url: "/dominiongame/enterboard", method: "POST", params: data}).then(function(response){
				$scope.goto("dominionboard");
			});
		}
		
		$scope.getBoard = function(){
			$http.post("/dominionboards").then(function(response){
				var lst = response.data.value;
				var i = 0;
				$scope.boardIds = new Array();
				while (i<lst.length){
					var boardObject = new Object();
					boardObject.id = lst[i];
					i = i+1;
					boardObject.status = lst[i];
					i = i+1;
					boardObject.lord = lst[i];
					i = i+1;
					boardObject.playersInfo = lst[i];
					i = i+1;
					boardObject.enterable = lst[i];
					i = i+1;
					$scope.boardIds.push(boardObject);
				}
			});
			$timeout(function(){
			    $scope.getBoard();
			},1000);
		}
		
		$scope.getBoard();
		
}]);
