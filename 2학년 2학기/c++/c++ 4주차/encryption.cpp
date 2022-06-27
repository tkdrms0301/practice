#include <iostream>
#include <string>
using namespace std;
const int ALPHABET_NUM = 26;
const int UPPER_LOWER_DIFFERENCE = 32;
const int ENCRYPTION_NUM = 3;
int main() {
	string inputString;
	cout << "문장입력 : ";
	getline(cin, inputString);
	for (int i = 0; i < inputString.length() && inputString[i] != '\0'; i++) {
		if (inputString[i] >= 'a' && inputString[i] <= 'z') {
			if ((inputString[i] + ENCRYPTION_NUM) > 'z')
				inputString[i] = inputString[i] + ENCRYPTION_NUM - ALPHABET_NUM;
			else
				inputString[i] = inputString[i] + ENCRYPTION_NUM;
		}
		else if (inputString[i] >= 'A' && inputString[i] <= 'Z') {
			if ((inputString[i] + ENCRYPTION_NUM) > 'Z')
				inputString[i] = inputString[i] + ENCRYPTION_NUM - ALPHABET_NUM;
			else
				inputString[i] = inputString[i] + ENCRYPTION_NUM;
		}
	}
	cout << "문장출력 : " << inputString;
	return 0;
}