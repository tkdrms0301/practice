#include "Set.h"
int main()
{
    Set s1, s2;
    cin >> s1 >> s2;
    cout << "합집합은 : " << (s1 | s2) << endl;
    cout << "교집합은 : " << (s1 & s2) << endl;
    return 0;
}