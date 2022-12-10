import React from 'react';
import './index.css';
import Form from './Form';
import ReactDOM from 'react-dom'

// const root = ReactDOM.createRoot(document.getElementById('root'));
// root.render(<App />);

const inputs = [{
    name: "username",
    placeholder: "username",
    type: "text"
  },{
    name: "password",
    placeholder: "password",
    type: "password"
  },{
    type: "submit",
    value: "Submit",
    className: "btn" 
  }]
  
  const props = {
    name: 'loginForm',
    method: 'POST',
    action: '/perform_login',
    inputs: inputs
  }
  
  const params = new URLSearchParams(window.location.search)
  
  ReactDOM.render(
    <Form {...props} error={params.get('error')} />,
    document.getElementById('container'))
