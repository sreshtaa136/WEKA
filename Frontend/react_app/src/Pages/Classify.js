import React, {useState} from 'react';
import Button from 'react-bootstrap/Button';
import { Form } from 'react-bootstrap';
import 'bootstrap/dist/css/bootstrap.min.css';
import './classify-style.css';
import axios from "axios";


// Classify page component
const Classify = () => {
    // state that stores the data summary
    const [content, setContent] = useState(null);
    // state that stores the algorithm selected for classifying
    const [algorithm, setAlgorithm] = useState(null);
    // state that stores the split percentage
    const [percentage, setPercentage] = useState("");
    // state that stores the type of target attribute
    const [nominal, setNominal] = useState(null);
    // state that stores the confusion matrix
    const [confMatrix, setConfMatrix] = useState(null);
    // state that stores the details table
    const [detailsTable, setDetailsTable] = useState(null);

    // form submit handler
    const submitHandler = (event) => {
        event.preventDefault();
        setContent(null);
        let link = "http://localhost:8082/api/classify/getDataSummary?algorithm=" + algorithm + "&percentage=" + percentage;
        let summary = null;
        axios.get(link)
            .then(res => {
                summary = res.data;
                setContent(summary);
                details();
            })
        event.target['algorithm'].value = "";
        event.target['type'].value = "";
        setAlgorithm("");
        setPercentage("");
    }

    // fetching 'details' part of the result and formatting it
    const details = () => {
        let link = "http://localhost:8082/api/classify/getDetails";
        let details = null;
        setDetailsTable(null);
        axios.get(link)
            .then(res => {
                details = Array.prototype.slice.call(res.data);
                setDetailsTable(
                    <div>
                        <div className="matrix-table">
                            <table>
                                {
                                    details.map((i) => (
                                        <tr>
                                            {
                                                i.map((j) => (
                                                    <td key={j}>
                                                        <b>{j}</b>
                                                    </td>
                                                ))
                                            }
                                        </tr>
                                    ))
                                }
                            </table>
                        </div>
                    </div>
                );
                matrix();
            })
    }

    // fetching the resultant confusion matrix and formatting it
    const matrix = () => {
        let link = "http://localhost:8082/api/classify/getConfusionMatrix";
        let matrix = null;
        setConfMatrix(null);
        axios.get(link)
            .then(res => {
                matrix = Array.prototype.slice.call(res.data);
                setConfMatrix(
                    <div>
                        <div className="matrix-table">
                            <table>
                                {
                                    matrix.map((i) => (
                                        <tr>
                                            {
                                                i.map((j) => (
                                                    <td key={j}>
                                                        <b>{j}</b>
                                                    </td>
                                                ))
                                            }
                                        </tr>
                                    ))
                                }
                            </table>
                        </div>
                    </div>
                );
            })
    }

    return (
        <div>
            <div className="form-container">
                <Form onSubmit={submitHandler}>
                    <Form.Group>
                        <Form.Select name="type" onChange={event => setNominal(event.target.value === "nominal")}>
                            <option value="">Select the type of your target attribute</option>
                            <option value="nominal">Nominal</option>
                            <option value="numeric">Numeric</option>
                        </Form.Select>
                    </Form.Group>
                    <br/>
                    <Form.Group>
                        {nominal ? (
                            <Form.Select name="algorithm" onChange={event => setAlgorithm(event.target.value)}>
                                <option value="">Select an algorithm for classification</option>
                                {/* used for nominal target variables */}
                                <option value="NaiveBayes">Naive Bayes</option>
                                <option value="ZeroR">Zero R</option>
                                <option value="Logistic">Logistic</option>
                            </Form.Select>
                        ):(
                            <Form.Select name="algorithm" onChange={event => setAlgorithm(event.target.value)}>
                                <option value="">Select an algorithm for classification</option>
                                <option>No options available</option>
                            </Form.Select>
                        )}
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
            <br/>
            <div>
                {/* If content exists, show it */}
                {content ? (
                    confMatrix ? (
                        <div className="classify-summary">
                            <div dangerouslySetInnerHTML={{ __html: content }}/>
                            <br/><br/>
                            <h2>Confusion Matrix</h2>
                            <br/>
                            {confMatrix}
                            {detailsTable ? (
                                <div className="details-summary">
                                    <br/><br/><br/>
                                    <h2>Detailed Accuracy By Class </h2>
                                    <br/><br/>
                                    {detailsTable}
                                </div>
                            ) : null}
                        </div>
                    ): null
                ): (
                    <div className="classify-content"></div>
                )}
            </div>
        </div>
    )
}

export default Classify;
