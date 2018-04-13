import React, { Component } from 'react'
import {Redirect} from "react-router-dom";

class CreateForm extends Component {

    constructor(props) {
        super(props);
        this.state = {
            post: {}
        }
    }

    handleSubmit = (event) => {
        event.preventDefault();
        this.props.createPost(this.props.user, this.state.post)
    }

    handleChange = (event) => {
        const attributeToChange = event.target.name
        const newValue = event.target.value;
        const updatedPost = { ...this.state.post }
        updatedPost[attributeToChange] = newValue
        this.setState({ post: updatedPost })
    }

    render() {

        const postTitleStyle = {
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
                <h2 style={postTitleStyle}>Create A Forum Post</h2>
                <form onSubmit={this.handleSubmit} id="create-post-form" style={formStyle}>
                    <div>
                        <label htmlFor="title">Title </label>
                        <input
                            id="create-title"
                            type="text"
                            name="title"
                            placeholder="Title"
                            onChange={this.handleChange} required/>
                    </div>
                    <div>
                        <label htmlFor="content">Body </label>
                        <textarea
                            id="create-content"
                            name="content"
                            placeholder="Body"
                            onChange={this.handleChange} required/>
                    </div>
                    <div>
                        <input
                            id="create-submit"
                            type="submit"
                            value="Post" />
                    </div>
                </form>
            </div>
        )
    }

}

export default CreateForm