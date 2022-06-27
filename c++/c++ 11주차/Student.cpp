#include "Student.h"

Student::Student() : Depositor(), major("")
{
}
Student::Student(const string& key, const string& name, const string& phoneNumber, const int deposit, const string& major) : Depositor(name, phoneNumber, deposit), key(key), major(major)
{
}

Student::~Student()
{
}

Student& Student::operator=(const Student& student)
{
	Depositor::operator=(student);
	this->key = student.getKey();
	this->major = student.getMajor();
	return *this;
}

string Student::getKey() const
{
	return key;
}

string Student::getMajor() const 
{
	return major;
}

void Student::setKey(const string& key)
{
	this->key = key;
}

void Student::setMajor(const string& major) 
{
	this->major = major;
}

void Student::showInfo() const
{
	cout << "[  학생  ] "
		<< getName()
		<< "(학번 : " << getKey() << ", "
		<< "학과 : " << getMajor() << ") "
		<< getPhoneNumber() << " "
		<< getDeposit() << " "
		<< endl;
}

string Student::tostring() const
{
	string line;
	line = "학생 " + getKey() + " " + getName() + " " + getMajor() + " " + getPhoneNumber() + " " + to_string(getDeposit()) + "\n";
	return line;
}
