#include <iostream>
using namespace std;

void printArr(int *arr, int arrLength);
int main()
{
    const int MAX = 5;
    int arr1[MAX] = { 1,2,3,4,5 };
    int arr2[MAX];
    int arr3[MAX];
    int *p;
    copy(arr1, arr1 + MAX, arr2);
    copy(arr1, arr1 + MAX, arr3);
    p = arr3;  // arr1의 내용을 p로 표현되는 동적배열에 복사
    p[0] = 99;
    printArr(arr1, MAX); printArr(arr2, MAX); printArr(p, MAX);
    return 0;
}
void printArr(int* arr, int arrLength) {
    for (int *i = &arr[0]; i < &arr[arrLength]; i++) {
        cout << *i << " ";
    }
    cout << endl;
}