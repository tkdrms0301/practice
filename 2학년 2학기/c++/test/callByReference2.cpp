#include <iostream>
using namespace std;
void swap(int& n1, int& n2); // ���Ŀ��� ���� ����ϴ� ����ȯ

//void sendConstRef(const Student &std1, const Student &std2);
//�б� �������� ���ڸ� �Լ��� ������
int main()
{
	int stdScore1, stdScore2;
	cout << "�л� �θ��� ������ �Է��ϼ��� : ";
		cin >> stdScore1 >> stdScore2;
	swap(stdScore1, stdScore2);
	cout << "��ȯ�� �ΰ��� ���� : ";
	cout << stdScore1 << "��" << stdScore2 << endl;
	return 0;
}
void swap(int& n1, int& n2)
{
	int temp = n1;
	n1 = n2;
	n2 = temp;
}