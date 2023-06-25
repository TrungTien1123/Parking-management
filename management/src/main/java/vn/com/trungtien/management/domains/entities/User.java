package vn.com.trungtien.management.domains.entities;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Formula;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.security.core.userdetails.UserDetails;
import vn.com.trungtien.management.domains.enums.RoleEnum;

import javax.persistence.*;
import java.util.List;

@Table(name = "user")
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EntityListeners(AuditingEntityListener.class)
public class User extends AbstractAuditingEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    @Column(name = "username", length = 50, unique = true, updatable = false)
    private String username;

    @Column(name = "password", length = 72, nullable = false)
    private String password;

    @Column(name = "first_name", length = 50,nullable = false)
    private String firstName;

    @Column(name = "last_name", length = 50,nullable = false)
    private String lastName;

    @Formula(" concat(first_name, ' ', last_name) ")
    private String fullName;

    @Column(name = "role")
    @Enumerated(EnumType.STRING)
    @Value("STUDENT")
    private RoleEnum role;

    @Column(name = "phone", length = 10)
    private String phone;

    @Column(name = "email")
    private String email;

    @Column(name = "status", length = 50)
    private String status;

    @ManyToOne
    @JoinColumn(name = "building_id", referencedColumnName = "id")
    private Building building;

    @ManyToOne
    @JoinColumn(name = "student_id", referencedColumnName = "id", unique = true)
    private Student student;

    @OneToMany(mappedBy = "user")
    private List<Vehicle> vehicleList;
}