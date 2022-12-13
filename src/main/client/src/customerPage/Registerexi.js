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
  const [exibitionid, setregiexibition] = useState();
  const [exhibitions, setexibitions] = useState(null);
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
    console.log(timeperiod);
    console.log(date);
    const url =  `http://localhost:8080/api/event/list/exhibition?topic=${exhibition}`
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


  const doregister = event => {
    //need to submitted to backend
    
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

        <div>
              studyrooms.length === 0 ? <h1>No Available Room</h1> : studyrooms.map(generateStudyRoom);
            </div>



        <form className = "register">
            <label >complete register</label>
            <input type="text" id="myInput"  placeholder="type exibition id ..." value = {exibitionid} onChange={(e) => setregiexibition(e.target.value)}></input>
            <div>
            <button onClick={doregister}>register</button>
            </div>
          </form>



      </div>
    );
  }
export default Registerexi;