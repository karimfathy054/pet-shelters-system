import styles from "../CSS/styleRequest.module.css"
import { useLocation } from "react-router-dom"
import { useState, useEffect } from "react";
import ProductListHeader from "../ProductListComponents/ProductListHeader";
const AnimalUploadForm = () => {
    // start
    const location = useLocation();
    const [user, setUser] = useState({});
    const [temp, setTemp] = useState(true);
    useEffect(() => {
        if (location.state != null) {
            setUser(location.state.user);
            setTemp(false);
        }
    }
    )
    //end

    const [selectedValue1, setSelectedValue1] = useState('male');
    const [selectedValue2, setSelectedValue2] = useState('trained');
    const [selectedValue3, setSelectedValue3] = useState(1);
    const [one, setone] = useState(1);
    const [zero, setzero] = useState(0);
    const [productName, setproductName] = useState('');
    const [Breed, setBreed] = useState('');
    const [species, setSpecies] = useState('');
    const [Description, setDescription] = useState('');
    const [image, setImage] = useState('');
    const [behavior, setBehavior] = useState('');
    const [health_status, setHealth] = useState('');
    const [birthDate, setBirth] = useState('');
    const [File, SetFile] = useState({});
    const handleProductName = (e) => {
        setproductName(e.target.value);
        console.log(productName);
    }
    const handleDescription = (e) => {
        setDescription(e.target.value);
        console.log(Description);
    }
    const handleBreed = (e) => {
        setBreed(e.target.value);

    }
    const handlespecies = (e) => {
        setSpecies(e.target.value);
    }
    const handlebehavior = (e) => {
        setBehavior(e.target.value);
    }
    const handlebehealth = (e) => {
        setHealth(e.target.value);
    }
    const handlebirthDate = (e) => {
        setBirth(e.target.value);
    }



    const handleFileChange = (e) => {
        const file = e.target.files[0];
        console.log(file.name);
        console.log(file)
        setImage(file.name);
        SetFile(file)
    }
    const handlegender = (e) => {
        setSelectedValue1(e.target.value);
        console.log(e.target.value);
    }
    const handletraining = (e) => {
        setSelectedValue2(e.target.value);
        console.log(e.target.value);
    }
    const handleneut = (e) => {
        setSelectedValue3(e.target.value);
        console.log(e.target.value);
    }
    const handleSubmit = async (e) => {
        const body2 = {
            name: productName,
            species: species,
            breed: Breed,
            birthDate: birthDate,
            gender: selectedValue1,
            healthStatus: health_status,
            behavior: behavior,
            description: Description,
            neuteringStatus: selectedValue3,
            shelterId: user.shelterID,
            houseTraining: selectedValue2
        }
        console.log("body = ", body2);
        e.preventDefault();
        const response = await fetch('http://localhost:8080/staff/addPet', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify({
                name: productName,
                species: species,
                breed: Breed,
                birthDate: birthDate,
                gender: selectedValue1,
                healthStatus: health_status,
                behavior: behavior,
                description: Description,
                neuteringStatus: selectedValue3,
                shelterId: user.shelterID,
                houseTraining: selectedValue2
            }),
        }).then(data => {
            window.alert("Uploading success")
        })
        // fetch(`http://localhost:8080/documents/add`, {
        //     method: 'POST',
        //     headers: {
        //         'Content-Type': 'application/json',
        //     },
        //     body: JSON.stringify({
        //         path: image,
        //         type: File.type,
        //         petID: 
        //     })
        // })
        //     .then(response => response.json())
        //     .then(data => {
        //         if (data === true) {
        //             window.alert("Successfully Deleted")
        //         }
        //         console.log(data);
        //     })
        //     .catch(error => {
        //         console.error('Error creating user:', error);
        //     });

        setDescription("");
        setImage(null);
        setproductName("");
        setSelectedValue1("male");
        setSelectedValue2("trained");
        setSelectedValue3(1);
        setBirth("");
        setBreed("");
        setHealth("");
        setSpecies("");
        setBehavior("");
    }

    return (
        <>
            <ProductListHeader user={user} />
            <div className={styles.mainClass}>
                <form className={styles.msform}>
                    <fieldset className={styles.request}>
                        <div className={styles.productFormContainer}>
                            <h2 className={styles.label}>Animal Upload Form</h2>
                            <form >
                                <label className={styles.label}>
                                    Animal Name
                                    <input className={styles.input2}
                                        type="text"
                                        name="productName"
                                        value={productName}
                                        onChange={handleProductName}
                                        required
                                    />
                                    <label className={styles.label}>
                                        Description
                                        <textarea className={styles.input2}
                                            name="description"
                                            value={Description}
                                            onChange={handleDescription}
                                            required
                                        />
                                    </label  >
                                    <label className={styles.labe3}>
                                        Breed
                                        <input className={styles.input3}
                                            name="breed"
                                            value={Breed}
                                            onChange={handleBreed}
                                            required

                                        />
                                    </label>
                                </label>


                                <label className={styles.labe3}>
                                    Birth Date
                                    <input type="date" className={styles.input5}
                                        name="birthDate"
                                        value={birthDate}
                                        onChange={handlebirthDate}
                                        required
                                    />
                                    <label className={styles.labe3}>
                                        species
                                        <input className={styles.input3}
                                            name="species"
                                            value={species}
                                            onChange={handlespecies}
                                            required

                                        />

                                    </label>
                                    <label className={styles.labe3}>
                                        behavior
                                        <input className={styles.input3}
                                            name="behavior"
                                            value={behavior}
                                            onChange={handlebehavior}
                                            required

                                        />

                                    </label>
                                </label>
                                <label className={styles.labe3}>
                                    health status
                                    <input className={styles.input3}
                                        name="health_status"
                                        value={health_status}
                                        onChange={handlebehealth}
                                        required

                                    />

                                </label>

                                <label className={styles.labe3}>
                                    file
                                    <input className={styles.input1}
                                        type="file"
                                        // accept="image/*"
                                        onChange={handleFileChange}
                                        required
                                    />
                                </label >
                                <div className={styles.select3}>
                                    <label className={styles.select2} htmlFor="mySelect">gender:</label>
                                    <select onChange={handlegender} style={{ marginRight: "10px" }}>
                                        <option value="male">male</option>
                                        <option value="female">female</option>
                                    </select>
                                    <label className={styles.select2} htmlFor="mySelect3">house training:</label>
                                    <select onChange={handletraining} style={{ marginRight: "10px" }}>
                                        <option value2="trained">trained</option>
                                        <option value2="not_trained">not_trained</option>
                                    </select>
                                    <label className={styles.select2} htmlFor="mySelect4">neutering status:</label>
                                    <select onChange={handleneut} style={{ marginRight: "10px" }}>
                                        <option value3={one} >true</option>
                                        <option value3={zero}>false</option>
                                    </select>

                                </div>
                                <button className={styles.button} type="submit" onClick={handleSubmit}>Upload Animal</button>
                            </form>
                        </div>
                    </fieldset>
                </form>
            </div>
        </>
    );
};

export default AnimalUploadForm;
