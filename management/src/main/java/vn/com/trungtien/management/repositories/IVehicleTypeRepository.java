package vn.com.trungtien.management.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import vn.com.trungtien.management.domains.entities.VehicleType;

public interface IVehicleTypeRepository extends JpaRepository<VehicleType, Long>, JpaSpecificationExecutor<VehicleType> {

}
