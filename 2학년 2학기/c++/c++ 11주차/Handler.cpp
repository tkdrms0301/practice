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

void Handler::input(const string file) // 입력마다 ' '로 구분, 마지막 입력은 '\n', 중복된 구분자 제외 ('  ', '\n ')
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
			data = split(line); // 문자열 " "기준 자르기
			if (data[0].compare("교직원") == 0) 
			{
				myList.add(new Professor(data[1], data[2], data[4], stoi(data[6]), data[3], stoi(data[5])));
			}
			else if (data[0].compare("학생") == 0) 
			{
				myList.add(new Student(data[1], data[2], data[4], stoi(data[5]), data[3]));
			}
			else if (data[0].compare("일반") == 0)
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
		cout << "잘못된 파일명" << endl;
	}
	is.close();
}

void Handler::output(const string file)
{
	char ch;
	while(true)
	{
		cout << "변경 사항을 " << file << "에 저장하려면 W / w, " << file << "을 이전 상태로 유지하려면 C / c : ";
		cin >> ch;
		if (ch == 'W' || ch == 'w')
		{
			ofstream os;
			os.open(file);
			if (os.is_open()) 
			{
				cout << ">>> 변경 사항 저장 후 종료 <<<" << endl;
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
			cout << ">>> 변경 사항 저장하지 않고 종료 <<<" << endl;
			return;
		}
		else
		{
			cout << ">>> 잘못된 입력 <<<" << endl;
		}
	}
}

void Handler::depositProgram(string file) {
	cout << "*************** 발전기금 관리 프로그램 ***************" << endl;
	cout << "--------- 기탁현황---------" << endl;

	displayPerson();

	char button;
	string keyOrPhoneNumber;
	while (true) {
		cout << "[1] 전체 조회 / [2] 신규 기탁자 등록 / [3] 기탁정보 변경 / [4] 기탁자 삭제 / [5] 종료 : ";
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
			cout << "변경을 원하는 사번, 학번, 기탁자 번호 또는 전화번호(XXX-XXXX-XXXX)를 입력하세요 : ";
			cin >> keyOrPhoneNumber;
			updatePerson(keyOrPhoneNumber);
			break;
		case DELETE:
			cout << "삭제를 원하는 사번이나 학번 또는 전화번호(XXX-XXXX-XXXX)를 입력하세요 : ";
			cin >> keyOrPhoneNumber;
			deletePerson(keyOrPhoneNumber);
			break;
		case EXIT:
			output(file);
			return;
		default:
			cout << "잘못된 번호 입력" << endl;
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
	cout << ">>> 기탁자 정보 형식 <<<" << endl;
	cout << "학생 학번 이름 학과 전화번호 기탁액" << endl;
	cout << "교직원 사번 이름 부서 내선번호 전화번호 기탁액" << endl;
	cout << "일반 이름 전화번호 기탁액" << endl;
	cout << "신규 기탁자 정보를 입력하세요 : ";
	cin.ignore();
	getline(cin, line);
	data = split(line); // 문자열 " "기준 자르기
	if (data[0].compare("교직원") == 0) {
		if (myList.searchKey(data[1]) == nullptr && myList.searchPhoneNumber(data[5]) == nullptr) {
			depositor = myList.add(new Professor(data[1], data[2], data[5], stoi(data[6]), data[3], stoi(data[4])));
			cout << "< 신규 > ";
			depositor->person->showInfo();
		}
		else if (myList.searchKey(data[1])) {
			cout << "!!! 동일한 키가 존재하여 신규 등록 불가 !!!" << endl;
		}
		else {
			cout << "!!! 동일한 전화번호가 존재하여 신규 등록 불가 !!!" << endl;
		}
	}
	else if (data[0].compare("학생") == 0)
	{
		if (myList.searchKey(data[1]) == nullptr && myList.searchPhoneNumber(data[4]) == nullptr)
		{
			depositor = myList.add(new Student(data[1], data[2], data[4], stoi(data[5]), data[3]));
			cout << "< 신규 > ";
			depositor->person->showInfo();
		}
		else if (myList.searchKey(data[1]))
		{
			cout << "!!! 동일한 키가 존재하여 신규 등록 불가 !!!" << endl;
		}
		else
		{
			cout << "!!! 동일한 전화번호가 존재하여 신규 등록 불가 !!!" << endl;
		}
	}
	else if (data[0].compare("일반") == 0) {
		if (myList.searchPhoneNumber(data[2]) == nullptr)
		{
			depositor = myList.add(new Normal(data[1], data[2], stoi(data[3])));
			cout << "< 신규 > "; 
			depositor->person->showInfo();
		}
		else
		{
			cout << "!!! 동일한 전화번호가 존재하여 신규 등록 불가 !!!" << endl;
		}
	}
	else {
		cout << "잘못된 입력" << endl;
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
		cout << "입력한 사번, 학번, 기탁자 번호 또는 전화번호(XXX-XXXX-XXXX)의 길이가 너무 짧습니다" << endl;
		return;
	}else if (keyOrPhoneNumber.at(PHONENUMBER_HYPHEN_FIRST) == '-' && keyOrPhoneNumber.at(PHONENUMBER_HYPHEN_SECOND) == '-')
	{
		curNode = myList.searchPhoneNumber(keyOrPhoneNumber);
		if (curNode == nullptr)
		{
			cout << keyOrPhoneNumber << "를 전화번호로 가진 사람이 없음" << endl;
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
			cout << keyOrPhoneNumber << "를 키로 가진 사람이 없음" << endl;
		}
		else {
			curNode = myList.removeByKey(keyOrPhoneNumber);
		}
	}
	if (curNode != nullptr)
	{
		cout << "추가 기탁액을 입력하세요 : ";
		cin >> addDeposit;
		curNode->person->setDeposit(curNode->person->getDeposit() + addDeposit);
		curNode = myList.add(curNode->person);
		cout << "< 변경 > ";
		curNode->person->showInfo();
	}
}

void Handler::deletePerson(const string keyOrPhoneNumber)
{
	Node<Depositor>* removeNode = nullptr;
	if (keyOrPhoneNumber.length() < 6)
	{
		cout << "입력한 사번, 학번, 기탁자 번호 또는 전화번호(XXX-XXXX-XXXX)의 길이가 너무 짧습니다" << endl;
		return;
	}
	else if (keyOrPhoneNumber.at(PHONENUMBER_HYPHEN_FIRST) == '-' && keyOrPhoneNumber.at(PHONENUMBER_HYPHEN_SECOND) == '-')
	{
		removeNode = myList.removeByPhoneNumber(keyOrPhoneNumber);
		if (removeNode == nullptr)
		{
			cout << keyOrPhoneNumber << "를 전화번호로 가진 사람이 없음" << endl;
		}
	}
	else
	{
		removeNode = myList.removeByKey(keyOrPhoneNumber);
		if (removeNode == nullptr)
		{
			cout << keyOrPhoneNumber << "를 키로 가진 사람이 없음" << endl;
		}
	}
	if (removeNode != nullptr)
	{
		cout << "< 삭제 > ";
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
