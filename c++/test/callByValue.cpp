#include <iostream>
using namespace std;
int sum(int num1, int num2);
int main()
{
	int stdScore1, stdScore2;
	cout << "�л� �θ��� ������ �Է��ϼ��� : ";
	cin >> stdScore1 >> stdScore2;
	int resultSum = sum(stdScore1, stdScore2);
	cout << stdScore1 << " + " << stdScore2 ;
	cout << " = " << resultSum << endl;
	return 0;
}
int sum(int num1, int num2)
{
	// ���� num1 = 100, num2 = 77 �� �Ѵٸ�?
	return num1 + num2;
}