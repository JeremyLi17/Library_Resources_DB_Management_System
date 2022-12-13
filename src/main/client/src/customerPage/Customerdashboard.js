import { useNavigate } from 'react-router';
import './Customerdashboard.css';

function Customerdashboard() {
  const navigate = useNavigate();
  const navigatetostudyroom = () =>{
    navigate('/customer/studyroom/*');
  }
  const navigatetobookrental = () =>{
    navigate('/customer/book/*');
  }
  const navigatetoexibition = () =>{
    navigate('/customer/exibition/*');
  }
  const navigatetologin = () =>{
    localStorage.clear();
    navigate('/*');
  }
  return (

    <div className="Customerdashboard">
      <div className='Home_LeftBar'>
            <div className='Home_LeftBar_Emp_Detail'>
              <div className='Home_LeftBar_Emp_Detail_Name_Email'>
                <div className='Home_LeftBar_Emp_Detail_Name'>
                  <span className='Home_LeftBar_Emp_Detail_Name_Span'>
                    IRIS
                  </span>
                </div>
                <div className='Home_LeftBar_Emp_Detail_Email'>
                  <span className='Home_LeftBar_Emp_Detail_Email_Span'>
                    kxiao855@gmail.com
                  </span>
                </div>
              </div>
            </div>
          </div>


      <header className="Customerdashboard-header">
        <h1>MENU</h1>
      </header>

      <form className = "Menubar">
        <div>
    
          <button onClick = {navigatetostudyroom} >Studyroom</button>  
        </div>
        <div>
          <button onClick = {navigatetoexibition}>Exibition</button>
        </div>
        <div>
          <button onClick = {navigatetobookrental}>Book rental</button>
        </div>
        <div>
          <button onClick = {navigatetologin}>Signout</button>
        </div>

      </form>

    </div>
  );
}

export default Customerdashboard;
