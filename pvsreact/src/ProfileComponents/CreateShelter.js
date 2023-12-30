import styles from "../CSS/profileStyles.module.css"
import { useEffect, useState } from "react"
import PhoneInput from 'react-phone-number-input'

export default function CreateShelter({ user }) {
    const [shelterName, setShelterName] = useState("");
    const [location, setLocation] = useState("");
    const [phone, setPhone] = useState();
    const handleSubmit = (e) => {
        //request for back to create chelter
        e.preventDefault();
        fetch('http://localhost:8080/shelter/add', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify({
                name: shelterName,
                location: location,
                phoneNumber: phone,
                managerId: user.staffId
            }),
        })
            .then(data => {
                console.log(data)
                if (data.status === 409) { window.alert("Account Aleady Exist") }
                else if (data.status === 200) { window.alert("Successfully") }
            })
            .catch(error => { console.error('Error creating user:', error); window.alert("Account Aleady Exist") });

    }
    return (
        <>
            <div id="CreateShelter1" className={styles.CreateShelter}>
                <div class={styles.container}>
                    <div class={styles.content}>
                        <h2>Shelter Information</h2>
                        <form onSubmit={handleSubmit}>
                            <input type="text" placeholder="Shelter Name" value={shelterName} onChange={(e) => setShelterName(e.target.value)} required></input>
                            <input type="text" placeholder="Location" value={location} onChange={(e) => setLocation(e.target.value)} required></input>
                            <PhoneInput id="phone" placeholder="Enter phone number" value={phone} onChange={setPhone} required />
                            <button type="submit">Create</button>
                        </form>
                    </div>
                </div>
            </div>
        </>
    )
}