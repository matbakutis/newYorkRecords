import React, {Component} from 'react';
import { Redirect } from "react-router-dom";

class Profile extends Component {
    constructor(props){
        super(props);
        this.state = {

        }
    }

    render() { 
        const userStyle = {
            'fontFamily': 'Comfortaa, cursive',
            'fontWeight': 'light',
            'color': 'black',
            'marginTop': '30px'
        }

        const profileWrapperStyle = {
            'textAlign': 'center'
        }

        return (
            <div style={profileWrapperStyle} id="profile-wrapper">
                <div style={userStyle}>
                    <div id={`user-${this.props.user.id}-user-name`}>
                        Username: {this.props.user.userName}
                    </div>

                    <div id={`user-${this.props.user.id}-first-name`}>
                        First Name: {this.props.user.firstName}
                    </div>

                    <div id={`user-${this.props.user.id}-last-name`}>
                        Last Name: {this.props.user.lastName}
                    </div>

                    <button
                        id={`delete-user-${this.props.user.id}`}
                        onClick={() => {this.props.deleteUser(this.props.user.id)}}>
                        <a href='/'>Delete</a>
                    </button>
                </div>
            </div>
        );
    }
}

export default Profile;