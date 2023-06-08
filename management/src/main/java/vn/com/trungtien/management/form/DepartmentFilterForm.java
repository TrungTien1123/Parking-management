package vn.com.trungtien.management.form;

import lombok.Getter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
@Getter

public class DepartmentFilterForm {
    private String search;
    private String code;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate createdDate;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate minCreatedDate;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate maxCreatedDate;
}
