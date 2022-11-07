import React from 'react';
import './Pages/style.css';
import { Link } from 'react-router-dom'

// Navbar component
const Navbar = () => {
  return (
    <div>
        <div className="left-nav">
            <img className="rmit-logo"
                src="https://keypath.uk.com/sites/uk/files/styles/half_width_600w_/public/image/2020-07/Rmit-White.png?itok=hUXxaWnz"
                alt="RMIT's logo" />
            <img className="weka-logo"
                src="https://dashboard.snapcraft.io/site_media/appmedia/2021/10/weka.png"
                alt="WEKA's logo" />
        </div>
        <div className="right-nav">
            <nav>
                <ul>
                    <li><Link to="/">Home</Link></li>
                    <li><Link to="/preprocess">Preprocess</Link></li>
                    <li><Link to="/classify">Classify</Link></li>
                    <li><Link to="/cluster">Cluster</Link></li>
                    {/*<li><Link to="/visualise">Visualise</Link></li>*/}
                    <li><Link to="/help">Help</Link></li>
                </ul>
            </nav>
        </div>
    </div>
  );
}

export default Navbar;