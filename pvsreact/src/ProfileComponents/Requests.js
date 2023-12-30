import styles from "../CSS/profileStyles.module.css";
import { useState, useEffect } from "react";
export default function Requests({ user }) {
    const [applications, setApplications] = useState([]);
    const [temp, setTemp] = useState(true);

    useEffect(() => {
        console.log(user.shelterID)
        if (temp) {
            fetch(`http://localhost:8080/staff/applications/shelterId=${user.shelterID}`, {
                method: 'GET',
            })
                .then(response => response.json())
                .then(data => {
                    if (!data.status) {
                        setApplications(data);
                    }
                    console.log(data);
                    setTemp(false);
                })
                .catch(error => {
                    console.error('Error creating user:', error);
                    setTemp(false);
                });
        }
    })

    const handleAcceptPet = (e) => {
        console.log(applications[e.target.id])
        fetch(`http://localhost:8080/staff/acceptApplication`, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify({
                id: applications[e.target.id].appId,
                status: "accept"
            })
        })
            .then(data => {
                if (data.status === 200) {
                    window.alert("Successfully Accepted")
                }
                else {
                    window.alert("Error")
                }
                console.log(data);
            })
            .catch(error => {
                console.error('Error creating user:', error);
            });
    }
    const handleRefusePet = (e) => {
        console.log(applications)
        fetch(`http://localhost:8080/staff/acceptApplication`, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify({
                id: applications[e.target.id].appId,
                status: "reject"
            })
        })
            .then(data => {
                if (data.status === 200) {
                    window.alert("Successfully Rejected")
                }
                else {
                    window.alert("Error")
                }
                console.log(data);
            })
            .catch(error => {
                console.error('Error creating user:', error);
            });
    }
    return (
        <>
            <div id="requests1" className={styles.requests}>
                <div class={styles.container}>
                    <div class={styles.content}>
                        {applications.map((product, index) => {
                            return (
                                (<div class={styles.box}>
                                    <div class={styles.text}>
                                        <h3>{product.petName}</h3>
                                        <p>{product.description}</p>
                                    </div>
                                    <div className={styles.brand}><p>Breed</p>{product.breed}</div>
                                    <div className={styles.category}><p>Breed</p>{product.status}</div>
                                    <div className={styles.actionProduct}>
                                        <div className={styles.accept} onClick={handleAcceptPet} id={index}>Accept</div>
                                        <div className={styles.refuse} onClick={handleRefusePet} id={index}>Refuse</div>
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