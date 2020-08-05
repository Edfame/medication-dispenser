package me.medicationdispenser.api.models;

import javax.persistence.*;

@Entity
public class DrugUser {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false, updatable = false)
    private long drugUser_id;

    private String drugUser_name;

    public DrugUser(String username) {

        this.drugUser_name = username;

    }

    public DrugUser() {

    }

    @Override
    public String toString() {
        return "User{" +
                "user_id='" + drugUser_id + '\'' +
                ", user_name='" + drugUser_name + '\'' +
                '}';
    }
}
