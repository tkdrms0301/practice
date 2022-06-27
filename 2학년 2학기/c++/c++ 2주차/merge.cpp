#include <iostream>
using namespace std;
void merge(int arr1[], int arr2[], int mergeArr[]);
const int ARRAY_LENGTH = 5;
const int MERGE_ARRAY_LENGTH = 10;
//함수 merge()가 mergeArr을 return하는 형태로 구현하면 컴파일이 되지 않는 이유
//배열ppt 5-9 funct3() 함수와 동일하게 함수가 끝나는 동시에 함수내 선언한 배열이 사라지므로 컴파일되지 않음
int main()
{
    int arr1[ARRAY_LENGTH] = {};
    int arr2[ARRAY_LENGTH] = {};
    int mergeArr[MERGE_ARRAY_LENGTH] = {};

    cout << "오름차순 정수 5개 입력 : ";
    for (int i = 0; i < ARRAY_LENGTH; i++) {
        cin >> arr1[i];
    }
    cout << "오름차순 정수 5개 입력 : ";
    for (int i = 0; i < ARRAY_LENGTH; i++) {
        cin >> arr2[i];
    }
    cout << "합병된 정수 10개 : ";
    merge(arr1, arr2, mergeArr);
    return 0;
}
void merge(int arr1[], int arr2[], int mergeArr[]) {
    int front = 0, end = 0;
    for (int i = 0; i < MERGE_ARRAY_LENGTH; i++) {
        if (front == ARRAY_LENGTH) {
            mergeArr[i] = arr2[end];
            end++;
            continue;
        }
        else if (end == ARRAY_LENGTH) {
            mergeArr[i] = arr1[front];
            front++;
            continue;
        }
        if (arr1[front] < arr2[end]) {
            mergeArr[i] = arr1[front];
            front++;
        }
        else {
            mergeArr[i] = arr2[end];
            end++;
        }
    }
    for (int i = 0; i < MERGE_ARRAY_LENGTH; i++) {
        cout << mergeArr[i] << " ";
    }
}
