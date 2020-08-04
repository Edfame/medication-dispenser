package me.medicationdispenser.api.models;

import lombok.Data;

import javax.persistence.*;

@Entity
public class Drug {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false, updatable = false)
    private long drug_id;

    private String drug_name;

    public Drug(String drugname) {

        this.drug_name = drugname;

    }

    public Drug() {

    }

    public String getDrugName() {
        return drug_name;
    }

    public void setDrugName(String drug_name) {
        this.drug_name = drug_name;
    }

    @Override
    public String toString() {
        return "Drug{" +
                "drug_id='" + drug_id + '\'' +
                ", drug_name='" + drug_name + '\'' +
                '}';
    }
}
