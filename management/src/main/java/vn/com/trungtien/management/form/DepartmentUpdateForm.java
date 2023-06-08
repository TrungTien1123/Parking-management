package vn.com.trungtien.management.form;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class DepartmentUpdateForm {
    private String name;
    @NotNull(message = "id must NOT be blank")
    private Long id;

}
