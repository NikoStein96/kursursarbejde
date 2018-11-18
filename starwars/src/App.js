import React, { Component } from "react"
import facade from "./apiFacade";
import LoggedIn from "./components/LoggedIn";
import LogIn from "./components/LogIn";

class App extends Component {
  constructor(props) {
    super(props);
    this.state = { loggedIn: false }
  }
  logout = () => {facade.logout();
    this.setState({ loggedIn: false });} //TODO
  login = (user, pass) => {facade.login(user,pass)
    .then(res =>this.setState({ loggedIn: true }));} //TODO
  render() {
    return (
      <div>

        {!this.state.loggedIn ? (<LogIn login={this.login} />) :
          (<div>
            <LoggedIn />
            <button onClick={this.logout}>Logout</button>
          </div>)}
      </div>
    )
  }
}
export default App;