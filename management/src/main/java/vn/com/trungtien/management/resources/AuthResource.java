package vn.com.trungtien.management.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import vn.com.trungtien.management.form.UserCreateForm;
import vn.com.trungtien.management.services.UserService;
import vn.com.trungtien.management.services.dto.ProfileDTO;

import javax.validation.Valid;
import java.security.Principal;

@RestController
@Validated
@RequestMapping("api/auth")
public class AuthResource {
    @Autowired
    private UserService service;
    @GetMapping("/login")
    public ProfileDTO login(Principal principal){
        String username = principal.getName();
        return service.findByUsername(username);
    }
    @PostMapping("/register")
    public void register(@Valid @RequestBody UserCreateForm form){
        service.create(form);
    }
}
