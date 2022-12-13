import './Managerental.css';
import { useNavigate } from 'react-router';
import { useState } from 'react';
// import TextField from '@material-ui/core/TextField';

function Managerental() {
  const navigate = useNavigate();
  const [returnid, setreturnid] = useState();

  const navigatetologin = () =>{
    localStorage.clear();
    navigate('/*');
  }
  const navigatetohome = () =>{
    navigate('/customer/*');
  }
  const navigatetoback = () =>{
    navigate('/customer/book/*');
  }
  const doreturn = event => {
    //need to submitted to backend
    
  }

    return (
      <div className="Managerental">
           <header >
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
          <label >return book</label>
            <input type="text" id="myInput"  placeholder="type rental id ..." value = {returnid} onChange={(e) => setreturnid(e.target.value)}></input>
            <div>
            <button onClick={doreturn}>return</button>
            </div>

          </div>
          
        </form>
       

      </div>

      
    );
  }
export default Managerental;