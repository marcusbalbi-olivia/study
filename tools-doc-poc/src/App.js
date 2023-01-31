import { useState } from 'react';
import ReactMarkdown from 'react-markdown'

function App() {
  const [text,setText] = useState('');
  return (
    <div className="App">
      <button>Edit</button>
      <button>Preview</button>
      <textarea onChange={(e) => { setText(e.target.value) }} ></textarea>
      <ReactMarkdown>{text}</ReactMarkdown>
    </div>
  );
}

export default App;
