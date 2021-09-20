package restdemodat20.demo.repository;

import org.springframework.data.repository.CrudRepository;
import restdemodat20.demo.model.Student;

public interface StudentRepository extends CrudRepository<Student,Long> {

}
