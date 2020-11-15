var setUrl = function(d){
	hearder = "http://";
	server = "localhost:8080";
	return header + server + d;
}

var app = angular.module("pokewhatCreateGameApp", []);
app.controller("pokewhatCreateGameCtrl", ['$scope', '$window', '$http', '$document', '$timeout',
	function($scope, $window, $http, $document, $timeout){
		$scope.avatarTableCols = [0,1,2,3,4]
		$scope.avatarTableRows = [0,1,2]
		$scope.avatarStyles = [];
		$scope.playerAvatarStyles = [];
	
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
		
		$scope.addBot = function(){
			$http.post('/pokewhatgame/addbot').then(function(response){
				$scope.getBoard();
			});
		}
		
		$scope.startGame = function(){
			$http.post('/pokewhatgame/startgame').then(function(response){
				$scope.goto('pokewhatgame');
			});
		}
		
		$scope.kick = function(x){
			var data = {"index" : x}
			$http({url: "/pokewhatgame/kick", method: "POST", params: data}).then(function(response){
				$scope.getBoard()
			});
		}
		
		$scope.dismiss = function(){
			$http.post('/pokewhatgame/dismiss').then(function(response){
				$scope.goto('pokewhatgame');
			});
		}
		
		$scope.changeFirstPlayer = function(x){
			var data = {"index" : x}
			alert(x)
			$http({url: "/pokewhatgame/changefirstplayer", method: "POST", params: data}).then(function(response){
				$scope.getBoard()
			});
		}
		
		$scope.changeAvatar = function(x){
			var data = {"x" : x}
			$http({url: "/pokewhatgame/changeavatar", method: "POST", params: data}).then(function(response){
				$scope.getBoard()
			});
		}
		
		setAvatars = function(){
			$scope.avatarStyles = [];
			for (i=0;i<$scope.avatars.length;i++){
				var imgUrl = "url('/image/Pokewhat/Avatar/" + $scope.avatars[i] + ".png')"
				singleAvatarStyle = {
					"background": imgUrl,
					"background-size": "cover",
					"border-style" : "solid",
					"border-width" : "2px"
				}
				$scope.avatarStyles.push(singleAvatarStyle);
			}
			$scope.playerAvatarStyles = [];
			for (i=0;i<$scope.playerAvatars.length;i++){
				var imgUrl = "url('/image/Pokewhat/Avatar/" + $scope.playerAvatars[i] + ".png')"
				singleAvatarStyle = {
					"background": imgUrl,
					"background-size": "cover",	
				}
				$scope.playerAvatarStyles.push(singleAvatarStyle);
			}
		}
		
		$scope.getBoard = function(){
			$http.get('/pokewhatgame/getboard').then(function(response){
				$scope.id = response.data.id;
				if ($scope.id == "NE"){
					$scope.goto('/pokewhat');
					return;
				}
				$scope.gamedata = response.data;
				$scope.lord = response.data.lord;
				$scope.status = response.data.status;
				$scope.curPlayer = parseInt(response.data.curPlayer);
				$scope.playerNames = response.data.playerNames;
				$scope.avatars = response.data.avatars;
				$scope.playerAvatars = response.data.playerAvatars;
				if ($scope.status != "0"){
					$scope.goto('pokewhatgame');
				}
				setAvatars()
			});
		}
		
		$scope.offturnHandle = function(){
			$scope.getBoard();
			$timeout(function(){
			    $scope.offturnHandle();
			},5000);
		}
		
		$scope.offturnHandle();
		
}]);
