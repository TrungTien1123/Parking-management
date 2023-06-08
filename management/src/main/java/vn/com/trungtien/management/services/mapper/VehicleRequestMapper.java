package vn.com.trungtien.management.services.mapper;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import vn.com.trungtien.management.domains.entities.VehicleRequest;
import vn.com.trungtien.management.services.dto.VehicleRequestDTO;

import java.util.List;

@Component
public class VehicleRequestMapper implements EntityMapper<VehicleRequest, VehicleRequestDTO> {

    @Autowired
    private ModelMapper modelMapper;
    @Override
    public VehicleRequestDTO toDTO(VehicleRequest entity) {
        return modelMapper.map(entity, VehicleRequestDTO.class);
    }

    @Override
    public VehicleRequest toEntity(VehicleRequestDTO dto) {
        return modelMapper.map(dto,VehicleRequest.class);
    }

    @Override
    public List<VehicleRequestDTO> toDTOS(List<VehicleRequest> entities) {
        return modelMapper.map(entities, new TypeToken<List<VehicleRequestDTO>>(){}.getType());
    }
    @Override
    public List<VehicleRequest> toEntities(List<VehicleRequestDTO> dtos) {
        return modelMapper.map(dtos, new TypeToken<List<VehicleRequest>>(){}.getType());
    }
}