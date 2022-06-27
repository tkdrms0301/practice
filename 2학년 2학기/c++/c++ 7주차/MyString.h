#pragma once
#include <iostream>
using namespace std;
class MyString
{
	friend istream& operator >>(istream& is, MyString& str); // 엔터키까지 입력.  getchar() 사용 가능
	friend ostream& operator <<(ostream& os, const MyString& str);
private:
	char *arr;
	int arrayLength;
	int usedArrayLength;
	const static int FIRST_ARRAY_SIZE = 8;
	const static int ERROR = -1;
	void resize(int arrayLength);
	int compare(const MyString str);
	int compare(const char* subStr);
public:
	MyString(); // 초기배열길이 8
	MyString(const char* srcStr); 
	~MyString();
	MyString operator = (const MyString& srcStr); 
	int length() const;
	char at(int pos) const;
	bool empty() const;
	const MyString operator + (const char* str2nd) const;
	const MyString operator + (const MyString& str2nd) const;
	const MyString append(const char* str2nd);
	const MyString append(const MyString& str2nd); 
	const bool operator ==(const MyString& str);
	const bool operator !=(const MyString& str);

	// 부분 문자열이 없으면 -1 return
	const int find(const char* subStr) const; 
	const int find(const MyString subStr) const; 
	const int find(int pos, const char* subStr) const;
	const int find(int pos, const MyString subStr) const;

	// 추가기능
	MyString(const MyString& srcStr);
	const MyString operator += (const char* str2nd) const;
	const bool operator <(const MyString& str);
	const bool operator >(const MyString& str);
	const bool operator <=(const MyString& str);
	const bool operator >=(const MyString& str);
	const bool operator <(const char* subStr);
	const bool operator >(const char* subStr);
	const bool operator <=(const char* subStr);
	const bool operator >=(const char* subStr);
	const MyString substr(int pos, int cnt) const;
	const MyString insert(int pos, char* subStr) const;
	const MyString replace(int pos, int cnt, char* subStr) const;
	const MyString erase(int pos, int cnt) const;
	const char operator [](int pos) const;
};
static char NUMBER[] = "0123456789ABCDEF";
static int ERROR = -1;
static int HEX = 16;
static int BINARY = 2;
static int DECIMAL = 10;
const int stoi(const MyString& str, int pos, int base);
const int stoi(const MyString& str);