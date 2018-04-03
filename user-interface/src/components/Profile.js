import React from 'react';

const Profile = (props) => {

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
        <div style={profileWrapperStyle}>
            <div style={userStyle}>
                <div id={`user-${props.user.id}-user-name`}>
                    Username: {props.user.userName}
                </div>

                <div id={`user-${props.user.id}-first-name`}>
                    First Name: {props.user.firstName}
                </div>

                <div id={`user-${props.user.id}-last-name`}>
                    Last Name: {props.user.lastName}
                </div>

                <button
                    id={`delete-user-${props.user.id}`}
                    onClick={() => {props.deleteUser(props.user.id)}}>
                    <a href='/'>Delete</a>
                </button>
            </div>
        </div>
    );
}

export default Profile;