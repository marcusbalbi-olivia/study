import React from 'react';
interface HeaderProps {
    title: string;
}
export const Header: React.FC<HeaderProps> = (props) => {
    return (
        <header>
            <h2>{props.title}</h2>
        </header>
    )
}
