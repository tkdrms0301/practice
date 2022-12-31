#include <iostream>
using namespace std;
double average(double n1, double n2);
double average(double n1, double n2, double n3);
int main()
{
	double result1, result2;
	double first, second, third;
	cin >> first
		>> second
		>> third;
	result1 = average(first, second);
	result2 = average(first, second, third);
	cout << result1 << endl;
	cout << result2 << endl;
	return 0;
}

double average(double n1, double n2)
{
	return ((n1 + n2) / 2.0);
}

double average(double n1, double n2, double n3)
{
	return ((n1 + n2 + n3) / 3.0);
}