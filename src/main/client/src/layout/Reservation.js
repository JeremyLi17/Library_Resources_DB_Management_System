import '../layout_attributes/Reservation.css';
import {useState} from 'react';
import {Routes, Route, useNavigate} from 'react-router-dom';

export default function Reservation() {
    return(<div className='Home_Reservation'>
                <h1>Reservation Info:</h1>
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
                <hr></hr>
                <div className='Home_Reservation_List'>
                
                </div>
                <hr></hr>
                <div className='Home_Reservation_List'>
                
                </div>
                <hr></hr>
                <div className='Home_Reservation_List'>
                
                </div>
                <hr></hr>
                <div className='Home_Reservation_List'>
                
                </div>
                <hr></hr>
                
            </div>);
}