#include <iostream>
using namespace std;
void swap(int& n1, int& n2); // 정렬에서 자주 사용하는 값교환

//void sendConstRef(const Student &std1, const Student &std2);
//읽기 전용으로 인자를 함수에 전달함
int main()
{
	int stdScore1, stdScore2;
	cout << "학생 두명의 점수를 입력하세요 : ";
		cin >> stdScore1 >> stdScore2;
	swap(stdScore1, stdScore2);
	cout << "교환된 두개의 값은 : ";
	cout << stdScore1 << "과" << stdScore2 << endl;
	return 0;
}
void swap(int& n1, int& n2)
{
	int temp = n1;
	n1 = n2;
	n2 = temp;
}