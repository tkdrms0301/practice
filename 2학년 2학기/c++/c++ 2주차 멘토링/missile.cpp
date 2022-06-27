#include <iostream>
using namespace std;
const int MISSILE_AMOUNT = 10;
void launchMissilePGM();
void launchMissile();
void checkMissile();
struct Missile {
    int missileNum;
    bool launch;
};
Missile missileLancher[MISSILE_AMOUNT];
int main()
{
    for (int i = 0; i < MISSILE_AMOUNT; i++) {
        missileLancher[i] = { i + 1, true };
    }
    launchMissilePGM();
    return 0;
}
void launchMissilePGM() {
    int num = 0;
    cout << "�̻��� �߻� ���α׷�" << endl
        << "1. �̻��� �߻�" << endl
        << "2. �̻��� ���� Ȯ��" << endl
        << "3. ����" << endl;
    while (num != 3) {
        cout << "��ȣ�� �Է��Ͻÿ� : ";
        cin >> num;
        switch (num)
        {
        case 1:
            cout << "�̻����� �߻� �մϴ�.." << endl;
            launchMissile();
            break;
        case 2:
            cout << "�̻��� ���縦 Ȯ���մϴ�.." << endl;
            checkMissile();
            break;
        case 3:
            cout << "�����մϴ�.." << endl;
            break;
        default:
            cout << "�߸��� �Է��Դϴ�." << endl;
            break;
        }
    }
}
void launchMissile() {
    int missileNum = -1;
    while (missileNum != 0) {
        cout << "�߻��� �̻��� ��ȣ�� �Է��Ͻÿ�(0�̸� ����)" << endl;
        cin >> missileNum;
        for (int i = 0; i < MISSILE_AMOUNT; i++) {
            if (missileLancher[i].missileNum == missileNum && missileLancher[i].launch == true) {
                cout << "������ �߻�Ǿ����ϴ�." << endl;
                missileLancher[i].launch = false;
                break;
            }
            else if (missileLancher[i].missileNum == missileNum && missileLancher[i].launch == false) {
                cout << "�̹� ������ �߻�Ǿ����ϴ�." << endl;
            }
        }
        bool isLanch = true;
        for (int i = 0; i < MISSILE_AMOUNT; i++) {
            if (missileLancher[i].launch == true) {
                isLanch = false;
            }
        }
        if (isLanch) {
            cout << "��� �̻����� �߻�Ǿ����ϴ�." << endl;
            break;
        }
    }
}
void checkMissile() {
    for (int i = 0; i < MISSILE_AMOUNT; i++) {
        if (missileLancher[i].launch == true) {
            cout << missileLancher[i].missileNum << "�� �̻����� �߻�Ǿ����ϴ�.." << endl;
        }
        else {
            cout << missileLancher[i].missileNum << "�� �̻����� �������� �ʽ��ϴ�.." << endl;
        }
    }
}