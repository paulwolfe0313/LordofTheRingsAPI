package edu.baylor.cs.se.hibernate.controller;

import edu.baylor.cs.se.hibernate.model.Teacher;
import edu.baylor.cs.se.hibernate.services.SuperRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


//Ignore this as it is Spring and not Java EE (Jax-RS) controller
@RestController
public class MyController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private SuperRepository superRepository;

    //very bad practise - using GET method to insert something to DB
    @RequestMapping(value = "/populate", method = RequestMethod.GET)
    public ResponseEntity populate(){
        superRepository.populate();
        return new ResponseEntity(HttpStatus.OK);
    }

    @RequestMapping(value = "/courses", method = RequestMethod.GET)
    public ResponseEntity<Teacher> getCoursesBySize(){
        return new ResponseEntity(superRepository.getCoursesBySize(),HttpStatus.OK);
    }

    @RequestMapping(value = "/courses2", method = RequestMethod.GET)
    public ResponseEntity<Teacher> getCoursesByStudentName(){
        return new ResponseEntity(superRepository.getCoursesByStudentName(),HttpStatus.OK);
    }
}
