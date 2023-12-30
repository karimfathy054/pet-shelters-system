import styles from "../CSS/profileStyles.module.css";
import { useState, useEffect } from "react";
export default function Assign({ user }) {
    const [staffEmail, setStaffEmail] = useState("");
    const [shelterName, setShelterName] = useState("");

    const handleSubmit = (e) => {
        e.preventDefault();
        fetch('http://localhost:8080/staff/associateStaffToShelter', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify({
                email: staffEmail,
                shelterName: shelterName
            }),
        })
            .then(data => {
                console.log(data)
                if (data.status === 409) { window.alert("Error in Assigning") }
                else if (data.status === 200) {
                    window.alert("Asign Successfully")
                }
            })
            .catch(error => { console.error('Error creating user:', error); window.alert("Error in Assigning") });
    }
    return (
        <>
            <div id="assign1" className={styles.assign}>
                <div class={styles.container}>
                    <div class={styles.content}>
                        <h2>Staff Information</h2>
                        <form onSubmit={handleSubmit}>
                            <input type="text" placeholder="Staff Email" value={staffEmail} onChange={(e) => setStaffEmail(e.target.value)} required></input>
                            <input type="text" placeholder="Shelter Name" value={shelterName} onChange={(e) => setShelterName(e.target.value)} required></input>
                            <button type="submit">Assign</button>
                        </form>
                    </div>
                </div>
            </div>
        </>
    )
}