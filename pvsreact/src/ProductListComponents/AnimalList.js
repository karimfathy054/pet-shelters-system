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
    const [sort, setSort] = useState('');
    useEffect(() => {
        if (temp) {
            fetch('http://localhost:8080/products/all', {
                method: 'GET',
                headers: {
                    'Authorization': `Bearer ${user.token}`,
                }
            })
                .then(response => response.json())
                .then(data => {
                    setProducts(data);
                    // console.log(data);
                    setTemp(false);
                })
                .catch(error => {
                    console.error('Error creating user:', error);
                    setTemp(false);
                });
        }
    }
    )
    const handleSearch = () => {
        console.log(search)
        console.log(searchType)
    }
    const handleFilterType = (e) => {
        if (e.target.value === "all") {
            setFilterType('');
            document.getElementById("gender").style.display = "none";
            document.getElementById("house").style.display = "none";
            document.getElementById("nueturing").style.display = "none";
        }
        else if (e.target.value === "gender") {
            setFilterType("gender")
            document.getElementById("gender").style.display = "flex";
            document.getElementById("house").style.display = "none";
            document.getElementById("nueturing").style.display = "none";
        }
        else if (e.target.value === "house") {
            setFilterType("house");
            document.getElementById("gender").style.display = "none";
            document.getElementById("house").style.display = "flex";
            document.getElementById("nueturing").style.display = "none";
        }
        else if (e.target.value === "nueturing") {
            setFilterType("nueturing")
            document.getElementById("gender").style.display = "none";
            document.getElementById("house").style.display = "none";
            document.getElementById("nueturing").style.display = "flex";
        }
    }
    const handleFilter = (e) => {
        console.log(e.target.value + " " + filterType)
    }
    const handleSort = (e) => {
        setSort(e.target.value);
    }
    const handleReadMe = (e) => {
        setSpecialProduct(products[e.target.id]);
        console.log(products[e.target.id]);
        document.getElementsByClassName(styles.cover)[0].style.display = "block"
    }
    const handleClose = () => {
        document.getElementsByClassName(styles.cover)[0].style.display = "none"
    }
    const handleAdopt = () => {
    }
    return (
        <div class={styles.list}>
            <div class={styles.container}>
                <h2 class={styles.heading}>Animals</h2>
                <div className={styles.action} >
                    <form onSubmit={(e) => { e.preventDefault(); handleSearch() }} style={{ display: "flex", alignItems: "center" }}>
                        <select onChange={(e) => setSearchType(e.target.value)} style={{ marginRight: "20px" }}>
                            <option value="all">All Pets</option>
                            <option value="species">Species</option>
                            <option value="breed">Breed</option>
                            <option value="name">Name</option>
                            <option value="gender">Gender</option>
                            <option value="health">Health Status</option>
                            <option value="behavior">behavior</option>
                            <option value="description">description</option>
                            <option value="house">House Training</option>
                            <option value="neutering">Neutring Status</option>
                            <option value="location">Shelter Location</option>
                        </select>
                        <input type="text" placeholder="Search" value={search} onChange={(e) => setSearch(e.target.value)}></input>
                        <FaSearch className={styles.searchIcon} onClick={handleSearch} />
                    </form>
                    <div style={{ display: "flex", alignItems: "center", height: "fit-content" }}>
                        <p style={{ marginRight: "10px" }}>Filter By</p>
                        <select onChange={handleFilterType} style={{ marginRight: "10px" }}>
                            <option value="all"></option>
                            <option value="gender">Gender</option>
                            <option value="house">House Training</option>
                            <option value="nueturing">Neuturing Status</option>
                        </select>
                        <select onChange={handleFilter} style={{ display: "none" }} id="gender" >
                            <option value="all"></option>
                            <option value="male">Male</option>
                            <option value="female">Female</option>
                        </select>
                        <select onChange={handleFilter} style={{ display: "none" }} id="house">
                            <option value="all"></option>
                            <option value="trained">Trained</option>
                            <option value="nottrained">Not Trained</option>
                        </select>
                        <select onChange={handleFilter} style={{ display: "none" }} id="nueturing">
                            <option value="all"></option>
                            <option value="yes">Yes</option>
                            <option value="no">No</option>
                        </select>
                    </div>
                    <div style={{ display: "flex", alignItems: "center", height: "fit-content" }}>
                        <p style={{ marginRight: "10px" }}>Sort By</p>
                        <select onChange={handleSort} style={{ marginRight: "10px" }}>
                            <option value="all"></option>
                            <option value="species">Species</option>
                            <option value="breed">Breed</option>
                            <option value="name">Name</option>
                            <option value="gender">Gender</option>
                            <option value="health">Health Status</option>
                            <option value="behavior">behavior</option>
                            <option value="description">description</option>
                            <option value="house">House Training</option>
                            <option value="neutering">Neutring Status</option>
                            <option value="location">Shelter Location</option>
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
                            <div class={styles.info}>
                                <a id={index} onClick={handleReadMe}>Read Me</a>
                                <FaArrowRight className={styles.i} />
                            </div>
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
        </div>
    )
}