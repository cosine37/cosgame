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
		$scope.hand = []
		$scope.buildable = []
		$scope.canclickhand = []
		$scope.phase = "-1"
		
		setButtons = function(){
			
		}
			
		setHand = function(){
			$scope.canclickhand = $scope.buildable
			if ($scope.phase == "2"){
				
			} else {
				var i
				for (i=0;i<$scope.canclickhand.length;i++){
					$scope.canclickhand[i] = 'n';
				}
			}
		}
		
		$scope.startTurn = function(){
			$http.post('/citadelsgame/startturn').then(function(response){
				$scope.getBoard();
			});
		}
		
		$scope.endTurn = function(){
			$http.post('/citadelsgame/endturn').then(function(response){
				$scope.getBoard();
			});
		}
		
		$scope.seeCards = function(){
			$http.post('/citadelsgame/seecards').then(function(response){
				$scope.getBoard();
			});
		}
		
		$scope.chooseCard = function(x){
			var data = {"index" : x}
			$http({url: "/citadelsgame/choosecard", method: "POST", params: data}).then(function(response){
				$scope.getBoard();
			});
		}
		
		$scope.taketwo = function(){
			$http.post('/citadelsgame/taketwocoins').then(function(response){
				$scope.getBoard();
			});
		}
		
		$scope.build = function(x){
			var data = {"index" : x}
			$http({url: "/citadelsgame/build", method: "POST", params: data}).then(function(response){
				$scope.getBoard();
			});
			
		}
		
		$scope.getBoard = function(){
			$http.get('/citadelsgame/getboard').then(function(response){
				$scope.gamedata = JSON.stringify(response.data)
				$scope.hand = response.data.hand
				$scope.buildable = response.data.buildable
				$scope.phase = response.data.phase
				$scope.revealedCards = response.data.revealedCards
				setButtons()
				setHand()
			});
		}
		
		$scope.logout = function(){
			$http({url: "/logout", method: "POST"}).then(function(response){
				$scope.goto('login');
			});
		}
		
		$scope.getBoard();
		
}]);