var setUrl = function(d){
	hearder = "https://";
	server = "localhost:8080";
	return header + server + d;
}

var app = angular.module("richGameApp", ["ngWebSocket", "ngSanitize"]);
app.controller("richGameCtrl", ['$scope', '$window', '$http', '$document', '$timeout', '$websocket','$sce',
	function($scope, $window, $http, $document, $timeout, $websocket,$sce){
		var ws = $websocket("wss://" + $window.location.host + "/rich/boardrefresh");
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
		});
		
		ws.onClose(function(event) {
		});
		
		ws.onOpen(function() {
			heartCheck.start();
		});
		
		adjustLogs = function(logElementId){
			var logcontent = document.getElementById(logElementId);
			logcontent.scrollTop = logcontent.scrollHeight;
		}
	
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
		
		$scope.dismiss = function(){
			$http.post('/rich/dismiss').then(function(response){
				ws.send("dismiss");
			});
		}
		
		$scope.bgms = []
		$scope.bgm = new Audio();
		$scope.volume = 0.5;
		randomizeBGM = function(){
			n = $scope.bgms.length
			newBgm = $scope.bgm.src
			while (newBgm == $scope.bgm.src){
				v = Math.floor(Math.random() * n);
				newBgm = $scope.bgms[v]
			}
			
			bgmSrc = '/sound/Rich/' + newBgm + '.mp3'
			$scope.bgm.src = bgmSrc
			$scope.bgm.volume = $scope.volume;
		}
		$scope.updateVolume = function() {
			$scope.bgm.volume = $scope.volume;
		};
		$scope.bgm.addEventListener("ended", function() {
			randomizeBGM()
			$scope.bgm.play();
		}, true);
		//randomizeBGM();
		
		$scope.ses == []
		$scope.se = new Audio();
		$scope.seVolume = 0.5;
		$scope.updateSeVolume = function() {
			$scope.se.volume = $scope.seVolume;
		};
		playSes = function(){
			if ($scope.ses == null) return;
			for (i=0;i<$scope.ses.length;i++){
				if ($scope.ses[i] != $scope.se.src){
					$scope.se.src = $scope.ses[i];
					$scope.se.play();
					
					$scope.lastPlayed = $scope.ses[i]
				}
				
			}
		}
		
		
		
		genArray = function(s,n){
			ans = []
			for (i=s;i<n;i++) ans.push(i);
			return ans;
		}
		
		$scope.OFFTURN = -1;
		$scope.ROLL = 100;
		$scope.MOVE = 200;
		$scope.RESOLVE = 300;
		$scope.ESCAPE = 99999;
		$scope.UTILITYPHASE = 99998;
		$scope.STATIONPHASE = 99997;
		
		$scope.UTILITYAREA = 101;
		$scope.STATIONAREA = 102;
		
		$scope.INGAME = 1;
		$scope.ENDGAME = 2;
		
		$scope.EMPTY = 0;
		$scope.ESTATE = 1;
		$scope.STARTPOINT = 3;
		$scope.TAX = 4;
		$scope.FATE = 5;
		$scope.JAIL = 6;
		$scope.GOTOJAIL = 7;
		$scope.HOSPITAL = 11;
		$scope.CARDGAINER = 12;
		
		// For estate details
		$scope.chosenEstateIndex = -1;
		$scope.chosenEstate = null;
		$scope.clickEstate = function(x){
			if (x == $scope.chosenEstateIndex){
				$scope.chosenEstateIndex = -1
			} else {
				$scope.chosenEstateIndex = x;
				if (x>=$scope.map.places.length || x<0) $scope.chosenEstateIndex = -1
			}
			
			if ($scope.chosenEstateIndex == -1){
				$scope.chosenEstate = null;
			} else {
				$scope.chosenEstate = $scope.map.places[x];
			}
		}
		
		// For bank & stock
		$scope.shownEco = -1;
		$scope.clickEco = function(x){
			$scope.shownEco = x
		}
		$scope.closeEco = function(x){
			$scope.shownEco = -1;
		}
		$scope.bankform = {
			wdAmount: 0
		}
		$scope.shownWD=0
		$scope.bankDeposit = function(){
			var x = parseInt($scope.bankform.wdAmount)
			if (x == 0){
				alert("警告！请不要干存入$0这样无意义的事情，Cosgame的作者可是生气了，哼！")
				$scope.bankform.wdAmount = 0;
				return
			} else if (x < 0){
				alert("警告！请不要干存入负数金额这样无意义的事情，Cosgame的作者可是生气了，哼！")
				$scope.bankform.wdAmount = 0;
				return
			} else if (x > $scope.gamedata.myMoney){
				alert("警告！你没有那么多钱可以存，Cosgame的作者可是要嘲笑你的，哈哈哈哈！")
				$scope.bankform.wdAmount = 0;
				return
			}
			var data = {"amount" : $scope.bankform.wdAmount}
			$http({url: "/rich/bankwd", method: "POST", params: data}).then(function(response){
				ws.send("refresh");
			});
			$scope.shownWD=0
			$scope.bankform.wdAmount = 0;
		}
		
		$scope.bankWithdraw = function(){
			var x = parseInt($scope.bankform.wdAmount)
			if (x == 0){
				alert("警告！请不要干取出$0这样无意义的事情，Cosgame的作者可是生气了，哼！")
				$scope.bankform.wdAmount = 0;
				return
			} else if (x < 0){
				alert("警告！请不要干取出负数金额这样无意义的事情，Cosgame的作者可是生气了，哼！")
				$scope.bankform.wdAmount = 0;
				return
			} else if (x > $scope.gamedata.mySaving){
				alert("警告！你没有那么多钱可以取，Cosgame的作者可是要嘲笑你的，哈哈哈哈！")
				$scope.bankform.wdAmount = 0;
				return
			}
			x = 0-x;
			var data = {"amount" : x}
			$http({url: "/rich/bankwd", method: "POST", params: data}).then(function(response){
				ws.send("refresh");
			});
			$scope.shownWD=0
			$scope.bankform.wdAmount = 0;
		}
		
		$scope.buttonPress = function(option){
			var data = {"option" : option}
			$http({url: "/rich/buttonpress", method: "POST", params: data}).then(function(response){
				ws.send("refresh");
			});
		}
		// end bank & stock
		
		
		$scope.PLAYSTYLE_CHOOSEPLAYER = 1;
		$scope.PLAYSTYLE_CHOOSEGRID = 2;
		$scope.PLAYSTYLE_CHOOSEOPTIONS = 3;
		
		$scope.curPlayStyle = -1;
		$scope.chosenCard = -1;
		$scope.chosenPlayer = -1;
		$scope.chosenGrid = -1;
		$scope.clickChooseGrid = function(x){
			if (x == $scope.chosenGrid){
				$scope.chosenGrid = -1
			} else {
				$scope.chosenGrid = x
			}
		}
		$scope.clickChoosePlayer = function(x){
			if (x == $scope.chosenPlayer){
				$scope.chosenPlayer = -1
			} else {
				$scope.chosenPlayer = x
			}
		}
		$scope.clickHand = function(x){
			if ($scope.phase != $scope.OFFTURN){
				$scope.chosenPlayer = -1;
				$scope.chosenGrid = -1;
				$scope.curPlayStyle = -1;
				
				//alert(chosenPlayer)
				
				if (x == $scope.chosenCard){
					$scope.chosenCard = -1;
				} else {
					$scope.chosenCard = x;
					
					if ($scope.hand[$scope.chosenCard].id == 9000){
						var tempSe = new Audio();
						tempSe.volume = $scope.seVolume;
						tempSe.src = '/sound/Rich/aoligei.mp3'
						tempSe.play();
					}
					if ($scope.hand[$scope.chosenCard].playable == true){
						$scope.curPlayStyle = $scope.hand[$scope.chosenCard].playStyle
					}
					
				}
				setHandStyles()
			}
			
		}
		$scope.showPlayButton = function(){
			if ($scope.chosenCard>=0 && $scope.chosenCard<$scope.hand.length){
				if ($scope.hand[$scope.chosenCard].playable == false){
					return false;
				} else if ($scope.curPlayStyle == $scope.PLAYSTYLE_CHOOSEPLAYER && $scope.chosenPlayer == -1){
					return false;
				} else if ($scope.curPlayStyle == $scope.PLAYSTYLE_CHOOSEGRID && $scope.chosenGrid == -1){
					return false;
				}
				return true;
			} else {
				return false;
			}
		}
		$scope.playCardOption = function(x){
			if ($scope.chosenCard>=0){
				var option = 10000;
				option = option+$scope.chosenCard * 100;
				option = option+x;
				if ($scope.curPlayStyle == $scope.PLAYSTYLE_CHOOSEPLAYER){
					option = option + parseInt($scope.chosenPlayer)
				} else if ($scope.curPlayStyle == $scope.PLAYSTYLE_CHOOSEGRID){
					option = option + parseInt($scope.chosenGrid)
				}
				var data = {"option" : option}
				$http({url: "/rich/buttonpress", method: "POST", params: data}).then(function(response){
					$scope.chosenCard = -1;
					$scope.curPlayStyle = -1;
					$scope.chosenPlayer = -1;
					$scope.chosenGrid = -1;
					ws.send("refresh");
				});
			}
		}
		$scope.playCard = function(){
			$scope.playCardOption(0);
		}
		
		$scope.throwCard = function(){
			if ($scope.chosenCard>=0){
				var f = confirm("你确定要丢弃这张牌吗？");
				if (f){
					var option = 10000;
					option = option+$scope.chosenCard * 100 + 99;
					var data = {"option" : option}
					$http({url: "/rich/buttonpress", method: "POST", params: data}).then(function(response){
						$scope.chosenCard = -1;
						$scope.curPlayStyle = -1;
						$scope.chosenPlayer = -1;
						$scope.chosenGrid = -1;
						ws.send("refresh");
					});
				}
			}
		}
		
		setMapLayout = function(){
			var i,j;
			$scope.bottomRow = []
			$scope.topRow = []
			for (i=0;i<$scope.map.width;i++){
				$scope.bottomRow.push($scope.map.places[$scope.map.width-i-1])
				$scope.topRow.push($scope.map.places[$scope.map.width+$scope.map.height-2+i])
			}
			$scope.secondRow = []
			$scope.secondRow.push([$scope.map.places[$scope.map.width+$scope.map.height-3]])
			$scope.secondRow.push([$scope.map.places[$scope.map.width*2+$scope.map.height-2]])
			
			$scope.restRows = []
			for (i=0;i<$scope.map.height-3;i++){
				var restRowCols = []
				restRowCols.push([$scope.map.places[$scope.map.width+$scope.map.height-4-i]])
				restRowCols.push([$scope.map.places[$scope.map.width*2+$scope.map.height-1+i]])
				$scope.restRows.push(restRowCols);
			}
			
			//alert(Json.stringify($scope.restRows[0]))
		}
		
		setPlayerStyles = function(){
			for (i=0;i<$scope.players.length;i++){
				if (i==$scope.curPlayer){
					$scope.players[i].curStyle={"background-color": "LightGray"}
				} else {
					$scope.players[i].curStyle={}
				}
				
				$scope.players[i].hpDisplay = [];
				$scope.players[i].starDisplay = [];
				
				for (j=0;j<5;j++){
					var x = {}
					x["background-size"] = "cover";
					
					if (j<$scope.players[i].hp){
						x["background-image"] = "url(/image/Rich/hp1.png)"
					} else {
						x["background-image"] = "url(/image/Rich/hp0.png)"
					}
					$scope.players[i].hpDisplay.push(x);
				}
				
				for (j=0;j<6;j++){
					var x = {}
					x["background-size"] = "cover";
					
					if (j<$scope.players[i].star){
						x["background-image"] = "url(/image/Rich/star1.png)"
					} else {
						x["background-image"] = "url(/image/Rich/star0.png)"
					}
					$scope.players[i].starDisplay.push(x);
				}
			}
		}
		
		$scope.handStyle = [];
		setHandStyles = function(){
			$scope.handStyle = [];
			for (i=0;i<$scope.hand.length;i++){
				$scope.hand[i].descDisplay = $sce.trustAsHtml($scope.hand[i].desc);
				//alert($scope.hand[i].descDisplay)
				var cstyle = {}
				if (i == $scope.chosenCard){
					cstyle["margin-top"] = "-25px"
				}
				
				if ($scope.hand[i].types[0] == true){
					cstyle["background-color"] = "LemonChiffon"
				}
				if ($scope.hand[i].types[1] == true){
					cstyle["background-color"] = "darkslategrey"
				}
				if ($scope.hand[i].types[2] == true){
					cstyle["background-color"] = "Brown"
				}
				
				$scope.handStyle.push(cstyle);
			}
		}
		
		$scope.getBoard = function(){
			$http.get('/rich/getboard').then(function(response){
				$scope.gamedata = response.data
				$scope.id = response.data.id
				
				//$scope.playerNames = response.data.playerNames;
				if ($scope.id == "NE"){
					$scope.goto('rich');
					return;
				}

				$scope.status = response.data.status;
				$scope.phase = response.data.phase;
				$scope.lord = response.data.lord;
				$scope.players = response.data.players;
				$scope.logs = response.data.logs;
				$scope.curPlayer = response.data.curPlayer;
				$scope.hand = response.data.myHand;
				
				if ($scope.phase != $scope.OFFTURN){
					$scope.shownEco = -1;
				}
				
				$scope.map = response.data.map;
				if ($scope.bgms.length == 0){
					$scope.bgms = $scope.map.bgms;
					randomizeBGM();
				}
				$scope.ses = response.data.ses;
				playSes($scope.ses);
				
				
				setMapLayout();
				setPlayerStyles();
				setHandStyles();
				
				$http.post('/citadelsgame/empty').then(function(response){
					adjustLogs("log-zone")
				});
				//$scope.$apply()
			});
		}
		
		$scope.getBoard();
		
		ws.onMessage(function(){
			$scope.getBoard();
		});
		
		
}]);
