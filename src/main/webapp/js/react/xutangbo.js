var interval;

class Middle extends React.Component {
	
	constructor(props) {
	    super(props);
	    this.state = {games: [], ingame: false, start: false, game: {dead: false, players: []}};
	    this.create = this.create.bind(this);
	    this.enter = this.enter.bind(this);
	    this.addBot = this.addBot.bind(this);
	    this.kick = this.kick.bind(this);
	    this.start = this.start.bind(this);
	    this.useMove = this.useMove.bind(this);
	    this.getGame = this.getGame.bind(this);
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
	    		games: tgames
	  	  	}));
		});
	}
	
	getGame(){
		var that = this;
		if (this.state.ingame && this.state.game.status != 2){
			$.get("/minigame/xutangbo/getgame", function(data,status){
				that.setState(prevState => ({
					game: data
				}));
				if (that.state.game.dead){
					clearInterval(interval);
					interval = setInterval((that.getGame), 2000);
				}
			});
		}
		
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
			//that.getGame();
		});
	}
	
	kick(name){
		var that = this;
		$.post("/minigame/xutangbo/kick", {kickedName: name}, function(data,status){
			//that.getGame();
		});
	}
	
	start(){
		var that = this;
		$.post("/minigame/xutangbo/start", function(data,status){
			that.setState(prevState => ({
	    		start: true
	  	  	}));
			//that.getGame();
		});
	}
	
	useMove(id){
		var that = this;
		$.post("/minigame/xutangbo/usemove", {moveId: id}, function(data,status){
			//that.getGame();
		});
	}
	
	gameLayout(){
		return (
			<div id="right" className="right">
				<div id="playersinfo" className="playersinfo">
					<h2>Player info</h2>
					<table>
						{this.state.game.players.map(player => (
							<tr>
								<td>
									{player.name} {player.statusStr}
								</td>
								<td>energy: {player.energy}</td>
								<td>bi: {player.bi}</td>
							</tr>
						))}
					</table>
				</div>
				<div id="logs" className="logs">
					<h2>Logs</h2>
					<table>
						{this.state.game.logs.map(log => (
							<tr>
								<td>{log}</td>
							</tr>
						))}
					</table>
				</div>
				<div id="moves" className="moves">
					<h2>Moves</h2>
					<button onClick={() => this.useMove(1)} disabled={this.state.game.disableMove}>bi</button>
					<button onClick={() => this.useMove(0)} disabled={this.state.game.disableMove}>蓄</button>
					<button onClick={() => this.useMove(2)} disabled={this.state.game.disableMove}>镗</button>
					<button onClick={() => this.useMove(3)} disabled={this.state.game.disableMove}>波</button>
					<button onClick={() => this.useMove(4)} disabled={this.state.game.disableMove}>大镗</button>
					<button onClick={() => this.useMove(5)} disabled={this.state.game.disableMove}>中波</button>
					<button onClick={() => this.useMove(6)} disabled={this.state.game.disableMove}>强烈镗</button>
					<button onClick={() => this.useMove(7)} disabled={this.state.game.disableMove}>大波</button>
					<button onClick={() => this.useMove(8)} disabled={this.state.game.disableMove}>波霸</button>
					<button onClick={() => this.useMove(9)} disabled={this.state.game.disableMove}>究极波</button>
				</div>
			</div>
		);
	}
	
	render(){
		var rightHTML;
		if (this.state.start){
			rightHTML = this.gameLayout();
		} else if (this.state.ingame){
			rightHTML = (
				<div id="right" className="right">
					<h2>players</h2>
					<button onClick={this.addBot}>Add bot</button>
					{this.state.game.players.length > 1 &&
						<button onClick={this.start}>Start</button>
					}
					<table>
						{this.state.game.players.map(player =>(
							<tr>
								<td>{player.name}</td>
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
	
	componentDidMount(){
		this.getGame();
		interval = setInterval((this.getGame), 200);
	}
}
ReactDOM.render(
  <Middle />,
  document.getElementById("middle")
);
