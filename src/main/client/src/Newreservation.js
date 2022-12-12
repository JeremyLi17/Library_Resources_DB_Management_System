import './Newreservation.css';
import { useNavigate } from 'react-router';
// import TextField from '@material-ui/core/TextField';

function Newreservation() {
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
      <div className="Newreservation"> 
          <label for="cars">Choose a time period:</label>
            <select name="cars" id="cars">
                <option value="1">1</option>
                <option value="2">2</option>
                <option value="3">3</option>
                <option value="4">4</option>
            </select>
            <label for="start">Choose a date:</label>
            <input type="date" id="start" name="trip-start"
            // value="2011-07-22"
            min="2000-01-01" 
            />

          <form >
              <div>
                  <button>Search</button>
              </div>
              
          </form>

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
export default Newreservation;