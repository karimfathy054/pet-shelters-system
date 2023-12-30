import { Link } from "react-router-dom"
import styles from "../CSS/style.module.css"
import cat from "../images/Screenshot 2023-11-20 201730.png"
import { useContext, useState } from "react"
import { FaRegCircle } from "react-icons/fa";
export default function Signin({ onLogin }) {
    const [userName, setUserName] = useState("");
    const [password, setPassword] = useState("");

    const handleUserName = (e) => {
        setUserName(e.target.value);
    }

    const handlePassword = (e) => {
        setPassword(e.target.value);
    }

    const handleSubmit = (e) => {
        e.preventDefault();
        const staffColor = document.getElementById(styles.staff).style.backgroundColor;
        const adopterColor = document.getElementById(styles.adopter).style.backgroundColor;
        if (staffColor == "rgb(33, 43, 49)") {
            fetch('http://localhost:8080/staff/login', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',
                },
                body: JSON.stringify({
                    email: userName,
                    password: password
                }),
            })
                .then(response => response.json())
                .then(data => {
                    console.log(data)
                    onLogin({
                        email: data.email,
                        firstName: data.firstname,
                        idAdmin: data.isAdmin,
                        lastName: data.lastName,
                        password: data.password,
                        phone: data.phone,
                        shelterID: data.shelterID,
                        staffId: data.id
                    });
                })
                .catch(error => { console.error('Error creating user:', error); window.alert("Account Not Found Login") });
        }
        else if (adopterColor == "rgb(33, 43, 49)") {
            console.log("adopter");
            fetch('http://localhost:8080/adopter/signIn', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',
                },
                body: JSON.stringify({
                    email: userName,
                    password: password
                }),
            })
                .then(response => response.json())
                .then(data => {
                    console.log(data)
                    onLogin({
                        email: data.email,
                        firstName: data.firstName,
                        lastName: data.secondName,
                        password: data.password,
                        phone: data.phone,
                        joinDate: data.joinDate,
                        address: data.address,
                        adopterId: data.adopterId,
                        adopter: true
                    });
                })
                .catch(error => { console.error('Error creating user:', error); window.alert("Account Not Found Login") });
        }
        else {
            window.alert("Please Choose staff or adopter")
        }
    }

    const handleStaff = () => {
        document.getElementById(styles.staff).style.backgroundColor = "#212b31";
        document.getElementById(styles.adopter).style.backgroundColor = "inherit";
    }
    const handleAdopter = () => {
        document.getElementById(styles.adopter).style.backgroundColor = "#212b31";
        document.getElementById(styles.staff).style.backgroundColor = "inherit";
    }
    return (
        <div className={styles.signin}>
            <div className={styles.content}>
                <form onSubmit={handleSubmit}>
                    <div className={styles.head}>Sign in</div>
                    <div className={styles.select}>
                        <div className={styles.staff} onClick={handleStaff}><FaRegCircle id={styles.staff} /> Staff</div>
                        <div className={styles.adopter} onClick={handleAdopter}><FaRegCircle id={styles.adopter} /> adopter</div>
                    </div>
                    <input type="email" placeholder="Email" value={userName} onChange={handleUserName} required></input>
                    <input type="password" placeholder="Password" value={password} onChange={handlePassword} pattern="(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{8,}" title="Invalid Password" required></input>
                    <button type="submit">Sign in</button>
                    <Link to="/Signup">Create acount</Link>
                    <div className={styles.line}></div>
                    {/* <div onClick={() => { navigate('../GoogleOAuthSigninController', { replace: true, state: { token: token } }) }} className={styles.googleSign}><FaGoogle></FaGoogle> Google</div> Need to implement in google OAuth */}
                    {/* <div onClick={() => login()} className={styles.googleSign}><FaGoogle></FaGoogle> Google</div> */}
                </form>
                {/* <img src={cat}></img> */}
            </div>
        </div>
    )
}
