package vn.com.trungtien.management.form;

import lombok.Getter;
import org.springframework.format.annotation.DateTimeFormat;
import vn.com.trungtien.management.domains.enums.RoleEnum;
import vn.com.trungtien.management.domains.enums.VehicleRequestStatusEnum;

import javax.validation.constraints.Pattern;
import java.time.LocalDate;
@Getter

public class VehicleRequestFilterForm {
    private String search;
    @Pattern(
            regexp = "PENDING|APPROVED|CANCELED",
            message = "User role must be PENDING or APPROVED or CANCELED"
    )
    private VehicleRequestStatusEnum status;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate createdDate;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate minCreatedDate;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate maxCreatedDate;
}
