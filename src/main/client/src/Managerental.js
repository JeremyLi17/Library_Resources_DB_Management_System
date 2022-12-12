import './Managerental.css';
import { useNavigate } from 'react-router';
// import TextField from '@material-ui/core/TextField';

function Managerental() {
  const navigate = useNavigate();
  const navigatetologin = () =>{
    navigate('/*');
  }
  const navigatetohome = () =>{
    navigate('/*');
  }
  const navigatetoback = () =>{
    navigate('/book/*');
  }
    return (
      <div className="Managerental">
           <header >
            <h1>MY RENTAL</h1>
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
export default Managerental;