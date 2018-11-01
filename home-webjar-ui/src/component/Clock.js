import React from "react";

export default class Clock extends React.Component {

    constructor(props) {
        super(props);
        this.state = {date: new Date()};
    }
// this function will be called after component rendered
    componentDidMount() {
        this.timerId = setInterval(
            () => this.tick(),
            1000
        );
    }

    componentWillUnmount() {
        clearInterval(this.timerId)
    }

    tick() {
        this.setState({
            date: new Date()
        });
    }

    render() {
        return (
            <div>
                <h1>My simple clock</h1>
                <h2>It is {(this.props.date || this.state.date).toLocaleTimeString()}.</h2>
            </div>
        );
    }
};