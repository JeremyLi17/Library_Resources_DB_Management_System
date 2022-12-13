import {createRoot} from 'react-dom/client';
import './employeePageAttribute/index.css';
import Login from './Login';
import EmployeeDashboard from './employeePage/EmployeeDashboard';
import EmployeeReservation from './employeePage/EmployeeReservation'
import {BrowserRouter as Router} from 'react-router-dom';
import {Routes, Route} from 'react-router-dom';

import CustomerRegister from './customerPage/CustomerRegister';
import EmployeeRegister from './employeePage/EmployeeRegister';
import Customerdashboard from './customerPage/Customerdashboard';
import Customerstudyroom from './customerPage/customerstudyroom';
import Newreservation from './customerPage/Newreservation';
import Managereservation from './customerPage/Managereservation';
import BookrentalSystem from './customerPage/BookrentalSystem';
import Rentbook from './customerPage/Rentbook';
import Registerexi from './customerPage/Registerexi';
import Manageexi from './customerPage/Manageexi';
import Managerental from './customerPage/Managerental';
import Exibitionsystem from './customerPage/Exibitionsystem';
import Payment from './customerPage/Payment';
import EmployeeRental from './employeePage/EmployeeRental';


const rootElement = document.getElementById('root');
const root = createRoot(rootElement); 

root.render(
  <Router>
    <Routes>
      <Route path = "/*" element={<Login />}/>
      <Route path = "/customerRegister/*" element={<CustomerRegister/>}/>
      <Route path = "/employeeRegister/*" element={<EmployeeRegister/>}/>
      <Route path = "/employee/*" element={<EmployeeDashboard />}/>
      <Route path = "/employee/reservation/*" element={<EmployeeReservation/>}/>
      <Route path = "/employee/rental/*" element={<EmployeeRental/>}/>
      <Route path = "/customer/*" element = {<Customerdashboard/>}/>
      <Route path = "/customer/studyroom/*" element = {<Customerstudyroom/>}/>
      <Route path = "/customer/studyroom/reserve/*" element = {<Newreservation/>}/>
      <Route path = "/customer/studyroom/manage/*" element = {<Managereservation/>}/>
      <Route path = "/customer/book/*" element = {<BookrentalSystem/>}/>
      <Route path = "/customer/book/rent/*" element = {<Rentbook/>}/>
      <Route path = "/customer/book/manage/*" element = {<Managerental/>}/>
      <Route path = "/customer/exibition/*" element = {<Exibitionsystem/>}/>
      <Route path = "/customer/exibition/register/*" element = {<Registerexi/>}/>
      <Route path = "/customer/exibition/manage/*" element = {<Manageexi/>}/>
      <Route path = "/customer/book/rent/payment/*" element = {<Payment/>}/>
    </Routes>
  </Router>
);  