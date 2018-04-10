import React, { Component } from 'react'
import {Redirect} from "react-router-dom";

class CreateForm extends Component {

    constructor(props) {
        super(props);
        this.state = {
            user: {admin: false}
        }
    }

    handleSubmit = (event) => {
        event.preventDefault();
        this.props.createUser(this.state.user, this.props.login)
    }

    handleChange = (event) => {
        const attributeToChange = event.target.name
        const newValue = event.target.type === 'checkbox' ? event.target.checked : event.target.value;
        const updatedUser = { ...this.state.user }
        updatedUser[attributeToChange] = newValue
        this.setState({ user: updatedUser })
    }

    render() {

        const userTitleStyle = {
            'textAlign': 'center',
            'fontFamily': 'Comfortaa, cursive',
            'fontWeight': 'bold'
        }
    
        const formStyle = {
            'textAlign': 'center',
            'fontFamily': 'Comfortaa, cursive'
        }

        return (
            <div>
                <h2 style={userTitleStyle}>Create An Account</h2>
                <form onSubmit={this.handleSubmit} id="create-account-form" style={formStyle}>
                    <div>
                        <label htmlFor="firstName">First Name </label>
                        <input
                            id="create-first-name"
                            type="text"
                            name="firstName"
                            onChange={this.handleChange} required/>
                    </div>
                    <div>
                        <label htmlFor="lastName">Last Name </label>
                        <input
                            id="create-last-name"
                            type="text"
                            name="lastName"
                            onChange={this.handleChange} required/>
                    </div>
                    <div>
                        <label htmlFor="userName">Username </label>
                        <input
                            id="create-user-name"
                            type="text"
                            name="userName"
                            onChange={this.handleChange} required/>
                    </div>
                    <div>
                        <label htmlFor="password">Password </label>
                        <input
                            id="create-password"
                            type="password"
                            name="password"
                            onChange={this.handleChange} required/>
                    </div>
                    <div>
                        <label htmlFor="admin">Admin </label>
                        <input
                            id="create-admin"
                            type="checkbox"
                            name="admin"
                            onChange={this.handleChange} />
                    </div>
                    <div>
                        <input
                            id="create-submit"
                            type="submit"
                            value="Create" />
                    </div>
                </form>
            </div>
        )
    }

}

export default CreateForm