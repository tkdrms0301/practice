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
		cout << "��ȯ�� ���ϴ� ������ �Է��ϼ���(0�� ����) : ";
		cin >> inputPyung;
		if (!inputPyung) break;
		cout << setprecision(2);
		cout << setw(4) << inputPyung << "�� = ";
		cout << inputPyung * SQUARE_METER_PER_PYONG << "�������� = ";
		cout << sqrt(inputPyung * SQUARE_METER_PER_PYONG) << "������ ����" << endl;
	}
	return 0;
}