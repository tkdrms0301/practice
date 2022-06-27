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
		int randDice = rand() % 6 + 1; // 1에서 6사이의 int형 난수:
		int randValue = rand() % 10 + 10; // 10에서 20사이의 int형 난수 :
		cout << "무작위 주사위 : " << randDice << endl;
		cout << "무작위 수학 성적 : " << randValue << endl;
		cout << "무작위 [0, 1] 실수 값 : " << randF << endl;
		cout << "계속할까요? (Y/N) ";
		cin >> inStr;
	} while (inStr == "Y" || inStr == "y");
	return 0;
}