package az.orient.course.service;

import az.orient.course.model.Student;
import az.orient.course.model.StudentTeacherLesson;

import java.util.List;

public interface StudentService {

    List<Student> getStudentList() throws Exception;

    void addStudent(Student student) throws Exception;

    Student getStudentById(Long studentId) throws Exception;

    void updateStudent(Student student) throws Exception;

    void deleteStudent(Long studentId) throws Exception;

    List<Student> searchStudentData(String keyword) throws Exception;

    List<Student> getStudentListByLessonIdAndTeacherId(Long lessonId, Long teacherId) throws Exception;

    StudentTeacherLesson getStudentTeacherLessonBySIdAndTIdAndLId(Long studentId, Long teacherId, Long lessonId) throws Exception;

    Student getStudentByUserId(Long userId) throws Exception;

}
