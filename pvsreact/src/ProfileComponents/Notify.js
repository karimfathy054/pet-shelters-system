import styles from "../CSS/profileStyles.module.css";
import { useState, useEffect } from "react";
export default function Notify({ user }) {
    const [notifies, setNotifies] = useState([]);
    const [temp, setTemp] = useState(true);
    useEffect(() => {
        if (temp) {
            fetch(`http://localhost:8080/notifications/${user.adopterId}`, {
                method: 'GET',
            })
                .then(response => response.json())
                .then(data => {
                    if (!data.status) {
                        setNotifies(data);
                    }
                    console.log(data);
                    setTemp(false);
                })
                .catch(error => {
                    console.error('Error creating user:', error);
                    setTemp(false);
                });
        }
    }
    )
    const handleReed = (e) => {
        fetch(`http://localhost:8080/notifications/app_id=${notifies[e.target.id].appId}`, {
            method: 'DELETE',
        })
            .then(response => response.json())
            .then(data => {
                if (data === true) {
                    window.alert("Successfully Deleted")
                }
                console.log(data);
            })
            .catch(error => {
                console.error('Error creating user:', error);
            });
    }
    return (
        <>
            <div id="notify1" className={styles.notify}>
                <div class={styles.container}>
                    <div class={styles.content}>
                        {notifies.map((product, index) => {
                            return (
                                (<div class={styles.box}>
                                    <div class={styles.text}>
                                        <h3>{product.petName}</h3>
                                        <p>{ }</p>
                                    </div>
                                    <div className={styles.brand}><p>Breed</p>{product.breed}</div>
                                    <div className={styles.brand}><p>Gender</p>{product.Gender}</div>
                                    <div className={styles.brand}><p>Shelter Location</p>{product.location}</div>
                                    <div className={styles.brand}><p>Species</p>{product.species}</div>
                                    <div className={styles.brand}><p>Notification Time</p>{product.notificationTime}</div>
                                    <div className={styles.brand}><p>Status</p>{product.status}</div>
                                    <div className={styles.actionProduct}>
                                        <div className={styles.accept} onClick={handleReed} id={index}>Done</div>
                                    </div>
                                </div>
                                )
                            )
                        })}

                    </div>
                </div>
            </div>
        </>
    )
}