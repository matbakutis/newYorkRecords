import React, { Component } from 'react';
import {Redirect} from "react-router-dom";

class LogIn extends Component {

    constructor() {
        super();
        this.state = {
            user: {},
            redirectToUsersPage: false
        }
    }

    handleSubmit = (event) => {
        event.preventDefault();

        this.props.logInUser(this.state.user.userName)

        this.setState({ redirectToUsersPage: true })
    }

    handleChange = (event) => {
        const attributeToChange = event.target.name
        const newValue = event.target.value

        const updatedUser = { ...this.state.user }
        updatedUser[attributeToChange] = newValue
        this.setState({ user: updatedUser })
    }

    render() {

        if (this.state.redirectToUsersPage) {
            return <Redirect to="/users" />
        }

        return (
            <div>
                <h2>Log In</h2>
                <form onSubmit={this.handleSubmit} id="login-form">
                    <div>
                        <label htmlFor="userName">Username </label>
                        <input
                            id="login-user-name"
                            type="text"
                            name="userName"
                            onChange={this.handleChange} />
                    </div>

                    <div>
                        <label htmlFor="password">Password </label>
                        <input
                            id="login-password"
                            type="password"
                            name="password"
                            onChange={this.handleChange} />
                    </div>

                    <div>
                        <input
                            id="login-submit"
                            type="submit"
                            value="Log In" />
                    </div>
                </form>
            </div>
        );
    }
}

export default LogIn;