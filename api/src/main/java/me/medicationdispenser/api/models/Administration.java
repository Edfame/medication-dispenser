package me.medicationdispenser.api.models;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Calendar;

@Entity
@Data
@NoArgsConstructor
public class Administration implements Serializable {

    @EmbeddedId
    private AdministrationIdentification administrationIdentification;

    public Administration(long drugId, long userId, Calendar administrationTimestamp) {
        this.administrationIdentification = new AdministrationIdentification(drugId, userId, administrationTimestamp);
    }

    public Administration(AdministrationIdentification administrationIdentification) {
        this.administrationIdentification = administrationIdentification;
    }
}
