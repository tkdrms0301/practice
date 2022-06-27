#pragma once
#include <iostream>
#include "Depositor.h"
using namespace std;

template <typename T>
class Node {
public:
	T* person;
	Node<T>* nextNode;
	Node() 
	{
		person = nullptr;
		nextNode = nullptr;
	}
	Node(T* newPerson) 
	{
		person = newPerson;
		nextNode = nullptr;
	}
	~Node() {
	}
};

template <typename T>
class MyList 
{
private:
	Node<T>* dummyNode;
	Node<T>* head;
	Node<T>* tail;
	Node<T>* remove(Node<T>* removeNode);
public:
	MyList<T>();
	~MyList<T>();
	Node<T>* getHead() const;
	Node<T>* getTail() const;
	const bool isEmpty() const;
	Node<T>* add(T* person);
	Node<T>* searchPhoneNumber(const string phoneNumber); // professor, student, normal
	Node<T>* searchKey(const string key); // professor, student ¸¸
	Node<T>* removeByKey(const string key);
	Node<T>* removeByPhoneNumber(const string phoneNumber);
};