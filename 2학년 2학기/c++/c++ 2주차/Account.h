#pragma once
class Account
{
private:
	long accNumber;
	double balance;
	const static int base = 100000; // 정적 데이터 멤버
	static int accountCNT;
public:
	Account(double bal); // 생성자
	~Account(); // 소멸자
	void print() const; // 접근자
	void deposit(double amount); // 설정자
	void withdraw(double amount); // 설정자
};