#include <iostream>
using namespace std;

int main()
{
	int winCount = 0;
	int loseCount = 0;
	int myScore, yourScore;
	cout << "내 점수 :";
	cin >> myScore;
	cout << "네 점수 :";
	cin >> yourScore;
	if (myScore > yourScore)
	{
		cout << "내 점수가 더 높음!\n";
			winCount++;
	}
	else if (myScore < yourScore)
	{
		cout << "졌다...\n";
			loseCount++;
	}
	else
		cout << "비겼군.\n";
	return 0;
}