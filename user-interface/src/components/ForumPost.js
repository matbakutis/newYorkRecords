import React, { Component } from 'react';

class ForumPost extends Component {
    render() {
        return (
            <div>
                <h3>{this.props.post.title}</h3>
                <p>{this.props.post.username}</p>
                <p>{this.props.post.content}</p>
            </div>
        );
    }
}

export default ForumPost;