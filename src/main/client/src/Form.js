import React, { Component } from 'react'
import PropTypes from 'prop-types'
import Input from './Input'
import {Routes, Route, useNavigate} from 'react-router-dom';
import './Form.css';

export default function Form() {

    const navigate = useNavigate();

    const navigateToPwd_error = () => {
        navigate('/wrong_pwd');
    }

    return (
        <div className="Form-Page">
            <header className="Sign_in_Title">
                <h1>
                    Sign in to Real Management System
                </h1>
            </header>
            <form className='Input' >
                <div className="InputBox">
                    <div>
                        <label for="uname"> Username </label>
                    </div>
                    <input
                    type="text"
                    id="uname"
                    name="name"
                    placeholder="Username"
                    className="InputBox"/>
                    <div>
                        <label for="pwd"> Password </label>
                    </div>
                    <input
                    type="text"
                    id="pwd"
                    name="name"
                    placeholder="Password"
                    className="InputBox"/>
                    <div>
                        <button onClick={navigateToPwd_error}>Customer Sign in</button>
                        <button>Employee Sign in</button>
                    </div>
                    <div>
                        <button>Forgot Password</button>
                    </div>
                    <div>
                        <button>Register for Customer</button>
                        <button>Register for Employee</button>
                    </div>
                    <Routes>
                        <Route path="/wrong_pwd" element={<Pwd_error />} />
                    </Routes>
                </div>
            </form>
        </div>
    )
}

function Pwd_error() {
    return <h2 className="wrong_pwd">wrong username or password</h2>;
}

function Home() {
    return <h2></h2>;
}
