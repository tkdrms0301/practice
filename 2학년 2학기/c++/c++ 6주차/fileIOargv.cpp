// ����� ���� ù��° ������ ������ �ι�° ���Ϸ� ����
#include <iostream>
#include <fstream>
#include <cassert>
using namespace std;
int main(int argc, char* argv[])
{
	char ch;
	ifstream istr; // ifstream ��ü�� ofstream ��ü �ν��Ͻ�ȭ
	ofstream ostr;
	// ���� ���� ���������� ���ȴ��� Ȯ��
	istr.open(argv[1], ios::in);
	if (!istr.is_open())
	{
		cout << argv[1] << "�� �� �� �����ϴ�." << endl;
		assert(false);
	}
	ostr.open(argv[2], ios::out);
	if (!ostr.is_open())
	{
		cout << argv[2] << "�� �� �� �����ϴ�." << endl;
		assert(false);
	}
	// file1�� ������ �о� file2�� ����
	while (istr.get(ch))
		ostr.put(ch);
	istr.close(); // ���� 2�� �ݱ�
	ostr.close();
	return 0;
}