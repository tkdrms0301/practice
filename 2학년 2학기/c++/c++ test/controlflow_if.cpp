#include <iostream>
using namespace std;

int main()
{
	int numberOfLangugages;

	cout << "Hello reader.\n"
		<< "Welcome to C++/\n";
	cout << "How many programming languages have you used? ";
	cin >> numberOfLangugages;

	if (numberOfLangugages < 1)
		cout << "Read the preface.\n"
			 << "a more elementary book by the same author.\n";
	else
	{
		cout << "Enjoy the book.\n";
	}
	return 0;
}