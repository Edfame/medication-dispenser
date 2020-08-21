package me.medicationdispenser.api.models;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.io.Serializable;
import java.util.Calendar;

@Embeddable
public class AdministrationId implements Serializable {

    @Column
    private long drugId;

    @Column
    private long userId;

    @Column
    @Temporal(TemporalType.TIMESTAMP)
    private Calendar administrationTimestamp;

    public AdministrationId() {

    }

    public AdministrationId(long drugId, long userId, Calendar administrationTimestamp) {
        this.drugId = drugId;
        this.userId = userId;
        this.administrationTimestamp = administrationTimestamp;

    }

    public long getDrugId() {
        return drugId;
    }

    public void setDrugId(long drugId) {
        this.drugId = drugId;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public Calendar getAdministrationTimestamp() {
        return administrationTimestamp;
    }

    public void setAdministrationTimestamp(Calendar administrationTimestamp) {
        this.administrationTimestamp = administrationTimestamp;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AdministrationId)) return false;

        AdministrationId that = (AdministrationId) o;

        if (getDrugId() != that.getDrugId()) return false;
        return getUserId() == that.getUserId();
    }

    @Override
    public int hashCode() {
        int result = (int) (getDrugId() ^ (getDrugId() >>> 32));
        result = 31 * result + (int) (getUserId() ^ (getUserId() >>> 32));
        return result;
    }
}
