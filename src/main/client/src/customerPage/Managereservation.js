import './Managereservation.css';
import { useNavigate } from 'react-router';
import { useState } from 'react';

function Managereservation() {
  const navigate = useNavigate();
  const [reserveid, setcancelreserve] = useState();
  const navigatetologin = () =>{
    navigate('/*');
  }
  const navigatetohome = () =>{
    navigate('/*');
  }
  const navigatetoback = () =>{
    navigate('/studyroom/*');
  }
  const docancelreserve = event => {
    //need to submitted to backend
    
  }
  return (
    <div className="Managereservation">
        <header >
            <h1>My reservation</h1>
        </header>
        <form className="Menu">
          <div>
            <button onClick={navigatetohome}>home</button>
          </div>
          <div>
            <button onClick={navigatetoback}>back</button>
          </div>
          <div>
            <button onClick={navigatetologin}>sign out</button>
          </div>

        </form>

        <form>
          <div>
          <label >cancel reservation</label>
            <input type="text" id="myInput"  placeholder="type rental id ..." value = {reserveid} onChange={(e) => setcancelreserve(e.target.value)}></input>
            <div>
            <button onClick={docancelreserve}>cancel</button>
            </div>

          </div>
          
        </form>



        

    </div>
  );
}
export default Managereservation;