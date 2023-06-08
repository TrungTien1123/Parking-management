package vn.com.trungtien.management.domains.entities;

import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.List;

@Table(name = "vehicle_type")
    @Entity
    @AllArgsConstructor
    @NoArgsConstructor
    @Getter
    @Setter
@EntityListeners(AuditingEntityListener.class)
    public class VehicleType {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "id", nullable = false)
        private Long id;

        @Column(name = "name")
        private String name;

        @OneToMany(mappedBy = "vehicleType")
        private List<Vehicle> vehicleList;
    }

