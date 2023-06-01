import 'bootstrap/dist/css/bootstrap.min.css'
import Form from 'react-bootstrap/Form';

const SelectPlate = (probs) => {
  return (
    <>
      <Form.Select ref={probs.plate}>
        <option key="total" value="total">전체</option>
        <option key="부산70자1854" value="부산70자1854">부산70자1854</option>
        <option key="부산70자1860" value="부산70자1860">부산70자1860</option>
        <option key="부산70자1893" value="부산70자1893">부산70자1893</option>
        <option key="부산70자1894" value="부산70자1894">부산70자1894</option>
      </Form.Select>
    </>
  );
}

export default SelectPlate;