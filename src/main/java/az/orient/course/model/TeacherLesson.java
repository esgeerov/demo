package az.orient.course.model;

public class TeacherLesson extends Course {

    private Teacher teacher;
    private Lesson lesson;
    private Double price;

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public Lesson getLesson() {
        return lesson;
    }

    public void setLesson(Lesson lesson) {
        this.lesson = lesson;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "TeacherLesson{" +
                "teacher=" + teacher +
                ", lesson=" + lesson +
                ", price=" + price +
                '}';
    }
}
