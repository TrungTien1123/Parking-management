package vn.com.trungtien.management.specification;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;
import vn.com.trungtien.management.domains.entities.Vehicle;
import vn.com.trungtien.management.domains.entities.Student;
import vn.com.trungtien.management.form.VehicleFilterForm;

import java.time.LocalDate;

public class VehicleSpecification {
    public static Specification<Vehicle> buildWhere(VehicleFilterForm form) {
        if (form == null) {
            return null;
        }
        return hasCreatedDateEqual(form.getCreatedDate())
                .and(hasCreatedDateLessThanOrEqualTo(form.getMaxCreatedDate()))
                .and(hasCreatedDateGreaterThanOrEqualTo(form.getMinCreatedDate()))
                .and(hasNameLike(form.getSearch()));
    }
    private static Specification<Vehicle> hasNameLike(String search){
        return (root, query, criteriaBuilder) -> {
            if (!StringUtils.hasText(search)){
                return null;
            }
            return criteriaBuilder.like(root.get("name"),"%"+search.trim()+"%");
        };
    }

    public static Specification<Vehicle> hasCreatedDateEqual(LocalDate createdDate) {
        return (root, query, criteriaBuilder) -> {
            if (createdDate == null) {
                return null;
            }
            return criteriaBuilder.equal(root.get("createdDate").as(LocalDate.class), createdDate);
        };
    }

    public static Specification<Vehicle> hasCreatedDateGreaterThanOrEqualTo(LocalDate minCreatedDate) {
        return (root, query, criteriaBuilder) -> {
            if (minCreatedDate == null) {
                return null;
            }
            return criteriaBuilder.greaterThanOrEqualTo(root.get("createdDate").as(LocalDate.class), minCreatedDate);
        };
    }
    public static Specification<Vehicle> hasCreatedDateLessThanOrEqualTo(LocalDate maxCreatedDate) {
        return (root, query, criteriaBuilder) -> {
            if (maxCreatedDate == null) {
                return null;
            }
            return criteriaBuilder.lessThanOrEqualTo(root.get("createdDate").as(LocalDate.class), maxCreatedDate);
        };
    }
}

