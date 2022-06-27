#pragma once
#include <iostream>
using namespace std;

class Depositor {
public:
	Depositor();
	Depositor(const string& name, const string& phoneNumber, const int deposit);
	virtual ~Depositor();
	Depositor operator =(const Depositor& depositor);
	virtual string getKey() const;
	string getName() const;
	string getPhoneNumber() const;
	int getDeposit() const;
	virtual void setKey(const string& inputKey);
	void setName(const string& inputName);
	void setPhoneNumber(const string& inputPhoneNumber);
	void setDeposit(const int inputDeposit);
	virtual void showInfo() const;
	virtual string tostring() const;
private:
	string name;
	string phoneNumber;
	int deposit;
};