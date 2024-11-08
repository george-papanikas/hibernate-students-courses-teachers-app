package gr.aueb.cf.schoolapp.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Entity
@Table(name = "STUDENTS")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "FIRSTNAME",length = 50, nullable = true, unique = false)
    private String firstname;

    @Column(name = "LASTNAME",length = 50, nullable = true, unique = false)
    private String lastname;

    @ManyToMany
    @JoinTable(name = "STUDENTS_COURSES", joinColumns = @JoinColumn(name = "STUDENT_ID", referencedColumnName = "ID")
            ,inverseJoinColumns = @JoinColumn(name = "COURSE_ID", referencedColumnName = "ID"))
    private List<Course> courses = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    protected List<Course> getCourses() {
        return courses;
    }

    protected void setCourses(List<Course> courses) {
        this.courses = courses;
    }

    public List<Course> getAllCourses() {
        return Collections.unmodifiableList(courses); // returns unmodifiable instance of courses
    }

    public void addCourse(Course course) {
        this.courses.add(course);
        for (Student student : course.getStudents()) {
            if (student == this) {
                return;
            }
        }
        course.addStudent(this);
    }

    public void deleteCourse(Course course) {
        boolean found = false;
        this.courses.remove(course);

        for (Student student : course.getStudents()) {
            if (student == this) {
                found = true;
                break;
            }
        }
        if (found) course.deleteStudent(this);
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                '}';
    }
}
