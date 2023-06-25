package vn.com.trungtien.management.form;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.Instant;
import java.util.List;
@Getter
@Setter
public class VehicleRequestUpdateForm {
    @NotNull(message = "Id must NOT be blank")
    private Long id;
    private String pic;

    private Instant picDate;

    @Length(max=400, message = "The max length of note is 400 characters")
    private String note;

    private List<VehicleRequestCreateForm.VehicleDTO> vehicleDTOS;

    @Getter
    @Setter
    public static class VehicleDTO{
        private String vehicleTypeId;
        @NotBlank(message = "userId must NOT be blank")
        @JsonProperty("vehicleId")
        private Long id;
    }
}
