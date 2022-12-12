import './Managereservation.css';
import { useNavigate } from 'react-router';

function Managereservation() {
  const navigate = useNavigate();
  const navigatetologin = () =>{
    navigate('/*');
  }
  const navigatetohome = () =>{
    navigate('/*');
  }
  const navigatetoback = () =>{
    navigate('/studyroom/*');
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

        

    </div>
  );
}
export default Managereservation;