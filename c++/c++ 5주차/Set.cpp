#include "Set.h"
Set::Set() {
	arr = new int[FIRST_ARRAY_LENGTH];
	arrLength = FIRST_ARRAY_LENGTH;
	arrUsedLength = 0;
}
void Set::resize(int length) {
	int *tmp = new int[arrUsedLength];
	for (int i = 0; i < arrUsedLength; i++) {
		tmp[i] = arr[i];
	}
	delete[] arr;
	arr = new int[length];
	for (int i = 0; i < arrUsedLength; i++) {
		arr[i] = tmp[i];
	}
	delete[] tmp;
	arrLength = length;
}
bool Set::isOverlap(int num){
	bool isOverlap = false;
	for (int i = 0; i < arrUsedLength; i++) {
		if (arr[i] == num) {
			isOverlap = true;
			break;
		}
	}
	return isOverlap;
}
istream& operator>>(istream& inputStream, Set& set) {
	cout << "두 정수집합 입력(음수는 한 집합의 끝)" << endl;
	int num = 0;
	while (!(num < 0)) {
		cin >> num;
		if (num < 0)
			break;
		if (set.arrUsedLength == set.arrLength)
			set.resize(set.arrLength * 2);
		if (set.isOverlap(num)) {
			cout << num << "는 중복원소이므로 제거" << endl;
			continue;
		}
		set.arr[set.arrUsedLength] = num;
		set.arrUsedLength++;
	}
	return inputStream;
}
ostream& operator<<(ostream& outputStream, const Set& set){
	for (int i = 0; i < set.arrUsedLength; i++) {
		cout << set.arr[i] << " ";
	}
	cout << endl;
	return outputStream;
}
Set Set::operator|(const Set set) {
	Set newSet = Set();
	for (int i = 0; i < this->arrUsedLength; i++) {
		if (newSet.arrLength == newSet.arrUsedLength)
			newSet.resize(newSet.arrLength * 2);
		newSet.arr[newSet.arrUsedLength] = this->arr[i];
		newSet.arrUsedLength++;
	}
	for (int i = 0; i < set.arrUsedLength; i++) {
		if (newSet.arrLength == newSet.arrUsedLength)
			newSet.resize(newSet.arrLength * 2);
		if (!newSet.isOverlap(set.arr[i])) {
			newSet.arr[newSet.arrUsedLength] = set.arr[i];
			newSet.arrUsedLength++;
		}
	}
	return newSet;
}
Set Set::operator&(Set set) {
	Set newSet = Set();
	for (int i = 0; i < this->arrUsedLength; i++) {
		if (set.isOverlap(this->arr[i])) {
			if (newSet.arrLength == newSet.arrUsedLength)
				newSet.resize(newSet.arrLength * 2);
			newSet.arr[newSet.arrUsedLength] = this->arr[i];
			newSet.arrUsedLength++;
		}
	}
	return newSet;
}
