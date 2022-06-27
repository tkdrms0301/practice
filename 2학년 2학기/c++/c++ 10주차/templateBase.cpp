#include <iostream>
using namespace std;
template<typename T> void swapValues(T& var1, T& var2);
int main() {
	int a = 2;
	int b = 5;
	swapValues(a, b);
	cout << a << endl;
	cout << b << endl;
	return 0;
}
template<typename T>
void swapValues(T& var1, T& var2)
{
	T temp = var1;
	var1 = var2;
	var2 = temp;
}