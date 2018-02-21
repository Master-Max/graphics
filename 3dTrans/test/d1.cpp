#include <GL/glut.h>
#include <glm/glm.hpp>
#include <glm/ext.hpp>

void displayMe(void)
{
    glm::vec4 a = glm::vec4(0.0, 0.0, 0.0, 1.0);
    glm::vec4 b = glm::vec4(0.5, 0.0, 0.0, 1.0);
    glm::vec4 c = glm::vec4(0.5, 0.5, 0.0, 1.0);

    //glVertex4f(GLfloat x, GLfloat y, GLfloat z, GLfloat w)

    glClear(GL_COLOR_BUFFER_BIT);
    glBegin(GL_POLYGON);
        glVertex4f(glm::value_ptr(a));
        glVertex4f(glm::value_ptr(b));
        glVertex4f(glm::value_ptr(c));
        //glVertex3f(0.0, 0.5, 0.0);
    glEnd();
    glFlush();
}

void drawLine(void)
{
  glClear(GL_COLOR_BUFFER_BIT);
	glBegin(GL_LINES);
		glVertex3f(-10.0, 0.0, 0.0);
		glVertex3f(5.0, 50.0, 0.0);
	glEnd();
  glLineWidth(2.5f);
	glFlush();
}



int main(int argc, char** argv)
{
    glutInit(&argc, argv);
    glutInitDisplayMode(GLUT_SINGLE);
    glutInitWindowSize(300, 300);
    glutInitWindowPosition(100, 100);
    glutCreateWindow("Hello world :D");
    glutDisplayFunc(displayMe);
		//glutDisplayFunc(drawLine);
    glutMainLoop();
    return 0;
}
