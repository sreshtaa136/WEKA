import React from 'react';
import './Pages/style.css';

// Footer component
const Footer = () => {
  return (
    <div>
        {/* configuring links inside the footer */}
        <footer className="footer">
            <li><a href="/about">About us</a></li>
            <li><a href="/contact">Contact us</a></li>
        </footer>
    </div>
  );
}

export default Footer;