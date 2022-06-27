#include <iostream>
#include <string>
using namespace std;
const int UPPER_LOWER_DIFFERENCE = 'A' - 'a';
int main() {
	string inputString;
	cout << "문장입력 : ";
	getline(cin, inputString);
	for (int i = 0; i < inputString.length() && inputString[i] != '\0'; i++) {
		if (inputString[i] >= 'A' && inputString[i] <= 'Z') {
			inputString[i] = inputString[i] - UPPER_LOWER_DIFFERENCE;
		}
		if (i == 0) {
			inputString[i] = inputString[i] + UPPER_LOWER_DIFFERENCE;
		}
	}
	cout << "문장출력 : " << inputString;
	return 0;
}