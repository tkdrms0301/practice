#pragma once
#include "Depositor.h"
#include <string>

class Professor : public Depositor
{
public:
	Professor();
	Professor(const string& key, const string& name, const string& phoneNumber, const int deposit, const string& department, const int extensionNumber);
	virtual ~Professor();
	Professor& operator=(const Professor& professor);
	virtual string getKey() const;
	string getDepartment() const;
	int getExtensionNumber() const;
	virtual void setKey(const string& key);
	void setDepartment(const string& inputDepartment);
	void setExtensionNumber(const int& inputExtensionNumber);
	virtual void showInfo() const;
	virtual string tostring() const;
private:
	string key;
	string department;
	int extensionNumber;
};