import React, { Component } from 'react'
import PropTypes from 'prop-types'
import {Routes, Route, useNavigate} from 'react-router-dom';
import './Login.css';
import Employee from './Employee'
import {useState} from 'react';

export default function Login() {

    const navigate = useNavigate();
    const [username, setUsername] = useState('');
    const [pwd, setPwd] = useState('');

    const handleSubmit = event => {
        // 👇️ prevent page refresh
        event.preventDefault();
        console.log('username is: ', username);
        console.log('pwd is: ', pwd);
        console.log('form submitted ✅');
    };

    const handleChangeUsername = event => {
        setUsername(event.target.value);
        console.log('username is:', event.target.value);
      };
    
    const handleChangePwd = event => {
        setPwd(event.target.value);
        console.log('password is:', event.target.value);
    };

    const navigateToPwd_error = () => {
        navigate('/wrong_pwd/*', {replace: true});
    }

    const navigateToEmployeeDashboard = () => {
        navigate('/dashboard/*');
    }

    return (
        <div className="Form-Page">
            <header className="Sign_in_Title">
                <h1>
                    Sign in to Real Management System
                </h1>
            </header>
            <form className='Input' onSubmit={handleSubmit}>
                <div className="InputBox">
                    <div>
                        <label> Username </label>
                    </div>

                    <input
                    type="text"
                    name="username"
                    placeholder= "Username"
                    className="InputBox"
                    value={username}
                    onChange={handleChangeUsername}/>

                    <div>
                        <label> Password </label>
                    </div>

                    <input
                    type="password"
                    name="pwd"
                    placeholder="Password"
                    className="InputBox"
                    value={pwd}
                    onChange={handleChangePwd}/>

                    <div>
                        <button onClick={navigateToPwd_error}>Customer Sign in</button>
                        <button onClick={navigateToEmployeeDashboard}>Employee Sign in</button>
                    </div>

                    <div>
                        <button>Forgot Password</button>
                    </div>

                    <div>
                        <button>Register for Customer</button>
                        <button>Register for Employee</button>
                    </div>

                    <Routes>
                        <Route path="/wrong_pwd/*" element={<Pwd_error />} />
                    </Routes>

                </div>
            </form>
        </div>
    )
}

function Employee_DashBoard() {
    return <Employee />;
}

function Pwd_error() {
    return <h2 className="wrong_pwd">wrong username or password</h2>;
}

function Home() {
    return <h2></h2>;
}
