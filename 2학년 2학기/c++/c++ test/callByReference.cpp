#include <iostream>
using namespace std;
void getNumbers(int& input1, int& input2);
void swapValues(int& variables1, int& variables2);
void showResults(int output1, int output2);
int main()
{
	int firstNum, secondNum;
	getNumbers(firstNum, secondNum);
	swapValues(firstNum, secondNum);
	showResults(firstNum, secondNum);
	return 0;
}
void getNumbers(int& input1, int& input2)
{
	cout << "enter two integers : ";
	cin >> input1
		>> input2;
}
void swapValues(int& variables1, int& variables2)
{
	int temp;

	temp = variables1;
	variables1 = variables2;
	variables2 = temp;
}
void showResults(int output1, int output2)
{
	cout << "in reverse order the numbers are "
		<< output1 << " " << output2 << endl;
}