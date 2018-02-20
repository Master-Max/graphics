#include <iostream>
//#include <sstream>
#include <fstream>
#include <string>
#include <vector>

//using namespace std;
//using namespace ios;

int main(int argc, char** argv)
//int argc;
//char **argv;
{
	std::string Filename = argv[1];

	std::ifstream input;

	input.open(Filename, std::ifstream::in);
	//std::ifstream input(Filename, std::ios::binary | ios::in);
	//std::ifstream input(Filename);
	std::string line;
	std::vector<std::string> lines;

	while(std::getline(input, line))
	{
		lines.push_back(line);
	}

	for(auto s : lines)
	{
		cout << s;
	}
	//return 0;
}
