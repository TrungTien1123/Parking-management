package vn.com.trungtien.management.form;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;
import vn.com.trungtien.management.domains.entities.Vehicle;
import vn.com.trungtien.management.services.dto.VehicleDTO;

import javax.persistence.Column;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.time.Instant;
import java.util.List;
@Getter
@Setter

public class VehicleRequestCreateForm {
    private String pic;

    private Instant picDate;

    @Length(max=400, message = "The max length of note is 400 characters")
    private String note;
    @NotEmpty
    private List<VehicleDTO> vehicleDTOS;

    @Getter
    @Setter
    public static class VehicleDTO{
        private String vehicleTypeId;
        @NotBlank(message = "userId must NOT be blank")
        private String userId;
    }


}
