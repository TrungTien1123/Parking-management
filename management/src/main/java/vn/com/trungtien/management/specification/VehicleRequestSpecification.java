package vn.com.trungtien.management.specification;

import org.springframework.data.jpa.domain.Specification;
import vn.com.trungtien.management.domains.entities.VehicleRequest;
import vn.com.trungtien.management.domains.enums.VehicleRequestStatusEnum;
import vn.com.trungtien.management.form.VehicleRequestFilterForm;

import java.time.LocalDate;

public class VehicleRequestSpecification {
    public static Specification<VehicleRequest> buildWhere(VehicleRequestFilterForm form) {
        if (form == null) {
            return null;
        }
        return hasCreatedDateEqual(form.getCreatedDate())
                .and(hasCreatedDateLessThanOrEqualTo(form.getMaxCreatedDate()))
                .and(hasCreatedDateGreaterThanOrEqualTo(form.getMinCreatedDate()))
                .and(hasStatusEqual(form.getStatus()));
    }

    public static Specification<VehicleRequest> hasCreatedDateEqual(LocalDate createdDate) {
        return (root, query, criteriaBuilder) -> {
            if (createdDate == null) {
                return null;
            }
            return criteriaBuilder.equal(root.get("createdDate").as(LocalDate.class), createdDate);
        };
    }

    public static Specification<VehicleRequest> hasCreatedDateGreaterThanOrEqualTo(LocalDate minCreatedDate) {
        return (root, query, criteriaBuilder) -> {
            if (minCreatedDate == null) {
                return null;
            }
            return criteriaBuilder.greaterThanOrEqualTo(root.get("createdDate").as(LocalDate.class), minCreatedDate);
        };
    }
    public static Specification<VehicleRequest> hasCreatedDateLessThanOrEqualTo(LocalDate maxCreatedDate) {
        return (root, query, criteriaBuilder) -> {
            if (maxCreatedDate == null) {
                return null;
            }
            return criteriaBuilder.lessThanOrEqualTo(root.get("createdDate").as(LocalDate.class), maxCreatedDate);
        };
    }
    private static Specification<VehicleRequest> hasStatusEqual(VehicleRequestStatusEnum status){
        return (root, query, criteriaBuilder) -> {
            if(status == null){
                return null;
            }
            return criteriaBuilder.equal(root.get("status"),status);
        };
    }
}
