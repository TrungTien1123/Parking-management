package vn.com.trungtien.management.domains.enums;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorEnum {
    BUILDING_NOT_FOUND("Không tìm thấy trường học", "building_not_found"),
    BUILDING_CODE_EXIST("Code đã tồn tại", "building_code_exist"),
    USER_NOT_FOUND("Tài khoản hoặc mật khẩu không chính xác", "incorrect_username_or_password");


    private String msg;
    private String code;
}
