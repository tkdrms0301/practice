#include <iostream>
#include <cassert>
#define NDEBUG
using namespace std;
float getSquareRoot(float t);
int main()
{
	cout << getSquareRoot(9) << endl;
	cout << getSquareRoot(-9) << endl; // �ߴ�
	return 0;
}
float getSquareRoot(float t)
{
	assert(t > 0); // t �� 0 ���� ������ assert �߻� 
	return(sqrt(t));
}