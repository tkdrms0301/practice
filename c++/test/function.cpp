#include <iostream>
#include <iomanip>
using namespace std;
// ���� ������ return�ϴ� �Լ��� �Լ� ����
int totalCost(int numberParameter, int priceParameter, int saleRate);
int main()
{
	int price, saleRate, number, bill;
	cout << "���� ���� : ";
	cin >> price;
	cout << "���� ���� : ";
	cin >> number;
	cout << "������(% ����) : ";
	cin >> saleRate;
	bill = totalCost(number, price, saleRate);
	cout << "���� " << price << "�� ��ǰ " << number << "���� "
		<< saleRate << "�� �������� �����ϸ� �� ������ "
		<< bill << endl;
	return 0;
}

int totalCost(int numberParameter, int priceParameter, int saleRate)
{
	int subtotal;
	subtotal = priceParameter * numberParameter;
	return (subtotal * (100 - saleRate) / 100);
}
