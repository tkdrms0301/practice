#include <iostream>
using namespace std;
int main()
{
	int num = 0;
	int count = 0;
	cout << "정수값을 입력하세요 (0이면 종료)";
	cin >> num;
	while (num < -10 || num > 10) {
		if (num == 0) {
			cout << "종료" << endl;
			break;
		}
		num = num / 10;
	}
	cout << "가장큰자리수 : " << num << endl;
	return 0;
}
