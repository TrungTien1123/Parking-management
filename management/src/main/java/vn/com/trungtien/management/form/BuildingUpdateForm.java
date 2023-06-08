package vn.com.trungtien.management.form;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class BuildingUpdateForm {
    @NotNull(message = "Id không thể để trống")
    private Long id;

    private String code;

    private String name;

    private String address;

    private Double area;

    private Boolean status;

    private String location;
}
