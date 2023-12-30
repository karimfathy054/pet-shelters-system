import styles from "../CSS/profileStyles.module.css";
import { useState, useEffect } from "react";
export default function Assign({ user }) {
    const [staffEmail, setStaffEmail] = useState("");
    const [shelterName, setShelterName] = useState("");

    const handleSubmit = (e) => {
        e.preventDefault();
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