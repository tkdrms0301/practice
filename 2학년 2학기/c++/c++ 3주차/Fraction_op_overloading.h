#pragma once
#include <iostream>
using namespace std;

class Fraction {
private:
	int top;
	int bottom;
public:
	Fraction();
	Fraction(int top, int bottom);
	void print();
	Fraction operator +(const Fraction fraction);
	Fraction operator -(const Fraction fraction);
	Fraction operator *(const Fraction fraction);
	Fraction operator /(const Fraction fraction);
	void isReduce();
	friend ostream& operator <<(ostream& outputStream, const Fraction& fraction);
	friend istream& operator >>(istream& inputStream, Fraction& fraction);
	const bool operator >(const Fraction fraction);
	const bool operator <(const Fraction fraction);
	const bool operator ==(const Fraction fraction);
};