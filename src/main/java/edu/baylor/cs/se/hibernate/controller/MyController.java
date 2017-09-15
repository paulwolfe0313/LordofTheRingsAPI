package edu.baylor.cs.se.hibernate.controller;

import edu.baylor.cs.se.hibernate.model.Student;
import edu.baylor.cs.se.hibernate.model.Teacher;
import edu.baylor.cs.se.hibernate.services.SuperRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


//Ignore this as it is Spring and not Java EE (Jax-RS) controller
@RestController
public class MyController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    SuperRepository superRepository;

    @RequestMapping(value = "/addStudent", method = RequestMethod.POST)
    public ResponseEntity<Student> addStudent(@RequestBody String name) {
        //bad practise - skipping all the input validation which should be done in every REST service
        try {
            Student s = superRepository.addStudent(name);
            return new ResponseEntity(s, HttpStatus.CREATED);
        }catch(Exception e){
            logger.error("dummy message", e);
            // bad practise - you should never ever return your error
            return new ResponseEntity(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //very bad practise - using GET method to insert something to DB
    @RequestMapping(value = "/populate", method = RequestMethod.GET)
    public ResponseEntity populate(){
        superRepository.populate();
        return new ResponseEntity(HttpStatus.OK);
    }

    @RequestMapping(value = "/teacher", method = RequestMethod.GET)
    public ResponseEntity<Teacher> getTeacher(){
        return new ResponseEntity(superRepository.getTeacher(),HttpStatus.OK);
    }

    @RequestMapping(value = "/course", method = RequestMethod.GET)
    public ResponseEntity<Teacher> getCourse(){
        return new ResponseEntity(superRepository.getCourse(),HttpStatus.OK);
    }
}
