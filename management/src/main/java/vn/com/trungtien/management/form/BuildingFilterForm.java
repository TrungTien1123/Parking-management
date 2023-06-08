package vn.com.trungtien.management.form;

import lombok.Getter;

import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Getter
public class BuildingFilterForm {
    private String search;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate minCreatedDate;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate maxCreatedDate;
}
