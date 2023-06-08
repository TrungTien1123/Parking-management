package vn.com.trungtien.management.resources;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import vn.com.trungtien.management.form.DepartmentCreateForm;
import vn.com.trungtien.management.form.DepartmentFilterForm;
import vn.com.trungtien.management.form.DepartmentUpdateForm;
import vn.com.trungtien.management.services.DepartmentService;
import vn.com.trungtien.management.services.dto.DepartmentDTO;

import javax.validation.Valid;

@RequestMapping("api/department")
@RequiredArgsConstructor
@RestController
@Validated
public class DepartmentResource {

    private final DepartmentService service;

    @GetMapping()
    public Page<DepartmentDTO> findAll(Pageable pageable, DepartmentFilterForm form){
        return service.findAll(pageable, form);
    }

    @GetMapping("/{id}")
    public DepartmentDTO findById(@PathVariable("id") Long id){
        return service.findById(id);
    }

    @PostMapping()
    public void  create( @Valid @RequestBody DepartmentCreateForm form){
        service.create(form);
    }

    @PutMapping("/{id}")
    public void update(@Valid @PathVariable Long id,@Valid @RequestBody DepartmentUpdateForm form){
        form.setId(id);
        service.update(form);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id){
        service.deleteById(id);
    }
}



