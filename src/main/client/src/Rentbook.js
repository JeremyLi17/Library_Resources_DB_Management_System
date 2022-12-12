import './Rentbook.css';
import { useNavigate } from 'react-router';
// import TextField from '@material-ui/core/TextField';

function Rentbook() {
  const navigate = useNavigate();
  const navigatetologin = () =>{
    navigate('/*');
  }
  const navigatetohome = () =>{
    navigate('/*');
  }
  const navigatetoback = () =>{
    navigate('/book/*');
  }
    return (
      <div className="Rentbook">
           <header >
            <h1>RENTAL SYSTEM</h1>
            </header>
         <div class="Search-container">
          <input type="text" id="myInput" onkeyup="myFunction()" placeholder="Search for book names.."></input>
          <button type="submit"><i class="fa fa-search">search</i></button>
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

      </div>
    );
  }
export default Rentbook;