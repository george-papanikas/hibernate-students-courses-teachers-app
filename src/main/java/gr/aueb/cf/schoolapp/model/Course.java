package gr.aueb.cf.schoolapp.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Entity
@Table(name = "COURSES")
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "TITLE", length = 50, nullable = true, unique = true)
    private String title;

    @Column(name = "DESCRIPTION", length = 255, nullable = true, unique = false)
    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "TEACHER_ID", nullable = true)
    private Teacher teacher;

    @ManyToMany
    @JoinTable(name = "COURSES_STUDENTS", joinColumns = @JoinColumn(name = "COURSE_ID", referencedColumnName = "ID")
                ,inverseJoinColumns = @JoinColumn(name = "STUDENT_ID", referencedColumnName = "ID"))
    private List<Student> students = new ArrayList<>();


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    protected List<Student> getStudents() {
        return students;
    }

    protected void setStudents(List<Student> students) {
        this.students = students;
    }

    public List<Student> getAllStudents() {
        return Collections.unmodifiableList(students);
    }

    public void addStudent(Student student) {
        this.students.add(student);
        for (Course course : student.getCourses()) {
            if (course == this) {
                return;
            }
        }
        student.addCourse(this);
    }

    public void deleteStudent(Student student) {
        boolean found = false;
        this.students.remove(student);

        for (Course course : student.getCourses()) {
            if (course == this) {
                found = true;
                break;
            }
        }
        if (found) student.deleteCourse(this);
    }

    @Override
    public String toString() {
        return "Course{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
