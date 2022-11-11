import React from "react";

interface HeaderProps {
  title: string;
}

export const Header: React.FC<HeaderProps> = ({ title }) => {
//   const [over, setOver] = useState("not over");
  return (
    <header
    //   onMouseOver={() => {
    //     setOver("Over!");
    //   }}
    //   onMouseLeave={() => {
    //     setOver("not over");
    //   }}
    >
      <h2>
        {title}
      </h2>
    </header>
  );
};
