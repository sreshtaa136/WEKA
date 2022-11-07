import React from 'react';
import './style.css';

// AboutUs page component
const About = () => {
  return (
    <div>
        <div className="about-weka">
                <img className="background" src="https://images.pexels.com/photos/7311920/pexels-photo-7311920.jpeg?cs=srgb&dl=pexels-alberlan-barros-7311920.jpg&fm=jpg" alt="" />
                <span className="weka-intro ">WEKA (Waikato Environment for Knowledge Analysis) is a free open-source software that was developed
                    at the University of Waikato, New Zealand. The current user interface of WEKA doesn’t make it any easier for students to use it.
                    It is a complex software which is not very user-friendly especially for students with no technical experience.<br/>
                    Our team together with Professor James Harland (Director, RMIT STEM Centre for Digital Innovation), Dr.Robert Shen (Director, RMIT RACE Hub) and Dr.Haytham Fayek (AI/ML Lecturer at RMIT)
                    have created this easy-to-use website version of WEKA, with the same behaviour and features, and with an improved user-interface.
                </span>
        </div>

        <div className="more">
            <div className="box">
                <div className="content">
                    <h3 style={{marginTop: "3em"}}>History of WEKA</h3>
                    <p style={{marginBottom: "2em"}}> In 1993, the University of Waikato in New Zealand began development of the original version of Weka.
                        In 1997, the decision was made to redevelop Weka from scratch in Java, including implementations of modeling algorithms.
                        In 2005, Weka received the SIGKDD Data Mining and Knowledge Discovery Service Award.
                        In 2006, Pentaho Corporation acquired an exclusive licence to use Weka for business intelligence.</p>
                </div>
            </div>

            <div className="box2">
                <div className="content2">
                    <h3 style={{marginTop: "3em"}}>Advantages of WEKA</h3>
                    <p style={{marginBottom: "2em"}}>  Free availability under the GNU General Public License. <br/>
                        Portability, since it is fully implemented in the Java programming language and thus runs on almost any modern computing platform. <br/>
                        A comprehensive collection of data preprocessing and modeling techniques.</p>
                </div>
            </div>

            <div className="box3">
                <div className="content">
                    <h3 style={{marginTop: "1em"}}>Our Goal</h3>
                    <p>1. Make our website accessible from any kind of browser<br/>
                    2. Make it as user-friendly as possible so that students from various disciplines,
                        even the ones with little or no programming/technical background can use it<br/>
                    3. Make it an open-source project someday, contributing to the data science community </p>
                </div>
            </div>
        </div>
    </div>
  );
}

export default About;