import Button from 'react-bootstrap/Button';
import React, { useEffect, useState } from "react";
import 'bootstrap/dist/css/bootstrap.min.css'

const LoadingButton = (probs) => {

    const [isLoading, setLoading] = useState(false);

    useEffect(() => {
        if (isLoading) {
            setLoading(false);
        }
    }, [probs.next]);

    const handleClick = () => {
        setLoading(true);
        probs.searchData();
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