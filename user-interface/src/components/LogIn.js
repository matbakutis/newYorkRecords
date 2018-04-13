import React, { Component } from 'react';
import { Redirect } from 'react-router-dom'
import CreateForm from './CreateForm'

class LogIn extends Component {

    constructor() {
        super();
        this.state = {
            user: {}
        }
    }

    handleSubmit = (event) => {
        event.preventDefault();
        this.props.logInUser(this.state.user.userName)
    }

    handleChange = (event) => {
        const attributeToChange = event.target.name
        const newValue = event.target.value

        const updatedUser = { ...this.state.user }
        updatedUser[attributeToChange] = newValue
        this.setState({ user: updatedUser })
    }

    render() {


        const loginTitleStyle = {
            'textAlign': 'center',
            'fontFamily': 'Comfortaa, cursive',
            'fontWeight': 'bold'
        }

        const formStyle = {
            'textAlign': 'center',
            'fontFamily': 'Comfortaa, cursive'
        }
    
        if(this.props.loggedIn) {
            return <Redirect to="/profile" />
        }

        return (
            <div>
                <div>
                    <h1 style={loginTitleStyle}>Log In</h1>
                    {this.props.message ? <h4 id="login-error-message">{this.props.message}</h4> : null}
                    <form onSubmit={this.handleSubmit} id="login-form" style={formStyle}>
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
                                value="Log In"
                                />
                        </div>
                    </form>
                </div>
                <CreateForm createUser={this.props.createUser} login={true}/>
            </div>
        );
    }
}

export default LogIn;