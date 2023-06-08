package vn.com.trungtien.management.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import vn.com.trungtien.management.domains.entities.Department;

@Repository

public interface IDepartmentRepository extends JpaRepository<Department,Long>, JpaSpecificationExecutor<Department> {


}
