import * as React from "react";

export default class WelcomeContent extends React.Component {

constructor(props){
  super(props);
  this.state = {
    userName: "Student" 
  }
}

changeStateData = () => {
  this.setState({
    userName: this.state.userName === "Student" ? "Gość" : "Student"
  })
}
  render() {
    return (
      <div className="row justify-content-md-center">
        <div className="jumbotron jumbotron-fluid">
          <div className="container">
            <h1 className="display-4">
              Welcome to the city, {this.state.userName}!
            </h1>
            <h2>Your tour starts here!</h2>
            <button className="btn btn-primary m-2"
              onClick={this.changeStateData}>
                Zmien odwiedzającego
            </button>
            <p className="lead">Login to see your tour!</p>
          </div>
        </div>
      </div>
      
      
    );
  }
}
