import "../css/CalendarForm.css";
import Calendar from "react-calendar";
import { useState, createContext } from "react";
import { useNavigate } from "react-router-dom";
import EventForm from "./EventForm";

function Calendar() {
	const [date, setDate] = useState(new Date());

	const navigate = useNavigate();
	const toEventForm = () => {
		navigate("/eventform");
	};

	return (
		<div className="calendar-form">
			<h1 className="text-center">React Calendar</h1>
			<div className="container">
				<Calendar onChange={setDate} value={date} />
				{/* <EventForm onChange={setDate} value={date} /> */}
			</div>
			{date.length > 0 ? (
				<p className="text-center">
					<span className="bold">Start:</span> {date[0].toDateString()}
					&nbsp;|&nbsp;
					<span className="bold">End:</span>
					{date[1].toDateString()}
				</p>
			) : (
				<p className="text-center">
					<span className="bold">Selected Date:</span> {date.toDateString()}
				</p>
			)}
			<p className="text-center">
				<button
					onClick={() => {
						toEventForm();
					}}
				>
					Add an event!
				</button>
			</p>
		</div>
	);
}

export default Calendar;
