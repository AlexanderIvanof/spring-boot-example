import React from "react";

class SampleComponent extends React.Component {
    render() {
        return (
            <div className="shopping-list">
                <h1>Shopping list for {this.props.name}</h1>
                <ul>
                    <li>A</li>
                    <li>B</li>
                    <li>C</li>
                    <li>D</li>
                </ul>
            </div>
        );
    }
}

export default SampleComponent;
