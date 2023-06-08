package vn.com.trungtien.management.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import vn.com.trungtien.management.domains.entities.VehicleRequest;

public interface IVehicleRequestRepository extends JpaRepository<VehicleRequest,Long>, JpaSpecificationExecutor<VehicleRequest> {
}
