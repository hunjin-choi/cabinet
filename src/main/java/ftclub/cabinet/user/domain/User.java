package ftclub.cabinet.user.domain;

import ftclub.cabinet.cabinet.domain.Cabinet;
import ftclub.cabinet.cabinetUsageHistroy.domain.LentPolicy;
import ftclub.cabinet.cabinetUsageHistroy.domain.UsageHistory;
import lombok.Getter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Optional;

// 정책 인터페이스 하나 둬서 상위단에서 그 인터페이스만 사용. 모든 정책 접근 가능.
@Entity
public class User {
    @Id
    @GeneratedValue
    private int id;

    pil
    @Getter
    int status;

    public void makeStatusInvalid() {
        this.status = 0;
    }

    public void makeStatusValid() {
        this.status = 1;
    }

    public Optional<UsageHistory> usageHistoryFactory(Cabinet cabinet, int lentCount, LentPolicy lentPolicy) {
        String msg = lentPolicy.LentPolicyCheck(this, cabinet, lentCount);
        if (msg == null) {
            return new UsageHistory(this.id, cabinet.);
        } else {
            return null;
        }
    }
}
