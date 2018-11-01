import React from "react";
import ReactDOM from "react-dom";
import SampleComponent from "./component/SampleConponent";
import Square from "./component/Square";
import Input from "./component/InputSample";
import Clock from "./component/Clock";

const Index = () => {
    return <div>
        <h1>React is awesome!</h1>
        <Square value="From Tag"/>
    </div>;
};

const App = (props) => {
    return (
        <div>
            <Index/>
            <SampleComponent name={props.author.name}/>
        </div>
    );
};

const book = {
    name: "My Apocalypse",
    author: {
        name: "Oleg Baskov",
        birthday: "10/21/1962"
    }
};

const secondAuthor = {
    name:"Nickola"
};

ReactDOM.render(<Clock />, document.getElementById("clock"));

ReactDOM.render(<Input/>, document.getElementById("input"));
ReactDOM.render(<App author={book.author}/>, document.getElementById("react1"));
ReactDOM.render(<App author={secondAuthor}/>, document.getElementById("react2"));