var setUrl = function(d){
	hearder = "https://";
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
			var x = "https://" + $window.location.host;
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
		
		$scope.logout = function(){
			$http({url: "/logout", method: "POST"}).then(function(response){
				$scope.goto('login');
			});
		}
		
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
		
		$scope.startupsCards = []
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
		$scope.startupsCards.push(c);
		
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
		$scope.popeCards.push(c9);
		
		wc1 = {
			"num": 13,
			"name": "孙悟空",
			"desc": "天生地产的石猴，唐僧的大徒弟，神通广大但经常张口就来。最喜欢别人称其为弼马温。",
			"avatarStyle": {
				'background-image': 'url(/image/Oink/West/13.png)',
				"background-size" : 'cover'
			},
		}
		$scope.westCards = []
		$scope.westCards.push(wc1);
}]);
