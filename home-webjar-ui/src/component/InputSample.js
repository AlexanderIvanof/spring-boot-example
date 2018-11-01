import React from "react";
import Toggle from "./Toggle";

class InputSample extends React.Component {

    constructor(props) {
        super(props);
        this.state = {
            text: ""
        };
    }

    handleChanges(event) {
        this.setState({text: event.target.value})
    }

    render() {
        return (
            <div>
                <input onChange={ event => this.handleChanges(event) }/>
                <div>
                    Something that you've just input ::{this.state.text}::
                </div>
                <Toggle />
            </div>
        );
    }
}

export default InputSample;