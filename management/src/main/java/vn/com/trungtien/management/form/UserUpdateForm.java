package vn.com.trungtien.management.form;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class UserUpdateForm {
    @NotNull(message = "Id không thể để trống")
    private Long id;
    private String password;
    private String firstName;
    private String lastName;
    private String role;
    private String phone;
    private String email;
    private String buildingId;
    private String studentId;
}
