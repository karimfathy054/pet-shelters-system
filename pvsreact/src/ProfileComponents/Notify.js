import styles from "../CSS/profileStyles.module.css";
import { useState, useEffect } from "react";
export default function Notify({ user }) {
    const [notifies, setNotifies] = useState([]);
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
                                    {product.imageLink ? (<div class={styles.image}><img src={require("../images/" + product.imageLink)} alt="" /></div>) : (<></>)}
                                    <div class={styles.text}>
                                        <h3>{product.productName}</h3>
                                        <p>{product.description}</p>
                                    </div>
                                    <div className={styles.brand}><p>Type</p>{product.type}</div>
                                    <div className={styles.brand}><p>Birth Date</p>{product.birthDate}</div>
                                    <div className={styles.category}><p>Breed</p>{product.breed}</div>
                                    <div className={styles.actionProduct}>
                                        <div className={styles.accept} onClick={handleAcceptPet} id={index}>Accept</div>
                                        <div className={styles.refuse} onClick={handleRefusePet} id={index}>Refuse</div>
                                    </div>
                                </div>
                                )
                            )
                        })}
                        <div class={styles.box}>
                            <div class={styles.text}>
                                <h3>Name</h3>
                                <p>description</p>
                            </div>
                            <div className={styles.brand}><p>Status</p>Status</div>
                            <div className={styles.actionProduct}>
                                <div className={styles.accept} onClick={handleReed} id="">Done</div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </>
    )
}