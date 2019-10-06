var setUrl = function(d){
	hearder = "http://";
	server = "localhost:8080";
	return header + server + d;
}

var app = angular.module("dominionBoardApp", []);
app.controller("dominionBoardCtrl", ['$scope', '$window', '$http', '$document', '$timeout',
	function($scope, $window, $http, $document, $timeout){
		$scope.goto = function(d){
			var x = "http://" + $window.location.host;
			$window.location.href = x + "/" + d;
		}
		
		$scope.numPlayers = 2;
		
		$scope.showCardList = false;
		
		$scope.isLord = false;
		$scope.showBigImage = false;
		
		var s = "";
		
		$scope.kindom = [];
		$scope.allCards = [];
		$scope.expStyle = [];
		$scope.cardStyle = [];
		
		var expansionShow = [];
		$scope.selected = [];
		$scope.used = [];
		$scope.usedExp = [];
		$scope.usedIndex = [];
		$scope.useHelperArray = new Array(10);
		
		$scope.addBot = function() {
			$http({url: "/dominiongame/addbot", method: "POST"}).then(function(response){
				
			});
		}
		
		$scope.leave = function() {
			$http({url: "/dominiongame/leaveboard", method: "POST"}).then(function(response){
				$scope.goto("dominion");
			});
		}
		
		$scope.numPlayerOptions = [2,3,4];
		
		$scope.changeNumPlayer = function(){
			var data = {"numPlayers": $scope.numPlayers};
			$http({url: "/dominiongame/setnumplayers", method: "POST", params: data}).then(function(response){
				
			});
		}
		
		$scope.showKick = function(index){
			if (index == 0) return false;
			return $scope.isLord;
		}
		
		$scope.ready = function() {
			if ($scope.playernames.length == $scope.numPlayers){
				$http({url: "/dominiongame/ready", method: "POST"}).then(function(response){
					
				});
			} else {
				alert("No enough players!");
			}
		}
		
		
		$scope.randomize = function(){
			$http({url: "/dominiongame/randomize", method: "POST"}).then(function(response){
				
			});
		}
		
		$scope.kick = function(name){
			var data = {"kickedName": name};
			$http({url: "/dominiongame/kick", method: "POST", params: data}).then(function(response){
				
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
		
		getAllInfo = function(){
			$http.post("/dominiongame/getboardallinfo").then(function(response){
				$scope.isLord = response.data.lord;
				$scope.status = response.data.status;
				$scope.playernames = response.data.names;
				$scope.playernames[0] = $scope.playernames[0] + "(lord)";
				$scope.kindom = response.data.kindom;
				$scope.readyStatus = response.data.ready;
				if ($scope.status == 1){
					$scope.goto("dominiongame");
				}
			});
			
			$timeout(function(){
				if ($scope.status == 0){
					getAllInfo();
				}
				
			},1000);
		}
		
		getAllInfo();
		
		setDominionButton = function(){
			var n = $scope.expansions.length;
			$scope.expStyle = new Array(n).fill("");
			$scope.expStyle[0] = {
				"color": "rgb(51,51,51)",
				"background": "rgba(255,255,255,0.8)"
			}	
		}
		
		getSelected = function(){
			$http.post('/dominionlist/getselected').then(function(response){
				$scope.selected = response.data;
				$http.post('/dominionlist/getexpcards').then(function(response){
					var expcards = response.data;
					$scope.used = new Array();
					$scope.usedExp = new Array();
					$scope.usedIndex = new Array();
					var i,j;
					for (i=0;i<expcards.length;i++){
						$scope.used.push($scope.expansions[expcards[i]].expCardImage);
					}
					for (i=0;i<$scope.selected.length;i++){
						for (j=0;j<$scope.selected[i].length;j++){
							if ($scope.selected[i][j] == 2){
								$scope.used.push($scope.expansions[i].piles[j].image);
								$scope.usedExp.push(i);
								$scope.usedIndex.push(j);
							}
						}
					}
				});
			});
		}
		
		getAllCards = function(){
			$http.post('/dominionlist/allcards').then(function(response){
				$scope.expansions=response.data;
				setDominionButton();
				getSelected();
			});
		}
		
		getAllCards();
		
		$scope.cardList = function(){
			if ($scope.showCardList){
				$scope.showCardList = false;
				expansionShow = [];
			} else {
				$scope.showCardList = true;
				$scope.cardListDivStyle = {
					"position": "absolute",
					"left": "0%",
					"top": "0%",
					"height": "100%",
					"width": "100%",
				}
				$scope.expansionDivStyle = {
					"left": "10%",
					"margin-left": "10%",
					"top": "10%",
					"height": "80%",
					"width": "80%",
				}
				//$scope.includeAll = true;
				var n = $scope.expansions.length;
				expansionShow = new Array(n).fill(false);
				expansionShow[0] = true;
				var m = $scope.expansions[0].piles.length;
				$scope.cardStyle = new Array(m);
				var i;
				for (i=0;i<m;i++){
					var tjsonObj = {
						"height": "120px", 
						"width": "80px", 
						"position": "relative",
						"background": "url(" + $scope.expansions[0].piles[i].image + ")", 
						"background-size": "cover"
					}
					$scope.cardStyle[i] = tjsonObj;
				}
				$scope.changeExpansion(0);
			}
		}
		
		$scope.showExpansion = function(index){
			if (expansionShow.length <= index) return false;
			return expansionShow[index];
		}
		
		$scope.changeExpansion = function(index){
			var n = $scope.expansions.length;
			expansionShow = new Array(n).fill(false);
			
			expansionShow[index] = true;
			var m = $scope.expansions[index].piles.length;
			$scope.cardStyle = new Array(m);
			var i;
			for (i=0;i<m;i++){
				var tjsonObj = {
					"height": "120px", 
					"width": "80px", 
					"position": "relative",
					"background": "url(" + $scope.expansions[index].piles[i].image + ")", 
					"background-size": "cover"
				}
				$scope.cardStyle[i] = tjsonObj;
			}
			$scope.expStyle = new Array(n).fill("");
			$scope.expStyle[index] = {
				"color": "rgb(51,51,51)",
				"background": "rgba(255,255,255,0.8)"
			}
		}
		
		$scope.use = function(k, key){
			if ($scope.selected[k][key] == 2){
				
			} else if ($scope.used.length == 10){
				
			} else if (!$scope.isLord){
				
			} else {
				$scope.selected[k][key] = 2;
				$scope.used.push($scope.expansions[k].piles[key].image);
				$scope.usedExp.push(k);
				$scope.usedIndex.push(key);
			}
		}
		
		$scope.unuse = function(index){
			$scope.selected[$scope.usedExp[index]][$scope.usedIndex[index]] = 0;
			$scope.used.splice(index,1);
			$scope.usedExp.splice(index,1);
			$scope.usedIndex.splice(index,1);
			
		}
		
		$scope.usedCard = function(index){
			var image = "/image/Dominion/cards/Card_back.png";
			if (index < $scope.used.length){
				image = $scope.used[index];
			}
			var tjsonObj = {
				"height": "120px", 
				"width": "80px", 
				"position": "relative",
				"background": "url(" + image + ")", 
				"background-size": "cover"
			}
			return tjsonObj;
		}
		
		$scope.disableExclude = function(k){
			if ($scope.selected == undefined) return true;
			if ($scope.selected.length == 0) return true;
 			var t = 0;
			for (i=0;i<$scope.usedExp.length;i++){
				if ($scope.usedExp[i] == k  && $scope.usedIndex[i] == -1){
					t = t+1;
				}
			}
			if (t>0) return true; else return false;
		}
		
		$scope.disableCheck = function(k,key){
			if ($scope.selected == undefined) return true;
			if ($scope.selected.length == 0) return true;
			if ($scope.selected[k][key] == 1) return false;
			var i;
			var total = 0;
			for (i=0;i<$scope.selected[k].length;i++){
				if ($scope.selected[k][i] == 0){
					total = total+1;
				}
			}
			var t = 0;
			for (i=0;i<$scope.usedExp.length;i++){
				if ($scope.usedExp[i] == k  && $scope.usedIndex[i] == -1){
					t = t+1;
				}
			}
			if (t>=total) return true; else return false;
		}
		
		$scope.check = function(k,key){
			if ($scope.selected[k][key] == 2){
				
			} else {
				$scope.selected[k][key] = 1 - $scope.selected[k][key];
			} 
		}
		
		$scope.includeAll = function(k){
			var i;
			for (i=0;i<$scope.selected[k].length;i++){
				if ($scope.selected[k][i]!=2){
					$scope.selected[k][i] = 0;
				}
			}
		}
		
		$scope.excludeAll = function(k){
			var i;
			for (i=0;i<$scope.selected[k].length;i++){
				if ($scope.selected[k][i]!=2){
					$scope.selected[k][i] = 1;
				}
			}
		}
		
		$scope.addOne = function(k){
			$scope.used.push($scope.expansions[k].expCardImage);
			$scope.usedExp.push(k);
			$scope.usedIndex.push(-1);
		}
		
		$scope.disableAddOne = function(k){
			if ($scope.selected == undefined) return true;
			if ($scope.selected.length == 0) return true;
			var i;
			var total = 0;
			for (i=0;i<$scope.selected[k].length;i++){
				if ($scope.selected[k][i] == 0){
					total = total+1;
				}
			}
			var t = 0;
			for (i=0;i<$scope.usedExp.length;i++){
				if ($scope.usedExp[i] == k && $scope.usedIndex[i] == -1){
					t = t+1;
				}
			}
			if (t>=total) return true; else return false;
		}
		
		$scope.confirm = function(){
			var i,j;
			var total = 0;
			for (i=0;i<$scope.selected.length;i++){
				for (j=0;j<$scope.selected[i].length;j++){
					if ($scope.selected[i][j]!=1) total = total+1;
				}
			}
			if (total < 10){
				alert("You have less than 10 available cards!")
			} else {
				var x = new Array();
				for (i=0;i<$scope.usedExp.length;i++){
					if ($scope.usedIndex[i] == -1){
						x.push($scope.usedExp[i]);
					}
				}
				if (x.length == 0) x = "NONE";
				var data = {"selectedJson": $scope.selected,"usedExp": x};
				$http({url: "/dominionlist/setselected", method: "POST", params: data}).then(function(response){
					$http({url: "/dominiongame/randomize", method: "POST"}).then(function(response){
						$scope.cardList();
					});
				});
			}
		}
		
		$scope.close = function(){
			$http.post('/dominionlist/getselected').then(function(response){
				$scope.selected = response.data;
				$scope.used = new Array();
				$scope.usedExp = new Array();
				$scope.usedIndex = new Array();
				var i,j;
				for (i=0;i<$scope.selected.length;i++){
					for (j=0;j<$scope.selected[i].length;j++){
						if ($scope.selected[i][j] == 2){
							$scope.used.push($scope.expansions[i].piles[j].image);
							$scope.usedExp.push(i);
							$scope.usedIndex.push(j);
						}
					}
				}
				$scope.cardList();
			});
		}
		
}]);
