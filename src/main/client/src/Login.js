import React, { Component } from 'react'
import {useNavigate} from 'react-router-dom';
import './Login.css';
import {useState} from 'react';
import axios from 'axios';

export default function Login() {

    const navigate = useNavigate();
    const [username, setUsername] = useState('');
    const [pwd, setPwd] = useState('');
    const [login_err, setLogin_err] = useState("");

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
            localStorage.setItem("customer_username", res.data['username']);
            localStorage.setItem("customer_email", res.data['email']);
            localStorage.setItem("customer_firstName", res.data['firstName']);
            localStorage.setItem("customer_lastName", res.data['lastName']);
            localStorage.setItem("customer_phoneNo", res.data['phoneNo']);
            localStorage.setItem("customer_idType", res.data['idType']);
            localStorage.setItem("customer_idNo", res.data['idNo']);
            localStorage.setItem("customer_id", res.data['id']);
            
            sessionStorage.removeItem('err');
            navigateToCustomerDashBoard();
        }).catch((e) => {
            console.log(e.response.data['message']);
            setLogin_err(e.response.data['message']);
        })

    };

    const employeeLogInRequest = async (e) => {
        // 👇️ prevent page refresh
        e.preventDefault();

        sessionStorage.setItem('emp_pressed', true);

        setUsername(e.target.value);
        setPwd(e.target.value);

        console.log(sessionStorage.getItem('login_err'));
        
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
            
            navigateToEmployeeDashboard();
        }).catch((e) => {
            setLogin_err(e.response.data['message']);
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
    };
    
    const handleChangePwd = event => {
        setPwd(event.target.value);
    };
    
    const navigateToCustomerDashBoard = () => {
        
        navigate('/customer/*');
    }

    const navigateToEmployeeDashboard = () => {
        
        navigate('/employee/*');
    }

    const navigateToEmployeePwdReset = () => {

        navigate('/employee-pwd-reset/*');
    }

    const navigateToCustomerPwdReset = () => {
        
        navigate('/Customer-pwd-reset/*');
    }

    if (localStorage.getItem('token') !== null) {
        if (localStorage.getItem("customer_username") === null) {
            navigateToEmployeeDashboard();
        } else {
            navigateToCustomerDashBoard();
        }
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
                        <button onClick={navigateToCustomerPwdReset}>Forgot Password (Customer)</button>
                        <button onClick={navigateToEmployeePwdReset}>Forgot Password (Employee)</button>
                    </div>

                    <div>
                        <button onClick={navigateToCustomerRegistration}>Register for Customer</button>
                        <button onClick={navigateToEmployeeRegistration}>Register for Employee</button>
                    </div>

                    <h1 className="wrong_pwd">{login_err}</h1>

                </div>
            </form>
        </div>
    )
}