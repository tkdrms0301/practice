#include <iostream>
using namespace std;

int main()
{
	int winCount = 0;
	int loseCount = 0;
	int myScore, yourScore;
	cout << "�� ���� :";
	cin >> myScore;
	cout << "�� ���� :";
	cin >> yourScore;
	if (myScore > yourScore)
	{
		cout << "�� ������ �� ����!\n";
			winCount++;
	}
	else if (myScore < yourScore)
	{
		cout << "����...\n";
			loseCount++;
	}
	else
		cout << "��屺.\n";
	return 0;
}