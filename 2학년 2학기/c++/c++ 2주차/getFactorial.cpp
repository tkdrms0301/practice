#include <iostream>
using namespace std;
int getFactorial(int num);
//����1
//50 �̻��� ���� �Է¿� ���� Ʋ�� ���� ������ ����
//16���� �Է��غ���� int �ڷ����� ���� ������ -2^32 ~ 2^32 - 1 �����̸� ������ �Ѿ�� ���� ǥ���Ҽ�����(�����÷ο� �߻�)

//����2
//���Ѵ� �Է¿� ���ؼ� �ùٸ� ����� ����� �� �ִ� ���
// -
int main()
{
	int number = 0;
	cout << "n! �� ���� n���� �Է��Ͻÿ�. : ";
	cin >> number;
	while (number < 0) {
		cout << "n! �� ���� n���� �ٽ� �Է��Ͻÿ�. : ";
		cin >> number;
	}
	
	int result = getFactorial(number);
	cout << "����� : " << result << endl;
	return 0;
}
int getFactorial(int num) {
	int calculateResult = 1;
	if (num == 0) {
		return calculateResult;
	}else {
		while (num) {
			calculateResult = calculateResult * num;
			num--;
		}
		return calculateResult;
	}
}