import './Newreservation.css';
import { useNavigate } from 'react-router';
import { useState } from 'react';
import axios from 'axios';
// import TextField from '@material-ui/core/TextField';
//  axios.defaults.headers.common["Authorization"] = `Bearer ${localStorage.getItem("token")}`;
function Newreservation() {
  const navigate = useNavigate();
  const [timeperiod, settimeperiod] = useState();
  const [date, setdate] = useState();
  const [studyroomid, setreservationid] = useState();
  const [studyrooms, setStudyRooms] = useState(null);
  const now = moment().tz('America/New_York');

  const navigatetologin = () =>{
    navigate('/*');
  }
  const navigatetohome = () =>{
    navigate('/customer/*');
  }
  const navigatetoback = () => {
    navigate('/customer/studyroom/*');
  }
  const searchresult = async (event) => {
    event.preventDefault;
    // setbookname(targetbookname);
    console.log(timeperiod);
    console.log(date);
    const url =  `http://localhost:8080/api/studyroom/list?date=${date}&timeslot=${timeperiod}`
    const config = {
      Headers: {
        "Authorization": `Bearer ${localStorage.getItem("token")}`
      }
    }
     await axios.get(url, config).then(
      (res) => {
        console.log(res);
        const result = res.data;
        setStudyRooms(result);
        // setcapacity(result.ROOM_CAPACITY);
      }
    ).catch((error) => {
      console.log(error);
    })
  }

  const doreserve = async (event) => {
    //need to submitted to backend
    event.preventDefault;
    const url =  `http://localhost:8080/api/resercation/add`
    const config = {
      Headers: {
        "Authorization": `Bearer ${localStorage.getItem("token")}`
      }
    }
    await axios.post(url, 
      {
        "customer": {
          "username": "test"
        },
        "date": date,
        "timeslot": timeperiod,
        "studyRoom": {
          "id": studyroomid
      }
      },
      config).then(
      (res) => {
        
        const result = res.data;

      }
    ).catch((error) => {
      console.log(error);
    })
    
  }
  
    return (
      <div className="Newreservation"> 
          <label for="timeperiod">Choose a time period:</label>
            <select name="timeperiod" id="timeperiod">
                value = {timeperiod}
                <option value="A">8AM-10AM</option>
                <option value="B">10AM-12PM</option>
                <option value="C">2PM-4PM</option>
                <option value="D">4PM-6PM</option>
                onChange={(e) => settimeperiod(e.target.value)}
            </select>
            <label for="start">Choose a date:</label>
            <input type="date" id="start" name="trip-start"
            // value="2011-07-22"
            value = {date}
            min= {now}
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
            <ul>
              {studyrooms.map((studyroom) => {
                return <li key={studyroom.index}>
                  studyroomId: {studyroom.id}, capacity: {studyroom.capacity}
                </li>
              })}
            </ul>
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