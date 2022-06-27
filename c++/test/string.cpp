#include <iostream>
using namespace std;

int main()
{
	string dogName;
	int actualAge;
	int humanAge;
	cout << "how old your dog? ";
	cin >> actualAge;
	humanAge = actualAge * 7;
	cout << "dog name? " << endl;
	cin >> dogName;

	cout << dogName << "'s age is approximately " << humanAge << endl;
	return 0;
}