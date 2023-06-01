import { Link } from 'react-router-dom'
import Button from 'react-bootstrap/Button';
import bus from '../image/bus.jpg'
import logo from '../image/logo.png'

const Header = () => {


    return (
        <>
            <div className='headerimage'>
                <Link to="/"><img src={logo} width="200" /></Link>
            </div>
            <div className='headerdetail'>
                <div className='headerbutton'>
                    <Link to="/" style={{ textDecoration: 'none' }}>HOME</Link>
                </div>
                <div className='headerbutton'>
                    <Link to="/personalAnalysis" style={{ textDecoration: 'none' }}>PRIVATE</Link>
                </div>
                <div className='headerbutton'>
                    <Link to="/myPage" style={{ textDecoration: 'none' }}>MY PAGE</Link>
                </div>
                <div className='signbutton'>
                    <Button variant="outline-primary">
                        <Link to="/login" style={{ textDecoration: 'none' }}>SIGN IN</Link>
                    </Button>
                    <Button variant="outline-primary">
                        <Link to="/join" style={{ textDecoration: 'none' }}>SIGN UP</Link>
                    </Button>
                </div>
            </div>
        </>

    )

}

export default Header;