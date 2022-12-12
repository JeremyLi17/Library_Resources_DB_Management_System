import React, { Component } from 'react'
import PropTypes from 'prop-types'
import {Routes, Route, useNavigate} from 'react-router-dom';
import '../layout_attributes/Login.css';
import Employee from './Employee'
import {useState} from 'react';
import userRequest from '../request/user-request';
import axios from 'axios';

axios.defaults.headers.common['Authorization'] = `Bearer ${localStorage.getItem("token")}`

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
        )

        if (res.status === 200) {
            localStorage.setItem("token", res.headers["jwt-token"]);
            // TODO: Add customer navigation
        }

    };

    // const onclick = async (e) => {
    //     e.preventDefault();
        
    //     const data = {
    //         username: '1210test',
    //         password: 'password'
    //     }

    //     const res = await axios.post(
    //         "http://localhost:8080/api/customer/login",
    //         data
    //     )


    //     console.log(res);
    //     localStorage.setItem("token", res.headers["jwt-token"]);
    //     // console.log(localStorage.getItem("token"));

    //     // const config = {
    //     //     headers: {
    //     //         "Authorization": `Bearer ${localStorage.getItem("token")}`
    //     //     }
    //     // };

    //     const res2 = await axios.get(
    //         "http://localhost:8080/api/customer/list",
    //     )

    //     console.log(res2);
    // }

    const employeeLogInRequest = async (e) => {
        // 👇️ prevent page refresh
        e.preventDefault();

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
            console.log('success');
            localStorage.setItem("token", res.headers["jwt-token"]);
            localStorage.setItem("currentUser", res.data);
            navigateToEmployeeDashboard();
        }).catch((e) => {
            console.log(e.response.data['message']);
            sessionStorage.setItem('err', res.response.data['message'])
            navigateToPwd_error();
        })

        
        // if (res.status === 200) {
        //     localStorage.setItem("token", res.headers["jwt-token"]);
        //     localStorage.setItem("currentUser", res.data);
        //     navigateToEmployeeDashboard();
        // }
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
                        <button onClick={onclick}>Customer Sign in</button>
                        <button onClick={employeeLogInRequest}>Employee Sign in</button>
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
    return <h2 className="wrong_pwd">{sessionStorage.getItem('err')}</h2>;
}

function Home() {
    return <h2></h2>;
}
