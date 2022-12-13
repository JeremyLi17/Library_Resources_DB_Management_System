import './Manageexi.css';
import { useNavigate } from 'react-router';
import { useState, useEffect } from 'react';
import axios from 'axios';
// import TextField from '@material-ui/core/TextField';
// axios.defaults.headers.common["Authorization"] = `Bearer ${localStorage.getItem("token")}`;
function Manageexi() {
  const navigate = useNavigate();
  const [registrations, setregistrations] = useState([]);
  const [cancelid, setcancelid] = useState();
  const navigatetologin = () =>{
    navigate('/*');
  }
  const navigatetohome = () =>{
    navigate('/customer/*');
  }
  const navigatetoback = () =>{
    navigate('/customer/exibition/*');
  }

  const username = localStorage.getItem("currentUsername");

  const getAllres = () => {
    const config = {
      headers: {
        "Authorization": `Bearer ${localStorage.getItem("token")}`
      }
    }
    
    axios.get(`http://localhost:8080/api/customerExhibition/list/exhibition/${username}`, config).then((response) => {
      console.log(response.data)
      setregistrations([])
      var list = []
      for (var obj in response.data) {
        // reservations.push(response.data[obj])
        list.push(response.data[obj])
      }
      console.log("here")
      setregistrations(list)
  }).catch((e) => {
    console.log(e);
  })
  }
  useEffect(() => {
    getAllres();
  }, [])

  const customerId = Number(localStorage.getItem("currentCustomerId"));

  const docancel = async(event) => {
    //need to submitted to backend
    event.preventDefault();
    //need to submitted to backend
    const url = `http://localhost:8080/api/customerExhibition/delete?eventId=${cancelid}&customerId=${customerId}`
    const config = {
      headers: {
        "Authorization": `Bearer ${localStorage.getItem("token")}`
      }
    }
    await axios.delete(url, config)
    navigate('/customer/exibition/*');

    
  }



    return (
      <div className="Manageexi">
        <header>
          <h1>MY REGISTRATION</h1>
        </header>

        <ul>
        {registrations.map((registration) => {
        return (
          <li key={registration.id}>
            <h4>Exhibition id: {registration.event.id}</h4>
            <h4>Name: {registration.event.name}</h4>
            <h4>Start at:{registration.event.startAt}</h4>
            <h4>Stop at:{registration.event.stopAt}</h4>
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
          <label >cancel register</label>
            <input type="text" id="myInput"  placeholder="type exibition id ..." value = {cancelid} onChange={(e) => setcancelid(e.target.value)}></input>
            <div>
            <button onClick={docancel}>cancel and back</button>
            </div>

          </div>
          
        </form>

        



        

      </div>

      
    );
  }
export default Manageexi;