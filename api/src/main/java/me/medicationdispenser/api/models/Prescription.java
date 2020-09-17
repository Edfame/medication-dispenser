package me.medicationdispenser.api.models;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
public class Prescription {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false, updatable = false)
    private long prescriptionId;

    private long drugId;
    private long userId;
    private int prescriptionPeriodicity;

    public Prescription(long drugId, long userId, int prescriptionPeriodicity) {
        this.drugId = drugId;
        this.userId = userId;
        this.prescriptionPeriodicity = prescriptionPeriodicity;

    }
}
