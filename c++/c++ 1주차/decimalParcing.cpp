#include <iostream>
using namespace std;
int main()
{
	double num = 0;
	while (!num) {
		cout << "부동소수점 값을 입력하세요(0에서 종료) : ";
		cin >> num;
		if (num == 0)
			break;
		int num_N = num;
		cout << "정수부 : " << num_N << " 소숫점부 : " << num - num_N << endl;
		num = 0;
	}
	cout << "종료";
	return 0;
}
