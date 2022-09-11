import React, { useEffect, useState } from 'react';
import { Link, useNavigate, useParams } from 'react-router-dom';
import { Button, Container, Form, FormGroup, Input, Label } from 'reactstrap';
import AppNavbar from './AppNavbar';

const NotificationEdit = () => {
	const initialFormState = {
		name: '',
		content: '',
		time: '',
		date: '',
		userId: '',

	};
	const [notification, setNotification] = useState(initialFormState);
	const navigate = useNavigate();
	const { id } = useParams();

	useEffect(() => {
		if (id !== 'new') {
			fetch(`/api/notification/${id}`)
				.then(response => response.json())
				.then(data => setNotification(data));
		}
	}, [id, setNotification]);

	const handleChange = (event) => {
		const { name, value } = event.target

		setNotification({ ...notification, [name]: value })
	}

	const handleSubmit = async (event) => {
		event.preventDefault();

		await fetch('/api/notification' + (notification.id ? '/' + notification.id : ''), {
			method: (notification.id) ? 'PUT' : 'POST',
			headers: {
				'Accept': 'application/json',
				'Content-Type': 'application/json'
			},
			body: JSON.stringify(notification)
		});
		setNotification(initialFormState);
		navigate('/notifications');
	}

	const title = <h2>{notification.id ? 'Edit Notification' : 'Add Notification'}</h2>;

	return (<div>
		<AppNavbar />
		<Container>
			{title}
			<Form onSubmit={handleSubmit}>
				<FormGroup>
					<Label for="name">Name</Label>
					<Input type="text" name="name" id="name" value={notification.name || ''}
						onChange={handleChange} autoComplete="name" />
				</FormGroup>
				<FormGroup>
					<Label for="content">Content</Label>
					<Input type="text" name="content" id="content" value={notification.content || ''}
						onChange={handleChange} autoComplete="content" />
				</FormGroup>
				<FormGroup>
					<Label for="time">Time</Label>
					<Input type="time" name="time" id="time" value={notification.time || ''}
						onChange={handleChange} autoComplete="time" />
				</FormGroup>
				<FormGroup>
					<Label for="date">Date</Label>
					<Input type="date" name="date" id="date" value={notification.date || ''}
						onChange={handleChange} autoComplete="date" />
				</FormGroup>
				<FormGroup>
					<Label for="userId">UserId</Label>
					<Input type="text" name="userId" id="userId" value={notification.userId || ''}
						onChange={handleChange} autoComplete="userId" />
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

export default NotificationEdit;