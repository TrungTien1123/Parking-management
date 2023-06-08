package vn.com.trungtien.management.form;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class DepartmentCreateForm {

    @NotBlank(message = "Code không thể để trống")
    private String code;
    @NotBlank(message = "name must NOT be blank")
    private String name;

}
