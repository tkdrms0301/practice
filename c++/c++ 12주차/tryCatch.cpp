#include <iostream>
using namespace std;

int main()
{
	int num1, num2, result;
	for (int i = 0; i < 3; i++)
	{
		cout << "���� �Է�: ";
		cin >> num1;
		cout << "���� ���� �Է� : ";
			cin >> num2;
		// try-catch ���
		try
		{
			if (num2 == 0)
				throw 0; // int �ڷ����� ���ܸ� �߻�
			result = num1 / num2;
			cout << "��� = " << result << endl; // ����1
			cout << "��� = " << quotient(num1, num2) << endl; // ����2
		}
		catch (int x)
		{
			cout << "0���� ���� �� �����ϴ�." << endl;
		}
	}
	return 0;
}
//�Լ� ����
int quotient(int first, int second)
{
	if(second == 0)
		throw 0;
	return first / second;
}