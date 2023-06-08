package vn.com.trungtien.management.resources;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import vn.com.trungtien.management.form.VehicleTypeCreateForm;
import vn.com.trungtien.management.form.VehicleTypeFilterForm;
import vn.com.trungtien.management.form.VehicleTypeUpdateForm;
import vn.com.trungtien.management.services.VehicleTypeService;
import vn.com.trungtien.management.services.dto.VehicleTypeDTO;

import javax.validation.Valid;
@RestController
@RequiredArgsConstructor
@RequestMapping("api/vehicle-type")
@Validated
public class VehicleTypeResource {
    private final VehicleTypeService service;
    @GetMapping()
    public Page<VehicleTypeDTO> findAll(Pageable pageable, VehicleTypeFilterForm form){
        return service.findAll(pageable, form);
    }

    @GetMapping("/{id}")
    public VehicleTypeDTO findById(@PathVariable("id") Long id){
        return service.findById(id);
    }

    @PostMapping()
    public void  create( @Valid @RequestBody VehicleTypeCreateForm form){
        service.create(form);
    }

    @PutMapping("/{id}")
    public void update(@Valid @PathVariable Long id,@Valid @RequestBody VehicleTypeUpdateForm form){
        form.setId(id);
        service.update(form);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id){
        service.deleteById(id);
    }
}
