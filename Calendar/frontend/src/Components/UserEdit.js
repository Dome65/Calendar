import React, { useEffect, useState } from 'react';
import { Link, useNavigate, useParams } from 'react-router-dom';
import { Button, Container, Form, FormGroup, Input, Label } from 'reactstrap';
import AppNavbar from './AppNavbar';

const UserEdit = () => {
	const initialFormState = {
		userName: '',
		email: '',
		password: '',

	};
	const [user, setUser] = useState(initialFormState);
	const navigate = useNavigate();
	const { id } = useParams();

	useEffect(() => {
		if (id !== 'new') {
			fetch(`/api/user/${id}`)
				.then(response => response.json())
				.then(data => setUser(data));
		}
	}, [id, setUser]);

	const handleChange = (event) => {
		const { name, value } = event.target

		setUser({ ...user, [name]: value })
	}

	const handleSubmit = async (event) => {
		event.preventDefault();

		await fetch('/api/user' + (user.id ? '/' + user.id : ''), {
			method: (user.id) ? 'PUT' : 'POST',
			headers: {
				'Accept': 'application/json',
				'Content-Type': 'application/json'
			},
			body: JSON.stringify(user)
		});
		setUser(initialFormState);
		navigate('/users');
	}

	const title = <h2>{user.id ? 'Edit User' : 'Add User'}</h2>;

	return (<div>
		<AppNavbar />
		<Container>
			{title}
			<Form onSubmit={handleSubmit}>
				<FormGroup>
					<Label for="userName">Username</Label>
					<Input type="text" name="userName" id="userName" value={user.userName || ''}
						onChange={handleChange} autoComplete="userName" />
				</FormGroup>
				<FormGroup>
					<Label for="email">Email</Label>
					<Input type="text" name="email" id="email" value={user.email || ''}
						onChange={handleChange} autoComplete="email" />
				</FormGroup>
				<FormGroup>
					<Label for="password">Password</Label>
					<Input type="text" name="password" id="password" value={user.password || ''}
						onChange={handleChange} autoComplete="password" />
				</FormGroup>

				<FormGroup>
					<Button color="primary" type="submit">Save</Button>{' '}
					<Button color="secondary" tag={Link} to="/users">Cancel</Button>
				</FormGroup>
			</Form>
		</Container>
	</div>
	)
};

export default UserEdit;