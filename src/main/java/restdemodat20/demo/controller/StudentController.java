package restdemodat20.demo.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import restdemodat20.demo.model.Student;
import restdemodat20.demo.repository.StudentRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class StudentController {

    private StudentRepository studentRep;


    //Constructor injection i stedet for fields-injection (aka. Autowired)
    public StudentController(StudentRepository studentRep) {
        this.studentRep = studentRep;
    }


    //HTTP GET (uri = /students)
    @GetMapping(value = "/students")
    public ResponseEntity<List<Student>> findAll() {
        List<Student> tmp = new ArrayList<>();
        studentRep.findAll().forEach(tmp::add);
        //ResponseEntity builder
        return ResponseEntity.status(HttpStatus.OK).body(tmp);
    }


    @GetMapping(value = "/students/{id}")
    public ResponseEntity<Student> findSpecificStudent(@PathVariable("id") long id) {
        Optional<Student> tmp = studentRep.findById(id);
        return ResponseEntity.status(HttpStatus.OK).body(tmp.get());
    }

    //Post create

    //Put update

    //Delete delete
}
