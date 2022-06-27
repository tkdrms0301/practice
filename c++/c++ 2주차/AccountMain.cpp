#include <iostream>
#include "Account.h"
using namespace std;
int main()
{
	// 계좌 3개 생성
	Account acc1(2000);
	Account acc2(5000);
	Account acc3(1000);
	// 트랜잭션
	acc1.deposit(150);
	acc2.print();
	acc1.print();
	acc3.withdraw(800);
	acc1.withdraw(1000);
	acc2.deposit(120);
	return 0;
}