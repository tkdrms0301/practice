// infile.txt로부터 숫자 3개를 읽어 합을 계산하고
// 결과를 output.txt 에 기록
#include <fstream>
using std::ifstream;
using std::ofstream;
using std::endl;
int main()
{
	ifstream inStream;
	ofstream outStream;
	inStream.open("infile.txt");
	outStream.open("outfile.txt");
	int first, second, third;
	inStream >> first >> second >> third;
	outStream << "The sum of the first 3\n"
		<< "numbers in infile.txt\n"
		<< "is " << (first + second + third)
		<< endl;
	inStream.close();
	outStream.close();
	return 0;
}
