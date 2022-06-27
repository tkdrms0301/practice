#include <iostream>
#include <cassert>
#include "Account.h"
using namespace std;
int Account::accountCNT = 0;
Account::Account(double bal) :balance(bal)
{
	if (bal < 0.0)
	{
		cout << "À½¼ö ÀÜ¾× ºÒ°¡´É. ÇÁ·Î±×·¥ Á¾·á.";
		assert(false);
	}
	accountCNT++;
	accNumber = base + accountCNT;
	cout << "°èÁÂ_#" << accNumber << "»ý¼º. " << endl;
	cout << "ÀÜ¾× $" << balance << endl << endl;
}
Account::~Account()
{
	cout << "°èÁÂ_#" << accNumber << "Closed." << endl;
	cout << "$" << balance << "¸¦ °í°´¿¡°Ô ¼Û±Ý." << endl << endl;
}
void Account::print() const
{
	cout << "°èÁÂ_# " << accNumber << endl;
	cout << "Æ®·£Àè¼Ç - ÀÜ¾× È®ÀÎ" << endl;
	cout << "ÀÜ¾×: $" << balance << endl << endl;
}
void Account::deposit(double amount)
{
	if (amount > 0.0)
	{
		balance += amount;
		cout << "°èÁÂ_# " << accNumber << endl;
		cout << "Æ®·£Àè¼Ç - ÀÔ±Ý $" << amount << endl;
		cout << "º¯°æµÈ ÀÜ¾×: $" << balance << endl << endl;
	}
	else
		cout << "Æ®·£Àè¼Ç Áß´Ü." << endl;
}
// ¼³Á¤ÀÚ ¸â¹ö ÇÔ¼ö
void Account::withdraw(double amount)
{
	if (amount > balance) amount = balance;
	balance -= amount;
	cout << "°èÁÂ_# " << accNumber << endl;
	cout << "Æ®·£Àè¼Ç - ÀÎÃâ: $" << amount << endl;
	cout << "º¯°æµÈ ÀÜ¾×: $" << balance << endl << endl;
}