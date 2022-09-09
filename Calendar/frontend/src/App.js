import React from 'react';
import './App.css';
import Home from './Components/Home';
import { BrowserRouter as Router, Route, Routes } from 'react-router-dom';
import GroupList from './Components/UserList';
//import GroupEdit from './Components/GroupEdit';

const App = () => {
	return (
		<Router>
			<Routes>
				<Route exact path="/" element={<Home />} />
				<Route path='/groups' exact={true} element={<GroupList />} />
			</Routes>
		</Router>
	)
}

export default App;