var setUrl = function(d){
	hearder = "https://";
	server = "localhost:8080";
	return header + server + d;
}

var app = angular.module("registerApp", []);
app.controller("registerCtrl", ['$scope', '$window', '$http', '$document',
	function($scope, $window, $http, $document){
		$scope.goto = function(d){
			var x = "https://" + $window.location.host;
			$window.location.href = x + "/" + d;
		}
		
		$scope.register = function(){
			var u = $scope.username;
			var p = $scope.p1;
			var p2 = $scope.p2;
			if (p == null || u == null || p == "" || u == ""){
				alert("Username and/or password cannot be empty!");
				return;
			}
			var data = {"username": u};
			$http({url: "/register/user/exists", method: "POST", params: data}).then(function(response){
				$scope.result = response.data.value[0];
				if ($scope.result == "exists"){
					alert("User "+u+" already exists!");
					$scope.username = "";
					$scope.p1 = "";
					$scope.p2 = "";
				} else if (p!=p2){
					alert("Passwords do not match!");
					$scope.p1 = "";
					$scope.p2 = "";
				} else {
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
					data = {"username": u, "encrypted": e};
					$http({url: "/register/user", method: "POST", params: data}).then(function(response){
						alert("Account created successfully!");
						$scope.goto('login');
					});
				}
			});
		}
}]);