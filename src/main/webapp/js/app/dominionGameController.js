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
		
		$scope.bigImage="/image/Dominion/cards/Dominion/Smithy.png";
		$scope.showBigImage = false;
		$scope.bigImageStyle = {};
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
			});
		});
		
		$scope.resign = function(){
			$scope.goto('dominionend');
		}
		
		a = function(image){
			$scope.bigImage = image;
			alert(image);
			$scope.showBigImage = true;
			alert($scope.showBigImage);
			$scope.bigImageStyle = {
				"height": "210px", 
				"width": "140px", 
				"background": "url(" + image + ")", 
				"background-size": "cover"
			}
		}
		
		$scope.showCard = function(image){
			a(image);
		}
		
		
}]);
