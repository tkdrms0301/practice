#include "Fraction.h"
#include <iostream>
using namespace std;

int main()
{
    Fraction f1, f2;
    cout << "분자와 분모를 정수로 입력하세요 : " << endl;
    cin >> f1; // 분모가 0으로 들어오면 다시 입력 
    cout << "분자와 분모를 정수로 입력하세요 : " << endl;
    cin >> f2; // 분모가 0으로 들어오면 다시 입력 
    cout << f1 << " + " << f2 << " = " << f1 + f2 << endl;
    cout << f1 << " - " << f2 << " = " << f1 - f2 << endl;
    cout << f1 << " * " << f2 << " = " << f1 * f2 << endl;
    cout << f1 << " / " << f2 << " = " << f1 / f2 << endl;

    if (f1 < f2)
        cout << f1 << " < " << f2 << endl;
    else if (f1 > f2)
        cout << f1 << " > " << f2 << endl;
    else if (f1 == f2)
        cout << f1 << " == " << f2 << endl;
    return 0;
}