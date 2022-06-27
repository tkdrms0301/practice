#pragma once
#include <iostream>
using namespace std;
const int FIRST_ARRAY_LENGTH = 4;
class Set {
	friend istream& operator>>(istream& inputStream, Set &set);
	friend ostream& operator<<(ostream& outputStream, const Set &set);
private:
	int *arr;
	int arrLength;
	int arrUsedLength;
	void resize(int length);
	bool isOverlap(int num);
	
public:
	Set();
	Set(int arrLength);
	Set operator|(const Set set);
	Set operator&(const Set set);
};