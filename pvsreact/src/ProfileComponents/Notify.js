import styles from "../CSS/profileStyles.module.css";
import { useState, useEffect } from "react";
export default function Notify({ user }) {
    const [notifies, setNotifies] = useState([]);
    const [temp, setTemp] = useState(true);
    useEffect(() => {
        if (temp) {
            fetch(`http://localhost:8080/adopter/getNotification/${user.adopterId}`, {
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
    const handleReed = () => {

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
                                        <h3>Name</h3>
                                        <p>description</p>
                                    </div>
                                    <div className={styles.brand}><p>Status</p>Status</div>
                                    <div className={styles.actionProduct}>
                                        <div className={styles.accept} onClick={handleReed} id="">Done</div>
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