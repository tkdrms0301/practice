#include <iostream>
using namespace std;
template <typename T>
T smaller(T first, T second)
{
	if (first < second)
		return first;
	return second;
}
int main()
{
	cout << "a�� B �߿� ���� �� = "
		<< smaller('a', 'B') << endl;
	cout << "12�� 15 �߿� ���� �� = "
		<< smaller(12, 15) << endl;
	cout << "44.2�� 33.1 �߿� ���� �� = "
		<< smaller(44.2, 33.1) << endl;
	return 0;
}