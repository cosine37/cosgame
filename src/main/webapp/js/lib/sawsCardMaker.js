function setNameStyle(cardDisplay){
	var c = cardDisplay.card
	cardDisplay.name = {}
	if (c.level == 1){
		cardDisplay.name = {
			"background-color": "deepskyblue"
		}
	} else if (c.level == 2){
		cardDisplay.name = {
			"background-color": "gold"
		}
	} else {
		cardDisplay.name = {
			"background-color": "lightgrey"
		}
	}
	
	if (c.name.length>5){
		cardDisplay.name["font-size"] = "26px"
		cardDisplay.name["line-height"] = "30px"
	}
}
function setStyleByType(cardDisplay){
	var c = cardDisplay.card
	if (c.type == "名流"){
		cardDisplay.showShield = true
		cardDisplay.img = {
			"background-image" : "url('/image/Gardenwar/saws/" + c.img + ".png')",
			"border": "2px solid white"
		}
		cardDisplay.base = {
			"background-color": "saddlebrown"
		}
		cardDisplay.clanText = {
			"width": "130px",
			"right": "30px"
		}
		cardDisplay.type = {
			"background": "darkslategrey",
			"color": "white",
			"border": "2px solid white"
		}
		cardDisplay.descText = {
			"color": "white"
		}
	}
}

function setDescStyle(cardDisplay){
	var c = cardDisplay.card
	cardDisplay.showDescText = false;
	cardDisplay.showSun = false;
	cardDisplay.showPea = false;
	if (c.desc != null && c.desc.length > 0){
		cardDisplay.showDescText = true;
	}
	if (c.sun != null && c.sun != 0){
		cardDisplay.showSun = true;
	}
	if (c.pea != null && c.pea != 0){
		cardDisplay.showPea = true;
	}
	
	if (cardDisplay.showSun || cardDisplay.showPea){
		cardDisplay.descText["top"] = "40px"
		if (c.desc == null || c.desc.length == 0){
			cardDisplay.sun = {
				"height": "50px",
				"width": "50px",
				"font-size": "32px"
				
			}
			cardDisplay.pea= {
				"height": "50px",
				"width": "50px",
				"font-size": "32px"
			}
			cardDisplay.vanilla = {
				"top": "30px"
			}
		}
	} else {
		if (c.desc !== null && c.desc.length>30){
			cardDisplay.descText["top"] = "0px"
		} else if (c.desc !== null && c.desc.length>20){
			cardDisplay.descText["top"] = "7px"
		} else if (c.desc !== null && c.desc.length>10){
			cardDisplay.descText["top"] = "15px"
		} else if (c.desc !== null){
			cardDisplay.descText["top"] = "25px"
		}
	}
	
}

function smartReplace(s, s1, s2){
	var i=0;
	var ans =""
	var n = s.length
	var x = s1.length
	while (i<n){
		if (i+x > n) {
			ans = ans+s[i]
			i++
		} else {
			var ts = s.substring(i,i+x);
			if (ts == s1){
				ans = ans + s2;
				i = i+x;
			} else {
				ans = ans+s[i]
				i++
			}
		}
	}
	return ans
}

function renderSingleSunPea(x,n){
	var ans = "<span class=";
	if (x == 'c'){
		ans = ans + "'sun-in-text'>"
	} else if (x == 'a'){
		ans = ans + "'pea-in-text'>"
	} else {
		return "";
	}
	if (n>='0' && n<='9'){
		ans = ans + n + "</span>"
	} else if (n == 'x'){
		ans = ans + "&nbsp;</span>"
	} else {
		ans = ans + "&nbsp;</span>" + n
	}
	return ans
}

function renderSunPea(s){
	var i=0;
	var ans = ""
	var n = s.length;
	for (i=0;i<n;i++){
		if (s[i] == 'c' || s[i] == 'a') {
			var x = i+1;
			if (x<n){
				ans = ans+renderSingleSunPea(s[i],s[x])
				i=i+1
			} else {
				ans = ans+renderSingleSunPea(s[i],'x')
			}
		} else {
			ans = ans+s[i]
		}
	}
	return ans
}

function renderKeywords(cardDisplay){
	var c = cardDisplay.card
	var desc = "";
	if (cardDisplay.card.desc != null){
		desc = cardDisplay.card.desc.toString()
	}
	desc = renderSunPea(desc)
	if (cardDisplay.showShield && c.taunt == true){
		desc = "<b class='taunt'>嘲讽</b><br>"+desc
	}
	if (cardDisplay.showShield){
		desc = smartReplace(desc, "消耗", "<b class='orange'>消耗</b>")
		desc = smartReplace(desc, "放置", "<b class='blue'>放置</b>")
		desc = smartReplace(desc, "抽牌", "<b class='blue'>抽牌</b>")
		desc = smartReplace(desc, "爆破", "<b class='red'>爆破</b>")
		desc = smartReplace(desc, "触发", "<b class='activate'>触发</b>")
	} else {
		desc = smartReplace(desc, "消耗", "<b class='dark-orange'>消耗</b>")
		desc = smartReplace(desc, "放置", "<b class='dark-blue'>放置</b>")
		desc = smartReplace(desc, "抽牌", "<b class='blue'>抽牌</b>")
		desc = smartReplace(desc, "爆破", "<b class='red'>爆破</b>")
	}
	//alert(desc)
	cardDisplay.card.desc = desc
}


function buildCard(c, b = false){
	c.inEquipArea = b
	var cardDisplay = {}
	cardDisplay.card = c
	cardDisplay.img = {
		"background-image" : "url('/image/Gardenwar/saws/" + c.img + ".png')"
	}
	cardDisplay.base = {
		"background-color": "beige"
	}
	cardDisplay.clan = {
		"background-color": "lightgrey"
	}
	cardDisplay.descText = {
		"color": "black"
	}
	cardDisplay.showShield = false
	setNameStyle(cardDisplay)
	setStyleByType(cardDisplay)
	setDescStyle(cardDisplay)
	renderKeywords(cardDisplay)
	return cardDisplay
}

