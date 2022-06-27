#include <iostream>
using namespace std;
int main()
{
	int num = 0;
	do {
		cout << "크기(3보다큰 홀수 / 0이면 종료) : ";
		cin >> num;
		if (num == 0) {
			cout << "종료";
			break;
		}
		else if (num % 2 == 1 && num > 3) {
			for (int i = 0; i < num; i++) {
				if (i < num / 2) {
					for (int j = num / 2 - i; j > 0; j--)
						cout << " ";
					for (int j = 0; j < i * 2 + 1; j++)
						cout << "*";
					cout << endl;
				}
				else {
					for (int j = 0; j < i - num / 2; j++)
						cout << " ";
					for (int j = 0; j < num - (i - num / 2) * 2; j++)
						cout << "*";
					cout << endl;
				}
			}
		}
		else {
			cout << "다시입력";
			num = 5;
		}
	} while (num % 2 == 1 && num > 3);
	return 0;
}
