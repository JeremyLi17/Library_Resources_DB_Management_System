import { useNavigate } from 'react-router';
import './App.css';

function App() {
  const navigate = useNavigate();
  const navigatetostudyroom = () =>{
    navigate('/studyroom/*');
  }
  const navigatetobookrental = () =>{
    navigate('/book/*');
  }
  const navigatetoexibition = () =>{
    navigate('/exibition/*');
  }
  const navigatetologin = () =>{
    navigate('/*');
  }
  return (

    <div className="App">
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


      <header className="App-header">
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

export default App;
