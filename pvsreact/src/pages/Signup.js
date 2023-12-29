import dog from "../images/Sign-In-1.jpg"
import { FaHome } from "react-icons/fa";
import styles from '../CSS/style.module.css'
import { useNavigate } from "react-router-dom";
import { useState } from "react";
import { FaGoogle } from "react-icons/fa";
import { Link } from "react-router-dom";
import { useGoogleLogin } from "@react-oauth/google";
import { useEffect } from "react";
import axios from "axios";
import { useCookies } from "react-cookie";
import PhoneInput from 'react-phone-number-input'


export default function Signup() {
    const [cookies, setCookie] = useCookies(["user"]);
    function handleLogin(user) {
        setCookie("user", user, { path: "/" });
    }
    const navigate = useNavigate();
    const [firstName, setFirstName] = useState("");
    const [lastName, setLastName] = useState("");
    const [password, setPassword] = useState("");
    const [confirmedPassword, setConfirmedPassword] = useState("");
    const [email, setEmail] = useState("");
    const [phone, setPhone] = useState();
    const [address, setAddress] = useState("");
    // const h
    const handleSubmit = (e) => {
        e.preventDefault();
        fetch('http://localhost:8080/api/auth/register', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify({
                email: email,
                password: password,
                userName: userName
            }),
        })
            .then(response => response.json())
            .then(data => {
                createUser(data.token, email)
            })
            .catch(error => { console.error('Error creating user:', error); window.alert("Account Is Already Exist"); });

    }

    function createUser(token, email) {
        let body = {
            email: email
        }
        console.log(body)
        fetch('http://localhost:8080/api/getUserByEmail', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
                Authorization: "Bearer " + token
            },
            body: JSON.stringify(body),
        })
            .then(response => response.json())
            .then(data => {
                handleLogin({

                });
                navigate('/', { replace: true });
            })
    }
    return (
        <>
            <div className={styles.signup}>
                <div className={styles.content}>
                    <Link to="/" className={styles.home}><FaHome></FaHome> Return</Link>
                    <div className={styles.headup}>Sign up</div>
                    <form onSubmit={handleSubmit}>
                        <input type="text" placeholder="First Name" value={firstName} onChange={(e) => setFirstName(e.target.value)} required></input>
                        <input type="text" placeholder="Last Name" value={lastName} onChange={(e) => setLastName(e.target.value)} required></input>
                        <input type="password" placeholder="Password" value={password} onChange={(e) => setPassword(e.target.value)} pattern="(?=.*[a-z])(?=.*[A-Z]).{8,}" title="Must contain at least one number and one uppercase and lowercase letter, and at least 8 or more characters" required></input>
                        <input type="password" placeholder="Confirm Password" value={confirmedPassword} onChange={(e) => setConfirmedPassword(e.target.value)} pattern={password} title="Password and Confirm Password does not match." required></input>
                        <input type="email" placeholder="Email" value={email} onChange={(e) => setEmail(e.target.value)} required></input>
                        <input type="text" placeholder="Address" value={address} onChange={(e) => setAddress(e.target.value)} required></input>
                        <PhoneInput id="phone" placeholder="Enter phone number" value={phone} onChange={setPhone} required />
                        <button type="submit">Sign Up</button>
                        {/* <div onClick={() => navigate('../GoogleOAuthSignupController', { replace: true })} className={styles.googleSign}><FaGoogle></FaGoogle> Google</div> */}
                        {/* <div onClick={() => { login(); }} className={styles.googleSign} ><FaGoogle></FaGoogle> Google</div> */}
                    </form>
                    {/* <img src={dog}></img> */}
                </div>
            </div>
        </>
    )
}
