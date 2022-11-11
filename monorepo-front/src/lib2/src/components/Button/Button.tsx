import React, { useState } from "react";
import "./Button.scss";

export interface ButtonProps
  extends React.DetailedHTMLProps<
    React.ButtonHTMLAttributes<HTMLButtonElement>,
    HTMLButtonElement
  > {
  label: string;
}

export const Button = (props: ButtonProps) => {
  const [count, setCount] = useState(0);
  return <button {...props}>{props.label}</button>;
};
