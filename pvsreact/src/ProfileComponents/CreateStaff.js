import styles from "../CSS/profileStyles.module.css";
import { useState, useEffect } from "react";
import PhoneInput from 'react-phone-number-input'

export default function CreateStaff({ user }) {
    const [firstName, setFirstName] = useState("");
    const [lastName, setLastName] = useState("");
    const [password, setPassword] = useState("");
    const [confirmedPassword, setConfirmedPassword] = useState("");
    const [email, setEmail] = useState("");
    const [phone, setPhone] = useState();
    const [shelterId, setShelterId] = useState(null);


    // .staffId(resultSet.getInt("staff_id"))
    //                     .firstName(resultSet.getString("first_name"))
    //                     .lastName(resultSet.getString("last_name"))
    //                     .shelterID(resultSet.getInt("shelter_id"))
    //                     .idAdmin(resultSet.getBoolean("is_admin"))
    //                     .phone(resultSet.getString("phone"))
    //                     .email(resultSet.getString("email"))
    //                     .password(resultSet.getString("password"))

    const handleSubmit = (e) => {
        console.log("qqqqqqqqqqqqqqqqqqqqqqqqqq")
        console.log("f" + firstName)
        console.log(lastName)
        e.preventDefault();
        fetch('http://localhost:8080/staff/createStaff', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify({
                firstName: firstName,
                lastName: lastName,
                shelterID: shelterId,
                idAdmin: 0,
                phone: phone,
                email: email,
                password: password
            }),
        })
            .then(response => response.json())
            .then(data => {
                console.log(data)
            })
            .catch(error => { console.error('Error creating user:', error); window.alert("Account Not Found Login") });
    }

    return (
        <>
            <div id="CreateStaff1" className={styles.CreateStaff}>
                <div class={styles.container}>
                    <div class={styles.content}>
                        <h2>Staff Information</h2>
                        <form onSubmit={handleSubmit}>
                            <input type="text" placeholder="First Name" value={firstName} onChange={(e) => setFirstName(e.target.value)} required></input>
                            <input type="text" placeholder="Last Name" value={lastName} onChange={(e) => setLastName(e.target.value)} required></input>
                            <input type="password" placeholder="Password" value={password} onChange={(e) => setPassword(e.target.value)} pattern="(?=.*[a-z])(?=.*[A-Z]).{8,}" title="Must contain at least one number and one uppercase and lowercase letter, and at least 8 or more characters" required></input>
                            <input type="password" placeholder="Confirm Password" value={confirmedPassword} onChange={(e) => setConfirmedPassword(e.target.value)} pattern={password} title="Password and Confirm Password does not match." required></input>
                            <input type="email" placeholder="Email" value={email} onChange={(e) => setEmail(e.target.value)} required></input>
                            <input type="number" placeholder="shelterId" value={shelterId} onChange={(e) => setShelterId(e.target.value)}></input>
                            <PhoneInput id="phone" placeholder="Enter phone number" value={phone} onChange={setPhone} required />
                            <button type="submit">Create</button>
                        </form>
                    </div>
                </div>
            </div>
        </>
    )
}