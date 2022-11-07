import React from 'react';
import ReactDOM from 'react-dom';
import App from './App';

// injecting the entire react app inside the file
// and triggering the div with id 'root'
ReactDOM.render(
    <App />, document.getElementById('root')
);