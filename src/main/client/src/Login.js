import React, { Component, useEffect } from 'react'
import {useNavigate} from 'react-router-dom';
import './Login.css';
import {useState} from 'react';
import axios from 'axios';
import Logo from './library-svgrepo-com.svg'
import "@fontsource/abel";

export default function Login() {

    const navigate = useNavigate();
    const [open, setOpen] = React.useState(false);
    const [username, setUsername] = useState('');
    const [pwd, setPwd] = useState('');
    const [login_err, setLogin_err] = useState('');
    const [employee, setEmployee] = useState(false);
    const [customer, setCustomer] = useState(false);

    const customerLogInRequest = async (e) => {
        e.preventDefault();

        setUsername(e.target.value);
        setPwd(e.target.value);
        
        const data = {
            username: username,
            password: pwd
        };
        
        const res = await axios.post(
            "http://localhost:8080/api/customer/login",
            data
        ).then((res)=> {
            console.log(res.data);
            localStorage.setItem("token", res.headers["jwt-token"]);
            localStorage.setItem("currentUsername", res.data['username']);
            localStorage.setItem("customer_email", res.data['email']);
            localStorage.setItem("customer_firstName", res.data['firstName']);
            localStorage.setItem("customer_lastName", res.data['lastName']);
            localStorage.setItem("customer_phoneNo", res.data['phoneNo']);
            localStorage.setItem("customer_idType", res.data['idType']);
            localStorage.setItem("customer_idNo", res.data['idNo']);
            localStorage.setItem("currentUserId", res.data['id']);
            
            sessionStorage.removeItem('err');
            useEffect(() => {
                navigateToCustomerDashBoard();
            })
        }).catch((e) => {
            console.log(e.response.data['message']);
            setLogin_err(e.response.data['message']);
            navigate('/session');
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
            username: username,
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
            useEffect(() => {
                navigateToEmployeeDashboard();
            })
        }).catch((e) => {
            setLogin_err(e.response.data['message']);
            navigate('/session');
        })
    };
    
    const logInRequest = (e) => {
        e.preventDefault();
        if (employee) {
            employeeLogInRequest(e);
        } else if (customer) {
            customerLogInRequest(e);
        } else {
            navigate('/session');
        }
    }

    const navigateToRegistration = () => {
        useEffect(() => {
            navigate('/register/*');
        })
    }

    const handleChangeUsername = event => {
        setUsername(event.target.value);
        console.log(username);
    };
    
    const handleChangePwd = event => {
        setPwd(event.target.value);
    };

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
        <div className='Login_Page'>
            <div className='Login_Frame'>
                <img src={Logo} className='Logo_svg'/>
                <span className='Login_Title'>
                    Sign in to Real Management System
                </span>
                {
                    window.location.pathname === '/session' ? (
                        <div className='Error_Notice'>
                            <div className='Error_Notice_First_Line'>
                                Login failed: Please check the following:
                            </div>
                            <div className='Error_Notice_Second_Line'>
                                * Username or Password
                            </div>
                            <div className='Error_Notice_Third_Line'>
                                * Account Type is Empty
                            </div>
                        </div>
                    ) : (<div></div>)
                }
                {
                    window.location.pathname !== '/session' ? 
                    (<div className='Login_Area_No_Error'>
                        <div className='Login_Form'>
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
                            <form onSubmit={logInRequest} className='Submission_Frame'>
                                <label className='Username_Title__Login'>
                                    Username
                                </label>
                                <input 
                                className='Username_Input_Login'
                                type='text'
                                value={username}
                                onChange={handleChangeUsername}
                                />
                                <div className='Password_Frame'>
                                    <label className='Password'>
                                        Password
                                    </label>
                                    <a href='http://localhost:3000/customer-pwd-reset/*' className='Forgot_Password'>
                                        Forgot Password?
                                    </a>
                                </div>
                                <input
                                className='Password_Input_Login'
                                type='password'
                                name="Password"
                                value={pwd}
                                onChange={handleChangePwd}/>

                                <button className='Signin_Button'>
                                    <div className='Signin_Title'>
                                        Sign in
                                    </div>
                                </button>
                            </form>
                        </div>
                    </div>) : (<div className='Login_Area_Error'>
                        <div className='Login_Form'>
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
                            <form onSubmit={logInRequest} className='Submission_Frame'>
                                <label className='Username_Title_Login'>
                                    Username
                                </label>
                                <input 
                                className='Username_Input_Login'
                                type='text'
                                value={username}
                                onChange={handleChangeUsername}
                                />
                                <div className='Password_Frame'>
                                    <label className='Password'>
                                        Password
                                    </label>
                                    <a href='http://localhost:3000/customer-pwd-reset/*' className='Forgot_Password'>
                                        Forgot Password?
                                    </a>
                                </div>
                                <input
                                className='Password_Input_Login'
                                type='password'
                                name="Password"
                                value={pwd}
                                onChange={handleChangePwd}/>

                                <button className='Signin_Button'>
                                    <div className='Signin_Title'>
                                        Sign in
                                    </div>
                                </button>
                            </form>
                        </div>
                    </div>
                    )
                }
                {
                    window.location.pathname !== '/session' ? (
                        <div className='Register_Frame_No_Error'>
                            <div className='Register_Title'>New to Real?</div>
                            <a href={customer ? 'http://localhost:3000/register' : 'http://localhost:3000/employeeRegister/*'} className='Registration'>
                                Create an account
                            </a>
                        </div>
                    ) : (
                        <div className='Register_Frame_Error'>
                            <div className='Register_Title'>New to Real?</div>
                            <a href={customer ? 'http://localhost:3000/register' : 'http://localhost:3000/employeeRegister/*'} className='Registration'>
                                Create an account
                            </a>
                        </div>
                    )
                }
            </div>
        </div>
        // <div className="Form-Page">
        //     <header className="Sign_in_Title">
        //         <h1>
        //             Sign in to Real Management System
        //         </h1>
        //     </header>
        //     <form className='Input'>
        //         <div className="InputBox">
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
        //                 <button onClick={customerLogInRequest}>Customer Sign in</button>
        //                 <button onClick={employeeLogInRequest}>Employee Sign in</button>
        //             </div>

        //             <div>
        //                 <button onClick={navigateToCustomerPwdReset}>Forgot Password (Customer)</button>
        //                 <button onClick={navigateToEmployeePwdReset}>Forgot Password (Employee)</button>
        //             </div>

        //             <div>
        //                 <button onClick={navigateToCustomerRegistration}>Register for Customer</button>
        //                 <button onClick={navigateToEmployeeRegistration}>Register for Employee</button>
        //             </div>

        //             <h1 className="wrong_pwd">{login_err}</h1>

        //         </div>
        //     </form>
        // </div>
    )
}