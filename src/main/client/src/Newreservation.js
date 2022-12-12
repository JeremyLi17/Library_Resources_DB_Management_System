import './Newreservation.css';
import { useNavigate } from 'react-router';
import { useState } from 'react';
// import TextField from '@material-ui/core/TextField';

function Newreservation() {
  const navigate = useNavigate();
  const [timeperiod, settimeperiod] = useState();
  const [date, setdate] = useState();
  const [studyroomid, setreservationid] = useState();
  const navigatetologin = () =>{
    navigate('/*');
  }
  const navigatetohome = () =>{
    navigate('/*');
  }
  const navigatetoback = () =>{
    navigate('/studyroom/*');
  }
  const searchresult = event => {
    event.preventDefault;
    // setbookname(targetbookname);
    console.log("exibition name: ", exibition);
    console.log("submitted!")
  }
  const doreserve = event => {
    //need to submitted to backend
    
  }
  
    return (
      <div className="Newreservation"> 
          <label for="timeperiod">Choose a time period:</label>
            <select name="timeperiod" id="timeperiod">
                value = {timeperiod}
                <option value="1">1</option>
                <option value="2">2</option>
                <option value="3">3</option>
                <option value="4">4</option>
                onChange={(e) => settimeperiod(e.target.value)}
            </select>
            <label for="start">Choose a date:</label>
            <input type="date" id="start" name="trip-start"
            // value="2011-07-22"
            value = {date}
            min="2000-01-01" 
            onChange={(e) => setdate(e.target.value)}
            />

          <form >
              <div>
                  <button onClick={searchresult} type="submit" ><i class="fa fa-search">search</i></button>
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
        <form className = "newreservation">
            <label >reserve studyroom</label>
            <input type="text" id="myInput"  placeholder="type studyroom id ..." value = {studyroomid} onChange={(e) => setreservationid(e.target.value)}></input>
            <div>
            <button onClick={doreserve}>reserve</button>
            </div>
          </form>

        
        
  
      </div>
    );
  }
export default Newreservation;