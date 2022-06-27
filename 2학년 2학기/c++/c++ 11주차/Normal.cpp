#include "Normal.h"

int Normal::depositNumber = 0;

Normal::Normal() : Depositor("", "", 0)
{
	this->key = getNextDepositKey();
}

Normal::Normal(const string& name, const string& phoneNumber, const int deposit) : Depositor(name, phoneNumber, deposit)
{
	this->key = getNextDepositKey();
}

Normal::~Normal() 
{
}

Normal& Normal::operator=(const Normal& normal)
{
	Depositor::operator=(normal);
	this->key = normal.getKey();
	return *this;
}

string Normal::getKey() const
{
	return this->key;
}

void Normal::setKey(const string& key)
{
	this->key = key;
}

void Normal::showInfo() const
{
	cout << "[  일반  ] "
		<< getName()
		<< "(기탁자코드 :" << getKey() << ") "
		<< getPhoneNumber() << " "
		<< getDeposit() << " "
		<< endl;
}

string Normal::tostring() const
{
	string line;
	line = "일반 " + getKey() + " " + getName() + " " + getPhoneNumber() + " " + to_string(getDeposit()) + "\n";
	return line;
}

const string Normal::getNextDepositKey() const
{
	depositNumber++;
	string depositNum = to_string(depositNumber);
	string key;
	key = "V";
	for (int i = 0; i < 5 - depositNum.length(); i++)
	{
		key += "0";
	}
	key += depositNum;
	return key;
}
