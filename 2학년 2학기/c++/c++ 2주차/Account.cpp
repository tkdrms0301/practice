#include <iostream>
#include <cassert>
#include "Account.h"
using namespace std;
int Account::accountCNT = 0;
Account::Account(double bal) :balance(bal)
{
	if (bal < 0.0)
	{
		cout << "���� �ܾ� �Ұ���. ���α׷� ����.";
		assert(false);
	}
	accountCNT++;
	accNumber = base + accountCNT;
	cout << "����_#" << accNumber << "����. " << endl;
	cout << "�ܾ� $" << balance << endl << endl;
}
Account::~Account()
{
	cout << "����_#" << accNumber << "Closed." << endl;
	cout << "$" << balance << "�� ������ �۱�." << endl << endl;
}
void Account::print() const
{
	cout << "����_# " << accNumber << endl;
	cout << "Ʈ����� - �ܾ� Ȯ��" << endl;
	cout << "�ܾ�: $" << balance << endl << endl;
}
void Account::deposit(double amount)
{
	if (amount > 0.0)
	{
		balance += amount;
		cout << "����_# " << accNumber << endl;
		cout << "Ʈ����� - �Ա� $" << amount << endl;
		cout << "����� �ܾ�: $" << balance << endl << endl;
	}
	else
		cout << "Ʈ����� �ߴ�." << endl;
}
// ������ ��� �Լ�
void Account::withdraw(double amount)
{
	if (amount > balance) amount = balance;
	balance -= amount;
	cout << "����_# " << accNumber << endl;
	cout << "Ʈ����� - ����: $" << amount << endl;
	cout << "����� �ܾ�: $" << balance << endl << endl;
}