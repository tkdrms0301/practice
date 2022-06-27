#include "Date.h"
#include "string"
const int First = 0;
// **���ڰ� Ŀ������ ����� ������ �������� ����**
int main() {
	string input;
	Date date;
	while (input != "Q") {
		date.printDate(date);
		cin >> input;
		if (input[First] == '+' || input[First] == '-') {
			if (input.length() == 1 && input[First] == '+') {
				++date;
			}
			else if(input.length() == 1 && input[First] == '-'){
				--date;
			}else{
				bool valid = true;
				for (int i = 1; i < input.length(); i++) {
					if (!('0' <= input[i] && input[i] <= '9')) {
						cout << "�߸��� ���� �Է��Դϴ�." << endl;
						cout << endl;
						valid = false;
						break;
					}
				}
				if (valid) {
					date.setD_Day(stoi(input));
					// +00 -00 D_Day �� ���� ��¥����
				}
				else
					continue;
			}
		}
		else if (input[First] == 'Q' || input[First] == 'q') {
			cout << "�����մϴ�." << endl;
		}
		else if ('0' <= input[First] && input[First] <= '9') {
			for (int i = 0; i < input.length(); i++) {
				if (!('0' <= input[i] && input[i] <= '9')) {
					cout << "�߸��� ���� �Է��Դϴ�." << endl;
					cout << endl;
					break;
				}
				else if (input.length() != 8) {
					cout << "�߸��� �ڸ��� �Է��Դϴ�." << endl;
					break;
				}
			}
			if (input.length() != 8) {
				cout << endl;
				continue;
			}
			int inputToDate = stoi(input);
			Date newDate(inputToDate / 10000, inputToDate / 100 % 100, inputToDate % 100);
			if (newDate.isValid()) {
				date.setDate(inputToDate / 10000, inputToDate / 100 % 100, inputToDate % 100);
			}
			else {
				cout << "�Ұ����� ��¥ �Դϴ�." << endl;
			}
		}
		else {
			cout << "�߸��� ���� �Է��Դϴ�." << endl;
		}
		cout << endl;
	}
	return 0;
}