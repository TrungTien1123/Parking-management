package vn.com.trungtien.management.form;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class StudentUpdateForm {
    @NotNull(message = "id must NOT be blank")
    private Long id;

    private String name;

    private String phone;

    private String address;

    private String className;
    private Long departmentId;
}
