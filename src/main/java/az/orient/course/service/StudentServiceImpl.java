package az.orient.course.service;

import az.orient.course.dao.StudentDao;
import az.orient.course.model.Student;
import az.orient.course.model.StudentTeacherLesson;
import az.orient.course.service.StudentService;

import java.util.List;

public class
StudentServiceImpl implements StudentService {

    private StudentDao studentDao;

    public StudentServiceImpl(StudentDao studentDao) {
        this.studentDao = studentDao;
    }

    @Override
    public List<Student> getStudentList() throws Exception {
        return studentDao.getStudentList();
    }

    @Override
    public void addStudent(Student student) throws Exception {
          studentDao.addStudent(student);
    }

    @Override
    public Student getStudentById(Long studentId) throws Exception {
        return studentDao.getStudentById(studentId);
    }

    @Override
    public void updateStudent(Student student) throws Exception {
          studentDao.updateStudent(student);
    }

    @Override
    public void deleteStudent(Long studentId) throws Exception {
         studentDao.deleteStudent(studentId);
    }

    @Override
    public List<Student> searchStudentData(String keyword) throws Exception {
        return studentDao.searchStudentData(keyword);
    }

    @Override
    public List<Student> getStudentListByLessonIdAndTeacherId(Long lessonId, Long teacherId) throws Exception {
        return studentDao.getStudentListByLessonIdAndTeacherId(lessonId,teacherId);
    }

    @Override
    public StudentTeacherLesson getStudentTeacherLessonBySIdAndTIdAndLId(Long studentId, Long teacherId, Long lessonId) throws Exception {
         return studentDao.getStudentTeacherLessonBySIdAndTIdAndLId(studentId, teacherId, lessonId);
    }

    @Override
    public Student getStudentByUserId(Long userId) throws Exception {
        return studentDao.getStudentByUserId(userId);
    }
}
