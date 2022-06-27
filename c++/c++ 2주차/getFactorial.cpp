#include <iostream>
using namespace std;
int getFactorial(int num);
//질문1
//50 이상의 정수 입력에 대해 틀린 값이 나오는 이유
//16까지 입력해본결과 int 자료형의 수의 범위가 -2^32 ~ 2^32 - 1 까지이며 범위를 넘어가는 수를 표현할수없음(오버플로우 발생)

//질문2
//무한대 입력에 대해서 올바른 결과를 출력할 수 있는 방법
// -
int main()
{
	int number = 0;
	cout << "n! 을 구할 n값을 입력하시오. : ";
	cin >> number;
	while (number < 0) {
		cout << "n! 을 구할 n값을 다시 입력하시오. : ";
		cin >> number;
	}
	
	int result = getFactorial(number);
	cout << "계산결과 : " << result << endl;
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