import axios from 'axios';
import { Link, useNavigate } from 'react-router-dom'
import Button from 'react-bootstrap/Button';
import logo from '../image/logo.png'

const Header = () => {

    const navigator = useNavigate();

    const handleLogout = async () => {
        console.log('로그아웃 버튼클릭')

        await axios
        .post('/api/user/logout')
        .then((response) => {
            console.log(response)
            sessionStorage.removeItem('isLoggedIn')
            navigator('/')
        })
        .catch((error) => {
            console.log(error)
        })
    }

    return (
        <>
            <div className='headerimage'>
                <Link to="/"><img src={logo} /></Link>
            </div>
            <div className='headerdetail'>
                <div className='headerbutton'>
                    <Link to="/" style={{ textDecoration: 'none' }}>HOME</Link>
                </div>
                <div className='headerbutton'>
                    <Link to="/prediction" style={{ textDecoration: 'none' }}>PRIVATE</Link>
                </div>
                <div className='headerbutton'>
                    <Link to="/myPage" style={{ textDecoration: 'none' }}>MY PAGE</Link>
                </div>
                <div className='signbutton'>
                    {sessionStorage.getItem('isLoggedIn') ? 
                        <Button variant="outline-primary" onClick={handleLogout}>LOGOUT</Button> :
                        <Button variant="outline-primary">
                            <Link to="/login" style={{ textDecoration: 'none' }}>SIGN IN</Link>
                        </Button>
                    }
                    <Button variant="outline-primary">
                        <Link to="/join" style={{ textDecoration: 'none' }}>SIGN UP</Link>
                    </Button>
                </div>
            </div>
        </>

    )

}

export default Header;