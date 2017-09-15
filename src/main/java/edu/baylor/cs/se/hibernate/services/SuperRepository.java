package edu.baylor.cs.se.hibernate.services;

import edu.baylor.cs.se.hibernate.model.Student;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

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
}
