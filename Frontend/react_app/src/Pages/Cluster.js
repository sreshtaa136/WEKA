import React, {useState} from "react";
import axios from "axios";
import {Form} from "react-bootstrap";
import Button from "react-bootstrap/Button";

// Cluster page component
const Cluster = () => {
    // state that stores data summary
    const [content, setContent] = useState(null);
    // state that stores the algorithm selected for clustering
    const [algorithm, setAlgorithm] = useState(null);
    // state that stores the split percentage
    const [percentage, setPercentage] = useState("");

    // form submit handler
    const submitHandler = (event) => {
        event.preventDefault();
        setContent(null);
        let link = "http://localhost:8084/api/cluster/getSummary?algorithm=" + algorithm + "&percentage=" + percentage;
        let summary = null;
        axios.get(link)
            .then(res => {
                summary = res.data;
                setContent(summary);
            })
        event.target['algorithm'].value = "";
        setAlgorithm("");
        setPercentage("");
    }


    return (
        <div>
            <div className="form-container">
                <Form onSubmit={submitHandler}>
                    <Form.Group>
                        <Form.Select name="algorithm" onChange={event => setAlgorithm(event.target.value)}>
                            <option value="">Select an algorithm for clustering</option>
                            <option value="SimpleKmeans">Simple KMeans</option>
                            <option value="EM">Expectation Maximization (EM)</option>
                        </Form.Select>
                    </Form.Group>
                    <br/>
                    <Form.Group>
                        <Form.Label>Split Percentage</Form.Label>
                        <Form.Control type="text" value={percentage} onChange={event => setPercentage(event.target.value)}/>
                        <Form.Text muted>
                            The split percentage will be the training data % and the remainder for testing data %
                        </Form.Text>
                    </Form.Group>
                    <br/>
                    <Button variant="primary" type="submit">
                        Submit
                    </Button>
                </Form>
            </div>
            <div>
                {/* If content exists, show it */}
                {content ? (
                    <div className="cluster-summary">
                        <div dangerouslySetInnerHTML={{ __html: content }}/>
\                    </div>
                ): (
                    <div className="cluster-content"></div>
                )}
            </div>
        </div>
    )
}

export default Cluster;