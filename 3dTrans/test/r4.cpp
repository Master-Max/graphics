#include <iostream>
#include <fstream>
#include <string>
#include <vector>
#include <sstream>

struct vertex{
  float x;
  float y;
  float z;
};

struct edge{
  int a;
  int b;
  int c;
};

int main(int argc, char *argv[]){
	if(argc != 2){
		std::cout << "Usage: r2.x [file]\n";
		return 1;
	}

	std::string file(argv[1]);

  std::ifstream infile(file.c_str(), std::ifstream::in);

  std::string line;
  std::vector<struct vertex> Vertex;
  std::vector<struct edge> Edge;

  float x,y,z;
  int n,a,b,c;

  while(std::getline(infile, line)){
    std::istringstream iss(line);

    if(line.find(".") != std::string::npos){
      if(iss >> x >> y >> z){
        struct vertex tmpV = {x,y,z};
        Vertex.push_back(tmpV);
        std::cout << "V: " << x << " " << y << " " << z << "\n";
      }
    }else if(iss >> n >> a >> b >> c){
      struct edge tmpE = {a,b,c};
      Edge.push_back(tmpE);
      std::cout << "E: " << a << " " << b << " " << c << "\n";
    }
  }

  std::cout << "\n\n";
  printf("Vertex[0] : %f %f %f\n", Vertex[0].x, Vertex[0].y, Vertex[0].z);
  printf("Edge[0] : %d %d %d\n", Edge[0].a, Edge[0].b, Edge[0].c);

  

  infile.close();

	return 0;
}
