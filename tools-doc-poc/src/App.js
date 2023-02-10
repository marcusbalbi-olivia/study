import { useState } from 'react';
import ReactMarkdown from 'react-markdown'

function App() {
  const [text,setText] = useState('');
  return (
    <div className="App" style={{ display: 'flex', height: '90vh', width: '90vw' }}>
      <textarea style={{ flex: 1, fontSize: '16px' }} onChange={(e) => { setText(e.target.value) }} ></textarea>
      <div style={{ flex: 1, marginLeft: '52px' }} >
        <ReactMarkdown>{text}</ReactMarkdown>
      </div>
    </div>
  );
}

export default App;
