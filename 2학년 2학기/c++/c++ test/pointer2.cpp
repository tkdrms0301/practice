#include <iostream>
using namespace std;
int main() {
	int v1 = 8, v2 = 9;
	int *p1 = &v1, *p2 = &v2;
	cout << v1 << " " << v2 << endl;
	cout << p1 << " " << p2 << endl;
	cout << *p1 << " " << *p2 << endl;
	cout << "change p1 = p2" << endl;
	*p1 = *p2;
	cout << v1 << " " << v2 << endl;
	cout << p1 << " " << p2 << endl;
	cout << *p1 << " " << *p2 << endl;
	return 0;
}