#include <iostream>
using namespace std;
int perfectNum(int num);
int main()
{
	int number;
	cout << "입력 : ";
	cin >> number;
	for (int i = 1; i <= number; i++) {
		perfectNum(i);
	}
	return 0;
}
int perfectNum(int num) {
	int sum = 0, count = 0;
	for (int i = 1; i < num; i++) {
		if (num % i == 0) {
			sum = sum + i;
			count++;
		}
	}
	if (sum == num) {
		cout << num << "(";
		for (int i = 1; i < num; i++) {
			if (num % i == 0) {
				count--;
				if (count != 0)
					cout << i << "+";
				else
					cout << i;
			}
		}
		cout << ")" << endl;
	}
	return 0;
}
