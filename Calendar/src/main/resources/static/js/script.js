const date = new Date();

const renderCalendar = () => {

	const month = date.getMonth();

	const monthDays = document.querySelector(".days");

	const lastDay = new Date(date.getFullYear(), date.getMonth() + 1, 0).getDate();

	const prevLastDay = new Date(date.getFullYear(), date.getMonth(), 0).getDate();

	const firstDayIndex = date.getDay();

	const lastDayIndex = new Date(
		date.getFullYear(),
		date.getMonth() + 1,
		0
	).getDay();

	const nextDays = 7 - lastDayIndex - 1;

	console.log(lastDayIndex);

	console.log(prevLastDay);

	const months = [
		"January",
		"February",
		"March",
		"April",
		"May",
		"June",
		"July",
		"August",
		"September",
		"October",
		"November",
		"December",
	];

	let days = "";

	document.querySelector(".date h1").innerHTML = months[month];

	document.querySelector(".date p").innerHTML = date.toDateString();

	//praeito menesio dienos
	for (let x = firstDayIndex; x > 0; x--) {
		days += `<button class="prev-date">${prevLastDay - x + 1}</button>`;
	}

	// dienu rodymas 
	for (let i = 1; i <= lastDay; i++) {
		if (i === new Date().getDate() && date.getMonth() === new Date().getMonth()) {
			days += `<button onclick="location.href = '/user/signup';" class="today">${i}</button></a>`;
		} else {
			days += `<button onclick="location.href = '/user/signup';">${i}</button>`; // ne ' o ` ISIMINK SITA
		}
	}

	//sekancio menesio dienos
	for (let j = 1; j <= nextDays; j++) {
		days += `<button class="next-date">${j}</button>`;
	}
	monthDays.innerHTML = days;

};

const month = date.getMonth();

const monthDays = document.querySelector(".days");

const lastDay = new Date(date.getFullYear(), date.getMonth() + 1, 0).getDate();

const prevLastDay = new Date(date.getFullYear(), date.getMonth(), 0).getDate();

const firstDayIndex = date.getDay();

const lastDayIndex = new Date(
	date.getFullYear(),
	date.getMonth() + 1,
	0
).getDay();

const nextDays = 7 - lastDayIndex - 1;

console.log(lastDayIndex);

console.log(prevLastDay);

const months = [
	"January",
	"February",
	"March",
	"April",
	"May",
	"June",
	"July",
	"August",
	"September",
	"October",
	"November",
	"December",
];

let days = "";

document.querySelector(".date h1").innerHTML = months[month];

document.querySelector(".date p").innerHTML = new Date().toDateString();

//praeito menesio dienos
for (let x = firstDayIndex; x > 0; x--) {
	days += `<div class="prev-date">${prevLastDay - x + 1}</div>`;
}

// dienu rodymas
for (let i = 1; i <= lastDay; i++) {
	if (i === new Date().getDate() && date.getMonth() === new Date().getMonth()) {
		days += `<div class="today">${i}</div>`;
	} else {
		days += `<div>${i}</div>`; // ne ' o ` ISIMINK SITA
	}
}

//sekancio menesio dienos
for (let j = 1; j <= nextDays; j++) {
	days += `<div class="next-date">${j}</div>`;
}
monthDays.innerHTML = days;

//mygtukas perjungt praeita menesi
document.querySelector(".prev").addEventListener("click", () => {
	date.setMonth(date.getMonth() - 1);
	renderCalendar();
});

//mygtukas perjungt sekanti menesi
document.querySelector(".next").addEventListener("click", () => {
	date.setMonth(date.getMonth() + 1);
	renderCalendar();
});



renderCalendar();

