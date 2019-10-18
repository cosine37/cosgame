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

class Toggle extends React.Component {
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
    	<div>
	    	<div id="righttabs" className="righttabs">
				<button onClick={this.rule}>rule</button>
				<button onClick={this.game}>game</button>
			</div>
			<RuleOrGame tabName={this.state.tabName}/>
    	</div>
    );
  }
}

ReactDOM.render(
  <Toggle />,
  document.getElementById('right')
);