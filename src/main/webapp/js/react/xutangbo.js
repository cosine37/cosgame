function ShowRule(props){
	return <div>rules rules rules</div>
}

function ShowGame(props){
	return <div>games games games</div>
}

function RuleOrGame(props){
	if (props.tabName == 'rule'){
		return ShowRule();
	} else if (props.tabName == 'game'){
		return ShowGame();
	} else {
		return <div></div>
	}
}

class Middle extends React.Component {
	constructor(props) {
	    super(props);
	    this.state = {games: [], ingame: false, start: false, players: []};
	    this.create = this.create.bind(this);
	    this.enter = this.enter.bind(this);
	    this.addBot = this.addBot.bind(this);
	    this.kick = this.kick.bind(this);
	    this.start = this.start.bind(this);
	    this.getAllGames(0);
	}
	
	getAllGames(flag){
		var that = this;
		$.get("/minigame/xutangbo/games", function(data,status){
	    	var i;
	    	var groupSize = 3;
	    	var tgames = [];
	    	for (i=0; i<data.value.length/groupSize;i++){
	    		var t = []
	    		t.push(data.value[i*groupSize]);
	    		t.push(data.value[i*groupSize+1]);
	    		tgames.push(t);
	    	}
	    	that.setState(prevState => ({
	    		games: tgames,
	  	  	}));
	    	if (flag == 1){
	    		that.getGame();
	    	}
		});
	}
	
	getGame(){
		var that = this;
		$.get("/minigame/xutangbo/players", function(data,status){
			that.setState(prevState => ({
				players: data.value
			}));
		});
	}
	
	create(){
		var that = this;
		$.post("/minigame/xutangbo/new", function(data,status){
			that.setState(prevState => ({
				ingame: true,
			}));
			that.getAllGames(1);
		})
	}
	
	enter(){
		
	}
	
	addBot(){
		var that = this;
		$.post("/minigame/xutangbo/addbot", function(data,status){
			that.getGame();
		});
	}
	
	kick(name){
		var that = this;
		$.post("/minigame/xutangbo/kick", {kickedName: name}, function(data,status){
			that.getGame();
		});
	}
	
	start(){
		var that = this;
		$.post("/minigame/xutangbo/start", function(data,status){
			that.setState(prevState => ({
	    		start: true
	  	  	}));
		});
	}
	
	render(){
		var rightHTML;
		if (this.state.start){
			rightHTML = (
				<div id="right" className="right">
					<h2>game starts</h2>
				</div>
			);
		} else if (this.state.ingame){
			rightHTML = (
				<div id="right" className="right">
					<h2>players</h2>
					<button onClick={this.addBot}>Add bot</button>
					{this.state.players.length > 1 &&
						<button onClick={this.start}>Start</button>
					}
					<table>
						{this.state.players.map(player =>(
							<tr>
								<td>{player}</td>
								<td><button onClick={() => this.kick(player)}>kick</button></td>
							</tr>
						))}
					</table>
				</div>
			);
		} else {
			rightHTML = (
				<div id="right" className="right">
				</div>
			);
		}
		
		return (
			<div id="middle">
				<div id="left" className="left">
					<h3>Rooms</h3>
					<table>
						<tr>
							<td>Room ID</td>
							<td>Lord</td>
							<td><button disabled={this.state.ingame} onClick={this.create}>Create</button></td>
						</tr>
						{this.state.games.map(item => (
							<tr>
								<td>{item[0]}</td>
								<td>{item[1]}</td>
								<td><button disabled={this.state.ingame}>Enter</button></td>
							</tr>	
						))}
					</table>
				</div>
				{rightHTML}
			</div>
		);
	}
}
ReactDOM.render(
  <Middle />,
  document.getElementById("middle")
);
