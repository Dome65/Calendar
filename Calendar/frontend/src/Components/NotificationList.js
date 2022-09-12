import React, { useEffect, useState } from 'react';
import { Button, ButtonGroup, Container, Table } from 'reactstrap';
import AppNavbar from './AppNavbar';
import { Link } from 'react-router-dom';
import axios from "axios";
import { useCookies } from 'react-cookie';

const NotificationList = () => {

	const [loading, setLoading] = useState(false);
	const [notifications, setNotifications] = useState([]);
	const [cookies] = useCookies(['XSRF-TOKEN']);

	const fetchData = () => {
		const notificationsUrl = '/api/notifications';
		const getNotifications = axios.get(notificationsUrl)
		axios.all([getNotifications, getNotifications]).then(
			axios.spread((...allData) => {
				const allNotificationData = allData[0].data

				setNotifications(allNotificationData)
			})
		)
	}

	useEffect(() => { fetchData() }, [])



	const remove = async (id) => {
		await fetch(`/api/notification/${id}`, {
			method: 'DELETE',
			headers: {
				'X-XSRF-TOKEN': cookies['XSRF-TOKEN'],
				'Accept': 'application/json',
				'Content-Type': 'application/json'
			},
			credentials: 'include'
		}).then(() => {
			let updatedNotifications = [...notifications].filter(i => i.id !== id);
			setNotifications(updatedNotifications);
		});
	}

	if (loading) {
		return <p>Loading...</p>;
	}

	const notificationList = notifications.map(notification => {

		return <tr key={notification.id}>
			<td style={{ whiteSpace: 'nowrap' }}>{notification.userId}</td>
			<td>{notification.userEmail}</td>
			<td>{notification.name}</td>
			<td>{notification.content}</td>
			<td>{notification.dateTime}</td>

			<td>
				<ButtonGroup>
					<Button size="sm" color="primary" tag={Link} to={"/notifications/" + notification.id}>Edit</Button>
					<Button size="sm" color="danger" onClick={() => remove(notification.id)}>Delete</Button>
				</ButtonGroup>
			</td>
		</tr >
	});

	return (
		<div>
			<AppNavbar />
			<Container fluid>
				<div className="float-end">
					<Button color="success" tag={Link} to="/notifications/new">Add Notification</Button>
				</div>
				<h3>My Notifications</h3>
				<Table className="mt-4">
					<thead>
						<tr>
							<th>UserId</th>
							<th>Email</th>
							<th>Subject</th>
							<th>Content</th>
							<th>DateTime</th>

							<th width="10%">Actions</th>
						</tr>
					</thead>
					<tbody>
						{notificationList}
					</tbody>
				</Table>
			</Container>
		</div>
	);
};

export default NotificationList;