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
		$scope.phase="";
		$scope.bigImage="";
		$scope.showBigImage = false;
		$scope.showPhaseButton = true;
		$scope.bigImageStyle = {};
		$scope.topMessage = "Your starting cards";
		$scope.phaseButton = "start";
		$scope.action=0
		$scope.buy=0;
		$scope.coin=0;
		$scope.choosehand=[];
		$scope.showClearButton = false;
		//$scope.chooseUpper=0;
		//$scope.chooseLower=0;

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
		
		gethand = function(){
			$http.post('/dominiongame/gethand').then(function(response){
				$scope.hand=response.data;
				var n = $scope.hand.length;
				$scope.choosehand = new Array(n);
				var i;
				for (i=0;i<n;i++){
					$scope.choosehand[i] = 0;
				}
			});
		}
		
		getaddon = function(){
			$http.post('dominiongame/addons').then(function(response){
				$scope.action=response.data.value[0];
				$scope.buy=response.data.value[1];
				$scope.coin=response.data.value[2];
				$scope.topMessage = $scope.topMessage + " Action: " + $scope.action;
				$scope.topMessage = $scope.topMessage + " Buy: " + $scope.buy;
				$scope.topMessage = $scope.topMessage + " Coin: " + $scope.coin;
				gethand();
			});
		}
		
		getphase = function(){
			$http.post('/dominiongame/getphase').then(function(response){
				$scope.phase=response.data.value[0];
				$scope.showPhaseButton = true;
				$scope.showClearButton = false;
				if ($scope.phase == "Start"){
					
				} else if ($scope.phase == "Action"){
					$http.post('/dominiongame/getask').then(function(response){
						$scope.ask = response.data;
						if ($scope.ask.type == 0){
							$scope.topMessage = "You may play Action cards";
							$scope.phaseButton = "End Action";
							getaddon();
						} else if ($scope.ask.type == 1){
							$scope.topMessage = $scope.ask.msg;
							$scope.showPhaseButton = false;
							getaddon();
						} else if ($scope.ask.type == 2){
							$scope.topMessage = $scope.ask.msg;
							$scope.showPhaseButton = showPhaseButtonWhenChooseHand();
							$scope.showClearButton = true;
							$scope.phaseButton = "Confirm";
							getaddon();
						}
					});
					
				} else if ($scope.phase == "Treasure"){
					if ($scope.phaseButton == "Buy Cards"){
						$scope.topMessage = "You may play Treasure cards";
						getaddon();
					} else {
						$scope.topMessage = "You may play Treasure cards";
						$scope.phaseButton = "Autoplay Treasures";
						getaddon();
					}
				} else if ($scope.phase == "Buy"){
					$scope.topMessage = "You may buy cards";
					$scope.phaseButton = "End Buy";
					getaddon();
				} else if ($scope.phase == "Night"){
					$scope.topMessage = "You may play Night cards";
					$scope.phaseButton = "End Night";
				} else if ($scope.phase == "Cleanup"){
					
				} else if ($scope.phase == "Offturn"){
					$scope.topMessage = "It's not your turn";
					$scope.phaseButton = "Don't click";
					$http.post('/dominiongame/nextplayer').then(function(response){
						$http.post('/dominiongame/getbase').then(function(response){
							$scope.base=response.data;
							$http.post('/dominiongame/getkindom').then(function(response){
								$scope.kindom=response.data;
								getstatus();
							});
						});
					});
				}
				
			});
		}
		
		getstatus = function(){
			$http.post('/dominiongame/getstatus').then(function(response){
				$scope.status=response.data.value[0];
				if ($scope.status == "first cards"){
					$scope.topMessage = "Your starting cards";
					$scope.phaseButton = "start";
					$http.post('/dominiongame/firstcards').then(function(response){
						$scope.hand=response.data;
					});
				} else if ($scope.status == "in game"){
					getphase();
				} else if ($scope.status == "end game"){
					alert("game ends");
					$scope.goto('dominionend');
				}
				
			});
		}
		
		$http.post('/dominiongame/getbase').then(function(response){
			$scope.base=response.data;
			$http.post('/dominiongame/getkindom').then(function(response){
				$scope.kindom=response.data;
				getstatus();
			});
		});
		
		$scope.resign = function(){
			$http.post('/dominiongame/resign').then(function(response){
				$scope.goto('dominionend');
			});
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
					getsupply();
				});
			} else if ($scope.status == "in game"){
				if ($scope.phase == "Offturn"){
					
				} else if ($scope.phase == "Treasure" && $scope.phaseButton == "Autoplay Treasures"){
					$http.post('dominiongame/autoplaytreasure').then(function(response){
						$scope.phaseButton = "Buy Cards";
						getstatus();
					});
				} else if ($scope.phase == "Action" && $scope.ask.type == 2){
					var s = "";
					var i,j,x;
					for (i=0;i<$scope.choosehand.length;i++){
						x = $scope.choosehand[i];
						for (j=0;j<x;j++){
							if (s == ""){
								s = $scope.hand[i].name;
							} else {
								s = s+","+$scope.hand[i].name;
							}
						}
					}
					var data = {"ans": s};
					$http({url: "/dominiongame/response", method: "POST", params: data}).then(function(response){
						$scope.ask = response.data;
						getsupply();
					});
				} else {
					$http.post('/dominiongame/nextphase').then(function(response){
						getstatus();
					});
				}
			}
		}
		
		$scope.choose = function(index){
			var data = {"ans": index.toString()};
			$http({url: "/dominiongame/response", method: "POST", params: data}).then(function(response){
				$scope.ask = response.data;
				getsupply();
			});
		}
		
		playCard = function(card){
			var data = {"cardName": card.name};
			$http({url: "/dominiongame/playcard", method: "POST", params: data}).then(function(response){
				$scope.ask = response.data;
				getsupply();
			});
		}
		
		$scope.clearChoosehand = function(){
			var i;
			for (i=0;i<$scope.choosehand.length;i++){
				$scope.choosehand[i] = 0;
			}
			$scope.showPhaseButton = showPhaseButtonWhenChooseHand();
		}
		
		$scope.play = function(index){
			if ($scope.phase == "Treasure"){
				if ($scope.hand[index].top.treasure){
					playCard($scope.hand[index].top);
				}
			}
			if ($scope.phase == "Action"){
				if ($scope.ask.type == 0){
					if ($scope.hand[index].top.actionType){
						playCard($scope.hand[index].top);
					}
				} else if ($scope.ask.type == 2){ // choosehand
					if ($scope.choosehand[index] == $scope.hand[index].numCards){
						$scope.choosehand[index] = 0;
					} else {
						var i;
						var total = 0;
						for (i=0;i<$scope.choosehand.length;i++){
							total = total + $scope.choosehand[i];
						}
						if (total<$scope.ask.upper){
							$scope.choosehand[index] = $scope.choosehand[index] + 1;
						}
						
					}
					$scope.showPhaseButton = showPhaseButtonWhenChooseHand();
				}
			}
		}
		
		getsupply = function(){
			$http.post('/dominiongame/getbase').then(function(response){
				$scope.base=response.data;
				$http.post('/dominiongame/getkindom').then(function(response){
					$scope.kindom=response.data;
					getstatus();
				});
			});
		}
		
		gainCard = function(cardName){
			var data = {"cardName": cardName}
			$http({url: "/dominiongame/gaincard", method: "POST", params: data}).then(function(response){
				getsupply();
			});
		}
		
		buyCard = function(cardName){
			var data = {"cardName": cardName}
			$http({url: "/dominiongame/buycard", method: "POST", params: data}).then(function(response){
				gainCard(cardName);
			});
		}
		
		showPhaseButtonWhenChooseHand = function(){
			var i;
			var total = 0;
			for (i=0;i<$scope.choosehand.length;i++){
				total = total + $scope.choosehand[i];
			}
			if (total >= $scope.ask.lower && total <= $scope.ask.upper){
				return true;
			}
			return false;
		}
		
		$scope.buyc = function(bk, index){
			if ($scope.phase == "Buy"){
				if ($scope.buy > 0){
					var pile;
					var cardName = "";
					var numCards = 0;
					var price = 0;
					if (bk == "kindom"){
						pile = $scope.kindom[index];
					} else if (bk = "base"){
						pile = $scope.base[index];
						
					}
					numCards = pile.numCards;
					if (numCards > 0){
						price = pile.cards[0].price;
						if (price <= $scope.coin){
							cardName = pile.cards[0].name;
							buyCard(cardName);
						}
					}
				}
			}
		}
		
}]);
