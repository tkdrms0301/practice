#include <iostream>
using namespace std;
void merge(int arr1[], int arr2[], int mergeArr[]);
const int ARRAY_LENGTH = 5;
const int MERGE_ARRAY_LENGTH = 10;
//�Լ� merge()�� mergeArr�� return�ϴ� ���·� �����ϸ� �������� ���� �ʴ� ����
//�迭ppt 5-9 funct3() �Լ��� �����ϰ� �Լ��� ������ ���ÿ� �Լ��� ������ �迭�� ������Ƿ� �����ϵ��� ����
int main()
{
    int arr1[ARRAY_LENGTH] = {};
    int arr2[ARRAY_LENGTH] = {};
    int mergeArr[MERGE_ARRAY_LENGTH] = {};

    cout << "�������� ���� 5�� �Է� : ";
    for (int i = 0; i < ARRAY_LENGTH; i++) {
        cin >> arr1[i];
    }
    cout << "�������� ���� 5�� �Է� : ";
    for (int i = 0; i < ARRAY_LENGTH; i++) {
        cin >> arr2[i];
    }
    cout << "�պ��� ���� 10�� : ";
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
