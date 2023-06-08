package vn.com.trungtien.management.resources;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityNotFoundException;

@RestController
@RequestMapping("api/exceptions")
public class ExceptionResource {
    @GetMapping
    public void throwException(){
        throw new EntityNotFoundException("....Exception Information");
    }
}
