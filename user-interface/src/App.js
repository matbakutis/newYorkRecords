import React, { Component } from 'react';
import axios from 'axios'
import {BrowserRouter as Router, Route, Switch, Link} from 'react-router-dom';
import UsersList from './components/UsersList'
import NewUserForm from './components/NewUserForm'
import Home from './components/Home'
import LogIn from './components/LogIn'
import Profile from './components/Profile'


class App extends Component {
	
	constructor(){
		super();
		this.state = {
			users: [],
			loggedIn: false,
			user: {},
			redirectToLogin: false,
			loginMessage: ''
		}
	}

    async componentDidMount() {
		this.getUsers()
	}
	
	getUsers = async (userId) => {
        const usersResponse = await axios.get('/api/users')
        this.setState({
            users: usersResponse.data,
            usersResponse
        })
    }

    deleteUser = async (userId, i) => {
        try {
			await axios.delete(`/api/users/${userId}`)
			if (this.state.user.id === userId){
				this.setState({user: {}, loggedIn: false})
			}
			this.getUsers()
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

	updateUser = async (updatedUser) => {
        try {
            const newUserResponse = await axios.patch('/api/users', updatedUser)
            this.getUsers();
        } catch (error) {
            console.log("Error updating User")
        }
	}


	logInUser = async (userUsername) => {
		const user = this.state.users.find((user)=>{
			return user.userName === userUsername;
		})
		if (user){
			try {
				const userResponse = await axios.get('/api/users/' + user.id)
				const userFromDatabase = userResponse.data
				this.setState({user: userFromDatabase, loggedIn: true, redirectToLogin: false, loginMessage: ''})
			} catch (error) {
				console.log("Error finding User")
			}
		}else {
			this.setState({loginMessage: 'Username or Password Incorrect', loggedIn: false})
		}
	}

	logOutUser = () => {
		this.setState({user: {}, loggedIn: false})
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
		
		const LogInFormComponent = () => (
            <LogIn logInUser={this.logInUser}/>
		)

		const ProfileComponent = () => (
            <Profile user={this.state.user} deleteUser={this.deleteUser} updateUser={this.updateUser}/>
		)

		const navBarStyle = {
			'display': 'flex',
			'flexDirection': 'row',
			'alignItems': 'center',
			'justifyContent': 'space-between'
		}

		const navTitleStyle = {
			'marginLeft': '30px',
			'fontFamily': 'Comfortaa, cursive',
			'fontWeight': 'bold'
		}

		const navLinkStyle = {
			'marginRight': '30px',
			'fontFamily': 'Comfortaa, cursive',
			'color': 'black',
			'textDecoration': 'none'
		}

        return (
			<Router>
				<div>
					<nav style={navBarStyle}>
						<h1 style={navTitleStyle}>NYC Records</h1>
						<div>
							<Link to="/" id="homeLink" style={navLinkStyle}>Home</Link>
							<Link to="/users" id="usersLink" style={navLinkStyle}>Users</Link>
							{this.state.loggedIn ? <Link to="/profile" id="profileLink" style={navLinkStyle}>Profile</Link> : null}
							{!this.state.loggedIn ? <Link to="/login" id="loginLink" style={navLinkStyle} >Log In</Link> : null}
							{this.state.loggedIn ? <Link to="/" id="logoutLink" style={navLinkStyle} onClick={this.logOutUser}>Log Out</Link> : null}
							{this.state.loginMessage ? <h4 id="login-error-message">{this.state.loginMessage}</h4> : null}
						</div>
					</nav>
					<Switch>
						<Route exact path="/" component={Home}/>
						<Route exact path="/users" render={UsersListComponent}/>
						<Route exact path="/new" render={NewUserFormComponent}/>
						<Route exact path="/login" render={LogInFormComponent}/>
						<Route exact path="/profile" render={ProfileComponent}/>
					</Switch>
				</div>
            </Router>
        )
    }
}

export default App;
