import styles from "../CSS/profileStyles.module.css";
import { useState, useEffect } from "react";
export default function Upgrade({ user }) {
    const [staffEmail, setStaffEmail] = useState("");

    const handleSubmit = (e) => {
        e.preventDefault();
        fetch('http://localhost:8080/staff/makeAdmin', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify({
                email: staffEmail
            }),
        })
            .then(data => {
                console.log(data)
                if (data.status === 409) { window.alert("Error in Upgrading") }
                else if (data.status === 200) {
                    window.alert("Upgrading Successful")
                }
            })
            .catch(error => { console.error('Error creating user:', error); window.alert("Error in Upgrading") });
    }
    return (
        <>
            <div id="upgrade1" className={styles.upgrade}>
                <div class={styles.container}>
                    <div class={styles.content}>
                        <h2>Staff Information</h2>
                        <form onSubmit={handleSubmit}>
                            <input type="text" placeholder="Staff Email" value={staffEmail} onChange={(e) => setStaffEmail(e.target.value)} required></input>
                            <button type="submit">Upgrade</button>
                        </form>
                    </div>
                </div>
            </div>
        </>
    )
}