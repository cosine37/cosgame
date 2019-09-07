window.onload = function(){
	var xmlhttp = new XMLHttpRequest();
	xmlhttp.onreadystatechange = function(){
		if (xmlhttp.readyState == 4 && xmlhttp.status == 200){
			setbgm(xmlhttp.responseText)
		}
	}
	xmlhttp.open("POST", "/dominiongame/getbgm", true);
	xmlhttp.send();
}
setbgm = function(response){
	var jsobj = JSON.parse(response);
	tArray = jsobj.value;
	var n = tArray.length;
	var bgmArray = new Array(n);
	var i = 0;
	while (tArray.length > 0){
		var x = Math.round(Math.random()*tArray.length);
		bgmArray[i] = tArray[x];
		tArray.splice(x,1);
		i++;
	}
	bgm = document.getElementById("bgm");
	var index = 0;
	bgm.src = bgmArray[0];
	bgm.play();
	bgm.addEventListener("ended", function(){
		index = index + 1;
		if (index == n) index = 0;
		bgm.src = bgmArray[index];
		bgm.play();
	});
}

mute = function(){
	var bgm = document.getElementById("bgm");
	var bgmOff = document.getElementById("mute");
	if (bgm.muted){
		bgm.muted = false;
		bgmOff.innerText = "BGM OFF";
	} else {
		bgm.muted = true;
		bgmOff.innerText = "BGM ON";
	}
}
