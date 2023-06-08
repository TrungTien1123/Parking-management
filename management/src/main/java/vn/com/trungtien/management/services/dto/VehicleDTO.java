package vn.com.trungtien.management.services.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class VehicleDTO {
    private String id;
    private Integer status;
    private String color;
    private String ticket;
    private String vehicleTypeId;
    private String userId;


}
