import React, { Component } from 'react'
import PropTypes from 'prop-types'
import {Routes, Route, useNavigate} from 'react-router-dom';
import './Login.css';
import Employee from './employeePage/EmployeeDashboard'
import {useState} from 'react';
import userRequest from './request/user-request';
import axios from 'axios';

export default function Login() {

    const navigate = useNavigate();
    const [username, setUsername] = useState('');
    const [pwd, setPwd] = useState('');

    const customerLogInRequest = async (e) => {
        e.preventDefault();

        setUsername(e.target.value);
        setPwd(e.target.value);
        
        const data = {
            username,
            password: pwd
        }
        
        const res = await axios.post(
            "http://localhost:8080/api/customer/login",
            data
        ).then((res)=> {
            console.log(res.data);
            localStorage.setItem("token", res.headers["jwt-token"]);
            localStorage.setItem("currentUser", res.data);
            sessionStorage.removeItem('err');
            navigateToCustomerDashBoard();
        }).catch((e) => {
            // console.log(e.response.data['message']);
            sessionStorage.setItem('err', e.response.data['message'])
            navigateToError();
        })

    };

    //     const res2 = await axios.get(
    //         "http://localhost:8080/api/customer/list",
    //     )

    //     console.log(res2);

    const employeeLogInRequest = async (e) => {
        // 👇️ prevent page refresh
        e.preventDefault();

        sessionStorage.setItem('emp_pressed', true);

        setUsername(e.target.value);
        setPwd(e.target.value);
        
        console.log(username);
        console.log(pwd);
        const data = {
            username,
            password: pwd
        }
        
        const res = await axios.post(
            "http://localhost:8080/api/employee/login",
            data
        ).then((res)=> {
            console.log(res.data);

            localStorage.setItem("token", res.headers["jwt-token"]);
            localStorage.setItem("emp_username", res.data['username']);
            localStorage.setItem("emp_email", res.data['email']);
            localStorage.setItem("emp_role", res.data['role']);
            
            sessionStorage.removeItem('err');
            navigateToEmployeeDashboard();
        }).catch((e) => {
            console.log(e.response.data['message']);
            sessionStorage.setItem('err', e.response.data['message'])
            navigateToError();
        })
    };

    const navigateToCustomerRegistration = () => {
        navigate('/customerRegister/*')
    }

    const navigateToEmployeeRegistration = () => {
        navigate('/employeeRegister/*')
    }

    const handleChangeUsername = event => {
        setUsername(event.target.value);
        console.log('username is:', event.target.value);
      };
    
    const handleChangePwd = event => {
        setPwd(event.target.value);
        console.log('password is:', event.target.value);
    };
    
    const navigateToCustomerDashBoard = () => {
        navigate('/customer/*');
    }

    const navigateToEmployeeDashboard = () => {
        navigate('/employee/*');
    }

    return (
        <div className="Form-Page">
            <header className="Sign_in_Title">
                <h1>
                    Sign in to Real Management System
                </h1>
            </header>
            <form className='Input'>
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
                        <button onClick={customerLogInRequest}>Customer Sign in</button>
                        <button onClick={employeeLogInRequest}>Employee Sign in</button>
                    </div>

                    <div>
                        <button>Forgot Password</button>
                    </div>

                    <div>
                        <button onClick={navigateToCustomerRegistration}>Register for Customer</button>
                        <button onClick={navigateToEmployeeRegistration}>Register for Employee</button>
                    </div>

                    <h1 className="wrong_pwd">{sessionStorage.getItem('err')}</h1>

                </div>
            </form>
        </div>
    )
}