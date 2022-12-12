import './Manageexi.css';
import { useNavigate } from 'react-router';
// import TextField from '@material-ui/core/TextField';

function Manageexi() {
  const navigate = useNavigate();
  const navigatetologin = () =>{
    navigate('/*');
  }
  const navigatetohome = () =>{
    navigate('/*');
  }
  const navigatetoback = () =>{
    navigate('/exibition/*');
  }

    return (
      <div className="Manageexi">
        <header>
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
export default Manageexi;