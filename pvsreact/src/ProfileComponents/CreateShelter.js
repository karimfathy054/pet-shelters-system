import styles from "../CSS/profileStyles.module.css"
import { useEffect, useState } from "react"
import PhoneInput from 'react-phone-number-input'

export default function CreateShelter({ user }) {
    const [shelterName, setShelterName] = useState("");
    const [location, setLocation] = useState("");
    const [phone, setPhone] = useState();
    const handleSubmit = () => {
        //request for back to create chelter
    }
    return (
        <>
            <div id="CreateShelter1" className={styles.CreateShelter}>
                <div class={styles.container}>
                    <div class={styles.content}>
                        <h2>Shelter Information</h2>
                        <form onSubmit={handleSubmit}>
                            <input type="text" placeholder="Shelter Name" value={shelterName} onChange={(e) => setShelterName(e.target.value)} required></input>
                            <input type="text" placeholder="Location" value={location} onChange={(e) => setLocation(e.target.value)} pattern="(?=.*[a-z])(?=.*[A-Z]).{8,}" title="Must contain at least one number and one uppercase and lowercase letter, and at least 8 or more characters" required></input>
                            <PhoneInput id="phone" placeholder="Enter phone number" value={phone} onChange={setPhone} required />
                            <button type="submit">Create</button>
                        </form>
                    </div>
                </div>
            </div>
        </>
    )
}