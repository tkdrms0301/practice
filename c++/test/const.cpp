#include <iostream>
using namespace std;

int main()
{
	const double RATE = 6.9;
	double deposit;

	cout << "enter your deposit";
	cin >> deposit;
	double newBalance;
	newBalance = deposit + deposit * (RATE / 100);
	cout << "your deposit is" << newBalance << ".\n";

	return 0;
}