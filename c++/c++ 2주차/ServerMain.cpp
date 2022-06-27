#include <iostream>
#include "Server.h"
using namespace std;
int main()
{
	Server s1('A'), s2('B');
	int num, cnt;
	do {
		cout << "How many in your group? ";
		cin >> num;
		cout << "Your turns are: ";
		for (cnt = 0; cnt < num; cnt++)
			cout << Server::getTurn() << ' ';
		cout << endl;
		s1.serveOne(); s2.serveOne();
	} while (Server::stillOpen());
	cout << "Now closing service.\n";
	return 0;
}