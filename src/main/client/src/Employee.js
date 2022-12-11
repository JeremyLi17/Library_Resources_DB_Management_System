import './Employee.css';

const emp_first_name = "Kun"
const emp_last_name = "Xiao"

export default function Employee() {

  return (
      
      <div className='Home'>
          <header className='Home_Header'>
           Customer Information DashBoard, opened by {emp_first_name} {emp_last_name}
          </header>
      </div>
  );
}

// export default App;
