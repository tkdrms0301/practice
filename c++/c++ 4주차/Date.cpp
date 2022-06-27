#include "Date.h"
#include <time.h>
const int MONTH_LENGTH = 12;
const int days[MONTH_LENGTH] = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
const int FIRST = 1;
Date::Date() {
	time_t timer = time(NULL);
	struct tm date;
	localtime_s(&date, &timer);
	year = date.tm_year + 1900;
	month = date.tm_mon + 1;
	day = date.tm_mday;
	d_Day = 0;
	d_Day_Option = false;
}
Date::Date(int inputYear, int inputMonth, int inputDay) {
	year = inputYear;
	month = inputMonth;
	day = inputDay;
	d_Day = 0;
	d_Day_Option = false;
}
int Date::GetLastDayOfMonth() {
	int isLeapDay = 0;
	if (year % 4 == 0 && year % 100 != 0 || year % 400 == 0) {
		isLeapDay = 1;
	}
	return days[month - 1] + isLeapDay;
}
bool Date::setYear(int inputYear) {
	if (inputYear < 0)
		return false;
	else {
		year = inputYear;
		return true;
	}
}
bool Date::setMonth(int inputMonth) {
	if (inputMonth < FIRST || inputMonth > MONTH_LENGTH)
		return false;
	else {
		month = inputMonth;
		return true;
	}
}
bool Date::setDay(int inputDay) {
	if (day < 1 || day > GetLastDayOfMonth()) {
		return false;
	}
	else {
		day = inputDay;
		return true;
	}
}
Date Date::D_DayDate(Date date) {
	Date D_DayDate(year, month, day);
	int tmp = day + d_Day;
	if (d_Day < 0) {
		while (!(FIRST <= tmp && tmp <= D_DayDate.GetLastDayOfMonth())) {
			if (D_DayDate.month - FIRST < 1) {
				D_DayDate.setMonth(MONTH_LENGTH);
				D_DayDate.setYear(D_DayDate.year - FIRST);
			}
			else {
				D_DayDate.setMonth(D_DayDate.month - FIRST);
			}
			tmp = tmp + D_DayDate.GetLastDayOfMonth();
		}
		D_DayDate.setDay(tmp);
	}
	else if (d_Day > 0) {
		tmp = tmp - 1; //현재날짜 포함
		while (tmp > D_DayDate.GetLastDayOfMonth()) {
			tmp = tmp - D_DayDate.GetLastDayOfMonth();
			if (D_DayDate.month + FIRST > MONTH_LENGTH) {
				D_DayDate.setMonth(FIRST);
				D_DayDate.setYear(D_DayDate.year + FIRST);
			}
			else {
				D_DayDate.setMonth(D_DayDate.month + FIRST);
			}
		}
		D_DayDate.setDay(tmp);
	}
	return D_DayDate;
}
void Date::printDate(Date date) {
	cout << "[현재 날짜] " << date;
	if (!d_Day_Option) {
		cout << "[D-day 없음]" << endl;
	}
	else {
		cout << "[D";
		if (d_Day >= 0) {
			cout << "+";
		}
		cout << d_Day << "] ";
		//d_Day 계산된 날짜 추가
		cout << date.D_DayDate(date) << endl;
	}
	cout << "날짜 이동은 년월일 또는 +(다음날) 또는 -(전날), D-day 계산은 +/-날짜, 종료는 Q : ";
}
void Date::setD_Day(int inputD_Day) {
	d_Day = inputD_Day;
	d_Day_Option = true;
}
void Date::setDate(int inputYear, int inputMonth, int inputDay) {
	year = inputYear;
	month = inputMonth;
	day = inputDay;
}
bool Date::isValid() {
	bool isValid = false;
	if (setYear(year) && setMonth(month) && setDay(day))
		isValid = true;		
	return isValid;
}
ostream& operator<<(ostream& outputStream, const Date date) {
	outputStream << date.year << "년 " << date.month << "월 " << date.day << "일 ";
	return outputStream;
}
void Date::operator++() {
	if (day != GetLastDayOfMonth()) {
		setDay(day + FIRST);
	}
	else {
		setDay(FIRST);
		if (month != MONTH_LENGTH) {
			setMonth(month + FIRST);
		}
		else {
			setMonth(FIRST);
			setYear(year + FIRST);
		}
	}
}
void Date::operator--() {
	if (day != FIRST) {
		setDay(day - FIRST);
	}
	else {
		if (month != FIRST) {
			setMonth(month - FIRST);
			setDay(GetLastDayOfMonth());
		}
		else {
			setMonth(MONTH_LENGTH);
			setDay(GetLastDayOfMonth());
			setYear(year - FIRST);
		}
	}
}
