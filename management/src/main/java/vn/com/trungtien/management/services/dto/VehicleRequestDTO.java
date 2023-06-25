package vn.com.trungtien.management.services.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import vn.com.trungtien.management.domains.enums.VehicleRequestStatusEnum;

import java.time.Instant;
import java.util.List;
@Getter
@Setter
public class VehicleRequestDTO {
    private String code;

    private VehicleRequestStatusEnum status;

    private String pic;

    private Instant picDate;

    private String note;

    private List<VehicleDTO> vehicleDTOS;

    @Getter
    @Setter
    public static class VehicleDTO{
        @JsonProperty("vehicleId")
        private Long id;
        private String vehicleTypeId;
    }
}
