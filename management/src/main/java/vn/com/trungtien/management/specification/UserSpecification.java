package vn.com.trungtien.management.specification;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;
import vn.com.trungtien.management.domains.entities.User;
import vn.com.trungtien.management.domains.enums.RoleEnum;
import vn.com.trungtien.management.form.UserFilterForm;

public class UserSpecification {
    public static Specification<User> buildWhere(UserFilterForm form){
        if(form == null){
            return null;
        }
        return hasNameLike(form.getSearch())
                .or(hasDeparmentNameLike(form.getSearch()))
                .and(hasRoleEqual(form.getRole()))
                .and(hasEmailLike(form.getSearch()));
    }

    private static Specification<User> hasNameLike(String search){
        return (root, query, criteriaBuilder) -> {
            if (!StringUtils.hasText(search)){
                return null;
            }
            return criteriaBuilder.like(root.get("fullName"),"%"+search.trim()+"%");
        };
    }
    private static Specification<User> hasDeparmentNameLike(String search){
        return (root, query, criteriaBuilder) -> {
            if (!StringUtils.hasText(search)){
                return null;
            }
            return criteriaBuilder.like(root.get("department").get("name"),"%"+search.trim()+"%");
        };
    }
    private static Specification<User> hasEmailLike(String search){
        return (root, query, criteriaBuilder) -> {
            if (!StringUtils.hasText(search)){
                return null;
            }
            return criteriaBuilder.like(root.get("email"),"%"+search.trim()+"%");
        };
    }

    private static Specification<User> hasRoleEqual(RoleEnum role){
        return (root, query, criteriaBuilder) -> {
            if(role == null){
                return null;
            }
            return criteriaBuilder.equal(root.get("role"),role);
        };
    }
}
