import './Rentbook.css';
import { useNavigate } from 'react-router';
import { useState } from 'react';
import userRequest from "../request/user-request"
// import TextField from '@material-ui/core/TextField';

function Rentbook() {
  const navigate = useNavigate();
  const [bookname,setbookname] = useState();
  const [targetbookname, settargetbookname] = useState();
  const [booknum,setbooknum] = useState();
  const [returndate, setreturndate] = useState();
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
  const searchresult = event => {
    event.preventDefault;
    console.log(targetbookname);
    console.log(bookname)
    // setbookname(targetbookname);
    console.log("book name is: ", bookname);
    console.log("submitted!")
  }
  const dorent = event => {
    navigate('./customer/book/rent/payment/*')
    //need to submitted to backend
    event.preventDefault();
    console.log(targetbookname);
    console.log(bookname)
    // setbookname(targetbookname);
    console.log("book name is: ", bookname);
    console.log("submitted!")
  }

  const { doRequest, errors } = userRequest({
    url: "/api/users/signin",
    method: "post",
    body: {
      email,
      password,
    },
    onSuccess: () => Router.push("/"),
  });

  const onsubmit = () => {

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

          <form className = "rent">
            <div>
            <label for="choosetorent">Choose the book to rent</label>
            <input type="text" id="myInput"  placeholder="type the number ..." value = {booknum} onChange={(e) => setbooknum(e.target.value)}></input>
              <label for="end">Choose a date to return:</label>
             <input type="date" id="end" name="end"
            // value="2011-07-22"
              value = {returndate}
              min= "2022-12-13"
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