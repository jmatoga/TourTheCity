import * as React from "react";
import { request } from "./axios_helper.js";

export default class AuthContent extends React.Component {
  constructor(props) {
    super(props);
    this.state = {
      data: [],
    };
  }

  componentDidMount() {
    request("get", "/auth/messages", {})
      .then((response) => {
        this.setState({ data: response.data });
      })
      .catch((error) => {
        console.log(error);
      });
  }

  render() {
    return (
      <div>
        <h1>wwwMessagesdsffffdddeeex</h1>
        <ul>
          {this.state.data &&
            this.state.data.map((line) => {
              return <p>{line}</p>;
            })}
        </ul>
        componentDidMount();
      </div>
    );
  }
}
