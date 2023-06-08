package vn.com.trungtien.management.form;

import lombok.Getter;
import org.springframework.format.annotation.DateTimeFormat;
import vn.com.trungtien.management.domains.enums.RoleEnum;

import javax.validation.constraints.Pattern;
import java.time.LocalDate;
@Getter
public class UserFilterForm {
    private String search;
    @Pattern(
            regexp = "ADMIN|GUARD|STUDENT",
            message = "User role must be ADMIN or GUARD or STUDENT"
    )
    private RoleEnum role;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate createdDate;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate minCreatedDate;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate maxCreatedDate;
}
