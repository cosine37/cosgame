var setUrl = function(d){
	hearder = "http://";
	server = "localhost:8080";
	return header + server + d;
}

var app = angular.module("loginApp", []);
app.controller("loginCtrl", ['$scope', '$window', '$http', '$document',
	function($scope, $window, $http, $document){
	
		$scope.goto = function(d){
			var x = "http://localhost:8080";
			$window.location.href = x + "/" + d;
		}
		
		$scope.encrypted = "";
		
		$scope.login = function(){
			var u = $scope.username;
			var p = $scope.password;
			var e = "";
			var i = 0;
			var t = 0;
			var c = 0;
			for (i=0;i<p.length;i++){
				c = p.charCodeAt(i);
				t = t+c+i;
				e = e + t.toString();
				t = c;
			}

			var data = {"username": u, "encrypted": e};
			$http({url: "/login/verify", method: "POST", params: data}).then(function(response){
				$scope.result = response.data.value;
				alert($scope.result);
			});
		}
}]);
