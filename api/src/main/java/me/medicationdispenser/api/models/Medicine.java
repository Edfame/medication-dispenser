package me.medicationdispenser.api.models;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Data
@NoArgsConstructor
@Table(name = "medicines")
public class Medicine implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "medicine_id", nullable = false, updatable = false)
    private Long id;

    @Column(name = "medicine_name")
    private String name;

     @Column(name = "medicine_dose")
    private int dose;

    public Medicine(String name) {

        this.name = name;

    }

    public Medicine(String name, int dose) {

        this.name = name;
        this.dose = dose;

    }
}
