import { useEffect, useState } from "react"
import styles from "../CSS/List.module.css"
import { FaArrowRight } from "react-icons/fa";
import { FaSearch } from "react-icons/fa";
import { FaCartShopping } from "react-icons/fa6";
import { IoClose } from "react-icons/io5";
import { useCookies } from "react-cookie";
export default function AnimalList({ user }) {
    const [products, setProducts] = useState([]);
    const [specialProduct, setSpecialProduct] = useState({});
    const [temp, setTemp] = useState(true);
    const [search, setSearch] = useState('');
    const [searchType, setSearchType] = useState('');
    const [filter, setFilter] = useState('');
    const [filterType, setFilterType] = useState('');
    const [sort, setSort] = useState('id');
    const [edit, setEdit] = useState('');
    const [editField, setEditField] = useState('');
    useEffect(() => {
        if (temp) {
            if (user.adopter) {
                fetch('http://localhost:8080/adopter/pets', {
                    method: 'GET',
                })
                    .then(response => response.json())
                    .then(data => {
                        if (!data.status) {
                            setProducts(data);
                        }
                        console.log(data);
                        setTemp(false);
                    })
                    .catch(error => {
                        console.error('Error creating user:', error);
                        setTemp(false);
                    });
            }
            else {
                console.log(user.shelterID)
                fetch(`http://localhost:8080/adopter/shelterName/${user.shelterID}`, {
                    method: 'GET',
                })
                    .then(response => response.json())
                    .then(data => {
                        if (!data.status) {
                            setProducts(data);
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
    }
    )
    const handleSearch = () => {
        console.log(search)
        console.log(searchType)
        console.log(sort)
        if (search !== "") {
            fetch(`http://localhost:8080/adopter/search/${searchType}/${search}/${sort}`, {
                method: 'GET',
            })
                .then(response => response.json())
                .then(data => {
                    if (!data.status) {
                        setProducts(data);
                    }
                    console.log(data);
                    setTemp(false);
                })
                .catch(error => {
                    console.error('Error creating user:', error);
                    setTemp(false);
                });
        }
        else {
            if (user.adopter) {
                fetch('http://localhost:8080/adopter/pets', {
                    method: 'GET',
                })
                    .then(response => response.json())
                    .then(data => {
                        if (!data.status) {
                            setProducts(data);
                        }
                        console.log(data);
                        setTemp(false);
                    })
                    .catch(error => {
                        console.error('Error creating user:', error);
                        setTemp(false);
                    });
            }
            else {
                console.log(user.shelterID)
                fetch(`http://localhost:8080/adopter/shelterName/${user.shelterID}`, {
                    method: 'GET',
                })
                    .then(response => response.json())
                    .then(data => {
                        if (!data.status) {
                            setProducts(data);
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
    }
    const handleFilterType = (e) => {
        if (e.target.value === "all") {
            setFilterType('');
            document.getElementById("gender").style.display = "none";
            document.getElementById("houseTraining").style.display = "none";
            document.getElementById("neuteringStatus").style.display = "none";
        }
        else if (e.target.value === "gender") {
            setFilterType("gender")
            document.getElementById("gender").style.display = "flex";
            document.getElementById("houseTraining").style.display = "none";
            document.getElementById("neuteringStatus").style.display = "none";
        }
        else if (e.target.value === "houseTraining") {
            setFilterType("houseTraining");
            document.getElementById("gender").style.display = "none";
            document.getElementById("houseTraining").style.display = "flex";
            document.getElementById("neuteringStatus").style.display = "none";
        }
        else if (e.target.value === "neuteringStatus") {
            setFilterType("neuteringStatus")
            document.getElementById("gender").style.display = "none";
            document.getElementById("houseTraining").style.display = "none";
            document.getElementById("neuteringStatus").style.display = "flex";
        }
    }
    const handleFilter = (e) => {
        console.log(e.target.value + " " + filterType)
        fetch(`http://localhost:8080/adopter/search/${filterType}/${e.target.value}/${sort}`, {
            method: 'GET',
        })
            .then(response => response.json())
            .then(data => {
                if (!data.status) {
                    setProducts(data);
                }
                console.log(data);
                setTemp(false);
            })
            .catch(error => {
                console.error('Error creating user:', error);
                setTemp(false);
            });
    }
    const handleSort = (e) => {
        setSort(e.target.value);
        console.log(e.target.value)
        fetch(`http://localhost:8080/adopter/search/${"birthDate"}/${"2"}/${e.target.value}`, {
            method: 'GET',
        })
            .then(response => response.json())
            .then(data => {
                if (!data.status) {
                    setProducts(data);
                }
                console.log(data);
                setTemp(false);
            })
            .catch(error => {
                console.error('Error creating user:', error);
                setTemp(false);
            });
    }
    const handleReadMe = (e) => {
        setSpecialProduct(products[e.target.id]);
        console.log(products[e.target.id]);
        document.getElementsByClassName(styles.cover)[0].style.display = "block"
    }
    const handleClose = () => {
        document.getElementsByClassName(styles.cover)[0].style.display = "none"
    }
    const handleClose2 = () => {
        document.getElementsByClassName(styles.cover2)[0].style.display = "none"
    }
    const handleEdit = (e) => {
        setSpecialProduct(products[e.target.id]);
        console.log(products[e.target.id]);
        document.getElementsByClassName(styles.cover2)[0].style.display = "block"
    }
    const handleAdopt = () => {
        //need to emplement
        fetch(`http://localhost:8080/adopter/addApplication`, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            }
            , body: JSON.stringify({
                petID: specialProduct.id,
                adopterId: user.adopterId
            })
        })
            .then(response => response.json())
            .then(data => {
                console.log(data);
            })
            .catch(error => {
                console.error('Error creating user:', error);
            });
    }
    const handleSave = (e) => {
        e.preventDefault();
        console.log(edit)
        console.log(editField)
        fetch(`http://localhost:8080/staff/change/${editField}/${edit}/${specialProduct.id}`, {
            method: 'POST',
        })
            .then(response => response.json())
            .then(data => {
                console.log(data);
            })
            .catch(error => {
                console.error('Error creating user:', error);
            });
    }
    const handleRemove = (e) => {
        fetch(`http://localhost:8080/staff/delete/petId=${products[e.target.id].id}`, {
            method: 'DELETE',
        })
            .then(response => response.json())
            .then(data => {
                console.log(data);
            })
            .catch(error => {
                console.error('Error creating user:', error);
            });
    }
    return (
        <div class={styles.list}>
            <div class={styles.container}>
                <h2 class={styles.heading}>Animals</h2>
                <div className={styles.action} >
                    <form onSubmit={(e) => { e.preventDefault(); handleSearch() }} style={{ display: "flex", alignItems: "center" }}>
                        <select onChange={(e) => setSearchType(e.target.value)} style={{ marginRight: "20px" }}>
                            {/* <option value="id">All Pets</option> */}
                            <option value="species">Species</option>
                            <option value="breed">Breed</option>
                            <option value="name">Name</option>
                            <option value="gender">Gender</option>
                            <option value="healthStatus">Health Status</option>
                            <option value="behavior">behavior</option>
                            <option value="description">description</option>
                            <option value="houseTraining">House Training</option>
                            <option value="neuteringStatus">Neutring Status</option>
                            <option value="birthDate">Birth Date</option>
                        </select>
                        <input type="text" placeholder="Search" value={search} onChange={(e) => setSearch(e.target.value)}></input>
                        <FaSearch className={styles.searchIcon} onClick={handleSearch} />
                    </form>
                    <div style={{ display: "flex", alignItems: "center", height: "fit-content" }}>
                        <p style={{ marginRight: "10px" }}>Filter By</p>
                        <select onChange={handleFilterType} style={{ marginRight: "10px" }}>
                            <option value="all"></option>
                            <option value="gender">Gender</option>
                            <option value="houseTraining">House Training</option>
                            <option value="neuteringStatus">Neuturing Status</option>
                        </select>
                        <select onChange={handleFilter} style={{ display: "none" }} id="gender" >
                            <option value="all"></option>
                            <option value="male">Male</option>
                            <option value="female">Female</option>
                        </select>
                        <select onChange={handleFilter} style={{ display: "none" }} id="houseTraining">
                            <option value="all"></option>
                            <option value="trained">Trained</option>
                            <option value="not_trained">Not Trained</option>
                        </select>
                        <select onChange={handleFilter} style={{ display: "none" }} id="neuteringStatus">
                            <option value="all"></option>
                            <option value="1">Yes</option>
                            <option value="0">No</option>
                        </select>
                    </div>
                    <div style={{ display: "flex", alignItems: "center", height: "fit-content" }}>
                        <p style={{ marginRight: "10px" }}>Sort By</p>
                        <select onChange={handleSort} style={{ marginRight: "10px" }}>
                            <option value="id"></option>
                            <option value="species">Species</option>
                            <option value="breed">Breed</option>
                            <option value="name">Name</option>
                            <option value="gender">Gender</option>
                            <option value="healthStatus">Health Status</option>
                            <option value="behavior">behavior</option>
                            <option value="description">description</option>
                            <option value="houseTraining">House Training</option>
                            <option value="neuteringStatus">Neutring Status</option>
                            <option value="birthDate">Birth Date</option>
                        </select>
                    </div>
                </div>
                <div class={styles.content}>
                    {products.map((product, index) => {
                        return (<div class={styles.box} key={product.id}>
                            {/* {product.imageLink ? (<div class={styles.image}><img src={require("../images/" + product.imageLink)} alt="" /></div>) : (<></>)} */}
                            <div class={styles.text}>
                                <h3>{product.name}</h3>
                                <p>{product.description}</p>
                                <p>{product.breed}</p>
                                <div className={styles.price}>For Adoption</div>
                            </div>
                            {
                                user.adopter ?
                                    (<div class={styles.info}>
                                        <a id={index} onClick={handleReadMe}>Read Me</a>
                                        <FaArrowRight className={styles.i} />
                                    </div>)
                                    :
                                    (
                                        <>
                                            <div class={styles.info}>
                                                <a id={index} onClick={handleEdit}>Edit</a>
                                            </div>
                                            <div class={styles.info}>
                                                <a id={index} onClick={handleRemove}>Remove</a>
                                            </div>
                                        </>
                                    )
                            }
                        </div>)
                    })}
                </div>
            </div>
            <div className={styles.cover}>
                <div class={styles.specialProduct}>
                    <div className={styles.close} onClick={handleClose}><IoClose /></div>
                    {/* {specialProduct.imageLink ? (<div class={styles.image}><img src={require("../images/" + specialProduct.imageLink)} alt="" /></div>) : <></>} */}
                    <div class={styles.text}>
                        <h3>{specialProduct.name}</h3>
                        <p>{specialProduct.description}</p>
                        <div className={styles.price}>For Adoption</div>
                        {/* <FaBookmark className={styles.book} onClick={handleBookMark} /> */}
                    </div>
                    <div className={styles.brand}><p>Type</p>{specialProduct.type}</div>
                    <div className={styles.brand}><p>Breed</p>{specialProduct.breed}</div>
                    <div className={styles.brand}><p>species</p>{specialProduct.species}</div>
                    <div className={styles.brand}><p>gender</p>{specialProduct.gender}</div>
                    <div className={styles.brand}><p>Health Status</p>{specialProduct.healthStatus}</div>
                    <div className={styles.brand}><p>behavior</p>{specialProduct.behavior}</div>
                    <div className={styles.brand}><p>trainingStatus</p>{specialProduct.trainingStatus}</div>
                    <div className={styles.brand}><p>neuteringStatus</p>{specialProduct.neuteringStatus}</div>
                    <div className={styles.category}><p>Birth-Date</p>{specialProduct.birthDate}</div>
                    <div className={styles.cart} onClick={handleAdopt}><FaCartShopping /> Adopt</div>
                </div>
            </div>
            <div className={styles.cover2}>
                <div class={styles.specialProduct} style={{ display: "flex", alignItems: "center" }}>
                    <div className={styles.close} onClick={handleClose2}><IoClose /></div>
                    <select onChange={(e) => setEditField(e.target.value)} style={{ marginRight: "20px" }}>
                        <option value="species">Species</option>
                        <option value="breed">Breed</option>
                        <option value="name">Name</option>
                        <option value="gender">Gender</option>
                        <option value="healthStatus">Health Status</option>
                        <option value="behavior">behavior</option>
                        <option value="description">description</option>
                        <option value="houseTraining">House Training</option>
                        <option value="neuteringStatus">Neutring Status</option>
                    </select>
                    <form onSubmit={handleSave} style={{ height: "fit-content", margin: "auto" }}>
                        <input type="text" value={edit} onChange={(e) => setEdit(e.target.value)}></input>
                    </form>
                </div>
            </div>
        </div>
    )
}