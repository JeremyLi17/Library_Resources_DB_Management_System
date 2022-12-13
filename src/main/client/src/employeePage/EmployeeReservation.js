import '../employeePageAttribute/EmployeeReservation.css';
import React, { Component } from 'react'
import {Routes, Route, useNavigate} from 'react-router-dom';
import {useState} from 'react';
import axios from 'axios';

axios.defaults.headers.common['Authorization'] = `Bearer ${localStorage.getItem("token")}`

const reservationList = ({list}) => {
    return (
        <div className='Home_Reservation'>
            <h1>Reservation Info:</h1>
            <ul>
                {list.map((el) => {
                    return (
                        <li key={list.id}>
                            <hr></hr>
                            <div className='Home_Reservation_List'>
                                <div className='Home_Reservation_Info'>
                                    <div className='Home_Reservation_Info_Line'>
                                    <div className='Home_Reservation_Info_Block'>
                                        <div className='Home_Reservation_Info_Text'>Room Capacity: {}</div>
                                    </div>
                                    <div className='Home_Reservation_Info_Block'>
                                        <div className='Home_Reservation_Info_Text'>Room Capacity: {}</div>
                                    </div>
                                    <div className='Home_Reservation_Info_Block'>

                                    </div>
                                    </div>
                                    <div className='Home_Reservation_Info_Line'>
                                    <div className='Home_Reservation_Info_Block'>

                                    </div>
                                    <div className='Home_Reservation_Info_Block'>

                                    </div>
                                    <div className='Home_Reservation_Info_Block'>

                                    </div>
                                    </div>
                                </div>
                            </div>
                        </li>
                    )
                })}
            </ul>
            <hr></hr>
        </div>
    );
}

export default async function EmployeeReservation() {

    // localStorage.getItem('')
    const [date, setDate] = useState();
    const [timeslot, setTimeslot] = useState();
    const [studyRoom, setStudyRoom] = useState();


}