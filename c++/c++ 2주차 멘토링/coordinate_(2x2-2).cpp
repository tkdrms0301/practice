#include <iostream>
using namespace std;
const int COORDINATE_LENGTH = 31;
struct Coordinate {
    int x; 
    int y;
};
int main()
{
    Coordinate coordinate_1[COORDINATE_LENGTH];
    for (int i = 0; i < COORDINATE_LENGTH; i++) {
        coordinate_1[i] = { i - 15, 2 * (i - 15) - 2};
    }
    int count = 30;
    for (int i = 0; i < COORDINATE_LENGTH; i++) {
        for (int j = 0; j < COORDINATE_LENGTH; j++) {
            while (coordinate_1[count].y > 15 || coordinate_1[count].y < -15) {
                count--;
            }
            if (coordinate_1[count].x == j - 15 && coordinate_1[count].y == 15 - i) {
                cout << " ";
                count--;
            }
            else
                cout << "*";
        }
        if (i == 15)
            cout << " xÃà";
        cout << endl;
    }
    return 0;
}