#pragma once
#include <iostream>
#include <fstream>
#include <string>
#include "Depositor.h"
#include "Professor.h"
#include "Student.h"
#include "Normal.h"
#include "MyList.h"
using namespace std;

class Handler {
public:
	Handler();
	~Handler();
	void run(const string file);
	void input(const string file);
	void output(const string file);
	void depositProgram(const string file);
	void displayPerson();
	void insertPerson();
	void updatePerson(const string keyOrPhoneNumber);
	void deletePerson(const string keyOrPhoneNumber);
private:
	const static int PROFESSOR;
	const static int STUDENT;
	const static int NORMAL;

	const static char SELECT_ALL;
	const static char INSERT;
	const static char UPDATE;
	const static char DELETE;
	const static char EXIT;

	const static int STRING_SIZE;

	const static int PHONENUMBER_HYPHEN_FIRST;
	const static int PHONENUMBER_HYPHEN_SECOND;

	MyList<Depositor> myList;
	string* split(const string line);
};