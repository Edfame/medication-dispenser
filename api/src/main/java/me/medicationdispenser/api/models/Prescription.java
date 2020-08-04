package me.medicationdispenser.api.models;

import javax.persistence.*;

@Entity
public class Prescription {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false, updatable = false)
    private long prescription_id;

    private long drug_id;
    private long user_id;
    private int prescription_periodicity;

    public Prescription() {

    }
}
