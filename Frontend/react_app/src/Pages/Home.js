import React, {useState} from 'react';
import axios from 'axios';
import video from '../Videos/weka_video.mp4';
import './style.css';

// Home component
const Home = () => {

    const [file, setFile] = useState('');
    const [fileName, setFileName] = useState("");
    const [content, setContent] = useState(null);

    const uploadFileHandler = (event) => {
        setFile(event.target.files[0]);
        setFileName(event.target.files[0].name);
    };

    // upload the file
    const fileSubmitHandler = (event) => {
        event.preventDefault();
        setContent(null);
        const formData = new FormData();
        formData.append(`file`, file);

        const requestOptions = {
            method: 'POST',
            body: formData
        };

        // send the filename to the classify microservice
        let link = "http://localhost:8082/api/classify/setFilename?fileName=" + fileName;
        axios.post(link).then(
            r => {
                console.log(r.status);
            }
        );

        // send the filename to the cluster microservice
        link = "http://localhost:8084/api/cluster/setFilename?fileName=" + fileName;
        axios.post(link).then(
            r => {
                console.log(r.status);
            }
        );

        // send the filename to the filter microservice
        link = "http://localhost:8083/api/filter/setFilename?fileName=" + fileName;
        axios.post(link).then(
            r => {
                console.log(r.status);
            }
        );

        // upload the file to load-data microservice
        let FILE_UPLOAD_BASE_ENDPOINT = "http://localhost:8081/api/load-data/";
        fetch(FILE_UPLOAD_BASE_ENDPOINT + 'uploadFile', requestOptions)
            .then(r  => {
                if(r.ok) {
                    pageData();
                }
            });
    };

    // creating the data summary table
    const pageData = () => {
        // fetching data summary
        let link = "http://localhost:8081/api/load-data/getDataSummary?fileName=" + fileName;
        let summary = null;
        axios.get(link)
            .then(res => {
                summary = res.data;
                let rows = Array.prototype.slice.call(summary);
                let final = [];
                let header = ["ID", "AttributeName", "Type", "Nominal", "Integer", "Real", "MissingValues", "UniqueValues", "DistinctValues"];
                let info = [];
                for(let i=0; i < rows.length;i++) {
                    if (i > 4) {
                        let data = rows[i];
                        let data_array = data.split(" ");
                        for(let j=0; j < data_array.length;j++) {
                            if(data_array[j] === "" || data_array[j] === " ") {
                                delete data_array[j];
                            }
                        }
                        final.push(data_array);
                    }
                    else if (i < 3) {
                        if (rows[i] !== "") {
                            info.push(rows[i]);
                        }
                    }
                }

                if(fileName !== "") {
                    setContent(
                        <div>
                            <h3><b>The file '{fileName}' has been successfully uploaded!</b></h3>
                            <br/><br/><br/>
                            {
                                info.map((i) => (
                                    <h3 key={i}><b>{i}</b></h3>
                                ))
                            }
                            <br/><br/>
                            <div className="data-table">
                                <table>
                                    <tr>
                                        {
                                            header.map((i) => (
                                                <th key={i}> {i} </th>
                                            ))
                                        }
                                    </tr>
                                    {
                                        final.map((i) => (
                                            <tr>
                                                {
                                                    i.map((j) => (
                                                        <td key={j}>{j}</td>
                                                    ))
                                                }
                                            </tr>
                                        ))
                                    }
                                </table>
                            </div>
                        </div>
                    );
                }
            })
    }


    return (
          <div className="grid-container">
              <section className="video-sect">
                  <div className="video-content">
                      <h1 id="text" style={{fontSize: "40px"}}><b>WEKA IS NOW IN CLOUD</b></h1>
                  </div>
                  <div className="video">
                      <video autoPlay muted loop id="video">
                          <source src={video}
                                  type="video/mp4" />
                          The web browser cannot support mp4
                      </video>
                  </div>
              </section>
              <br/>
              <br/>
              <br/>
              <br/>
              <section className="file-upload">
                  <h2><b>Start by uploading a database file</b></h2>
                  <form className="file-upload-form" onSubmit={fileSubmitHandler}>
                      <input type="file" className="selectFile" accept=".arff,.arff.gz,.names,.data,.csv,.json,.json.gz,
                      .libsvm,.m,.dat,.bsi,.xrff,.xrff.gz" onChange={uploadFileHandler} />
                      <br /><br />
                      <button className="uploadFile" type='submit' >Upload File</button>
                  </form>
                  {/* If content exists, show it */}
                  {content ? (
                      <div className="data-summary">
                          {content}
                      </div>
                  ):null}
              </section>
          </div>
    );
}

export default Home;