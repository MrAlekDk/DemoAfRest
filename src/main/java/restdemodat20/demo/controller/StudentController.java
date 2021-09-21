package restdemodat20.demo.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import restdemodat20.demo.model.Student;
import restdemodat20.demo.repository.StudentRepository;
import org.springframework.http.ResponseEntity;

import javax.swing.text.html.Option;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequestMapping("/students")
@RestController
public class StudentController {

    private StudentRepository studentRep;


    //Constructor injection i stedet for fields-injection (aka. Autowired)
    public StudentController(StudentRepository studentRep) {
        this.studentRep = studentRep;
    }


    //HTTP GET (uri = /students)
    @GetMapping()
    public ResponseEntity<List<Student>> findAll() {
        List<Student> tmp = new ArrayList<>();
        studentRep.findAll().forEach(tmp::add);
        //ResponseEntity builder
        return ResponseEntity.status(HttpStatus.OK).body(tmp);
    }


    @GetMapping(value = "/{id}")
    public ResponseEntity<Student> findSpecificStudent(@PathVariable("id") long id) {
        Optional<Student> tmp = studentRep.findById(id);
        if (!tmp.isEmpty()) {
            return ResponseEntity.status(HttpStatus.OK).body(tmp.get());
        }
        return ResponseEntity.status(HttpStatus.OK).body(tmp.get());
    }

    //Orgins = hvor må forespørgsel komme fra
    //exposedHeaders - hvilke Header elementer høre til ??
    @CrossOrigin(origins = "*", exposedHeaders = "Location")
    @PostMapping("")
    public ResponseEntity<Student> create(@RequestBody Student student) {
        //Opret ny student
        if (student.getId() != null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
            //student.setId(null);
        }
        Student newStudent = studentRep.save(student);
        //location header: /students/newId
        //"location", "/students/" + newStudent.getId()
        //status http status created
        String locationString = "/students/" + newStudent.getId();
        return ResponseEntity.status(HttpStatus.CREATED).header("Location", locationString)
                .body(newStudent);
    }

    //Put update
    @PutMapping("/{id}")
    public ResponseEntity<String> update(@PathVariable Long id, @RequestBody Student student) {

        Optional<Student> optionalStudent = studentRep.findById(id);
        if (optionalStudent.isPresent()) {
            if (id.equals(student.getId())) {
                student.setId(id);
                studentRep.save(student);
                return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
            }
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    //Delete delete
    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        Optional<Student> optionalStudent = studentRep.findById(id);

        if (optionalStudent.isPresent()) {
            studentRep.deleteById(id);
            return ResponseEntity.status(HttpStatus.OK).build();
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
}
