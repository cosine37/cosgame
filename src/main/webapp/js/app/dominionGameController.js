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
		$scope.baseStyle=[];
		$scope.kindomStyle=[];
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
		$scope.options = [];
		$scope.showClearButton = false;
		$scope.showViewed = false;
		$scope.viewed=[];
		$scope.forRearrange = -1;
		$scope.chooseViewed = [];
		$scope.logs = [];
		$scope.trashCards = [];
		$scope.tokens = [];
		$scope.showLogs = true;
		$scope.showTrash = false;
		$scope.guessedCardName = "";

		$scope.baseStyle={
			"position": "relative",
			"height": "109px",
			"width": "71px"
		};
		
		$scope.kindomStyle={
			"position": "relative",
			"height": "210px",
			"width": "140px"
		};
		
		$scope.playStyle={
			"height": "109px",
			"width": "71px"
		}
		
		$scope.viewedStyle = {
			"position": "absolute",
			"left": "30%",
			"top": "10%",
			"padding": "10px",
			"background-color": "grey",
			"border": "2px"
		}
		
		$scope.deckStyle = {
			"background": "url('/image/Dominion/cards/Card_back.jpg')",
			"background-size": "cover",
			"float": "left"
		}
		
		$scope.goto = function(d){
			var x = "http://" + $window.location.host;
			$window.location.href = x + "/" + d;
		}
		
		$scope.logButton = function(){
			$scope.showLogs = true;
			$scope.showTrash = false;
		}
		
		$scope.trashButton = function(){
			$scope.showLogs = false;
			$scope.showTrash = true;
		}
		
		gettokens = function(){
			$http.post('/dominiongame/gettokens').then(function(response){
				$scope.tokens=response.data.value;
			});
		}
		
		gettrash = function(){
			$http.post('/dominiongame/gettrash').then(function(response){
				$scope.trashCards=response.data;
				gettokens();
			});
		}
		
		adjustLogs = function(){
			var div = document.getElementById('logs');
			div.scrollTop = div.scrollHeight;
			gettrash();
		}
		
		getplay = function(){
			$http.post('/dominiongame/getplay').then(function(response){
				$scope.playCards=response.data;
				adjustLogs();
			});
		}
		
		getlogs = function(){
			$http.post('/dominiongame/getlogs').then(function(response){
				$scope.logs = response.data.value;
				getplay();
			});
		}
		
		
		
		getdeck = function(){
			$http.post('/dominiongame/getdeck').then(function(response){
				$scope.numDeck = response.data.value[0];
				if ($scope.numDeck == 0){
					$scope.deckStyle = {
						"background": "url('/image/Dominion/cards/Card_back.jpg')",
						"background-size": "cover",
						"float": "left",
						"visibility": "hidden"
					}
				} else {
					$scope.deckStyle = {
						"background": "url('/image/Dominion/cards/Card_back.jpg')",
						"background-size": "cover",
						"float": "left"
					}
				}
				getlogs();
			});
		}
		
		getdiscard = function(){
			$http.post('/dominiongame/getdiscard').then(function(response){
				$scope.discardTop = response.data;
				getdeck();
			});
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
				getdiscard()
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
				$scope.showViewed = false;
				$scope.options = [];
				if ($scope.phase == "Start"){
					
				} else if ($scope.phase == "Action"){
					$http.post('/dominiongame/getask').then(function(response){
						$scope.ask = response.data;
						var task = $scope.ask;
						while (task.type == 11){
							task = task.thronedAsk;
						}
						if (task.type == 0){
							$scope.topMessage = "You may play Action cards";
							$scope.phaseButton = "End Action";
							getaddon();
						} else if (task.type == 1){
							$scope.topMessage = task.msg;
							$scope.showPhaseButton = false;
							$scope.options = task.options;
							getaddon();
						} else if (task.type == 2){
							$scope.topMessage = task.msg;
							$scope.showPhaseButton = showPhaseButtonWhenChooseHand();
							$scope.showClearButton = true;
							$scope.phaseButton = "Confirm";
							getaddon();
						} else if (task.type == 3 || task.type == 7){
							$scope.guessedCardName = "";
							$scope.topMessage = task.msg;
							$scope.showPhaseButton = false;
							getaddon();
						} else if (task.type == 4){
							$scope.showViewed = true;
							$scope.viewed = task.viewedCardsImage;
							if (task.subType == 1){
								$scope.topMessage = task.msg;
								$scope.showPhaseButton = false;
								$scope.options = task.options;
							} else if (task.subType == 51){
								$scope.topMessage = task.msg;
								$scope.showPhaseButton = showPhaseButtonWhenChooseViewed();
								$scope.showClearButton = true;
								$scope.phaseButton = "Confirm";
								var n = $scope.viewed.length;
								$scope.chooseViewed = new Array(n);
								for (i=0;i<n;i++){
									$scope.chooseViewed[i]=0;
								}
							} else if (task.subType == 52){
								$scope.topMessage = task.msg;
								$scope.showPhaseButton = true;
								$scope.phaseButton = "Confirm";
								var n = $scope.viewed.length;
								$scope.chooseViewed = new Array(n);
								for (i=0;i<n;i++){
									$scope.chooseViewed[i] = i;
								}
							}
							getaddon();
						} else if (task.type == 6){
							$scope.topMessage = "You may play Action cards";
							$scope.showPhaseButton = true;
							$scope.options = task.options;
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
					$http.post('/dominiongame/getask').then(function(response){
						$scope.ask = response.data;
						var task = $scope.ask;
						while (task.type == 11){
							task = task.thronedAsk;
						}
						$scope.topMessage = "You may buy cards";
						$scope.phaseButton = "End Buy";
						getaddon();
					});
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
				} else if ($scope.phase == "Action"){
					var task = $scope.ask;
					while (task.type == 11){
						task = task.thronedAsk;
					}
					if (task.type == 2){
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
					} else if (task.type == 4 && task.subType == 51){
						var s = "";
						var i,j;
						for (i=0;i<$scope.chooseViewed.length; i++){
							if ($scope.chooseViewed[i] == 1){
								if (s=="") {
									s=i.toString();
								} else {
									s = s+","+i.toString();
								}
							}
						}
						var data = {"ans": s};
						$http({url: "/dominiongame/response", method: "POST", params: data}).then(function(response){
							$scope.ask = response.data;
							getsupply();
						});
					} else if (task.type == 4 && task.subType == 52){
						var s = "";
						var i,j;
						for (i=0;i<$scope.chooseViewed.length;i++){
							if (s == ""){
								s = $scope.chooseViewed[i].toString();
							} else {
								s = s + "," + $scope.chooseViewed[i].toString();
							}
						}
						var data = {"ans": s};
						$http({url: "/dominiongame/response", method: "POST", params: data}).then(function(response){
							$scope.ask = response.data;
							getsupply();
						});
					} else{
						$http.post('/dominiongame/nextphase').then(function(response){
							getstatus();
						});
					}
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
				var task = $scope.ask;
				while (task.type == 11){
					task = task.thronedAsk;
				}
				
				if (task.type == 0 || task.type == 6){
					if ($scope.hand[index].top.actionType){
						playCard($scope.hand[index].top);
					}
				} else if (task.type == 2){ // choosehand
					if (task.restriction == 0 || (task.restriction == 1001 && $scope.hand[index].top.actionType) || (task.restriction == 1002 && $scope.hand[index].top.treasure)
							|| (task.restriction == 1015 && $scope.hand[index].top.actionType) || (task.restriction == 1015 && $scope.hand[index].top.general)){
						if ($scope.choosehand[index] == $scope.hand[index].numCards){
							$scope.choosehand[index] = 0;
						} else {
							var i;
							var total = 0;
							for (i=0;i<$scope.choosehand.length;i++){
								total = total + $scope.choosehand[i];
							}
							if (total<task.upper){
								$scope.choosehand[index] = $scope.choosehand[index] + 1;
							}
							
						}
					}
					$scope.showPhaseButton = showPhaseButtonWhenChooseHand();
				} 
			}
		}
		
		setBaseStyle = function(){
			var i;
			var n = $scope.base.length;
			$scope.baseStyle = new Array(n);
			for (i=0;i<n;i++){
				var tJsonObj = {
					"position": "relative",
					"float": "left",
					"height": "105px",
					"width": "70px"	
				}
				tJsonObj["background-image"] = "url("+$scope.base[i].cards[0].image+")";
				tJsonObj["background-size"] = "cover";
				if (i%1 == 0){
					tJsonObj["margin-left"] = "5px";
				}
				if (i>1){
					tJsonObj["margin-top"] = "5px";
				}
				$scope.baseStyle[i] = tJsonObj;
			}
		}
		
		setKindomStyle = function(){
			var i;
			var n = $scope.kindom.length;
			$scope.kindomStyle = new Array(n);
			for (i=0;i<n;i++){
				var tJsonObj = {
					"position": "relative",
					"float": "left",
					"height": "157px",
					"width": "105px"	
				}
				tJsonObj["background-image"] = "url("+$scope.kindom[i].cards[0].image+")";
				tJsonObj["background-size"] = "cover";
				if (i!=0 && i!=5){
					tJsonObj["margin-left"] = "10px";
				}
				if (i>4){
					tJsonObj["margin-top"] = "5px";
				}
				$scope.kindomStyle[i] = tJsonObj;
			}
		}
		
		getsupply = function(){
			$http.post('/dominiongame/getbase').then(function(response){
				$scope.base=response.data;
				setBaseStyle();
				
				$http.post('/dominiongame/getkindom').then(function(response){
					$scope.kindom=response.data;
					setKindomStyle();
					getstatus();
				});
			});
		}
		
		getsupply();
		
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
		
		$scope.showRevealedTop = function(index){
			var task = $scope.ask;
			while (task.type == 11){
				task = task.thronedAsk;
			}
			if (task.type == 4 && task.subType == 52){
				if (index == $scope.viewed.length-1){
					return true;
				} else {
					return false;
				}
			} else {
				return false;
			}
		}
		
		$scope.showRevealedTarget = function(index){
			var task = $scope.ask;
			while (task.type == 11){
				task = task.thronedAsk;
			}
			if (task.type == 4 && task.subType == 51){
				if ($scope.chooseViewed[index] > 0){
					return true;
				} else {
					return false;
				}
			} else if (task.type == 4 && task.subType == 52){
				if (index == $scope.forRearrange){
					return true;
				} else {
					return false;
				}
			} else {
				return false;
			}
		}
		
		showPhaseButtonWhenChooseHand = function(){
			var task = $scope.ask;
			while (task.type == 11){
				task = task.thronedAsk;
			}
			var i;
			var total = 0;
			for (i=0;i<$scope.choosehand.length;i++){
				total = total + $scope.choosehand[i];
			}
			if (total >= task.lower && total <= task.upper){
				return true;
			}
			return false;
		}
		
		showPhaseButtonWhenChooseViewed = function(){
			var task = $scope.ask;
			while (task.type == 11){
				task = task.thronedAsk;
			}
			var i;
			var total = 0;
			if (task.type == 4){
				if (task.subType == 51){
					for (i=0;i<$scope.chooseViewed.length;i++){
						total = total + $scope.chooseViewed[i];
					}
					if (total >= task.lower && total <= task.upper){
						return true;
					}
				} else if (task.subType == 52){
					return true;
				}
			} 
			
			return false;
		}
		
		$scope.clickViewed = function(index){
			var task = $scope.ask;
			while (task.type == 11){
				task = task.thronedAsk;
			}
			
			if (task.type == 4){
				if (task.subType == 51){
					var total = 0;
					for (i=0;i<$scope.chooseViewed.length;i++){
						total = total + $scope.chooseViewed[i];
					}
					if (total == task.upper && $scope.chooseViewed[index] == 0){
						
					} else {
						$scope.chooseViewed[index] = 1 - $scope.chooseViewed[index];
						$scope.showPhaseButton = showPhaseButtonWhenChooseViewed();
					}
				} else if (task.subType == 52){
					if ($scope.forRearrange == -1){
						$scope.forRearrange = index;
					} else if ($scope.forRearrange == index){
						$scope.forRearrange = -1;
					} else {
						var temp = $scope.chooseViewed[$scope.forRearrange];
						$scope.chooseViewed[$scope.forRearrange] = $scope.chooseViewed[index];
						$scope.chooseViewed[index] = temp;
						temp = $scope.viewed[$scope.forRearrange];
						$scope.viewed[$scope.forRearrange] = $scope.viewed[index];
						$scope.viewed[index] = temp;
						$scope.forRearrange = -1;
					}
				}
			}
		}
		
		$scope.showGuess = function(){
			if ($scope.ask == null) return false;
			var task = $scope.ask;
			while (task.type == 11){
				task = task.thronedAsk;
			}
			if (task.type == 7) return true;
			return false;
		}
		
		$scope.guess = function(){
			var data = {"ans": $scope.guessedCardName};
			$scope.guessedCardName = "";
			$http({url: "/dominiongame/response", method: "POST", params: data}).then(function(response){
				$scope.ask = response.data;
				getsupply();
			});
		}
		
		$scope.showPlus = function(bk, index){
			if ($scope.ask == null) return false;
			var task = $scope.ask;
			while (task.type == 11){
				task = task.thronedAsk;
			}
			if (task.type == 3 || task.type == 7){
				if (bk == "kindom"){
					pile = $scope.kindom[index];
				} else if (bk = "base"){
					pile = $scope.base[index];
				}
				numCards = pile.numCards;
				if (numCards > 0){
					price = pile.cards[0].price;
					if (task.restriction == 0 || (task.restriction == 1001 && pile.cards[0].actionType) || (task.restriction == 1002 && pile.cards[0].treasure)){
						if (task.type == 7 || (price >= task.lower && price <= task.upper)){
							return true;
						}
					}
				}
			} else if ($scope.phase == "Buy"){
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
							return true;
						}
					}
				}
			}
			return false;
		}
		
		$scope.buyc = function(bk, index){
			var task = $scope.ask;
			while (task.type == 11){
				task = task.thronedAsk;
			}
			if (task.type == 3 || task.type == 7){
				if (bk == "kindom"){
					pile = $scope.kindom[index];
				} else if (bk = "base"){
					pile = $scope.base[index];
				}
				numCards = pile.numCards;
				if (numCards > 0){
					price = pile.cards[0].price;
					if (task.restriction == 0 || (task.restriction == 1001 && pile.cards[0].actionType) || (task.restriction == 1002 && pile.cards[0].treasure)){
						if (task.type == 7 || (price >= task.lower && price <= task.upper)){
							var data = {"ans": pile.cards[0].name};
							$http({url: "/dominiongame/response", method: "POST", params: data}).then(function(response){
								$scope.ask = response.data;
								getsupply();
							});
						}
					}
				}
			} else if ($scope.phase == "Buy"){
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
		
		$scope.useMemorial = function(){
			$http.post('/dominiongame/usememorial').then(function(response){
				getsupply();
			});
		}
		
		$scope.disableUseMemorial = function(){
			if ($scope.phase=="Action" && $scope.ask.type==0){
				return false;
			}
			return true;
		}
		
}]);
