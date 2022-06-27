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
		cout << inputNum << "은" << i << "로 나누어지므로 소수가 아니다.";
	else
	{
		cout << inputNum << "은 소수입니다." << endl;
	}
	return 0;
}