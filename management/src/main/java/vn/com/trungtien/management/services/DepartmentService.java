package vn.com.trungtien.management.services;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import vn.com.trungtien.management.domains.entities.Department;
import vn.com.trungtien.management.form.DepartmentCreateForm;
import vn.com.trungtien.management.form.DepartmentFilterForm;
import vn.com.trungtien.management.form.DepartmentUpdateForm;
import vn.com.trungtien.management.repositories.IDepartmentRepository;
import vn.com.trungtien.management.services.dto.DepartmentDTO;
import vn.com.trungtien.management.services.mapper.DepartmentMapper;
import vn.com.trungtien.management.specification.DepartmentSpecification;

import java.util.List;
@Service
@RequiredArgsConstructor
public class DepartmentService {

    private final IDepartmentRepository repository;
    private final DepartmentMapper mapper;

    private final  ModelMapper modelMapper;
    public Page<DepartmentDTO> findAll(Pageable pageable, DepartmentFilterForm form){
        Specification<Department> spec = DepartmentSpecification.buildWhere(form);
        Page<Department> page = repository.findAll(spec,pageable);
        List<Department> departments = page.getContent();
        List<DepartmentDTO> dtos = mapper.toDTOS(departments);
        return new PageImpl<>(dtos, pageable, page.getTotalElements());
    }
    public DepartmentDTO findById(Long id){
        return mapper.toDTO(repository.findById(id).orElse(null));
    }
    public void create(DepartmentCreateForm form){
        repository.save(modelMapper.map(form, Department.class));
/*      Hàm tạo trực tiếp List Student kèm theo Department ( phải thêm @Transactional)
        Department department = modelMapper.map(form, Department.class);
        Department saved = repository.save(department);
        List<Student> students = department.getStudentList();
        for (Student student : students) {
            student.setDepartment(saved);
        }
        studentRepository.saveAll(students);*/
    }
    public void update(DepartmentUpdateForm form){
        repository.save(modelMapper.map(form, Department.class));
    }
    public void deleteById(Long id){
        repository.deleteById(id);
    }

}
