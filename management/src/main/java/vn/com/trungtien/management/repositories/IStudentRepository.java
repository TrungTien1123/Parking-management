package vn.com.trungtien.management.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import vn.com.trungtien.management.domains.entities.Student;
@Repository
public interface IStudentRepository extends JpaRepository<Student, Long>, JpaSpecificationExecutor<Student> {

}
