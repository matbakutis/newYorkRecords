import React, { Component } from 'react';
import SearchResult from './SearchResult'
import axios from 'axios'

class SearchForm extends Component {
    constructor(){
        super();
        this.state = {
            results: [],
            search: ''
        }
    }

    getResults = async (search) => {
        if (search) {
            const resultsResponse = await axios.get(`https://data.cityofnewyork.us/resource/buex-bi6w.json?type_of_notice_description=${search}`)
            this.setState({
                results: resultsResponse.data
            })
        }else {
            const resultsResponse = await axios.get('https://data.cityofnewyork.us/resource/buex-bi6w.json')
            this.setState({
                results: resultsResponse.data
            })
        }
    }

    handleSubmit = (event) => {
        event.preventDefault()
        this.getResults(this.state.search);
    }

    handleChange = (event) => {
        const state = this.state;
        state[event.target.name] = event.target.value;
        this.setState(state);
    }

    render() {

        const userTitleStyle = {
            'textAlign': 'center',
            'fontFamily': 'Comfortaa, cursive',
            'fontWeight': 'bold'
        }

        const searchInputStyle = {
            'width': '50%'
        }

        const searchWrapperStyle = {
            'width': '75%',
            'margin': '0 auto',
            'textAlign': 'center',
            'fontFamily': 'Comfortaa, cursive'
        }

        const searchResults = this.state.results.map((result, i)=>{
            return <SearchResult result={result} key={i} />
        })

        return (
            <div style={searchWrapperStyle}>
                <div>
                    <h1 style={userTitleStyle}>Search by Type of Notice</h1>
                    <input
                        style={searchInputStyle}
                        placeholder="Notice Type..."
                        id="search-bar"
                        type="text"
                        name="search"
                        onChange={this.handleChange} required/>
                    <button onClick={this.handleSubmit}>Search</button>
                </div>
                <div>
                    <h3>Results</h3>
                    { searchResults }
                </div>
            </div>
        );
    }
}

export default SearchForm;