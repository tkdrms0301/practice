#include <iostream>
#include <iomanip>
using namespace std;
// 최종 가격을 return하는 함수의 함수 원형
int totalCost(int numberParameter, int priceParameter, int saleRate);
int main()
{
	int price, saleRate, number, bill;
	cout << "개당 가격 : ";
	cin >> price;
	cout << "구매 갯수 : ";
	cin >> number;
	cout << "할인율(% 기준) : ";
	cin >> saleRate;
	bill = totalCost(number, price, saleRate);
	cout << "가격 " << price << "인 상품 " << number << "개를 "
		<< saleRate << "의 할인율로 구매하면 총 가격은 "
		<< bill << endl;
	return 0;
}

int totalCost(int numberParameter, int priceParameter, int saleRate)
{
	int subtotal;
	subtotal = priceParameter * numberParameter;
	return (subtotal * (100 - saleRate) / 100);
}
