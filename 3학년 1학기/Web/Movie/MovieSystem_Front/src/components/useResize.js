import { useState, useEffect } from 'react';

export default function useResize() {
    const [width, setWidth] = useState(window.innerWidth);

    const getWidth = () => window.innerWidth;

    useEffect(() => {
        let time = null;

        const resizeListener = () => {
            clearTimeout(time);

            time = setTimeout(() => setWidth(getWidth()), 1000);
        };
        window.addEventListener('resize', resizeListener);

        return () => {
            window.removeEventListener('resize', resizeListener);
        };
    }, []);

    return width;
}
