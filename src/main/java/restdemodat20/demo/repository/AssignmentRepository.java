package restdemodat20.demo.repository;


import org.springframework.data.repository.CrudRepository;
import restdemodat20.demo.model.Assignment;

public interface AssignmentRepository extends CrudRepository<Assignment,Long> {
}
