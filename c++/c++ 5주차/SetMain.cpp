#include "Set.h"
int main()
{
    Set s1, s2;
    cin >> s1 >> s2;
    cout << "�������� : " << (s1 | s2) << endl;
    cout << "�������� : " << (s1 & s2) << endl;
    return 0;
}