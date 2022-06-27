#include <iostream>
using namespace std;
const int ARRAY_LENGTH = 3;
void matrixAdd(int arr1[][3], int arr2[][3], int addArr[][3]);
void matrixMulti(int arr1[][3], int arr2[][3], int multiArr[][3]);
void matrixPrint(int arr[][3]);
int main()
{
	int arr1[ARRAY_LENGTH][ARRAY_LENGTH] = { {1, 2, 3}, {4, 5, 6}, {7, 8, 9} };
	int arr2[ARRAY_LENGTH][ARRAY_LENGTH] = { {1, -1, 0}, {0, -1, 1}, {-1, 1, 0} };
	int addArr[ARRAY_LENGTH][ARRAY_LENGTH] = {};
	int multiArr[ARRAY_LENGTH][ARRAY_LENGTH] = {};

	matrixAdd(arr1, arr2, addArr);
	matrixMulti(arr1, arr2, multiArr);

	cout << "두 행렬의 합은" << endl;
	matrixPrint(addArr);
	cout << "두 행렬의 곱은" << endl;
	matrixPrint(multiArr);
	return 0;
}
void matrixAdd(int arr1[][3], int arr2[][3], int addArr[][3]) {
	for (int i = 0; i < ARRAY_LENGTH; i++) {
		for (int j = 0; j < ARRAY_LENGTH; j++) {
			addArr[i][j] = arr1[i][j] + arr2[i][j];
		}
	}
}
void matrixMulti(int arr1[][3], int arr2[][3], int multiArr[][3]) {
	for (int i = 0; i < ARRAY_LENGTH; i++) {
		for (int j = 0; j < ARRAY_LENGTH; j++) {
			int sum = 0;
			for (int k = 0; k < ARRAY_LENGTH; k++) {
				sum = sum + arr1[i][k] * arr2[k][j];
			}
			multiArr[i][j] = sum;
		}
		
	}
}
void matrixPrint(int arr[][3]) {
	for (int i = 0; i < ARRAY_LENGTH; i++) {
		cout << "| ";
		for (int j = 0; j < ARRAY_LENGTH; j++) {
			if(arr[i][j] >= 0)
				cout << " " << arr[i][j] << " ";
			else
				cout << arr[i][j] << " ";
		}
		cout << "|" << endl;
	}
}