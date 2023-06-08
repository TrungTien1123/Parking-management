package vn.com.trungtien.management.domains.entities;

import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import vn.com.trungtien.management.domains.enums.VehicleRequestStatusEnum;

import javax.persistence.*;
import java.time.Instant;
import java.util.List;

@Table(name = "vehicle_request")
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class VehicleRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "code", length = 50)
    private String code;

    @Column(name = "status", length = 20)
    @Enumerated(EnumType.STRING)
    private VehicleRequestStatusEnum status;

    @Column(name = "pic")
    private String pic;

    @Column(name = "pic_date")
    private Instant picDate;

    @Column(name = "note", length = 4000)
    private String note;

    @OneToMany(mappedBy = "vehicleRequest")
    private List<Vehicle> vehicleList;
}