import { Link } from "react-router-dom";
import styles from "../CSS/profileStyles.module.css"
import { MdDashboard } from "react-icons/md";
import { FaHome } from "react-icons/fa";
import { IoIosSettings } from "react-icons/io";
import { FaCodePullRequest } from "react-icons/fa6";
import { MdOutlineAdminPanelSettings } from "react-icons/md";
import { FaUserPlus } from "react-icons/fa";
import { IoIosNotifications } from "react-icons/io";
import { MdOutlineAssignmentInd } from "react-icons/md";
export default function Navbar({ user }) {
    const handleSetting = () => {
        document.getElementById("info").style.display = "flex";
        document.getElementById(styles.setting).style.color = "#f22c5c";
        if (user.adopter) {
            document.getElementById("notify1").style.display = "none";
            document.getElementById(styles.notify).style.color = "white";
        }
        if (!user.adopter) {
            document.getElementById("CreateStaff1").style.display = "none";
            document.getElementById(styles.CreateStaff).style.color = "white";
        }
        if (user.idAdmin) {
            document.getElementById(styles.CreateShelter).style.color = "white";
            document.getElementById("CreateShelter1").style.display = "none";
            document.getElementById(styles.requests).style.color = "white";
            document.getElementById("requests1").style.display = "none";
            document.getElementById(styles.assign).style.color = "white";
            document.getElementById("assign1").style.display = "none";
            document.getElementById("upgrade1").style.display = "none";
            document.getElementById(styles.upgrade).style.color = "white";
        }
    }
    const handleCreateStaff = () => {
        document.getElementById("CreateStaff1").style.display = "flex";
        document.getElementById("info").style.display = "none";
        document.getElementById(styles.setting).style.color = "white";
        document.getElementById(styles.CreateStaff).style.color = "#f22c5c";
        if (user.idAdmin) {
            document.getElementById(styles.requests).style.color = "white";
            document.getElementById(styles.CreateShelter).style.color = "white";
            document.getElementById("CreateShelter1").style.display = "none";
            document.getElementById("requests1").style.display = "none";
            document.getElementById("assign1").style.display = "none";
            document.getElementById(styles.assign).style.color = "white";
            document.getElementById("upgrade1").style.display = "none";
            document.getElementById(styles.upgrade).style.color = "white";
        }
    }

    const handleRequests = () => {
        document.getElementById("requests1").style.display = "flex";
        document.getElementById("CreateStaff1").style.display = "none";
        document.getElementById("info").style.display = "none";
        document.getElementById(styles.setting).style.color = "white";
        document.getElementById(styles.CreateStaff).style.color = "white";
        document.getElementById(styles.requests).style.color = "#f22c5c";
        document.getElementById("CreateShelter1").style.display = "none";
        document.getElementById(styles.CreateShelter).style.color = "white";
        document.getElementById("assign1").style.display = "none";
        document.getElementById(styles.assign).style.color = "white";
        document.getElementById("upgrade1").style.display = "none";
        document.getElementById(styles.upgrade).style.color = "white";
    }

    const handleCreateShelter = () => {
        document.getElementById("CreateShelter1").style.display = "block";
        document.getElementById("requests1").style.display = "none";
        document.getElementById("CreateStaff1").style.display = "none";
        document.getElementById("info").style.display = "none";
        document.getElementById(styles.setting).style.color = "white";
        document.getElementById(styles.CreateStaff).style.color = "white";
        document.getElementById(styles.requests).style.color = "white";
        document.getElementById(styles.CreateShelter).style.color = "#f22c5c";
        document.getElementById("assign1").style.display = "none";
        document.getElementById(styles.assign).style.color = "white";
        document.getElementById("upgrade1").style.display = "none";
        document.getElementById(styles.upgrade).style.color = "white";
    }

    const handleNotify = () => {
        document.getElementById("info").style.display = "none";
        document.getElementById(styles.setting).style.color = "white";
        document.getElementById("notify1").style.display = "flex";
        document.getElementById(styles.notify).style.color = "#f22c5c";
    }

    const handleAssign = () => {
        document.getElementById("assign1").style.display = "block";
        document.getElementById("requests1").style.display = "none";
        document.getElementById("CreateStaff1").style.display = "none";
        document.getElementById("info").style.display = "none";
        document.getElementById(styles.assign).style.color = "#f22c5c";
        document.getElementById(styles.setting).style.color = "white";
        document.getElementById(styles.CreateStaff).style.color = "white";
        document.getElementById(styles.requests).style.color = "white";
        document.getElementById("CreateShelter1").style.display = "none";
        document.getElementById(styles.CreateShelter).style.color = "white";
        document.getElementById("upgrade1").style.display = "none";
        document.getElementById(styles.upgrade).style.color = "white";
    }

    const handleUpgrade = () => {
        document.getElementById("assign1").style.display = "none";
        document.getElementById("requests1").style.display = "none";
        document.getElementById("CreateStaff1").style.display = "none";
        document.getElementById("info").style.display = "none";
        document.getElementById(styles.assign).style.color = "white";
        document.getElementById(styles.setting).style.color = "white";
        document.getElementById(styles.CreateStaff).style.color = "white";
        document.getElementById(styles.requests).style.color = "white";
        document.getElementById("CreateShelter1").style.display = "none";
        document.getElementById(styles.CreateShelter).style.color = "white";
        document.getElementById("upgrade1").style.display = "block";
        document.getElementById(styles.upgrade).style.color = "#f22c5c";
    }
    return (
        <>
            <div className={styles.navbar}>
                <div className={styles.dashboard}><MdDashboard /> Dashboard</div>
                <ul>
                    <li><Link to='/' className={styles.li}><FaHome />Home</Link></li>
                    <li><div id={styles.setting} className={styles.li} onClick={handleSetting}><IoIosSettings />Setting</div></li>
                    {user.adopter ? (<div id={styles.notify} className={styles.li} onClick={handleNotify}><IoIosNotifications />Notifycations</div>) : (
                        <>
                            <li><div id={styles.requests} className={styles.li} onClick={handleRequests}><FaCodePullRequest />Adoption Requests</div></li>
                        </>)}
                    {user.idAdmin ? (<>
                        <li><div id={styles.CreateStaff} className={styles.li} onClick={handleCreateStaff}><FaUserPlus />Create Staff</div></li>
                        <li><div id={styles.upgrade} className={styles.li} onClick={handleUpgrade}><FaUserPlus />Upgrade Staff</div></li>
                        <li><div id={styles.CreateShelter} className={styles.li} onClick={handleCreateShelter}><MdOutlineAdminPanelSettings />Create Shelter</div></li>
                        <li><div id={styles.assign} className={styles.li} onClick={handleAssign}><MdOutlineAssignmentInd />Assign Staff</div></li>
                    </>
                    ) : (<></>)}
                </ul>
            </div>
        </>
    )
}