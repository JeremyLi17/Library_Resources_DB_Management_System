import './Managerental.css';
import { useNavigate } from 'react-router';
import { useState, useEffect } from 'react';
import axios from 'axios';
// import TextField from '@material-ui/core/TextField';
// axios.defaults.headers.common["Authorization"] = `Bearer ${localStorage.getItem("token")}`;
function Managerental() {
  const navigate = useNavigate();
  const [returnid, setreturnid] = useState();
  const [rentals, setrentals] = useState([]);
  

  const getAllres = () => {
    const config = {
      headers: {
        "Authorization": `Bearer ${localStorage.getItem("token")}`
      }
    }
    const customerId = Number(localStorage.getItem("currentCustomerId"));
  
    axios.get(`http://localhost:8080/api/rental/list/${customerId}`, config).then((response) => {
      setrentals([])
      var list = []
      for (var obj in response.data) {
        // reservations.push(response.data[obj])
        list.push(response.data[obj])
      }
      console.log("here")
      setrentals(list)
  }).catch((e) => {
    console.log(e);
  })
  }
  useEffect(() => {
    getAllres();
  }, [])


  const navigatetologin = () =>{
    navigate('/*');
  }
  const navigatetohome = () =>{
    navigate('/customer/*');
  }
  const navigatetoback = () =>{
    navigate('/customer/book/*');
  }
  const doreturn  = async(event)=> {
    //need to submitted to backend
    const url = `http://localhost:8080/api/rental/delete/${returnid}`
    const config = {
      headers: {
        "Authorization": `Bearer ${localStorage.getItem("token")}`
      }
    }
    await axios.delete(url, config)
    navigate('/customer/book/*');
    
    
  }
  const directtopayment = () =>{
    navigate('/customer/book/rent/payment/*');
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

        <ul>
        {rentals.map((rental) => {
        return (
          <li key={rental.id}>
            Rental id :{rental.id}, 
            Rental Status:{rental.status}, 
            Book name:{rental.copy.book.bookName},
            Expectend return date:{rental.expReturnDate}
          </li>
        );
         })}
        </ul>

        
        <form>
          <div>
          <label >return book</label>
            <input type="text" id="myInput"  placeholder="type rental id ..." value = {returnid} onChange={(e) => setreturnid(e.target.value)}></input>
            <div>
            <button onClick={doreturn}>return</button>
            </div>

          </div>
          
        </form>

        <div>
          <button onClick = {directtopayment}>pay</button>
        </div>
       

      </div>

      
    );
  }
export default Managerental;