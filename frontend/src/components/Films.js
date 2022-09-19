import React, {Component} from "react";
import Table from 'react-bootstrap/Table'
import Form from 'react-bootstrap/Form';
import Button from 'react-bootstrap/Button';
import {getFilmsData} from "../actions/FilmsActions";
import {ArrowLeft, ArrowRight} from 'react-bootstrap-icons';
import {Col, Row} from "react-bootstrap";

class Films extends Component {

    constructor(props) {
        super(props);
        this.state = {
            data: {
                content: [],
                totalElements: 0,
                totalPages: 0,
                pageable: {
                    offset: 0,
                    pageNumber: 0,
                    pageSize: 10
                }
            },
            filter: {
                lastName: '',
                releasedFrom: '',
                releasedUntil: '',
                page: 0,
                size: 10
            },
            releasedFromValidate: false,
            releasedUntilValidate: false,
            disable: false
        };
    }

    async componentDidMount() {
        const response = await getFilmsData(this.state.filter)
        this.setState({data: response})
    }

    async changePageSize(event) {
        const currentSize = event.target.value
        const {filter} = this.state
        if (currentSize === 'All') {
            filter.size = this.state.data.totalElements
        } else {
            filter.size = currentSize
        }
        const result = await getFilmsData(filter)
        this.setState({filter: filter, data: result})
    }

    async turnRightPage() {
        const {filter} = this.state
        filter.page = this.state.data.pageable.pageNumber + 1
        const result = await getFilmsData(filter)
        this.setState({filter: filter, data: result})
    }

    async turnLeftPage() {
        const {filter} = this.state
        filter.page = this.state.data.pageable.pageNumber - 1
        const result = await getFilmsData(filter)
        this.setState({filter: filter, data: result})
    }

    checkYearInput(event) {
        const id = event.target.id
        const value = event.target.value
        const input = Number.parseInt(value)
        const isValid = value === '' || (Number.isInteger(input) && input > 1800)
        if (id === "from") {
            this.setState({releasedFromValidate: !isValid, disable: !isValid})
        } else if (id === "until") {
            this.setState({releasedUntilValidate: !isValid, disable: !isValid})
        }
    }

    async handleOnSearch(event) {
        event.preventDefault();
        event.stopPropagation();
        const releasedFrom = event.target.from.value
        const releasedUntil = event.target.until.value
        const lastName = event.target.lastName.value
        if (releasedUntil !== '' && releasedFrom > releasedUntil) {
            alert("Released From[" + releasedFrom + "] should be less or equal than released Until[" + releasedUntil + "]")
            this.setState({disable: true})
        } else {
            const {filter} = this.state
            filter.lastName = lastName
            filter.releasedFrom = releasedFrom
            filter.releasedUntil = releasedUntil
            filter.page = 0
            const result = await getFilmsData(filter)
            this.setState({filter: filter, data: result})
        }
    }

    render() {
        const {content, pageable, totalPages, totalElements} = this.state.data
        const {releasedFromValidate, releasedUntilValidate, disable} = this.state
        return (
            <div>
                <Form onSubmit={this.handleOnSearch.bind(this)} className="row align-items-center">
                    <Col className="mb-3">
                        <Form.Label>Last Name</Form.Label>
                        <Form.Control id="lastName"/>
                    </Col>
                    <Col className="mb-3">
                        <Form.Label>Released From</Form.Label>
                        <Form.Control onChange={this.checkYearInput.bind(this)}
                                      isInvalid={releasedFromValidate}
                                      id="from"/>
                        <Form.Control.Feedback type="invalid">
                            Released From must be more than 1800
                        </Form.Control.Feedback>
                    </Col>
                    <Col className="mb-3">
                        <Form.Label>Released Until</Form.Label>
                        <Form.Control onChange={this.checkYearInput.bind(this)} isInvalid={releasedUntilValidate}
                                      id="until"/>
                        <Form.Control.Feedback type="invalid">
                            Released Until must be more than 1800
                        </Form.Control.Feedback>
                    </Col>
                    <Col className="mt-3">
                        <Button disabled={disable} type="submit">Search</Button>
                    </Col>
                </Form>
                <Table striped bordered hover>
                    <thead>
                    <tr>
                        <th>First Name</th>
                        <th>Last Name</th>
                        <th>Birth Date</th>
                        <th>Film Name</th>
                        <th>Release Date</th>
                        <th>Genre</th>
                    </tr>
                    </thead>
                    <tbody>
                    {content.map((film, index) =>
                        <tr key={index} className={index % 2 === 0 ? "table-success" : "table-primary"}>
                            <td>{film.directorFirstName}</td>
                            <td>{film.directorLastName}</td>
                            <td>{film.directorBirthDate}</td>
                            <td>{film.filmName}</td>
                            <td>{film.filmReleaseDate}</td>
                            <td>{film.filmGenre}</td>
                        </tr>
                    )}
                    </tbody>
                </Table>
                <Row className="justify-content-center align-items-center">
                    <Col xs="auto">
                        <b>Row per page:</b>
                    </Col>
                    <Col>
                        <Form.Select style={{width: 70}} size="sm" onClick={this.changePageSize.bind(this)}
                                     defaultValue={pageable.pageSize}>
                            <option>5</option>
                            <option>10</option>
                            <option>20</option>
                            <option>All</option>
                        </Form.Select>
                    </Col>
                    <Col xs="auto">
                        <b>{pageable.offset + 1} - {pageable.offset + pageable.pageSize < totalElements ? pageable.offset + pageable.pageSize : totalElements} of {totalElements}</b>
                    </Col>
                    <Col xs="auto">
                        <Button size="sm" variant="outline-dark" disabled={pageable.pageNumber === 0}
                                onClick={this.turnLeftPage.bind(this)}>
                            <ArrowLeft/>
                        </Button>
                    </Col>
                    <Col xs="auto">
                        <Button size="sm" variant="outline-dark" disabled={pageable.pageNumber === totalPages - 1}
                                onClick={this.turnRightPage.bind(this)}>
                            <ArrowRight/>
                        </Button>
                    </Col>
                </Row>
            </div>
        );
    }
}

export default Films