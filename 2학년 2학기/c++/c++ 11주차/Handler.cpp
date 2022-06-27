#include "Handler.h"
#include "Depositor.h"
#include "Professor.h"
#include "Student.h"
#include "Normal.h"
#include "MyList.cpp"

const int Handler::PROFESSOR = 1;
const int Handler::STUDENT = 2;
const int Handler::NORMAL = 3;

const char Handler::SELECT_ALL = '1';
const char Handler::INSERT = '2';
const char Handler::UPDATE = '3';
const char Handler::DELETE = '4';
const char Handler::EXIT = '5';

const int Handler::STRING_SIZE = 10;

const int Handler::PHONENUMBER_HYPHEN_FIRST = 3;
const int Handler::PHONENUMBER_HYPHEN_SECOND = 8;

Handler::Handler()
{
}

Handler::~Handler()
{
}

void Handler::run(const string file)
{
	input(file);
	depositProgram(file);
}

void Handler::input(const string file) // �Է¸��� ' '�� ����, ������ �Է��� '\n', �ߺ��� ������ ���� ('  ', '\n ')
{
	string* data = nullptr;
	ifstream is;
	is.open(file, ios::in);
	if (is.is_open()) 
	{
		while (!is.eof()) 
		{
			string line;
			std::getline(is, line);
			data = split(line); // ���ڿ� " "���� �ڸ���
			if (data[0].compare("������") == 0) 
			{
				myList.add(new Professor(data[1], data[2], data[4], stoi(data[6]), data[3], stoi(data[5])));
			}
			else if (data[0].compare("�л�") == 0) 
			{
				myList.add(new Student(data[1], data[2], data[4], stoi(data[5]), data[3]));
			}
			else if (data[0].compare("�Ϲ�") == 0)
			{
				myList.add(new Normal(data[2], data[3], stoi(data[4])));
			}
			delete[] data;
			data = new string[STRING_SIZE];
		}
		delete[] data;
	}
	else 
	{
		cout << "�߸��� ���ϸ�" << endl;
	}
	is.close();
}

void Handler::output(const string file)
{
	char ch;
	while(true)
	{
		cout << "���� ������ " << file << "�� �����Ϸ��� W / w, " << file << "�� ���� ���·� �����Ϸ��� C / c : ";
		cin >> ch;
		if (ch == 'W' || ch == 'w')
		{
			ofstream os;
			os.open(file);
			if (os.is_open()) 
			{
				cout << ">>> ���� ���� ���� �� ���� <<<" << endl;
				Node<Depositor>* curNode = myList.getHead()->nextNode;
				while (curNode != nullptr) 
				{
					string line = curNode->person->tostring();
					curNode = curNode->nextNode;
					cout << line;
					os.write(line.c_str(), line.size());
				}
			}
			os.close();
			return;
		}
		else if (ch == 'c' || ch == 'C') 
		{
			cout << ">>> ���� ���� �������� �ʰ� ���� <<<" << endl;
			return;
		}
		else
		{
			cout << ">>> �߸��� �Է� <<<" << endl;
		}
	}
}

void Handler::depositProgram(string file) {
	cout << "*************** ������� ���� ���α׷� ***************" << endl;
	cout << "--------- ��Ź��Ȳ---------" << endl;

	displayPerson();

	char button;
	string keyOrPhoneNumber;
	while (true) {
		cout << "[1] ��ü ��ȸ / [2] �ű� ��Ź�� ��� / [3] ��Ź���� ���� / [4] ��Ź�� ���� / [5] ���� : ";
		cin >> button;
		switch (button)
		{
		case SELECT_ALL:
			displayPerson();
			break;
		case INSERT:
			insertPerson();
			break;
		case UPDATE:
			cout << "������ ���ϴ� ���, �й�, ��Ź�� ��ȣ �Ǵ� ��ȭ��ȣ(XXX-XXXX-XXXX)�� �Է��ϼ��� : ";
			cin >> keyOrPhoneNumber;
			updatePerson(keyOrPhoneNumber);
			break;
		case DELETE:
			cout << "������ ���ϴ� ����̳� �й� �Ǵ� ��ȭ��ȣ(XXX-XXXX-XXXX)�� �Է��ϼ��� : ";
			cin >> keyOrPhoneNumber;
			deletePerson(keyOrPhoneNumber);
			break;
		case EXIT:
			output(file);
			return;
		default:
			cout << "�߸��� ��ȣ �Է�" << endl;
			break;
		}
		cout << endl;
	}
}

void Handler::displayPerson()
{
	Node<Depositor>* curNode = myList.getHead()->nextNode;
	while (curNode != nullptr) 
	{
		curNode->person->showInfo();
		curNode = curNode->nextNode;
	}
}

void Handler::insertPerson()
{
	string* data = new string[STRING_SIZE];
	string line;
	Node<Depositor>* depositor = nullptr;
	cout << ">>> ��Ź�� ���� ���� <<<" << endl;
	cout << "�л� �й� �̸� �а� ��ȭ��ȣ ��Ź��" << endl;
	cout << "������ ��� �̸� �μ� ������ȣ ��ȭ��ȣ ��Ź��" << endl;
	cout << "�Ϲ� �̸� ��ȭ��ȣ ��Ź��" << endl;
	cout << "�ű� ��Ź�� ������ �Է��ϼ��� : ";
	cin.ignore();
	getline(cin, line);
	data = split(line); // ���ڿ� " "���� �ڸ���
	if (data[0].compare("������") == 0) {
		if (myList.searchKey(data[1]) == nullptr && myList.searchPhoneNumber(data[5]) == nullptr) {
			depositor = myList.add(new Professor(data[1], data[2], data[5], stoi(data[6]), data[3], stoi(data[4])));
			cout << "< �ű� > ";
			depositor->person->showInfo();
		}
		else if (myList.searchKey(data[1])) {
			cout << "!!! ������ Ű�� �����Ͽ� �ű� ��� �Ұ� !!!" << endl;
		}
		else {
			cout << "!!! ������ ��ȭ��ȣ�� �����Ͽ� �ű� ��� �Ұ� !!!" << endl;
		}
	}
	else if (data[0].compare("�л�") == 0)
	{
		if (myList.searchKey(data[1]) == nullptr && myList.searchPhoneNumber(data[4]) == nullptr)
		{
			depositor = myList.add(new Student(data[1], data[2], data[4], stoi(data[5]), data[3]));
			cout << "< �ű� > ";
			depositor->person->showInfo();
		}
		else if (myList.searchKey(data[1]))
		{
			cout << "!!! ������ Ű�� �����Ͽ� �ű� ��� �Ұ� !!!" << endl;
		}
		else
		{
			cout << "!!! ������ ��ȭ��ȣ�� �����Ͽ� �ű� ��� �Ұ� !!!" << endl;
		}
	}
	else if (data[0].compare("�Ϲ�") == 0) {
		if (myList.searchPhoneNumber(data[2]) == nullptr)
		{
			depositor = myList.add(new Normal(data[1], data[2], stoi(data[3])));
			cout << "< �ű� > "; 
			depositor->person->showInfo();
		}
		else
		{
			cout << "!!! ������ ��ȭ��ȣ�� �����Ͽ� �ű� ��� �Ұ� !!!" << endl;
		}
	}
	else {
		cout << "�߸��� �Է�" << endl;
		return;
	}
	delete[] data;
}

void Handler::updatePerson(const string keyOrPhoneNumber)
{
	Node<Depositor>* curNode = nullptr;
	int addDeposit;
	if(keyOrPhoneNumber.length() < 6)
	{
		cout << "�Է��� ���, �й�, ��Ź�� ��ȣ �Ǵ� ��ȭ��ȣ(XXX-XXXX-XXXX)�� ���̰� �ʹ� ª���ϴ�" << endl;
		return;
	}else if (keyOrPhoneNumber.at(PHONENUMBER_HYPHEN_FIRST) == '-' && keyOrPhoneNumber.at(PHONENUMBER_HYPHEN_SECOND) == '-')
	{
		curNode = myList.searchPhoneNumber(keyOrPhoneNumber);
		if (curNode == nullptr)
		{
			cout << keyOrPhoneNumber << "�� ��ȭ��ȣ�� ���� ����� ����" << endl;
		}
		else {
			curNode = myList.removeByPhoneNumber(keyOrPhoneNumber);
		}
	}
	else
	{
		curNode = myList.searchKey(keyOrPhoneNumber);
		if (curNode == nullptr) 
		{
			cout << keyOrPhoneNumber << "�� Ű�� ���� ����� ����" << endl;
		}
		else {
			curNode = myList.removeByKey(keyOrPhoneNumber);
		}
	}
	if (curNode != nullptr)
	{
		cout << "�߰� ��Ź���� �Է��ϼ��� : ";
		cin >> addDeposit;
		curNode->person->setDeposit(curNode->person->getDeposit() + addDeposit);
		curNode = myList.add(curNode->person);
		cout << "< ���� > ";
		curNode->person->showInfo();
	}
}

void Handler::deletePerson(const string keyOrPhoneNumber)
{
	Node<Depositor>* removeNode = nullptr;
	if (keyOrPhoneNumber.length() < 6)
	{
		cout << "�Է��� ���, �й�, ��Ź�� ��ȣ �Ǵ� ��ȭ��ȣ(XXX-XXXX-XXXX)�� ���̰� �ʹ� ª���ϴ�" << endl;
		return;
	}
	else if (keyOrPhoneNumber.at(PHONENUMBER_HYPHEN_FIRST) == '-' && keyOrPhoneNumber.at(PHONENUMBER_HYPHEN_SECOND) == '-')
	{
		removeNode = myList.removeByPhoneNumber(keyOrPhoneNumber);
		if (removeNode == nullptr)
		{
			cout << keyOrPhoneNumber << "�� ��ȭ��ȣ�� ���� ����� ����" << endl;
		}
	}
	else
	{
		removeNode = myList.removeByKey(keyOrPhoneNumber);
		if (removeNode == nullptr)
		{
			cout << keyOrPhoneNumber << "�� Ű�� ���� ����� ����" << endl;
		}
	}
	if (removeNode != nullptr)
	{
		cout << "< ���� > ";
		removeNode->person->showInfo();
		delete removeNode;
	}
}

string* Handler::split(const string line)
{
	string* data = new string[STRING_SIZE];
	string type;
	int dataSize = 0;
	int spacebar = 0;
	for (int i = 0; i < line.length(); i++) {
		if (line.at(i) == ' ') {
			type = line.substr(spacebar, i - spacebar);
			spacebar = i + 1;
			data[dataSize] = type;
			dataSize++;
		}
		else if (i == line.length() - 1) {
			type = line.substr(spacebar, i - spacebar + 1);
			spacebar = i + 1;
			data[dataSize] = type;
			dataSize++;
		}
	}
	return data;
}
