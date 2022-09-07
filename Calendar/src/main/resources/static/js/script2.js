const date = new Date();

const month = date.getMonth();

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

document.querySelector(".daysNotification h1").innerHTML = months[month];

document.querySelector(".daysNotification p").innerHTML = new Date().toDateString();