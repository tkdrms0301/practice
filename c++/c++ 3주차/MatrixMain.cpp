#include "Matrix.h"
#include <iostream>
using namespace std;
int main()
{
    Matrix m1, m2; // 자동으로 3x3 랜덤 행렬 생성. 
                   // 각 요소는 -10~10 범위의 값이 되도록 
    m1.print();
    cout << endl;
    m2.print();
    cout << endl;
    Matrix m3 = m1.add(m2);
    cout << "두 행렬의 합은 " << endl;
    m3.print();
    cout << endl;
    m3 = m1.multi(m2);
    cout << "두 행렬의 곱은 " << endl;
    m3.print();
    return 0;
}