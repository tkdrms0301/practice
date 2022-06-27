#include <iostream>
using namespace std;
int sum(int num1, int num2);
int main()
{
	int stdScore1, stdScore2;
	cout << "학생 두명의 점수를 입력하세요 : ";
	cin >> stdScore1 >> stdScore2;
	int resultSum = sum(stdScore1, stdScore2);
	cout << stdScore1 << " + " << stdScore2 ;
	cout << " = " << resultSum << endl;
	return 0;
}
int sum(int num1, int num2)
{
	// 만일 num1 = 100, num2 = 77 을 한다면?
	return num1 + num2;
}