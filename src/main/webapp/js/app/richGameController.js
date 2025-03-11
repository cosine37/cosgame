var setUrl = function(d){
	hearder = "https://";
	server = "localhost:8080";
	return header + server + d;
}

var app = angular.module("richGameApp", ["ngWebSocket"]);
app.controller("richGameCtrl", ['$scope', '$window', '$http', '$document', '$timeout', '$websocket',
	function($scope, $window, $http, $document, $timeout, $websocket){
		var ws = $websocket("wss://" + $window.location.host + "/rich/boardrefresh");
		var heartCheck = {
			timeout: 10000,//10s
			timeoutObj: null,
			reset: function(){
				clearTimeout(this.timeoutObj);
			　　 	this.start();
			},
			start: function(){
				this.timeoutObj = setTimeout(function(){
					var msg = $scope.username + " heart beat"
					ws.send(msg);
				}, this.timeout)
			}
		}
		ws.onError(function(event) {
		});
		
		ws.onClose(function(event) {
		});
		
		ws.onOpen(function() {
			heartCheck.start();
		});
	
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
		
		$scope.dismiss = function(){
			$http.post('/rich/dismiss').then(function(response){
				ws.send("dismiss");
			});
		}
		
		$scope.buttonPress = function(option){
			var data = {"option" : option}
			$http({url: "/rich/buttonpress", method: "POST", params: data}).then(function(response){
				ws.send("refresh");
			});
		}
		
		genArray = function(s,n){
			ans = []
			for (i=s;i<n;i++) ans.push(i);
			return ans;
		}
		
		$scope.OFFTURN = -1;
		$scope.ROLL = 100;
		$scope.MOVE = 200;
		$scope.RESOLVE = 300;
		
		$scope.ESTATE = 1;
		
		setMapLayout = function(){
			var i,j
			$scope.bottomRow = []
			$scope.topRow = []
			for (i=0;i<$scope.map.width;i++){
				$scope.bottomRow.push($scope.map.places[$scope.map.width-i-1])
				$scope.topRow.push($scope.map.places[$scope.map.width+$scope.map.height-2+i])
			}
			$scope.secondRow = []
			$scope.secondRow.push([$scope.map.places[$scope.map.width+$scope.map.height-3]])
			$scope.secondRow.push([$scope.map.places[$scope.map.width*2+$scope.map.height-2]])
			
			$scope.restRows = []
			for (i=0;i<$scope.map.height-3;i++){
				var restRowCols = []
				restRowCols.push([$scope.map.places[$scope.map.width+$scope.map.height-4-i]])
				restRowCols.push([$scope.map.places[$scope.map.width*2+$scope.map.height-1+i]])
				$scope.restRows.push(restRowCols);
			}
			
			//alert(Json.stringify($scope.restRows[0]))
		}
		
		$scope.getBoard = function(){
			$http.get('/rich/getboard').then(function(response){
				$scope.gamedata = response.data
				$scope.id = response.data.id
				
				//$scope.playerNames = response.data.playerNames;
				if ($scope.id == "NE"){
					$scope.goto('rich');
					return;
				}

				$scope.status = response.data.status;
				$scope.phase = response.data.phase;
				$scope.lord = response.data.lord;
				$scope.players = response.data.players;
				$scope.logs = response.data.logs;
				
				$scope.map = response.data.map;
				setMapLayout();
				//$scope.$apply()
			});
		}
		
		$scope.getBoard();
		
		ws.onMessage(function(){
			$scope.getBoard();
		});
		
		
}]);
