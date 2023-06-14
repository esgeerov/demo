package az.orient.course.controller;

import az.orient.course.dao.StudentDao;
import az.orient.course.dao.StudentDaoImpl;
import az.orient.course.model.Student;
import az.orient.course.service.StudentService;
import az.orient.course.service.StudentServiceImpl;

import java.io.*;
import java.util.List;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

@WebServlet(name = "ContorollerServlet", value = "/cs")
public class ControlllerServlet extends HttpServlet {
    private String message;

    public void init() {
        message = "Hello World!";
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        processRequest(request, response);


    }

    public void destroy() {
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        processRequest(request, response);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        StudentDao studentDao=new StudentDaoImpl();
        StudentService studentService=new StudentServiceImpl(studentDao);
        try{  if (request.getParameter("action") != null) {

            String action = request.getParameter("action");
            if (action.equalsIgnoreCase("register")) {
                try (BufferedWriter bw = new BufferedWriter(new FileWriter("C:/Users/samir/IdeaProjects/demo/text.txt"));) {
                    String name = request.getParameter("name");
                    bw.write("name= "+name);
                    bw.newLine();
                    String password = request.getParameter("password");
                    bw.write("password= "+password);
                    bw.newLine();
                    String dob = request.getParameter("dob");
                    bw.write("dob= "+dob);
                    bw.newLine();
                    String email = request.getParameter("email");
                    bw.write("email= "+email);
                    bw.newLine();
                    String country = request.getParameter("country");
                    bw.write("country= "+country);
                    bw.newLine();
                }
                out.write("<h1>Success</h1>");
            } else if (action.equalsIgnoreCase("getStudentList")) {
                List<Student>list=studentService.getStudentList();

            }
        }

        }catch (Exception exception){
            exception.printStackTrace();

        }


    }
}