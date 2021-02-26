import React, {Component} from "react";

class Administrations extends Component {

    constructor() {
        super();

        this.state = {
            administrations: [],

        }
    }

    componentDidMount() {

        this.fetchAdministrations()
    }

    fetchAdministrations() {

        let body = {
            "drugId": 10,
            "userId": 17
        }

        let method_properties = {
            method: "POST",
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(body)
        }

        //TODO change address to environment var
        fetch("http://localhost:8080/api/administrations", method_properties)
            .then((response) => {this.setState({administrations: response});
                                        console.log(this.state)})

    }

    render() {
        return(<h1>check console</h1>);
    }

}

export default Administrations