package ftclub.cabinet.cabinetUsageHistroy.domain;

import lombok.Getter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

@Entity
public class UsageHistory {
    @Id()
    @GeneratedValue()
    private int id;
    private int cabinetId;
    private int userId;
    @Getter
    private Date return_time;

    public UsageHistory(int userId, int cabinetId, Date o) {
    }

    protected UsageHistory() {

    }

    public void returnComplete() {
        this.return_time = new Date();
    }
}
