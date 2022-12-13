import { useNavigate } from 'react-router';
import './customerstudyroom.css';

function customerstudyroom() {
    const navigate = useNavigate();
    const navigatetoreservation = () =>{
        navigate('/customer/studyroom/reserve/*');
      }
    const navigatetomanagere = () =>{
        navigate('/customer/studyroom/manage/*');
    }
    const navigatetologin = () =>{
        localStorage.clear();
        navigate('/*');
    }
    const navigatetohome = () =>{
        navigate('/customer/*');
    }
  return (
    <div className="customerstudyroom">
        <header className="customerstudyroomhead">
            <h1>Studyroom system</h1>
        </header>

        <form className = "Menubar1">
            <div>
                <button onClick = {navigatetoreservation}>New Reservation</button>
            </div>
            <div>
                <button onClick = {navigatetomanagere}>Manage Reservation</button>
            </div>
            <div>
                <button onClick = {navigatetohome} >home</button>
            </div>
            <div>
                <button onClick = {navigatetologin} >sign out</button>
            </div>
        </form>

    </div>
  );
}
export default customerstudyroom;