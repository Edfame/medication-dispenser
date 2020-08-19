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
    private AdministrationId administrationId;

    @Column
    @Temporal(TemporalType.TIMESTAMP)
    private Calendar administrationTimestamp;

    public Administration(long drugId, long userId, Calendar administrationTimestamp) {
        this.administrationId = new AdministrationId(drugId, userId);
        this.administrationTimestamp = administrationTimestamp;
    }
}
