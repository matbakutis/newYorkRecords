import React from 'react';

const SearchResult = (props) => {

    const resultStyle = {
        'paddingTop': '15px',
        'width': '75%',
        'margin': '15px auto',
        'minHeight': '50px',
        'backgroundColor': 'Grey',
        'borderRadius': '10px',
        'fontFamily': 'Comfortaa, cursive',
        'paddingBottom': '15px',
        'textAlign': 'center'
    }

    const resultSubjectStyle = {
        'textDecoration': 'underline'
    }

    return (
        <div style={resultStyle}>
            { props.result.agency_name ? <div><h4 style={resultSubjectStyle}>Agency</h4> <p>{props.result.agency_name}</p></div> : null }
            { props.result.category_description ? <div><h4 style={resultSubjectStyle}>Category</h4> <p>{props.result.category_description}</p></div> : null }
            { props.result.vendor_name ? <div><h4 style={resultSubjectStyle}>Vendor</h4> <p>{props.result.vendor_name}</p></div> : null }
            { props.result.request_id ? <div><h4 style={resultSubjectStyle}>Request Id</h4> <p>{props.result.request_id}</p></div> : null }
            { props.result.short_title ? <div><h4 style={resultSubjectStyle}>Short Title</h4> <p>{props.result.short_title}</p></div> : null }
            { props.result.printout_1 ? <div><h4 style={resultSubjectStyle}>Description</h4> <p>{props.result.printout_1}</p></div>: null }
        </div>
    );
};

export default SearchResult;