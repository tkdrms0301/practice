#include <iostream>
using namespace std;
int main()
{
	int num, result = 0, count = 0;
	int number[4] = {};
	cin >> num;
	int numCopy = num;
	for (int i = 0; i < 4; i++) {
		number[i] = numCopy % 10;
		numCopy = numCopy / 10;
	}
	while(true){
		for (int i = 0; i < 4; i++) {
			for (int j = 1; j < 4; j++) {
				if (number[i] > number[j]) {
					int temp = number[j];
					number[j] = number[i];
					number[i] = temp;
				}
			}
		}
		int asd = 0, desc = 0;
		for (int i = 0; i < 4; i++) {
			desc = desc + number[i];
			desc = desc * 10;
		}
		for (int i = 3; i >= 0; i--) {
			asd = asd + number[i];
			asd = asd * 10;
		}
		result = asd - desc;
		if (num == result) {
			break;
		}
		count++;
	}
	return 0;
}