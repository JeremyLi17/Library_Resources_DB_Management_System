import './Managereservation.css';
import { useNavigate } from 'react-router';
import { useState } from 'react';
import axios from 'axios';
// axios.defaults.headers.common["Authorization"] = `Bearer ${localStorage.getItem("token")}`;
const Managereservation = ({reservations}) => {
  const navigate = useNavigate();
  const [reserveid, setcancelreserve] = useState();


  const navigatetologin = () =>{
    navigate('/*');
  }
  const navigatetohome = () =>{
    navigate('/customer/*');
  }
  const navigatetoback = () =>{
    navigate('/customer/studyroom/*');
  }
  const docancelreserve = async(event)=> {
    //need to submitted to backend
    const url = `http://localhost:8080/api/reservation/delete?id=${reserveid}`
    const config = {
      headers: {
        "Authorization": `Bearer ${localStorage.getItem("token")}`
      }
    }
    await axios.delete(url, config)
    navigate('/customer/studyroom/*');

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



        <ul>
        {reservations.map((reservation) => {
        return (
          <li key={reservation.id}>
            studyroom:{reservation.studyRoom.id}
            date:{reservation.date}
            timeslot:{reservation.timeslot}
          </li>
        );
         })}
        </ul>



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

Managereservation.getInitalProps = async (context) =>{
  const result = await axios.get(`http://localhost:8080/api/reservation/list/{customerId}`)
  return{ reservations : result};
}

export default Managereservation;