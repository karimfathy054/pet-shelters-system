import { Link } from "react-router-dom";
import styles from "../CSS/profileStyles.module.css"
import { MdDashboard } from "react-icons/md";
import { FaHome } from "react-icons/fa";
import { IoIosSettings } from "react-icons/io";
import { FaCodePullRequest } from "react-icons/fa6";
import { MdOutlineAdminPanelSettings } from "react-icons/md";
import { FaUserPlus } from "react-icons/fa";
export default function Navbar({ user }) {
    const handleSetting = () => {
        document.getElementById("info").style.display = "flex";
        document.getElementById("CreateStaff1").style.display = "none";
        document.getElementById(styles.setting).style.color = "#f22c5c";
        document.getElementById(styles.CreateStaff).style.color = "white";
        if (user.idAdmin) {
            document.getElementById("admins1").style.display = "none";
            document.getElementById(styles.admins).style.color = "white";
            document.getElementById(styles.requests).style.color = "white";
            document.getElementById("requests1").style.display = "none";
        }
    }
    const handleCreateStaff = () => {
        document.getElementById("CreateStaff1").style.display = "flex";
        document.getElementById("info").style.display = "none";
        document.getElementById(styles.setting).style.color = "white";
        document.getElementById(styles.CreateStaff).style.color = "#f22c5c";
        if (user.idAdmin) {
            document.getElementById(styles.requests).style.color = "white";
            document.getElementById(styles.admins).style.color = "white";
            document.getElementById("admins1").style.display = "none";
            document.getElementById("requests1").style.display = "none";
        }
    }

    const handleRequests = () => {
        document.getElementById("requests1").style.display = "flex";
        document.getElementById("CreateStaff1").style.display = "none";
        document.getElementById("info").style.display = "none";
        document.getElementById(styles.setting).style.color = "white";
        document.getElementById(styles.CreateStaff).style.color = "white";
        document.getElementById(styles.requests).style.color = "#f22c5c";
        document.getElementById("admins1").style.display = "none";
        document.getElementById(styles.admins).style.color = "white";
    }

    const handleAdmins = () => {
        document.getElementById("admins1").style.display = "block";
        document.getElementById("requests1").style.display = "none";
        document.getElementById("CreateStaff1").style.display = "none";
        document.getElementById("info").style.display = "none";
        document.getElementById(styles.setting).style.color = "white";
        document.getElementById(styles.CreateStaff).style.color = "white";
        document.getElementById(styles.requests).style.color = "white";
        document.getElementById(styles.admins).style.color = "#f22c5c";
    }

    return (
        <>
            <div className={styles.navbar}>
                <div className={styles.dashboard}><MdDashboard /> Dashboard</div>
                <ul>
                    <li><Link to='/' className={styles.li}><FaHome />Home</Link></li>
                    <li><div id={styles.setting} className={styles.li} onClick={handleSetting}><IoIosSettings />Setting</div></li>
                    <li><div id={styles.CreateStaff} className={styles.li} onClick={handleCreateStaff}><FaUserPlus />Create Staff</div></li>
                    {user.idAdmin ? (<>
                        <li><div id={styles.requests} className={styles.li} onClick={handleRequests}><FaCodePullRequest />Requests</div></li>
                        <li><div id={styles.admins} className={styles.li} onClick={handleAdmins}><MdOutlineAdminPanelSettings />Generate Admins</div></li>
                    </>
                    ) : (<></>)}
                </ul>
            </div>
        </>
    )
}