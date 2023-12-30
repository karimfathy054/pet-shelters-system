import { useEffect, useState } from "react";
import styles from "../CSS/List.module.css"
import logo from "../images/logo.png"
import { FaUser } from "react-icons/fa";
import { useNavigate } from "react-router-dom";


export default function ProductListHeader({ user }) {
    const navigate = useNavigate();
    const handleHome = () => {
        navigate('/', { replace: true });
    }
    const handleAnimals = () => {
        navigate('../Animals', { replace: true, state: { user } });
    }
    const handleAddAnimal = () => {
        navigate('/RequestAnimalForm', { replace: true, state: { user } });
    }
    return (
        <div className={styles.header}>
            <div className={styles.welcome}>
                <p className={styles.semititle}>PVS</p>
                <div className={styles.user} style={{ cursor: "default" }}><FaUser /> {user.firstName}</div>
            </div>
            <ul>
                <li><a onClick={handleHome}>Home</a></li>
                <li><a onClick={handleAnimals} id={styles.animals}>Pets</a></li>
                <li><a onClick={handleAddAnimal} >Add Animal</a></li>
            </ul>
        </div>
    )
}