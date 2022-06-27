#include <iostream>
#include <iomanip>
#include <cmath>
using namespace std;
int main()
{
	const double SQUARE_METER_PER_PYONG = 3.3;
	double inputPyung;
	while (1)
	{
		cout << "변환을 원하는 평형을 입력하세요(0은 종료) : ";
		cin >> inputPyung;
		if (!inputPyung) break;
		cout << setprecision(2);
		cout << setw(4) << inputPyung << "평 = ";
		cout << inputPyung * SQUARE_METER_PER_PYONG << "제곱미터 = ";
		cout << sqrt(inputPyung * SQUARE_METER_PER_PYONG) << "미터의 제곱" << endl;
	}
	return 0;
}