import React, { Component } from 'react';

class ForumPost extends Component {
    render() {

        const postStyle = {
            'width': '75%',
            'margin': '15px auto',
            'minHeight': '50px',
            'backgroundColor': 'Grey',
            'borderRadius': '10px',
            'fontFamily': 'Comfortaa, cursive',
            'paddingBottom': '15px',
            'textAlign': 'left'
        }

        const postTitleStyle = {
            'display': 'flex',
            'flexWrap': 'wrap',
            'alignItems': 'center',
            'justifyContent': 'space-between',
            'margin': '15px 15px 0 15px',
            'fontFamily': 'Comfortaa, cursive'
        }

        const hrStyle = {
            'border': 0,
            'height': '1px',
            'backgroundImage': '-webkit-linear-gradient(left, grey, black, grey)' 
        }

        const postContentStyle = {
            'margin': '15px',
            'fontFamily': 'Comfortaa, cursive',
        }

        return (
            <div id={`post-${this.props.post.id}`} style={postStyle}>
                <div style={postTitleStyle}>
                    <h3>{this.props.post.title}</h3>
                    <p>{this.props.post.username}</p>
                </div>
                <hr style={hrStyle}/> 
                <p style={postContentStyle}>{this.props.post.content}</p>
            </div>
        );
    }
}

export default ForumPost;