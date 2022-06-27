#include <iostream>
using namespace std;
int main() {
	int val1 = 8, val2 = 3; // 정수형 변수
	int* pInt1, * pInt2;// 정수형 포인터
	double x = 1.7, * pDouble = &x; // 실수형
	char c = 'A', * pChar = &c; // 문자형
	pInt1 = &val1;
	cout << &val1 << " " << val1 << endl;
	cout << pInt1 << " " << *pInt1 << " " << endl;
	pInt1 = &val2; pInt2 = pInt1;
	cout << pInt1 << " " << *pInt1 << " " << endl;
	cout << pInt2 << " " << *pInt2 << " " << endl;
	*pInt1 = 77;
	cout << pInt2 << " " << *pInt2 << " " << endl;
	cout << (void*)pChar << " " << *pChar << " " << endl;
	cout << sizeof(pChar) << " " << sizeof(pInt1) << " " << sizeof(pDouble) << endl;
	return 0;
}