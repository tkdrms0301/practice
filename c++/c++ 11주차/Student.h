#pragma once
#include "Depositor.h"
#include <string>

class Student : public Depositor
{
public:
	Student();
	Student(const string& key, const string& name, const string& phoneNumber, const int deposit, const string& major);
	virtual ~Student();
	Student& operator=(const Student& student);
	virtual string getKey() const;
	string getMajor() const;
	virtual void setKey(const string& key);
	void setMajor(const string& major);
	virtual void showInfo() const;
	virtual string tostring() const;
private:
	string key;
	string major;
};