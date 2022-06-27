#include "Handler.h"

int main(int argc, char** argv) {
	Handler handler = Handler();
	if (argc == 2) {
		string src = argv[1];
		handler.run(src);
	}
	else {
		cout << "Error";
	}
	return 0;
}