package vn.com.trungtien.management.domains.entities;



import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

@Table(name = "vehicle_history")
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EntityListeners(AuditingEntityListener.class)
public class VehicleHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "username")
    private String username;

    @Column(name = "vehicle_type_id")
    private Long vehicleTypeId;

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

    @Column(name = "building_id")
    private Long buildingId;

    @Column(name = "updated_reason", length = 4000)
    private String updatedReason;

    @ManyToOne
    @JoinColumn(name = "vehicle_id", referencedColumnName = "id")
    private Vehicle vehicle;
}