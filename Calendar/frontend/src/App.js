import React from 'react';
import './App.css';
import Home from './Components/Home';
import { BrowserRouter as Router, Route, Routes } from 'react-router-dom';
import UserList from './Components/UserList';
import UserEdit from './Components/UserEdit';
import NotificationEdit from './Components/NotificationEdit';
import NotificationList from './Components/NotificationList';

const App = () => {
	return (
		<Router>
			<Routes>
				<Route exact path="/" element={<Home />} />
				<Route path='/users' exact={true} element={<UserList />} />
				<Route path='/users/:id' element={<UserEdit />} />
				<Route path='/notifications/:id' element={<NotificationEdit />} />
				<Route path='/notifications' element={<NotificationList />} />
			</Routes>
		</Router>
	)
}

export default App;