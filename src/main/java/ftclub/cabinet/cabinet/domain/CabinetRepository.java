package ftclub.cabinet.cabinet.domain;

import org.springframework.data.repository.Repository;

import java.util.Optional;

public interface CabinetRepository extends Repository<Cabinet, Integer> {
    public Optional<Cabinet> findById(int id);
}
