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

function setDescStyle(cardDisplay){
	var desc = cardDisplay.card.desc;
	desc = smartReplace(desc, "王道", "<b class='gold'>王道</b>")
	desc = smartReplace(desc, "霸道", "<b class='deepskyblue'>霸道</b>")
	
	cardDisplay.card.desc = desc
}

function setFactionStyle(cardDisplay){
	var c = cardDisplay.card
	var background = {}
	var factionStyle = {}
	var descDivStyle = {}
	if (c.faction == 0){
		cardDisplay.faction="魏"
		background["background-color"] = "darkblue"
		factionStyle["color"] = "deepskyblue"
		factionStyle["background-color"] = "darkblue"
		factionStyle["text-shadow"] = "2px 2px blue"
		descDivStyle["background-color"] = "darkblue"
	} else if (c.faction == 1){
		cardDisplay.faction="蜀"
		background["background-color"] = "orangered"
		factionStyle["color"] = "gold"
		factionStyle["background-color"] = "orangered"
		factionStyle["text-shadow"] = "2px 2px red"
		descDivStyle["background-color"] = "orangered"
	} else if (c.faction == 2){
		cardDisplay.faction="吴"
		background["background-color"] = "forestgreen"
		factionStyle["color"] = "lightgreen"
		factionStyle["background-color"] = "forestgreen"
		factionStyle["text-shadow"] = "2px 2px green"
		descDivStyle["background-color"] = "forestgreen"
	} else if (c.faction == 3){
		cardDisplay.faction="群"
		background["background-color"] = "darkslategrey"
		factionStyle["color"] = "lightgrey"
		factionStyle["background-color"] = "darkslategrey"
		factionStyle["text-shadow"] = "2px 2px grey"
		descDivStyle["background-color"] = "darkslategrey"
	}
	cardDisplay.background = background
	cardDisplay.factionStyle = factionStyle
	cardDisplay.descDivStyle = descDivStyle
}

function buildCard(c){
	var cardDisplay = {}
	cardDisplay.card = c
	if (c.blankSpace) return cardDisplay
	cardDisplay.role = "/image/Threechaodoms/Roles/" + c.img + ".png"
	setFactionStyle(cardDisplay)
	setDescStyle(cardDisplay)
	return cardDisplay
}

