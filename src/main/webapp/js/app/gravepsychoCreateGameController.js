var setUrl = function(d){
	hearder = "http://";
	server = "localhost:8080";
	return header + server + d;
}

var app = angular.module("gravepsychoCreateGameApp", []);
app.controller("gravepsychoCreateGameCtrl", ['$scope', '$window', '$http', '$document', '$timeout',
	function($scope, $window, $http, $document, $timeout){
		$scope.avatars=[]
		for (i=0;i<4;i++){
			var singleLine = []
			for (j=1;j<=4;j++){
				var num = i*4+j
				var imgSrc = "/image/Gravepsycho/Avatar/" + num.toString() + ".png"
				singleLine.push(imgSrc)
			}
			$scope.avatars.push(singleLine)
		}
	
	
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
		
		$scope.changeAvatar = function(x){
			var data = {"x" : x}
			$http({url: "/gravepsycho/setavatar", method: "POST", params: data}).then(function(response){
				$scope.getBoard()
			});
		}
		
		$scope.startGame = function(){
			$http.post('/gravepsycho/startgame').then(function(response){
				$scope.goto('gravepsychogame')
			});
		}
		
		$scope.dismiss = function(){
			$http.post('/gravepsycho/dismiss').then(function(response){
				$scope.goto('gravepsycho')
			});
		}
		
		$scope.getBoard = function(){
			$http.get('/gravepsycho/getboard').then(function(response){
				$scope.gamedata = response.data
				$scope.id = response.data.id
				$scope.status = response.data.status;
				$scope.playerNames = response.data.playerNames;
				$scope.avatar = response.data.avatar
				if ($scope.id == "NE"){
					$scope.goto('/gravepsycho');
					return;
				}
				if ($scope.status != "0"){
					$scope.goto('/gravepsychogame')
				}
				$scope.lord = response.data.lord
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
