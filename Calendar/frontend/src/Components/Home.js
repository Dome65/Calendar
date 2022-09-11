import React from 'react';
import '../App.css';
import AppNavbar from './AppNavbar';
import { Link } from 'react-router-dom';
import { Button, Container } from 'reactstrap';

const Home = () => {
	return (
		<div>
			<AppNavbar />
			<Container fluid>
				<Button color="link"><Link to="/users">Manage Users</Link></Button>
				<Button color="link"><Link to="/notifications">Manage Notifications</Link></Button>
			</Container>
		</div>
	);
}

export default Home;