package restdemodat20.demo.controller;

import org.springframework.http.HttpStatus;
import restdemodat20.demo.model.Student;
import restdemodat20.demo.repository.StudentRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class StudentController {

    private StudentRepository studentRep;


    //Constructor injection i stedet for fields-injection (aka. Autowired)
    public StudentController(StudentRepository studentRep) {
        this.studentRep = studentRep;
    }


    //HTTP GET (uri = /students)
    @GetMapping(value = "/students")
    public ResponseEntity<List<Student>> findAll(){
        List<Student> tmp = new ArrayList<>();
        studentRep.findAll().forEach(tmp::add);
        return ResponseEntity.status(HttpStatus.OK).body(tmp);
    }
}
