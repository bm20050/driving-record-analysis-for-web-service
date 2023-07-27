import Button from 'react-bootstrap/Button';
import React, { useEffect, useState } from "react";
import 'bootstrap/dist/css/bootstrap.min.css'

const LoadingButton = (props) => {

    const [isLoading, setLoading] = useState(false);

    useEffect(() => {
        if (isLoading) {
            setLoading(false);
        }
    }, [props.next]);

    const handleClick = () => {
        setLoading(true);
        props.searchData();
    }

    return (
        <Button
            variant="outline-primary"
            disabled={isLoading}
            onClick={!isLoading ? handleClick : null}
        >
            {isLoading ? 'Loading…' : '조회'}
        </Button>
    );
}

export default LoadingButton;