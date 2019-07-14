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
			
			if (p == null || u == null || p == "" || u == ""){
				alert("Username and/or password cannot be empty!");
				return;
			}

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
				$scope.result = response.data.value[0];
				if ($scope.result=="verified"){
					$http({url: "/login/username", method: "POST", params: data}).then(function(response){
						data = {"username": u};
						$scope.goto('index');
					});
					
				} else {
					alert("Wrong username and/or password!");
					$scope.username="";
					$scope.password="";
				}
			});
		}
		
}]);
