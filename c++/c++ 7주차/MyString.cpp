#include "MyString.h"

MyString::MyString()
{
	arrayLength = FIRST_ARRAY_SIZE;
	usedArrayLength = 0;
	arr = new char[arrayLength];
	arr[usedArrayLength] = '\0';
}
MyString::MyString(const char* srcStr)
{
	arrayLength = FIRST_ARRAY_SIZE;
	usedArrayLength = 0;
	arr = new char[arrayLength];
	for (int i = 0; i < srcStr[i] != '\0'; i++)
	{
		if (usedArrayLength == arrayLength)
			resize(arrayLength * 2);
		arr[i] = srcStr[i];
		usedArrayLength++;
	}
	if (usedArrayLength == arrayLength)
		resize(arrayLength * 2);
	arr[usedArrayLength] = '\0';
}
MyString::MyString(const MyString& srcStr)
{
	arrayLength = srcStr.arrayLength;
	usedArrayLength = srcStr.usedArrayLength;
	arr = new char[srcStr.arrayLength];
	for (int i = 0; i < usedArrayLength; i++)
	{
		if (usedArrayLength == arrayLength)
			resize(arrayLength * 2);
		arr[i] = srcStr.arr[i];
	}
	if (usedArrayLength == arrayLength)
		resize(arrayLength * 2);
	arr[usedArrayLength] = '\0';
}
MyString::~MyString()
{
	delete[] arr;
}
istream& operator >>(istream& is, MyString& str)
{
	delete[] str.arr;
	str.arr = new char[str.arrayLength];
	str.usedArrayLength = 0;
	char character = cin.get();
	while (character != '\n')
	{
		if (str.usedArrayLength == str.arrayLength)
			str.resize(str.arrayLength * 2);
		str.arr[str.usedArrayLength] = (char)character;
		str.usedArrayLength++;
		character = getchar();
	}
	if (str.usedArrayLength == str.arrayLength)
		str.resize(str.arrayLength * 2);
	str.arr[str.usedArrayLength] = '\0';
	// 엔터키까지 입력.  getchar() 사용 가능
	return is;
}

ostream& operator <<(ostream& os, const MyString& str)
{
	for (int i = 0; i < str.usedArrayLength; i++)
	{
		cout << str.arr[i];
	}
	return os;
}
void MyString::resize(int length) {
	char* tmp = new char[length];
	for (int i = 0; i < usedArrayLength; i++)
	{
		tmp[i] = arr[i];
	}
	delete[] arr;
	arr = tmp;
	arrayLength = length;
}
int MyString::compare(const MyString str)
{
	return this->compare(str.arr);
}
int MyString::compare(const char* subStr)
{
	int subStrLength = 0;
	while (subStr[subStrLength] != '\0')
		subStrLength++;
	int compareLength;
	if (usedArrayLength > subStrLength)
		compareLength = subStrLength;
	else
		compareLength = usedArrayLength;
	for (int i = 0; i < compareLength; i++)
	{
		if (arr[i] > subStr[i])
			return 1;
		else if (arr[i] < subStr[i])
			return -1;
	}
	if (usedArrayLength == subStrLength) return 0;
	else if (usedArrayLength > subStrLength)
		return 1;
	else
		return -1;
}
MyString MyString::operator=(const MyString& srcStr)
{
	if (this == &srcStr)
		return *this;
	arrayLength = srcStr.arrayLength;
	usedArrayLength = srcStr.usedArrayLength;
	delete[] arr;
	arr = new char[arrayLength];
	for (int i = 0; i < usedArrayLength; i++)
	{
		if (arrayLength == usedArrayLength)
			resize(arrayLength * 2);
		arr[i] = srcStr.arr[i];
	}
	if (arrayLength == usedArrayLength)
		resize(arrayLength * 2);
	arr[usedArrayLength] = '\0';
	return *this;
}
int MyString::length() const
{
	return usedArrayLength;
}
char MyString::at(int pos) const
{
	if (pos >= usedArrayLength)
		return ' ';
	// 인덱스보다 큰경우 결과값을반환하는 프로그램을 실행했지만 프로그램실행이 중단되었음
	// 공백 문자열로 대체
	else
		return arr[pos];
}
bool MyString::empty() const
{
	if (usedArrayLength == 0)
		return true;
	else
		return false;
}
const MyString MyString::operator + (const char* str2nd) const
{
	int subStrLength = 0;
	while (str2nd[subStrLength] != '\0')
		subStrLength++;
	char* tmp = new char[usedArrayLength + subStrLength + 1];
	for (int i = 0; i < usedArrayLength; i++)
	{
		tmp[i] = arr[i];
	}
	for (int i = usedArrayLength; i < usedArrayLength + subStrLength; i++)
	{
		tmp[i] = str2nd[i - usedArrayLength];
	}
	tmp[usedArrayLength + subStrLength] = '\0';
	MyString myString(tmp);
	delete[] tmp;
	return myString;
}
const MyString MyString::operator + (const MyString& str2nd) const
{
	return this->operator+(str2nd.arr);
}
const MyString MyString::append(const char* str2nd)
{
	int subStrLength = 0;
	while (str2nd[subStrLength] != '\0')
		subStrLength++;
	for (int i = 0; i < subStrLength; i++)
	{
		if (arrayLength == usedArrayLength)
			resize(arrayLength * 2);
		arr[usedArrayLength] = str2nd[i];
		usedArrayLength++;
	}
	arr[usedArrayLength] = '\0';
	return *this;
}
const MyString MyString::append(const MyString& str2nd)
{
	this->append(str2nd.arr);
	return *this;
}
const bool MyString::operator ==(const MyString& str)
{
	if (this->compare(str) == 0)
		return true;
	else
		return false;
}
const bool MyString::operator !=(const MyString& str)
{
	if (this->compare(str) != 0)
		return true;
	else
		return false;
}
const int MyString::find(const char* subStr) const
{
	int subStrLength = 0;
	while (subStr[subStrLength] != '\0')
		subStrLength++;
	if (subStrLength == 0)
		return ERROR;
	else if (usedArrayLength < subStrLength)
		return ERROR;
	else if (usedArrayLength == subStrLength)
	{
		if (MyString(subStr) == *this)
			return 0;
		else
			return ERROR;
	}
	else
	{
		bool isSame;
		for (int i = 0; i < usedArrayLength - subStrLength + 1; i++)
		{
			if (arr[i] == subStr[0]) {
				isSame = true;
				for (int j = 0; j < subStrLength; j++)
				{
					if (arr[i + j] != subStr[j])
					{
						return ERROR;
					}
				}
				if (isSame)
					return i;
			}
		}
		return ERROR;
	}
}
const int MyString::find(const MyString subStr) const
{
	return this->find(subStr.arr);
}
const int MyString::find(int pos, const char* subStr) const
{
	if (pos == 0)
		return this->find(subStr);
	else
	{
		MyString myString = this->substr(pos, usedArrayLength);
		return myString.find(subStr) + pos;
	}
}
const int MyString::find(int pos, const MyString subStr) const
{
	if (pos == 0)
	{
		return this->find(subStr);
	}
	else
	{
		return this->find(pos, subStr.arr);
	}
}
const MyString MyString::operator+=(const char* str2nd) const
{
	return this->operator+(str2nd);
}
const bool MyString::operator <(const MyString& str)
{
	if (this->compare(str) > 0)
		return true;
	else
		return false;
}
const bool MyString::operator >(const MyString& str)
{
	if (this->compare(str) < 0)
		return true;
	else
		return false;
}
const bool MyString::operator <=(const MyString& str)
{
	if (this->compare(str) >= 0)
		return true;
	else
		return false;
}
const bool MyString::operator >=(const MyString& str)
{
	if (this->compare(str) <= 0)
		return true;
	else
		return false;
}
const bool MyString::operator <(const char* subStr)
{
	if (this->compare(subStr) > 0)
		return true;
	else
		return false;
}
const bool MyString::operator >(const char* subStr)
{
	if (this->compare(subStr) < 0)
		return true;
	else
		return false;
}
const bool MyString::operator <=(const char* subStr)
{
	if (this->compare(subStr) >= 0)
		return true;
	else
		return false;
}
const bool MyString::operator >=(const char* subStr)
{
	if (this->compare(subStr) <= 0)
		return true;
	else
		return false;
}
const MyString MyString::substr(int pos, int cnt) const
{
	MyString myString;
	char* tmp;
	if (pos >= usedArrayLength || cnt <= 0) // 에러
	{
		tmp = new char[1];
		tmp[0] = '\0';
		MyString myString(tmp);
		return myString;
	}
	else if (pos + cnt > usedArrayLength) //문자열끝까지
	{
		tmp = new char[usedArrayLength - pos];
		for (int i = pos; i < usedArrayLength; i++)
			tmp[i - pos] = arr[i];
		myString = MyString(tmp);
		delete[] tmp;
		return myString;
	}
	else
	{
		tmp = new char[cnt];
		for (int i = 0; i < cnt; i++)
			tmp[i] = arr[i + pos];
		myString = MyString(tmp);
		delete[] tmp;
		return myString;
	}
}
const MyString MyString::insert(int pos, char* subStr) const
{
	MyString myString;
	MyString tmpMyString = MyString(subStr);
	char* tmp;
	if (pos >= usedArrayLength) // 에러 '\0' null문자 반환
	{
		tmp = new char[1];
		tmp[0] = '\0';
		myString = MyString(tmp);
		delete[] tmp;
		return myString;
	}
	else if (pos == 0) // 처음에 삽입
	{
		myString = MyString(subStr) + *this;
		return myString;
	}
	else if (pos == usedArrayLength - 1) // 마지막에 삽입
	{
		myString = *this + MyString(subStr);
		return myString;
	}
	else
	{
		MyString newMyString(subStr); // 가운데 삽입
		myString = this->substr(0, pos) + newMyString + this->substr(pos, usedArrayLength);
		return myString;
	}
}
const MyString MyString::replace(int pos, int cnt, char* subStr) const
{
	int subStrLength = 0;
	while (subStr[subStrLength] != '\0')
		subStrLength++;
	char* tmp;
	MyString myString;
	if ((pos < 0 || pos >= usedArrayLength) || cnt < 0)
	{
		tmp = new char[1];
		tmp[0] = '\0';
		myString = MyString(tmp);
		delete[] tmp;
		return myString;
	}
	else if (cnt == 0)
	{
		return *this;
	}
	else if (pos + cnt >= usedArrayLength)
	{
		myString = this->substr(0, pos) + MyString(subStr);
		return myString;
	}
	else if (cnt > subStrLength)
	{
		myString = this->substr(0, pos) + MyString(subStr) + this->substr(pos + cnt, usedArrayLength);
		return myString;
	}
	else // cnt < subStrLength || cnt == subStrLength
	{
		for (int i = 0; i < cnt; i++)
			arr[i + pos] = subStr[i];
		return *this;
	}
}
const MyString MyString::erase(int pos, int cnt) const
{
	MyString myString;
	char* tmp;
	if (pos >= usedArrayLength || cnt < 0) // 에러
	{
		tmp = new char[1];
		tmp[0] = '\0';
		myString = MyString(tmp);
		delete[] tmp;
		return myString;
	}
	else if (cnt == 0) // 변화없음
	{
		return *this;
	}
	else if (pos == 0 && cnt >= usedArrayLength) // 모두삭제 반환값은 '\0'
	{
		tmp = new char[1];
		tmp[0] = '\0';
		myString = MyString(tmp);
		delete[] tmp;
		return myString;
	}
	else if (pos == 0) // 인덱스 0부터 cnt가 끝날 때 까지 삭제
	{
		return this->substr(cnt, usedArrayLength - cnt);
	}
	else if (pos + cnt >= usedArrayLength) // 인덱스 pos 부터 끝까지 삭제
	{
		return this->substr(0, pos);
	}
	else // pos부터 cnt까지
	{
		myString = this->substr(0, pos) + this->substr(pos + cnt, usedArrayLength);
		return myString;
	}
}
const char MyString::operator[](int pos) const
{
	if (pos >= usedArrayLength)
		return ' ';
	// 인덱스보다 큰경우 결과값을반환하는 프로그램을 실행했지만 프로그램실행이 중단되었음
	// 공백 문자열로 대체
	else
		return arr[pos];
}
const int stoi(const MyString& str, int pos, int base) // str[pos]로부터의 숫자부분을 정수로 변환하여 return
{
	MyString myString;
	int sum = 0;
	if (str.length() == 0) // 길이가 0이면 입력값이 없는 경우
		return 0;
	else if ((pos < 0 || pos >= str.length()) || !(BINARY <= base && base <= HEX))
		return ERROR;
	else if (base <= DECIMAL) // 2 ~ 10
	{
		myString = str.substr(pos, str.length()); // pos 부터 끝까지
		int count = 0;
		for (int i = 0; i < myString.length(); i++)
		{
			if (!('0' <= myString[i] && myString[i] <= NUMBER[base - 1]))
				return stoi(myString.substr(0, count), 0, base);
			else
			{
				sum += myString[i] - '0'; // ascii char '0' = int 48
				if (i == myString.length() - 1)
					break;
				sum *= base;
			}
			count++;
		}
		return sum;
	}
	else // base > 10 // 11 ~ 16
	{
		myString = str.substr(pos, str.length()); // pos 부터 끝까지
		int count = 0;
		for (int i = 0; i < myString.length(); i++)
		{
			if ('0' <= myString[i] && myString[i] <= '9')
			{
				sum += myString[i] - '0';
			}
			else if ('a' <= myString[i] && myString[i] <= NUMBER[base - 1] - 'A' + 'a')
			{
				sum += myString[i] - 'a' + DECIMAL;
			}
			else if ('A' <= myString[i] && myString[i] <= NUMBER[base - 1])
			{
				sum += myString[i] - 'A' + DECIMAL;
			}
			else
				return stoi(myString.substr(0, count), 0, base);
			if (i == myString.length() - 1)
				break;
			sum *= base;
			count++;
		}
		return sum;
	}
}
const int stoi(const MyString& str)
{
	return stoi(str, 0, 10);
}
