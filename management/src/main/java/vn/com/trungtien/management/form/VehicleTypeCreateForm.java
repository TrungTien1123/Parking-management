package vn.com.trungtien.management.form;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class VehicleTypeCreateForm {
    @NotBlank(message = "name must NOT be blank")
    private String name;
}
