package vn.com.trungtien.management.services;


import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import vn.com.trungtien.management.domains.entities.VehicleType;
import vn.com.trungtien.management.form.VehicleTypeCreateForm;
import vn.com.trungtien.management.form.VehicleTypeFilterForm;
import vn.com.trungtien.management.form.VehicleTypeUpdateForm;
import vn.com.trungtien.management.repositories.IVehicleTypeRepository;
import vn.com.trungtien.management.services.dto.VehicleTypeDTO;
import vn.com.trungtien.management.services.mapper.VehicleTypeMapper;
import vn.com.trungtien.management.specification.VehicleTypeSpecification;

import java.util.List;

@Service
@RequiredArgsConstructor
public class VehicleTypeService {

    private final IVehicleTypeRepository repository;

    private final VehicleTypeMapper mapper;

    private final ModelMapper modelMapper;
    public Page<VehicleTypeDTO> findAll(Pageable pageable, VehicleTypeFilterForm form){
        Specification<VehicleType> spec = VehicleTypeSpecification.buildWhere(form);
        Page<VehicleType> page = repository.findAll(spec, pageable);
        List<VehicleType> VehicleTypes = page.getContent();
        List<VehicleTypeDTO> dtos = mapper.toDTOS(VehicleTypes);
        return new PageImpl<>(dtos, pageable, page.getTotalElements());
    }
    public VehicleTypeDTO findById(Long id){
        return mapper.toDTO(repository.findById(id).orElse(null));
    }
    public void create(VehicleTypeCreateForm form){
        repository.save(modelMapper.map(form, VehicleType.class));
    }
    public void update(VehicleTypeUpdateForm form){
        repository.save(modelMapper.map(form, VehicleType.class));
    }
    public void deleteById(Long id){
        repository.deleteById(id);
    }
}