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
		descDivStyle["background-color"] = "darkblue"
	} else if (c.faction == 1){
		cardDisplay.faction="蜀"
		background["background-color"] = "orangered"
		factionStyle["color"] = "gold"
		factionStyle["background-color"] = "orangered"
		descDivStyle["background-color"] = "orangered"
		//factionStyle["border-bottom"] = "2px solid gold"
		//factionStyle["border-right"] = "2px solid gold"
	} else if (c.faction == 2){
		cardDisplay.faction="吴"
	} else if (c.faction == 3){
		cardDisplay.faction="群"
	}
	cardDisplay.background = background
	cardDisplay.factionStyle = factionStyle
	cardDisplay.descDivStyle = descDivStyle
}

function buildCard(c){
	var cardDisplay = {}
	cardDisplay.card = c
	cardDisplay.role = "/image/Threechaodoms/Roles/" + c.img + ".png"
	setFactionStyle(cardDisplay)
	return cardDisplay
}

