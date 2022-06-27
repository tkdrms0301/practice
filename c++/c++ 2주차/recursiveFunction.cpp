#include <iostream>
using namespace std;
void showDigit(int n);
void showReverseDigit(int n);
int main()
{
    int val;
    cout << "정수 입력: ";
    cin >> val;

    cout << "바로 출력 : ";
    showDigit(val);
    cout << endl;

    cout << "거꾸로 출력: ";
    showReverseDigit(val);
    cout << endl;
    return 0;
}
void showDigit(int n) {
    if (n < 0) {
        n = -n;
        cout << "- ";
    }  
    if (n / 10 != 0) {
        showDigit(n / 10);
    }
    cout << n % 10 << " ";
}
void showReverseDigit(int n) {
    if (n < 0) {
        n = -n;
        cout << "- ";
    }
    cout << n % 10 << " ";
    if (n / 10 != 0) {
        showReverseDigit(n / 10);
    }
}