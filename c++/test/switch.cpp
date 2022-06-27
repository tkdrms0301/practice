#include <iostream>
using namespace std;

int main()
{
	int vehicleClass;
	double toll;
	cout << "vehicle Class : ";
	cin >> vehicleClass;
	switch (vehicleClass)
	{
	case 1:
		cout << "passanger car";
		toll = 0.50;
		break;
	case 2:
		cout << "bus";
		toll = 1.50;
		break;
	case 3:
		cout << "truck";
		toll = 2.00;
		break;
	default:
		cout << "unknwon";
		break;
	}
	return 0;
}