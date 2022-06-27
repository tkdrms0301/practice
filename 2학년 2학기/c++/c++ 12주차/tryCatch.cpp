#include <iostream>
using namespace std;

int main()
{
	int num1, num2, result;
	for (int i = 0; i < 3; i++)
	{
		cout << "정수 입력: ";
		cin >> num1;
		cout << "다음 정수 입력 : ";
			cin >> num2;
		// try-catch 블록
		try
		{
			if (num2 == 0)
				throw 0; // int 자료형의 예외를 발생
			result = num1 / num2;
			cout << "결과 = " << result << endl; // 패턴1
			cout << "결과 = " << quotient(num1, num2) << endl; // 패턴2
		}
		catch (int x)
		{
			cout << "0으로 나눌 수 없습니다." << endl;
		}
	}
	return 0;
}
//함수 정의
int quotient(int first, int second)
{
	if(second == 0)
		throw 0;
	return first / second;
}