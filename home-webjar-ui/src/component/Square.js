import React from "react";

export default class Square extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            value: "Default",
            isBlank: true
        };
    }

    render() {
        return (
            <button className="square" onClick={() => this.setState({value: "Clicked"})}>
                {this.state.value} - {this.state.isBlank}
            </button>
        );
    }
}