var setUrl = function(d){
	hearder = "http://";
	server = "localhost:8080";
	return header + server + d;
}

var app = angular.module("dominionGameApp", []);

app.directive('ngRightClick',function($parse){
    return function (scope,element,attrs){
        var fn = $parse(attrs.ngRightClick);
        element.bind('contextmenu',function(event){
            event.preventDefault();
            fn(scope,{$event:event});
        })
    }
});

app.controller("dominionGameCtrl", ['$scope', '$window', '$http', '$document',
	function($scope, $window, $http, $document){
		$scope.base=[];
		$scope.kindom=[];
		$scope.status="first cards";
		$scope.bigImage="/image/Dominion/cards/Dominion/Smithy.png";
		$scope.showBigImage = false;
		$scope.bigImageStyle = {};
		$scope.topMessage = "Your starting cards";
		$scope.phaseButton = "start";
		/*
		$scope.bigImageStyle = {
			"height": "210px", 
			"width": "140px", 
			"background": "url(" + $scope.bigImage + ")", 
			"background-size": "cover"
		}
		*/
		$scope.baseStyle={
			"height": "109px",
			"width": "71px"
		};
		
		$scope.kindomStyle={
			"height": "210px",
			"width": "140px"
		};
		
		$scope.playStyle={
			"height": "109px",
			"width": "71px"
		}
		
		$scope.goto = function(d){
			var x = "http://localhost:8080";
			$window.location.href = x + "/" + d;
		}
		
		$http.post('/dominiongame/getbase').then(function(response){
			$scope.base=response.data;
			$http.post('/dominiongame/getkindom').then(function(response){
				$scope.kindom=response.data;
				$http.post('/dominiongame/getstatus').then(function(response){
					$scope.status=response.data.value[0];
					if ($scope.status == "first cards"){
						$scope.topMessage = "Your starting cards";
						$scope.phaseButton = "start";
						$http.post('/dominiongame/firstcards').then(function(response){
							$scope.hand=response.data;
						})
					} else if ($scope.status == "in game"){
						$scope.topMessage = "";
						$scope.phaseButton = "";
						$http.post('/dominiongame/gethand').then(function(response){
							$scope.hand=response.data;
						})
					} else {
						
					}
					
				})
			});
		});
		
		$scope.resign = function(){
			$scope.goto('dominionend');
		}
		
		$scope.showCard = function(image){
			$scope.bigImage = image;
			$scope.showBigImage = true;
			$scope.bigImageStyle = {
				"height": "420px", 
				"width": "280px", 
				"position": "absolute",
				"left": "50%",
				"top": "50%",
				"margin-left": "-140px",
				"margin-top": "-210px",
				"background": "url(" + image + ")", 
				"background-size": "cover"
			}
			$scope.bigImageDivStyle = {
				"position": "absolute",
				"left": "0%",
				"top": "0%",
				"height": "100%",
				"width": "100%",
				"background": "rgba(150, 150, 150, 0.5)"
			}
		}
		
		$scope.unshowBigImage = function(){
			$scope.showBigImage = false;
		}
		
		$scope.pb = function(){
			if ($scope.status == "first cards"){
				$http.post('/dominiongame/finishfirstcards').then(function(response){
					$http.post('/dominiongame/gethand').then(function(response){
						$scope.hand=response.data;
					});
				});
			}
		}
		
		
		
}]);
