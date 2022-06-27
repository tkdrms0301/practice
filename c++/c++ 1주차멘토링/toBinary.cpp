#include <iostream>
#include <string>
using namespace std;
int main()
{
	int num;
	string result = "";
	cin >> num;
	while (num != 0) {
		result = to_string(num % 2) + result;
		num = num / 2;
	}
	cout << result;
	return 0;
}