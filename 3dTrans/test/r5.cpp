#include <iostream>
#include <fstream>
#include <sstream>
#include <string>
#include <vector>
#include <glm/glm.hpp>
#include <glm/ext.hpp>

struct Face{
  int a;
  int b;
  int c;
};

glm::vec4 Translate(glm::vec4 v, float Dx, float Dy, float Dz);

glm::vec4 Scale(glm::vec4 v, float Sx, float Sy, float Sz);

glm::vec4 Rotate(glm::vec4 v, char Plane, float R);

int main(int argc, char *argv[]){
	if(argc != 2){
		std::cout << "Usage: r2.x [file]\n";
		return 1;
	}

	std::string file(argv[1]);
  std::ifstream infile(file.c_str(), std::ifstream::in);
  std::string line;

  float x,y,z;
  int n,a,b,c;

  //NEW CODE USING ARRAYS of VEC4s
  glm::vec4 *vertices;
  int vertCnt = 0;
  struct Face *faces;
  int faceCnt = 0;

  int vertex_TOTAL;
  int face_TOTAL;

  int header = 1;

  std::string stA, stB;

  while(std::getline(infile, line)){
    std::istringstream iss(line);

    //std::cout << line << "\n";

    if(header){
      std::cout << line << "\n";
      if(line.find("element vertex") != std::string::npos){
        if(iss >> stA >> stB >> vertex_TOTAL){
          vertices = new glm::vec4[vertex_TOTAL];
          std::cout << "=== Created Vertex Array\n";
        }else{
          printf("Error Creating Verticies Array\n");
          return 0;
        }
      }else if(line.find("element face") != std::string::npos){
        if(iss >> stA >> stB >> face_TOTAL){
          faces = new struct Face[face_TOTAL];
          std::cout << "=== Created Face Array\n";
        }else{
          printf("Error Creating Faces Array\n");
          return 0;
        }
      }else if(line.find("end_header") != std::string::npos){
        printf("=== Ending Header\n");
        printf("VTotal : %d\n", vertex_TOTAL);
        printf("FTotal : %d\n", face_TOTAL);
        std::cout<< "================\n";
        printf("VCount : %d\n", vertCnt);
        printf("FCount : %d\n", faceCnt);
        header = 0;
      }
    }else if(!header){
      if(vertCnt < vertex_TOTAL){
        iss >> x >> y >> z;
        //printf("%d| V: %f, %f, %f\n",vertCnt+11,x,y,z);
        vertices[vertCnt] = glm::vec4(x,y,z,1.0f);
        vertCnt++;
      }else if(faceCnt < face_TOTAL){
        iss >> n >> a >> b >> c;
        struct Face tmpF = {a,b,c};
        faces[faceCnt] = tmpF;
        faceCnt++;
      }
    }
  }

  int v,f;
  v = vertex_TOTAL - 1;
  f = face_TOTAL - 1;

  std::cout << "\n\n";

  printf("vertex[0] : %f, %f, %f, %f\n",
   vertices[0][0],vertices[0][1],vertices[0][2],vertices[0][3]);
  printf("vertex[%d] : %f, %f, %f, %f\n",
   v,vertices[v][0],vertices[v][1],vertices[v][2],vertices[v][3]);

  printf("face[0] : %d, %d, %d\n",
  faces[0].a, faces[0].b, faces[0].c);
  printf("face[%d] : %d, %d, %d\n",
  f, faces[f].a, faces[f].b, faces[f].c);

  infile.close();

  //FILE SHOULD NOW BE LOADED
  char cmd, amt;

  std::cout << "\n====================\n";

  while(true){//GET USER INPUT AND MODIFY THE MODEL
    printf("\nvertex[000] : %f, %f, %f, %f\n",
     vertices[0][0],vertices[0][1],vertices[0][2],vertices[0][3]);
    printf("vertex[%d] : %f, %f, %f, %f\n\n",
     v,vertices[v][0],vertices[v][1],vertices[v][2],vertices[v][3]);
    if(std::cin >> cmd >> amt){
      switch(cmd){
        case 'r':
          switch(amt){
            case 'x':
              printf("Rotating by X\n");
              for(int i = 0; i < vertex_TOTAL; i++){
                vertices[i] = Rotate(vertices[i], 'x', 0.1f);
              }
              break;
            case 'y':
              printf("Rotating by Y\n");
              for(int i = 0; i < vertex_TOTAL; i++){
                vertices[i] = Rotate(vertices[i], 'y', 0.1f);
              }
              break;
            case 'z':
              printf("Rotating by Z\n");
              for(int i = 0; i < vertex_TOTAL; i++){
                vertices[i] = Rotate(vertices[i], 'z', 0.1f);
              }
              break;
            default :
              printf("Invalid Rotation Axis\n");
          }
          break;
        case 't':
          switch(amt){
            case 'u':
              printf("Translate Up\n");
              for(int i = 0; i < vertex_TOTAL; i++){
                vertices[i] = Translate(vertices[i],0.0f,0.0f,5.0f);
              }
              break;
            case 'd':
              printf("Translate Down\n");
              for(int i = 0; i < vertex_TOTAL; i++){
                vertices[i] = Translate(vertices[i],0.0f,0.0f,-5.0f);
              }
              break;
            case 'f':
              printf("Translate Forward\n");
              for(int i = 0; i < vertex_TOTAL; i++){
                vertices[i] = Translate(vertices[i],0.0f,5.0f,0.0f);
              }
              break;
            case 'b':
              printf("Translate Back\n");
              for(int i = 0; i < vertex_TOTAL; i++){
                vertices[i] = Translate(vertices[i],0.0f,-5.0f,0.0f);
              }
              break;
            case 'l':
              printf("Translate Left\n");
              for(int i = 0; i < vertex_TOTAL; i++){
                vertices[i] = Translate(vertices[i],-5.0f,0.0f,0.0f);
              }
              break;
            case 'r':
              printf("Translate Right\n");
              for(int i = 0; i < vertex_TOTAL; i++){
                vertices[i] = Translate(vertices[i],5.0f,0.0f,0.0f);
              }
              break;
            default :
              printf("Invalid Translate Direction\n");
          }
          break;
        case 's':
          switch(amt){
            case 'i':
              printf("Scale Increase\n");
              for(int i = 0; i < vertex_TOTAL; i++){
                vertices[i] = Translate(vertices[i],10.0f,10.0f,10.0f);
              }
              break;
            case 'd':
              printf("Scale Decrease\n");
              for(int i = 0; i < vertex_TOTAL; i++){
                vertices[i] = Translate(vertices[i],-10.0f,-10.0f,-10.0f);
              }
              break;
            default:
              printf("Invalid Scale Factor\n");
          }
          break;
        case 'q':
          printf("Quitting\n");
          return 1;
          break;
        default:
          printf("Invalid Command\n");
      }
    }
  }

	return 1;
}

glm::vec4 Translate(glm::vec4 v, float Dx, float Dy, float Dz){
  //glm::mat4 traMx = glm::translate(glm::mat4(), glm::vec3(Dx,Dy,Dz));
  float tmpT[16] = {
    1.0f, 0.0f, 0.0f, Dx,
    0.0f, 1.0f, 0.0f, Dy,
    0.0f, 0.0f, 1.0f, Dz,
    0.0f, 0.0f, 0.0f, 1.0f};
  glm::mat4 traMx = glm::make_mat4(tmpT);
  glm::vec4 result = v * traMx;
  return result;
}

glm::vec4 Scale(glm::vec4 v, float Sx, float Sy, float Sz){
  //glm::mat4 scaMx = glm::scale(Sx,Sy,Sz);
  float tmpS[16] = {
    Sx, 0.0f, 0.0f, 0.0f,
    0.0f, Sy, 0.0f, 0.0f,
    0.0f, 0.0f, Sz, 0.0f,
    0.0f, 0.0f, 0.0f, 1.0f};
  glm::mat4 scaMx = glm::make_mat4(tmpS);
  glm::vec4 result = v * scaMx;
  return result;
}

glm::vec4 Rotate(glm::vec4 v, char Plane, float R){
  glm::mat4 rotMx;
  if(Plane == 'x'){
    float xRot[16] = {
      1.0f, 0.0f, 0.0f, 0.0f,
      0.0f, cos(R), -sin(R), 0.0f,
      0.0f, sin(R), cos(R), 0.0f,
      0.0f, 0.0f, 0.0f, 1.0f};
    rotMx = glm::make_mat4(xRot);
  }
  else if(Plane == 'y'){
    float yRot[16] = {
      cos(R), 0.0f, sin(R), 0.0f,
      0.0f, 1.0f, 0.0f, 0.0f,
      -sin(R), 0.0f, cos(R), 0.0f,
      0.0f, 0.0f, 0.0f, 1.0f};
    rotMx = glm::make_mat4(yRot);
  }
  else if(Plane == 'z'){
    float zRot[16] = {
      cos(R), -sin(R), 0.0f, 0.0f,
      sin(R), cos(R), 0.0f, 0.0f,
      0.0f, 0.0f, 1.0f, 0.0f,
      0.0f, 0.0f, 0.0f, 1.0f};
    rotMx = glm::make_mat4(zRot);
  }
  glm::vec4 result = v * rotMx;
  return result;
}
