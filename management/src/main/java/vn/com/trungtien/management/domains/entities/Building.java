package vn.com.trungtien.management.domains.entities;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.List;

@Table(name = "building")
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class Building extends AbstractAuditingEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "code", length = 50, unique = true)
    private String code;

    @Column(name = "name")
    private String name;

    @Column(name = "address", length = 1000)
    private String address;

    @Column(name = "area")
    private Double area;

    @Column(name = "status")
    private Boolean status;

    @Column(name = "location", length = 50)
    private String location;

    @OneToMany(mappedBy = "building")
    private List<User> userList;

    @OneToMany(mappedBy = "building")
    private List<Vehicle> vehicleList;

    @OneToMany(mappedBy = "building")
    private List<Department> departmentList;
}
