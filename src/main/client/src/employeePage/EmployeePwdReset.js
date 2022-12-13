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

export default function EmployeePwdReset() {

    const navigate = useNavigate();
    
    const [email, setEmail] = useState();
    const [username, setUsername] = useState();
    const [pwd, setPwd] = useState();
    const [confirm_pwd, setConfirm_Pwd] = useState();
    const [emp_reset_error, setEmp_reset_error] = useState('');

    const handleChangeEmail = event => {
        setEmail(event.target.value);
    }

    const handleChangeUsername = event => {
        setUsername(event.target.value);
    }

    const handleChangePwd = event => {
        setPwd(event.target.value);
    }

    const handleChangeConfirm_Pwd = event => {
        setConfirm_Pwd(event.target.value);
    }

    const navigateToEmployeeDashboard = () => {
        navigate('/employee/*', {replace: true});
    }

    const navigateToLogIn = event => {
        navigate('/', {replace: true});
    }

    const resetRequest = async (e) => {
        e.preventDefault();

        sessionStorage.setItem('reg_pressed', true);

        const registration = {
            username,
            password: pwd
        }

        console.log('here');
        if (pwd === confirm_pwd) {
            const res = await axios.post(
                "http://localhost:8080/api/employee/resetpassword",
                registration
            ).then((res) => {
                console.log('success');
                localStorage.setItem("token", res.headers["jwt-token"]);
                localStorage.setItem("currentUser", res.data);
                navigateToEmployeeDashboard()
            }).catch((e) => {
                console.log(e.response.data['message']);
                setEmp_reset_error(e.response.data['message']);
            })
        } else {
            setEmp_reset_error('New Password Mismatched: Please Try Again');
        }

        console.log('here3');
    }

    return (
        <div className="Form-Page">
            <header className="Sign_in_Title">
                <h1>
                    Password Reset for Employee
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
                        <label> Confirm Password </label>
                    </div>

                    <input
                    type="password"
                    name="confirm pwd"
                    placeholder="Confirm Password"
                    className="InputBox"
                    value={confirm_pwd}
                    onChange={handleChangeConfirm_Pwd}/>

                    <div>
                        <button onClick={resetRequest}>
                            Register
                        </button>
                        <button onClick={navigateToLogIn}>
                            back
                        </button>
                    </div>
                    <h2 className="wrong_pwd">{emp_reset_error}</h2>
                </div>
            </form>
        </div>
    );
}