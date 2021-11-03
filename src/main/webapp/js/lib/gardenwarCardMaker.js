

function buildCard(c){
	var cardDisplay = {}
	cardDisplay.card = c
	if (c.sun != null && c.sun != 0){
		cardDisplay.showSun = true;
	}
	if (c.atk != null && c.atk != 0){
		cardDisplay.showPea = true;
	}
	
	cardDisplay.plant = "/image/Gardenwar/gen1/" + c.img
	
	cardDisplay.base = {
		"background-color": "green"
	}
	
	cardDisplay.img = {
		"background-color": "lightgreen"
	}
	
	cardDisplay.name = {
		"background-color": "lightgrey"
	}
	
	cardDisplay.clan = {
		"background-color": "lightgrey"
	}
	
	return cardDisplay
}
