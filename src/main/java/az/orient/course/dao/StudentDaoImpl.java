package az.orient.course.dao;

import az.orient.course.dao.DbHelper;
import az.orient.course.dao.StudentDao;
import az.orient.course.model.Student;
import az.orient.course.model.StudentTeacherLesson;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class StudentDaoImpl implements StudentDao {

    @Override
    public List<Student> getStudentList() throws Exception {
        List<Student> studentList = new ArrayList<>();
        String sql = "select * from qrup58db.student\n" +
                "where active = 1";
        try (Connection c = DbHelper.getConnection(); PreparedStatement ps = c.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Student student = new Student();
                student.setId(rs.getLong("id"));
                student.setName(rs.getString("name"));
                student.setSurname(rs.getString("surname"));
                student.setDob(rs.getDate("dob"));
                student.setAddress(rs.getString("address"));
                student.setPhone(rs.getString("phone"));
                student.setPin(rs.getString("pin"));
                studentList.add(student);
            }
        }
        return studentList;
    }

    @Override
    public void addStudent(Student student) throws Exception {
        String sql = "insert into student(name,surname,dob,address,phone,pin)" +
                " values(?,?,?,?,?,?) ";
        try(Connection c = DbHelper.getConnection();PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1,student.getName());
            ps.setString(2,student.getSurname());
            ps.setDate(3,new java.sql.Date(student.getDob().getTime()));
            ps.setString(4,student.getAddress());
            ps.setString(5,student.getPhone());
            ps.setString(6,student.getPin());
            ps.execute();
        }
    }

    @Override
    public Student getStudentById(Long studentId) throws Exception {
        Student student = new Student();
        String sql = "select * from qrup58db.student\n" +
                "where active = 1 and id = ?";
        try (Connection c = DbHelper.getConnection(); PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setLong(1,studentId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                student.setId(rs.getLong("id"));
                student.setName(rs.getString("name"));
                student.setSurname(rs.getString("surname"));
                student.setDob(rs.getDate("dob"));
                student.setAddress(rs.getString("address"));
                student.setPhone(rs.getString("phone"));
                student.setPin(rs.getString("pin"));
            } else {
                student = null;
            }
        }
        return student;
    }

    @Override
    public void updateStudent(Student student) throws Exception {
        String sql = "update student set name = ?,surname = ?,dob = ?,address = ?,phone = ?,pin = ?" +
                " where id = ?";
        try(Connection c = DbHelper.getConnection();PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1,student.getName());
            ps.setString(2,student.getSurname());
            if (student.getDob() != null)
            ps.setDate(3,new java.sql.Date(student.getDob().getTime()));
            else 
            ps.setDate(3, null);
            ps.setString(4,student.getAddress());
            ps.setString(5,student.getPhone());
            ps.setString(6,student.getPin());
            ps.setLong(7,student.getId());
            ps.execute();
        }
    }

    @Override
    public void deleteStudent(Long studentId) throws Exception {
        String sql = "update student set active = 0 " +
                " where id = ?";
        try(Connection c = DbHelper.getConnection();PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setLong(1,studentId);
            ps.execute();
        }
    }

    @Override
    public List<Student> searchStudentData(String keyword) throws Exception {
        List<Student> studentList = new ArrayList<>();
        String sql = "select * from qrup58db.student\n" +
                "where active = 1 AND (LOWER(NAME) LIKE LOWER(?) OR LOWER(SURNAME) LIKE LOWER(?) OR LOWER(ADDRESS) LIKE LOWER(?))";
        try (Connection c = DbHelper.getConnection(); PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1,"%"+keyword+"%");
            ps.setString(2,"%"+keyword+"%");
            ps.setString(3,"%"+keyword+"%");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Student student = new Student();
                student.setId(rs.getLong("id"));
                student.setName(rs.getString("name"));
                student.setSurname(rs.getString("surname"));
                student.setDob(rs.getDate("dob"));
                student.setAddress(rs.getString("address"));
                student.setPhone(rs.getString("phone"));
                student.setPin(rs.getString("pin"));
                studentList.add(student);
            }
        }
        return studentList;
    }

    @Override
    public List<Student> getStudentListByLessonIdAndTeacherId(Long lessonId, Long teacherId) throws Exception {
        List<Student> studentList = new ArrayList<>();
        String sql = "select s.id,s.name,s.surname from student_teacher_lesson stl\n" +
                "inner join student s on stl.student_id = s.id\n" +
                "inner join teacher_lesson tl on stl.teacher_lesson_id = tl.id\n" +
                "inner join teacher t on tl.teacher_id = t.id\n" +
                "inner join lesson l on tl.lesson_id = l.id\n" +
                "where stl.active = 1 and t.id = ? and l.id = ?";
        try (Connection c = DbHelper.getConnection(); PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setLong(1,teacherId);
            ps.setLong(2,lessonId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Student student = new Student();
                student.setId(rs.getLong("id"));
                student.setName(rs.getString("name"));
                student.setSurname(rs.getString("surname"));
                studentList.add(student);
            }
        }

        return studentList;
    }

    @Override
    public StudentTeacherLesson getStudentTeacherLessonBySIdAndTIdAndLId(Long studentId, Long teacherId, Long lessonId) throws Exception {
        StudentTeacherLesson studentTeacherLesson = new StudentTeacherLesson();
        String sql = "select stl.id id from student_teacher_lesson stl\n" +
                        "inner join teacher_lesson tl on stl.teacher_lesson_id = tl.id\n" +
                        "inner join teacher t on tl.teacher_id = t.id\n" +
                        "inner join lesson l on tl.lesson_id = l.id\n" +
                        "where stl.active = 1 and stl.student_id = ? and t.id = ? and l.id = ? ";
         try (Connection connection = DbHelper.getConnection();
              PreparedStatement preparedStatement = connection.prepareStatement(sql)){
             preparedStatement.setLong(1,studentId);
             preparedStatement.setLong(2,teacherId);
             preparedStatement.setLong(3,lessonId);
             ResultSet resultSet = preparedStatement.executeQuery();
             if(resultSet.next()){
                  studentTeacherLesson.setId(resultSet.getLong("id"));
             }
         }
         return studentTeacherLesson;
    }

    @Override
    public Student getStudentByUserId(Long userId) throws Exception {
        Student student = new Student();
        String sql = "select * from qrup58db.student s\n" +
                "where s.active = 1 and s.user_id = ?";
        try (Connection c = DbHelper.getConnection(); PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setLong(1,userId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                student.setId(rs.getLong("id"));
                student.setName(rs.getString("name"));
                student.setSurname(rs.getString("surname"));
                student.setDob(rs.getDate("dob"));
                student.setAddress(rs.getString("address"));
                student.setPhone(rs.getString("phone"));
                student.setPin(rs.getString("pin"));
            } else {
                student = null;
            }
        }
        return student;
    }
}
