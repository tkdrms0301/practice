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
	cout << "a와 B 중에 작은 것 = "
		<< smaller('a', 'B') << endl;
	cout << "12와 15 중에 작은 것 = "
		<< smaller(12, 15) << endl;
	cout << "44.2와 33.1 중에 작은 것 = "
		<< smaller(44.2, 33.1) << endl;
	return 0;
}