import '../employeePageAttribute/EmployeeDashboard.css';
import {useState} from 'react';
import {Routes, Route, useNavigate} from 'react-router-dom';
import axios from 'axios';

const emp_username = localStorage.getItem('emp_username');
const emp_email = localStorage.getItem('emp_email');
const emp_role = localStorage.getItem('emp_role');

export default function EmployeeDashboard() {

  // console.log(localStorage.getItem("emp_username"));
  // console.log(emp_email);
  // console.log(emp_role);

  const navigate = useNavigate();

  const navigateToLogIn = () => {
    navigate('/', {replace: true});
  }

  const config = {
    headers: {
      'Authorization': `Bearer ${localStorage.getItem("token")}`
    }
  }

  const [targetUsername, setTargetUsername] = useState();
  
  const [username, setUsername] = useState();
  const [firstName, setFirstName] = useState();
  const [lastName, setLastName] = useState();
  const [email, setEmail] = useState();
  const [phoneNumber, setPhoneNumber] = useState();
  const [idType, setIdType] = useState();
  const [idNumber, setIdNumber] = useState();
  const [id, setId] = useState();

  const handleChangeTargetUsername = event => {
    setTargetUsername(event.target.value);
  }

  const handleChangeUsername = event => {
    setUsername(event.target.value);
  }

  const handleChangeFirstName = event => {
    setFirstName(event.target.value);
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

  

  const navigateToReservation = async (e) => {
    const res = await axios.get(
      "http://localhost:8080/api/reservation/list/" + id,
      config
    ).then((res) => {
      localStorage.setItem("reserveList", res.data);
      navigate('/employee/reservation/*');
    }).catch((e) => {
      
    })
  }

  const navigateToRental = () => {
    navigate('/employee/rental/*');
  }
  
  const handleSearch = async (e) => {

    e.preventDefault();

    // setTargetUsername(e.target.value);
    
    console.log(targetUsername);
    const res = await axios.get(
      "http://localhost:8080/api/customer/find/" + targetUsername,
      config
    ).then((res) => {
      // console.log(res);
      setUsername(res.data['username']);
      setFirstName(res.data['firstName']);
      setLastName(res.data['lastName']);
      setEmail(res.data['email']);
      setPhoneNumber(res.data['phoneNo']);
      setIdType(res.data['idType']);
      setIdNumber(res.data['idNo']);
      setId(res.data['customerId']);
    })
    //     console.log(res2);
  };

  const handleUpdate = event => {
    event.preventDefault();

    setFirstName(event.target.value);
    setLastName(event.target.value);
    setEmail(event.target.value);
    setPhoneNumber(event.target.value);
    setIdType(event.target.value);
    setIdNumber(event.target.value);

    console.log("update completed");
    console.log("email: ", email);
    console.log("phone: ", phoneNumber);
  }

  return (
      
    <div className='Employee'>
      <div className='Home_Bar'>
        <Emp_profile/>
        <Signout/>
      </div>

      <div className='Home_Customer'>
        <div className='Home_Customer_Detail'>
          <form className='Home_Customer_Detail_Input' onSubmit={handleSearch}>
            <div className='Home_Customer_Detail_Input_Frame'>
              <label>Username</label>
            </div>
            <div className='Home_Customer_Detail_Input_Frame'>
              <input
              type="text"
              name="targetUsername"
              placeholder="username"
              value={targetUsername}
              onChange={handleChangeTargetUsername}
              />
            </div>
            <div className='Home_Customer_Detail_Input_Frame'>
              <button>
                Search
                <div>{}</div>
              </button>
            </div>
          </form>
          <form className='Home_Customer_Detail_List'>
            <div className='Customer_First_Name'>
              Customer Username:
              <input
              type="text"
              name="username"
              value={username}
              onChange={handleChangeUsername}
              />
            </div>
            <div className='Customer_First_Name'>
              Customer First Name:
              <input
              type="text"
              name="first_name"
              value={firstName}
              onChange={handleChangeFirstName}
              />
            </div>
            <div className='Customer_Last_Name'>
              Customer Last Name:
              <input
              type="text"
              name="last_name"
              value={lastName}
              onChange={handleChangeLastName}
              />
            </div>
            <div className='Customer_Email'>
              Customer Email:
              <input
              type="text"
              name="email"
              value={email}
              onChange={handleChangeEmail}
              />
            </div>
            <div className='Customer_Phone_Number'>
              Customer Mobile Phone:
              <input
              type="text"
              name="phone"
              value={phoneNumber}
              onChange={handleChangePhoneNumber}
              />
            </div>
            <div className='Customer_Id_Type'>
              Customer ID:
              <input
              type="text"
              name="id"
              value={idType}
              onChange={handleChangeIdType}
              />
            </div>
            <div className='Customer_Id_Number'>
              Customer ID Number:
              <input
              type="text"
              name="id_number"
              value={idNumber}
              onChange={handleChangeIdNumber}
              />
            </div>
            <div className='Customer_Others'>
              <button onClick={navigateToRental}>
                Rental Info
              </button>
              <button onClick={navigateToReservation}>
                Reservation Info
              </button>
              <button onClick={handleUpdate}>
                Update All
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

// export default App;
