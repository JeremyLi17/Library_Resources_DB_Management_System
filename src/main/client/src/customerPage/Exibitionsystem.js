import './Exibitionsystem.css';
import { useNavigate } from 'react-router';

function Exibitionsystem() {
    const navigate = useNavigate();
    const navigatetoregister = () =>{
        navigate('/exibition/register/*');
      }
    const navigatetomanagereg = () =>{
        navigate('/exibition/manage/*');
    }
    const navigatetologin = () =>{
        navigate('/*');
    }
    const navigatetohome = () =>{
        navigate('/*');
    }
  return (
    <div className="Exibitionsystem">
        <header className="Exibitionsystemhead">
            <h1>Exibitionsystem</h1>
        </header>

        <form className = "Menubar1">
            <div>
                
                <button onClick = {navigatetoregister}>register</button>
            </div>
            <div>
                <button onClick = {navigatetomanagereg}>manage</button>
            </div>
            <div>
                <button onClick = {navigatetohome} >home</button>
            </div>
            <div>
                <button onClick = {navigatetologin} >sign out</button>
            </div>
        </form>

    </div>
  );
}
export default Exibitionsystem;