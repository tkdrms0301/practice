#include <iostream>
#include <string>
using namespace std;
string toBinary(int num);
int main()
{
    int num = 1;
    while (num != 0) {
        cout << "10진수 입력(종료는 0) : ";
        cin >> num;
        if (num == 0) {
            cout << "종료";
            break;
        }
        string toBinaryResult = toBinary(num);
        cout << "2진수는 " << toBinaryResult << endl;
    }
    return 0;
}
string toBinary(int num) {
    string toBinaryResult = "";
    int count = 0;
    while (num != 0) {
        toBinaryResult = to_string(num % 2) + toBinaryResult;
        num = num / 2;
        count++;
        if (count % 4 == 0)
            toBinaryResult = " " + toBinaryResult;
    }
    return toBinaryResult;
}