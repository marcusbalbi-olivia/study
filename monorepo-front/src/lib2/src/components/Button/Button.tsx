import React from "react";
import "./Button.scss";

export interface ButtonProps
  extends React.DetailedHTMLProps<
    React.ButtonHTMLAttributes<HTMLButtonElement>,
    HTMLButtonElement
  > {
  label: string;
}

export const Button = (props: ButtonProps) => {
  return <button {...props}>{props.label}</button>;
};
