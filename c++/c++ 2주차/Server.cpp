#include <iostream>
#include "Server.h"
using namespace std;

int Server::turn = 0;
int Server::lastServed = 0;
bool Server::nowOpen = true;
Server::Server(char letterName)
	:name(letterName) {}
int Server::getTurn() {
	turn++;
	return turn;
}
bool Server::stillOpen() {
	return nowOpen;
}
void Server::serveOne()
{
	if (nowOpen && lastServed < turn)
	{
		lastServed++;
		cout << "Server " << name
			<< " now serving " << lastServed << endl;
	}
	if (lastServed >= turn) //Everyone served
		nowOpen = false;
}