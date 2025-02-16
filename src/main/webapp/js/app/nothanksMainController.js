var setUrl = function(d){
	hearder = "https://";
	server = "localhost:8080";
	return header + server + d;
}

var app = angular.module("nothanksMainApp", []);
app.controller("nothanksMainCtrl", ['$scope', '$window', '$http', '$document', '$timeout',
	function($scope, $window, $http, $document, $timeout){
		const thisTab = "nothanks";
		$http.get('/alltabs').then(function(response){
			var tempTabs = response.data;
			for (i=0;i<tempTabs.length;i++){
				if (tempTabs[i].path == thisTab){
					tempTabs[i].style = {
						"padding-top": "0px",
						"font-size": "24px",
						"color": tempTabs[i].color,
						"background-color": tempTabs[i].backgroundColor
					}
				} else {
					tempTabs[i].style = {}
				}
			}
			
			$scope.allTabs = tempTabs;
		});
		
		$scope.boards = []
		$scope.statuses = []
		$scope.lords = []
		$scope.canBack = []
		$scope.onTablesTab = true;
	
		$scope.goto = function(d){
			var x = "https://" + $window.location.host;
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
		
		$scope.getAllBoards = function(){
			$http.get('/nothanks/allboards').then(function(response){
				var n = response.data.value.length / 4;
				$scope.boards = []
				$scope.statuses = []
				$scope.lords = []
				$scope.canBack = []
				for (var i=0;i<n;i++){
					$scope.boards.push(response.data.value[i*4])
					var l = response.data.value[i*4+1]
					$scope.lords.push(l)
					var x = response.data.value[i*4+2]
					var t = ''
					if (x == '0'){
						t = '准备中'
					} else if (x == '2'){
						t = '游戏结束'
					} else {
						t = '游戏中'
					}
					$scope.statuses.push(t)
					var y = response.data.value[i*4+3]
					$scope.canBack.push(y)
				}
			});
		}
		
		$scope.newGame = function(){
			$http.post('/nothanksgame/newboard').then(function(response){
				$scope.goto('nothankscreategame');
			});
		}
		
		$scope.showTablesTab = function(){
			$scope.onTablesTab = true;
		}
		
		$scope.hideTablesTab = function(){
			$scope.onTablesTab = false;
		}
		
		$scope.goToBoard = function(index){
			var data = {"boardId" : $scope.boards[index]}
			$http({url: "/nothanksgame/setboardid", method: "POST", params: data}).then(function(response){
				$http.post("/nothanksgame/join").then(function(response){
					$scope.goto('nothankscreategame')
				});
			});
		}
		
		$scope.backToBoard = function(index){
			var data = {"boardId" : $scope.boards[index]}
			$http({url: "/nothanksgame/setboardid", method: "POST", params: data}).then(function(response){
				$scope.goto('nothanksgame')
			});
		}
		
		$scope.offturnHandle = function(){
			if ($scope.onTablesTab){
				$scope.getAllBoards();
			}
			
			$timeout(function(){
			    $scope.offturnHandle();
			},4000);
			
		}
		
		
		$scope.offturnHandle();
		
}]);
