package gr.aueb.cf.schoolapp.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Entity
@Table(name = "TEACHERS")
public class Teacher {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "FIRSTNAME", length = 50, nullable = true, unique = false)
    private String firstname;

    @Column(name = "LASTNAME", length = 50, nullable = true, unique = false)
    private String lastname;

    @OneToMany(mappedBy = "teacher")
    List<Course> courses = new ArrayList<>();

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
        return Collections.unmodifiableList(courses);
    }

    public void addCourse(Course course) {
        course.setTeacher(this);
        this.courses.add(course);
    }

    @Override
    public String toString() {
        return "Teacher{" +
                "id=" + id +
                ", firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                '}';
    }
}
