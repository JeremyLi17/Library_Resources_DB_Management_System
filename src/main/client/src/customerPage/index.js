import React from 'react';
import ReactDOM from 'react-dom/client';
import { BrowserRouter as Router} from 'react-router-dom'; 
import {Routes, Route} from 'react-router-dom';
import axios from 'axios';
// axios.defaults.headers.common["Authorization"] = `Bearer ${localStorage.getItem("token")}`;

import './index.css';
import App from './Customerdashboard';


import Customerstudyroom from './customerstudyroom';
import Newreservation from './Newreservation';
import Managereservation from './Managereservation';
import BookrentalSystem from './BookrentalSystem';
import Rentbook from './Rentbook';
import Registerexi from './Registerexi';
import Manageexi from './Manageexi';
import Managerental from './Managerental';
import Exibitionsystem from './Exibitionsystem';
import Payment from './Payment';

const root = ReactDOM.createRoot(document.getElementById('root'));

root.render(
    <Router>
        <Routes>
            <Route path = "/*" element = {<App/>}/>
            <Route path = "/studyroom/*" element = {<Customerstudyroom/>}/>
            <Route path = "/studyroom/reserve/*" element = {<Newreservation/>}/>
            <Route path = "/studyroom/manage/*" element = {<Managereservation/>}/>
            <Route path = "/book/*" element = {<BookrentalSystem/>}/>
            <Route path = "/book/rent/*" element = {<Rentbook/>}/>
            <Route path = "/book/manage/*" element = {<Managerental/>}/>
            <Route path = "/exibition/*" element = {<Exibitionsystem/>}/>
            <Route path = "/exibition/register/*" element = {<Registerexi/>}/>
            <Route path = "/exibition/manage/*" element = {<Manageexi/>}/>
            <Route path = "/book/rent/payment/*" element = {<Payment/>}/>

            
        </Routes>
    </Router>






);
