package vn.com.trungtien.management.resources;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import vn.com.trungtien.management.form.BuildingCreateForm;
import vn.com.trungtien.management.form.BuildingFilterForm;
import vn.com.trungtien.management.form.BuildingUpdateForm;
import vn.com.trungtien.management.services.BuildingService;
import vn.com.trungtien.management.services.dto.BuildingDTO;

import javax.validation.Valid;
@RestController
@RequiredArgsConstructor
@RequestMapping("api/building")
@Validated
public class BuildingResource {
    private final BuildingService service;
    @GetMapping()
    public Page<BuildingDTO> findAll(Pageable pageable, BuildingFilterForm form){
        return service.findAll(pageable, form);
    }

    @GetMapping("/{id}")
    public BuildingDTO findById(@PathVariable("id") Long id){
        return service.findById(id);
    }

    @PostMapping()
    public void  create( @Valid @RequestBody BuildingCreateForm form){
        service.create(form);
    }

    @PutMapping("/{id}")
    public void update(@Valid @PathVariable Long id,@Valid @RequestBody BuildingUpdateForm form){
        form.setId(id);
        service.update(form);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id){
        service.deleteById(id);
    }
}
