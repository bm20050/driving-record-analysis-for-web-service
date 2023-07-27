import Chart from "./charts/Chart";
import Accordion from 'react-bootstrap/Accordion';
import { Table } from "react-bootstrap";

// import 'bootstrap/dist/css/bootstrap.min.css'

const Right = (props) => {

    return (
        <>

            <div className="chartarea">
                <div className="explain">
                    <Accordion>
                        <Accordion.Item eventKey="0">
                            <Accordion.Header>위험운전행동기준&nbsp;<span style={{fontSize:'0.8em'}}>  (출처: 한국교통안전공단)</span></Accordion.Header>
                            <Accordion.Body>
                                <Table bordered size="sm">
                                    <tbody>
                                        <tr>
                                            <td rowSpan={3} align="center">급가속</td>
                                            <td>10km/h이하</td>
                                            <td>6.0km/h이상 속도에서 초당 8km/h 이상 가속 운행한 경우</td>
                                        </tr>
                                        <tr>
                                            <td>20km/h이하</td>
                                            <td>6.0km/h이상 속도에서 초당 7km/h 이상 가속 운행한 경우</td>
                                        </tr>
                                        <tr>
                                            <td>20km/h초과</td>
                                            <td>6.0km/h이상 속도에서 초당 6km/h 이상 가속 운행한 경우</td>
                                        </tr>
                                        <tr>
                                            <td align="center">급출발</td>
                                            <td colSpan={2}>5.0km/h 이하 속도에서 출발하여 초당 8km/h 이상 가속 운행한 경우</td>
                                        </tr>
                                        <tr>
                                            <td rowSpan={3} align="center">급감속</td>
                                            <td>30km/h이하</td>
                                            <td>초당 9km/h 이상 감속 운행하고 속도가 6.0km/h 이상인 경우</td>
                                        </tr>
                                        <tr>
                                            <td>50km/h이하</td>
                                            <td>초당 10km/h 이상 감속 운행하고 속도가 6.0km/h 이상인 경우</td>
                                        </tr>
                                        <tr>
                                            <td>50km/h초과</td>
                                            <td>초당 12km/h 이상 감속 운행하고 속도가 6.0km/h 이상인 경우</td>
                                        </tr>
                                        <tr>
                                            <td align="center">급정지</td>
                                            <td colSpan={2}>초당 9km/h 이상 감속하여 속도가 5.0km/h 이하가 된 경우</td>
                                        </tr>
                                    </tbody>
                                </Table>
                            </Accordion.Body>
                        </Accordion.Item>
                    </Accordion>

                </div>
                <Chart chartData={props.chartData} />
            </div>
        </>
    )
}

export default Right;
