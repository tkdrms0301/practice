#include <iostream>
using namespace std;

int main()
{
	int i = 7; double x = 7.0; float y = 7.0;
	printf("%d%f%f\n", i, x, y);
	printf("%.2f\n", x);
	printf("%10.4f\n", y);
	char c = 'x';
	printf("%c\n", c);

	char str[16] = "Hello!";
	printf("%s\n", str);

	int l; double j; float k;
	scanf_s("%d%f%f\n", &l, &j, &k);

	char d;
	scanf_s("%c\n", &d);

	char string[10];
	scanf_s("%s\n", string, sizeof(string));
	return 0;
}