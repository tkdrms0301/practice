#include "MyList.h"

template <typename T>
MyList<T>::MyList()
{
	dummyNode = new Node<T>();
	head = dummyNode;
	tail = dummyNode;
}

template <typename T>
Node<T>* MyList<T>::remove(Node<T>* removeNode)
{
	Node<T>* curNode = removeNode;
	Node<T>* prevNode = nullptr;
	if (isEmpty()) {
		return nullptr;
	}
	else if (curNode == nullptr)
	{
		return nullptr;
	}
	else if (curNode == getTail())
	{
		prevNode = getHead();
		while (prevNode != nullptr)
		{
			if (prevNode->nextNode == curNode)
			{
				break;
			}
			else
			{
				prevNode = prevNode->nextNode;
			}
		}
		prevNode->nextNode = nullptr;
		tail = prevNode;
		return curNode;
	}
	else
	{
		prevNode = getHead();
		while (prevNode != nullptr)
		{
			if (prevNode->nextNode == curNode)
			{
				break;
			}
			else
			{
				prevNode = prevNode->nextNode;
			}
		}
		//prevNode->nextNode = prevNode->nextNode->nextNode;
		prevNode->nextNode = curNode->nextNode;
		return curNode;
	}
}

template <typename T>
MyList<T>::~MyList()
{
	Node<T>* prevNode = nullptr;
	while (head != nullptr) 
	{
		prevNode = head;
		head = head->nextNode;
		delete prevNode;
	}
}

template <typename T>
Node<T>* MyList<T>::getHead() const
{
	return head;
}

template <typename T>
Node<T>* MyList<T>::getTail() const
{
	return tail;
}

template <typename T>
const bool MyList<T>::isEmpty() const
{
	return head == tail;
}

template <typename T>
Node<T>* MyList<T>::add(T* person)
{
	Node<T>* node = new Node<T>();
	node->person = person;
	if (isEmpty())
	{
		dummyNode->nextNode = node;
		tail = node;
		return node;
	}
	else {
		Node<T>* prevNode = getHead();
		Node<T>* curNode = getHead()->nextNode;
		while (curNode != nullptr)
		{
			if (node->person->getDeposit() > curNode->person->getDeposit())
			{ //newNode < curNode
				node->nextNode = prevNode->nextNode;
				prevNode->nextNode = node;
				return node;
			}
			else if (node->person->getDeposit() == curNode->person->getDeposit())
			{ //newNode == curNode
				if (node->person->getName().compare(curNode->person->getName()) < 0)
				{
					node->nextNode = prevNode->nextNode;
					prevNode->nextNode = node;
					return node;
				}
				else if (node->person->getName().compare(curNode->person->getName()) == 0)
				{
					if (node->person->getPhoneNumber().compare(curNode->person->getPhoneNumber()) < 0)
					{
						node->nextNode = prevNode->nextNode;
						prevNode->nextNode = node;
						return node;
					}
				}
			}
			prevNode = prevNode->nextNode;
			curNode = curNode->nextNode; // nullptr이 되는경우 while 끝
		}
		node->nextNode = prevNode->nextNode;
		prevNode->nextNode = node;
		tail = node;
		return node;
	}
}

template <typename T>
Node<T>* MyList<T>::searchPhoneNumber(const string phoneNumber)
{
	Node<T>* curNode = nullptr;
	if (isEmpty()) 
	{
		return nullptr;
	}
	else 
	{
		curNode = getHead()->nextNode;
		while (curNode != nullptr)
		{
			if (curNode->person->getPhoneNumber().compare(phoneNumber) == 0)
			{
				return curNode;
			}
			curNode = curNode->nextNode;
		}
		return nullptr;
	}
}

template <typename T>
Node<T>* MyList<T>::searchKey(const string key)
{
	Node<T>* curNode = nullptr;
	if (isEmpty())
	{
		return curNode;
	}
	else {
		curNode = getHead()->nextNode;
		while (curNode != nullptr)
		{
			if (curNode->person->getKey().compare(key) == 0)
			{
				return curNode;
			}
			curNode = curNode->nextNode;
		}
		return curNode;
	}
}

template <typename T>
Node<T>* MyList<T>::removeByKey(const string key)
{
	Node<T>* curNode = searchKey(key);
	return remove(curNode);
}

template <typename T>
Node<T>* MyList<T>::removeByPhoneNumber(const string phoneNumber)
{
	Node<T>* curNode = searchPhoneNumber(phoneNumber);
	return remove(curNode);
}