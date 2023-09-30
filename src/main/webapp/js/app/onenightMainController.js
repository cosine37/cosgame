var setUrl = function(d){
	hearder = "http://";
	server = "localhost:8080";
	return header + server + d;
}

var app = angular.module("onenightMainApp", ["ngWebSocket"]);
app.controller("onenightMainCtrl", ['$scope', '$window', '$http', '$document', '$timeout', '$websocket',
	function($scope, $window, $http, $document, $timeout, $websocket){
		const thisTab = "onenight";
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
		
		var ws = $websocket("ws://" + $window.location.host + "/onenight/allboardsrefresh");
		ws.onError(function(event) {
		});
	
		ws.onClose(function(event) {
		});
	
		ws.onOpen(function() {
		});
		
		var boardws = $websocket("ws://" + $window.location.host + "/onenight/boardrefresh");
		boardws.onError(function(event) {
		});
	
		boardws.onClose(function(event) {
		});
	
		boardws.onOpen(function() {
		});
		
		$scope.onTablesTab = true;
	
		$scope.goto = function(d){
			var x = "http://" + $window.location.host;
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
		
		$scope.showTablesTab = function(){
			$scope.onTablesTab = true;
		}
		
		$scope.hideTablesTab = function(){
			$scope.onTablesTab = false;
		}
		
		$scope.getAllBoards = function(){
			$http.get('/onenight/allboards').then(function(response){
				var n = response.data.value.length / 4;
				$scope.boards = []
				$scope.statuses = []
				$scope.lords = []
				$scope.canBack = []
				for (var i=0;i<n;i++){
					$scope.boards.push(response.data.value[i*4])
					var l = response.data.value[i*4+1]
					$scope.lords.push(l)
					var x = response.data.value[i*4+2]
					var t = ''
					if (x == '0'){
						t = '准备中'
					} else if (x == '5'){
						t = '游戏结束'
					} else {
						t = '游戏中'
					}
					$scope.statuses.push(t)
					var y = response.data.value[i*4+3]
					$scope.canBack.push(y)
				}
			});
		}
		
		$scope.newGame = function(){
			$http.post('/onenight/newboard').then(function(response){
				var json_data = '{"type":"notify","content":"refresh"}';
		        ws.send(json_data);
				$scope.goto('onenightcreategame');
			});
		}
		
		$scope.goToBoard = function(index){
			var data = {"boardId" : $scope.boards[index]}
			$http({url: "/onenight/setboardid", method: "POST", params: data}).then(function(response){
				$http.post("/onenight/join").then(function(response){
					boardws.send($scope.boards[index]);
					$scope.goto('onenightcreategame')
				});
			});
		}
		
		$scope.backToBoard = function(index){
			var data = {"boardId" : $scope.boards[index]}
			$http({url: "/onenight/setboardid", method: "POST", params: data}).then(function(response){
				$scope.goto('onenightgame');
			});
		}
		
		$scope.getAllBoards();
		
		ws.onMessage(function(){
			$scope.getAllBoards();
		});
		
		playGotoSE = function(){
			var audio = new Audio("/sound/Belltower/goto.wav")
			audio.play();
		}
		
		playLeaveSE = function(){
			var audio = new Audio("/sound/Belltower/leave.wav")
			audio.play();
		}
		
		playChatSE = function(){
			var audio = new Audio("/sound/Belltower/" + $scope.chatSound)
			audio.play();
		}
		
		playFlipSE = function(){
			var audio = new Audio("/sound/Belltower/flip.wav")
			audio.play();
		}
		
		playMiningSE = function(){
			var audio = new Audio("/sound/Belltower/mining.wav")
			audio.play();
		}
		
		playOpeningSE = function(){
			var audio = new Audio("/sound/Belltower/opening.wav")
			audio.play();
		}
		
		$scope.dailyReward = function(){
			$http.post('/belltower/dailyreward').then(function(response){
				$scope.getAccountInfo();
			});
		}
		
		$scope.cleanAccount = function(){
			$http.post('/belltower/cleanaccount').then(function(response){
				$scope.getAccountInfo();
			});
		}
		
		$scope.showPlaceButtons = true;
		$scope.shownPlace = "";
		$scope.openPlace = function(x){
			playGotoSE()
			if (x == 'home'){
				setIconInfo();
			}
			if (x == 'belltower'){
				setBelltowerEvent();
			}
			$scope.loadingReward = false;
			$scope.showReward = false;
			$scope.shownPlace = x
			$scope.placeStyle = {
				"background-image" : "url('/image/Belltower/" + x + ".jpg')",
				"background-size" : "cover"
			}
		}
		$scope.closePlace = function(){
			playLeaveSE()
			$scope.shownPlace = ""
		}
		
		
		$scope.loadingReward = false;
		$scope.showReward = false;
		$scope.dig = function(){
			playMiningSE()
			$scope.loadingReward = true;
			$scope.chatSound = "gain.wav"
			$http({url: "/belltower/dig", method: "POST"}).then(function(response){
				var rewardMsgRaw = response.data.value[0];
				$scope.rewardMsg = "";
				if (rewardMsgRaw.charAt(0) == 'd'){
					$scope.rewardImg = "/image/Belltower/diamond.png";
					if (rewardMsgRaw.substring(1) == "1"){
						
					} else if (rewardMsgRaw.substring(1) == "2"){
						$scope.chatSound = "success.wav"
					} else {
						$scope.chatSound = "cheer.wav"
					}
					$scope.rewardMsg = "获得" + rewardMsgRaw.substring(1) + "颗钻石";
				} else if (rewardMsgRaw.charAt(0) == 'i'){
					$scope.rewardImg = "/image/Belltower/Icons/" + rewardMsgRaw.substring(1,4) + ".png";
					var level = rewardMsgRaw.charAt(1);
					if (level == '1'){
						$scope.rewardMsg = "获得头像："
					} else if (level == '2'){
						$scope.chatSound = "success.wav"
						$scope.rewardMsg = "获得稀有头像："
					} else if (level == '3'){
						$scope.chatSound = "cheer.wav"
						$scope.rewardMsg = "获得史诗头像："
					} else {
						$scope.rewardMsg = "获得头像："
					}
					$scope.rewardMsg = $scope.rewardMsg + rewardMsgRaw.substring(4);
				} else if (rewardMsgRaw.charAt(0) == 'k'){
					$scope.chatSound = "cheer.wav"
					$scope.rewardImg = "/image/Belltower/chest.png";
					$scope.rewardMsg = "获得一个宝箱";
				}
				$scope.getAccountInfo()
				for (var i=0;i<2500000000;i++){}
				//time.sleep(2)
				$scope.loadingReward = false;
				$scope.showReward = true;
				playChatSE()
			});
		}
		
		$scope.openChest = function(){
			$scope.loadingReward = true;
			playOpeningSE()
			$http({url: "/belltower/openchest", method: "POST"}).then(function(response){
				$scope.chatSound = "success.wav"
				var rewardMsgsRaw = response.data.value;
				$scope.rewardMsgs = []
				$scope.rewardImgs = []
				for (var i=0;i<rewardMsgsRaw.length;i++){
					var rewardMsgRaw = rewardMsgsRaw[i];
					if (rewardMsgRaw.charAt(0) == 'd'){
						$scope.rewardImg = "/image/Belltower/diamond.png";
						$scope.rewardMsg = rewardMsgRaw.substring(1) + "颗钻石";
					} else if (rewardMsgRaw.charAt(0) == 'i'){
						$scope.rewardImg = "/image/Belltower/Icons/" + rewardMsgRaw.substring(1,4) + ".png";
						var level = rewardMsgRaw.charAt(1);
						if (level == '1'){
							$scope.rewardMsg = "头像："
						} else if (level == '2'){
							$scope.rewardMsg = "稀有头像："
						} else if (level == '3'){
							$scope.rewardMsg = "史诗头像："
						} else {
							$scope.rewardMsg = "头像："
						}
						$scope.rewardMsg = $scope.rewardMsg + rewardMsgRaw.substring(4);
					} else if (rewardMsgRaw.charAt(0) == 'm'){
						$scope.rewardImg = "/image/Belltower/coin.png";
						$scope.rewardMsg = rewardMsgRaw.substring(1) + "枚钱币";
					}
					$scope.rewardImgs.push($scope.rewardImg);
					$scope.rewardMsgs.push($scope.rewardMsg);
				}
				$scope.getAccountInfo()
				for (var i=0;i<2500000000;i++){}
				//time.sleep(2)
				$scope.loadingReward = false;
				$scope.showReward = true;
				playChatSE()
			});
		}
		
		var setCurCharacterPage = function(){
			var cols = 5;
			var rows = 2;
			var pageSize = 10;
			var i,j;
			$scope.charsCurPage = [["x","x","x","x","x"],["x","x","x","x","x"]];
			for (i=0;i<rows;i++){
				for (j=0;j<cols;j++){
					var x = ($scope.curPage - 1)*pageSize + i*cols + j;
					if (x>=0 && x<$scope.accountInfo.availableCharacters.length){
						$scope.charsCurPage[i][j] = $scope.accountInfo.availableCharacters[x];
					}
				}
			}
		}
		
		var setIconInfo = function(){
			var pageSize = 10;
			$scope.numPages = Math.ceil($scope.accountInfo.availableCharacters.length / pageSize);
			if ($scope.numPages == 0) $scope.numPages = 1;
			$scope.curPage = 1;
			setCurCharacterPage();
		}
		
		$scope.canFlipCharPage = function(x){
			if (x == 1){
				if ($scope.curPage < $scope.numPages){
					return true;
				} else {
					return false;
				}
			} else if (x == -1){
				if ($scope.curPage > 1){
					return true;
				} else {
					return false;
				}
			}
		}
		
		$scope.flipCharPage = function(x){
			playFlipSE()
			$scope.curPage = $scope.curPage+x;
			setCurCharacterPage();
		}
		
		$scope.belltowerEvent = 0;
		var setBelltowerEvent = function(){
			$scope.belltowerEvent = 0;
			$scope.numButtonShown = 1;
			$scope.disableFirstButton = false;
			
			if ($scope.accountInfo.visitedBelltower == false){
				$scope.belltowerEvent = -2;
				$scope.belltowerEventMsg = "你在钟楼里遇到了一位小男孩。";
				$scope.belltowerButton = "对话";
				$scope.belltowerImg = "boy.jpg"
				return;
			}
			
			var x = Math.floor(Math.random() * 10);
			if (x<4){
				$scope.belltowerEvent = Math.floor(Math.random()*8)+1;
			}
			//$scope.belltowerEvent = 9;
			var msgs = ["楼顶有一只钟。","你在钟楼里遇到了一只羊。","你在钟楼里遇到了一位长相可怕的独眼乞丐。他说：“行行好。”",
				"你在钟楼里发现了金、银、铅匣子，一旁的贵族说道：“我家小姐的头像就在其中一个匣子里，你只能打开一个，祝你好运。”",
				"你在钟楼里遇到了一位彷徨的贵族模样的人。","你在钟楼里遇到了一位看起来十分睿智的老者。","你在钟楼里遇到了一位中年船长。","你在钟楼里遇到了一位小男孩。",
				"你在钟楼里遇到了一位看起来十分有才的艺术家。","你在钟楼里遇到了一位探险家。"];
			
			var btns = ["敲钟","对话","给1个钱币","打开金匣子","对话","对话","对话","对话","对话","对话"];
			var imgs = ["", "goat.png","beggar.jpg","caskets.jpg","hamlet.jpg","davinci.jpg","magellan.jpg","boy.jpg","michelangelo.jpg", "colombo.jpg"];
			
			$scope.belltowerEventMsg = msgs[$scope.belltowerEvent];
			$scope.belltowerButton = btns[$scope.belltowerEvent];
			$scope.belltowerImg = imgs[$scope.belltowerEvent]
			
			if ($scope.belltowerEvent == 2){
				if ($scope.accountInfo.money == 0){
					$scope.disableFirstButton = true;
				}
				$scope.numButtonShown = 2;
				$scope.belltowerButton2 = "朝他扔石头";
			} else if ($scope.belltowerEvent == 3){
				var i
				var alreadyHasIcon = false
				for (i=0;i<$scope.accountInfo.availableCharacters.length;i++){
					var a = $scope.accountInfo.availableCharacters[i]
					if (a == "900"){
						alreadyHasIcon = true;
						break;
					}
				}
				if (alreadyHasIcon){
					$scope.belltowerEvent = 0;
					$scope.belltowerEventMsg = msgs[$scope.belltowerEvent];
					$scope.belltowerButton = btns[$scope.belltowerEvent];
					$scope.belltowerImg = imgs[$scope.belltowerEvent]
				} else {
					$scope.numButtonShown = 3;
					$scope.belltowerButton2 = "打开银匣子";
					$scope.belltowerButton3 = "打开铅匣子";
				}
			} else if ($scope.belltowerEvent == 6){
				var i
				var has904 = false
				for (i=0;i<$scope.accountInfo.availableCharacters.length;i++){
					var a = $scope.accountInfo.availableCharacters[i]
					if (a == "904"){
						has904 = true;
						break;
					}
				}
				if (has904){
					$scope.belltowerEvent = 0;
					$scope.belltowerEventMsg = msgs[$scope.belltowerEvent];
					$scope.belltowerButton = btns[$scope.belltowerEvent];
					$scope.belltowerImg = imgs[$scope.belltowerEvent]
				} else {
					var has903 = false
					for (i=0;i<$scope.accountInfo.availableCharacters.length;i++){
						var a = $scope.accountInfo.availableCharacters[i]
						if (a == "903"){
							has903 = true;
							break;
						}
					}
					if (has903){
						$scope.belltowerEventMsg = "你在钟楼里遇到了一位水手。水手说道：“啊！你居然有我船长的名片，你一定是他的朋友，我有他托我带给你的东西！”";
						$scope.belltowerButton = "赶紧拿出来";
						$scope.belltowerImg = "sailor.jpg"
					}
				}
			}
		}
		
		$scope.triggerBelltowerEvent = function(x){
			$scope.chatSound = "chat.wav"
			$scope.numButtonShown = 0;
			if ($scope.belltowerEvent == -2){
				$scope.belltowerEventMsg = "小男孩说道：“我听大人们缩，有些头像只能在这座钟楼里获得耶。不造他们缩的是什么意思呢。”";
				$http({url: "/belltower/firstvisit", method: "POST"}).then(function(response){
					$scope.getAccountInfo();
				});
			} if ($scope.belltowerEvent == 0){
				$scope.chatSound = "bell.wav"
				$scope.belltowerEvent = -1;
				var t = Math.floor(Math.random() * 10);
				if (t < 3){
					var k = Math.floor(Math.random() * 5)+1;
					$scope.belltowerEventMsg = "从敲动的钟内掉落了"+k+"枚钱币，你收集了掉落的钱币。";
					var data = {"amount":k, "msg":"敲钟获得"}
					$http({url: "/belltower/belltowerevent", method: "POST", params: data}).then(function(response){
						$scope.getAccountInfo();
					});
				} else {
					$scope.belltowerEventMsg = "钟声响彻四周。";
				}
				
			} else if ($scope.belltowerEvent == 1){
				var date = new Date();
				var month = date.getMonth() + 1;
				var day = date.getDate();
				var hour = date.getHours();
				var minute = date.getMinutes();
				var time = month+"月"+day+"日"+hour+"时"+minute+"分";
				
				$scope.belltowerEventMsg = "那只羊说道：“现在时间是" + time + "。”，真是一只神奇的羊。"
			} else if ($scope.belltowerEvent == 2){
				if (x==0){
					$scope.belltowerEventMsg = "乞丐笑了，但他的笑容逐渐消失：“当我高兴时，我笑。当我笑时，我丑。”"
					var data = {"amount":-1, "msg":"施舍乞丐"}
					$http({url: "/belltower/belltowerevent", method: "POST", params: data}).then(function(response){
						$scope.getAccountInfo();
					});
				} else if (x==1){
					$scope.chatSound = "stone.wav"
					$scope.belltowerEventMsg = "乞丐说道：“我知道我长得丑，被扔石头无所谓，但让你害怕让我觉得很难过。”"
				}
			} else if ($scope.belltowerEvent == 3){
				var t = Math.floor(Math.random() * 3);
				if (t == 0){
					$scope.chatSound = "success.wav"
					$scope.belltowerImg = "portia.png"
					$scope.belltowerEventMsg = "恭喜你，获得传说头像：波西亚！";
					if (x == 0){
						$scope.belltowerEventMsg = $scope.belltowerEventMsg + "匣子里还写了一句话：“是金子总会发光。”";
					} else if (x == 1){
						$scope.belltowerEventMsg = $scope.belltowerEventMsg + "匣子里还写了一句话：“以银为结，以银为彩，以银为荣，以银为贵。”";
					} else if (x == 2){
						$scope.belltowerEventMsg = $scope.belltowerEventMsg + "匣子里还写了一句话：“你选择不凭着外表，果然给你直中鹄心。”";
					}
					var data = {"charId":900}
					$http({url: "/belltower/addcharacter", method: "POST", params: data}).then(function(response){
						$scope.getAccountInfo();
					});
				} else {
					$scope.chatSound = "fail.wav"
					$scope.belltowerEventMsg = "小姐的头像不在匣子里。";
					if (x == 0){
						$scope.belltowerEventMsg = $scope.belltowerEventMsg + "匣子里还写了一句话：“发光的不一定是金子。”";
					} else if (x == 1){
						$scope.belltowerEventMsg = $scope.belltowerEventMsg + "匣子里还写了一句话：“世上尽有些呆鸟，空有着镀银的外表。”";
					} else if (x == 2){
						$scope.belltowerEventMsg = $scope.belltowerEventMsg + "匣子里还写了一句话：“不宜妄自菲薄，引喻失义。”";
					}
				}
			} else if ($scope.belltowerEvent == 4){
				$scope.belltowerEventMsg = "那贵族说着“生存还是毁灭，这是个问题。”这样令人半懂不懂的话。"
			} else if ($scope.belltowerEvent == 5){
				var has901 = false;
				var has902 = false;
				for (i=0;i<$scope.accountInfo.availableCharacters.length;i++){
					var a = $scope.accountInfo.availableCharacters[i]
					if (a == "901"){
						has901 = true;
					} else if (a == "902"){
						has902 = true;
					}
				}
				var t = Math.floor(Math.random() * 10);
				if (has901 && has902){
					t = 9;
				}
				if (t == 0){
					$scope.chatSound = "success.wav"
					var y = Math.floor(Math.random() * 2);
					if (y == 0){
						if (has901) y=1;
					} 
					var characters = ["丽莎·焦孔多","切奇利娅"];
					var charStrs = ["901", "902"];
					var charName = characters[y];
					var charId = charStrs[y];
					$scope.belltowerImg = charId + ".png";
					$scope.belltowerEventMsg = "老者说道：“这是我之前作品的初稿，看在你我有缘的份上，这份初稿就送给你了。” 恭喜你，获得传说头像：" + charName + "！";
					var data = {"charId":charId}
					$http({url: "/belltower/addcharacter", method: "POST", params: data}).then(function(response){
						$scope.getAccountInfo();
					});
				} else {
					var quotes = ["老者说道：“真理是时间的女儿。”", "老者激昂地说道：“认识自己，无往不利。”","老者说道：“成功的人很少等待机会来临，他们总会主动创造机会。”","老者一本正经地说道：“妄想一夜致富的人定会一事无成。”",
						"老者笑着说：“偶尔远离你的工作，给自己放松一下。”","老者说道：“眼睛是心灵的窗户。”","老者义愤填膺地说：“不惩罚犯罪的人就是下令犯罪。”","老者无奈地说：“最不幸的事情就是你的思想超前于你的工作。”",
						"老者吐槽道：“人若沒有美好面孔以及美感素质将会早亡。”"];
					var y = Math.floor(Math.random() * quotes.length);
					$scope.belltowerEventMsg = quotes[y];
				}
			} else if ($scope.belltowerEvent == 6){
				var has903 = false;
				for (i=0;i<$scope.accountInfo.availableCharacters.length;i++){
					var a = $scope.accountInfo.availableCharacters[i]
					if (a == "903"){
						has903 = true;
						break;
					}
				}
				var t = Math.floor(Math.random() * 3);
				if (has903){
					$scope.chatSound = "success.wav"
					$scope.belltowerImg = "904.png";
					$scope.belltowerEventMsg = "恭喜你，获得宝箱和传说头像：安东尼奥！“可惜船长被香料岛的土著杀了，我一定要写书纪念这次航行。”，水手说。";
					var data = {"charId":"904"}
					$http({url: "/belltower/addcharacter", method: "POST", params: data}).then(function(response){
						$http({url: "/belltower/addkey", method: "POST", params: data}).then(function(response){
							$scope.getAccountInfo();
						});
					});
				} else if (t == 0){
					$scope.chatSound = "success.wav"
					$scope.belltowerImg = "903.png";
					$scope.belltowerEventMsg = "船长亢奋地说道：“明天我就要启程开辟新航道了！请收下我名片，史书终将留下我姓名！” 恭喜你，获得传说头像：费尔南多！";
					var data = {"charId":"903"}
					$http({url: "/belltower/addcharacter", method: "POST", params: data}).then(function(response){
						$scope.getAccountInfo();
					});
				} else {
					var quotes = ["船长无语地说道：“我相信地球是圆的，可大家都以为我疯了。”","船长说道：“我听说东南亚有香料群岛，我有朝一日一定要去那里开展贸易！”"]
					var y = Math.floor(Math.random() * quotes.length);
					$scope.belltowerEventMsg = quotes[y];
				}
			} else if ($scope.belltowerEvent == 7){
				var quotes = ["小男孩不耐烦地说道：“就超扯的，为什么老有人叫我盒蛋，烦内。”","小男孩说道：“我听大人们缩，在矿场，除了头像，还能挖到钻石 汗 宝箱耶。真的假的~”",
					"小男孩惊喜说道：“哇哦，我跟你讲吼，刚我撞钟，居然有硬币掉出来了耶，有够赞！”","小男孩笑着说道：“你造嘛，这个小镇是一个叫孔性的程式工程师设计的耶，真的好棒棒喔！”"]
				var y = Math.floor(Math.random() * quotes.length);
				$scope.belltowerEventMsg = quotes[y];
			} else if ($scope.belltowerEvent == 8){ 
				var has905 = false;
				for (i=0;i<$scope.accountInfo.availableCharacters.length;i++){
					var a = $scope.accountInfo.availableCharacters[i]
					if (a == "905"){
						has905 = true;
						break;
					}
				}
				var t = Math.floor(Math.random() * 10);
				if (has905){
					t = 9;
				}
				if (t == 0){
					$scope.chatSound = "success.wav"
					$scope.belltowerImg = "905.png";
					$scope.belltowerEventMsg = "那艺术家说道：“我曾是个画家，但我现在更爱雕刻。我这画作草稿就送你了。” 恭喜你，获得传说头像：先知！";
					var data = {"charId":"905"}
					$http({url: "/belltower/addcharacter", method: "POST", params: data}).then(function(response){
						$scope.getAccountInfo();
					});
				} else {
					var quotes = ["那艺术家唱道：“爱要精心来雕刻，我是米开朗基罗。”","那艺术家说道：“忧郁症是我的欢乐。”","那艺术家说道：“完美不是一个小细节，但注重细节可以成就完美。”"]
					var y = Math.floor(Math.random() * quotes.length);
					$scope.belltowerEventMsg = quotes[y];
				}
			} else if ($scope.belltowerEvent == 9){
				var t = Math.floor(Math.random() * 8);
				if ($scope.accountInfo.canBuyEpic){
					t = 7;
				}
				if (t == 0){
					$scope.chatSound = "success.wav"
					$scope.belltowerEventMsg = "那探险家说道：“我累了，不想出去航海了，就在这儿卖一卖东印度土著送给我的画作吧。” 集市里多了一位商人。";
					$http({url: "/belltower/unlockepic", method: "POST"}).then(function(response){
						$scope.getAccountInfo();
					});
				} else {
					var quotes = ["那探险家说道：“言语无法做到的事情，金子可以做。”","那探险家得意地说道：“你知道吗，我去过日本，还把那边改名为西班牙，嘿嘿嘿嘿嘿嘿嘿。”","那探险家雄心勃勃地说道：“除非你有勇气到达看不到岸边的地方，否则你永远不可能跨越大洋。”",
						"那探险家坚定地说道：“我曾经去过的一定是马可波罗所去过的亚洲，但质疑我的人越来越多。”","那探险家说道：“我有次去东印度时遇到了月全食，我告诉当地土著因为他们怠慢我们月亮发怒了，吓得他们赶紧给我们食物。”"]
					var y = Math.floor(Math.random() * quotes.length);
					$scope.belltowerEventMsg = quotes[y];
				}
			}
			playChatSE()
		}
		
		$scope.resolveBelltowerEvent = function(){
			$scope.chatSound = "chat.wav"
			playChatSE()
			$scope.belltowerEvent = 0;
			$scope.numButtonShown = 1;
			$scope.belltowerEventMsg = "楼顶有一只钟。";
			$scope.belltowerButton = "敲钟";
			$scope.disableFirstButton = false;
		}
		
		$scope.getAccountInfo = function(){
			$http.get('/belltower/accountinfo').then(function(response){
				$scope.accountInfo = response.data;
			});
		}
		
}]);
