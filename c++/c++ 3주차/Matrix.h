#pragma once
static const int ARRAY_LENGTH = 3;
class Matrix {
private:
	int arr[ARRAY_LENGTH][ARRAY_LENGTH];
public:
	Matrix();
	void print();
	Matrix add(Matrix matrix);
	Matrix multi(Matrix matrix);
};