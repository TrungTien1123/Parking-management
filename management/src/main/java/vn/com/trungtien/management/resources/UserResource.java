package vn.com.trungtien.management.resources;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import vn.com.trungtien.management.form.UserCreateForm;
import vn.com.trungtien.management.form.UserFilterForm;
import vn.com.trungtien.management.form.UserUpdateForm;
import vn.com.trungtien.management.services.UserService;
import vn.com.trungtien.management.services.dto.UserDTO;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/user")
@Validated
public class UserResource {

    private final UserService service;

    @GetMapping()
    public Page<UserDTO> findAll(Pageable pageable, UserFilterForm form){
        return service.findAll(pageable, form);
    }

    @GetMapping("/{id}")
    public UserDTO findById(@PathVariable("id") Long id){
        return service.findById(id);
    }

    @PostMapping()
    public void  create( @Valid  @RequestBody UserCreateForm form){
        service.create(form);
    }

    @PutMapping("/{id}")
    public void update(@Valid @PathVariable Long id,@Valid @RequestBody UserUpdateForm form){
        form.setId(id);
        service.update(form);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id){
        service.deleteById(id);
    }
}
