#include <iostream>
using namespace std;
void showDigit(int n);
void showReverseDigit(int n);
int main()
{
    int val;
    cout << "���� �Է�: ";
    cin >> val;

    cout << "�ٷ� ��� : ";
    showDigit(val);
    cout << endl;

    cout << "�Ųٷ� ���: ";
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