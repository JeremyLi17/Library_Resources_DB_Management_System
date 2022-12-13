import './Rentbook.css';
import { useNavigate } from 'react-router';
import { useState } from 'react';
import axios from 'axios';
import userRequest from "../request/user-request"
import moment from 'moment';
// import TextField from '@material-ui/core/TextField';
// axios.defaults.headers.common["Authorization"] = `Bearer ${localStorage.getItem("token")}`;
function Rentbook() {
  const navigate = useNavigate();
  const now = moment().format('YYYY-MM-DD');  
  const [bookname,setbookname] = useState();
  const [targetbookname, settargetbookname] = useState();
  const [copyId,setcopynum] = useState();
  const [expectedreturndate, setreturndate] = useState();
  const [copies,setCopies] = useState([]);
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
  const navigatetorentbook = () =>{
    navigate('/customer/book/rent/*')
  }

  const config = {
    headers: {
      "Authorization": `Bearer ${localStorage.getItem("token")}`
    }
  }
  const searchresult = async(event) => {
    event.preventDefault();
    const url =  `http://localhost:8080/api/copy/list/${bookname}`
    const config = {
      headers: {
        "Authorization": `Bearer ${localStorage.getItem("token")}`
      }
    }
    await axios.get(url, config).then(
      (res) => {
        console.log(res);
        const result = res.data;
        setCopies(result);
        // setcapacity(result.ROOM_CAPACITY);
      }
    ).catch((error) => {
      console.log(error);
    })

    
  }
  const dorent = async(event)=> {
    event.preventDefault();
    const config = {
      headers: {
        "Authorization": `Bearer ${localStorage.getItem("token")}`
      }
    }
    const url = `http://localhost:8080/api/rental/add`
    const userID = localStorage.getItem("currentUsername")
    await axios.post(url, 
      {
        "copy":{
          "id" : copyId
        },
        "customer": {
          "id": userID
        },

        "expReturnDate": String(expectedreturndate)
        
      
      },
      config).then(
      (res) => {
        const result = res.data;
        console.log("success");
      }
    ).catch(async (error) => {
      console.log(error);
      
    })
    
    
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


          <ul>
            <h2>
              AVALABLIE Copy Number:
            </h2>
              {copies.map((copy) => {
                return <li key={copy.id}>
                   {copy.id}
                </li>
              })}
            </ul>

          <form className = "rent">
            <div>
            <label for="choosetorent">Choose the Copy to rent</label>
            <input type="text" id="myInput"  placeholder="type the number ..." value = {copyId} onChange={(e) => setcopynum(e.target.value)}></input>
              <label for="end">Choose a date to return:</label>
             <input type="date" id="end" name="end"
            // value="2011-07-22"
              value = {expectedreturndate}
              min= {now}
              onChange={(e) => setreturndate(e.target.value)}
            />
            </div>
              <button onClick={dorent}>rent</button>
            <div>

            </div>
          </form>

          
          
      </div>
    );
  }
export default Rentbook;