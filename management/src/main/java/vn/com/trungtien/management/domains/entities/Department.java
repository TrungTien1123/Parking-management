package vn.com.trungtien.management.domains.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.List;

@Table(name="department")
@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class Department extends AbstractAuditingEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "code", length = 50, nullable = false)
    private String code;

    @Column(name = "name", nullable = false)
    private String name;

    @ManyToOne
    @JoinColumn(name="building_id", referencedColumnName = "id")
    private Building building;

    @OneToMany(mappedBy = "department")
    private List<Student> studentList;
}
