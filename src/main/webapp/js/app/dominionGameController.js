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
		$scope.disablePhaseButton = false;
		$scope.showConfirmButton = false;
		$scope.showAutoplayButton = false;
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
		$scope.showMat = false;
		$scope.guessedCardName = "";
		$scope.addon = "";

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
			"background": "url('/image/Dominion/cards/Card_back.png')",
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
			$scope.showMat = false;
		}
		
		$scope.trashButton = function(){
			$scope.showLogs = false;
			$scope.showTrash = true;
			$scope.showMat = false;
		}
		
		$scope.matButton = function(){
			$scope.showLogs = false;
			$scope.showTrash = false;
			$scope.showMat = true;
		}
		
		setCards = function(){
			var n = $scope.hand.length;
			$scope.choosehand = new Array(n);
			var i;
			for (i=0;i<n;i++){
				$scope.choosehand[i] = 0;
			}
			
			if ($scope.numDeck == 0 || $scope.status != 2){
				$scope.deckStyle = {
					"background": "url('/image/Dominion/cards/Card_back.png')",
					"background-size": "cover",
					"float": "left",
					"visibility": "hidden"
				}
			} else {
				$scope.deckStyle = {
					"background": "url('/image/Dominion/cards/Card_back.png')",
					"background-size": "cover",
					"float": "left"
				}
			}
			if ($scope.discardTop == null || $scope.status != 2){
				$scope.discardStyle = {
					"background": "url('/image/Dominion/cards/Card_back.png')",
					"background-size": "cover",
					"float": "left",
					"visibility": "hidden"
				}
			} else {
				$scope.discardStyle = {
					"background": "url(" + $scope.discardTop.image + ")",
					"background-size": "cover",
					"float": "left"
				}
			}
		}
		
		setAddon = function(){
			$scope.addon = " Action: " + $scope.action;
			$scope.addon = $scope.addon + " Buy: " + $scope.buy;
			$scope.addon = $scope.addon + " Coin: " + $scope.coin;
		}
		
		setButtons = function(){
			$scope.showConfirmButton = false;
			$scope.showClearButton = false;
			$scope.showAutoplayButton = false;
			$scope.showViewed = false;
			$scope.options = [];
			if ($scope.phase == "Start"){
				
			} else if ($scope.phase == "Action"){
				$scope.phaseButton = "End Action";
				var task = $scope.ask;
				while (task.type == 11){
					task = task.thronedAsk;
				}
				if (task.type == 0 || task.type == 6){
					$scope.disablePhaseButton = false;
					$scope.topMessage = "You may play Action cards";
					if ($scope.tokens[3] > 0){
						$scope.topMessage = $scope.topMessage + " and use Memorial tokens";
					}
					if (task.type == 6){
						$scope.options = task.options;
					}
				} else{
					$scope.topMessage = task.msg;
					$scope.disablePhaseButton = true;
					if (task.type == 1){
						$scope.options = task.options;
					} else if (task.type == 2){
						$scope.showClearButton = true;
						$scope.showConfirmButton = showConfirmButtonWhenChooseHand();
					} else if (task.type == 3 || task.type == 7){
						$scope.guessedCardName = "";
					} else if (task.type == 4){
						$scope.showViewed = true;
						$scope.viewed = task.viewedCardsImage;
						if (task.subType == 1){
							$scope.options = task.options;
						} else if (task.subType == 51){
							$scope.showClearButton = true;
							$scope.showConfirmButton = showConfirmButtonWhenChooseViewed();
							var n = $scope.viewed.length;
							$scope.chooseViewed = new Array(n);
							for (var i=0;i<n;i++){
								$scope.chooseViewed[i] = 0;
							}
						} else if (task.subType == 52){
							$scope.showConfirmButton = true;
							var n = $scope.viewed.length;
							$scope.chooseViewed = new Array(n);
							for (var i=0;i<n;i++){
								$scope.chooseViewed[i] = i;
							}
						}
					}
				}
			} else if ($scope.phase == "Treasure"){
				$scope.disablePhaseButton = false;
				$scope.topMessage = "You may play Treasure cards";
				$scope.showAutoplayButton = true;
				$scope.phaseButton = "Buy Cards";
			} else if ($scope.phase == "Buy"){
				$scope.disablePhaseButton = false;
				$scope.topMessage = "You may buy cards";
				$scope.phaseButton = "End Buy";
			} else if ($scope.phase == "Night"){
				$scope.topMessage = "You may play Night cards";
				$scope.phaseButton = "End Night";
			} else if ($scope.phase == "Offturn"){
				$scope.topMessage = "It's not your turn";
				$scope.phaseButton = "Don't click";
				$http.post('/dominiongame/nextplayer').then(function(response){
					getboard();
				});
			}
		}
		
		setStatus = function() {
			if ($scope.status == 1){
				$scope.topMessage = "Your starting cards";
				$scope.showConfirmButton = true;
				$scope.showPhaseButton = false;
				$http.post('/dominiongame/firstcards').then(function(response){
					$scope.hand=response.data;
				});
			} else if ($scope.status == 2){
				$scope.showPhaseButton = true;
				setButtons();
			} else if ($scope.status == 3){
				alert("game ends");
				$scope.goto('dominionend');
			}
		}
		
		adjustLogs = function(){
			var div = document.getElementById('logs');
			div.scrollTop = div.scrollHeight;
		}
		
		getplayer = function(){
			$http.post("/dominiongame/getplayer").then(function(response){
				adjustLogs();
				$scope.playCards = response.data.play;
				$scope.tokens = response.data.tokens;
				$scope.numDeck = response.data.deck;
				$scope.discardTop = response.data.discard;
				$scope.hand = response.data.hand;
				$scope.phase = response.data.phase;
				$scope.action = response.data.action;
				$scope.buy = response.data.buy;
				$scope.coin = response.data.coin;
				$scope.mats = response.data.mats;
				$scope.ask = response.data.ask;
				setStatus();
				setAddon();
				setCards();
			});
		}
		
		getboard = function(){
			$http.post("/dominiongame/getboard").then(function(response){
				$scope.base = response.data.base;
				$scope.kindom = response.data.kindom;
				$scope.trashCards = response.data.trash;
				$scope.logs = response.data.logs;
				$scope.status = response.data.status;
				setBaseStyle();
				setKindomStyle();
				getplayer();
			});
		}
		
		getboard();
		
		$scope.resign = function(){
			var r = confirm("Are you sure you want to resign?")
			if (r){
				$http.post('/dominiongame/resign').then(function(response){
					$scope.goto('dominionend');
				});
			}
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
		
		$scope.autoplay = function(){
			$http.post('/dominiongame/autoplaytreasure').then(function(response){
				getboard();
			});
		}
		
		$scope.cb = function(){
			
			if ($scope.status == 1){
				$http.post('/dominiongame/finishfirstcards').then(function(response){
					getboard();
				});
			} else if ($scope.status == 2){
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
						getboard();
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
						getboard();
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
						getboard();
					});
				}
			}
		}
		
		$scope.pb = function(){
			if ($scope.status == 2){
				if ($scope.phase == "Offturn"){
					
				} else {
					$http.post('/dominiongame/nextphase').then(function(response){
						getboard();
					});
				}
			}
		}
		
		$scope.choose = function(index){
			var data = {"ans": index.toString()};
			$http({url: "/dominiongame/response", method: "POST", params: data}).then(function(response){
				$scope.ask = response.data;
				getboard();
			});
		}
		
		playCard = function(card){
			var data = {"cardName": card.name};
			$http({url: "/dominiongame/playcard", method: "POST", params: data}).then(function(response){
				$scope.ask = response.data;
				getboard();
			});
		}
		
		$scope.clearChoosehand = function(){
			var i;
			for (i=0;i<$scope.choosehand.length;i++){
				$scope.choosehand[i] = 0;
			}
			$scope.showConfirmButton = showConfirmButtonWhenChooseHand();
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
					$scope.showConfirmButton = showConfirmButtonWhenChooseHand();
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

		gainCard = function(cardName){
			var data = {"cardName": cardName}
			$http({url: "/dominiongame/gaincard", method: "POST", params: data}).then(function(response){
				getboard();
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
		
		showConfirmButtonWhenChooseHand = function(){
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
		
		showConfirmButtonWhenChooseViewed = function(){
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
					if (task.restriction == 0 || task.viewedCardsChooseable[index] == "yes"){
						var total = 0;
						for (i=0;i<$scope.chooseViewed.length;i++){
							total = total + $scope.chooseViewed[i];
						}
						if (total == task.upper && $scope.chooseViewed[index] == 0){
							
						} else {
							$scope.chooseViewed[index] = 1 - $scope.chooseViewed[index];
							$scope.showConfirmButton = showConfirmButtonWhenChooseViewed();
						}
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
				getboard();
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
								getboard();
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
				getboard();
			});
		}
		
		$scope.disableUseMemorial = function(){
			if ($scope.phase=="Action" && $scope.ask.type==0){
				return false;
			}
			return true;
		}
		
}]);
