#include <iostream>
#include "Fraction.h"
using namespace std;
Fraction::Fraction() {
	top = 0;
	bottom = 0;
}
Fraction::Fraction(int inputTop, int inputBottom) {
	top = inputTop;
	bottom = inputBottom;
}
void Fraction::print() {
	cout << top << "/" << bottom << endl;
}
Fraction Fraction::add(Fraction fraction) {
	Fraction fractionResult;
	if (bottom == fraction.bottom) {
		fractionResult.bottom = bottom;
		fractionResult.top = top + fraction.top;
	}
	else {
		fractionResult.bottom = bottom * fraction.bottom;
		fractionResult.top = top * fraction.bottom + fraction.top * bottom;
	}
	fractionResult.isReduce();
	return fractionResult;
}
Fraction Fraction::subtract(Fraction fraction) {
	Fraction fractionResult;
	if (bottom == fraction.bottom) {
		fractionResult.bottom = bottom;
		fractionResult.top = top - fraction.top;
	}
	else {
		fractionResult.bottom = bottom * fraction.bottom;
		fractionResult.top = top * fraction.bottom - fraction.top * bottom;
	}
	fractionResult.isReduce();
	return fractionResult;
}
Fraction Fraction::multiply(Fraction fraction) {
	Fraction fractionResult;
	fractionResult.top = top * fraction.top;
	fractionResult.bottom = bottom * fraction.bottom;
	fractionResult.isReduce();
	return fractionResult;
}
Fraction Fraction::divide(Fraction fraction) {
	Fraction fractionResult;
	fractionResult.top = top * fraction.bottom;
	fractionResult.bottom = bottom * fraction.top;
	fractionResult.isReduce();
	return fractionResult;
}
void Fraction::isReduce() {
	bool isTopNegative = false;
	bool isBottomNegative = false;
	bool isAllNegative = false;
	if (top < 0 && bottom < 0) {
		top = -top;
		bottom = -bottom;
		isAllNegative = true;
	}
	if (top < 0) {
		top = -top;
		isTopNegative = true;
	}
	else if (bottom < 0) {
		bottom = -bottom;
		isBottomNegative = true;
	}
	else if (top == 0)
		return;
	int divisior;
	if (top > bottom) {
		divisior = bottom;
		for (int i = divisior; i > 0; i--) {
			if (top % divisior == 0 && bottom % divisior == 0)
				break;
			divisior--;
		}
		top = top / divisior;
		bottom = bottom / divisior;
	}
	else if (top == bottom) {
		divisior = top;
		top = top / divisior;
		bottom = bottom / divisior;
	}
	else {
		divisior = top;
		for (int i = divisior; i > 0; i--) {
			if (top % divisior == 0 && bottom % divisior == 0)
				break;
			divisior--;
		}
		top = top / divisior;
		bottom = bottom / divisior;
	}
	if (isAllNegative) {

	}
	else if (isTopNegative) {
		top = -top;
	}
	else if (isBottomNegative) {
		top = -top;
	}
}