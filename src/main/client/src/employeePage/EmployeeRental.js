import '../employeePageAttribute/EmployeeRental.css';
import React, { Component } from 'react';
import { Routes, Route, useNavigate } from 'react-router-dom';
import { useState } from 'react';
import axios from 'axios';

const list = JSON.parse(localStorage.getItem("rentalList"));
const emp_username = localStorage.getItem('emp_username');
const emp_email = localStorage.getItem('emp_email');
const emp_role = localStorage.getItem('emp_role');

function RentalList() {
    console.log(list);
  return (
    <div className="Home_Reservation">
      <h1>Reservation Info:</h1>
      <ul>
        {list.map((el) => {
          return (
            <li key={el['id']}>
              <hr></hr>
              <div className="Home_Reservation_List">
                <div className="Home_Reservation_Info">
                  <div className="Home_Reservation_Info_Line">
                    <div className="Home_Reservation_Info_Block">
                      <div className="Home_Reservation_Info_Text">
                        date: {el['date']}
                      </div>
                    </div>
                    <div className="Home_Reservation_Info_Block">
                        <div className="Home_Reservation_Info_Text">
                            Study Room Capacity: {el['studyRoom']['capacity']}
                        </div>
                    </div>
                    <div className="Home_Reservation_Info_Block">
                        <div className="Home_Reservation_Info_Text">
                            Time Slot: {el['timeslot']}
                        </div>
                    </div>
                  </div>
                </div>
              </div>
            </li>
          );
        })}
      </ul>
      <hr></hr>
    </div>
  );
};

function Emp_profile() {

    return (<div className='Home_LeftBar'>
              <div className='Home_LeftBar_Emp_Detail'>
                <div className='Home_LeftBar_Emp_Detail_Name_Email'>
                  <div className='Home_LeftBar_Emp_Detail_Name'>
                    <span className='Home_LeftBar_Emp_Detail_Name_Span'>
                      {emp_username} ({emp_role})
                    </span>
                  </div>
                  <div className='Home_LeftBar_Emp_Detail_Email'>
                    <span className='Home_LeftBar_Emp_Detail_Email_Span'>
                      {emp_email}
                    </span>
                  </div>
                </div>
              </div>
            </div>);
  }
  
function Signout() {

    const navigate = useNavigate();

    const NavigateToEmployeeDashBoard = () => {
        localStorage.removeItem("rentalList");
        navigate('/employee/*', {replace: true});
    }

    const navigateToLogIn = () => {
        localStorage.clear();
        navigate('/', {replace: true});
    }

    return (<form className='Home_RightBar'>
                <button className='Home_RightBar_Emp_Detail'
                        onClick={navigateToLogIn}>
                <div className='Home_RightBar_Emp_Detail_SignOut'>
                    Sign Out
                </div>
                </button>
                <button onClick={NavigateToEmployeeDashBoard} className='Home_RightBar_Emp_Detail'>
                    <div className='Home_RightBar_Emp_Detail_SignOut'>back</div>
                </button>
            </form>);
}

const EmployeeRental = () => {
    return (
        <div className='Employee'>
            <div className='Home_Bar'>
                <Emp_profile/>
                <Signout/>
            </div>

            <RentalList/>
        </div>
    );
}

export default EmployeeRental;