package vn.com.trungtien.management.services.mapper;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import vn.com.trungtien.management.domains.entities.Student;
import vn.com.trungtien.management.services.dto.StudentDTO;

import java.util.List;

@Component
public class StudentMapper implements EntityMapper<Student, StudentDTO> {

    @Autowired
    private ModelMapper modelMapper;
    @Override
    public StudentDTO toDTO(Student entity) {
        return modelMapper.map(entity, StudentDTO.class);
    }

    @Override
    public Student toEntity(StudentDTO dto) {
        return modelMapper.map(dto,Student.class);
    }

    @Override
    public List<StudentDTO> toDTOS(List<Student> entities) {
        return modelMapper.map(entities, new TypeToken<List<StudentDTO>>(){}.getType());
    }
    @Override
    public List<Student> toEntities(List<StudentDTO> dtos) {
        return modelMapper.map(dtos, new TypeToken<List<Student>>(){}.getType());
    }
}