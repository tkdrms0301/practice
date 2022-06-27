#include <iostream>
using namespace std;
void printArr(int *p, int size)
{
    for (int count = 0, *i = &p[0]; i < &p[size * size]; i++)
    {
        cout << *i << " ";
        count++;
        if(count % 3 == 0)
            cout << endl;
    }
}
int main()
{
    const int MAX = 3;
    int arr[MAX][MAX] = { 1,2,3,4,5,6,7,8,9 };
    printArr((int*)arr, MAX);
    return 0;
}