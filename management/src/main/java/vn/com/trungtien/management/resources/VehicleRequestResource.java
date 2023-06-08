package vn.com.trungtien.management.resources;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import vn.com.trungtien.management.form.VehicleRequestCreateForm;
import vn.com.trungtien.management.form.VehicleRequestFilterForm;
import vn.com.trungtien.management.form.VehicleRequestUpdateForm;
import vn.com.trungtien.management.services.VehicleRequestService;
import vn.com.trungtien.management.services.dto.VehicleRequestDTO;

import javax.validation.Valid;
@RestController
@RequiredArgsConstructor
@RequestMapping("api/vehicle-request")
@Validated
public class VehicleRequestResource {
    private final VehicleRequestService service;
    @GetMapping()
    public Page<VehicleRequestDTO> findAll(Pageable pageable, VehicleRequestFilterForm form){
        return service.findAll(pageable, form);
    }

    @GetMapping("/{id}")
    public VehicleRequestDTO findById(@PathVariable("id") Long id){
        return service.findById(id);
    }

    @PostMapping()
    public void  create( @Valid @RequestBody VehicleRequestCreateForm form){
        service.create(form);
    }

    @PutMapping("/{id}")
    public void update(@Valid @PathVariable Long id,@Valid @RequestBody VehicleRequestUpdateForm form){
        form.setId(id);
        service.update(form);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id){
        service.deleteById(id);
    }
}
