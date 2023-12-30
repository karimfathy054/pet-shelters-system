import styles from "../CSS/profileStyles.module.css"
import Navbar from "../ProfileComponents/Navbar"
import Info from "../ProfileComponents/Info";
import { useLocation } from "react-router-dom";
import { useState, useEffect } from "react";
import CreateStaff from "../ProfileComponents/CreateStaff";
import Requests from "../ProfileComponents/Requests";
import CreateShelter from "../ProfileComponents/CreateShelter";
import Notify from "../ProfileComponents/Notify";
import Assign from "../ProfileComponents/Assign";
import Upgrade from "../ProfileComponents/Upgrade";
export default function Profile() {
  const location = useLocation();
  const [user, setUser] = useState({});
  useEffect(() => {
    if (location.state != null) {
      setUser(location.state.user);
    }
  })


  return (
    <>
      <div className={styles.profile}>
        <Navbar user={user}></Navbar>
        <Info user={user}></Info>
        <CreateStaff user={user}></CreateStaff>
        <Requests user={user}></Requests>
        <CreateShelter user={user}></CreateShelter>
        <Notify user={user}></Notify>
        <Assign user={user}></Assign>
        <Upgrade user={user}></Upgrade>
      </div>
    </>
  )
}