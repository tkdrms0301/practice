#include<iostream>
using namespace std;

// 템플릿 함수 정의
template <typename T>
T smaller(const T& first, const T& second)
{
	if (first < second) return first;
	return second;
}
// 템플릿 함수 특수화
template<>
const char* smaller(const char* const& first, const char* const& second)
{
	if (strcmp(first, second) < 0) return first;
	return second;
}
int main()
{
	// 문자열로 템플릿 함수 호출
	string str1 = "Hello";
	string str2 = "Hi";
	cout << "Smaller(Hello , Hi) = " << smaller(str1, str2) << endl;
	// C 문자열로 템플릿 함수 호출
	const char* s1 = "Bye";
	const char* s2 = "Bye Bye";
	cout << "Smaller(Bye, Bye Bye) = " << smaller(s1, s2) << endl;
	return 0;
}