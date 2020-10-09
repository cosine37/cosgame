var setUrl = function(d){
	hearder = "http://";
	server = "localhost:8080";
	return header + server + d;
}

var app = angular.module("nothanksEndGameApp", []);
app.controller("nothanksEndGameCtrl", ['$scope', '$window', '$http', '$document',
	function($scope, $window, $http, $document){
		$scope.allHandsStyle = []
		
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
		
		setAllHands = function(){
			$scope.allHandsStyle = []
			for (var i=0;i<$scope.allHands.length;i++){
				var singleHandStyle = []
				for (var j=0;j<$scope.allHands[i].length;j++){
					var imgUrl = "url('/image/Nothanks/Cards/" + $scope.allHands[i][j] + ".png')"
					var marginLeft = "0px"
					var singleCardStyle = {
						"background": imgUrl,
						"background-size": "cover",
						"float": "left",
						"margin-left" :marginLeft
					}
					singleHandStyle.push(singleCardStyle)
				}
				$scope.allHandsStyle.push(singleHandStyle)
			}
		}
		
		$scope.dismiss = function(x){
			$http.post("/nothanksgame/dismiss").then(function(response){
				$scope.goto('nothanks');
			});
		}
		
		$scope.getBoard = function(){
			$http.get('/nothanksgame/getboard').then(function(response){
				$scope.gamedata = response.data
				$scope.playerNames = response.data.playerNames
				$scope.allHands = response.data.allHands;
				$scope.scores = response.data.scores;
				$scope.lord = response.data.lord
				setAllHands();
			});
		}
		
		$scope.getBoard();
		
}]);
