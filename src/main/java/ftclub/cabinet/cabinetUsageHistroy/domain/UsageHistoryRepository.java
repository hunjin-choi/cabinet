package ftclub.cabinet.cabinetUsageHistroy.domain;

import org.springframework.data.repository.Repository;

import java.util.Date;
import java.util.Optional;

public interface UsageHistoryRepository extends Repository<UsageHistory, Integer> {
    public Optional<UsageHistory> findById(int id);
    public int countByCabinetIdAndReturn_time(int CabinetId, Date data);
    public UsageHistory findByCabinetIdAndUserIdAndReturn_time(int cabinetId, int userId, Date return_time);
}
