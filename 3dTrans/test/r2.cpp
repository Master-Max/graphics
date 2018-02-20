#include <iostream>
#include <fstream>
#include <string>

int main(int argc, char *argv[]){
	if(argc != 2){
		std::cout << "Usage: r2.x [file]\n";
		return 1;
	}

	std::string file(argv[1]);
	//char* file = argv[1];


	std::ifstream ifs;

	//ifs.open("monkey.ply", std::ifstream::in);
	ifs.open(file.c_str(), std::ifstream::in);
	//ifs.open(file, std::ifstream::in);


	char c = ifs.get();

	while(ifs.good()){
		std::cout << c;
		c = ifs.get();
	}

	ifs.close();

	return 0;
}
