package vn.com.trungtien.management.services;


import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vn.com.trungtien.management.domains.entities.Vehicle;
import vn.com.trungtien.management.domains.entities.VehicleRequest;
import vn.com.trungtien.management.form.VehicleRequestCreateForm;
import vn.com.trungtien.management.form.VehicleRequestFilterForm;
import vn.com.trungtien.management.form.VehicleRequestUpdateForm;
import vn.com.trungtien.management.repositories.IVehicleRepository;
import vn.com.trungtien.management.repositories.IVehicleRequestRepository;
import vn.com.trungtien.management.services.dto.VehicleRequestDTO;
import vn.com.trungtien.management.services.mapper.VehicleRequestMapper;
import vn.com.trungtien.management.specification.VehicleRequestSpecification;

import java.util.List;

@Service
@RequiredArgsConstructor
public class VehicleRequestService {

    private final IVehicleRequestRepository repository;
    private final IVehicleRepository vehicleRepository;
    private final VehicleRequestMapper mapper;

    private final ModelMapper modelMapper;
    public Page<VehicleRequestDTO> findAll(Pageable pageable, VehicleRequestFilterForm form){
        Specification<VehicleRequest> spec = VehicleRequestSpecification.buildWhere(form);
        Page<VehicleRequest> page = repository.findAll(spec, pageable);
        List<VehicleRequest> VehicleRequests = page.getContent();
        List<VehicleRequestDTO> dtos = mapper.toDTOS(VehicleRequests);
        return new PageImpl<>(dtos, pageable, page.getTotalElements());
    }
    public VehicleRequestDTO findById(Long id){
        return mapper.toDTO(repository.findById(id).orElse(null));
    }
    @Transactional
    public void create(VehicleRequestCreateForm form){
        VehicleRequest vehicleRequest = modelMapper.map(form, VehicleRequest.class);
        VehicleRequest saved = repository.save(vehicleRequest);
        List<Vehicle> vehicles = vehicleRequest.getVehicleList();
        for (Vehicle vehicle : vehicles) {
            vehicle.setVehicleRequest(saved);
        }
        vehicleRepository.saveAll(vehicles);
    }
    public void update(VehicleRequestUpdateForm form){
        repository.save(modelMapper.map(form, VehicleRequest.class));
    }
    public void deleteById(Long id){
        repository.deleteById(id);
    }
}