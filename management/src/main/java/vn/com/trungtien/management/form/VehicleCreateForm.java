package vn.com.trungtien.management.form;

import lombok.Getter;
import lombok.Setter;
import vn.com.trungtien.management.domains.entities.*;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;
import java.util.List;

@Getter
@Setter
public class VehicleCreateForm {


    private String licensePlate;


    private String automaker;


    private String color;


    private String ticket;

    @NotBlank(message = "userId must NOT be blank")
    private String userId;

}
