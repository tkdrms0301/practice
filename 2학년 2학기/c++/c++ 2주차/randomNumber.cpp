#include <iostream>
#include <cstdlib>
using namespace std;
int main()
{
	cout << "���� 100������ �ڿ��� �ϳ��� �����ϰ� �ֽ��ϴ�." << endl;
	srand(1);
	int randomNumber = rand() % 100 + 1;
	int number = -1;
	int count = 0;
	
	while (randomNumber != number) {
		cout << "���ڸ� �����ؼ� �Է��ϼ���: ";
		cin >> number;
		count++;
		if (randomNumber > number) {
			cout << "���� �����ϰ� �ִ� ���ڴ� " << number << "���ٴ� ū �����Դϴ�." << endl;
		}else if (randomNumber < number) {
			cout << "���� �����ϰ� �ִ� ���ڴ� " << number << "���ٴ� ���� �����Դϴ�." << endl;
		}else {
			cout << "������ϴ�!!! " << count << "�� ���� �����ϼ̳׿�!" << endl;
		}
		cout << endl;
	}
	return 0;
}