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
    cout << "미사일 발사 프로그램" << endl
        << "1. 미사일 발사" << endl
        << "2. 미사일 존재 확인" << endl
        << "3. 종료" << endl;
    while (num != 3) {
        cout << "번호를 입력하시오 : ";
        cin >> num;
        switch (num)
        {
        case 1:
            cout << "미사일을 발사 합니다.." << endl;
            launchMissile();
            break;
        case 2:
            cout << "미사일 존재를 확인합니다.." << endl;
            checkMissile();
            break;
        case 3:
            cout << "종료합니다.." << endl;
            break;
        default:
            cout << "잘못된 입력입니다." << endl;
            break;
        }
    }
}
void launchMissile() {
    int missileNum = -1;
    while (missileNum != 0) {
        cout << "발사할 미사일 번호를 입력하시오(0이면 종료)" << endl;
        cin >> missileNum;
        for (int i = 0; i < MISSILE_AMOUNT; i++) {
            if (missileLancher[i].missileNum == missileNum && missileLancher[i].launch == true) {
                cout << "로켓이 발사되었습니다." << endl;
                missileLancher[i].launch = false;
                break;
            }
            else if (missileLancher[i].missileNum == missileNum && missileLancher[i].launch == false) {
                cout << "이미 로켓이 발사되었습니다." << endl;
            }
        }
        bool isLanch = true;
        for (int i = 0; i < MISSILE_AMOUNT; i++) {
            if (missileLancher[i].launch == true) {
                isLanch = false;
            }
        }
        if (isLanch) {
            cout << "모든 미사일이 발사되었습니다." << endl;
            break;
        }
    }
}
void checkMissile() {
    for (int i = 0; i < MISSILE_AMOUNT; i++) {
        if (missileLancher[i].launch == true) {
            cout << missileLancher[i].missileNum << "번 미사일이 발사되었습니다.." << endl;
        }
        else {
            cout << missileLancher[i].missileNum << "번 미사일이 존재하지 않습니다.." << endl;
        }
    }
}