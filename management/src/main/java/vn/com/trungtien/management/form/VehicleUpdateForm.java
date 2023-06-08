package vn.com.trungtien.management.form;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class VehicleUpdateForm {
    @NotBlank(message = "Id must NOT be blank")
    private Long id;


    private String licensePlate;


    private String automaker;


    private String color;


    private String ticket;


    private Integer status;


    private String updatedReason;

    private String userId;

    private String vehicleTypeId;


    private String buildingId;

    private String vehicleRequestId;

}
