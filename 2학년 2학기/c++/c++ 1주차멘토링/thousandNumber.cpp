#include <iostream>
using namespace std;
bool iskaprekarNum(int num);
int main()
{
	cout << "1000이하의 자연수 입력 : ";
	int num;
	cin >> num;
	int startNum = 1;
	bool isKaprekarNum;
	cout << "kaprekar number : ";
	while (startNum != num) {
		isKaprekarNum = iskaprekarNum(startNum);
		if (isKaprekarNum == true) {
			cout << startNum << " ";
		}
		startNum++;
	}
	return 0;
}
bool iskaprekarNum(int num) {
	int sqrt = num * num;
	int count = 0;
	int result;
	int digit = 1;
	while (sqrt != 0) {
		sqrt = sqrt / 10;
		count++;
	}
	if (count == 1) {
		result = num * num;
	}
	else {
		for (int i = 1; i < count; i++) {
			digit = digit * 10;
			result = (num * num) / digit + (num * num) % digit;
			if ((num * num) / digit == 0 || (num * num) % digit == 0)
				continue;
			if (num == result)
				return true;
		}
	}
	return false;
}