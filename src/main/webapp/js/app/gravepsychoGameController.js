var setUrl = function(d){
	hearder = "http://";
	server = "localhost:8080";
	return header + server + d;
}

var app = angular.module("gravepsychoGameApp", []);
app.controller("gravepsychoGameCtrl", ['$scope', '$window', '$http', '$document', '$timeout',
	function($scope, $window, $http, $document, $timeout){
		$scope.myDecision = "-1"
		$scope.revealed = []
		
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
		
		$scope.decision = function(x){
			var data = {"x" : x}
			$http({url: "/gravepsycho/decision", method: "POST", params: data}).then(function(response){
				$scope.getBoard()
			});
		}
		
		setRevealedStyle = function(){
			$scope.revealedStyle = []
			for (i=0;i<$scope.revealed.length;i++){
				var imgUrl = "url('/image/Gravepsycho/Cards/" + $scope.revealed[i] + ".png')"
				var singleStyle = {
					"background": imgUrl,
					"background-size": "cover"
				}
				$scope.revealedStyle.push(singleStyle)
			}
		}
		
		$scope.getBoard = function(){
			$http.get('/gravepsycho/getboard').then(function(response){
				$scope.gamedata = response.data
				$scope.status = response.data.status
				if ($scope.status == '4'){
					$scope.goto('gravepsychoendgame')
				}
				$scope.round = response.data.round
				$scope.myIndex = response.data.myIndex;
				$scope.myDecision = response.data.myDecision;
				$scope.playerNames = response.data.playerNames;
				$scope.revealed = response.data.revealed;
				$scope.moneyThisTurn = response.data.moneyThisTurn;
				$scope.myMoney = response.data.myMoney;
				setRevealedStyle()
			});
		}
		
		$scope.offturnHandle = function(){
			if ($scope.myDecision != "0"){
				$scope.getBoard();
			}
			$timeout(function(){
			    $scope.offturnHandle();
			},1500);
		}
		
		$scope.offturnHandle();
}]);