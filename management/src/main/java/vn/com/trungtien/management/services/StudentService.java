package vn.com.trungtien.management.services;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import vn.com.trungtien.management.domains.entities.Student;
import vn.com.trungtien.management.form.StudentCreateForm;
import vn.com.trungtien.management.form.StudentFilterForm;
import vn.com.trungtien.management.form.StudentUpdateForm;
import vn.com.trungtien.management.repositories.IStudentRepository;
import vn.com.trungtien.management.services.dto.StudentDTO;
import vn.com.trungtien.management.services.mapper.StudentMapper;
import vn.com.trungtien.management.specification.StudentSpecification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;

@Service
@RequiredArgsConstructor
public class StudentService {

    private final IStudentRepository repository;

    private final StudentMapper mapper;

    private final ModelMapper modelMapper;
    public Page<StudentDTO> findAll(Pageable pageable, StudentFilterForm form){
        Specification<Student> spec = StudentSpecification.buildWhere(form);
        Page<Student> page = repository.findAll(spec, pageable);
        List<Student> students = page.getContent();
        List<StudentDTO> dtos = mapper.toDTOS(students);
        return new PageImpl<>(dtos, pageable, page.getTotalElements());
    }
    public StudentDTO findById(Long id){
        return mapper.toDTO(repository.findById(id).orElse(null));
    }
    public void create(StudentCreateForm form){
        repository.save(modelMapper.map(form, Student.class));
    }
    public void update(StudentUpdateForm form){
        repository.save(modelMapper.map(form, Student.class));
    }
    public void deleteById(Long id){
        repository.deleteById(id);
    }
}
