var setUrl = function(d){
	hearder = "https://";
	server = "localhost:8080";
	return header + server + d;
}

var app = angular.module("citadelsCreateGameApp", []);
app.controller("citadelsCreateGameCtrl", ['$scope', '$window', '$http', '$document', '$timeout',
	function($scope, $window, $http, $document, $timeout){
		//$scope.crown = 0
	
		$scope.showConfig = 'n'
	
		$scope.goto = function(d){
			var x = "https://" + $window.location.host;
			$window.location.href = x + "/" + d;
		}
		
		$http.post('/username').then(function(response){
			$scope.username = response.data.value[0];
		});
		
		$scope.getBoard = function(){
			$http.get('/citadelsgame/getboard').then(function(response){
				if (response.data.id == "NE"){
					$scope.goto('citadels');
				} else {
					$scope.gamedata = JSON.stringify(response.data)
					$scope.playerNames = response.data.playerNames
					$scope.crown = parseInt(response.data.crown)
					$scope.tcrown = $scope.crown
					$scope.isLord = response.data.isLord
					$scope.status = response.data.status
					$scope.finishCount = response.data.finishCount
					$scope.regicide = response.data.regicide
					$scope.no8 = response.data.no8
					$scope.no9 = response.data.no9
					$scope.useDuoColor = response.data.useDuoColor
					$scope.useOmniColor = response.data.useOmniColor
					$scope.numDelicacyUsed = response.data.numDelicacyUsed
					if ($scope.regicide == "y"){
						$scope.regicideDisplay = "送葬者获得市长标记"
					} else {
						$scope.regicideDisplay = "回合结束时市长获得市长标记"
					}
					if ($scope.useDuoColor == "y"){
						$scope.duoColorButton = "不使用双类型建筑"
						$scope.useDuoColorDisplay = "使用"
					} else {
						$scope.duoColorButton = "使用双类型建筑"
						$scope.useDuoColorDisplay = "不使用"
					}
					
					if ($scope.useOmniColor == "y"){
						$scope.omniColorButton = "不使用变类型建筑"
						$scope.useOmniColorDisplay = "使用"
					} else {
						$scope.omniColorButton = "使用变类型建筑"
						$scope.useOmniColorDisplay = "不使用"
					}
					
					$scope.no8Display = "城管"
					if ($scope.no8 == "0"){
						$scope.no8Display = "城管"
					} else if ($scope.no8 == "1"){
						$scope.no8Display = "法官"
					}
					
					$scope.no9Display = "不使用"
					if ($scope.no9 == "-1"){
						$scope.no9Display = "不使用"
					} else if ($scope.no9 == "0"){
						$scope.no9Display = "记者"
					} else if ($scope.no9 == "1"){
						$scope.no9Display = "家具商"
					}
					if ($scope.status != "0"){
						$scope.goto('citadelsgame');
					}
				}
			});
		}
		
		$scope.giveCrown = function(){
			var data = {"crownIndex" : $scope.tcrown}
			$http({url: "/citadelsgame/givecrown", method: "POST", params: data}).then(function(response){
				$scope.getBoard();
			});
		}
		
		$scope.setEndNum = function(x){
			var data = {"endnum" : x}
			$http({url: "/citadelsgame/setendnum", method: "POST", params: data}).then(function(response){
				$scope.getBoard();
			});
		}
		
		$scope.setNo8 = function(x){
			var y = parseInt(x)
			var data = {"no8" : y}
			$http({url: "/citadelsgame/setno8", method: "POST", params: data}).then(function(response){
				$scope.getBoard();
			});
		}
		
		$scope.setNo9 = function(x){
			var y = parseInt(x)
			var data = {"no9" : y}
			$http({url: "/citadelsgame/setno9", method: "POST", params: data}).then(function(response){
				$scope.getBoard();
			});
		}
		
		$scope.setRegicide = function(){
			$http.post('/citadelsgame/setregicide').then(function(response){
				$scope.getBoard();
			});
		}
		
		$scope.setUseDuoColor = function(){
			$http.post('/citadelsgame/setuseduocolor').then(function(response){
				$scope.getBoard();
			});
		}
		
		$scope.setUseOmniColor = function(){
			$http.post('/citadelsgame/setuseomnicolor').then(function(response){
				$scope.getBoard();
			});
		}
		
		$scope.setNumDelicacy = function(x){
			var y = parseInt(x)
			var data = {"n" : y}
			$http({url: "/citadelsgame/setnumdelicacies", method: "POST", params: data}).then(function(response){
				$scope.getBoard();
			});
		}
		
		$scope.startGame = function(){
			/*
			if ($scope.playerNames.length < 4){
				alert("No enough players.  The players size must between 4~7")
			} else if ($scope.playerNames.length > 7){
				alert("Too much players.  The players size must between 4~7")
			} else {
				$http.post('citadelsgame/start').then(function(response){
					$scope.goto('citadelsgame');
				})
			}
			*/
			$http.post('citadelsgame/start').then(function(response){
				$scope.goto('citadelsgame');
			})
		}
		
		$scope.addBot = function(){
			$http.post('citadelsgame/addbot').then(function(response){
				$scope.getBoard();
			})
		}
		
		$scope.setTCrown = function(x){
			$scope.tcrown = x;
		}
		
		$scope.randomCrown = function(){
			var n = $scope.playerNames.length;
			var x = Math.floor(Math.random() * n);
			$scope.setTCrown(x);
		}
		
		$scope.kick = function(x){
			var data = {"index" : x}
			$http({url: "/citadelsgame/kick", method: "POST", params: data}).then(function(response){
				$scope.getBoard();
			});
		}
		
		$scope.dismiss = function(x){
			$http.post("/citadelsgame/dismiss").then(function(response){
				$scope.goto('citadels');
			});
		}
		
		
		$scope.showConfigDiv = function(){
			$scope.showConfig = 'y'
		}
		
		$scope.hideConfigDiv = function(){
			$scope.showConfig = 'n'
		}
		
		$scope.debug = function(){
			alert($scope.tcrown)
		}
		
		$scope.offturnHandle = function(){
			if ($scope.showConfig == 'n'){
				$scope.getBoard();
			}
			$timeout(function(){
			    $scope.offturnHandle();
			},5000);
		}
		
		$scope.offturnHandle();
}]);