package edu.baylor.cs.se.hibernate.services;

import edu.baylor.cs.se.hibernate.model.Course;
import edu.baylor.cs.se.hibernate.model.Student;
import edu.baylor.cs.se.hibernate.model.Teacher;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.LinkedList;
import java.util.List;

//Spring annotation, feel free to ignore it
@Repository
@Transactional(readOnly = true)
public class SuperRepository {

    @PersistenceContext
    EntityManager em;

    public Student addStudent(String name){
        Student student = new Student();
        student.setName(name);
        em.persist(student);
        return student;
    }

    public void populate(){
        Student student = new Student();
        student.setName("Joe");
        em.persist(student);

        Student student2 = new Student();
        student2.setName("John");
        em.persist(student2);

        Teacher teacher = new Teacher();
        teacher.setFirstName("Bob");
        teacher.setLastName("Porter");
        teacher.setEmail("bob@porter.com");
        em.persist(teacher);

        Course course = new Course();
        List<Student> students = new LinkedList();
        students.add(student);
        students.add(student2);
        course.setStudents(students);
        course.setName("Software engineering");
        course.setTeacher(teacher);
        //course.getStudents().add(student);
        //course.getStudents().add(student2);
        em.persist(course);
    }

    public Teacher getTeacher(){
        return (Teacher)em.createQuery("SELECT t FROM Teacher t").getResultList().get(0);
    }

    public Course getCourse(){
        return (Course)em.createQuery("SELECT c FROM Course c").getResultList().get(0);
    }
}
