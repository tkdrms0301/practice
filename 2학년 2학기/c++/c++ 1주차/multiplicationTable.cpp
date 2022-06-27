#include <iostream>
using namespace std;
int main()
{
	int dan, mul, dan_in_row = 0;
	cout << "몇단까지 출력할까요? ";
	cin >> dan;
	cout << "몇까지 곱할까요? : ";
	cin >> mul;
	cout << "한줄에 몇단씩 출력할까요? : ";
	cin >> dan_in_row;
	int row = 0;
	for (int i = 0; i <= dan / dan_in_row; i++) {
		for (int j = 1; j <= mul; j++) {
			for (int k = 1; k <= dan_in_row; k++) {
				if ((k + dan_in_row * i) > dan)
					break;
				cout << k + dan_in_row * i << " x " << j << " = " << (k + dan_in_row * i) * j << "\t";
			}
			cout << endl;
		}
		cout << endl;
	}
	return 0;
}
