import React, { Component } from 'react'
import PropTypes from 'prop-types'
import Input from './Input'
import './Form.css';

class Form extends Component {

    render() {
        return (
            <div className="Form-Page">
                <header className="Sign_in_Title">
                    <h1>
                        Sign in to Real Management System
                    </h1>
                </header>
                <form className='Input' >
                    <div className="InputBox">
                        <div>
                            <label for="uname"> Username </label>
                        </div>
                        <input
                        type="text"
                        id="uname"
                        name="name"
                        placeholder="Username"
                        className="InputBox"/>
                        <div>
                            <label for="pwd"> Password </label>
                        </div>
                        <input
                        type="text"
                        id="pwd"
                        name="name"
                        placeholder="Password"
                        className="InputBox"/>
                        <div>
                            <button>Submit</button>
                        </div>
                    </div>
                </form>
            </div>
        )
    }

    handleSubmit = (event) => {
        event.preventDefault()
        if(!this.state.errcount) {
            const data = new FormData(this.form)
            fetch(this.form.action, {
              method: this.form.method,
              body: new URLSearchParams(data)
            })
            .then(v => {
                if(v.redirected) window.location = v.url
            })
            .catch(e => console.warn(e))
        }
    }
}

Form.propTypes = {
  name: PropTypes.string,
  action: PropTypes.string,
  method: PropTypes.string,
  inputs: PropTypes.array,
  error: PropTypes.string
}

export default Form