package vn.com.trungtien.management.services.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
public class DepartmentDTO {
    private Long id;
    private String name;
    private LocalDate createdDate;
    private List<StudentDTO> studentDTOS;

    @Getter
    @Setter
    public static class StudentDTO{
        private Long id;
        private String name;
    }
}
