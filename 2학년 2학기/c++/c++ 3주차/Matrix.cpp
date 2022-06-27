#include "Matrix.h"
#include <cstdlib>
#include <iostream>
using namespace std;

Matrix::Matrix() {
	srand(rand());
	for (int i = 0; i < ARRAY_LENGTH; i++) {
		for (int j = 0; j < ARRAY_LENGTH; j++) {
			arr[i][j] = rand() % 20 - 10;
		}
	}
}
void Matrix::print(){
	for (int i = 0; i < ARRAY_LENGTH; i++) {
		cout << "| ";
		for (int j = 0; j < ARRAY_LENGTH; j++) {
			cout << arr[i][j] << " ";
		}
		cout << "|" << endl;
	}
}
Matrix Matrix::add(Matrix matrix) {
	Matrix matrixResult;
	for (int i = 0; i < ARRAY_LENGTH; i++) {
		for (int j = 0; j < ARRAY_LENGTH; j++) {
			matrixResult.arr[i][j] = arr[i][j] + matrix.arr[i][j];
		}
	}
	return matrixResult;
}
Matrix Matrix::multi(Matrix matrix) {
	Matrix matrixResult;
	for (int i = 0; i < ARRAY_LENGTH; i++) {
		for (int j = 0; j < ARRAY_LENGTH; j++) {
			int sum = 0;
			for (int k = 0; k < ARRAY_LENGTH; k++) {
				sum = sum + arr[i][k] * matrix.arr[k][j];
			}
			matrixResult.arr[i][j] = sum;
		}
	}
	return matrixResult;
}
