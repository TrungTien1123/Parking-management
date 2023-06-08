package vn.com.trungtien.management.services.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProfileDTO {
    private Long id;
    private String username;
    private String fullName;
    private String role;
    private String buildingName;
}
