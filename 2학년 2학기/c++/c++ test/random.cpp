#include <cstdlib>
#include <time.h>
#include <string>
#include <iostream>
using namespace std;
int main()
{
	string inStr;
	srand((unsigned int)time(NULL));
	do
	{
		double randF = (RAND_MAX - rand()) / static_cast<double>(RAND_MAX);
		int randDice = rand() % 6 + 1; // 1���� 6������ int�� ����:
		int randValue = rand() % 10 + 10; // 10���� 20������ int�� ���� :
		cout << "������ �ֻ��� : " << randDice << endl;
		cout << "������ ���� ���� : " << randValue << endl;
		cout << "������ [0, 1] �Ǽ� �� : " << randF << endl;
		cout << "����ұ��? (Y/N) ";
		cin >> inStr;
	} while (inStr == "Y" || inStr == "y");
	return 0;
}