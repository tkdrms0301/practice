#include <iostream>
#include <cassert>
#define NDEBUG
using namespace std;
float getSquareRoot(float t);
int main()
{
	cout << getSquareRoot(9) << endl;
	cout << getSquareRoot(-9) << endl; // 중단
	return 0;
}
float getSquareRoot(float t)
{
	assert(t > 0); // t 가 0 보다 작을때 assert 발생 
	return(sqrt(t));
}