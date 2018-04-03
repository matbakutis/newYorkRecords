import React, { Component } from 'react';

class Home extends Component {
	render() {
		
		const homeTitleStyle = {
			'textAlign': 'center'
		}

		return (
			<div>
				<h1 style={homeTitleStyle}>New York City Records</h1>
			</div>
		);
	}
}

export default Home;