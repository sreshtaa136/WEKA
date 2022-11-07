import React from 'react';
import { BrowserRouter as Router, Routes, Route } from "react-router-dom";
import './Pages/style.css';
import About from "./Pages/About";
import Home from "./Pages/Home";
import Error from "./Pages/Error"
import Navbar from "./Navbar";
import Footer from "./Footer";
import Classify from "./Pages/Classify";
import Cluster from "./Pages/Cluster";
import Help from "./Pages/Help";
import ContactUs from "./Pages/ContactUs";
import Filter from "./Pages/Filter";

// Main app component
const App = () => {
  return (
    <div className='routes'>
        {/* configuring component routes for navigation */}
        <Router>
          <Navbar />
          <Routes>
            <Route path='/' element={<Home />} />
            <Route path='/preprocess' element={<Filter />} />
            <Route path='/classify' element={<Classify />} />
            <Route path='/cluster' element={<Cluster />} />
            <Route path='/contact' element={<ContactUs />} />
            <Route path='/about' element={<About />} />
            <Route path='/help' element={<Help />} />
            <Route path='*' element={<Error />} />
          </Routes>
          <Footer />
        </Router>
    </div>
  );
}

export default App;
