package vn.com.trungtien.management.resources;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import vn.com.trungtien.management.form.StudentCreateForm;
import vn.com.trungtien.management.form.StudentFilterForm;
import vn.com.trungtien.management.form.StudentUpdateForm;
import vn.com.trungtien.management.services.StudentService;
import vn.com.trungtien.management.services.dto.StudentDTO;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/student")
@Validated
public class StudentResource {

    private final StudentService service;

    @GetMapping()
    public Page<StudentDTO> findAll(Pageable pageable, StudentFilterForm form){
        return service.findAll(pageable, form);
    }

    @GetMapping("/{id}")
    public StudentDTO findById(@PathVariable("id") Long id){
        return service.findById(id);
    }

    @PostMapping()
    public void  create( @Valid @RequestBody StudentCreateForm form){
        service.create(form);
    }

    @PutMapping("/{id}")
    public void update(@Valid @PathVariable Long id,@Valid @RequestBody StudentUpdateForm form){
        form.setId(id);
        service.update(form);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id){
        service.deleteById(id);
    }
}
