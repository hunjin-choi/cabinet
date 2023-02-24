package ftclub.cabinet.user.domain;

import org.springframework.data.repository.Repository;

import java.util.Optional;

public interface UserRepository extends Repository<User, Integer> {
    public Optional<User> findById(int id);
}
