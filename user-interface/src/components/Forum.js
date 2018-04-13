import React, { Component } from 'react';
import axios from 'axios'
import ForumPost from './ForumPost'
import CreatePost from './CreatePost'

class Forum extends Component {
    constructor() {
        super();
        this.state = {
            posts: []
        }
    }

    async componentDidMount() {
        this.getPosts()
    }

    getPosts = async () => {
        const postsResponse = await axios.get(`${process.env.REACT_APP_POSTS_API}/`)
        this.setState({
            posts: postsResponse.data
        })
    }

    createPost = async (user, post) => {
        const postWithUser = {
            title: post.title,
            content: post.content,
            userid: user.id,
            username: user.userName
        }
        try {
            const postsResponse = await axios.post(`${process.env.REACT_APP_POSTS_API}/`, postWithUser)
            const newPost = postsResponse.data

            const updatedPostsList = [...this.state.posts]
            updatedPostsList.push(newPost)

            this.setState({ posts: updatedPostsList })
        } catch (error) {
            console.log("Error creating new Post")
        }
    }

    deletePost = async (postid) => {
        try {
			await axios.delete(`${process.env.REACT_APP_POSTS_API}/${postid}`)
			this.getPosts()
        } catch (error) {
            console.log("Error deleting Post with ID: " + postid)
        }
    }

    render() {

        const posts = this.state.posts.map((post, i) => {
            return <div key={i}>
                        <ForumPost post={post} user={this.props.user} loggedIn={this.props.loggedIn}  />
                        {this.props.user.id === post.userid ? <button onClick={() => {this.deletePost(post.id)}}>Delete</button> : null}
                   </div>
            })

        return (
            <div>
                <h2>Forum Posts</h2>
                {posts}
                <CreatePost user={this.props.user} createPost={this.createPost} />
            </div>
        );
    }
}

export default Forum;