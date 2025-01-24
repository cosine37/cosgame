var setUrl = function(d){
	hearder = "http://";
	server = "localhost:8080";
	return header + server + d;
}

var app = angular.module("oinkMainApp", ["ngWebSocket"]);
app.controller("oinkMainCtrl", ['$scope', '$window', '$http', '$document', '$websocket',
	function($scope, $window, $http, $document, $websocket){
		const thisTab = "oink";
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
		
		var ws = $websocket("ws://" + $window.location.host + "/oink/allboardsrefresh");
		ws.onError(function(event) {
		});
	
		ws.onClose(function(event) {
		});
	
		ws.onOpen(function() {
		});
		
		var boardws = $websocket("ws://" + $window.location.host + "/oink/boardrefresh");
		boardws.onError(function(event) {
		});
	
		boardws.onClose(function(event) {
		});
	
		boardws.onOpen(function() {
		});
	
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
		
		$scope.newGame = function(){
			$http({url: "/oink/newboard", method: "POST"}).then(function(response){
				var json_data = '{"type":"notify","content":"refresh"}';
		        ws.send(json_data);
		        $scope.goto('oinkcreategame')
			});
			
		}
		
		$scope.goToBoard = function(index){
			var data = {"boardId" : $scope.boards[index]}
			$http({url: "/oink/setboardid", method: "POST", params: data}).then(function(response){
				$http.post("/oink/join").then(function(response){
					//var json_data = '{"type":"notify","content":"refresh"}';
			        boardws.send("refresh");
					$scope.goto('oinkcreategame')
				});
			});
		}
		
		$scope.backToBoard = function(index){
			var data = {"boardId" : $scope.boards[index]}
			$http({url: "/oink/setboardid", method: "POST", params: data}).then(function(response){
				$scope.goto('oinkgame');
			});
		}
		
		$scope.getAllBoards = function(){
			$http.get('/oink/allboards').then(function(response){
				var n = response.data.value.length / 5;
				$scope.boards = []
				$scope.statuses = []
				$scope.lords = []
				$scope.gameModes = []
				$scope.canBack = []
				for (var i=0;i<n;i++){
					$scope.boards.push(response.data.value[i*5])
					var l = response.data.value[i*5+1]
					$scope.lords.push(l)
					var x = response.data.value[i*5+2]
					var t = ''
					if (x == '0'){
						t = '准备中'
					} else if (x == '10'){
						t = '游戏结束'
					} else {
						t = '游戏中'
					}
					$scope.statuses.push(t)
					var z = response.data.value[i*5+3]
					t = ''
					if (x == '0'){
						t = '-'
					} else if (z == '1'){
						t = '初创公司'
					} else if (z == '2'){
						t = '狗头侦探'
					} else if (z == '6'){
						t = '保卫教宗'
					} else if (z == '4'){
						t = '兄莠弟攻'
					}
					$scope.gameModes.push(t);
					var y = response.data.value[i*5+4]
					$scope.canBack.push(y)
				}
			});
		}
		
		$scope.getAllBoards();
		
		ws.onMessage(function(){
			$scope.getAllBoards();
		});
		
		$scope.chooseAvatar = function(x){
			var data = {"avatarId" : x}
			$http({url: "/oink/chooseavatar", method: "POST", params: data}).then(function(response){
				$scope.getAccountInfo()
			});
		}
		
		$scope.changeSignature = function(){
			var data = {"s" : $scope.mySignature}
			$http({url: "/oink/changesignature", method: "POST", params: data}).then(function(response){
				$scope.getAccountInfo()
			});
		}
		
		$scope.getAccountInfo = function(){
			$http.get('/oink/accountinfo').then(function(response){
				$scope.accountInfo = response.data;
			});
		}
		
		$scope.getAccountInfo()
		
		
		$scope.cards = []
		c = {
			"num" : 5,
			"name" : "五福铁门",
			"coinOn" : 0,
			"barColor": {
				"background-color": "rgb(255,165,0)"
			},
			"iconStyle" : {
				'background-image': 'url(/image/Oink/Startups/5.png)',
				"background-size" : 'cover'
			}
		}
		c2 = {
			"num" : 10,
			"name" : "十年纱窗",
			"coinOn" : 0,
			"barColor": {
				"background-color": "rgb(210,43,43)"
			},
			"iconStyle" : {
				'background-image': 'url(/image/Oink/Startups/10.png)',
				"background-size" : 'cover'
			}
		}
		$scope.cards.push(c);
		
		$scope.groveCards = []
		c = {
			"num": 8,
			"name": "阿瑟·夏朋婕",
			"description": "夏朋婕太太的儿子，爱莉丝·夏朋婕的哥哥，服役于海军，最近正好在休假，曾拿着木棍追着德雷伯先生打。",
			"avatarStyle": {
				'background-image': 'url(/image/Oink/Grove/Arthur.png)',
				"background-size" : 'cover'
			}
		}
		$scope.groveCards.push(c);
		
		$scope.groveCards2 = []
		c = {
			"num": 5,
			"name": "约瑟夫·斯坦格森",
			"description": "德雷伯先生的秘书，掌管德雷伯先生的开支，是个沉默寡言、有涵养的人。",
			"avatarStyle": {
				'background-image': 'url(/image/Oink/Grove/Strangerson.png)',
				"background-size" : 'cover'
			}
		}
		$scope.groveCards2.push(c);
		
		$scope.groveCards3 = []
		c = {
			"num": -2,
			"name": "托比亚斯·格雷格森",
			"description": "苏格兰场的警探，是那些蠢货中的佼佼者。明明这么普通但却那么自信。",
			"avatarStyle": {
				'background-image': 'url(/image/Oink/Grove/Gregson.png)',
				"background-size" : 'cover'
			}
		}
		$scope.groveCards3.push(c);
		
		$scope.popeCards = []
		c9 = {
			"num": 9,
			"name": "教宗",
			"description": "若你打出或弃置该牌，你出局。",
			"avatarStyle": {
				'background-image': 'url(/image/Oink/Pope/Pope.png)',
				"background-size" : 'cover'
			},
			"primaryColor": {
				"background-color": "goldenrod"
			},
			"secondaryColor": {
				"background-color": "PaleGoldenRod"
			}
		}
		c8 = {
			"num": 8,
			"name": "村长",
			"description": "无效果。若你的手牌中有女巫或法师，你必须打出这张村长。",
			"avatarStyle": {
				'background-image': 'url(/image/Oink/Pope/Mayor.png)',
				"background-size" : 'cover'
			},
			"primaryColor": {
				"background-color": "DarkGreen"
			},
			"secondaryColor": {
				"background-color": "PaleGreen"
			}
		}
		c7 = {
			"num": 7,
			"name": "法师",
			"description": "与任意一名玩家交换手牌。",
			"avatarStyle": {
				'background-image': 'url(/image/Oink/Pope/Mage.png)',
				"background-size" : 'cover'
			},
			"primaryColor": {
				"background-color": "Purple"
			},
			"secondaryColor": {
				"background-color": "Plum"
			}
		}
		c1 = {
			"num": 1,
			"name": "狼人",
			"description": "猜另一名玩家的手牌(不能猜狼人)，若猜中则该玩家出局。",
			"avatarStyle": {
				'background-image': 'url(/image/Oink/Pope/Werewolf.png)',
				"background-size" : 'cover'
			},
			"primaryColor": {
				"background-color": "rgb(126,33,18)"
			},
			"secondaryColor": {
				"background-color": "Wheat"
			}
		}
		
		c2 = {
			"num": 2,
			"name": "预言家",
			"description": "查看一名其他在场玩家的手牌。",
			"avatarStyle": {
				'background-image': 'url(/image/Oink/Pope/Seer.png)',
				"background-size" : 'cover'
			},
			"primaryColor": {
				"background-color": "DarkCyan"
			},
			"secondaryColor": {
				"background-color": "LightCyan"
			}
		}
		
		c3 = {
			"num": 3,
			"name": "骑士",
			"description": "与一名其他在场玩家秘密比较手牌声望，声望小的玩家出局，相同则无人出局。",
			"avatarStyle": {
				'background-image': 'url(/image/Oink/Pope/Knight.png)',
				"background-size" : 'cover'
			},
			"primaryColor": {
				"background-color": "OrangeRed"
			},
			"secondaryColor": {
				"background-color": "LightSalmon"
			}
		}
		
		c4 = {
			"num": 4,
			"name": "守卫",
			"description": "到下一回合开始前，所有牌对你无效。",
			"avatarStyle": {
				'background-image': 'url(/image/Oink/Pope/Guard.png)',
				"background-size" : 'cover'
			},
			"primaryColor": {
				"background-color": "rgb(0,150,255)"
			},
			"secondaryColor": {
				"background-color": "PaleTurquoise"
			}
		}
		
		c5 = {
			"num": 5,
			"name": "女巫",
			"description": "指定一名在场玩家（可以是自己），该玩家弃置手牌并抽一张牌。",
			"avatarStyle": {
				'background-image': 'url(/image/Oink/Pope/Witch.png)',
				"background-size" : 'cover'
			},
			"primaryColor": {
				"background-color": "Purple"
			},
			"secondaryColor": {
				"background-color": "Plum"
			}
		}
		
		c0 = {
			"num": 0,
			"name": "盗贼",
			"description": "本轮游戏结束时，若你是唯一在场打出或弃置盗贼的玩家，你获得一个灵杖。",
			"avatarStyle": {
				'background-image': 'url(/image/Oink/Pope/Thief.png)',
				"background-size" : 'cover'
			},
			"primaryColor": {
				"background-color": "DarkSlateGrey"
			},
			"secondaryColor": {
				"background-color": "LightGrey"
			}
		}
		
		c6 = {
			"num": 6,
			"name": "学者",
			"description": "抽3张牌（不足则全抽），将你的手牌放回至牌堆直至你只有一张手牌。",
			"avatarStyle": {
				'background-image': 'url(/image/Oink/Pope/Scholar.png)',
				"background-size" : 'cover'
			},
			"primaryColor": {
				"background-color": "Magenta"
			},
			"secondaryColor": {
				"background-color": "LightPink"
			}
		}
		$scope.popeCards.push(c0);
		$scope.popeCards.push(c1);
		$scope.popeCards.push(c2);
		$scope.popeCards.push(c3);
		$scope.popeCards.push(c4);
		$scope.popeCards.push(c5);
		$scope.popeCards.push(c6);
		$scope.popeCards.push(c7);
		$scope.popeCards.push(c8);
		$scope.popeCards.push(c9);
		
		wc1 = {
			"num": 15,
			"name": "孙悟空",
			"desc": "石头里蹦出来的猢狲，保唐僧西天取经。",
			"avatarStyle": {
				'background-image': 'url(/image/Oink/West/1l.png)',
				"background-size" : 'cover'
			},
		}
		$scope.westCards = []
		//$scope.westCards.push(wc1);
	
}]);
