import React from 'react';
import './contact-style.css';

// ContactUS page component
const ContactUs = () => {
    return(
        <div className="contact">
            <div className="container">
            <br/>
                <div className="contact-box">
                    <div className="container">
                        <form action="">
                            <label>
                                Name
                                <input type="text" id="name" name="userName" placeholder="Your name.."  />
                            </label>
                            <br/>
                            <label>
                                Email
                                <input type="text" id="email" name="userEmail" placeholder="Your email.." />
                            </label>
                            <br/>
                            <label>
                                Subject
                                <textarea id="message" name="userMessage" placeholder="Write something.." style={{height: "200px"}}></textarea>
                            </label>
                            <br/>
                            <input type="submit" value="Submit" />
                        </form>
                    </div>
                    <div className="contact-right">
                            <h3 style={{color:"rgb(55, 41, 185)", marginBottom:"1em"}}>Our details</h3>
                            <p>EMAIL:<br/>weka.rmit@rmit.com</p>
                            <p>PHONE:<br/>+61 123 456 789</p>
                            <p>ADDRESS:<br/>124 La Trobe St, Melbourne VIC 3000</p>
                    </div>
                </div>
            </div>
            <h2 style={{textAlign: "center",marginBottom: "16px"}}>Meet Our Team!</h2><br/><br/>
            <div className="team" style={{textAlign: "center"}}>
                <div style={{float: "left", width: "20%"}}>
                    <h4>Sai Sreshtaa Turaga</h4>
                    <p>Front-End and Back-End Developer</p>
                </div>

                <div style={{float: "left", width: "20%"}}>
                    <h4>Zhihong Deng</h4>
                    <p>Back-End Developer and Tester</p>
                </div>

                <div style={{float: "left", width: "20%"}}>
                    <h4>Yitao Ma</h4>
                    <p>Back-End Developer and Tester</p>

                </div>

                <div style={{float: "left", width: "20%"}}>
                    <h4>Zehu Liu</h4>
                    <p>Back-End Developer and Tester</p>
                </div>

                <div style={{float: "left", width: "20%"}}>
                    <h4>Christoper Adrianus Sindarto</h4>
                    <p>Back-End Developer</p>
                </div>
            </div>
            <br/><br/><br/><br/>
        </div>
    );
}

export default ContactUs;