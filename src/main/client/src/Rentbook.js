import './Rentbook.css';
import { useNavigate } from 'react-router';
import { useState } from 'react';
// import TextField from '@material-ui/core/TextField';

function Rentbook() {
  const navigate = useNavigate();
  const [bookname,setbookname] = useState();
  const [targetbookname, settargetbookname] = useState();
  const navigatetologin = () =>{
    navigate('/*');
  }
  const navigatetohome = () =>{
    navigate('/*');
  }
  const navigatetoback = () =>{
    navigate('/book/*');
  }
  const navigatetorentbook = () =>{
    navigate('/book/rent/*')
  }
  const searchresult = event => {
    event.preventDefault;
    console.log(targetbookname);
    console.log(bookname)
    // setbookname(targetbookname);
    console.log("book name is: ", bookname);
    console.log("submitted!")
  }
    return (
      <div className="Rentbook">
           <header >
            <h1>RENTAL SYSTEM</h1>
            </header>
         <div class="Search-container">
          <input type="text" id="myInput" placeholder="Search for book names.." value = {bookname} onChange={(e) => setbookname(e.target.value)}></input>
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
          
      </div>
    );
  }
export default Rentbook;