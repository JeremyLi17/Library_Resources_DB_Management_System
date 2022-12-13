import './Manageexi.css';
import { useNavigate } from 'react-router';
import { useState } from 'react';
// import TextField from '@material-ui/core/TextField';

function Manageexi() {
  const navigate = useNavigate();
  const [cancelid, setcancelid] = useState();
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
  const docancel = event => {
    //need to submitted to backend
    
  }




    return (
      <div className="Manageexi">
        <header>
          <h1>MY RENTAL</h1>
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

        <form>
          <div>
          <label >cancel register</label>
            <input type="text" id="myInput"  placeholder="type exibition id ..." value = {cancelid} onChange={(e) => setcancelid(e.target.value)}></input>
            <div>
            <button onClick={docancel}>cancel</button>
            </div>

          </div>
          
        </form>

        



        

      </div>

      
    );
  }
export default Manageexi;