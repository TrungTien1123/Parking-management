package vn.com.trungtien.management.specification;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;
import vn.com.trungtien.management.domains.entities.VehicleType;
import vn.com.trungtien.management.form.VehicleTypeFilterForm;

public class VehicleTypeSpecification {
    public static Specification<VehicleType> buildWhere(VehicleTypeFilterForm form){
        if(form == null){
            return null;
        }
        return hasNameLike(form.getSearch());
    }

    private static Specification<VehicleType> hasNameLike(String search){
        return (root, query, criteriaBuilder) -> {
            if (!StringUtils.hasText(search)){
                return null;
            }
            return criteriaBuilder.like(root.get("name"),"%"+search.trim()+"%");
        };
    }
}
