package vn.com.trungtien.management.services.mapper;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import vn.com.trungtien.management.domains.entities.Building;
import vn.com.trungtien.management.services.dto.BuildingDTO;

import java.util.List;

@Component
public class BuildingMapper implements EntityMapper<Building, BuildingDTO> {

    @Autowired
    private ModelMapper modelMapper;
    @Override
    public BuildingDTO toDTO(Building entity) {
        return modelMapper.map(entity, BuildingDTO.class);
    }

    @Override
    public Building toEntity(BuildingDTO dto) {
        return modelMapper.map(dto,Building.class);
    }

    @Override
    public List<BuildingDTO> toDTOS(List<Building> entities) {
        return modelMapper.map(entities, new TypeToken<List<BuildingDTO>>(){}.getType());
    }
    @Override
    public List<Building> toEntities(List<BuildingDTO> dtos) {
        return modelMapper.map(dtos, new TypeToken<List<Building>>(){}.getType());
    }
}