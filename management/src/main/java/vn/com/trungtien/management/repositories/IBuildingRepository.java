package vn.com.trungtien.management.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import vn.com.trungtien.management.domains.entities.Building;

import java.util.List;
@Repository
public interface IBuildingRepository extends JpaRepository<Building, Long>, JpaSpecificationExecutor<Building> {

}