#include <iostream>
using namespace std;
int main()
{
    const int MAX = 5;
    int arr[MAX] = { 1,2,3,4,5 };
    for (int* p = &arr[0]; p < &arr[MAX]; p++)
        cout << *p << " ";
    cout << endl;
    return 0;
}