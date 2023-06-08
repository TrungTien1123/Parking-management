package vn.com.trungtien.management.form;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class VehicleTypeUpdateForm {
    @NotNull(message = "Id must NOT be blank")
    private Long id;
    private String name;
}
