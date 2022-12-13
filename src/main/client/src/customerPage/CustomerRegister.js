import React, { Component } from 'react'
import PropTypes from 'prop-types'
import {Routes, Route, useNavigate} from 'react-router-dom';
import './Login.css';
import Employee from './employeePage/EmployeeDashboard'
import {useState} from 'react';
import userRequest from './request/user-request';
import axios from 'axios';

export default function CustomerRegister() {
    
    const [firstName, setFirstName] = useState();
    const [middleName, setMiddleName] = useState();
    const [lastName, setLastName] = useState();
    const [email, setEmail] = useState();
    const [phoneNumber, setPhoneNumber] = useState();
    const [idType, setIdType] = useState();
    const [idNumber, setIdNumber] = useState();

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
        setPhoneNumber(event.target.value);
    }

    const handleChangeIdType = event => {
        setIdType(event.target.value);
    }

    const handleChangeIdNumber = event => {
        setIdNumber(event.target.value);
    }

    const handleUpdate = (e) => {
        const data = {
            firstName,
            middleName,
            lastName,
            email,
            phoneNo: phoneNumber,
            idType,
            idNo: idNumber
        }
    }

    return (
        <div className='Employee'>
            <div className='Home_Bar'>
                <Emp_profile/>
                <Signout/>
            </div>
            <div className='Home_Customer'>
                <div className='Home_Customer_Detail'>
                    <form className='Home_Customer_Detail_List'>
                        <div className='Customer_First_Name'>
                            Customer First Name:
                            <input
                            type="text"
                            name="first_name"
                            value={firstName}
                            onChange={handleChangeFirstName}/>
                        </div>
                        <div className='Customer_Middle_Name'>
                            Customer Middle Name:
                            <input
                            type="text"
                            name="middle_name"
                            value={middleName}
                            onChange={handleChangeMiddleName}/>
                        </div>
                        <div className='Customer_Last_Name'>
                            Customer Last Name:
                            <input
                            type="text"
                            name="last_name"
                            value={lastName}
                            onChange={handleChangeLastName}/>
                        </div>
                        <div className='Customer_Email'>
                            Customer Email:
                            <input
                            type="text"
                            name="email"
                            value={email}
                            onChange={handleChangeEmail}/>
                        </div>
                        <div className='Customer_Phone_Number'>
                            Customer Mobile Phone:
                            <input
                            type="text"
                            name="phone"
                            value={phoneNumber}
                            onChange={handleChangePhoneNumber}/>
                        </div>
                        <div className='Customer_Id_Type'>
                            Customer ID:
                            <input
                            type="text"
                            name="id"
                            value={idType}
                            onChange={handleChangeIdType}/>
                        </div>
                        <div className='Customer_Id_Number'>
                            Customer ID Number:
                            <input
                            type="text"
                            name="id_number"
                            value={idNumber}
                            onChange={handleChangeIdNumber}/>
                        </div>
                        <div className='Customer_Others'>
                            <button onClick={handleUpdate}>
                                Sign Up
                            </button>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    );
}

function Emp_profile() {

    return (<div className='Home_LeftBar'>
              <div className='Home_LeftBar_Emp_Detail'>
                <div className='Home_LeftBar_Emp_Detail_Name_Email'>
                  <div className='Home_LeftBar_Emp_Detail_Name'>
                    <span className='Home_LeftBar_Emp_Detail_Name_Span'>
                      {emp_first_name} {emp_last_name}
                    </span>
                  </div>
                  <div className='Home_LeftBar_Emp_Detail_Email'>
                    <span className='Home_LeftBar_Emp_Detail_Email_Span'>
                      kxiao855@gmail.com
                    </span>
                  </div>
                </div>
              </div>
            </div>);
  }
  
  function Signout() {
  
    const navigate = useNavigate();
  
    const navigateToLogIn = () => {
      navigate('/', {replace: true});
    }
  
    return (<form className='Home_RightBar'>
                <button className='Home_RightBar_Emp_Detail'
                        onClick={navigateToLogIn}>
                <div className='Home_RightBar_Emp_Detail_SignOut'>
                    Sign Out
                </div>
                </button>
            </form>);
  }