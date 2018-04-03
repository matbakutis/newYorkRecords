import React, { Component } from 'react';

const Profile = (props) => {
    return (
        <div>
            <div id={`user-${props.user.id}-user-name`}>
                {props.user.userName}
            </div>

            <div id={`user-${props.user.id}-first-name`}>
                {props.user.firstName}
            </div>

            <div id={`user-${props.user.id}-last-name`}>
                {props.user.lastName}
            </div>

            <button
                id={`delete-user-${props.user.id}`}
                onClick={() => {props.deleteUser(props.user.id)}}>
                <a href='/'>Delete</a>
            </button>
        </div>
    );
}

export default Profile;