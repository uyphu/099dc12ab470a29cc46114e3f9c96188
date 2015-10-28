// This script is released to the public domain and may be used, modified and
// distributed without restrictions. Attribution not necessary but appreciated.
// Source: http://weeknumber.net/how-to/javascript 
function AppUtils() {}
// Returns the ISO week of the date.
AppUtils.prototype.getWeek = function() {
	//var date = new Date(this.getTime());
	var date = new Date();
	date.setHours(0, 0, 0, 0);
	// Thursday in current week decides the year.
	date.setDate(date.getDate() + 3 - (date.getDay() + 6) % 7);
	// January 4 is always in week 1.
	var week1 = new Date(date.getFullYear(), 0, 4);
	// Adjust to Thursday in week 1 and count number of weeks from date to week1.
	return 1 + Math
			.round(((date.getTime() - week1.getTime()) / 86400000 - 3 + (week1
					.getDay() + 6) % 7) / 7);
}

// Returns the four-digit year corresponding to the ISO week of the date.
AppUtils.prototype.getWeekYear = function() {
	var date = new Date(this.getTime());
	date.setDate(date.getDate() + 3 - (date.getDay() + 6) % 7);
	return date.getFullYear();
}

AppUtils.prototype.convert = function(item) {
	if (item.status == 0) {
		item.statusString = AppConstant.IN_WORK;
	} else if (item.status == 1) {
		item.statusString = AppConstant.PENDING;
	} else if (item.status == 2) {
		item.statusString = AppConstant.APPROVED;
	} else if (item.status == 3) {
		item.statusString = AppConstant.CANCELLED;
	}
	return item;
}