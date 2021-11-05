function setStyleByType(cardDisplay){
	var c = cardDisplay.card
	if (c.type == "放置"){
		cardDisplay.showShield = true
		cardDisplay.img = {
			"border": "2px solid white"
		}
		cardDisplay.base = {
			"background-color": "darkslategrey"
		}
		cardDisplay.clanText = {
			"width": "130px",
			"right": "30px"
		}
		cardDisplay.type = {
			"background": "darkblue",
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
	
	if (cardDisplay.showSun && cardDisplay.showPea){
		cardDisplay.sun = {
			"top": "0px",
			"height": "40px",
			"width": "40px",
			"left": "65px"
		}
		cardDisplay.sunValue = {
			"width": "40px",
			"font-size": "24px"
		}
		cardDisplay.pea = {
			"top": "50px",
			"height": "40px",
			"width": "40px",
			"left": "65px"
		}
		cardDisplay.peaValue = {
			"width": "40px",
			"font-size": "24px"
		}
	} else if (cardDisplay.showSun){
		cardDisplay.sun = {
			"top": "20px"
		}
		if (cardDisplay.showDescText){
			cardDisplay.sun = {
				"top": "0px",
				"height": "40px",
				"width": "40px",
				"left": "65px"
			}
			cardDisplay.sunValue = {
				"width": "40px",
				"font-size": "24px"
			}
			cardDisplay.descText["top"] = "50px"
		}
	} else if (cardDisplay.showPea){
		cardDisplay.pea = {
			"top": "0px",
			"height": "40px",
			"width": "40px",
			"left": "65px"
		}
		cardDisplay.peaValue = {
			"width": "40px",
			"font-size": "24px"
		}
		cardDisplay.descText["top"] = "50px"
	} else {
		if (c.desc.length>30){
			cardDisplay.descText["top"] = "0px"
		} else if (c.desc.length>20){
			cardDisplay.descText["top"] = "7px"
		} else {
			cardDisplay.descText["top"] = "15px"
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
	if (x == 's'){
		ans = ans + "'sun-in-text'>"
	} else if (x == 'p'){
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
		if (s[i] == 's' || s[i] == 'p') {
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
	var desc = cardDisplay.card.desc.toString()
	desc = renderSunPea(desc)
	if (cardDisplay.showShield && c.taunt == true){
		desc = "<b class='taunt'>嘲讽</b><br>"+desc
	}
	if (cardDisplay.showShield){
		desc = smartReplace(desc, "消耗", "<b class='orange'>消耗</b>")
		desc = smartReplace(desc, "放置", "<b class='blue'>放置</b>")
		desc = smartReplace(desc, "爆破", "<b class='red'>爆破</b>")
		desc = smartReplace(desc, "触发", "<b class='activate'>触发</b>")
	} else {
		desc = smartReplace(desc, "消耗", "<b class='dark-orange'>消耗</b>")
		desc = smartReplace(desc, "放置", "<b class='dark-blue'>放置</b>")
		desc = smartReplace(desc, "爆破", "<b class='red'>爆破</b>")
	}
	//alert(desc)
	cardDisplay.card.desc = desc
}


function buildCard(c){
	var cardDisplay = {}
	cardDisplay.card = c
	cardDisplay.plant = "/image/Gardenwar/gen1/" + c.img
	cardDisplay.base = {
		"background-color": "beige"
	}
	cardDisplay.name = {
		"background-color": "lightgrey"
	}
	cardDisplay.clan = {
		"background-color": "lightgrey"
	}
	cardDisplay.descText = {
		"color": "black"
	}
	cardDisplay.showShield = false
	setStyleByType(cardDisplay)
	setDescStyle(cardDisplay)
	renderKeywords(cardDisplay)
	return cardDisplay
}
