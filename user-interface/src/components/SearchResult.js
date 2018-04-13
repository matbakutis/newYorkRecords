import React from 'react';

const SearchResult = (props) => {
    return (
        <div>
            { this.props.result.agency_name ? <h4>Agency: {props.result.agency_name}</h4> : null }
            { this.props.result.category_description ? <h4>Category: {props.result.category_description}</h4> : null }
            { this.props.result.vendor_name ? <h4>Vendor: {props.result.vendor_name}</h4> : null }
            { this.props.result.request_id ? <h4>Request Id: {props.result.request_id}</h4> : null }
            { this.props.result.short_title ? <h4>Short Title: {props.result.short_title}</h4> : null }
            { this.props.result.printout_1 ? <p>Description: {props.result.printout_1}</p> : null }
        </div>
    );
};

export default SearchResult;