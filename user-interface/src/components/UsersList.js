import React, { Component } from 'react'
import User from './User'
import {Link} from "react-router-dom";

const UsersList = (props) => {

    const userTitleStyle = {
        'textAlign': 'center',
        'font-family': 'Comfortaa, cursive',
        'font-weight': 'bold'
    }

    const userCreateStyle = {
        'font-family': 'Comfortaa, cursive',
        'color': 'black'
    }

    const usersWrapperStyle = {
        'textAlign': 'center'
    }

    return (
        <div id="users-wrapper" style={usersWrapperStyle}>
            <h1 style={userTitleStyle}>Users</h1>

            <Link to="/new" id="new-user-link" style={userCreateStyle}>Create New User</Link>
            
            {
                props.users.map((user, index) => {
                    return (
                        <User
                            deleteUser={props.deleteUser}
                            user={user}
                            key={index}
                            index={index} />
                    )
                })
            }
        </div>
    )
}

export default UsersList