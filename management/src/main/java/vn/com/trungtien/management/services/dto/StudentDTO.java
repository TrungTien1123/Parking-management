package vn.com.trungtien.management.services.dto;


import lombok.Data;

@Data
public class StudentDTO {
    private Long id;

    private String name;

    private String phone;

    private String address;

    private String className;

    private String departmentName;
}
