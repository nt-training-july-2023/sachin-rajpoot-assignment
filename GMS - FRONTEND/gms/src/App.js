// import "./App.css";
import Login from "./Components/Login";
import { Routes, Route } from "react-router-dom";
import Navbar from "./Components/Navbar";
import Home from "./Components/Home";
import Footer from "./Components/Footer";
import UserRegistration from "./Components/UserRegistration";
import NewTicket from "./Components/NewTicket";

// import 'bootstrap/dist/css/bootstrap.min.css';

function App() {
  return (
    <div className="App">
      <Navbar/>
      {/* <Login/> */}
     
      <Routes>
        <Route path="/" element={<Home />}></Route>
        <Route path="/login" element={<Login/>}></Route>
        <Route path="/newuser" element={<UserRegistration/>}></Route>
        <Route path="/newdept" element={<NewTicket/>}></Route>
      </Routes>
      <Footer/>
    </div>
  );
}

export default App;
