package me.medicationdispenser.api.models;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@Table(name = "administrations")
public class Administration implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "administration_id", nullable = false, updatable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "prescription_id", referencedColumnName = "prescription_id")
    private Prescription prescription;

    @Column(name = "administration_date")
    private LocalDateTime administerDate;

}
