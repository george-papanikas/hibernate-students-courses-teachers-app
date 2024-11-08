package gr.aueb.cf.schoolapp;

import gr.aueb.cf.schoolapp.model.Course;
import gr.aueb.cf.schoolapp.model.Student;
import gr.aueb.cf.schoolapp.model.Teacher;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("school8PU");
        EntityManager em = emf.createEntityManager();

        em.getTransaction().begin();

/*        Teacher thanos = new Teacher();
        thanos.setFirstname("Thanos");
        thanos.setLastname("Androutsos");

        Teacher kapetis = new Teacher();
        kapetis.setFirstname("Makis");
        kapetis.setLastname("Kapetis");

        Teacher markos = new Teacher();
        markos.setFirstname("Markos");
        markos.setLastname("Karabatsis");

        Course java = new Course();
        java.setTitle("Java");
        java.setDescription("Java Course");

        Course sql = new Course();
        sql.setTitle("SQL");
        sql.setDescription("SQL Course");

        thanos.addCourse(java);
        kapetis.addCourse(sql);

        Student alice = new Student();
        alice.setFirstname("Alice");
        alice.setLastname("W");

        alice.addCourse(java);

        em.persist(thanos);
        em.persist(kapetis);
        em.persist(markos);
        em.persist(java);
        em.persist(sql);
        em.persist(alice);*/

//        Student alice = em.find(Student.class, 6L);
//        Course java = em.find(Course.class, 4L);
//        alice.deleteCourse(java);

//        TypedQuery<Object[]> query = em.createQuery("SELECT t, c FROM Teacher t JOIN t.courses c", Object[].class);
//        List<Object[]> results = query.getResultList();
//
//        for (Object[] result : results) {
//            System.out.print(result[0]);
//            System.out.println(result[1]);
//        }

        TypedQuery<Teacher> query = em.createQuery("select t from Teacher t join t.courses c where c.title = :title and " +
                "t.lastname = :lastname", Teacher.class);
        query.setParameter("title", "Java");
        query.setParameter("lastname", "Androutsos");
        List<Teacher> teachers = query.getResultList();

        teachers.forEach(System.out::println);
//        teachers.forEach(teacher -> System.out.println(teacher));



        em.getTransaction().commit();
        em.close();
        emf.close();
    }
}
