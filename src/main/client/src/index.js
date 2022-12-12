import {createRoot} from 'react-dom/client';
import './index.css';
import Login from './Login';
import Employee from './Employee';
import {BrowserRouter as Router} from 'react-router-dom';
import {Routes, Route} from 'react-router-dom';

const rootElement = document.getElementById('root');
const root = createRoot(rootElement);

root.render(
  <Router>
    <Routes>
      <Route path="/*" element={<Login />}/>
      <Route path="/dashboard/*" element={<Employee />} />
    </Routes>
  </Router>
);