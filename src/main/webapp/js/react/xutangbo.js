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

function Right(props){
	if (props.info.ingame){
		return <div>games games games</div>
	} else {
		return <div></div>
	}
}

class Middle extends React.Component {
	constructor(props) {
	    super(props);
	    this.state = {games: [], ingame: false};
	    this.create = this.create.bind(this);
	    this.enter = this.enter.bind(this);
	    this.getAllGames();
	}
	
	getAllGames(){
		var that = this;
		$.get("/minigame/xutangbogames", function(data,status){
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
	
	create(){
		var that = this;
		$.post("/minigame/xutangbonew", function(data,status){
			that.setState(prevState => ({
				ingame: true
			}));
			that.getAllGames();
		})
	}
	
	enter(){
		
	}
	
	
	render(){
		return (
			<div>
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
				<Right info={this.state}/>
			</div>
		);
	}
}
/*
class Right extends React.Component {
  constructor(props) {
    super(props);
    this.state = {tabName: 'rule'};
    
    this.rule = this.rule.bind(this);
    this.game = this.game.bind(this);
  }

  rule(){
	  this.setState(prevState => ({
		  tabName: 'rule'
	  }));
  }
  
  game(){
	  this.setState(prevState => ({
	      tabName: 'game'
	  }));
  }
  
  RuleOrGame(){
	  if (this.state.tabName == 'rule'){
		  return ShowRule();
	  } else if (this.state.tabName == 'game'){
		  return ShowGame();
	  } else {
		  return (
			<div></div>
		  )
	  }
  }
  
  render() {
    return (
    	<div className="right">
	    	<div id="righttabs" className="righttabs">
				<button onClick= {this.rule} >rule</button>
				<button onClick= {this.game} >game</button>
			</div>
			<RuleOrGame tabName={this.state.tabName}/>
    	</div>
    );
  }
}
*/
ReactDOM.render(
  <Middle />,
  document.getElementById("middle")
);
