import React from 'react';

const SearchResult = (props) => {
    return (
        <div>
            { props.result.agency_name ? <div><h4>Agency:</h4> <p>{props.result.agency_name}</p></div> : null }
            { props.result.category_description ? <div><h4>Category:</h4> <p>{props.result.category_description}</p></div> : null }
            { props.result.vendor_name ? <div><h4>Vendor:</h4> <p>{props.result.vendor_name}</p></div> : null }
            { props.result.request_id ? <div><h4>Request Id:</h4> <p>{props.result.request_id}</p></div> : null }
            { props.result.short_title ? <div><h4>Short Title:</h4> <p>{props.result.short_title}</p></div> : null }
            { props.result.printout_1 ? <div><h4>Description:</h4> <p>{props.result.printout_1}</p></div>: null }
        </div>
    );
};

export default SearchResult;