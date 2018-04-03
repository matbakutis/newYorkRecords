import React, { Component } from 'react';

class Home extends Component {
	render() {

		const homeTitleStyle = {
			'textAlign': 'center',
			'font-family': 'Comfortaa, cursive',
			'font-weight': 'bold'
		}

		const imageStyle = {
			'background-image': 'url("http://hdwpro.com/wp-content/uploads/2018/02/new-york-background-image.jpg")',
			'height': '400px', 
			'background-attachment': 'fixed',
			'background-position': 'center',
			'background-repeat': 'no-repeat',
			'background-size': 'cover',
			'box-shadow': 'inset 0px 5px 20px #000'
		}

		return (
			<div>
				<h1 style={homeTitleStyle}>New York City Records</h1>
				<h3 style={homeTitleStyle}>The Official New York City Record Seaching Tool!</h3>
				<div style={imageStyle}></div>
				<h1 style={homeTitleStyle}>Create an Account!</h1>
				<h5 style={homeTitleStyle}>Create an account to be able to search and save your searchs. It is free and easy to create an account, just click on the log in button to create an account or log into an alreadt existing account.</h5>
				<h1 style={homeTitleStyle}>Search for Records!!</h1>
				<h5 style={homeTitleStyle}>Once you create an account you will be able to log in and see the search tab. The search tab will allow you to put in certain parameters to base your search off of. If you do not see the search tab please contact our support team at (555) 555 - 5555.</h5>
			</div>
		);
	}
}

export default Home;