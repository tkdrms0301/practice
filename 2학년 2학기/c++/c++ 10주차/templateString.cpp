#include<iostream>
using namespace std;

// ���ø� �Լ� ����
template <typename T>
T smaller(const T& first, const T& second)
{
	if (first < second) return first;
	return second;
}
// ���ø� �Լ� Ư��ȭ
template<>
const char* smaller(const char* const& first, const char* const& second)
{
	if (strcmp(first, second) < 0) return first;
	return second;
}
int main()
{
	// ���ڿ��� ���ø� �Լ� ȣ��
	string str1 = "Hello";
	string str2 = "Hi";
	cout << "Smaller(Hello , Hi) = " << smaller(str1, str2) << endl;
	// C ���ڿ��� ���ø� �Լ� ȣ��
	const char* s1 = "Bye";
	const char* s2 = "Bye Bye";
	cout << "Smaller(Bye, Bye Bye) = " << smaller(s1, s2) << endl;
	return 0;
}