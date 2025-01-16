import './App.css';
import 'bootstrap/dist/css/bootstrap.min.css';
import SelectionBar from './components/SelectionBar';
import Institution from './components/Institution';
import Event from './components/event';
import { useState } from 'react';

function App() {
  const [showInstitution, setShowInstitution] = useState(false);
  const changePage = () => {
    setShowInstitution(!showInstitution);
  };
  return (
    <div className="App">
      <div className='container'>
        <div class="row">
          <div class="col-3">
            <SelectionBar showInstitution={showInstitution} changePage={changePage}/>
          </div>
          <div class="col-8">
            {showInstitution ? <Institution/> : <Event/>}
          </div>
        </div>
      </div>
    </div>
  );
}

export default App;
