import styles from "../CSS/profileStyles.module.css"
import { FaUserCircle } from "react-icons/fa";

export default function Info({ user }) {
    return (
        <>
            <div className={styles.info} id="info">
                <div className={styles.general}>
                    <h1>General Information</h1>
                    <div className={styles.userName}><p>First Name</p> {user.firstName}</div>
                    <div className={styles.userName}><p>Last Name</p> {user.lastName}</div>
                    <div className={styles.userName}><p>Email</p> {user.email}</div>
                    <div className={styles.userName}><p>Phone</p> {user.phone}</div>
                    {user.idAdmin ? (<h2>Manager Of The Shelters</h2>) : (<div className={styles.userName}><p>Shelter</p> {user.shelterID}</div>)}
                </div>
                <div className={styles.private}>
                    <div className={styles.privateInfo}>
                        <div className={styles.photo}>{user.image ? (<img src={require("../images/" + user.image)}></img>) : <FaUserCircle />}</div>
                        <div className={styles.role}>{user.idAdmin ? (<h3>admin</h3>) : (<h3>Staff</h3>)}</div>
                        <div className={styles.email}>{user.email}</div>
                    </div>
                </div>
            </div>
        </>
    )
}