#include <iostream>
using namespace std;

int main()
{
	int inputNum, i;
	cout << "prime number";
	cin >> inputNum;

	for (i = 2; i < inputNum / 2; i++)
		if (inputNum % i == 0)
			break;

	if (i < inputNum / 2)
		cout << inputNum << "��" << i << "�� ���������Ƿ� �Ҽ��� �ƴϴ�.";
	else
	{
		cout << inputNum << "�� �Ҽ��Դϴ�." << endl;
	}
	return 0;
}