import './App.css';
import { useState } from 'react';

const App = () => {

  // Our state that gets populated when the user types value in input fields
  const [fullName, setFullName] = useState('');
  const [npiNumber, setNpiNumber] = useState('');
  const [businessAddress, setBusinessAddress] = useState('');
  const [phoneNumber, setPhoneNumber] = useState('');
  const [email, setEmail] = useState('');

  const submitHandler = async (event) => {
    event.preventDefault();

    // We make this call to our 'db' to make sure we are not registering existing users
    const response = await fetch(`http://localhost:8000/users?npiNumber=${npiNumber}`, {
        method: 'GET',
        headers: {
            'Content-Type': 'application/json'        
        }
    });

    const data = await response.json();

    // Message that we will display if the user already exists in our 'db'
    if (data.length != 0) {
      alert("This user already exists. Try a differe NPI number");
      return;
    }

    else {
      // This request will store new user into our 'db'
      fetch('http://localhost:8000/users', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'        
        },
        body: JSON.stringify({
          fullName, 
          npiNumber,
          businessAddress,
          phoneNumber,
          email
        })
      }).then(() => { 
        alert("User inserted successfully!")
        document.getElementById("registerForm").reset();
      });
    }
  }

  return (
    <div className="App">
      <div id="registerContainer">
        <form id="registerForm" onSubmit={submitHandler}>
          <div>
            <label htmlFor="fullName">Full Name</label>
            <input type="text" id="fullName" placeholder="Enter your full name" name="fullName" onChange={event => setFullName(event.target.value)} required/>
          </div>
          <div>
            <label htmlFor="npiNumber">NPI Number</label>
            <input type="text" id="npiNumber" placeholder="Enter your NPI number" name="npiNumber" onChange={event => setNpiNumber(event.target.value)} required/>
          </div>
          <div>
            <label htmlFor="businessAddress">Bussiness Address</label>
            <input type="text" id="businessAddress" placeholder="Enter your bussiness Address" name="businessAddress" onChange={event => setBusinessAddress(event.target.value)} required/>
          </div>
          <div>
            <label htmlFor="phoneNumber">Telephone Number</label>
            <input type="text" id="phoneNumber" placeholder="Enter your phone number" name="phoneNumber" onChange={event => setPhoneNumber(event.target.value)} required/>
          </div>
          <div>
            <label htmlFor="email">Email Address</label>
            <input type="email" id="email" placeholder="Enter your email" name="email" onChange={event => setEmail(event.target.value)} required/>
          </div>
          <button type="submit" id="registerButton">Sign Up</button>
        </form>
      </div>
    </div>
  );
}

export default App;
