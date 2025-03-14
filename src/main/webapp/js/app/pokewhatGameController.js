var setUrl = function(d){
	hearder = "https://";
	server = "localhost:8080";
	return header + server + d;
}

var app = angular.module("pokewhatGameApp", ["ngWebSocket"]);
app.controller("pokewhatGameCtrl", ['$scope', '$window', '$http', '$document', '$timeout', '$websocket',
	function($scope, $window, $http, $document, $timeout, $websocket){
		var ws = $websocket("wss://" + $window.location.host + "/pokewhat/boardrefresh");
		var heartCheck = {
			timeout: 10000,//10s
			timeoutObj: null,
			reset: function(){
				clearTimeout(this.timeoutObj);
			　　 	this.start();
			},
			start: function(){
				this.timeoutObj = setTimeout(function(){
					var msg = $scope.username + " heart beat"
					ws.send(msg);
				}, this.timeout)
			}
		}
	
		ws.onError(function(event) {
			//alert("error!")
		});
	
		ws.onClose(function(event) {
			//alert("closed!")
		});
	
		ws.onOpen(function() {
			heartCheck.start();
		});
	
		$scope.moves = [];
		$scope.allCards = [];
		$scope.allCardsStyles = [];
		$scope.pmToChooseStyles = [];
		$scope.pmFromPoolStyles = [];
		$scope.avatarStyles = [];
		$scope.hpBarStyle = []
		$scope.pmStyles = [];
		$scope.otherIndexes = [];
		$scope.moveStyles = [];
		$scope.moveClasses = [];
		$scope.ancientStyles = [];
		$scope.logs = [];
		$scope.phase = "";
		$scope.lastMove = 0;
		$scope.otherTdStyle = {};
		$scope.showBigImage = false;
		$scope.bigImage = "";
		$scope.bigImageStyle = {};
		$scope.bigImageDivStyle = {};
		$scope.muteButton = "播放"
		$scope.playedBGM = false;
		//$scope.bgm = new Audio('/sound/Pokewhat/game_bgm.mp3');
		$scope.showAnimationImg = []
		$scope.playingAnimation = false;
		$scope.firstGetBoard = true;
		$scope.animationDivStyles = []
		
		
		$scope.volume = 0.5;
		$scope.updateVolume = function() {
			$scope.bgm.volume = $scope.volume;
		};
		$scope.bgm = new Audio();
		randomizeBGM = function(){
			v = Math.floor(Math.random() * 3);
			$scope.bgm.src ='/sound/Pokewhat/game_bgm.mp3'
			if (v == 0){
				$scope.bgm.src ='/sound/Pokewhat/game_bgm_old.mp3'
			}
			$scope.bgm.volume = $scope.volume;
		}
		randomizeBGM();
		$scope.bgm.addEventListener("ended", function() {
			randomizeBGM()
			$scope.bgm.play();
		}, true);

		$scope.goto = function(d){
			var x = "https://" + $window.location.host;
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
		
		$scope.choosePm = function(x){
			var data = {"x" : x}
			$http({url: "/pokewhatgame/choosepm", method: "POST", params: data}).then(function(response){
				//$scope.getBoard()
				$scope.allRefresh()
			});
		}
		
		$scope.choosePmFromPool = function(x){
			var data = {"x" : x}
			$http({url: "/pokewhatgame/choosepmfrompool", method: "POST", params: data}).then(function(response){
				//$scope.getBoard()
				$scope.allRefresh()
			});
		}
		
		$scope.botChoosePm = function(){
			$http({url: "/pokewhatgame/botchoosepm", method: "POST"}).then(function(response){
				//$scope.getBoard()
				$scope.allRefresh()
			});
		}
		
		$scope.useMove = function(x){
			var data = {"x" : x}
			$http({url: "/pokewhatgame/usemove", method: "POST", params: data}).then(function(response){
				//$scope.getBoard()
				$scope.allRefresh()
			});
		}
		
		$scope.endTurn = function(){
			if ($scope.allCards[$scope.curPlayer].length == 0){
				$scope.useMove(0);
			} else {
				$http({url: "/pokewhatgame/endturn", method: "POST"}).then(function(response){
					//$scope.getBoard()
					$scope.allRefresh()
				});
			}
		}
		
		$scope.confirmRoundEnd = function(){
			$http({url: "/pokewhatgame/confirmendround", method: "POST"}).then(function(response){
				//$scope.getBoard()
				$scope.allRefresh()
			});
		}
		
		$scope.botNextMove = function(){
			$http({url: "/pokewhatgame/botnextmove", method: "POST"}).then(function(response){
				//$scope.getBoard()
				$scope.allRefresh()
			});
		}
		
		setBigImageStyle = function(){
			$scope.bigImageStyle = {
				"height": "609px", 
				"width": "392px", 
				"position": "absolute",
				"left": "50%",
				"top": "50%",
				"margin-left": "-196px",
				"margin-top": "-280px",
				"background": "url(" + $scope.bigImage + ")", 
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
		
		$scope.showMoveBigImage = function(index){
			$scope.showBigImage = true
			$scope.bigImage = "/image/Pokewhat/Cards/" + index.toString() + ".png"
			setBigImageStyle()
		}
		
		$scope.unshowBigImage = function(){
			$scope.showBigImage = false
		}
		
		$scope.playBGM = function(){
			if ($scope.playedBGM == false){
				$scope.playedBGM = true;
		        $scope.bgm.play();
		        $scope.muteButton = "静音"
			}
		}
		
		$scope.mute = function(){
			if ($scope.playedBGM == true){
				if ($scope.bgm.paused){
					$scope.bgm.play();
					$scope.muteButton = "静音"
				} else {
					$scope.bgm.pause();
					$scope.muteButton = "播放"
				}
			}
		}
		
		setOtherTdStyle = function(){
			var n = $scope.playerNames.length;
			if (n == 5){
				$scope.otherTdStyle = {
					"border-right" : "1px solid white",
					"padding-left": "32px",
					"padding-right": "12px"
				}
			} else if (n == 4){
				$scope.otherTdStyle = {
					"border-right" : "1px solid white",
					"padding-left": "93px",
					"padding-right": "74px"
				}
			} else if (n == 3){
				$scope.otherTdStyle = {
					"border-right" : "1px solid white",
					"padding-left": "217px",
					"padding-right": "197px"
				}
			} else if (n == 2){
				$scope.otherTdStyle = {
					"border-right" : "1px solid white",
					"padding-left": "587px",
					"padding-right": "567px"
				}
			}
			
		}
		
		setAvatarPmStyles = function(){
			$scope.avatarStyles = [];
			$scope.pmStyles = [];
			for (i=0;i<$scope.pms.length;i++){
				if ($scope.pms[i] == null){
					singleStyle = {}
				} else {
					var imgUrl = "url('/image/Pokewhat/PM/" + $scope.pms[i] + ".png')"
					singleStyle = {
						"background": imgUrl,
						"background-size": "cover",
						"height": $scope.pmSizes[i] + "px",
						"width": $scope.pmSizes[i] + "px"
					}
				}
				$scope.pmStyles.push(singleStyle);
			}
			for (i=0;i<$scope.avatars.length;i++){
				var imgUrl = "url('/image/Pokewhat/Avatar/" + $scope.playerAvatars[i] + ".png')"
				singleStyle = {
					"background": imgUrl,
					"background-size": "cover"
				}
				$scope.avatarStyles.push(singleStyle);
			}
		}
		
		setPmToChooseStyles = function(){
			$scope.pmToChooseStyles = [];
			$scope.pmFromPoolStyles = [];
			for (i=0;i<$scope.pmToChoose.length;i++){
				var imgUrl = "url('/image/Pokewhat/PM/" + $scope.pmToChoose[i] + ".png')"
				singleStyle = {
					"background": imgUrl,
					"background-size": "cover"
				}
				$scope.pmToChooseStyles.push(singleStyle);
			}
			for (i=0;i<$scope.pmFromPool.length;i++){
				var imgUrl = "url('/image/Pokewhat/PM/" + $scope.pmFromPool[i] + ".png')"
				singleStyle = {
					"background": imgUrl,
					"background-size": "cover"
				}
				$scope.pmFromPoolStyles.push(singleStyle);
			}
		}
		
		sortAllCards = function(){
			for (k=0;k<$scope.allCards.length;k++){
				for (i=0;i<$scope.allCards[k].length;i++){
					for (j=i+1;j<$scope.allCards[k].length;j++){
						if ($scope.allCards[k][i]>$scope.allCards[k][j]){
							temp = $scope.allCards[k][i]
							$scope.allCards[k][i] = $scope.allCards[k][j]
							$scope.allCards[k][j] = temp
						}
					}
				}
			}
		}
		
		setAllCardStyles = function(){
			$scope.allCardsStyles = [];
			for (i=0;i<$scope.allCards.length;i++){
				var cardsStyles = [];
				for (j=0;j<$scope.allCards[i].length;j++){
					var imgUrl = "url('/image/Pokewhat/Cards/" + $scope.allCards[i][j] + ".png')"
					singleStyle = {
						"background": imgUrl,
						"background-size": "cover"
					}
					cardsStyles.push(singleStyle)
				}
				$scope.allCardsStyles.push(cardsStyles)
			}
		}
		
		setHpBarStyle = function(){
			$scope.hpBarStyle = [];
			for (i=0;i<$scope.hp.length;i++){
				$scope.hp[i] = parseInt($scope.hp[i]);
				var barStyle = [];
				for (j=0;j<$scope.hp[i];j++){
					var singleStyle
					if ($scope.hp[i] == 1){
						singleStyle = {
							"background-color": "red",
							"border-right": "1px solid black"
						}
					} else if ($scope.hp[i] < 4){
						singleStyle = {
							"background-color": "orange",
							"border-right": "1px solid black"
						}
					} else {
						singleStyle = {
							"background-color": "green",
							"border-right": "1px solid black"
						}
					}
					barStyle.push(singleStyle)
				}
				
				
				$scope.hpBarStyle.push(barStyle)
			}
		}
		
		setAncientStyles = function(){
			$scope.ancientStyles = [];
			for (i=0;i<$scope.ancient.length;i++){
				var imgUrl = "url('/image/Pokewhat/Cards/" + $scope.ancient[i] + ".png')"
				singleStyle = {
					"background": imgUrl,
					"background-size": "cover"
				}
				$scope.ancientStyles.push(singleStyle)
			}
		}
		
		setMoveStyles = function(){
			$scope.moveStyles = [];
			$scope.moveClasses = [];
			for (var i=1;i<=8;i++){
				$scope.moves.push(i);
				var imgUrl = "url('/image/Pokewhat/Cards/" + i.toString() + ".png')"
				if (i<$scope.lastMove || $scope.phase != 1 || $scope.playingAnimation){
					singleStyle = {
						"background": imgUrl,
						"background-size": "cover",
					}
					$scope.moveClasses.push("disabled-move");
				} else {
					singleStyle = {
						"background": imgUrl,
						"background-size": "cover",
					}
					$scope.moveClasses.push("enabled-move");
				}
				$scope.moveStyles.push(singleStyle)
			}
			var imgUrl = "url('/image/Pokewhat/Cards/0.png')"
			$scope.moveStyleZero = {
				"background": imgUrl,
				"background-size": "cover",
			}
		}
		
		setOtherIndexes = function(){
			$scope.otherIndexes = [];
			var i = $scope.myIndex;
			do {
				if (i != $scope.myIndex){
					$scope.otherIndexes.push(i);
				}
				i = i+1;
				if (i == $scope.playerNames.length){
					i=0;
				}
			} while (i != $scope.myIndex)
		}
		
		adjustLogs = function(){
			var logcontent = document.getElementById("logs");
			logcontent.scrollTop = logcontent.scrollHeight;
		}
		
		afterAnimationSetup = function(response){
			$scope.scores = response.data.scores;
			$scope.hp = response.data.hp;
			$scope.curPlayer = response.data.curPlayer;
			$scope.roundEndMsg = response.data.roundEndMsg;
			$scope.scoringMsg = response.data.scoringMsg;
			$scope.ancientSize = response.data.ancientSize;
			$scope.ancient = response.data.ancient;
			$scope.round = response.data.round;
			$scope.turn = response.data.turn;
			$scope.deckSize = response.data.deckSize;
			
			setMoveStyles();
			setHpBarStyle();
			setAncientStyles();
			$scope.firstGetBoard = false;
		}
		
		playFrame = function(){
			i = $scope.curFrame
			$scope.animationImg = $scope.frameImg[i] + ".png";
			var imgUrl = "url('/image/Pokewhat/Animation/" + $scope.frameImg[i] + ".png')"
			$scope.showAnimationImg = []
			$scope.animationDivStyles = []
			for (j=0;j<$scope.playerNames.length;j++){
				$scope.showAnimationImg.push(false);
				var singleStyle = {
					"background": imgUrl,
					"background-size": "cover",
					"height": $scope.pmSizes[j] + "px",
					"width": $scope.pmSizes[j] + "px"
				}
				$scope.animationDivStyles.push(singleStyle)
			}
			for (j=0;j<$scope.frameTargets[i].length;j++){
				index = parseInt($scope.frameTargets[i][j]);
				$scope.showAnimationImg[index] = true;
			}
			//alert($scope.showAnimationImg)
			$timeout(function(){
				$scope.curFrame = $scope.curFrame+1
				if ($scope.curFrame < $scope.frameImg.length){
					playFrame();
				} else {
					$scope.playingAnimation = false;
					afterAnimationSetup($scope.response);
				}
			}, parseInt($scope.frameTime[i]));
		}
		
		animationHandle = function(response){
			if ($scope.animationId != response.data.animationId && response.data.frameImg.length > 0){
				$scope.animationId = response.data.animationId
				$scope.frameImg = response.data.frameImg
				$scope.frameTargets = response.data.frameTargets
				$scope.frameTime = response.data.frameTime
				$scope.frameType = response.data.frameType
				
				$scope.playingAnimation = true;
				$scope.curFrame = 0;
				setMoveStyles();
				playFrame();
			} else {
				afterAnimationSetup(response);
			}
		}
			
		$scope.getBoard = function(){
			$http.get('/pokewhatgame/getboard').then(function(response){
				$scope.id = response.data.id;
				if ($scope.id == "NE"){
					$scope.goto('/pokewhat');
					return;
				}
				$scope.response = response;
				$scope.lord = response.data.lord;
				$scope.phase = response.data.phase;
				$scope.lastMove = parseInt(response.data.lastMove);
				$scope.playerNames = response.data.playerNames;
				$scope.allCards = response.data.allCards;
				$scope.playedCards = response.data.playedCards;
				$scope.status = response.data.status;
				$scope.pmToChoose = response.data.pmToChoose;
				$scope.pmToChooseNames = response.data.pmToChooseNames;
				$scope.pmFromPool = response.data.pmFromPool;
				$scope.pmFromPoolNames = response.data.pmFromPoolNames;
				$scope.avatars = response.data.avatars;
				$scope.pms = response.data.pm;
				$scope.pmNames = response.data.pmNames;
				$scope.pmSizes = response.data.pmSizes;
				$scope.playerAvatars = response.data.playerAvatars;
				$scope.myIndex = parseInt(response.data.myIndex);
				$scope.playerAncients = response.data.playerAncients
				$scope.gameEndScore = response.data.gameEndScore;
				$scope.confirmed = response.data.confirmed;
				
				var updateLogs = false;
				if ($scope.logs.length < response.data.logs.length){
					updateLogs = true;
				}
				$scope.logs = response.data.logs;
				
				if (response.data.hasBot == "0"){
					$scope.hasBot = false;
				} else {
					$scope.hasBot = true;
				}
				
				if ($scope.status == "3"){
					setPmToChooseStyles();
				}
				setOtherTdStyle();
				setAvatarPmStyles();
				sortAllCards();
				setAllCardStyles();
				setOtherIndexes();
				animationHandle(response);
				if (updateLogs){
					$http.post('/citadelsgame/empty').then(function(response){
						adjustLogs()
					});
				}
				if ($scope.status == "2"){
					alert("Game Ends");
					$scope.goto("pokewhatendgame");
				}
			});
		}
		
		$scope.getBoard();
		
		ws.onMessage(function(e){
			//alert(JSON.stringify(e.data));
			var message = e.data
			heartCheck.reset();
			if (message == 'refresh'){
				$scope.getBoard();
			}
			
		});
		
		$scope.allRefresh = function(){
			var msg = "refresh";
	        ws.send(msg);
		}
		
}]);