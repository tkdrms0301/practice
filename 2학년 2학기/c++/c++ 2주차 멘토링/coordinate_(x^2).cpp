#include <iostream>
#include <cstdlib>
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
        coordinate_1[i] = { i - 15, (i - 15) * (i - 15) };
    }
    for (int i = 0; i < COORDINATE_LENGTH; i++) {
        cout << coordinate_1[i].x << " " << coordinate_1[i].y << " " << endl;
    }
    for (int i = 0; i < COORDINATE_LENGTH; i++) {
        for (int j = 0; j < COORDINATE_LENGTH; j++) {
            bool dot = false;
            for (int k = 0; k < COORDINATE_LENGTH; k++) {
                if (15 - coordinate_1[k].x == j && 15 - coordinate_1[k].y == i) {
                    cout << " ";
                    dot = true;
                }
            }
            if (!dot) {
                cout << "*";
            }
        }
        if (i == 15)
            cout << " xÃà";
        cout << endl;
    }
    return 0;
}