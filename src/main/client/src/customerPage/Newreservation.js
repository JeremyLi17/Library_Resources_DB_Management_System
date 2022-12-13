import './Newreservation.css';
import { useNavigate } from 'react-router';
import { useState } from 'react';
import axios from 'axios';
import moment from 'moment';

function Newreservation() {
  const navigate = useNavigate();
  const [date, setdate] = useState();
  const [studyroomid, setreservationid] = useState();
  const [studyrooms, setStudyRooms] = useState([]);
  const now = moment().format('YYYY-MM-DD');  

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
    event.preventDefault();
    console.log("here");
    // setbookname(targetbookname);

    // console.log(date);
    const slot = document.getElementById("timeperiod").value;
    console.log(slot);
    
    const url =  `http://localhost:8080/api/studyroom/list?date=${String(date)}&timeslot=${slot}`
    const config = {
      headers: {
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
    event.preventDefault();
    const slot = document.getElementById("timeperiod").value;

    const url =  `http://localhost:8080/api/reservation/add`
    const config = {
      headers: {
        "Authorization": `Bearer ${localStorage.getItem("token")}`
      }
    }
    const userID = localStorage.getItem("currentUsername")
    await axios.post(url, 
      {
        "customer": {
          "username": "test"
        },
        "date": date,
        "timeslot": slot,
        "studyRoom": {
          "id": studyroomid
      }
      },
      config).then(
      (res) => {
        const result = res.data;
        console.log("success");
      }
    ).catch(async (error) => {
      console.log(error);
      
    })

    
  }
  
    return (
      <div className="Newreservation"> 
          <label for="timeperiod">Choose a time period:</label>
            <select name="timeperiod" id="timeperiod" > 
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
                return <li key={studyroom.id}>
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