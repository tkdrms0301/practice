#include <iostream>
using namespace std;
void showVolume(int length, int width = 1, int height = 1);
int main()
{
	showVolume(4, 6, 2);
	showVolume(4, 6);
	showVolume(4);
	return 0;
}
void showVolume(int length, int width, int height)
{
	cout << "Volume of a box with \n"
		<< "Length = " << length << ", width = " << width
		<< " and height = " << height << " is " << length * width * height << endl;
}