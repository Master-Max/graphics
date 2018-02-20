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


  /* THIS WORKS
	std::ifstream ifs;

	ifs.open(file.c_str(), std::ifstream::in);
  */

	//char c = ifs.get();
  std::string line;
  std::vector<std::string> lines;
  std::vector<std::string> header;
  std::vector<struct vertex> Vertex;
  std::vector<struct edge> Edge;

  float x,y,z;
  int n,a,b,c;

/*
  while(true){

    if(infile >> x >> y >> z){
      std::cout << x << " " << y << " " << z << " \n";
    }
    //std::cout << "hi\n";
  }*/

  //std::cout << "it didn't work\n";

  while(std::getline(infile, line)){
    std::istringstream iss(line);

    //std::string type;
    //in >> type;
    if(line.find(".") != std::string::npos){
      if(iss >> x >> y >> z){
        std::cout << "V: " << x << " " << y << " " << z << "\n";
      }
    }else if(iss >> n >> a >> b >> c){
      std::cout << "E: " << a << " " << b << " " << c << "\n";
    }

/*
    //if((iss >> x >> y >> z) && !(iss >> n >> a >> b >> c)){
    if((iss >> x >> y >> z) && (line.find(".") != std::string::npos)){
      //iss >> x >> y >> z;
      std::cout << "V: " << x << " " << y << " " << z << "\n";
    }//else if((iss >> n >> a >> b >> c) && !(line.find(".") != std::string::npos)){
    if(iss >> n >> a >> b >> c){
      //iss >> n >> a >> b >> c;
      std::cout << "E: " << a << " " << b << " " << c << "\n";
    }
  //  else{
      //std::cout << "Womp: " << line << "\n";
//    }

    /*
    if(iss >> n >> a >> b >> c){
      iss >> n >> a >> b >> c;
      std::cout << "E: " << a << " " << b << " " << c << " \n";
    }*/
    /*
    if(iss >> x >> y >> z){
      iss >> x >> y >> z;
      std::cout << "V: " << x << " " << y << " " << z << " \n";
    }else{
      //std::cout << "Header: " << line << " \n";
    }*/

    /*
    if(!(iss >> x >> y >> z)){
      std::cout << "no Floats\n";
    }else{
      iss >> x >> y >> z;
      std::cout << x << " " << y << " " << z << " \n";
    }*/
  }

  std::cout << "it didn't work\n";

/*
  while(std::getline(ifs, line))
  {
    if(line >> X >> Y >> Z){
      Vertex.push_back(vertex(X,Y,Z));
    }
/*
    else if(line >> n >> a >> b >> c){
      Edge.push_back(edge(a,b,c));
    }*/
    //lines.push_back(line);
//  }

  //std::cout << Vertex[0].x << Vertex[0].y << Vertex[0].z << endl;
  //std::cout << Edge[0].x << Edge[0].y << Edge[0].z << endl;

  //std::string delimiter = " ";


/*
  for(int i = 0; i < lines.size(); i++)
  {
    std::istringstream in()
  }*/


  /*
  for(int i = 0; i < lines.size(); i++){
    std::cout << lines[i] << std::endl;
  }

  std::cout << lines[0] << std::endl;
  std::cout << lines[9] << std::endl;
  std::cout << lines[10] << std::endl;
  std::cout << lines[517] << std::endl;
  */

  infile.close();
  //ifs.close();

	return 0;
}
