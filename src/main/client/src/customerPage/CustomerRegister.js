import React, { Component } from 'react'
import PropTypes from 'prop-types'
import {BrowserRouter, Routes, Route, useNavigate} from 'react-router-dom';
import '../customerPage/CustomerRegister.css';
import {useState} from 'react';
import Logo from '../library-svgrepo-com.svg'
import "@fontsource/abel";
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
    
    const [empEmail, setempEmail] = useState();
    const [role, setRole] = useState();
    const [empUsername, setEmpUsername] = useState();
    const [empPwd, setEmpPwd] = useState();

    const [employee, setEmployee] = useState(false);
    const [customer, setCustomer] = useState(false);
    const [open, setOpen] = React.useState(false);

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

    const handleOpen = () => {
        setOpen(!open);
    }

    const handleSelectCustomer = () => {
        setCustomer(true);
        setEmployee(false);
        setOpen(false);
    }

    const handleSelectEmployee = () => {
        setEmployee(true);
        setCustomer(false);
        setOpen(false);
    }

    const navigateToCustomerDashboard = () => {
        navigate('/customer/*', {replace: true});
    }

    const navigateToLogIn = () => {
        localStorage.clear();
        navigate('/', {replace: true});
    }

    const registerRequest = (e) => {
        e.preventDefault();

        if (customer) {
            registerRequestCustomer(e);
        } else if (employee) {
            registerRequestEmployee(e);
        } else {
            navigate('/register/session', {replace: true});
        }
    }

    const registerRequestEmployee = async (e) => {
        e.preventDefault();

        sessionStorage.setItem('reg_pressed', true);

        const registration = {
            email,
            role,
            username,
            password: pwd
        }

        console.log('here');
        const res = await axios.post(
            "http://localhost:8080/api/employee/register",
            registration
        ).then((res) => {
            console.log('success');
            localStorage.setItem("token", res.headers["jwt-token"]);
            localStorage.setItem("currentUser", res.data);
            sessionStorage.removeItem('err');
            navigateToCustomerDashboard();
        }).catch((e) => {
            console.log(e.response.data['message']);
            setEmp_signup_error(e.response.data['message']);
            navigate('/register/session', {replace:true})
        })
    }

    const registerRequestCustomer = async (e) => {
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
            navigate('/register/session', {replace:true})
        })

        console.log('here3');
    }

    // if (sessionStorage.getItem('reg_pressed') === null) {
    //     sessionStorage.removeItem('err');
    // }

    return (
        <div className='Signup_Page'>
            <div className='Signup_Frame'>
                <img src={Logo} className='Logo_svg'/>
                <span className='Login_Title'>
                    Sign in to Real Management System
                </span>
                {
                    window.location.pathname === '/register/session' ? (
                        <div className='Error_Notice'>
                            <div className='Error_Notice_First_Line'>
                                Failed to Sign up:
                            </div>
                            <div className='Error_Notice_Second_Line'>
                                * Unable to Process Info
                            </div>
                            <div className='Error_Notice_Third_Line'>
                                * Account Type is Empty
                            </div>
                        </div>
                    ) : (<div></div>)
                }
                {
                    window.location.pathname !== '/register/session' ? 
                    (customer ?
                    (<div className='Signup_Area_Customer_No_Error'>
                        <div className='Signup_Form_Customer'>
                            <div className='Selection_Frame'>
                                <label className='Selection_Title'>
                                    Please Select Account Type
                                </label>
                                <button onClick={handleOpen} className='Selection_Button'>
                                    <div className='Selection_Button_Placeholder'>
                                        {
                                            !customer && !employee ? 'N/A' : (customer ? 'Customer' : 'Employee')
                                        }
                                    </div>
                                </button>
                                {open ? (
                                    <ul className="menu">
                                    <li className="menu-item">
                                        <button onClick={handleSelectCustomer}>Customer</button>
                                    </li>
                                    <li className="menu-item">
                                        <button onClick={handleSelectEmployee}>Employee</button>
                                    </li>
                                    </ul>
                                ) : null}
                            </div>
                            <div className='Submission_Frame_Customer'>
                                <form onSubmit={registerRequest} className='Submission_Area_Customer'>
                                    <label className='First_Name_Title'>
                                        First Name
                                    </label>
                                    <input 
                                    type='text'
                                    className='First_Name_Input'
                                    value={firstName}
                                    placeholder='ex. George'
                                    onChange={handleChangeFirstName}/>

                                    <label className='Middle_Name_Title'>
                                        Middle Name (N/A if None)
                                    </label>
                                    <input 
                                    type='text'
                                    className='Middle_Name_Input'
                                    value={middleName}
                                    placeholder='ex. W.'
                                    onChange={handleChangeMiddleName}/>

                                    <label className='Last_Name_Title'>
                                        Last Name
                                    </label>
                                    <input 
                                    type='text'
                                    className='Last_Name_Input'
                                    value={lastName}
                                    placeholder='Bush'
                                    onChange={handleChangeLastName}/>

                                    <label className='Email_Title'>
                                        Email
                                    </label>
                                    <input 
                                    type='text'
                                    className='Email_Input'
                                    value={email}
                                    placeholder='example@example.com'
                                    onChange={handleChangeEmail}/>

                                    <label className='Phone_Title'>
                                        Phone
                                    </label>
                                    <input 
                                    type='text'
                                    className='Phone_Input'
                                    value={phoneNumber}
                                    placeholder="ex. (123)456-7890"
                                    onChange={handleChangePhoneNumber}/>
                                    <label className='IdType_Title'>
                                        Id Type
                                    </label>
                                    <input 
                                    type='text'
                                    className='IdType_Input'
                                    value={idType}
                                    placeholder="Abbreviated form (ex. P -> Passport)"
                                    onChange={handleChangeIdType}/>

                                    <label className='IdNo_Title'>
                                        Id Number
                                    </label>
                                    <input 
                                    type='text'
                                    className='IdNo_Input'
                                    value={idNumber}
                                    onChange={handleChangeIdType}/>

                                    <label className='Username_Title'>
                                        Username
                                    </label>
                                    <input
                                    type='text'
                                    className='Username_Input'
                                    value={username}
                                    onChange={handleChangeUsername}/>

                                    <label className='Password_Title'>
                                        Password
                                    </label>
                                    <input 
                                    type='password'
                                    className='Password_Input'
                                    value={pwd}
                                    onChange={handleChangePwd}/>

                                    <button className='Signup_Button'>
                                        <div className='Signup_Button_Title'>
                                            Sign up
                                        </div>
                                    </button>
                                </form>

                                <button className="Back_Button_Customer">
                                    <div className='Back_Button_Customer_Title'>
                                        Back
                                    </div>
                                </button>
                            </div>
                        </div>
                    </div>) : (<div className='Signup_Area_Employee_No_Error'>
                        <div className='Signup_Form_Employee'>
                            <div className='Selection_Frame'>
                                <label className='Selection_Title'>
                                    Please Select Account Type
                                </label>
                                <button onClick={handleOpen} className='Selection_Button'>
                                    <div className='Selection_Button_Placeholder'>
                                        {
                                            !customer && !employee ? 'N/A' : (customer ? 'Customer' : 'Employee')
                                        }
                                    </div>
                                </button>
                                {open ? (
                                    <ul className="menu">
                                    <li className="menu-item">
                                        <button onClick={handleSelectCustomer}>Customer</button>
                                    </li>
                                    <li className="menu-item">
                                        <button onClick={handleSelectEmployee}>Employee</button>
                                    </li>
                                    </ul>
                                ) : null}
                            </div>
                            <div className='Submission_Frame_Employee'>
                                <form onSubmit={registerRequest} className='Submission_Area_Employee'>
                                    <label className='Username_Employee_Title'>
                                        Username
                                    </label>
                                    <input 
                                    type='text'
                                    className='Username_Employee_Input'
                                    value={firstName}
                                    placeholder='ex. George'
                                    onChange={handleChangeFirstName}/>

                                    <label className='Password_Employee_Title'>
                                        Password
                                    </label>
                                    <input 
                                    type='text'
                                    className='Password_Employee_Input'
                                    value={middleName}
                                    placeholder='ex. W.'
                                    onChange={handleChangeMiddleName}/>

                                    <label className='Role_Title'>
                                        Role
                                    </label>
                                    <input 
                                    type='text'
                                    className='Role_Input'
                                    value={lastName}
                                    placeholder='Bush'
                                    onChange={handleChangeLastName}/>

                                    <label className='Email_Employee_Title'>
                                        Email
                                    </label>
                                    <input 
                                    type='text'
                                    className='Email_Employee_Input'
                                    value={lastName}
                                    placeholder='Bush'
                                    onChange={handleChangeLastName}/>

                                    <button className='Signup_Button_Employee'>
                                        <div className='Signup_Button_Title'>
                                            Sign up
                                        </div>
                                    </button>
                                </form>
                                
                                <button className="Back_Button_Employee">
                                    <div className='Back_Button_Customer_Title'>
                                        Back
                                    </div>
                                </button>
                            </div>
                        </div>
                    </div>)) : (customer ? (
                    <div className='Signup_Area_Customer_Error'>
                        <div className='Signup_Form_Customer'>
                            <div className='Selection_Frame'>
                                <label className='Selection_Title'>
                                    Please Select Account Type
                                </label>
                                <button onClick={handleOpen} className='Selection_Button'>
                                    <div className='Selection_Button_Placeholder'>
                                        {
                                            !customer && !employee ? 'N/A' : (customer ? 'Customer' : 'Employee')
                                        }
                                    </div>
                                </button>
                                {open ? (
                                    <ul className="menu">
                                    <li className="menu-item">
                                        <button onClick={handleSelectCustomer}>Customer</button>
                                    </li>
                                    <li className="menu-item">
                                        <button onClick={handleSelectEmployee}>Employee</button>
                                    </li>
                                    </ul>
                                ) : null}
                            </div>
                            <div className='Submission_Frame_Customer'>
                                <form onSubmit={registerRequest} className='Submission_Area_Customer'>
                                    <label className='First_Name_Title'>
                                        First Name
                                    </label>
                                    <input 
                                    type='text'
                                    className='First_Name_Input'
                                    value={firstName}
                                    placeholder='ex. George'
                                    onChange={handleChangeFirstName}/>

                                    <label className='Middle_Name_Title'>
                                        Middle Name (N/A if None)
                                    </label>
                                    <input 
                                    type='text'
                                    className='Middle_Name_Input'
                                    value={middleName}
                                    placeholder='ex. W.'
                                    onChange={handleChangeMiddleName}/>

                                    <label className='Last_Name_Title'>
                                        Last Name
                                    </label>
                                    <input 
                                    type='text'
                                    className='Last_Name_Input'
                                    value={lastName}
                                    placeholder='Bush'
                                    onChange={handleChangeLastName}/>

                                    <label className='Email_Title'>
                                        Email
                                    </label>
                                    <input 
                                    type='text'
                                    className='Email_Input'
                                    value={email}
                                    placeholder='example@example.com'
                                    onChange={handleChangeEmail}/>

                                    <label className='Phone_Title'>
                                        Phone
                                    </label>
                                    <input 
                                    type='text'
                                    className='Phone_Input'
                                    value={phoneNumber}
                                    placeholder="ex. (123)456-7890"
                                    onChange={handleChangePhoneNumber}/>
                                    <label className='IdType_Title'>
                                        Id Type
                                    </label>
                                    <input 
                                    type='text'
                                    className='IdType_Input'
                                    value={idType}
                                    placeholder="Abbreviated form (ex. P -> Passport)"
                                    onChange={handleChangeIdType}/>

                                    <label className='IdNo_Title'>
                                        Id Number
                                    </label>
                                    <input 
                                    type='text'
                                    className='IdNo_Input'
                                    value={idNumber}
                                    onChange={handleChangeIdType}/>

                                    <label className='Username_Title'>
                                        Username
                                    </label>
                                    <input
                                    type='text'
                                    className='Username_Input'
                                    value={username}
                                    onChange={handleChangeUsername}/>

                                    <label className='Password_Title'>
                                        Password
                                    </label>
                                    <input 
                                    type='password'
                                    className='Password_Input'
                                    value={pwd}
                                    onChange={handleChangePwd}/>

                                    <button className='Signup_Button'>
                                        <div className='Signup_Button_Title'>
                                            Sign up
                                        </div>
                                    </button>
                                </form>

                                <button className="Back_Button_Customer">
                                    <div className='Back_Button_Customer_Title'>
                                        Back
                                    </div>
                                </button>
                            </div>
                        </div>
                    </div>
                    ) : (<div className='Signup_Area_Employee_Error'>
                        <div className='Signup_Form_Customer'>
                            <div className='Selection_Frame'>
                                <label className='Selection_Title'>
                                    Please Select Account Type
                                </label>
                                <button onClick={handleOpen} className='Selection_Button'>
                                    <div className='Selection_Button_Placeholder'>
                                        {
                                            !customer && !employee ? 'N/A' : (customer ? 'Customer' : 'Employee')
                                        }
                                    </div>
                                </button>
                                {open ? (
                                    <ul className="menu">
                                    <li className="menu-item">
                                        <button onClick={handleSelectCustomer}>Customer</button>
                                    </li>
                                    <li className="menu-item">
                                        <button onClick={handleSelectEmployee}>Employee</button>
                                    </li>
                                    </ul>
                                ) : null}
                            </div>
                            <div className='Submission_Frame_Customer'>
                                <form onSubmit={registerRequest} className='Submission_Area_Employee'>
                                    <label className='Username_Employee_Title'>
                                        Username
                                    </label>
                                    <input 
                                    type='text'
                                    className='Username_Employee_Input'
                                    value={firstName}
                                    placeholder='ex. George'
                                    onChange={handleChangeFirstName}/>

                                    <label className='Password_Employee_Title'>
                                        Password
                                    </label>
                                    <input 
                                    type='text'
                                    className='Password_Employee_Input'
                                    value={middleName}
                                    placeholder='ex. W.'
                                    onChange={handleChangeMiddleName}/>

                                    <label className='Role_Title'>
                                        Role
                                    </label>
                                    <input 
                                    type='text'
                                    className='Role_Input'
                                    value={lastName}
                                    placeholder='Bush'
                                    onChange={handleChangeLastName}/>

                                    <label className='Email_Employee_Title'>
                                        Email
                                    </label>
                                    <input 
                                    type='text'
                                    className='Email_Employee_Input'
                                    value={lastName}
                                    placeholder='Bush'
                                    onChange={handleChangeLastName}/>
                                </form>
                                
                                <button className='Signup_Button_Employee'>
                                        <div className='Signup_Button_Title'>
                                            Sign up
                                        </div>
                                    </button>
                                <button className="Back_Button_Employee">
                                    <div className='Back_Button_Customer_Title'>
                                        Back
                                    </div>
                                </button>
                            </div>
                        </div>
                    </div>))
                }
            </div>
        </div>
        // <div className="Form-Page">
        //     <header className="Sign_in_Title">
        //         <h1>
        //             Sign Up for Customer
        //         </h1>
        //     </header>
        //     <form className='Input'>
        //         <div className="InputBox">
        //             <div>
        //                 <label>First Name</label>
        //             </div>

        //             <input
        //             type="text"
        //             name="firstName"
        //             placeholder= "Michael"
        //             className="InputBox"
        //             value={firstName}
        //             onChange={handleChangeFirstName}/>

        //             <div>
        //                 <label>Middle Name</label>
        //             </div>

        //             <input
        //             type="text"
        //             name="middleName"
        //             placeholder= "Leave Blank If N/A"
        //             className="InputBox"
        //             value={middleName}
        //             onChange={handleChangeMiddleName}/>

        //             <div>
        //                 <label>Last Name</label>
        //             </div>

        //             <input
        //             type="text"
        //             name="lastName"
        //             placeholder= "Jorden"
        //             className="InputBox"
        //             value={lastName}
        //             onChange={handleChangeLastName}/>

        //             <div>
        //                 <label>Email Address</label>
        //             </div>

        //             <input
        //             type="text"
        //             name="email"
        //             placeholder= "email"
        //             className="InputBox"
        //             value={email}
        //             onChange={handleChangeEmail}/>

        //             <div>
        //                 <label>Phone Number</label>
        //             </div>

        //             <input
        //             type="text"
        //             name="phoneNumber"
        //             placeholder= "(123)456-7890"
        //             className="InputBox"
        //             value={phoneNumber}
        //             onChange={handleChangePhoneNumber}/>

        //             <div>
        //                 <label>ID Type</label>
        //             </div>

        //             <input
        //             type="text"
        //             name="idType"
        //             placeholder= "Ex. Passport"
        //             className="InputBox"
        //             value={idType}
        //             onChange={handleChangeIdType}/>

        //             <div>
        //                 <label>ID No.</label>
        //             </div>

        //             <input
        //             type="text"
        //             name="idNo"
        //             placeholder= "Ex. Passport No."
        //             className="InputBox"
        //             value={idNumber}
        //             onChange={handleChangeIdNumber}/>

        //             <div>
        //                 <label> Username </label>
        //             </div>

        //             <input
        //             type="text"
        //             name="username"
        //             placeholder= "Username"
        //             className="InputBox"
        //             value={username}
        //             onChange={handleChangeUsername}/>

        //             <div>
        //                 <label> Password </label>
        //             </div>

        //             <input
        //             type="password"
        //             name="pwd"
        //             placeholder="Password"
        //             className="InputBox"
        //             value={pwd}
        //             onChange={handleChangePwd}/>

        //             <div>
        //                 <button onClick={registerRequest}>
        //                     Register
        //                 </button>
        //                 <button onClick={navigateToLogIn}>
        //                     back
        //                 </button>
        //             </div>
        //             <h2 className="wrong_pwd">{cust_signup_error}</h2>
        //         </div>
        //     </form>
        // </div>
    );
}