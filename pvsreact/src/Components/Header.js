import { useEffect, useState } from "react";
import styles from "../CSS/HomeStyle.module.css"
import logo from "../images/logo.png"
import { FaUser } from "react-icons/fa";
import { FaExchangeAlt } from "react-icons/fa";
import { useNavigate } from "react-router-dom";
import { PiSignOutBold } from "react-icons/pi";

export default function Header({ user }) {
    const navigate = useNavigate();
    const handleUser = () => {
        navigate('/Profile', { replace: true, state: { user: user } });
    }
    const handleChangeAccount = () => {
        const removeCookie = (name) => {
            document.cookie = `${name}=; expires=Thu, 01 Jan 1970 00:00:00 UTC; path=/;`;
        };

        // Replace 'yourCookieName' with the actual name of the cookie you want to remove
        removeCookie('user');
        removeCookie('cart');
    }
    return (
        <div className={styles.header}>
            <div className={styles.welcome}>
                <p className={styles.semititle}>PVS</p>
                <img src={logo} className={styles.logo}></img>
                <div className={styles.user} >
                    <div className={styles.userName} onClick={handleUser}><FaUser /> {user.firstName}</div>
                    <div className={styles.changeAccount} onClick={handleChangeAccount}>Sign out<PiSignOutBold /></div>
                </div>
            </div>
            <div className={styles.title}>PetVetSet</div>
            <ul>
                <li><a href="#home">Home</a></li>
                <li><a href="#services">Services</a></li>
                <li><a href="#products">Products</a></li>
                <li><a href="#about">About US</a></li>
                <li><a href="#contact">Contact US</a></li>
            </ul>
        </div>
    )
}
