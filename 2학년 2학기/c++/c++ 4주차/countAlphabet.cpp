#include <iostream>
#include <string>
using namespace std;
const int ALPHABET_NUM = 26;
const int UPPER_LOWER_DIFFERENCE = 32;
int main() {
	string inputString;
	int alphabetCount[ALPHABET_NUM] = {};
	cout << "문장입력 : ";
	getline(cin, inputString);
	for (int i = 0; i < inputString.length() && inputString[i] != '\0'; i++) {
		if (inputString[i] >= 'a' && inputString[i] <= 'z') {
			alphabetCount[inputString[i] - 'a']++;
		}
		else if (inputString[i] >= 'A' && inputString[i] <= 'Z') {
			alphabetCount[inputString[i] - 'A']++;
		}
	}
	for (int i = 0; i < ALPHABET_NUM; i++) {
		if (alphabetCount[i] == 0)
			continue;
		else {
			cout << "[" << (char)(i + 'a') << "] " << alphabetCount[i] << " ";
		}
	}
	return 0;
}