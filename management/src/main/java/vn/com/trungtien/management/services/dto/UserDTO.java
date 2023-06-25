package vn.com.trungtien.management.services.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
public class UserDTO {
    private Long id;
    private String username;
    private String lastName;
    private String firstName;
    private Date createdDate;
    private Date lastModifiedDate;
    private String role;
    private String buildingName;

    private List<VehicleDTO> vehicles;

   @Getter
   @Setter
    public static class VehicleDTO {
        private Long id;
        private String accountName;
    }
}
