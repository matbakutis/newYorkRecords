import React from 'react'

const User = (props) => {


    const userStyle = {
        'font-family': 'Comfortaa, cursive',
        'font-weight': 'light',
        'color': 'black',
        'marginTop': '30px'
    }


    return (
        <div id={`user-${props.user.id}`} data-user-display style={userStyle}>
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
                onClick={() => {props.deleteUser(props.user.id, props.index)}}>
                Delete
            </button>
        </div>
    )
}

export default User