#include <iostream>
using namespace std;
int main() {
	int val1 = 8, val2 = 3; // ������ ����
	int* pInt1, * pInt2;// ������ ������
	double x = 1.7, * pDouble = &x; // �Ǽ���
	char c = 'A', * pChar = &c; // ������
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