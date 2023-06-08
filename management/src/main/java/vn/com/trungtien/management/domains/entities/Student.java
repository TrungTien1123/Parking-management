package vn.com.trungtien.management.domains.entities;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.List;

@Table(name = "student")
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class Student extends AbstractAuditingEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "phone", length = 10)
    private String phone;

    @Column(name = "address")
    private String address;

    @Column(name = "class_name",nullable = false)
    private String className;

    @ManyToOne
    @JoinColumn(name="department_id", referencedColumnName = "id", nullable = false)
    private Department department;

    @OneToMany(mappedBy = "student")
    private List<User> userList;
}