#pragma once
#include <iostream>
using namespace std;

class Date{
private:
	int year;
	int month;
	int day;
	int d_Day;
	bool d_Day_Option;

	int GetLastDayOfMonth();
	bool setYear(int inputYear);
	bool setMonth(int inputMonth);
	bool setDay(int inputDay);
	Date D_DayDate(Date date);
public:
	Date();
	Date(int inputYear, int inputMonth, int inputDay);
	void printDate(Date date);
	void setD_Day(int inputD_Day);
	void setDate(int inputYear, int inputMonth, int inputDay);
	bool isValid();
	friend ostream& operator <<(ostream& outputStream, const Date date);
	void operator++();
	void operator--();
};