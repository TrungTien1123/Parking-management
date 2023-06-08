package vn.com.trungtien.management.services;


import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import vn.com.trungtien.management.domains.entities.Building;
import vn.com.trungtien.management.form.BuildingCreateForm;
import vn.com.trungtien.management.form.BuildingFilterForm;
import vn.com.trungtien.management.form.BuildingUpdateForm;
import vn.com.trungtien.management.repositories.IBuildingRepository;
import vn.com.trungtien.management.services.dto.BuildingDTO;
import vn.com.trungtien.management.services.mapper.BuildingMapper;
import vn.com.trungtien.management.specification.BuildingSpecification;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BuildingService {

    private final IBuildingRepository repository;

    private final BuildingMapper mapper;

    private final ModelMapper modelMapper;
    public Page<BuildingDTO> findAll(Pageable pageable, BuildingFilterForm form){
        Specification<Building> spec = BuildingSpecification.buildWhere(form);
        Page<Building> page = repository.findAll(spec, pageable);
        List<Building> Buildings = page.getContent();
        List<BuildingDTO> dtos = mapper.toDTOS(Buildings);
        return new PageImpl<>(dtos, pageable, page.getTotalElements());
    }
    public BuildingDTO findById(Long id){
        return mapper.toDTO(repository.findById(id).orElse(null));
    }
    public void create(BuildingCreateForm form){
        repository.save(modelMapper.map(form, Building.class));
    }
    public void update(BuildingUpdateForm form){
        repository.save(modelMapper.map(form, Building.class));
    }
    public void deleteById(Long id){
        repository.deleteById(id);
    }
}