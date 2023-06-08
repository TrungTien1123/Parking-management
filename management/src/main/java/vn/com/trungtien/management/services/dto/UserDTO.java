package vn.com.trungtien.management.services.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class UserDTO {
    private Long id;
    private String username;

    private String buildingName;

    private String role;

    private List<VehicleDTO> vehicles;

   @Getter
   @Setter
    public static class VehicleDTO {
        private Long id;
        private String accountName;
    }
}
