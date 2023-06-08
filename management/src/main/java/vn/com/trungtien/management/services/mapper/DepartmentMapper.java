package vn.com.trungtien.management.services.mapper;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import vn.com.trungtien.management.domains.entities.Department;
import vn.com.trungtien.management.services.dto.DepartmentDTO;

import java.util.List;
@Component

public class DepartmentMapper implements EntityMapper<Department, DepartmentDTO> {

        @Autowired
        private ModelMapper modelMapper;
        @Override
        public DepartmentDTO toDTO(Department entity) {
            return modelMapper.map(entity, DepartmentDTO.class);
        }

        @Override
        public Department toEntity(DepartmentDTO dto) {
            return modelMapper.map(dto,Department.class);
        }

        @Override
        public List<DepartmentDTO> toDTOS(List<Department> entities) {
            return modelMapper.map(entities, new TypeToken<List<DepartmentDTO>>(){}.getType());
        }
        @Override
        public List<Department> toEntities(List<DepartmentDTO> dtos) {
            return modelMapper.map(dtos, new TypeToken<List<Department>>(){}.getType());
        }
}

