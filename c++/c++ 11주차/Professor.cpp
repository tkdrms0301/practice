#include "Professor.h"

Professor::Professor() : Depositor(), department(""), extensionNumber(0)
{
}

Professor::Professor(const string& key, const string& name, const string& phoneNumber, const int deposit, const string& department, const int extensionNumber) : Depositor(name, phoneNumber, deposit), key(key), department(department), extensionNumber(extensionNumber)
{
}


Professor::~Professor()
{
}

Professor& Professor::operator=(const Professor& professor)
{
	Depositor::operator=(professor);
	this->key = professor.getKey();
	this->department = professor.getDepartment();
	this->extensionNumber = professor.getExtensionNumber();
	return *this;
}

string Professor::getKey() const 
{
	return this->key;
}

string Professor::getDepartment() const 
{
	return department;
}

int Professor::getExtensionNumber() const 
{
	return extensionNumber;
}

void Professor::setKey(const string& key) 
{
	this->key = key;
}

void Professor::setDepartment(const string& department)
{
	this->department = department;
}

void Professor::setExtensionNumber(const int& extensionNumber)
{
	this->extensionNumber = extensionNumber;
}

void Professor::showInfo() const 
{
	cout << "[ 교직원 ] " 
		<< getName()
		<< "(사번 : " << getKey() << ", "
		<< "(부서 : " << getDepartment() 
		<< "(x" << getExtensionNumber() << ")) "
		<< getPhoneNumber() << " "
		<< getDeposit() << " "
		<< endl;
}

string Professor::tostring() const
{
	string line;
	line = "교직원 " + getKey() + " " + getName() + " " + getDepartment() + " " + getPhoneNumber() + " " + to_string(getExtensionNumber()) + " " + to_string(getDeposit()) + "\n";
	return line;
}