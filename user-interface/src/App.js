import React, { Component } from 'react';
import axios from 'axios'
import {BrowserRouter as Router, Route, Switch, Link} from 'react-router-dom';
import UsersList from './components/UsersList'
import NewUserForm from './components/NewUserForm'
import Home from './components/Home'

class App extends Component {
	
	constructor(){
		super();
		this.state = {
			users: []
		}
	}

    async componentDidMount() {
		const usersResponse = await axios.get('/api/users')
		console.log(usersResponse.data, usersResponse)
        this.setState({
            users: usersResponse.data,
            usersResponse
        })
    }

    deleteUser = async (userId, i) => {
        try {
            await axios.delete(`/api/users/${userId}`)

            const updatedUsersList = [...this.state.users]
            updatedUsersList.splice(i, 1)

            this.setState({users: updatedUsersList})

        } catch (error) {
            console.log("Error deleting User with ID: " + userId)
        }
    }

    createUser = async (newUser) => {
        try {
            const newUserResponse = await axios.post('/api/users', newUser)
            const newUserFromDatabase = newUserResponse.data

            const updatedUsersList = [...this.state.users]
            updatedUsersList.push(newUserFromDatabase)

            this.setState({users: updatedUsersList})

        } catch (error) {
            console.log("Error creating new User")
        }
    }

    render() {

        const UsersListComponent = () => (
            <UsersList
                users={this.state.users}
                deleteUser={this.deleteUser}/>
        )

        const NewUserFormComponent = () => (
            <NewUserForm createUser={this.createUser}/>
        )

        return (
			<Router>
				<div>
					<nav>
						<Link to="/" id="homeLink">Home</Link>
						<Link to="/users" id="usersLink">Users</Link>
						<Link to="/login" id="loginLink">Log In</Link>
						<Link to="/logout" id="logoutLink">Log Out</Link>
					</nav>
					<Switch>
						<Route exact path="/" component={Home}/>
						<Route exact path="/users" render={UsersListComponent}/>
						<Route exact path="/new" render={NewUserFormComponent}/>
					</Switch>
				</div>
            </Router>
        )
    }
}

export default App;
