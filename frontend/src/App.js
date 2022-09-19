import './App.css';
import {BrowserRouter as Router, Route, Routes} from 'react-router-dom';
import React from "react";
import Films from "./components/Films";

function App() {
    return (
        <Router>
            <Routes>
                <Route path='/' exact={true} element={<Films/>}/>
                <Route path='/films' exact={true} element={<Films/>}/>
            </Routes>
        </Router>
    )
}

export default App;
