#pragma once
#include "Depositor.h"
#include <string>

class Normal : public Depositor
{
public:
	Normal();
	Normal(const string& name, const string& phoneNumber, const int deposit);
	virtual ~Normal();
	Normal& operator=(const Normal& normal);
	virtual string getKey() const;
	virtual void setKey(const string& key);
	virtual void showInfo() const;
	virtual string tostring() const;
private:
	string key;
	const string getNextDepositKey() const;
	static int depositNumber;
};


