import { useNavigate } from 'react-router';
import './Payment.css';
import { useState } from 'react';
import userRequest from "../request/user-request"

function Payment(){
    const [cashamount, setcashamount] = useState();
    const [cashname, setcashname] = useState();

    const [creditcardamount, setcreditcardamount] = useState();
    const [creditcardname, setcreditcardname] = useState();

    const [debitcardamount, setdebitcardamount] = useState();
    const [debitcardname, setdebitcardname] = useState();

    const [paypalamount, setpaypalamount] = useState();
    const [paypalname, setpaypalname] = useState();



    // const submitcashamount = event => {
        
    // }
    // const submitcreditcardamount = event => {
        
    // }
    // const submitdebitcardamount = event => {
        
    
    // const submitpaypalamount = event => {
        
    // }
    const submit = event => {
        
    }

    return(
        <div className='Payment'>
            <header>
            <h1>Payment</h1>
            </header>

            <div>
                <label>
                    total amount : {totalamount}
                </label>

                <form className='Pay'>
                <label>
                    Cash:
                </label>
                <input type="text" id="myPaymemt" placeholder="type amount.." value = {cashamount} onChange={(e) => setcashamount(e.target.value)}></input>
                <input type="text" id="myPaymemt" placeholder="type name.." value = {cashname} onChange={(e) => setcashname(e.target.value)}></input>
                {/* <button onClick = {submitcashamount} type="submit"><i class="fa fa-search">confirm</i></button> */}

                <label>
                    Credit card amount:
                </label>
                <input type="text" id="myPaymemt" placeholder="type amount.." value = {creditcardamount} onChange={(e) => setcreditcardamount(e.target.value)}></input>
                <input type="text" id="myPaymemt" placeholder="type name.." value = {creditcardname} onChange={(e) => setcreditcardname(e.target.value)}></input>
                {/* <button onClick = {submitcreditcardamount} type="submit"><i class="fa fa-search">confirm</i></button> */}

                <label>
                    debit card amount:
                </label>
                <input type="text" id="myPaymemt" placeholder="type amount.." value = {debitcardamount} onChange={(e) => setdebitcardamount(e.target.value)}></input>
                <input type="text" id="myPaymemt" placeholder="type name.." value = {debitcardname} onChange={(e) => setdebitcardname(e.target.value)}></input>
                {/* <button onClick = {submitdebitcardamount} type="submit"><i class="fa fa-search">confirm</i></button> */}

                <label>
                    paypal amount:
                </label>
                <input type="text" id="myPaymemt" placeholder="type amount.." value = {paypalamount} onChange={(e) => setpaypalamount(e.target.value)}></input>
                <input type="text" id="myPaymemt" placeholder="type name.." value = {paypalname} onChange={(e) => setpaypalname(e.target.value)}></input>
                {/* <button onClick = {submitpaypalamount} type="submit"><i class="fa fa-search">confirm</i></button> */}


                <button onClick = {submit} type="submit"><i class="fa fa-search">Place order</i></button>

                </form>
                

            </div>
        </div>

    );

}
export default Payment;
