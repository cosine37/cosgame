var setUrl = function(d){
	hearder = "http://";
	server = "localhost:8080";
	return header + server + d;
}

var app = angular.module("indexApp", []);
app.controller("indexCtrl", ['$scope', '$window', '$http', '$document',
	function($scope, $window, $http, $document){
		const thisTab = "index";
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
	
		$scope.showHiddenImg = false;
	
		$scope.goto = function(d){
			var x = "http://" + $window.location.host;
			$window.location.href = x + "/" + d;
		}
		
		$http.post('/username').then(function(response){
			$scope.username = response.data.value[0];
		});
		
		$scope.expandDisplay = "展开"
		$scope.showCards = false;
		
		$scope.show = function(){
			if ($scope.showCards){
				$scope.showCards = false;
				$scope.expandDisplay = "展开"
			} else {
				$scope.showCards = true;
				$scope.expandDisplay = "收起"
			}
			
		}
		/*
		$scope.show = function(x){
			var imgs = ["p611", "p405", "p303", "p509", "p612"]
			var imgUrl = "url('/image/Citadels/Cards/" + imgs[x] + ".png')"
			$scope.imgStyle = {
				"background": imgUrl,
				"background-size": "cover",
				"height": "544px",
				"width": "357px"
			}
			$scope.showHiddenImg = true;
		}
		
		$scope.unshow = function(){
			$scope.showHiddenImg = false;
		}
		*/
		$scope.logout = function(){
			$http({url: "/logout", method: "POST"}).then(function(response){
				$scope.goto('login');
			});
		}
}]);
