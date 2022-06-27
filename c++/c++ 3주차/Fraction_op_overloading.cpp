#include <cstdlib>
#include "Fraction.h"

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
Fraction Fraction::operator +(const Fraction fraction) {
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
Fraction Fraction::operator -(const Fraction fraction) {
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
Fraction Fraction::operator *(const Fraction fraction) {
	Fraction fractionResult;
	fractionResult.top = top * fraction.top;
	fractionResult.bottom = bottom * fraction.bottom;
	fractionResult.isReduce();
	return fractionResult;
}
Fraction Fraction::operator /(const Fraction fraction) {
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
ostream& operator <<(ostream& outputStream, const Fraction& fraction) {
	outputStream << fraction.top << "/" << fraction.bottom << endl;
	return outputStream;
}
istream& operator >>(istream& inputStream, Fraction& fraction) {
	do {
		inputStream >> fraction.top;
		inputStream >> fraction.bottom;
		if (fraction.bottom == 0)
			cout << "분자와 분모를 정수로 입력하세요 (분모는 0이 될 수 없다.) :" << endl;
	} while (fraction.bottom == 0);
	
	return inputStream;
}
const bool Fraction::operator >(const Fraction fraction){
	return (top * fraction.bottom > fraction.top * bottom);
}
const bool Fraction::operator <(const Fraction fraction){
	return (top * fraction.bottom < fraction.top * bottom);
}
const bool Fraction::operator ==(const Fraction fraction) {
	return (top * fraction.bottom == fraction.top * bottom);
}