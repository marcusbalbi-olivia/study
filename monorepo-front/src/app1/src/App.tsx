import React from 'react';
import './App.css';
import { Button, Header } from "@monorepo/lib2"

function App() {
  return (
    <div className="App">
      <Header title='BLAUD'></Header>
      <Button onClick={() => { alert('ok!') }} label='teste' />
    </div>
  );
}

export default App;
