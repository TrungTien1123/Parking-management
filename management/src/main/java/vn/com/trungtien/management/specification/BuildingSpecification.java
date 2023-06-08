package vn.com.trungtien.management.specification;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;
import vn.com.trungtien.management.domains.entities.Building;
import vn.com.trungtien.management.form.BuildingFilterForm;

import java.time.LocalDate;

public class BuildingSpecification {
    public static Specification<Building> buildWhere(BuildingFilterForm form) {
        if (form == null) {
            return null;
        }
        return hasCreatedDateLessThanOrEqualTo(form.getMaxCreatedDate())
                .and(hasCreatedDateGreaterThanOrEqualTo(form.getMinCreatedDate()))
                .and(hasNameLike(form.getSearch()))
                .and(hasCodeLike(form.getSearch()))
                .and(hasAddressLike(form.getSearch()))
                .and(hasLocationLike(form.getSearch()));
    }
    private static Specification<Building> hasNameLike(String search){
        return (root, query, criteriaBuilder) -> {
            if (!StringUtils.hasText(search)){
                return null;
            }
            return criteriaBuilder.like(root.get("name"),"%"+search.trim()+"%");
        };
    }
    private static Specification<Building> hasCodeLike(String search){
        return (root, query, criteriaBuilder) -> {
            if (!StringUtils.hasText(search)){
                return null;
            }
            return criteriaBuilder.like(root.get("code"),"%"+search.trim()+"%");
        };
    }
    private static Specification<Building> hasLocationLike(String search){
        return (root, query, criteriaBuilder) -> {
            if (!StringUtils.hasText(search)){
                return null;
            }
            return criteriaBuilder.like(root.get("location"),"%"+search.trim()+"%");
        };
    }
    private static Specification<Building> hasAddressLike(String search){
        return (root, query, criteriaBuilder) -> {
            if (!StringUtils.hasText(search)){
                return null;
            }
            return criteriaBuilder.like(root.get("address"),"%"+search.trim()+"%");
        };
    }


    public static Specification<Building> hasCreatedDateGreaterThanOrEqualTo(LocalDate minCreatedDate) {
        return (root, query, criteriaBuilder) -> {
            if (minCreatedDate == null) {
                return null;
            }
            return criteriaBuilder.greaterThanOrEqualTo(root.get("createdDate").as(LocalDate.class), minCreatedDate);
        };
    }
    public static Specification<Building> hasCreatedDateLessThanOrEqualTo(LocalDate maxCreatedDate) {
        return (root, query, criteriaBuilder) -> {
            if (maxCreatedDate == null) {
                return null;
            }
            return criteriaBuilder.lessThanOrEqualTo(root.get("createdDate").as(LocalDate.class), maxCreatedDate);
        };
    }
}

