package ftclub.cabinet.cabinetUsageHistroy.domain;

import ftclub.cabinet.cabinet.domain.Cabinet;
import ftclub.cabinet.user.domain.User;

public class LentService {
    private LentPolicy lentPolicy;
    private userChangeStatus userChangeStatus;

    public void LentService(LentPolicy lentPolicy) {
        this.lentPolicy = lentPolicy;
    }

    public void LentService() {}

    public void ChangeStatus(Cabinet cabinet, User user, int lentCount) {
//        user.makeStatusInvalid();
        userChangeStatus.changeStatus(user);
        if (lentPolicy.changeCabinetStatusCheck(cabinet, lentCount)) cabinet.makeTypeUnavailable();
    }
}
