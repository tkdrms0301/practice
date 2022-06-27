#pragma once
class Fraction{
private:
	int top;
	int bottom;
public:
	Fraction();
	Fraction(int top, int bottom);
	void print();
	Fraction add(Fraction fraction);
	Fraction subtract(Fraction fraction);
	Fraction multiply(Fraction fraction);
	Fraction divide(Fraction fraction);
	void isReduce();
};