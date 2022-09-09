import React, { useEffect, useState } from 'react';
import { Button, ButtonGroup, Container, Table } from 'reactstrap';
import AppNavbar from './AppNavbar';
import { Link } from 'react-router-dom';
import axios from "axios";

const UserList = () => {

	const [users, setUsers] = useState([]);
	const [loading, setLoading] = useState(false);
	const [notifications, setNotifications] = useState([]);

	useEffect(() => {
		setLoading(true);
		
		fetch('/api/users')
			.then(response => response.json())
			.then(data => {
				setUsers(data);
				setLoading(false);
			})
	}, []);

	

	const remove = async (id) => {
		await fetch(`/api/user/${id}`, {
			method: 'DELETE',
			headers: {
				'Accept': 'application/json',
				'Content-Type': 'application/json'
			}
		}).then(() => {
			let updatedUsers = [...users].filter(i => i.id !== id);
			setUsers(updatedUsers);
		});
	}

	if (loading) {
		return <p>Loading...</p>;
	}

	const userList = users.map(user => {

		return <tr key={user.id}>
			<td style={{ whiteSpace: 'nowrap' }}>{user.userName}</td>
			<td>{user.email}</td>

			<td>
				<ButtonGroup>
					<Button size="sm" color="primary" tag={Link} to={"/users/" + user.id}>Edit</Button>
					<Button size="sm" color="danger" onClick={() => remove(user.id)}>Delete</Button>
				</ButtonGroup>
			</td>
		</tr>
	});

	return (
		<div>
			<AppNavbar />
			<Container fluid>
				<div className="float-end">
					<Button color="success" tag={Link} to="/users/new">Add User</Button>
				</div>
				<h3>My Users</h3>
				<Table className="mt-4">
					<thead>
						<tr>
							<th width="20%">Name</th>
							<th width="20%">Email</th>
							<th>Notifications</th>
							<th width="10%">Actions</th>
						</tr>
					</thead>
					<tbody>
						{userList}
					</tbody>
				</Table>
			</Container>
		</div>
	);
};

export default UserList;