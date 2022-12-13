import React, { Component } from 'react'
import PropTypes from 'prop-types'
import {BrowserRouter, Routes, Route, useNavigate} from 'react-router-dom';
import '../employeePageAttribute/EmployeeRegister.css';
import {useState} from 'react';
// import userRequest from './request/user-request';
import axios from 'axios';

function Error() {
    console.log('here2');
    return <h2 className="wrong_pwd">{sessionStorage.getItem('err')}</h2>;
}

export default function EmployeeRegister() {

    const navigate = useNavigate();
    
    const [email, setEmail] = useState();
    const [role, setRole] = useState();
    const [username, setUsername] = useState();
    const [pwd, setPwd] = useState();
    const [emp_signup_error, setEmp_signup_error] = useState('');

    const handleChangeEmail = event => {
        setEmail(event.target.value);
    }

    const handleChangeUsername = event => {
        setUsername(event.target.value);
    }

    const handleChangePwd = event => {
        setPwd(event.target.value);
    }

    const handleChangeRole = event => {
        setRole(event.target.value);
    }

    const navigateToCustomerDashboard = () => {
        navigate('/employee/*', {replace: true});
    }

    const navigateToLogIn = event => {
        navigate('/', {replace: true});
    }

    const registerRequest = async (e) => {
        e.preventDefault();

        sessionStorage.setItem('reg_pressed', true);

        const registration = {
            email,
            role,
            username,
            password: pwd
        }

        console.log('here');
        const res = await axios.post(
            "http://localhost:8080/api/employee/register",
            registration
        ).then((res) => {
            console.log('success');
            localStorage.setItem("token", res.headers["jwt-token"]);
            localStorage.setItem("currentUser", res.data);
            sessionStorage.removeItem('err');
            navigateToCustomerDashboard();
        }).catch((e) => {
            console.log(e.response.data['message']);
            setEmp_signup_error(e.response.data['message']);
        })

        console.log('here3');
    }

    return (
        <div className="Form-Page">
            <header className="Sign_in_Title">
                <h1>
                    Sign Up for Customer
                </h1>
            </header>
            <form className='Input'>
                <div className="InputBox">
                    <div>
                        <label>Email Address</label>
                    </div>

                    <input
                    type="text"
                    name="email"
                    placeholder= "email"
                    className="InputBox"
                    value={email}
                    onChange={handleChangeEmail}/>

                    <div>
                        <label>Role</label>
                    </div>

                    <input
                    type="text"
                    name="role"
                    placeholder= "Customer Service Provider"
                    className="InputBox"
                    value={role}
                    onChange={handleChangeRole}/>

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
                        <button onClick={registerRequest}>
                            Register
                        </button>
                        <button onClick={navigateToLogIn}>
                            back
                        </button>
                    </div>
                    <h2 className="wrong_pwd">{emp_signup_error}</h2>
                </div>
            </form>
        </div>
    );
}