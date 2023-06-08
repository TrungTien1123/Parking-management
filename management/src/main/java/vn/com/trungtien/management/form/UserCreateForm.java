package vn.com.trungtien.management.form;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.persistence.Column;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
@Getter
@Setter
public class UserCreateForm {
    @NotBlank(message = " Username must NOT be blank")
    @Length(max=50, message = "The max length is 50 characters")
    private String username;
    @NotBlank(message = " Password must NOT be blank")
    @Length(max = 72, min = 8, message = "The length is between 8 and 72 characters")
    private String password;
    @NotBlank(message = " First name must NOT be blank")
    private String firstName;
    @NotBlank(message = " Last name must NOT be blank")
    private String lastName;
    @Length(max = 10, message = "The max length of phone number is 10 characters")
    private String phone;
    @Pattern(
        regexp = "ADMIN|GUARD|STUDENT",
        message = "User role must be ADMIN or GUARD or STUDENT"
    )
    private String role;
    @NotNull(message = "Building Id must NOT be blank")
    private Long buildingId;
}
