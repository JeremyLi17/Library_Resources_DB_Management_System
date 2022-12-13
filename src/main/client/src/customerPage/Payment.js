import { useNavigate } from 'react-router';
import './Payment.css';
import { useState,useEffect } from 'react';
import moment from 'moment';
import axios from 'axios';
import userRequest from "../request/user-request"
// axios.defaults.headers.common["Authorization"] = `Bearer ${localStorage.getItem("token")}`;
function Payment(){
    const now = moment().format('YYYY-MM-DD');  
    const [invoices, setinvoices] = useState([]);
    const [cardholdername,setcardholdername] = useState();
    const [paymenttype, setpaymenttype] = useState();
    const [amount,setpaymentamount] = useState();
    const [invoiceid,setinvoiceid] = useState();

    const config = {
        headers: {
          "Authorization": `Bearer ${localStorage.getItem("token")}`
        }
      }

    const getAllres = () => {
        
        const customerId = Number(localStorage.getItem("currentCustomerId"));
      
        axios.get(`http://localhost:8080/api/invoice/list/${customerId}`, config).then((response) => {
          setinvoices([])
          var list = []
          for (var obj in response.data) {
            // reservations.push(response.data[obj])
            list.push(response.data[obj])
          }
          console.log("here")
          setinvoices(list)
      }).catch((e) => {
        console.log(e);
      })
    }
    useEffect(() => {
        getAllres();
    }, [])


    const dopay = async (event) => {
        console.log("pay")
        event.preventDefault();
        const paymethod = document.getElementById("method").value;
        const url = `http://localhost:8080/api/payment/add`
        await axios.post(url, 
            {
                "invoice": {
                    "id":invoiceid
                },
              "paymentAmount":amount,
              "method": paymethod,
              "cardHolderFullName":cardholdername
              
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

    return(
        <div className='Payment'>
            <header>
            <h1>Payment</h1>
            </header>


            <ul>
            {invoices.map((invoice) => {

            
            return (
            <li key={invoice.id}>
                rental id: {invoice.id}
                total amount:{invoice.amount}
            
                {/* money paid: {getpayments} */}
            </li>
            );
            })}
            </ul>


            <div>
                {/* <label>
                    total amount : {totalamount}
                </label> */}
                <select name="method" id="method" > 
                <option value="cash">cash</option>
                <option value="creditcard">credit card</option>
                <option value="debitcard">debit card</option>
                <option value="paypal">pay pal</option>
                onChange={(e) => setpaymenttype(e.target.value)}
                </select>
                <input type="number" id="myInput" placeholder="invoice id.." value = {invoiceid} onChange={(e) => setinvoiceid(e.target.value)}></input>
                <input type="number" id="myInput" placeholder="type amount.." value = {amount} onChange={(e) => setpaymentamount(e.target.value)}></input>
                <input type="text" id="myInput" placeholder="card holder name" value = {cardholdername} onChange={(e) => setcardholdername(e.target.value)}></input>
            <div>
            <button onClick={dopay}>pay</button>
            </div>
            </div>
        </div>

    );

}
export default Payment;
