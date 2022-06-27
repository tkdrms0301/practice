#include "Depositor.h"

Depositor::Depositor()
{
	name = "";
	phoneNumber = "";
	deposit = 0;
}

Depositor::Depositor(const string& name, const string& phoneNumber, const int deposit)
: name(name), phoneNumber(phoneNumber), deposit(deposit)
{

}

Depositor::~Depositor()
{
}

Depositor Depositor::operator=(const Depositor& depositor)
{
	this->name = depositor.getName();
	this->phoneNumber = depositor.getPhoneNumber();
	this->deposit = depositor.getDeposit();
	return *this;
}

string Depositor::getKey() const {
	return "";
}

string Depositor::getName() const {
	return name;
}

string Depositor::getPhoneNumber() const {
	return phoneNumber;
}

int Depositor::getDeposit() const {
	return deposit;
}

void Depositor::setKey(const string& inputKey) {
}

void Depositor::setName(const string& inputName) {
	name = inputName;
}
void Depositor::setPhoneNumber(const string& inputPhoneNumber) {
	phoneNumber = inputPhoneNumber;
}
void Depositor::setDeposit(const int inputDeposit) {
	deposit = inputDeposit;
}

void Depositor::showInfo() const
{
}

string Depositor::tostring() const
{
	string line;
	line = "±âÅ¹ÀÚ " + getName() + " " + getName() + " " + getPhoneNumber() + "\n";
	return string();
}
