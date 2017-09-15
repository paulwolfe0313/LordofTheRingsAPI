package edu.baylor.cs.se.hibernate.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
//annotation bellow is just for Jackson serialization in controller
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id")
public class Student implements Serializable{

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private String name;

    public Long getId() {
        return id;
    }

    @ManyToMany(mappedBy = "students", cascade = CascadeType.ALL)
    private List<Course> courses = new ArrayList();

    public List<Course> getCourses() {
        return courses;
    }

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }
}
