import React from 'react';

interface HeaderProps {
    title: string;
}

export const Header: React.FC<HeaderProps> = ({title}) => {
    return (
        <header>
            <h2>{title}</h2>
        </header>
    )
}