window.onload = function(){
	var n = 3;
	var tArray = new Array(n);
	tArray[0] = "/sound/BGM/TritschTratschPolka.mp3";
	tArray[1] = "/sound/BGM/RondoAllaTurca.mp3";
	tArray[2] = "/sound/BGM/RadetzkyMarsch.mp3";
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
		//var source = this.getAttribute("src");
		//var getPos = bgmArray.indexOf(source);
		index = index + 1;
		
		if (index == n) index = 0;
		bgm.src = bgmArray[index];
		bgm.play();
	});
	
}