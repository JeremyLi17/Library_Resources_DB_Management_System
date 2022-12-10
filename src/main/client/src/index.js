import {createRoot} from 'react-dom/client';
import './index.css';
import Form from './Form';
import {BrowserRouter as Router} from 'react-router-dom';
// import ReactDOM from 'react-dom'

// const root = ReactDOM.createRoot(document.getElementById('root'));
// root.render(<App />);
  
//   const props = {
//     name: 'loginForm',
//     method: 'POST',
//     action: '/perform_login',
//     inputs: inputs
//   }
  
//   const params = new URLSearchParams(window.location.search)
  
  const rootElement = document.getElementById('root');
  const root = createRoot(rootElement);

  root.render(
    <Router>
      <Form />
    </Router>
  );
