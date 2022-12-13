import './Registerexi.css';
import { useNavigate } from 'react-router';
import { useState } from 'react';
// import TextField from '@material-ui/core/TextField';

function Registerexi() {
  const navigate = useNavigate();
  const [exibition, setexibitionname] = useState();
  const [targetexibition, settargetexibition] = useState();
  const [exibitionid, setregiexibition] = useState();
  const navigatetologin = () =>{
    localStorage.clear();
    navigate('/*');
  }
  const navigatetohome = () =>{
    navigate('/customer/*');
  }
  const navigatetoback = () =>{
    navigate('/customer/exibition/*');
  }
  const searchresult = event => {
    event.preventDefault;
    // setbookname(targetbookname);
    console.log("exibition name: ", exibition);
    console.log("submitted!")
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
          <input type="text" id="myInput"  placeholder="Search for exibitions ..." value = {exibition} onChange={(e) => setexibitionname(e.target.value)}></input>
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
            <input type="text" id="myInput"  placeholder="type exibition id ..." value = {exibitionid} onChange={(e) => setregiexibition(e.target.value)}></input>
            <div>
            <button onClick={doregister}>register</button>
            </div>
          </form>



      </div>
    );
  }
export default Registerexi;