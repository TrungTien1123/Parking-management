package vn.com.trungtien.management.form;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class BuildingCreateForm {
    @NotBlank(message = "code must NOT be blank")
    @Length(max = 50, message = "Max length is 50 characters")
    private String code;
    @NotBlank(message = "name must NOT be blank")
    private String name;
    private String address;
}
