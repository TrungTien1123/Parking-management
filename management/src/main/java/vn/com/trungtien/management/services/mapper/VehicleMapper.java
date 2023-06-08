package vn.com.trungtien.management.services.mapper;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import vn.com.trungtien.management.domains.entities.Vehicle;
import vn.com.trungtien.management.services.dto.VehicleDTO;

import java.util.List;

@Component
public class VehicleMapper implements EntityMapper<Vehicle, VehicleDTO> {

    @Autowired
    private ModelMapper modelMapper;
    @Override
    public VehicleDTO toDTO(Vehicle entity) {
        return modelMapper.map(entity, VehicleDTO.class);
    }

    @Override
    public Vehicle toEntity(VehicleDTO dto) {
        return modelMapper.map(dto,Vehicle.class);
    }

    @Override
    public List<VehicleDTO> toDTOS(List<Vehicle> entities) {
        return modelMapper.map(entities, new TypeToken<List<VehicleDTO>>(){}.getType());
    }
    @Override
    public List<Vehicle> toEntities(List<VehicleDTO> dtos) {
        return modelMapper.map(dtos, new TypeToken<List<Vehicle>>(){}.getType());
    }
}