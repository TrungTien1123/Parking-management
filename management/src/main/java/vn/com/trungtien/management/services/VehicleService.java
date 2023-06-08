package vn.com.trungtien.management.services;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import vn.com.trungtien.management.domains.entities.Vehicle;
import vn.com.trungtien.management.form.VehicleCreateForm;
import vn.com.trungtien.management.form.VehicleFilterForm;
import vn.com.trungtien.management.form.VehicleUpdateForm;
import vn.com.trungtien.management.repositories.IVehicleRepository;
import vn.com.trungtien.management.services.dto.VehicleDTO;
import vn.com.trungtien.management.services.mapper.VehicleMapper;
import vn.com.trungtien.management.specification.VehicleSpecification;

import java.util.List;

@Service
@RequiredArgsConstructor
public class VehicleService {

    private final IVehicleRepository repository;

    private final VehicleMapper mapper;

    private final ModelMapper modelMapper;
    public Page<VehicleDTO> findAll(Pageable pageable, VehicleFilterForm form){
        Specification<Vehicle> spec = VehicleSpecification.buildWhere(form);
        Page<Vehicle> page = repository.findAll(spec, pageable);
        List<Vehicle> Vehicles = page.getContent();
        List<VehicleDTO> dtos = mapper.toDTOS(Vehicles);
        return new PageImpl<>(dtos, pageable, page.getTotalElements());
    }
    public VehicleDTO findById(Long id){
        return mapper.toDTO(repository.findById(id).orElse(null));
    }
    public void create(VehicleCreateForm form){
        repository.save(modelMapper.map(form, Vehicle.class));
    }
    public void update(VehicleUpdateForm form){
        repository.save(modelMapper.map(form, Vehicle.class));
    }
    public void deleteById(Long id){
        repository.deleteById(id);
    }
}