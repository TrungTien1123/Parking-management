package vn.com.trungtien.management.specification;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;
import vn.com.trungtien.management.domains.entities.Student;
import vn.com.trungtien.management.form.StudentFilterForm;

public class StudentSpecification {
    public static Specification<Student> buildWhere(StudentFilterForm form){
        if(form == null){
            return null;
        }
        return hasNameLike(form.getSearch())
                .or(hasDeparmentNameLike(form.getSearch()));
    }

    private static Specification<Student> hasNameLike(String search){
        return (root, query, criteriaBuilder) -> {
            if (!StringUtils.hasText(search)){
                return null;
            }
            return criteriaBuilder.like(root.get("name"),"%"+search.trim()+"%");
        };
    }
    private static Specification<Student> hasDeparmentNameLike(String search){
        return (root, query, criteriaBuilder) -> {
            if (!StringUtils.hasText(search)){
                return null;
            }
            return criteriaBuilder.like(root.get("department").get("name"),"%"+search.trim()+"%");
        };
    }
}
