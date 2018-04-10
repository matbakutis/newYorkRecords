import React from 'react'
import User from './User'
import {Link} from "react-router-dom";
import CreateForm from './CreateForm';

const UsersList = (props) => {

    const userTitleStyle = {
        'textAlign': 'center',
        'fontFamily': 'Comfortaa, cursive',
        'fontWeight': 'bold'
    }

    const userCreateStyle = {
        'fontFamily': 'Comfortaa, cursive',
        'color': 'black'
    }

    const usersWrapperStyle = {
        'textAlign': 'center'
    }

    return (
        <div id="users-wrapper" style={usersWrapperStyle}>
            <h1 style={userTitleStyle}>Users</h1>
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
            <CreateForm createUser={props.createUser} login={false} />
        </div>
    )
}

export default UsersList