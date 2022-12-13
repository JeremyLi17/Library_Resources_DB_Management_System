import React, { Component } from 'react'
import PropTypes from 'prop-types'
import {BrowserRouter, Routes, Route, useNavigate} from 'react-router-dom';
import './CustomerRegister.css';
import {useState} from 'react';
// import userRequest from './request/user-request';
import axios from 'axios';

function Error() {
    console.log('here2');
    return <h2 className="wrong_pwd">{sessionStorage.getItem('err')}</h2>;
}

export default function CustomerRegister() {

    const navigate = useNavigate();
    
    const [firstName, setFirstName] = useState();
    const [middleName, setMiddleName] = useState();
    const [lastName, setLastName] = useState();
    const [email, setEmail] = useState();
    const [phoneNumber, setPhoneNumber] = useState();
    const [idType, setIdType] = useState();
    const [idNumber, setIdNumber] = useState();
    const [username, setUsername] = useState();
    const [pwd, setPwd] = useState();
    const [cust_signup_error, setCust_signup_error] = useState("");

    const handleChangeFirstName = event => {
        setFirstName(event.target.value);
    }

    const handleChangeMiddleName = event => {
        setMiddleName(event.target.value);
    }
    
    const handleChangeLastName = event => {
        setLastName(event.target.value);
    }

    const handleChangeEmail = event => {
        setEmail(event.target.value);
    }

    const handleChangePhoneNumber = event => {
        if (!isNaN(event.target.value)) {
            setPhoneNumber(event.target.value);
        }
    }

    const handleChangeIdType = event => {
        setIdType(event.target.value);
    }

    const handleChangeIdNumber = event => {
        setIdNumber(event.target.value);
    }

    const handleChangeUsername = event => {
        setUsername(event.target.value);
    }

    const handleChangePwd = event => {
        setPwd(event.target.value);
    }

    const navigateToCustomerDashboard = () => {
        navigate('/customer/*', {replace: true});
    }

    const navigateToLogIn = () => {
        navigate('/', {replace: true});
    }

    const registerRequest = async (e) => {
        e.preventDefault();

        sessionStorage.setItem('reg_pressed', true);

        const registration = {
            firstName,
            middleName,
            lastName,
            email,
            phoneNo: phoneNumber,
            idType,
            idNo: idNumber,
            username,
            password: pwd
        }

        console.log('here');
        const res = await axios.post(
            "http://localhost:8080/api/customer/register",
            registration
        ).then((res) => {
            console.log('success');
            localStorage.setItem("token", res.headers["jwt-token"]);
            localStorage.setItem("currentUser", res.data);
            sessionStorage.removeItem('err');
            navigateToCustomerDashboard();
        }).catch((e) => {
            console.log(e.response.data['message']);
            setCust_signup_error(e.response.data['message']);
        })

        console.log('here3');
    }

    // if (sessionStorage.getItem('reg_pressed') === null) {
    //     sessionStorage.removeItem('err');
    // }

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
                        <label>First Name</label>
                    </div>

                    <input
                    type="text"
                    name="firstName"
                    placeholder= "Michael"
                    className="InputBox"
                    value={firstName}
                    onChange={handleChangeFirstName}/>

                    <div>
                        <label>Middle Name</label>
                    </div>

                    <input
                    type="text"
                    name="middleName"
                    placeholder= "Leave Blank If N/A"
                    className="InputBox"
                    value={middleName}
                    onChange={handleChangeMiddleName}/>

                    <div>
                        <label>Last Name</label>
                    </div>

                    <input
                    type="text"
                    name="lastName"
                    placeholder= "Jorden"
                    className="InputBox"
                    value={lastName}
                    onChange={handleChangeLastName}/>

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
                        <label>Phone Number</label>
                    </div>

                    <input
                    type="text"
                    name="phoneNumber"
                    placeholder= "(123)456-7890"
                    className="InputBox"
                    value={phoneNumber}
                    onChange={handleChangePhoneNumber}/>

                    <div>
                        <label>ID Type</label>
                    </div>

                    <input
                    type="text"
                    name="idType"
                    placeholder= "Ex. Passport"
                    className="InputBox"
                    value={idType}
                    onChange={handleChangeIdType}/>

                    <div>
                        <label>ID No.</label>
                    </div>

                    <input
                    type="text"
                    name="idNo"
                    placeholder= "Ex. Passport No."
                    className="InputBox"
                    value={idNumber}
                    onChange={handleChangeIdNumber}/>

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
                    <h2 className="wrong_pwd">{cust_signup_error}</h2>
                </div>
            </form>
        </div>
    );
}