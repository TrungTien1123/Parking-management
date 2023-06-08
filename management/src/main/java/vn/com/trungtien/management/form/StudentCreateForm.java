package vn.com.trungtien.management.form;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class StudentCreateForm {
    @NotBlank(message = "name must NOT be blank")
    private String name;
    @Length(max=10, message = "The max length of phone number is 10 characters")
    private String phone;
    private String className;
    @NotBlank(message = "Department Id must NOT be blank")
    private Long departmentId;
}
