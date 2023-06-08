package vn.com.trungtien.management.services.mapper;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import vn.com.trungtien.management.domains.entities.VehicleType;
import vn.com.trungtien.management.services.dto.VehicleTypeDTO;

import java.util.List;

@Component
public class VehicleTypeMapper implements EntityMapper<VehicleType, VehicleTypeDTO> {

    @Autowired
    private ModelMapper modelMapper;
    @Override
    public VehicleTypeDTO toDTO(VehicleType entity) {
        return modelMapper.map(entity, VehicleTypeDTO.class);
    }

    @Override
    public VehicleType toEntity(VehicleTypeDTO dto) {
        return modelMapper.map(dto,VehicleType.class);
    }

    @Override
    public List<VehicleTypeDTO> toDTOS(List<VehicleType> entities) {
        return modelMapper.map(entities, new TypeToken<List<VehicleTypeDTO>>(){}.getType());
    }
    @Override
    public List<VehicleType> toEntities(List<VehicleTypeDTO> dtos) {
        return modelMapper.map(dtos, new TypeToken<List<VehicleType>>(){}.getType());
    }
}

