import 'bootstrap/dist/css/bootstrap.min.css'
import Form from 'react-bootstrap/Form';

const SelectDanger = (props) => {
  return (
    <>
      <Form.Select ref={props.danger}>
        <option key="totalDan" value="totalDan">전체</option>
        <option key="suddenAcc" value="suddenAcc">급가속</option>
        <option key="suddenDrop" value="suddenDrop">급감속</option>
        <option key="suddenDeparture" value="suddenDeparture">급출발</option>
        <option key="suddenStop" value="suddenStop">급정지</option>
      </Form.Select>
    </>
  );
}

export default SelectDanger;