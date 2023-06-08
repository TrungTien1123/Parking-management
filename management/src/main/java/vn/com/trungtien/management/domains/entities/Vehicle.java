package vn.com.trungtien.management.domains.entities;


import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.List;

@Table(name = "vehicle")
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class Vehicle  {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "license_plate", length = 200)
    private String licensePlate;

    @Column(name = "automaker", length = 500)
    private String automaker;

    @Column(name = "color", length = 50)
    private String color;

    @Column(name = "ticket", length = 100)
    private String ticket;

    @Column(name = "status")
    private Integer status;

    @Column(name = "updated_reason", length = 4000)
    private String updatedReason;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "vehicle_type_id")
    private VehicleType vehicleType;

    @ManyToOne
    @JoinColumn(name = "building_id")
    private Building building;

    @ManyToOne
    @JoinColumn(name = "vehicle_request_id")
    private VehicleRequest vehicleRequest;

    @OneToMany(mappedBy = "vehicle")
    private List<VehicleHistory> vehicleHistoryList;
}