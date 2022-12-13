import './Registerexi.css';
import { useNavigate } from 'react-router';
import { useState } from 'react';
import axios from 'axios';
// import TextField from '@material-ui/core/TextField';
// axios.defaults.headers.common["Authorization"] = `Bearer ${localStorage.getItem("token")}`;
function Registerexi() {
  const navigate = useNavigate();
  const [exhibition, setexibitionname] = useState();
  const [targetexibition, settargetexibition] = useState();
  const [exhibitionid, setregiexibition] = useState();
  const [exhibitions, setexibitions] = useState([]);
  const navigatetologin = () =>{
    navigate('/*');
  }
  const navigatetohome = () =>{
    navigate('/customer/*');
  }
  const navigatetoback = () =>{
    navigate('/customer/exibition/*');
  }
  const searchresult = async (event) => {
    event.preventDefault;
    // setbookname(targetbookname);
    const url =  `http://localhost:8080/api/event/list/exhibition?topic=${String(exhibition)}`
    const config = {
      headers: {
        "Authorization": `Bearer ${localStorage.getItem("token")}`
      }
    }
     await axios.get(url, config).then(
      (res) => {
        console.log(res);
        const result = res.data;
        setexibitions(result);
        // setcapacity(result.ROOM_CAPACITY);
      }
    ).catch((error) => {
      console.log(error);
    })
  }


  const doregister = async(event) => {
    //need to submitted to backend
    event.preventDefault();
    const config = {
      headers: {
        "Authorization": `Bearer ${localStorage.getItem("token")}`
      }
    }
    const url =  `http://localhost:8080/api/customerExhibition/add`
    await axios.post(url, 
      {
        "customer": {
          "username": "audrey"
        },
        "exhibitionEvent":{
          "id": exhibitionid
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
      <div className="Registerexi">
           <header >
            <h1>Exibition Search</h1>
            </header>
         <div class="Search-container">
          <input type="text" id="myInput"  placeholder="Search for exibitions ..." value = {exhibition} onChange={(e) => setexibitionname(e.target.value)}></input>
          <button onClick = {searchresult} type="submit"><i class="fa fa-search">search</i></button>
          </div>
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

        



        <form className = "register">
            <label >complete register</label>
            <input type="text" id="myInput"  placeholder="type exibition id ..." value = {exhibitionid} onChange={(e) => setregiexibition(e.target.value)}></input>
            <div>
            <button onClick={doregister}>register</button>
            </div>
          </form>


          <ul>
            <h1>Search result</h1>
              {exhibitions.map((exhibition) => {
                return <li key={exhibition.id}>
                  <h4>id:{exhibition.id}</h4>
                  <h4>Name: {exhibition.event.name}</h4>
                  <h4>Start at:{exhibition.event.startAt}</h4>
                  <h4>Stop at:{exhibition.event.stopAt}</h4>
                </li>
              })}
            </ul>



      </div>
    );
  }
export default Registerexi;