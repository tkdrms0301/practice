#include "Matrix.h"
#include <iostream>
using namespace std;
int main()
{
    Matrix m1, m2; // �ڵ����� 3x3 ���� ��� ����. 
                   // �� ��Ҵ� -10~10 ������ ���� �ǵ��� 
    m1.print();
    cout << endl;
    m2.print();
    cout << endl;
    Matrix m3 = m1.add(m2);
    cout << "�� ����� ���� " << endl;
    m3.print();
    cout << endl;
    m3 = m1.multi(m2);
    cout << "�� ����� ���� " << endl;
    m3.print();
    return 0;
}