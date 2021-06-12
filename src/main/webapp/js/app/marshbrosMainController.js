var setUrl = function(d){
	hearder = "http://";
	server = "localhost:8080";
	return header + server + d;
}

var app = angular.module("marshbrosMainApp", ["ngWebSocket"]);
/*
app.factory('socketData', function ($websocket, $location) {
    var host = $location.host();
    if ($location.port() && $location.port() != 80 && $location.port() != 443) {
        host = host + ':' + $location.port();
    }
    var ws = "ws";
    if ($location.protocol() == 'https') {
        ws = "wss";
    }

    // 开始连接
    var dataStream = $websocket(ws + '://' + host + '/Api/App');
    dataStream.reconnectIfNotNormalClose = true;
    var collection = [];
    var methods = {
        lastestdata: {},
        readyState: 0,
        collection: collection,
        sendData: function (data) {
            dataStream.send(JSON.stringify(data));
        }
    };
    dataStream.onMessage(function (message) {
    methods.readyState = dataStream.readyState;
    methods.lastestdata = JSON.parse(message.data);
    collection.push(JSON.parse(message.data));
    });
    dataStream.onError(function (message) {
//监控状态变化，实时跟进连接状态
        methods.readyState = dataStream.readyState;
    });
    dataStream.onOpen(function (message) {
        methods.readyState = dataStream.readyState;
    });

    dataStream.onClose(function (message) {
        methods.readyState = dataStream.readyState;
    });
    return methods;
})
*/
app.controller("marshbrosMainCtrl", ['$scope', '$window', '$http', '$document', '$websocket',
	function($scope, $window, $http, $document, $websocket){
		
		var ws = $websocket("ws://localhost:13737/marshbros/allboardsrefresh");
		ws.onError(function(event) {
		});
	
		ws.onClose(function(event) {
		});
	
		ws.onOpen(function() {
		});
		
		
	
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
		
		$scope.newGame = function(){
			$http({url: "/marshbros/newboard", method: "POST"}).then(function(response){
				var json_data = '{type:notify,content:refresh}';
		        ws.send(json_data);
				$scope.goto('marshbroscreategame');
			});
		}
		
		$scope.getAllBoards = function(){
			$http.get('/marshbros/allboards').then(function(response){
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
					} else if (x == '5'){
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
		
		$scope.getAllBoards();
		
		ws.onMessage(function(){
			$scope.getAllBoards();
		});
		
		
}]);
