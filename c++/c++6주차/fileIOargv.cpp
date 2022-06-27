// 명령형 인자 첫번째 파일의 내용을 두번째 파일로 복사
#include <iostream>
#include <fstream>
#include <cassert>
using namespace std;
int main(int argc, char* argv[])
{
	char ch;
	ifstream istr; // ifstream 객체와 ofstream 객체 인스턴스화
	ofstream ostr;
	// 파일 열고 정상적으로 열렸는지 확인
	istr.open(argv[1], ios::in);
	if (!istr.is_open())
	{
		cout << argv[1] << "을 열 수 없습니다." << endl;
		assert(false);
	}
	ostr.open(argv[2], ios::out);
	if (!ostr.is_open())
	{
		cout << argv[2] << "을 열 수 없습니다." << endl;
		assert(false);
	}
	// file1의 내용을 읽어 file2에 쓰기
	while (istr.get(ch))
		ostr.put(ch);
	istr.close(); // 파일 2개 닫기
	ostr.close();
	return 0;
}