package vn.com.trungtien.management.resources;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import vn.com.trungtien.management.form.VehicleCreateForm;
import vn.com.trungtien.management.form.VehicleFilterForm;
import vn.com.trungtien.management.form.VehicleUpdateForm;
import vn.com.trungtien.management.services.VehicleService;
import vn.com.trungtien.management.services.dto.VehicleDTO;

import javax.validation.Valid;
@RestController
@RequiredArgsConstructor
@RequestMapping("api/vehicle")
@Validated
public class VehicleResource {
    private final VehicleService service;
    @GetMapping()
    public Page<VehicleDTO> findAll(Pageable pageable, VehicleFilterForm form){
        return service.findAll(pageable, form);
    }

    @GetMapping("/{id}")
    public VehicleDTO findById(@PathVariable("id") Long id){
        return service.findById(id);
    }

    @PostMapping()
    public void  create( @Valid @RequestBody VehicleCreateForm form){
        service.create(form);
    }

    @PutMapping("/{id}")
    public void update(@Valid @PathVariable Long id,@Valid @RequestBody VehicleUpdateForm form){
        form.setId(id);
        service.update(form);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id){
        service.deleteById(id);
    }
}
