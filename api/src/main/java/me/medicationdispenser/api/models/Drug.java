package me.medicationdispenser.api.models;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Drug {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false, updatable = false)
    private long drugId;

    private String drugName;

    public Drug(String drugname) {

        this.drugName = drugname;

    }
}
