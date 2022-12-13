import './Managereservation.css';
import { useNavigate } from 'react-router';
import { useEffect, useState } from 'react';
import axios from 'axios';


const Managereservation = () => {
  
  const navigate = useNavigate();
  const [reservations, setreservations] = useState([]);
  const [cancelId, setCancelId] = useState();

  const getAllres = () => {
    const config = {
      headers: {
        "Authorization": `Bearer ${localStorage.getItem("token")}`
      }
    }
    const customerId = Number(localStorage.getItem("currentCustomerId"));
  
    axios.get(`http://localhost:8080/api/reservation/list/${customerId}`, config).then((response) => {
      setreservations([])
      var list = []
      for (var obj in response.data) {
        // reservations.push(response.data[obj])
        list.push(response.data[obj])
      }
      console.log("here")
      setreservations(list)
  }).catch((e) => {
    console.log(e);
  })
  }

  useEffect(() => {
    getAllres();
  }, [])

  const navigatetologin = () =>{
    localStorage.clear();
    navigate('/*');
  }
  const navigatetohome = () =>{
    navigate('/customer/*');
  }
  const navigatetoback = () =>{
    navigate('/customer/studyroom/*');
  }
  const docancelreserve = async(event)=> {

    event.preventDefault();
    //need to submitted to backend
    const url = `http://localhost:8080/api/reservation/delete?id=${cancelId}`
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


      



        <form>
          <div>
          <label >cancel reservation</label>
            <input type="text" id="myInput"  placeholder="type rental id ..." value = {cancelId} onChange={(e) => setCancelId(e.target.value)}></input>
            <div>
            <button onClick={docancelreserve}>cancel</button>
            </div>
          </div>
          
        </form>



        

    </div>
  );
}

// Managereservation.getInitalProps = async (context) =>{
//   console.log("here")
//   const result = await axios.get(`http://localhost:8080/api/reservation/list/{customerId}`)

//   return{ reservations : result};
// }

export default Managereservation;