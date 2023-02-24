package ftclub.cabinet.cabinetUsageHistroy.domain;

import ftclub.cabinet.user.domain.User;

public interface userChangeStatus {
    changeStatus(User user){
        user.makeStatusInvalid();
    }
}
