import { useNavigate } from 'react-router';
import './Bookrentalsystem.css';

function BookrentalSystem() {
    const navigate = useNavigate();
    const navigatetorental = () =>{
        navigate('/book/rent/*');
      }
    const navigatetomanage = () =>{
        navigate('/book/manage/*');
    }
    const navigatetologin = () =>{
        navigate('/*');
    }
    const navigatetohome = () =>{
        navigate('/*');
    }

  return (
    <div className="BookrentalSystem">
        <header className="BookrentalSystemhead">
            <h1>BookrentalSystem</h1>
        </header>

        <form className = "Menubar1">
            <div>
                
                <button onClick = {navigatetorental} >Rent Book</button>
            </div>
            <div>
                <button onClick = {navigatetomanage} >My rental</button>
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
export default BookrentalSystem;