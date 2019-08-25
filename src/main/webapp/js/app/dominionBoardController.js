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
		
		$scope.showCardList = false;
		
		$scope.isLord = false;
		$scope.showBigImage = false;
		//$scope.includeAll = true;
		var s = "";
		
		$scope.kindom = [];
		$scope.allCards = [];
		$scope.cardStyle = [];
		
		var expansionShow = [];
		$scope.selected = [];
		$scope.used = [];
		$scope.usedExp = [];
		$scope.usedIndex = [];
		$scope.useHelperArray = new Array(10);
		
		$http({url: "/dominiongame/islord", method: "POST"}).then(function(response){
			s = response.data.value[0];
			if (s == "Lord") $scope.isLord = true;
		});
	
		$http({url: "/dominiongame/playernames", method: "POST"}).then(function(response){
			$scope.playernames = response.data.value;
			$scope.playernames[0] = $scope.playernames[0] + "(lord)";
		});
		
		$http.post('/dominiongame/playerstatus').then(function(response){
			$scope.playerstatus = response.data.value;
		});
		
		$http.post('/dominiongame/getkindom').then(function(response){
			$scope.kindom=response.data;
		});
		
		$scope.addBot = function() {
			$http({url: "/dominiongame/addbot", method: "POST"}).then(function(response){
				$http({url: "/dominiongame/playernames", method: "POST"}).then(function(response){
					$scope.playernames = response.data.value;
					$scope.playernames[0] = $scope.playernames[0] + "(lord)";
				});
			});
		}
		
		$scope.leave = function() {
			$http({url: "/dominiongame/leaveboard", method: "POST"}).then(function(response){
				$scope.goto("dominion");
			});
		}
		
		$scope.numPlayerOptions = [2,3,4];
		
		$scope.showKick = function(index){
			if (index == 0) return false;
			return $scope.isLord;
		}
		
		$scope.ready = function() {
			if ($scope.playernames.length == $scope.numPlayers){
				$http({url: "/dominiongame/setup", method: "POST"}).then(function(response){
					$scope.goto("dominiongame");
				});
			} else {
				alert("No enough players!");
			}
		}
		
		
		$scope.randomize = function(){
			$http({url: "/dominiongame/randomize", method: "POST"}).then(function(response){
				$http.post('/dominiongame/getkindom').then(function(response){
					$scope.kindom=response.data;
				});
			});
		}
		
		$scope.kick = function(name){
			var data = {"kickedName": name};
			$http({url: "/dominiongame/kick", method: "POST", params: data}).then(function(response){
				$http({url: "/dominiongame/playernames", method: "POST"}).then(function(response){
					$scope.playernames = response.data.value;
					$scope.playernames[0] = $scope.playernames[0] + "(lord)";
				});
			});
		}
		
		$scope.showCard = function(image){
			//alert(image.toString());
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
			$http({url: "/dominiongame/playernames", method: "POST"}).then(function(response){
				$scope.playernames = response.data.value;
				$scope.playernames[0] = $scope.playernames[0] + "(lord)";
				$http.post('/dominiongame/getkindom').then(function(response){
					$scope.kindom=response.data;
				});
			});
			$timeout(function(){
				if ($scope.showCardList == false){
					getAllInfo();
				}
			},1000);
		}
		
		getAllInfo();
		
		getSelected = function(){
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
			});
		}
		
		getAllCards = function(){
			$http.post('/dominionlist/allcards').then(function(response){
				$scope.expansions=response.data;
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
					"background": "rgba(150, 150, 150)"
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
				
			}
		}
		
		$scope.showExpansion = function(index){
			if (expansionShow.length <= index) return false;
			return expansionShow[index];
		}
		
		$scope.changeExpansion = function(index){
			//$scope.includeAll = true;
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
		}
		
		$scope.use = function(k, key){
			if ($scope.selected[k][key] == 2){
				
			} else if ($scope.used.length == 10){
				
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
			var image = "/image/Dominion/cards/Card_back.jpg";
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
				var data = {"selectedJson": $scope.selected};
				$http({url: "/dominionlist/setselected", method: "POST", params: data}).then(function(response){
					$http({url: "/dominiongame/randomize", method: "POST"}).then(function(response){
						$http.post('/dominiongame/getkindom').then(function(response){
							$scope.kindom=response.data;
							$scope.cardList();
						});
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
