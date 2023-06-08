package vn.com.trungtien.management.services.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.util.Date;

@Data
public class BuildingDTO {
    private Long id;

    private String code;

    private String name;

    private String address;
    private Date createdDate;
    private Date lastModifiedDate;
    private Boolean status;

}
