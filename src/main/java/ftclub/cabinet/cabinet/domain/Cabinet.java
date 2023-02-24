package ftclub.cabinet.cabinet.domain;

import lombok.Getter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Cabinet {
    @Id
    @GeneratedValue
    private int id;

    @Getter
    private int type;

    public void makeTypeUnavailable() {
        this.type = 0;
    }

    public void makeTypeAvailable() {
        this.type = 1;
    }
}
